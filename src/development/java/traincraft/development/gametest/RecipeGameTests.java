package traincraft.development.gametest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import traincraft.production.MachineBlockEntity;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

/** Every recipe the mod ships crafts what it says: a stack per ingredient goes in, the result comes out. */
final class RecipeGameTests {
    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("every_recipe_crafts", RecipeGameTests::everyRecipeCrafts);
        tests.put("assembly_tiers_gate_recipes", RecipeGameTests::tiers);
    }

    private static ItemStack sample(Ingredient ingredient, int count) {
        return ingredient.items().findFirst().map(item -> new ItemStack(item.value(), count)).orElse(ItemStack.EMPTY);
    }

    private static MachineBlockEntity machine(GameTestHelper h, MachineKind kind, int index) {
        var pos = h.absolutePos(new BlockPos(1 + index % 4, 2, 1 + index / 4));
        h.getLevel().setBlock(pos, ProductionRegistry.BLOCKS.get(kind).get().defaultBlockState(), 3);
        return (MachineBlockEntity) h.getLevel().getBlockEntity(pos);
    }

    private static void everyRecipeCrafts(GameTestHelper h) {
        var level = h.getLevel();
        List<String> failures = new ArrayList<>();
        int checked = 0;
        Map<MachineKind, MachineBlockEntity> machines = new java.util.EnumMap<>(MachineKind.class);
        int index = 0;
        for (MachineKind kind : MachineKind.values()) machines.put(kind, machine(h, kind, index++));
        for (RecipeHolder<?> holder : level.recipeAccess().recipeMap().values()) {
            if (!holder.id().identifier().getNamespace().equals("tc")) continue;
            Recipe<?> recipe = holder.value();
            checked++;
            String name = holder.id().identifier().toString();
            ItemStack made;
            if (recipe instanceof ShapedRecipe shaped) {
                List<ItemStack> stacks = new ArrayList<>();
                for (Optional<Ingredient> ingredient : shaped.pattern.ingredients()) stacks.add(ingredient.map(i -> sample(i, 1)).orElse(ItemStack.EMPTY));
                CraftingInput input = CraftingInput.of(shaped.pattern.width(), shaped.pattern.height(), stacks);
                made = shaped.matches(input, level) ? shaped.assemble(input) : ItemStack.EMPTY;
            } else if (recipe instanceof ShapelessRecipe shapeless) {
                List<ItemStack> stacks = new ArrayList<>();
                for (Ingredient ingredient : shapeless.placementInfo().ingredients()) stacks.add(sample(ingredient, 1));
                while (stacks.size() < 9) stacks.add(ItemStack.EMPTY);
                CraftingInput input = CraftingInput.of(3, 3, stacks);
                made = shapeless.matches(input, level) ? shapeless.assemble(input) : ItemStack.EMPTY;
            } else if (recipe instanceof AbstractCookingRecipe cooking) {
                SingleRecipeInput input = new SingleRecipeInput(sample(cooking.input(), 1));
                made = cooking.matches(input, level) ? cooking.assemble(input) : ItemStack.EMPTY;
            } else if (recipe instanceof MachineRecipe machineRecipe) {
                MachineKind kind = java.util.Arrays.stream(MachineKind.values()).filter(k -> k.id.equals(machineRecipe.machine())).findFirst().orElseThrow();
                MachineBlockEntity machine = machines.get(kind);
                machine.clearContent();
                for (MachineRecipe.Input input : machineRecipe.inputs()) machine.setItem(input.slot(), sample(input.ingredient(), input.count()));
                MachineRecipe found = machine.recipe();
                made = found == null ? ItemStack.EMPTY : found.assemble(new MachineRecipe.Inventory(kind, machine));
                if (found != null && !ItemStack.isSameItemSameComponents(made, machineRecipe.result().create())) {
                    failures.add(name + " resolves to " + made);
                    continue;
                }
            } else {
                failures.add(name + " has an unexpected type " + recipe.getType());
                continue;
            }
            if (made.isEmpty()) failures.add(name + " does not craft from its own ingredients");
        }
        h.assertTrue(checked > 2000, "Expected every fleet and CE recipe, checked " + checked);
        h.assertTrue(failures.isEmpty(), failures.size() + " recipes fail: " + String.join("; ", failures.subList(0, Math.min(10, failures.size()))));
        h.succeed();
    }

    private static void tiers(GameTestHelper h) {
        var tierOne = machine(h, MachineKind.ASSEMBLY_I, 0);
        var tierThree = machine(h, MachineKind.ASSEMBLY, 1);
        Object[] parts = {"controls", 2, "bogie", 6, "steel_frame", 2, "steel_ingot", 2, "steel_chimney", 1, "steel_cab", 1, "electric_motor", 6, "diesel_engine", 6, "generator", 4};
        for (var machine : new MachineBlockEntity[] {tierOne, tierThree}) {
            for (int slot = 0; slot * 2 < parts.length; slot++) machine.setItem(slot, new ItemStack(ProductionRegistry.item((String) parts[slot * 2]), (Integer) parts[slot * 2 + 1]));
            machine.setItem(9, new ItemStack(net.minecraft.world.item.Items.DYE.pick(net.minecraft.world.item.DyeColor.RED)));
        }
        h.assertTrue(tierThree.recipe() != null, "Table III assembles the SD40");
        h.assertTrue(tierOne.recipe() == null, "Table I does not assemble a tier three locomotive");
        h.succeed();
    }
}
