package traincraft.development.gametest;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import traincraft.bootstrap.EntityRegistry;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;
import traincraft.vehicle.entity.Es44LocomotiveEntity;

final class DieselGameTests {
    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("es44_canister_refuel", DieselGameTests::canisters);
        tests.put("es44_runs_dry", DieselGameTests::runsDry);
        tests.put("es44_assembly", DieselGameTests::assembly);
    }

    private static Es44LocomotiveEntity loco(GameTestHelper h) {
        var loco = EntityRegistry.LOCO_DIESEL_ES44.get().create(h.getLevel(), EntitySpawnReason.COMMAND);
        var p = h.absolutePos(new BlockPos(2, 3, 2));
        loco.setPos(p.getX() + .5, p.getY(), p.getZ() + .5);
        return loco;
    }

    private static void tickTo(Es44LocomotiveEntity loco, int ticks) {
        for (int i = 0; i < ticks; i++) loco.tick();
    }

    private static void canisters(GameTestHelper h) {
        var loco = loco(h);
        loco.setItem(0, new ItemStack(ProductionRegistry.item("diesel"), 2));
        tickTo(loco, 8);
        h.assertTrue(loco.getFuel() == 1000 && loco.getFluidName().equals("Diesel"), "One canister is 1000 mB of diesel");
        h.assertTrue(loco.getItem(1).is(ProductionRegistry.item("empty_canister")), "The empty canister goes to the cargo");
        loco.setItem(0, new ItemStack(ProductionRegistry.item("refined_fuel")));
        tickTo(loco, 8);
        h.assertTrue(loco.getFuel() == 1000 && loco.getItem(0).getCount() == 1, "A different fuel must not mix into the tank");
        loco.restoreFuel(22500);
        loco.setItem(0, new ItemStack(ProductionRegistry.item("diesel")));
        tickTo(loco, 8);
        h.assertTrue(loco.getFuel() == 22500 && loco.getItem(0).getCount() == 1, "A canister that does not fit stays in the slot");
        h.succeed();
    }

    private static void runsDry(GameTestHelper h) {
        var loco = loco(h);
        loco.setItem(0, new ItemStack(Items.COAL));
        tickTo(loco, 8);
        h.assertTrue(loco.getFuel() == 0 && loco.getItem(0).getCount() == 1, "Diesel rejects furnace fuel");
        loco.restoreFuel(15);
        loco.setEngineOn(true);
        tickTo(loco, 100);
        h.assertTrue(loco.getFuel() == 5 && loco.isEngineOn(), "One interval drains the spec's fuel consumption");
        tickTo(loco, 100);
        h.assertTrue(loco.getFuel() == 0 && !loco.isEngineOn(), "Running dry stops the engine");
        h.succeed();
    }

    private static void assembly(GameTestHelper h) {
        var pos = h.absolutePos(new BlockPos(2, 2, 2));
        h.getLevel().setBlock(pos, ProductionRegistry.BLOCKS.get(MachineKind.ASSEMBLY).get().defaultBlockState(), 3);
        var machine = (traincraft.production.MachineBlockEntity) h.getLevel().getBlockEntity(pos);
        String[] parts = {"controls", "bogie", "steel_frame", "steel_ingot", "steel_chimney", "steel_cab", "electric_motor", "diesel_engine", "generator"};
        int[] counts = {6, 5, 5, 2, 2, 3, 4, 5, 4};
        for (int i = 0; i < parts.length; i++) machine.setItem(i, new ItemStack(ProductionRegistry.item(parts[i]), counts[i]));
        machine.setItem(9, new ItemStack(Items.DYE.pick(net.minecraft.world.item.DyeColor.ORANGE)));
        var recipe = machine.recipe();
        h.assertTrue(recipe != null, "CE assembly inputs must resolve");
        var result = recipe.assemble(new MachineRecipe.Inventory(machine.kind, machine));
        h.assertTrue(result.is(traincraft.bootstrap.ItemRegistry.LOCO_DIESEL_ES44.get()), "Assembly produces the ES44");
        h.assertTrue(result.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA).copyTag().getStringOr("trainColor", "").equals("Orange"), "Dye sets the livery");
        h.succeed();
    }
}
