package traincraft.development.gametest;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.EntityRegistry;
import traincraft.track.TrackPlacementPlanner;
import traincraft.track.TrackPlacer;
import traincraft.track.TrackType;
import traincraft.vehicle.coupling.LinkHandler;
import traincraft.vehicle.coupling.StockCollision;
import traincraft.vehicle.coupling.StockContacts;
import traincraft.vehicle.entity.AliceLocomotiveEntity;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.simulation.ContactResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

final class CollisionGameTests {
    private CollisionGameTests() {}

    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("stock_collisions_follow_rail_axis", CollisionGameTests::railAxis);
        tests.put("stock_contact_is_order_independent", CollisionGameTests::orderIndependent);
        tests.put("coupled_stock_does_not_collide", CollisionGameTests::coupledStock);
        tests.put("freight_pushes_vanilla_minecart", CollisionGameTests::vanillaMinecart);
        tests.put("bogie_detects_mobs_and_stock", CollisionGameTests::bogieContacts);
        tests.put("stock_collision_protects_riders", CollisionGameTests::riders);
        tests.put("stock_collision_speed_thresholds", CollisionGameTests::speedThresholds);
        tests.put("stock_hitbox_stops_at_blocks", CollisionGameTests::blockCollision);
        tests.put("slope_supports_players_but_not_stock", CollisionGameTests::slopeCollision);
        tests.put("pushed_stock_never_overlaps", CollisionGameTests::neverOverlaps);
        tests.put("locomotive_trains_derail_on_impact", CollisionGameTests::trainsDerail);
        tests.put("slow_locomotive_contact_does_not_derail", CollisionGameTests::slowContact);
        tests.put("locomotive_pushes_a_loose_rake", CollisionGameTests::pushesRake);
        tests.put("wrecked_stock_stays_off_the_track", CollisionGameTests::wreckedStock);
        tests.put("pushed_rake_stops_at_a_buffer_stop", helper -> bufferStop(helper, false));
        tests.put("pushed_rake_derails_at_a_buffer_stop", helper -> bufferStop(helper, true));
        tests.put("every_vehicle_meets_buffer_to_buffer", CollisionGameTests::everyVehicleMeets);
        tests.put("every_vehicle_stops_at_a_block", CollisionGameTests::everyVehicleStopsAtBlock);
    }

    private static void solve(RollingStockEntity... stock) {
        StockContacts.solve(List.of(stock));
    }

    /**
     * The space between two bodies as their hitbox parts lay them out, worked out here from the
     * bounds rather than by the code under test: both bodies are projected onto the line between
     * their middles, and a negative result is how far they overlap.
     */
    private static double gap(RollingStockEntity one, RollingStockEntity two) {
        Vec3 axis = two.bodyMiddle().subtract(one.bodyMiddle()).multiply(1, 0, 1).normalize();
        double[] first = extent(one, axis);
        double[] second = extent(two, axis);
        return second[0] - first[1];
    }

    private static double[] extent(RollingStockEntity stock, Vec3 axis) {
        double front = stock.position().add(stock.bodyAxis().scale(stock.bounds().front())).dot(axis);
        double back = stock.position().add(stock.bodyAxis().scale(stock.bounds().back())).dot(axis);
        return new double[] {Math.min(front, back), Math.max(front, back)};
    }

    /** A locomotive whose own tick lets it move: fuelled, warm and with the engine running. */
    private static void running(LocomotiveEntity loco) {
        if (loco instanceof traincraft.vehicle.entity.SteamLocomotiveEntity steam) {
            steam.fillWater(1000);
        }
        loco.restoreFuel(5000);
        loco.setTemperature(loco.getAverageOverheat());
        loco.setEngineOn(true);
    }

    private static RollingStockEntity stock(
            GameTestHelper helper, boolean locomotive, Vec3 pos, float yaw) {
        RollingStockEntity stock =
                locomotive
                        ? helper.spawn(EntityRegistry.LOCO_STEAM_ALICE.get(), pos)
                        : helper.spawn(EntityRegistry.FREIGHT_CART_YELLOW.get(), pos);
        stock.setYRot(yaw);
        return stock;
    }

    private static void railAxis(GameTestHelper helper) {
        for (float yaw : new float[] {0, 45, 90, 135, 180, -90}) {
            for (boolean locomotive : new boolean[] {false, true}) {
                double angle = Math.toRadians(yaw);
                Vec3 forward = new Vec3(-Math.sin(angle), 0, Math.cos(angle));
                Vec3 origin = new Vec3(5, 3, 5);
                var one = stock(helper, locomotive, origin, yaw);
                var two = stock(helper, false, origin.add(forward.scale(8)), yaw);
                spaceAfter(one, two, -0.5, forward);
                one.setDeltaMovement(forward.scale(0.1));
                solve(one, two);
                helper.assertTrue(
                        two.getDeltaMovement().dot(forward) > 0.04,
                        "Head-on impact ignored: yaw=" + yaw + " locomotive=" + locomotive);
                one.discard();
                two.discard();

                one = stock(helper, locomotive, origin, yaw);
                two = stock(helper, false, origin.add(forward.z, 0, -forward.x), yaw);
                one.setDeltaMovement(forward.scale(0.1));
                solve(one, two);
                helper.assertTrue(
                        two.getDeltaMovement().lengthSqr() == 0,
                        "Parallel track received an impulse: yaw=" + yaw);
                two.setPos(one.position().add(forward.z, 0, -forward.x).add(forward.scale(2)));
                solve(one, two);
                helper.assertTrue(
                        two.getDeltaMovement().lengthSqr() == 0,
                        "Staggered cart on a parallel track received an impulse: yaw=" + yaw);
                one.discard();
                two.discard();
            }
        }
        helper.succeed();
    }

    /**
     * The contact pass settles a pair the same whichever of the two it meets first, and does not
     * leave it to the entity ticks to apply it a second time.
     */
    private static void orderIndependent(GameTestHelper helper) {
        Vec3[][] results = new Vec3[2][];
        for (int order = 0; order < 2; order++) {
            var one = stock(helper, false, new Vec3(5, 3, 5), 0);
            var two = stock(helper, false, new Vec3(5, 3, 6), 0);
            one.setDeltaMovement(0, 0, 0.1);
            if (order == 0) {
                solve(one, two);
            } else {
                solve(two, one);
            }
            StockCollision.apply(one, two);
            StockCollision.apply(two, one);
            results[order] =
                    new Vec3[] {
                        one.position(), two.position(), one.getDeltaMovement(), two.getDeltaMovement()
                    };
            one.discard();
            two.discard();
        }
        for (int i = 0; i < 4; i++) {
            helper.assertTrue(
                    results[0][i].distanceTo(results[1][i]) < 1.0E-9,
                    "Contact depends on the order the pair was met in: " + results[0][i] + " vs " + results[1][i]);
        }
        helper.assertTrue(
                Math.abs(results[0][2].z + results[0][3].z - 0.1) < 1.0E-9,
                "Two wagons did not share their momentum: " + results[0][2] + " " + results[0][3]);
        helper.succeed();
    }

    private static void coupledStock(GameTestHelper helper) {
        var one = stock(helper, true, new Vec3(5, 3, 5), 0);
        var two = stock(helper, false, new Vec3(5, 3, 6), 0);
        one.cartLinked1 = two;
        two.cartLinked1 = one;
        one.setDeltaMovement(0, 0, 0.1);
        solve(one, two);
        helper.assertTrue(
                two.getDeltaMovement().lengthSqr() == 0, "Coupler also received collision impulse");
        helper.assertTrue(
                !one.canCollideWith(two) && !two.canCollideWith(one),
                "Coupled stock is a solid barrier");
        helper.succeed();
    }

    private static void vanillaMinecart(GameTestHelper helper) {
        var one = stock(helper, false, new Vec3(5, 3, 5), 0);
        var two = helper.spawn(EntityTypes.MINECART, new Vec3(5, 3, 6));
        one.setDeltaMovement(0, 0, 0.1);
        one.collisions().tick(one);
        helper.assertTrue(two.getDeltaMovement().z > 0.04, "Freight did not push a vanilla cart");
        helper.succeed();
    }

    private static void bogieContacts(GameTestHelper helper) {
        BlockPos origin = helper.absolutePos(new BlockPos(5, 2, 1));
        var plan = TrackPlacementPlanner.plan(TrackType.VERY_LONG_STRAIGHT, 0);
        for (var p : plan.placements()) {
            BlockPos at = origin.offset(p.dx(), p.dy(), p.dz());
            helper.getLevel().setBlockAndUpdate(at, Blocks.AIR.defaultBlockState());
            helper.getLevel().setBlockAndUpdate(at.below(), Blocks.STONE.defaultBlockState());
        }
        helper.assertTrue(
                TrackPlacer.apply(helper.getLevel(), origin, plan).placed(),
                "Track placement failed");
        var loco = stock(helper, true, new Vec3(5.5, 2.2 + RollingStockEntity.Y_OFFSET, 4.5), 0);
        loco.alignToTrackOnPlacement();
        Vec3 bogie = loco.bogiePosition();
        helper.assertTrue(bogie != null, "Missing bogie");
        var mob = helper.spawn(EntityTypes.PIG, new Vec3(1, 3, 1));
        mob.setPos(bogie.add(0.2, -RollingStockEntity.Y_OFFSET, 0.2));
        helper.assertTrue(
                !loco.getBoundingBox().inflate(0.2).intersects(mob.getBoundingBox()),
                "Test mob must only touch the bogie");
        loco.setDeltaMovement(0, 0, 0.1);
        loco.collisions().tick(loco);
        helper.assertTrue(mob.getDeltaMovement().z > 0, "Bogie did not push mob");
        mob.discard();
        // Beyond the bogie, and the locomotive backing towards it: only the bogie is near enough.
        Vec3 back = bogie.subtract(loco.position()).multiply(1, 0, 1).normalize();
        var cart = stock(helper, false, new Vec3(1, 3, 1), 0);
        cart.setPos(bogie.add(back.scale(1.0)));
        loco.setDeltaMovement(back.scale(0.1));
        solve(loco, cart);
        helper.assertTrue(cart.getDeltaMovement().dot(back) > 0, "Bogie did not push stock");
        helper.succeed();
    }

    private static void riders(GameTestHelper helper) {
        var loco = stock(helper, true, new Vec3(5, 3, 5), 0);
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setPos(loco.position().add(0, 0, 0.2));
        player.startRiding(loco, true, false);
        helper.assertTrue(player.getVehicle() == loco, "Test driver did not board");
        loco.setDeltaMovement(0, 0, 0.4);
        float health = player.getHealth();
        StockCollision.apply(loco, player);
        helper.assertTrue(
                player.getHealth() == health && player.getDeltaMovement().lengthSqr() == 0,
                "Driver was hit by own locomotive");
        helper.succeed();
    }

    private static void speedThresholds(GameTestHelper helper) {
        for (double kmh : new double[] {0, 20, 40, 70}) {
            var loco = stock(helper, true, new Vec3(5, 3, 5), 0);
            var pig = helper.spawn(EntityTypes.PIG, new Vec3(5, 3, 5.3));
            loco.setDeltaMovement(0, 0, kmh / 216.0);
            float health = pig.getHealth();
            StockCollision.apply(loco, pig);
            helper.assertTrue(
                    (pig.getHealth() < health) == (kmh >= 35), "Wrong mob threshold: " + kmh);
            helper.assertTrue(
                    Double.isFinite(pig.getDeltaMovement().lengthSqr()),
                    "Nonfinite resting impulse");
            pig.discard();
            var player = helper.makeMockPlayer(GameType.SURVIVAL);
            player.setPos(loco.position().add(0, 0, 0.3));
            health = player.getHealth();
            StockCollision.apply(loco, player);
            helper.assertTrue(
                    (player.getHealth() < health) == (kmh > 60), "Wrong player threshold: " + kmh);
            loco.discard();
        }
        helper.succeed();
    }

    private static void slopeCollision(GameTestHelper helper) {
        var level = helper.getLevel();
        var player = helper.makeMockPlayer(GameType.SURVIVAL);
        var cart = stock(helper, false, new Vec3(1, 4, 1), 0);
        for (TrackType type : new TrackType[] {
                TrackType.SLOPE_WOOD, TrackType.LARGE_SLOPE_WOOD,
                TrackType.VERY_LARGE_SLOPE_WOOD}) {
            for (int facing = 0; facing < 4; facing++) {
                BlockPos origin = helper.absolutePos(new BlockPos(20, 2, 20));
                var plan = TrackPlacementPlanner.plan(type, facing);
                for (var p : plan.placements()) {
                    level.setBlockAndUpdate(origin.offset(p.dx(), p.dy(), p.dz()).below(),
                            Blocks.STONE.defaultBlockState());
                }
                helper.assertTrue(TrackPlacer.apply(level, origin, plan).placed(), "Slope placement failed");
                for (var p : plan.placements()) {
                    BlockPos at = origin.offset(p.dx(), p.dy(), p.dz());
                    double height = level.getBlockEntity(at)
                            instanceof traincraft.track.block.TrackOccupancyBlockEntity gag
                                    ? gag.collisionHeight() : 0.125;
                    player.setPos(at.getX() + 0.5, at.getY() + 2, at.getZ() + 0.5);
                    player.move(MoverType.SELF, new Vec3(0, -3, 0));
                    helper.assertTrue(Math.abs(player.getY() - at.getY() - height) < 0.0001,
                            "Player clipped through " + type + " facing " + facing + " at " + at);
                    helper.assertTrue(level.getBlockState(at).getCollisionShape(level, at,
                            net.minecraft.world.phys.shapes.CollisionContext.of(cart)).isEmpty(),
                            "Track collision interferes with rolling stock");
                }
                traincraft.track.TrackBreaker.dismantle(level, origin, false);
            }
        }
        cart.discard();
        helper.succeed();
    }

    /**
     * A locomotive run into a loose wagon takes it along: the wagon is put buffer to buffer with it
     * and given its speed, and the locomotive keeps nearly all of its own.
     */
    private static void neverOverlaps(GameTestHelper helper) {
        var one = stock(helper, true, new Vec3(5, 3, 5), 0);
        var two = stock(helper, false, new Vec3(5, 3, 12), 0);
        spaceAfter(one, two, -0.5);
        one.setDeltaMovement(0, 0, 0.3);
        solve(one, two);
        helper.assertTrue(gap(one, two) >= -1.0E-6, "Stock overlaps: gap=" + gap(one, two));
        helper.assertTrue(
                one.getDeltaMovement().z >= 0.95 * 0.3,
                "The locomotive was stopped by a loose wagon: " + one.getDeltaMovement().z);
        helper.assertTrue(
                Math.abs(two.getDeltaMovement().z - one.getDeltaMovement().z) < 1.0E-9,
                "The wagon did not take the locomotive's speed: " + two.getDeltaMovement().z);
        helper.succeed();
    }

    private static LocomotiveEntity locomotive(GameTestHelper helper, Vec3 pos, float yaw) {
        return (LocomotiveEntity) stock(helper, true, pos, yaw);
    }

    /**
     * Two trains with a locomotive each, meeting at speed: the two vehicles that touch are thrown
     * off the rails, and everything else in both trains stops where it is.
     */
    private static void trainsDerail(GameTestHelper helper) {
        var one = locomotive(helper, new Vec3(5, 3, 8), 0);
        var tail = stock(helper, false, new Vec3(5, 3, 5.5), 0);
        one.setNewUniqueID(one.getId());
        tail.setNewUniqueID(tail.getId());
        new LinkHandler().couple(one, one.endFacing(tail), tail, tail.endFacing(one));
        var two = locomotive(helper, new Vec3(5, 3, 16), 180);
        spaceAfter(one, two, -0.3);
        running(two);
        one.setDeltaMovement(0, 0, 0.1);
        tail.setDeltaMovement(0, 0, 0.1);
        solve(one, tail, two);
        helper.assertTrue(one.isWrecked() && two.isWrecked(), "The locomotives that met stayed on the rails");
        helper.assertTrue(!tail.isWrecked(), "A vehicle that did not touch was derailed");
        for (RollingStockEntity stock : List.of(one, tail, two)) {
            helper.assertTrue(
                    stock.getDeltaMovement().horizontalDistanceSqr() == 0.0,
                    "Stock kept moving after the crash: " + stock.getDeltaMovement());
        }
        helper.assertTrue(!one.hasAnyLink() && !tail.hasAnyLink(), "A derailed vehicle kept its coupling");
        helper.assertTrue(
                Math.abs(one.getX() - 5.0 - helper.absolutePos(BlockPos.ZERO).getX()) > 0.5,
                "The derailed locomotive was not thrown off the track: " + one.position());
        helper.succeed();
    }

    /** Below the derailing speed, two locomotives just meet buffer to buffer and share momentum. */
    private static void slowContact(GameTestHelper helper) {
        var one = locomotive(helper, new Vec3(5, 3, 5), 0);
        var two = locomotive(helper, new Vec3(5, 3, 12), 180);
        spaceAfter(one, two, -0.3);
        running(one);
        running(two);
        double speed = 5.0 / 216.0;
        helper.assertTrue(!ContactResponse.derails(speed), "Test speed is above the threshold");
        one.setDeltaMovement(0, 0, speed);
        solve(one, two);
        helper.assertTrue(!one.isWrecked() && !two.isWrecked(), "A slow contact derailed");
        helper.assertTrue(gap(one, two) >= -1.0E-6, "Locomotives overlap: gap=" + gap(one, two));
        double before = one.weightKg() * speed;
        double after = one.weightKg() * one.getDeltaMovement().z + two.weightKg() * two.getDeltaMovement().z;
        helper.assertTrue(Math.abs(before - after) < 1.0E-6, "Momentum was not kept: " + before + " -> " + after);
        helper.assertTrue(
                Math.abs(one.getDeltaMovement().z - two.getDeltaMovement().z) < 1.0E-9,
                "The buffers bounced: " + one.getDeltaMovement() + " " + two.getDeltaMovement());
        helper.succeed();
    }

    /**
     * A locomotive driven into three loose wagons pushes them all, none of them sliding into the
     * next, and when the locomotive stops they stop with it rather than rolling on.
     */
    /** Two very long straights end to end along +Z, cleared above so nothing but the test is in the way. */
    private static BlockPos layLine(GameTestHelper helper) {
        BlockPos origin = helper.absolutePos(new BlockPos(1, 2, 1));
        for (int piece = 0; piece < 2; piece++) {
            BlockPos at = origin.offset(0, 0, 12 * piece);
            var plan = TrackPlacementPlanner.plan(TrackType.VERY_LONG_STRAIGHT, 0);
            for (var p : plan.placements()) {
                BlockPos rail = at.offset(p.dx(), p.dy(), p.dz());
                for (int height = 0; height <= 3; height++) {
                    helper.getLevel().setBlockAndUpdate(rail.above(height), Blocks.AIR.defaultBlockState());
                }
                helper.getLevel().setBlockAndUpdate(rail.below(), Blocks.STONE.defaultBlockState());
            }
            helper.assertTrue(TrackPlacer.apply(helper.getLevel(), at, plan).placed(), "Track placement failed");
        }
        return origin;
    }

    /** Stock made for the test alone: not added to the level, so only the test ticks it. */
    private static <T extends RollingStockEntity> T onLine(
            GameTestHelper helper, net.minecraft.world.entity.EntityType<T> type, BlockPos origin, double z) {
        T stock = type.create(helper.getLevel(), net.minecraft.world.entity.EntitySpawnReason.COMMAND);
        stock.setPos(origin.getX() + 0.5, origin.getY() + 0.2 + RollingStockEntity.Y_OFFSET, z);
        stock.alignToTrackOnPlacement();
        return stock;
    }

    /** Moves {@code stock} along +Z until it stands {@code gap} clear of {@code ahead}'s end. */
    private static void spaceAfter(RollingStockEntity ahead, RollingStockEntity stock, double gap) {
        spaceAfter(ahead, stock, gap, new Vec3(0, 0, 1));
    }

    /** The same along any direction; {@code stock} must already stand beyond {@code ahead}'s middle. */
    private static void spaceAfter(RollingStockEntity ahead, RollingStockEntity stock, double gap, Vec3 direction) {
        stock.shiftAlongTrack(direction.scale(gap - gap(ahead, stock)));
    }

    private static void pushesRake(GameTestHelper helper) {
        BlockPos origin = layLine(helper);
        AliceLocomotiveEntity loco = onLine(helper, EntityRegistry.LOCO_STEAM_ALICE.get(), origin, origin.getZ() + 3.5);
        running(loco);
        List<RollingStockEntity> train = new ArrayList<>();
        train.add(loco);
        for (int i = 0; i < 3; i++) {
            RollingStockEntity previous = train.getLast();
            var wagon = onLine(helper, EntityRegistry.FREIGHT_CART_YELLOW.get(), origin, previous.getZ() + 3.0);
            spaceAfter(previous, wagon, i == 0 ? 0.5 : 0.3);
            train.add(wagon);
        }
        double start = train.getLast().getZ();
        for (int tick = 0; tick < 40; tick++) {
            loco.setDeltaMovement(0, 0, 0.1);
            for (RollingStockEntity stock : train) stock.tick();
            StockContacts.solve(train);
            for (int i = 1; i < train.size(); i++) {
                double gap = gap(train.get(i - 1), train.get(i));
                helper.assertTrue(gap >= -0.05, "Stock " + (i - 1) + " and " + i + " overlap at tick " + tick + ": " + gap);
            }
        }
        helper.assertTrue(
                train.getLast().getZ() > start + 1.5,
                "The rake was not pushed: " + (train.getLast().getZ() - start));
        for (RollingStockEntity wagon : train.subList(1, train.size())) {
            helper.assertTrue(wagon.isPushed(), "A wagon in the rake was left behind: " + train.indexOf(wagon));
        }
        loco.setParkingBrake(true);
        for (int tick = 0; tick < 2; tick++) {
            for (RollingStockEntity stock : train) stock.tick();
            StockContacts.solve(train);
        }
        for (RollingStockEntity stock : train) {
            helper.assertTrue(
                    stock.getDeltaMovement().horizontalDistanceSqr() == 0.0,
                    "Stock rolled on after the push ended: " + train.indexOf(stock) + " " + stock.getDeltaMovement());
        }
        for (RollingStockEntity stock : train) stock.discard();
        helper.succeed();
    }

    /** How far past the face of a block a probe may leave an end, for floating-point slack. */
    private static final double BLOCK_SLACK = 1.0E-6;

    /** Where a block stands in front of the +Z end of a body, one cell above the rail. */
    private static BlockPos blockAfter(RollingStockEntity stock, BlockPos origin) {
        Vec3 end = stock.endFacing(new Vec3(0, 0, 1));
        return new BlockPos(origin.getX(), origin.getY() + 1, (int) Math.floor(end.z) + 1);
    }

    /**
     * A locomotive pushing two loose wagons into a block on the line. Slowly, the whole train comes
     * to rest against it with nothing in the block or in anything else; fast, the wagon that hits
     * the block leaves the rails and the rest stops dead.
     */
    private static void bufferStop(GameTestHelper helper, boolean fast) {
        BlockPos origin = layLine(helper);
        AliceLocomotiveEntity loco = onLine(helper, EntityRegistry.LOCO_STEAM_ALICE.get(), origin, origin.getZ() + 3.5);
        running(loco);
        List<RollingStockEntity> train = new ArrayList<>();
        train.add(loco);
        for (int i = 0; i < 2; i++) {
            RollingStockEntity previous = train.getLast();
            var wagon = onLine(helper, EntityRegistry.FREIGHT_CART_YELLOW.get(), origin, previous.getZ() + 3.0);
            spaceAfter(previous, wagon, i == 0 ? 0.05 : 0.0);
            train.add(wagon);
        }
        RollingStockEntity lead = train.getLast();
        BlockPos stop = blockAfter(lead, origin).offset(0, 0, 1);
        helper.getLevel().setBlockAndUpdate(stop, Blocks.STONE.defaultBlockState());
        double speed = fast ? 0.1 : 0.03;
        for (int tick = 0; tick < 120; tick++) {
            loco.setDeltaMovement(0, 0, speed);
            for (RollingStockEntity stock : train) stock.tick();
            StockContacts.solve(train);
            if (lead.isWrecked()) break;
            helper.assertTrue(
                    lead.endFacing(new Vec3(0, 0, 1)).z <= stop.getZ() + BLOCK_SLACK,
                    "The leading wagon went into the block at tick " + tick + ": " + lead.endFacing(new Vec3(0, 0, 1)));
            for (int i = 1; i < train.size(); i++) {
                double gap = gap(train.get(i - 1), train.get(i));
                helper.assertTrue(gap >= -0.05, "Stock " + (i - 1) + " and " + i + " overlap at tick " + tick + ": " + gap);
            }
        }
        if (fast) {
            helper.assertTrue(lead.isWrecked(), "A train run hard into a block did not derail the wagon that hit it");
            for (RollingStockEntity stock : train) {
                helper.assertTrue(
                        stock.getDeltaMovement().horizontalDistanceSqr() == 0.0,
                        "Stock kept moving after hitting the block: " + train.indexOf(stock) + " " + stock.getDeltaMovement());
            }
            helper.assertTrue(!loco.isWrecked() && !train.get(1).isWrecked(), "Stock away from the block was derailed");
        } else {
            helper.assertTrue(!lead.isWrecked(), "A gentle stop against a block derailed the wagon");
            helper.assertTrue(
                    stop.getZ() - lead.endFacing(new Vec3(0, 0, 1)).z < 0.1,
                    "The train never reached the block: " + lead.endFacing(new Vec3(0, 0, 1)));
            helper.assertTrue(
                    Math.abs(loco.getDeltaMovement().z) < 1.0E-6,
                    "The locomotive kept pushing into the stopped train: " + loco.getDeltaMovement());
        }
        for (RollingStockEntity stock : train) stock.discard();
        helper.succeed();
    }

    /** Every kind of rolling stock this mod registers, made fresh for the test. */
    private static List<net.minecraft.world.entity.EntityType<?>> everyStockType(GameTestHelper helper) {
        List<net.minecraft.world.entity.EntityType<?>> types = new ArrayList<>();
        for (var type : net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE) {
            if (!net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(type).getNamespace()
                    .equals(traincraft.Traincraft.MODID)) {
                continue;
            }
            var probe = type.create(helper.getLevel(), net.minecraft.world.entity.EntitySpawnReason.COMMAND);
            if (probe instanceof RollingStockEntity) types.add(type);
            if (probe != null) probe.discard();
        }
        helper.assertTrue(types.size() > 100, "Found only " + types.size() + " kinds of stock");
        return types;
    }

    @SuppressWarnings("unchecked")
    private static RollingStockEntity onLine(
            GameTestHelper helper, net.minecraft.world.entity.EntityType<?> type, BlockPos origin) {
        return onLine(helper, (net.minecraft.world.entity.EntityType<RollingStockEntity>) type, origin, origin.getZ() + 9.5);
    }

    /** A loose wagon run into any piece of stock stops against its end, not inside it. */
    private static void everyVehicleMeets(GameTestHelper helper) {
        BlockPos origin = layLine(helper);
        for (var type : everyStockType(helper)) {
            RollingStockEntity stock = onLine(helper, type, origin);
            var wagon = onLine(helper, EntityRegistry.FREIGHT_CART_YELLOW.get(), origin, stock.getZ() + 6.0);
            spaceAfter(stock, wagon, -0.5);
            wagon.setDeltaMovement(0, 0, -0.03);
            solve(stock, wagon);
            double gap = gap(stock, wagon);
            helper.assertTrue(gap >= -1.0E-6, "A wagon stopped inside " + type + ": gap=" + gap);
            stock.discard();
            wagon.discard();
        }
        helper.succeed();
    }

    /**
     * Any piece of stock run into a block stops at the face of it by its own end, not by the middle
     * of the body; run in hard, it derails.
     */
    private static void everyVehicleStopsAtBlock(GameTestHelper helper) {
        BlockPos origin = layLine(helper);
        for (var type : everyStockType(helper)) {
            for (boolean fast : new boolean[] {false, true}) {
                RollingStockEntity stock = onLine(helper, type, origin);
                helper.assertTrue(stock.isOnStraightTrack(), type + " did not settle on the straight");
                BlockPos stop = blockAfter(stock, origin);
                // Just short of the face, so this tick's travel would carry it in.
                stock.shiftAlongTrack(new Vec3(0, 0, stop.getZ() - 0.01 - stock.endFacing(new Vec3(0, 0, 1)).z));
                helper.getLevel().setBlockAndUpdate(stop, Blocks.STONE.defaultBlockState());
                double speed = (fast ? 20.0 : 5.0) / 216.0;
                stock.setDeltaMovement(0, 0, speed);
                solve(stock);
                if (fast) {
                    helper.assertTrue(stock.isWrecked(), type + " ran hard into a block and stayed on the rails");
                } else {
                    double end = stock.endFacing(new Vec3(0, 0, 1)).z + stock.getDeltaMovement().z;
                    helper.assertTrue(!stock.isWrecked(), type + " derailed against a block at walking pace");
                    helper.assertTrue(
                            end <= stop.getZ() + BLOCK_SLACK,
                            type + " would run " + (end - stop.getZ()) + " into the block");
                }
                helper.getLevel().setBlockAndUpdate(stop, Blocks.AIR.defaultBlockState());
                stock.discard();
            }
        }
        helper.succeed();
    }

    /** Derailed stock lies where it fell, keeps that through a save, and will not couple. */
    private static void wreckedStock(GameTestHelper helper) {
        BlockPos origin = helper.absolutePos(new BlockPos(5, 2, 1));
        var plan = TrackPlacementPlanner.plan(TrackType.VERY_LONG_STRAIGHT, 0);
        for (var p : plan.placements()) {
            BlockPos at = origin.offset(p.dx(), p.dy(), p.dz());
            helper.getLevel().setBlockAndUpdate(at, Blocks.AIR.defaultBlockState());
            helper.getLevel().setBlockAndUpdate(at.below(), Blocks.STONE.defaultBlockState());
            helper.getLevel().setBlockAndUpdate(at.offset(1, 0, 0), Blocks.AIR.defaultBlockState());
            helper.getLevel().setBlockAndUpdate(at.offset(1, -1, 0), Blocks.STONE.defaultBlockState());
        }
        helper.assertTrue(TrackPlacer.apply(helper.getLevel(), origin, plan).placed(), "Track placement failed");
        var wreck = stock(helper, false, new Vec3(5.5, 2.2 + RollingStockEntity.Y_OFFSET, 4.5), 0);
        var other = stock(helper, false, new Vec3(5.5, 2.2 + RollingStockEntity.Y_OFFSET, 7.0), 0);
        wreck.alignToTrackOnPlacement();
        double centre = wreck.getX();
        wreck.derailFromCollision(new Vec3(1, 0, 0));
        helper.runAfterDelay(
                10,
                () -> {
                    helper.assertTrue(wreck.isWrecked(), "The wreck was put back on the rails");
                    helper.assertTrue(
                            Math.abs(wreck.getX() - centre) > 0.5,
                            "The wreck went back onto the track: " + wreck.position());
                    wreck.setArmed(true);
                    other.setArmed(true);
                });
        helper.runAfterDelay(
                20,
                () -> {
                    helper.assertTrue(!wreck.hasAnyLink() && !other.hasAnyLink(), "The wreck coupled");
                    var output = net.minecraft.world.level.storage.TagValueOutput.createWithContext(
                            net.minecraft.util.ProblemReporter.DISCARDING, helper.getLevel().registryAccess());
                    wreck.saveWithoutId(output);
                    var copy = EntityRegistry.FREIGHT_CART_YELLOW.get()
                            .create(helper.getLevel(), net.minecraft.world.entity.EntitySpawnReason.COMMAND);
                    copy.load(net.minecraft.world.level.storage.TagValueInput.create(
                            net.minecraft.util.ProblemReporter.DISCARDING,
                            helper.getLevel().registryAccess(),
                            output.buildResult()));
                    helper.assertTrue(copy.isWrecked(), "Being derailed did not survive a save");
                    helper.succeed();
                });
    }

    private static void blockCollision(GameTestHelper helper) {
        for (boolean locomotive : new boolean[] {false, true}) {
            var stock = stock(helper, locomotive, new Vec3(5.5, 3.65, 5.5), 0);
            helper.assertTrue(
                    Math.abs(stock.getBbWidth() - 0.98) < 0.001
                            && Math.abs(stock.getBbHeight() - 1.98) < 0.001,
                    "Wrong stock dimensions");
            helper.assertTrue(
                    Math.abs(stock.getBoundingBox().minY - (stock.getY() - 0.65)) < 0.001,
                    "Hitbox lost CE yOffset");
            helper.setBlock(new BlockPos(5, 3, 7), Blocks.STONE);
            stock.move(MoverType.SELF, new Vec3(0, 0, 2));
            helper.assertTrue(
                    stock.getBoundingBox().maxZ
                            <= helper.absolutePos(new BlockPos(5, 3, 7)).getZ() + 0.001,
                    "Stock passed through a solid block");
            stock.discard();
        }
        helper.succeed();
    }
}
