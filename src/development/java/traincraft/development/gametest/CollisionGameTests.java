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
import traincraft.vehicle.coupling.StockCollision;
import traincraft.vehicle.entity.RollingStockEntity;

import java.util.Map;
import java.util.function.Consumer;

final class CollisionGameTests {
    private CollisionGameTests() {}

    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("stock_collisions_follow_rail_axis", CollisionGameTests::railAxis);
        tests.put("stock_collision_is_applied_once", CollisionGameTests::oncePerPair);
        tests.put("coupled_stock_does_not_collide", CollisionGameTests::coupledStock);
        tests.put("freight_pushes_vanilla_minecart", CollisionGameTests::vanillaMinecart);
        tests.put("bogie_detects_mobs_and_stock", CollisionGameTests::bogieContacts);
        tests.put("stock_collision_protects_riders", CollisionGameTests::riders);
        tests.put("stock_collision_speed_thresholds", CollisionGameTests::speedThresholds);
        tests.put("stock_hitbox_stops_at_blocks", CollisionGameTests::blockCollision);
        tests.put("slope_supports_players_but_not_stock", CollisionGameTests::slopeCollision);
        tests.put("stock_never_overlaps", CollisionGameTests::neverOverlaps);
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
                var two = stock(helper, false, origin.add(forward), yaw);
                one.setDeltaMovement(forward.scale(0.1));
                one.collisions().tick(one);
                helper.assertTrue(
                        two.getDeltaMovement().dot(forward) > 0.04,
                        "Head-on impact ignored: yaw=" + yaw + " locomotive=" + locomotive);
                one.discard();
                two.discard();

                one = stock(helper, locomotive, origin, yaw);
                two = stock(helper, false, origin.add(forward.z, 0, -forward.x), yaw);
                one.setDeltaMovement(forward.scale(0.1));
                one.collisions().tick(one);
                helper.assertTrue(
                        two.getDeltaMovement().lengthSqr() == 0,
                        "Parallel track received an impulse: yaw=" + yaw);
                two.setPos(one.position().add(forward.z, 0, -forward.x).add(forward.scale(2)));
                one.collisions().tick(one);
                helper.assertTrue(
                        two.getDeltaMovement().lengthSqr() == 0,
                        "Staggered cart on a parallel track received an impulse: yaw=" + yaw);
                one.discard();
                two.discard();
            }
        }
        helper.succeed();
    }

    private static void oncePerPair(GameTestHelper helper) {
        var one = stock(helper, false, new Vec3(5, 3, 5), 0);
        var two = stock(helper, false, new Vec3(5, 3, 6), 0);
        one.setDeltaMovement(0, 0, 0.1);
        StockCollision.apply(one, two);
        Vec3 first = one.getDeltaMovement();
        Vec3 second = two.getDeltaMovement();
        StockCollision.apply(two, one);
        one.collisions().tick(one);
        helper.assertTrue(
                first.equals(one.getDeltaMovement()) && second.equals(two.getDeltaMovement()),
                "Both ends or both tick loops applied the same collision twice");
        // Keep the ordinary entity ticks from consuming the next tick's contact before assertion.
        one.noPhysics = true;
        two.noPhysics = true;
        helper.runAfterDelay(
                1,
                () -> {
                    one.noPhysics = false;
                    two.noPhysics = false;
                    one.setDeltaMovement(Vec3.ZERO);
                    two.setDeltaMovement(Vec3.ZERO);
                    StockCollision.apply(one, two);
                    helper.assertTrue(
                            two.getDeltaMovement().lengthSqr() > 0,
                            "Pair was never released next tick");
                    helper.succeed();
                });
    }

    private static void coupledStock(GameTestHelper helper) {
        var one = stock(helper, true, new Vec3(5, 3, 5), 0);
        var two = stock(helper, false, new Vec3(5, 3, 6), 0);
        one.cartLinked1 = two;
        two.cartLinked1 = one;
        one.setDeltaMovement(0, 0, 0.1);
        StockCollision.apply(one, two);
        StockCollision.apply(two, one);
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
        var cart = stock(helper, false, new Vec3(1, 3, 1), 0);
        cart.setPos(bogie.add(0, 0, 1));
        loco.collisions().tick(loco);
        helper.assertTrue(cart.getDeltaMovement().z > 0, "Bogie did not push stock");
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

    private static void neverOverlaps(GameTestHelper helper) {
        for (boolean locomotive : new boolean[] {true, false}) {
            var one = stock(helper, true, new Vec3(5, 3, 5), 0);
            var two = stock(helper, locomotive, new Vec3(5, 3, 6), 0);
            double minimum = one.getLinkageDistance(one) - 0.15;
            one.setDeltaMovement(0, 0, 0.3);
            for (int i = 0; i < 5; i++) {
                one.collisions().tick(one);
                two.collisions().tick(two);
            }
            double distance = two.position().subtract(one.position()).horizontalDistance();
            helper.assertTrue(
                    distance >= minimum - 1.0E-6,
                    "Stock overlaps: distance=" + distance + " minimum=" + minimum + " locomotive=" + locomotive);
            helper.assertTrue(
                    one.getDeltaMovement().z <= 1.0E-6,
                    "Rammed stock kept its speed: " + one.getDeltaMovement().z);
            one.discard();
            two.discard();
        }
        helper.succeed();
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
