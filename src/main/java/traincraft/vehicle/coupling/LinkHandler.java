package traincraft.vehicle.coupling;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.simulation.CouplingSpring;

import java.util.ArrayList;
import java.util.List;

/**
 * Couples stock together and keeps coupled stock together.
 *
 * <p>Community Edition's {@code LinkHandler}. Two jobs share the class because they share the
 * distance measurement: the search for a partner for an end the player has set coupleable, and the
 * spring that runs every tick on a link that already exists.
 */
public final class LinkHandler {

    private static final double ATTACH_SEARCH = 15.0;
    private static final double LOST_LINK_DISTANCE = 20.0;

    /** Ticks a piece of stock has to have been on the rails before its position means anything. */
    private static final int SETTLED_TICKS = 5;

    /** How far a coupling may be followed when checking that a candidate is not already behind. */
    private static final int CONSIST_WALK_LIMIT = 64;

    public void tick(RollingStockEntity one) {
        for (VehicleEnd end : VehicleEnd.values()) {
            if (one.isArmed() && !one.hasLink(end)) {
                searchForPartner(one, end);
            }
        }
        if (one.cartLinked1 != null) {
            springTick(one.cartLinked1, one, 1);
        }
        if (one.cartLinked2 != null) {
            springTick(one.cartLinked2, one, 2);
        }
    }

    /**
     * Looks for another armed piece of stock with a free end facing this one, and takes the nearest.
     *
     * <p>The reach is {@code self.getLinkageDistance(self)} -- upstream asks the first cart about
     * itself, not about the one it is being coupled to, so the second cart's length does not enter
     * into it.
     */
    private void searchForPartner(RollingStockEntity self, VehicleEnd end) {
        if (self.level().isClientSide() || self.updateTicks < SETTLED_TICKS) {
            return;
        }
        double reach = self.getLinkageDistance(self);
        RollingStockEntity nearest = null;
        VehicleEnd nearestEnd = null;
        double shortest = Double.MAX_VALUE;
        for (RollingStockEntity other : candidates(self)) {
            VehicleEnd theirs = other.endFacing(self);
            if (!accepts(self, end, other, theirs)) {
                continue;
            }
            Vec3 separation = nearestSeparation(self, other);
            double distance = Math.sqrt(separation.x * separation.x + separation.z * separation.z);
            if (distance > reach || distance >= shortest) {
                continue;
            }
            if (!StockCollision.onSameAxis(self, other, separation, distance)) {
                continue;
            }
            nearest = other;
            nearestEnd = theirs;
            shortest = distance;
        }
        if (nearest != null) {
            couple(self, end, nearest, nearestEnd);
        }
    }

    private static List<RollingStockEntity> candidates(RollingStockEntity self) {
        List<net.minecraft.world.entity.Entity> found =
                new ArrayList<>(
                        self.level()
                                .getEntities(
                                        self,
                                        self.getBoundingBox()
                                                .inflate(ATTACH_SEARCH, 5.0, ATTACH_SEARCH)));
        Vec3 bogie = self.bogiePosition();
        if (bogie != null) {
            found.addAll(
                    self.level()
                            .getEntities(
                                    self,
                                    AABB.ofSize(
                                                    bogie,
                                                    self.getBbWidth(),
                                                    self.getBbHeight(),
                                                    self.getBbWidth())
                                            .inflate(7.0, 5.0, 7.0)));
        }
        List<RollingStockEntity> stock = new ArrayList<>();
        for (var entity : found) {
            if (entity instanceof RollingStockEntity other && !stock.contains(other)) {
                stock.add(other);
            }
        }
        return stock;
    }

    /** Everything about a candidate that does not depend on how far away it is. */
    private static boolean accepts(
            RollingStockEntity self,
            VehicleEnd end,
            RollingStockEntity other,
            VehicleEnd theirs) {
        if (other == self || other.updateTicks < SETTLED_TICKS) {
            return false;
        }
        if (!other.isArmed() || other.hasLink(theirs)) {
            return false;
        }
        if (self.endFacing(other) != end) {
            return false;
        }
        return !areLinked(self, other) && !reachableThrough(self, other);
    }

    /**
     * Whether the candidate is already somewhere in this piece of stock's own consist.
     *
     * <p>Without this a train whose two ends are brought together couples into a ring, which has no
     * front and no back and which nothing downstream -- the consist walk, the coupling spring -- is
     * written to survive.
     */
    private static boolean reachableThrough(RollingStockEntity self, RollingStockEntity other) {
        List<RollingStockEntity> seen = new ArrayList<>();
        List<RollingStockEntity> queue = new ArrayList<>();
        queue.add(self);
        while (!queue.isEmpty() && seen.size() < CONSIST_WALK_LIMIT) {
            RollingStockEntity current = queue.removeLast();
            if (current == other) {
                return true;
            }
            if (seen.contains(current)) {
                continue;
            }
            seen.add(current);
            if (current.cartLinked1 != null) {
                queue.add(current.cartLinked1);
            }
            if (current.cartLinked2 != null) {
                queue.add(current.cartLinked2);
            }
        }
        return false;
    }

    /** Writes the coupling into the matching end of each piece of stock. */
    public void couple(
            RollingStockEntity self,
            VehicleEnd selfEnd,
            RollingStockEntity other,
            VehicleEnd otherEnd) {
        self.setLink(selfEnd, other.getUniqueTrainID());
        self.setCoupled(selfEnd, other);
        other.setLink(otherEnd, self.getUniqueTrainID());
        other.setCoupled(otherEnd, self);

        // One arming, one coupling: a vehicle stops hunting the moment it has what it was asked
        // for, so a wagon left armed in a yard does not go on collecting whatever rolls past.
        self.setArmed(false);
        other.setArmed(false);

        // The neighbours' consists are thrown away rather than extended: the next pass of
        // handleTrain builds one that spans the new coupling.
        self.dropConsist();
        other.dropConsist();

        Player player = self.level().getNearestPlayer(self, 20.0);
        if (player != null) {
            player.sendSystemMessage(Component.literal("attached!"));
        }
    }

    public static boolean areLinked(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart2.getUniqueTrainID() == cart1.link1
                || cart2.getUniqueTrainID() == cart1.link2
                || cart1.getUniqueTrainID() == cart2.link1
                || cart1.getUniqueTrainID() == cart2.link2;
    }

    public static double optimalDistance(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart1.optimalDistance(cart2) + cart2.optimalDistance(cart1);
    }

    private static boolean canCartBeAdjustedBy(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart1 != cart2 && cart1.canBeAdjusted(cart2);
    }

    /**
     * One tick of the coupling between two pieces of stock.
     *
     * <p>Both have to have been on the rails for five ticks: a cart still being placed, or one
     * whose chunk has just loaded, has not yet been put where it belongs, and the distance between
     * them means nothing until it has.
     */
    private void springTick(RollingStockEntity cart1, RollingStockEntity cart2, int linkIndex) {
        if (cart1.level().isClientSide()
                || cart1.updateTicks < SETTLED_TICKS
                || cart2.updateTicks < SETTLED_TICKS) {
            return;
        }
        if (!areLinked(cart2, cart1)) {
            return;
        }
        boolean adjust1 = canCartBeAdjustedBy(cart1, cart2);
        boolean adjust2 = canCartBeAdjustedBy(cart2, cart1);
        Vec3 separation = nearestSeparation(cart1, cart2);
        double separationX = separation.x;
        double separationZ = separation.z;
        double distance = Math.sqrt(separationX * separationX + separationZ * separationZ);

        if (distance > LOST_LINK_DISTANCE) {
            Player player =
                    cart1.level()
                            .getNearestPlayer(cart1.getX(), cart1.getY(), cart1.getZ(), 300.0, false);
            if (player != null) {
                player.sendSystemMessage(
                        Component.literal(
                                String.format(
                                        "[TRAINCRAFT] The rolling stock at %d %d %d had a problem"
                                            + " loading and has lost its link. Attached cart was"
                                            + " too far away",
                                        (int) cart1.getX(),
                                        (int) cart1.getY(),
                                        (int) cart1.getZ())));
            }
            VehicleEnd end = linkIndex == 1 ? VehicleEnd.FRONT : VehicleEnd.BACK;
            cart2.decouple(end);
            return;
        }

        double unitX = separationX / distance;
        double unitZ = separationZ / distance;
        CouplingSpring spring =
                CouplingSpring.spring(separationX, separationZ, optimalDistance(cart1, cart2));
        if (adjust1) {
            cart1.setDeltaMovement(cart1.getDeltaMovement().add(spring.x(), 0.0, spring.z()));
        }
        if (adjust2) {
            cart2.setDeltaMovement(cart2.getDeltaMovement().subtract(spring.x(), 0.0, spring.z()));
        }

        CouplingSpring damping =
                CouplingSpring.damping(
                        cart1.getDeltaMovement().x - cart2.getDeltaMovement().x,
                        cart1.getDeltaMovement().z - cart2.getDeltaMovement().z,
                        unitX,
                        unitZ);
        if (adjust1) {
            cart1.setDeltaMovement(cart1.getDeltaMovement().add(damping.x(), 0.0, damping.z()));
        }
        if (adjust2) {
            cart2.setDeltaMovement(cart2.getDeltaMovement().subtract(damping.x(), 0.0, damping.z()));
        }
    }

    /**
     * The shortest of the separations between the two carts' contact points.
     *
     * <p>A piece of stock with a bogie has two: its body and the bogie. Upstream measures every
     * combination and keeps the shortest, which is what lets a long locomotive couple by its
     * trailing end rather than its middle.
     */
    static Vec3 nearestSeparation(RollingStockEntity cart1, RollingStockEntity cart2) {
        Vec3 bogie1 = cart1.bogiePosition();
        Vec3 bogie2 = cart2.bogiePosition();
        if (bogie1 == null && bogie2 == null) {
            return new Vec3(cart1.getX() - cart2.getX(), 0.0, cart1.getZ() - cart2.getZ());
        }
        double[] separationX = new double[] {100.0, 100.0, 100.0, 100.0};
        double[] separationZ = new double[] {100.0, 100.0, 100.0, 100.0};
        separationX[0] = cart1.getX() - cart2.getX();
        separationZ[0] = cart1.getZ() - cart2.getZ();
        if (bogie1 != null) {
            separationX[1] = bogie1.x - cart2.getX();
            separationZ[1] = bogie1.z - cart2.getZ();
        } else {
            separationX[1] = cart1.getX() - bogie2.x;
            separationZ[1] = cart1.getZ() - bogie2.z;
        }
        if (bogie1 != null && bogie2 != null) {
            separationX[2] = cart1.getX() - bogie2.x;
            separationZ[2] = cart1.getZ() - bogie2.z;
            separationX[3] = bogie1.x - bogie2.x;
            separationZ[3] = bogie1.z - bogie2.z;
        }
        int nearest = 0;
        double shortest = Double.MAX_VALUE;
        for (int i = 0; i < separationX.length; i++) {
            double euclidian =
                    Math.sqrt(
                            separationX[i] * separationX[i] + separationZ[i] * separationZ[i]);
            if (i == 0) {
                shortest = euclidian;
                continue;
            }
            if (Math.abs(euclidian) < Math.abs(shortest)) {
                shortest = euclidian;
                nearest = i;
            }
        }
        return new Vec3(separationX[nearest], 0.0, separationZ[nearest]);
    }
}
