package traincraft.development.gametest;

import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Blocks;
import traincraft.item.TraincraftItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.EntityRegistry;
import traincraft.vehicle.entity.ZeppelinEntity;

final class ZeppelinGameTests {
    private ZeppelinGameTests() {}

    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("zeppelin_takes_a_rider_on_click", ZeppelinGameTests::boards);
        tests.put("zeppelin_climbs_when_latched_and_sinks_otherwise", ZeppelinGameTests::latchedModes);
        tests.put("zeppelin_drops_tnt_from_its_hold", ZeppelinGameTests::bomb);
        tests.put("zeppelins_push_each_other_apart", ZeppelinGameTests::pushApart);
        tests.put("zeppelin_item_refuses_an_occupied_spot", ZeppelinGameTests::noRoom);
        tests.put("zeppelin_item_keeps_off_the_player", ZeppelinGameTests::notOnPlayer);
    }

    private static void boards(GameTestHelper helper) {
        ZeppelinEntity zeppelin = helper.spawn(EntityRegistry.ZEPPELIN.get(), new Vec3(5, 4, 5));
        helper.assertTrue(zeppelin.isPickable(), "Zeppelin cannot be targeted by a click");
        helper.assertTrue(zeppelin.getBbWidth() > 3.0F, "One-balloon zeppelin lost its 3.4 width");
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setPos(zeppelin.position());
        zeppelin.interact(player, InteractionHand.MAIN_HAND, Vec3.ZERO);
        helper.assertTrue(player.getVehicle() == zeppelin, "Click did not board the zeppelin");
        helper.succeed();
    }

    private static void latchedModes(GameTestHelper helper) {
        ZeppelinEntity zeppelin = helper.spawn(EntityRegistry.AIRSHIP.get(), new Vec3(5, 6, 5));
        zeppelin.setItem(ZeppelinEntity.FUEL_SLOT, new ItemStack(Items.COAL));
        zeppelin.tick();
        helper.assertTrue(zeppelin.getFuel() > 0, "Coal did not fuel the zeppelin");
        double start = zeppelin.getY();
        zeppelin.tick();
        helper.assertTrue(zeppelin.getY() < start, "An unlatched zeppelin did not sink");
        zeppelin.setKeyHeld(ZeppelinEntity.KEY_ASCEND, true);
        zeppelin.setKeyHeld(ZeppelinEntity.KEY_ASCEND, false);
        start = zeppelin.getY();
        zeppelin.tick();
        zeppelin.tick();
        helper.assertTrue(zeppelin.climbing() && zeppelin.getY() > start, "Climb did not latch after the key was released");
        zeppelin.setKeyHeld(ZeppelinEntity.KEY_IDLE, true);
        start = zeppelin.getY();
        zeppelin.tick();
        helper.assertTrue(zeppelin.idling() && Math.abs(zeppelin.getY() - start) < 1.0E-6, "Idle did not hold height");
        helper.succeed();
    }

    private static void bomb(GameTestHelper helper) {
        ZeppelinEntity zeppelin = helper.spawn(EntityRegistry.ZEPPELIN.get(), new Vec3(5, 6, 5));
        Player rider = helper.makeMockPlayer(GameType.SURVIVAL);
        rider.startRiding(zeppelin, true, false);
        zeppelin.setItem(3, new ItemStack(Items.TNT, 2));
        zeppelin.setKeyHeld(ZeppelinEntity.KEY_BOMB, true);
        zeppelin.setKeyHeld(ZeppelinEntity.KEY_BOMB, true);
        helper.assertTrue(zeppelin.getItem(3).getCount() == 1, "Bomb key ignored the cooldown or the hold");
        helper.assertTrue(!helper.getLevel().getEntitiesOfClass(net.minecraft.world.entity.item.PrimedTnt.class, zeppelin.getBoundingBox().inflate(2.0)).isEmpty(), "No primed TNT below the zeppelin");
        helper.succeed();
    }

    private static void pushApart(GameTestHelper helper) {
        ZeppelinEntity one = helper.spawn(EntityRegistry.ZEPPELIN.get(), new Vec3(5, 6, 5));
        ZeppelinEntity two = helper.spawn(EntityRegistry.ZEPPELIN.get(), new Vec3(5.5, 6, 5));
        one.tick();
        helper.assertTrue(one.getDeltaMovement().x < 0 && two.getDeltaMovement().x > 0, "Overlapping zeppelins did not push apart");
        helper.succeed();
    }

    private static void notOnPlayer(GameTestHelper helper) {
        BlockPos floor = helper.absolutePos(new BlockPos(8, 1, 5));
        helper.getLevel().setBlockAndUpdate(floor.below(), Blocks.STONE.defaultBlockState());
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.snapTo(floor.getX() + 0.5, floor.getY(), floor.getZ() + 0.5, 0.0F, 90.0F);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(TraincraftItems.item("zeppelin")));
        TraincraftItems.item("zeppelin").use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        var placed = helper.getLevel().getEntitiesOfClass(ZeppelinEntity.class, new net.minecraft.world.phys.AABB(floor).inflate(12.0));
        ZeppelinEntity probe = EntityRegistry.ZEPPELIN.get().create(helper.getLevel(), net.minecraft.world.entity.EntitySpawnReason.COMMAND);
        probe.setYRot(player.getYRot());
        String detail = "";
        for (double shift = 0.0; shift <= 8.0; shift += 2.0) {
            probe.setPos(floor.getX() + 0.5, floor.getY() + 0.5, floor.getZ() + 0.5 + shift);
            detail += " shift " + shift + ": " + probe.obstruction(player.getBoundingBox().inflate(1.5, 0.0, 1.5));
        }
        helper.assertTrue(placed.size() == 1, "Expected one zeppelin, found " + placed.size() + ";" + detail);
        var clearance = player.getBoundingBox().inflate(1.0, 0.0, 1.0);
        helper.assertTrue(!placed.get(0).getBoundingBox().intersects(clearance), "Zeppelin hull was placed against the player");
        for (var part : placed.get(0).getParts()) {
            helper.assertTrue(!part.getBoundingBox().intersects(clearance), "Zeppelin balloon was placed against the player");
        }
        helper.succeed();
    }

    private static void noRoom(GameTestHelper helper) {
        BlockPos floor = helper.absolutePos(new BlockPos(5, 1, 5));
        for (int x = -5; x <= 10; x++) {
            for (int z = -5; z <= 10; z++) {
                helper.getLevel().setBlockAndUpdate(floor.offset(x, -1, z), Blocks.STONE.defaultBlockState());
                helper.getLevel().setBlockAndUpdate(floor.offset(x, 3, z), Blocks.STONE.defaultBlockState());
            }
        }
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.snapTo(floor.getX() + 0.5, floor.getY(), floor.getZ() - 4.0, 0.0F, 45.0F);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(TraincraftItems.item("airship")));
        var result = TraincraftItems.item("airship").use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        int placed = helper.getLevel().getEntitiesOfClass(ZeppelinEntity.class, new net.minecraft.world.phys.AABB(floor).inflate(12.0)).size();
        helper.assertTrue(placed == 0, "An airship was placed under a ceiling three blocks up; result " + result);
        helper.assertTrue(player.getItemInHand(InteractionHand.MAIN_HAND).getCount() == 1, "The refused airship was consumed");
        helper.succeed();
    }
}
