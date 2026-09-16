package traincraft.development.gametest;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.mojang.authlib.GameProfile;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.EntityRegistry;
import traincraft.item.TraincraftItems;
import traincraft.vehicle.coupling.StockCollision;
import traincraft.vehicle.entity.ForneyLocomotiveEntity;
import traincraft.vehicle.entity.FreightCartYellowEntity;
import traincraft.vehicle.entity.RollingStockEntity;

final class LockGameTests {
    private LockGameTests() {}

    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("wrench_toggles_the_lock_for_the_owner", LockGameTests::wrenchLock);
        tests.put("stranger_cannot_lock_with_the_admin_book", LockGameTests::strangerBook);
        tests.put("operator_wrench_breaks_a_locked_cart", LockGameTests::operatorBypass);
        tests.put("stock_car_boards_a_nearby_mob", LockGameTests::stockCarBoardsMob);
        tests.put("forney_drives_unboosted_for_a_plain_player", LockGameTests::forneyPlain);
        tests.put("es44_keeps_its_engine_number", LockGameTests::engineNumber);
        tests.put("creative_break_drops_the_cargo", LockGameTests::creativeBreakDropsCargo);
    }

    private static RollingStockEntity cart(GameTestHelper helper, String owner) {
        RollingStockEntity cart = helper.spawn(EntityRegistry.FREIGHT_CART_YELLOW.get(), new Vec3(5, 3, 5));
        cart.setOwner(owner);
        return cart;
    }

    private static Player operator(GameTestHelper helper, String item) {
        Player operator = new Player(helper.getLevel(), new GameProfile(UUID.randomUUID(), "operator")) {
            @Override
            public GameType gameMode() {
                return GameType.SURVIVAL;
            }

            @Override
            public boolean isClientAuthoritative() {
                return false;
            }

            @Override
            public PermissionSet permissions() {
                return PermissionSet.ALL_PERMISSIONS;
            }
        };
        operator.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(TraincraftItems.item(item)));
        return operator;
    }

    private static Player holding(GameTestHelper helper, GameType type, String item) {
        Player player = helper.makeMockPlayer(type);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(TraincraftItems.item(item)));
        return player;
    }

    private static void wrenchLock(GameTestHelper helper) {
        Player owner = holding(helper, GameType.SURVIVAL, "composite_wrench");
        RollingStockEntity cart = cart(helper, owner.getGameProfile().name());
        cart.interact(owner, InteractionHand.MAIN_HAND, Vec3.ZERO);
        helper.assertTrue(cart.isLocked(), "Wrench did not lock the owner's cart");
        cart.interact(owner, InteractionHand.MAIN_HAND, Vec3.ZERO);
        helper.assertTrue(!cart.isLocked(), "Second wrench click did not unlock");
        helper.succeed();
    }

    private static void strangerBook(GameTestHelper helper) {
        RollingStockEntity cart = cart(helper, "SomebodyElse");
        Player stranger = holding(helper, GameType.SURVIVAL, "admin_book");
        cart.interact(stranger, InteractionHand.MAIN_HAND, Vec3.ZERO);
        helper.assertTrue(!cart.isLocked(), "A stranger locked somebody else's cart");
        Player operator = operator(helper, "admin_book");
        cart.interact(operator, InteractionHand.MAIN_HAND, Vec3.ZERO);
        helper.assertTrue(cart.isLocked(), "The admin book did not lock for an operator");
        helper.succeed();
    }

    private static void creativeBreakDropsCargo(GameTestHelper helper) {
        RollingStockEntity cart = cart(helper, "");
        ((FreightCartYellowEntity) cart).setItem(0, new ItemStack(Items.COAL, 17));
        Player creative = helper.makeMockPlayer(GameType.CREATIVE);
        creative.getAbilities().instabuild = true;
        cart.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(creative), 100.0F);
        helper.assertTrue(cart.isRemoved(), "A creative hit did not remove the cart");
        helper.assertItemEntityPresent(Items.COAL, new BlockPos(5, 3, 5), 3.0);
        helper.succeed();
    }

    private static void operatorBypass(GameTestHelper helper) {
        RollingStockEntity cart = cart(helper, "SomebodyElse");
        cart.setLocked(true);
        Player stranger = helper.makeMockPlayer(GameType.SURVIVAL);
        cart.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(stranger), 100.0F);
        helper.assertTrue(!cart.isRemoved(), "A stranger broke a locked cart");
        Player operator = operator(helper, "composite_wrench");
        cart.hurtServer(helper.getLevel(), helper.getLevel().damageSources().playerAttack(operator), 100.0F);
        helper.assertTrue(cart.isRemoved(), "The operator's wrench did not remove the locked cart");
        helper.succeed();
    }

    private static void stockCarBoardsMob(GameTestHelper helper) {
        RollingStockEntity stockCar = helper.spawn(EntityRegistry.PASSENGER_STOCK_CAR.get(), new Vec3(5, 3, 5));
        var pig = helper.spawn(EntityTypes.PIG, new Vec3(5, 3, 5.4));
        StockCollision.apply(stockCar, pig);
        helper.assertTrue(pig.getVehicle() == stockCar, "The stock car did not take the pig aboard");
        RollingStockEntity freight = helper.spawn(EntityRegistry.FREIGHT_CART_YELLOW.get(), new Vec3(5, 3, 9));
        var cow = helper.spawn(EntityTypes.COW, new Vec3(5, 3, 9.4));
        StockCollision.apply(freight, cow);
        helper.assertTrue(cow.getVehicle() == null, "A freight cart took a cow aboard");
        helper.succeed();
    }

    private static void forneyPlain(GameTestHelper helper) {
        ForneyLocomotiveEntity forney = helper.spawn(EntityRegistry.LOCO_STEAM_FORNEY.get(), new Vec3(5, 3, 5));
        Player driver = helper.makeMockPlayer(GameType.SURVIVAL);
        driver.startRiding(forney, true, false);
        helper.assertTrue(forney.getMaxSpeed() == forney.spec().maxSpeed(), "Speed multiplied for a plain driver");
        helper.assertTrue(forney.currentHorsePower() == forney.spec().horsePower(), "Power multiplied for a plain driver");
        helper.succeed();
    }

    private static void engineNumber(GameTestHelper helper) {
        RollingStockEntity es44 = helper.spawn(EntityRegistry.LOCO_DIESEL_ES44.get(), new Vec3(5, 3, 5));
        es44.setEngineNumber("BNSF 7434");
        helper.assertTrue(es44.getEngineNumber().equals("BNSF 7434"), "Engine number not kept");
        RollingStockEntity forney = helper.spawn(EntityRegistry.LOCO_STEAM_FORNEY.get(), new Vec3(5, 3, 9));
        helper.assertTrue(!forney.supportsEngineNumber(), "Forney took an engine number");
        RollingStockEntity asteri = helper.spawn(EntityRegistry.LOCO_STEAM_ASTERI.get(), new Vec3(5, 3, 13));
        helper.assertTrue(asteri.getDisplayName().getString().equals("RickRoll"), "asteri is missing");
        helper.succeed();
    }
}
