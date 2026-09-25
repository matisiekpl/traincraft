package traincraft.vehicle.coupling;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import org.jspecify.annotations.Nullable;

import traincraft.Traincraft;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.simulation.ContactResponse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Buffer contacts between uncoupled pieces of stock, solved once a tick after everything has moved.
 *
 * <p>Solving them inside each entity's own tick, as upstream does, sees half the stock where it was
 * last tick and half where it is now, and settles a chain one pair at a time: a locomotive pushing
 * three loose wagons squeezed them into one another. Here every contact is looked at together.
 *
 * <p>The rules. Stock coupled together is one group. A group with no locomotive in it is passive:
 * it moves with whatever pushes it and stops the moment the push ends. Two trains with a locomotive
 * each that meet faster than {@link ContactResponse#DERAIL_CLOSING_SPEED} derail the two vehicles
 * that touched, and everything else in both stops dead; slower than that, the buffers simply meet.
 */
@EventBusSubscriber(modid = Traincraft.MODID)
public final class StockContacts {

    /** How near two buffers count as touching, beyond what this tick's travel will close. */
    private static final double TOUCH_TOLERANCE = 0.05;

    /** Further apart than this, no two contact points of any pair of stock can meet this tick. */
    private static final double SEARCH_DISTANCE_SQR = 24.0 * 24.0;

    /** Gauss-Seidel passes over the ordinary contacts, enough for a short line of trains. */
    private static final int ITERATIONS = 4;

    private static final int GROUP_WALK_LIMIT = 64;

    /** The block probe at the leading end: how deep, how wide and at what height above the rail. */
    private static final double PROBE_DEPTH = 0.1;
    private static final double PROBE_HALF_WIDTH = 0.25;
    private static final double PROBE_BOTTOM = 0.3;
    private static final double PROBE_TOP = 1.3;

    private static final Map<Level, List<RollingStockEntity>> ENLISTED = new WeakHashMap<>();

    private StockContacts() {}

    /** Called by every piece of stock that ticked on the server, once it has moved. */
    public static void enlist(RollingStockEntity stock) {
        ENLISTED.computeIfAbsent(stock.level(), level -> new ArrayList<>()).add(stock);
    }

    @SubscribeEvent
    static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        List<RollingStockEntity> stock = ENLISTED.remove(level);
        if (stock != null) {
            solve(stock);
        }
    }

    /** Coupled stock, which moves as one body when it is pushed or run into. */
    private static final class Group {
        final List<RollingStockEntity> members = new ArrayList<>();
        boolean powered;
        boolean held;
        double mass;
        boolean pushed;
        Train train;
    }

    /** A group under its own power, and whatever loose stock it has ended up pushing. */
    private static final class Train {
        final Group root;
        final List<Group> groups = new ArrayList<>();
        double pushedMass;
        boolean crashed;

        Train(Group root) {
            this.root = root;
            groups.add(root);
        }

        boolean held() {
            for (Group group : groups) {
                if (group.held) return true;
            }
            return false;
        }

        double inverseMass() {
            if (held()) return 0.0;
            double mass = 0.0;
            for (Group group : groups) mass += group.mass;
            return 1.0 / mass;
        }

        void push(Vec3 change) {
            if (change.lengthSqr() == 0.0) return;
            for (Group group : groups) {
                for (RollingStockEntity member : group.members) {
                    member.setDeltaMovement(member.getDeltaMovement().add(change));
                }
            }
        }

        void shift(Vec3 offset) {
            for (Group group : groups) {
                for (RollingStockEntity member : group.members) {
                    member.shiftAlongTrack(offset);
                }
            }
        }

        void stop() {
            for (Group group : groups) {
                for (RollingStockEntity member : group.members) {
                    stopHorizontally(member);
                }
            }
        }
    }

    private record Contact(RollingStockEntity first, RollingStockEntity second, Group firstGroup, Group secondGroup) {
        Group other(Group group) {
            return group == firstGroup ? secondGroup : firstGroup;
        }
    }

    /** Where two pieces of stock stand relative to each other along the rail they share. */
    private record Measure(Vec3 axis, double gap) {}

    /** A block ahead of a piece of stock, {@code gap} from its end along its direction of travel. */
    private record BlockContact(RollingStockEntity stock, Group group, Vec3 axis, double gap) {}

    /**
     * One pass over the given stock. Public so a test can run it on stock it has just placed
     * without waiting for the end of the tick.
     */
    public static void solve(List<RollingStockEntity> enlisted) {
        List<RollingStockEntity> stock = new ArrayList<>();
        java.util.Set<RollingStockEntity> seen = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
        for (RollingStockEntity one : enlisted) {
            if (!one.level().isClientSide() && !one.isRemoved() && !one.isWrecked() && seen.add(one)) {
                stock.add(one);
            }
        }
        Map<RollingStockEntity, Group> groups = groups(stock);
        List<Contact> contacts = contacts(stock, groups);

        List<Group> distinct = new ArrayList<>();
        for (Group group : groups.values()) {
            if (!distinct.contains(group)) distinct.add(group);
        }
        propagatePushes(distinct, contacts);
        List<BlockContact> blocks = new ArrayList<>();
        for (RollingStockEntity one : stock) {
            BlockContact block = blockContact(one, groups.get(one));
            if (block != null) blocks.add(block);
        }
        crash(contacts, blocks);
        settle(contacts, blocks);

        for (Group group : distinct) {
            Train train = group.train;
            if (group == train.root && group.powered && !train.crashed && train.pushedMass > 0.0) {
                double factor = ContactResponse.pushingFactor(group.mass, train.pushedMass);
                for (Group member : train.groups) {
                    for (RollingStockEntity one : member.members) {
                        Vec3 motion = one.getDeltaMovement();
                        one.setDeltaMovement(motion.x * factor, motion.y, motion.z * factor);
                    }
                }
            }
        }
        for (Group group : distinct) {
            for (RollingStockEntity member : group.members) {
                if (!group.pushed && member.isPushed()) {
                    // Loose stock goes only as far as it is pushed: when the push ends, it stops.
                    stopHorizontally(member);
                }
                member.setPushed(group.pushed);
            }
        }
    }

    private static Map<RollingStockEntity, Group> groups(List<RollingStockEntity> stock) {
        Map<RollingStockEntity, Group> groups = new IdentityHashMap<>();
        for (RollingStockEntity start : stock) {
            if (groups.containsKey(start)) continue;
            Group group = new Group();
            ArrayDeque<RollingStockEntity> queue = new ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty() && group.members.size() < GROUP_WALK_LIMIT) {
                RollingStockEntity current = queue.removeLast();
                if (current.isRemoved() || current.isWrecked() || groups.containsKey(current)) continue;
                groups.put(current, group);
                group.members.add(current);
                group.powered |= current.isLocomotive();
                group.held |= current.isHeldInPlace();
                group.mass += Math.max(1.0, current.weightKg());
                if (current.cartLinked1 != null) queue.add(current.cartLinked1);
                if (current.cartLinked2 != null) queue.add(current.cartLinked2);
            }
            group.train = new Train(group);
        }
        return groups;
    }

    private static List<Contact> contacts(List<RollingStockEntity> stock, Map<RollingStockEntity, Group> groups) {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < stock.size(); i++) {
            RollingStockEntity first = stock.get(i);
            for (int j = i + 1; j < stock.size(); j++) {
                RollingStockEntity second = stock.get(j);
                Group firstGroup = groups.get(first);
                Group secondGroup = groups.get(second);
                if (firstGroup == secondGroup
                        || first.level() != second.level()
                        || first.consist != null && first.consist == second.consist
                        || first.position().subtract(second.position()).horizontalDistanceSqr() > SEARCH_DISTANCE_SQR) {
                    continue;
                }
                Vec3 separation = second.bodyMiddle().subtract(first.bodyMiddle());
                double distance = separation.horizontalDistance();
                if (distance >= 1.0E-4
                        && !StockCollision.onSameAxis(first, second, separation, distance)
                        && !StockCollision.onSameAxis(second, first, separation, distance)) {
                    continue;
                }
                contacts.add(new Contact(first, second, firstGroup, secondGroup));
            }
        }
        return contacts;
    }

    /**
     * How far apart the facing ends of two bodies are, along the line between their middles. The
     * ends are the model's, the same the hitbox parts are laid out to, so stock stands buffer to
     * buffer whatever its length and wherever its position sits along it. Negative is an overlap.
     */
    public static double bufferGap(RollingStockEntity from, RollingStockEntity to) {
        return measure(from, to).gap();
    }

    /** The axis points from {@code from} to {@code to}; a negative gap is an overlap. */
    private static Measure measure(RollingStockEntity from, RollingStockEntity to) {
        Vec3 separation = to.bodyMiddle().subtract(from.bodyMiddle());
        double distance = separation.horizontalDistance();
        Vec3 axis =
                distance < 1.0E-4
                        ? from.bodyAxis()
                        : new Vec3(separation.x / distance, 0.0, separation.z / distance);
        Vec3 fromEnd = from.endFacing(axis);
        Vec3 toEnd = to.endFacing(axis.scale(-1.0));
        Vec3 between = toEnd.subtract(fromEnd);
        return new Measure(axis, between.x * axis.x + between.z * axis.z);
    }

    private static double along(RollingStockEntity stock, Vec3 axis) {
        Vec3 motion = stock.getDeltaMovement();
        return motion.x * axis.x + motion.z * axis.z;
    }

    /**
     * Hands each powered group's speed on to the loose stock in front of it, and from that to the
     * loose stock in front of that, so a whole line of wagons moves off in the same tick.
     */
    private static void propagatePushes(List<Group> groups, List<Contact> contacts) {
        ArrayDeque<Group> queue = new ArrayDeque<>();
        for (Group group : groups) {
            if (group.powered) queue.add(group);
        }
        while (!queue.isEmpty()) {
            Group pusher = queue.removeFirst();
            for (Contact contact : contacts) {
                if (contact.firstGroup != pusher && contact.secondGroup != pusher) continue;
                Group pushed = contact.other(pusher);
                if (pushed.powered || pushed.train.root.powered) continue;
                RollingStockEntity from = contact.firstGroup == pusher ? contact.first : contact.second;
                RollingStockEntity to = contact.firstGroup == pusher ? contact.second : contact.first;
                Measure measure = measure(from, to);
                double approach = along(from, measure.axis());
                if (approach <= 0.0 || measure.gap() > approach + TOUCH_TOLERANCE) continue;

                boolean wasPushed = false;
                for (RollingStockEntity member : pushed.members) wasPushed |= member.isPushed();
                // Held against the buffers while the push lasts: an overlap is taken out, and so is
                // a gap opened by the pusher slowing, which would otherwise let go and catch up
                // again every few ticks.
                double correction = wasPushed ? -measure.gap() : Math.max(0.0, -measure.gap());
                Vec3 offset = measure.axis().scale(correction);
                for (RollingStockEntity member : pushed.members) {
                    Vec3 motion = member.getDeltaMovement();
                    member.setDeltaMovement(measure.axis().x * approach, motion.y, measure.axis().z * approach);
                    member.shiftAlongTrack(offset);
                }
                pushed.pushed = true;
                pushed.train = pusher.train;
                pusher.train.groups.add(pushed);
                pusher.train.pushedMass += pushed.mass;
                queue.add(pushed);
            }
        }
    }

    /**
     * A solid block in the way of the leading end of a piece of stock.
     *
     * <p>Only looked for on straight track: on a curve or a slope the end of a long body stands off
     * the rail, and a probe there would find the tunnel wall beside the line rather than anything on
     * it.
     */
    private static @Nullable BlockContact blockContact(RollingStockEntity stock, Group group) {
        if (!stock.isOnStraightTrack()) return null;
        Vec3 motion = stock.getDeltaMovement();
        double speed = motion.horizontalDistance();
        if (speed < 1.0E-4) return null;
        Vec3 axis = new Vec3(motion.x / speed, 0.0, motion.z / speed);
        AABB probe = probe(stock, axis);
        double depth = penetration(stock, probe, axis);
        if (depth > 0.0) {
            return new BlockContact(stock, group, axis, -depth);
        }
        double reach = speed + TOUCH_TOLERANCE;
        Vec3 allowed = Entity.collideBoundingBox(stock, axis.scale(reach), probe, stock.level(), List.of());
        double gap = allowed.x * axis.x + allowed.z * axis.z;
        return gap < reach - 1.0E-7 ? new BlockContact(stock, group, axis, gap) : null;
    }

    /**
     * A thin slab just inside the end of the body at buffer height: above the rail and whatever it
     * is laid on, below the roof of a two-high tunnel, and narrower than the track.
     */
    private static AABB probe(RollingStockEntity stock, Vec3 axis) {
        Vec3 end = stock.endFacing(axis);
        Vec3 lateral = new Vec3(-axis.z, 0.0, axis.x).scale(PROBE_HALF_WIDTH);
        Vec3 inner = end.subtract(axis.scale(PROBE_DEPTH)).add(lateral);
        Vec3 outer = end.subtract(lateral);
        double base = stock.getY() - stock.yOffset();
        return new AABB(inner.x, base + PROBE_BOTTOM, inner.z, outer.x, base + PROBE_TOP, outer.z);
    }

    /**
     * How far the end has already gone into whatever the probe touches, measured from the face it
     * went in through. Zero when the probe is clear.
     */
    private static double penetration(RollingStockEntity stock, AABB probe, Vec3 axis) {
        double end = Math.max(probe.minX * axis.x, probe.maxX * axis.x)
                + Math.max(probe.minZ * axis.z, probe.maxZ * axis.z);
        double depth = 0.0;
        for (var shape : stock.level().getBlockCollisions(stock, probe)) {
            if (shape.isEmpty()) continue;
            AABB solid = shape.bounds();
            double face = Math.min(solid.minX * axis.x, solid.maxX * axis.x)
                    + Math.min(solid.minZ * axis.z, solid.maxZ * axis.z);
            depth = Math.max(depth, end - face);
        }
        return depth;
    }

    /**
     * Collisions hard enough to derail: two trains under power that meet, and anything that runs
     * into a block, which stands in for a train that cannot be moved. The vehicles that touched
     * leave the rails and everything behind them stops dead.
     */
    private static void crash(List<Contact> contacts, List<BlockContact> blocks) {
        for (Contact contact : contacts) {
            Train first = contact.firstGroup.train;
            Train second = contact.secondGroup.train;
            if (first == second || !first.root.powered || !second.root.powered || first.crashed || second.crashed) {
                continue;
            }
            Measure measure = measure(contact.first, contact.second);
            double closing = along(contact.first, measure.axis()) - along(contact.second, measure.axis());
            if (measure.gap() > closing + TOUCH_TOLERANCE || !ContactResponse.derails(closing)) {
                continue;
            }
            first.stop();
            second.stop();
            first.crashed = true;
            second.crashed = true;
            Vec3 lateral = new Vec3(-measure.axis().z, 0.0, measure.axis().x);
            contact.first.derailFromCollision(lateral);
            contact.second.derailFromCollision(lateral.scale(-1.0));
        }
        for (BlockContact block : blocks) {
            Train train = block.group.train;
            double closing = along(block.stock, block.axis);
            if (train.crashed || block.gap > closing + TOUCH_TOLERANCE || !ContactResponse.derails(closing)) {
                continue;
            }
            train.stop();
            train.crashed = true;
            block.stock.derailFromCollision(new Vec3(-block.axis.z, 0.0, block.axis.x));
        }
    }

    /**
     * Every other contact: the buffers meet without bouncing, and momentum is shared by mass. A
     * block moves for nothing, so the whole train that ran into it -- whatever was pushing
     * included -- is stopped short of it and set back out of it.
     */
    private static void settle(List<Contact> contacts, List<BlockContact> blocks) {
        double[] blockGaps = new double[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            blockGaps[i] = blocks.get(i).gap;
        }
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            for (Contact contact : contacts) {
                Train first = contact.firstGroup.train;
                Train second = contact.secondGroup.train;
                if (first == second
                        || first.crashed
                        || second.crashed
                        || contact.first.isWrecked()
                        || contact.second.isWrecked()) {
                    continue;
                }
                Measure measure = measure(contact.first, contact.second);
                double inverseFirst = first.inverseMass();
                double inverseSecond = second.inverseMass();

                ContactResponse.Axial split = ContactResponse.split(-measure.gap(), inverseFirst, inverseSecond);
                first.shift(measure.axis().scale(-split.first()));
                second.shift(measure.axis().scale(split.second()));

                double speedFirst = along(contact.first, measure.axis());
                double speedSecond = along(contact.second, measure.axis());
                ContactResponse.Axial after =
                        ContactResponse.plastic(
                                speedFirst,
                                speedSecond,
                                inverseFirst,
                                inverseSecond,
                                Math.max(0.0, measure.gap()));
                first.push(measure.axis().scale(after.first() - speedFirst));
                second.push(measure.axis().scale(after.second() - speedSecond));
            }
            for (int i = 0; i < blocks.size(); i++) {
                BlockContact block = blocks.get(i);
                Train train = block.group.train;
                if (train.crashed || block.stock.isWrecked()) continue;
                if (blockGaps[i] < 0.0) {
                    train.shift(block.axis.scale(blockGaps[i]));
                    blockGaps[i] = 0.0;
                }
                double speed = along(block.stock, block.axis);
                // Against something immovable the train's own mass does not matter.
                ContactResponse.Axial after = ContactResponse.plastic(speed, 0.0, 1.0, 0.0, blockGaps[i]);
                train.push(block.axis.scale(after.first() - speed));
            }
        }
    }

    private static void stopHorizontally(RollingStockEntity stock) {
        stock.setDeltaMovement(0.0, stock.getDeltaMovement().y, 0.0);
    }
}
