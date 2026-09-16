package traincraft.production;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

/** Slot-addressed CE recipes. Empty input slots are significant in assembly/workbench recipes. */
public record MachineRecipe(String machine, List<Input> inputs, ItemStackTemplate result, int duration, int chance, int diesel, int tier)
        implements Recipe<MachineRecipe.Inventory> {
    public record Input(int slot, Ingredient ingredient, int count) {
        public static final Codec<Input> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.intRange(0, 9).fieldOf("slot").forGetter(Input::slot),
                Ingredient.CODEC.fieldOf("ingredient").forGetter(Input::ingredient),
                Codec.intRange(1, 64).optionalFieldOf("count", 1).forGetter(Input::count)
        ).apply(i, Input::new));
    }
    public static final MapCodec<MachineRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.STRING.fieldOf("machine").forGetter(MachineRecipe::machine),
            Input.CODEC.listOf().fieldOf("inputs").forGetter(MachineRecipe::inputs),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(MachineRecipe::result),
            Codec.intRange(0, 100000).optionalFieldOf("duration", 0).forGetter(MachineRecipe::duration),
            Codec.intRange(1, 100000).optionalFieldOf("chance", 1).forGetter(MachineRecipe::chance),
            Codec.intRange(0, 30000).optionalFieldOf("diesel", 0).forGetter(MachineRecipe::diesel),
            Codec.intRange(1, 3).optionalFieldOf("tier", 3).forGetter(MachineRecipe::tier)
    ).apply(i, MachineRecipe::new));
    public MachineRecipe {
        inputs = List.copyOf(inputs);
        MachineKind kind = java.util.Arrays.stream(MachineKind.values()).filter(k -> k.id.equals(machine)).findFirst().orElseThrow();
        if (inputs.isEmpty() || inputs.stream().anyMatch(in -> in.slot >= kind.inputs)
                || inputs.stream().map(Input::slot).distinct().count() != inputs.size())
            throw new IllegalArgumentException("Invalid machine recipe slots");
    }
    public record Inventory(MachineKind kind, Container container) implements RecipeInput {
        @Override public ItemStack getItem(int slot) { return container.getItem(slot); }
        @Override public int size() { return kind.inputs; }
    }
    @Override public boolean matches(Inventory inventory, Level level) {
        if (inventory.kind.tier > 0 ? tier > inventory.kind.tier || !machine.equals(MachineKind.ASSEMBLY.id) : !inventory.kind.id.equals(machine)) return false;
        for (int slot = 0; slot < inventory.size(); slot++) {
            Input expected = null;
            for (Input input : inputs) if (input.slot == slot) expected = input;
            ItemStack stack = inventory.getItem(slot);
            if (expected == null ? !stack.isEmpty() : !expected.ingredient.test(stack) || stack.getCount() < expected.count) return false;
        }
        return true;
    }
    @Override public ItemStack assemble(Inventory inventory) { return result.create(); }
    @Override public boolean showNotification() { return false; }
    @Override public boolean isSpecial() { return true; }
    @Override public String group() { return ""; }
    @Override public RecipeSerializer<MachineRecipe> getSerializer() { return ProductionRegistry.SERIALIZER.get(); }
    @Override public RecipeType<MachineRecipe> getType() { return ProductionRegistry.RECIPE_TYPE.get(); }
    @Override public PlacementInfo placementInfo() { return PlacementInfo.NOT_PLACEABLE; }
    @Override public RecipeBookCategory recipeBookCategory() { return RecipeBookCategories.CRAFTING_MISC; }
}
