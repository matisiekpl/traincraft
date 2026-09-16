package traincraft.development.gametest;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import traincraft.bootstrap.EntityRegistry;
import traincraft.production.ProductionRegistry;
import traincraft.vehicle.entity.TracksBuilderEntity;

final class CarGameTests {
    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("tank_cart_empties_bucket", CarGameTests::bucket);
        tests.put("tank_cart_drains_a_stack_bucket_by_bucket", CarGameTests::bucketStack);
        tests.put("b_unit_feeds_diesel", CarGameTests::bUnit);
        tests.put("work_cart_smelts", CarGameTests::smelts);
        tests.put("tracks_builder_lays_rail", CarGameTests::builder);
        tests.put("tracks_builder_lays_traincraft_track", CarGameTests::builderLaysTraincraftTrack);
        tests.put("tracks_builder_stops_over_air", CarGameTests::builderStopsOverAir);
        tests.put("tracks_builder_climbs_on_a_slope", CarGameTests::builderLaysSlope);
    }

    private static <T extends Entity> T at(GameTestHelper h, EntityType<T> type, int x, int z) {
        T entity = type.create(h.getLevel(), EntitySpawnReason.COMMAND);
        var p = h.absolutePos(new BlockPos(x, 3, z));
        entity.setPos(p.getX() + .5, p.getY(), p.getZ() + .5);
        return entity;
    }

    private static void tick(Entity entity, int ticks) {
        for (int i = 0; i < ticks; i++) entity.tick();
    }

    private static void bucket(GameTestHelper h) {
        var cart = at(h, EntityRegistry.TANK_TANK_WAGON_US.get(), 2, 2);
        cart.setItem(0, new ItemStack(Items.WATER_BUCKET));
        tick(cart, 8);
        h.assertTrue(cart.tank().amount() == 1000 && cart.tank().fluid() == Fluids.WATER, "A bucket is 1000 mB of water");
        h.assertTrue(cart.getItem(1).is(Items.BUCKET), "The empty bucket goes to the output slot");
        cart.setItem(0, new ItemStack(Items.LAVA_BUCKET));
        tick(cart, 8);
        h.assertTrue(cart.getItem(0).is(Items.LAVA_BUCKET) && cart.tank().amount() == 1000, "Lava stays out of a water tank");
        h.succeed();
    }

    private static void bucketStack(GameTestHelper h) {
        var cart = at(h, EntityRegistry.TANK_TANK_WAGON_US.get(), 2, 2);
        cart.tank().fill(3000);
        cart.setItem(0, new ItemStack(Items.BUCKET, 3));
        tick(cart, 8);
        h.assertTrue(cart.tank().amount() == 2000, "One bucket per pass, not the whole stack: " + cart.tank().amount());
        h.assertTrue(cart.getItem(0).getCount() == 2 && cart.getItem(1).is(Items.WATER_BUCKET) && cart.getItem(1).getCount() == 1, "Two empties stay, one full goes out");
        tick(cart, 8);
        h.assertTrue(cart.tank().amount() == 2000 && cart.getItem(1).getCount() == 1, "A full output slot holds the rest");
        cart.setItem(1, ItemStack.EMPTY);
        tick(cart, 8);
        h.assertTrue(cart.tank().amount() == 1000 && cart.getItem(0).getCount() == 1, "The next bucket follows once the slot is clear");
        h.succeed();
    }

    private static void bUnit(GameTestHelper h) {
        var unit = at(h, EntityRegistry.TANK_B_UNIT_EMDF7.get(), 2, 2);
        unit.setItem(0, new ItemStack(ProductionRegistry.item("diesel")));
        tick(unit, 8);
        h.assertTrue(unit.holdsFuel() && unit.getItem(1).is(ProductionRegistry.item("empty_canister")), "A B-unit takes a canister");
        var loco = at(h, EntityRegistry.LOCO_DIESEL_EMDF7.get(), 2, 4);
        loco.cartLinked1 = unit;
        loco.setEngineOn(true);
        tick(loco, 10);
        h.assertTrue(loco.getFuel() == 100 && loco.getFluidName().equals("Diesel"), "A coupled locomotive draws 100 mB every ten ticks");
        h.succeed();
    }

    private static void smelts(GameTestHelper h) {
        var cart = at(h, EntityRegistry.WORK_WORK_CART.get(), 2, 2);
        cart.setItem(0, new ItemStack(Items.RAW_IRON));
        cart.setItem(1, new ItemStack(Items.COAL));
        tick(cart, 202);
        h.assertTrue(cart.getItem(2).is(Items.IRON_INGOT), "Two hundred ticks smelt the ore");
        h.assertTrue(cart.isBurning() && cart.getItem(1).isEmpty(), "The coal is burning");
        h.succeed();
    }

    private static void builder(GameTestHelper h) {
        var origin = h.absolutePos(new BlockPos(1, 1, 2));
        for (int dx = 0; dx < 6; dx++) {
            for (int dy = 0; dy < (dx < 1 ? 2 : 3); dy++) {
                h.getLevel().setBlockAndUpdate(origin.offset(dx, dy, 0), Blocks.STONE.defaultBlockState());
            }
        }
        var builder = at(h, EntityRegistry.PASSENGER_TRACKS_BUILDER.get(), 1, 2);
        builder.setPos(builder.getX(), builder.getY() + 0.2 + traincraft.vehicle.entity.RollingStockEntity.Y_OFFSET, builder.getZ());
        builder.setItem(TracksBuilderEntity.FUEL_SLOT, new ItemStack(Items.COAL));
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM + 1, new ItemStack(Items.RAIL, 16));
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM, new ItemStack(Items.GRAVEL, 16));
        for (int cell = 0; cell < TracksBuilderEntity.TEMPLATE_WIDTH; cell++) {
            builder.setTemplateBlock(cell, Blocks.GRAVEL);
        }
        builder.setYRot(-90.0F);
        builder.setStarted(true);
        tick(builder, 3);
        var ahead = builder.railPosition().east();
        h.assertTrue(builder.getFuel() > 0 && builder.isWorking(), "Coal fuels the builder and it works once started");
        h.assertTrue(h.getLevel().getBlockState(ahead).getBlock() instanceof BaseRailBlock, "A rail goes down ahead");
        h.assertTrue(h.getLevel().getBlockState(ahead.below()).is(Blocks.GRAVEL), "The floor row goes under it");
        h.assertTrue(h.getLevel().getBlockState(ahead.below().south(2)).is(Blocks.GRAVEL), "The floor row spans five blocks");
        h.assertTrue(h.getLevel().getBlockState(ahead.above()).isAir(), "The tunnel is cleared");
        h.assertTrue(builder.getItem(TracksBuilderEntity.MATERIALS_FROM + 1).getCount() == 15, "One rail is consumed");
        h.assertTrue(builder.getItem(TracksBuilderEntity.MATERIALS_FROM).getCount() == 11, "Five floor blocks come out of the materials");
        h.succeed();
    }

    private static void builderStopsOverAir(GameTestHelper h) {
        var origin = h.absolutePos(new BlockPos(1, 1, 2));
        h.getLevel().setBlockAndUpdate(origin, Blocks.STONE.defaultBlockState());
        h.getLevel().setBlockAndUpdate(origin.above(), Blocks.STONE.defaultBlockState());
        var builder = at(h, EntityRegistry.PASSENGER_TRACKS_BUILDER.get(), 1, 2);
        builder.setPos(builder.getX(), builder.getY() + 0.2 + traincraft.vehicle.entity.RollingStockEntity.Y_OFFSET, builder.getZ());
        builder.setItem(TracksBuilderEntity.FUEL_SLOT, new ItemStack(Items.COAL));
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM + 1, new ItemStack(Items.RAIL, 4));
        builder.setYRot(-90.0F);
        builder.setStarted(true);
        tick(builder, 3);
        var ahead = builder.railPosition().east();
        h.assertTrue(h.getLevel().getBlockState(ahead).isAir(), "A rail was laid over air");
        h.assertTrue(builder.status() == TracksBuilderEntity.Status.NO_FLOOR, "Status is not NO_FLOOR: " + builder.status());
        h.assertTrue(builder.getItem(TracksBuilderEntity.MATERIALS_FROM + 1).getCount() == 4, "A rail was consumed");
        h.succeed();
    }

    private static void builderLaysSlope(GameTestHelper h) {
        var origin = h.absolutePos(new BlockPos(1, 1, 2));
        for (int dx = 0; dx < 9; dx++) {
            h.getLevel().setBlockAndUpdate(origin.offset(dx, 0, 0), Blocks.STONE.defaultBlockState());
            h.getLevel().setBlockAndUpdate(origin.offset(dx, 1, 0), Blocks.STONE.defaultBlockState());
        }
        var builder = at(h, EntityRegistry.PASSENGER_TRACKS_BUILDER.get(), 1, 2);
        builder.setPos(builder.getX(), builder.getY() + 0.2 + traincraft.vehicle.entity.RollingStockEntity.Y_OFFSET, builder.getZ());
        builder.setItem(TracksBuilderEntity.FUEL_SLOT, new ItemStack(Items.COAL));
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM + 1, new ItemStack(traincraft.bootstrap.TrackItemRegistry.itemFor(traincraft.track.TrackType.SMALL_STRAIGHT), 5));
        builder.setYRot(-90.0F);
        builder.setStarted(true);
        builder.setPlannedHeight(builder.railPosition().getY() + 1);
        tick(builder, 2);
        h.assertTrue(builder.status() == TracksBuilderEntity.Status.NO_TRACK, "Five track items are not a six-block slope: " + builder.statusText());
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM + 1, new ItemStack(traincraft.bootstrap.TrackItemRegistry.itemFor(traincraft.track.TrackType.SMALL_STRAIGHT), 8));
        builder.setStarted(true);
        tick(builder, 2);
        var ahead = builder.railPosition().east();
        h.assertTrue(
                h.getLevel().getBlockEntity(ahead) instanceof traincraft.track.block.TrackBlockEntity rail && rail.geometry().slope() != null,
                "A slope does not start ahead: " + h.getLevel().getBlockState(ahead));
        h.assertTrue(builder.getItem(TracksBuilderEntity.MATERIALS_FROM + 1).getCount() == 2, "The slope did not cost six track items");
        h.assertTrue(builder.status() == TracksBuilderEntity.Status.WORKING, "Status is " + builder.statusText());
        h.succeed();
    }

    private static void builderLaysTraincraftTrack(GameTestHelper h) {
        var origin = h.absolutePos(new BlockPos(1, 1, 2));
        for (int dx = 0; dx < 6; dx++) {
            h.getLevel().setBlockAndUpdate(origin.offset(dx, 0, 0), Blocks.STONE.defaultBlockState());
            h.getLevel().setBlockAndUpdate(origin.offset(dx, 1, 0), Blocks.STONE.defaultBlockState());
        }
        var builder = at(h, EntityRegistry.PASSENGER_TRACKS_BUILDER.get(), 1, 2);
        builder.setPos(builder.getX(), builder.getY() + 0.2 + traincraft.vehicle.entity.RollingStockEntity.Y_OFFSET, builder.getZ());
        builder.setItem(TracksBuilderEntity.FUEL_SLOT, new ItemStack(Items.COAL));
        builder.setItem(TracksBuilderEntity.MATERIALS_FROM + 1, new ItemStack(traincraft.bootstrap.TrackItemRegistry.itemFor(traincraft.track.TrackType.SMALL_STRAIGHT), 4));
        builder.setYRot(-90.0F);
        builder.setStarted(true);
        tick(builder, 3);
        var ahead = builder.railPosition().east();
        h.assertTrue(h.getLevel().getBlockState(ahead).getBlock() instanceof traincraft.track.block.TrackBlock, "A small straight goes down ahead at rail level");
        h.assertTrue(builder.getItem(TracksBuilderEntity.MATERIALS_FROM + 1).getCount() == 3, "One track item is consumed");
        h.succeed();
    }
}
