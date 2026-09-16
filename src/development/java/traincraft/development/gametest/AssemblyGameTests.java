package traincraft.development.gametest;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import traincraft.bootstrap.ItemRegistry;
import traincraft.production.MachineBlockEntity;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

/** CE's assembly table III rows for the locomotives that have no other test of their own. */
final class AssemblyGameTests {
    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("sd40_assembly", h -> assembly(h, ItemRegistry.LOCO_DIESEL_SD40, DyeColor.RED, "Red",
                new Object[] {"controls", 2, "bogie", 6, "steel_frame", 2, "steel_ingot", 2, "steel_chimney", 1, "steel_cab", 1, "electric_motor", 6, "diesel_engine", 6, "generator", 4}));
        tests.put("sd70_assembly", h -> assembly(h, ItemRegistry.LOCO_DIESEL_SD70, DyeColor.ORANGE, "Orange",
                new Object[] {"controls", 2, "bogie", 6, "steel_frame", 2, "steel_ingot", 2, "steel_chimney", 1, "steel_cab", 1, "electric_motor", 4, "diesel_engine", 4, "generator", 4}));
        tests.put("v60_assembly", h -> assembly(h, ItemRegistry.LOCO_DIESEL_V60, DyeColor.CYAN, "Cyan",
                new Object[] {"controls", 1, "bogie", 2, "steel_frame", 2, "steel_ingot", 2, null, 0, "steel_cab", 1, "transmission", 1, "diesel_engine", 3}));
        tests.put("br_e69_assembly", h -> assembly(h, ItemRegistry.LOCO_ELECTRIC_BR_E69, DyeColor.GREEN, "Green",
                new Object[] {"controls", 2, "bogie", 3, "steel_frame", 2, "steel_ingot", 1, "steel_chimney", 1, "steel_cab", 1, "transformer", 2, "electric_motor", 2, Items.REDSTONE, 2}));
        tests.put("e10_assembly", h -> assembly(h, ItemRegistry.LOCO_ELECTRIC_E10, DyeColor.BROWN, "Brown",
                new Object[] {"controls", 2, "bogie", 2, "steel_frame", 2, "steel_ingot", 2, "steel_chimney", 2, "steel_cab", 1, "electric_motor", 1, "transformer", 2}));
        tests.put("transmission_takes_a_diesel_canister", AssemblyGameTests::transmission);
    }

    private static void assembly(GameTestHelper h, DeferredHolder<Item, ?> item, DyeColor dye, String colour, Object[] slots) {
        var machine = machine(h, MachineKind.ASSEMBLY);
        for (int slot = 0; slot * 2 < slots.length; slot++) {
            Object part = slots[slot * 2];
            if (part == null) continue;
            Item ingredient = part instanceof Item vanilla ? vanilla : ProductionRegistry.item((String) part);
            machine.setItem(slot, new ItemStack(ingredient, (Integer) slots[slot * 2 + 1]));
        }
        machine.setItem(9, new ItemStack(Items.DYE.pick(dye)));
        var recipe = machine.recipe();
        h.assertTrue(recipe != null, "CE assembly inputs must resolve");
        var result = recipe.assemble(new MachineRecipe.Inventory(machine.kind, machine));
        h.assertTrue(result.is(item.get()), "Assembly produces " + item.getId());
        h.assertTrue(result.get(DataComponents.CUSTOM_DATA).copyTag().getStringOr("trainColor", "").equals(colour), "Dye sets the livery");
        h.succeed();
    }

    private static void transmission(GameTestHelper h) {
        var machine = machine(h, MachineKind.WORKBENCH);
        for (int slot : new int[] {1, 3, 5, 7}) machine.setItem(slot, new ItemStack(ProductionRegistry.item("steel_ingot")));
        machine.setItem(4, new ItemStack(ProductionRegistry.item("diesel")));
        var recipe = machine.recipe();
        h.assertTrue(recipe != null, "Steel around a diesel canister is CE's transmission");
        h.assertTrue(recipe.assemble(new MachineRecipe.Inventory(machine.kind, machine)).is(ProductionRegistry.item("transmission")), "The workbench makes one transmission");
        h.succeed();
    }

    private static MachineBlockEntity machine(GameTestHelper h, MachineKind kind) {
        var pos = h.absolutePos(new BlockPos(2, 2, 2));
        h.getLevel().setBlock(pos, ProductionRegistry.BLOCKS.get(kind).get().defaultBlockState(), 3);
        return (MachineBlockEntity) h.getLevel().getBlockEntity(pos);
    }
}
