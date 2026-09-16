package traincraft.production;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class MachineBlockEntity extends BaseContainerBlockEntity {
    public final MachineKind kind;
    private NonNullList<ItemStack> items;
    public int progress, burnTime, burnDuration, diesel;
    private String activeRecipe = "";

    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(ProductionRegistry.MACHINE.get(), pos, state);
        kind = ((MachineBlock) state.getBlock()).kind;
        items = NonNullList.withSize(kind.slots, ItemStack.EMPTY);
    }
    @Override protected NonNullList<ItemStack> getItems() { return items; }
    @Override protected void setItems(NonNullList<ItemStack> value) { items = value; }
    @Override public int getContainerSize() { return kind.slots; }
    @Override protected Component getDefaultName() { return Component.translatable("block.tc." + kind.id); }
    @Override protected AbstractContainerMenu createMenu(int id, Inventory inventory) { return new MachineMenu(id, inventory, this); }

    public MachineRecipe recipe() {
        if (!(level instanceof ServerLevel server)) return null;
        var inventory = new MachineRecipe.Inventory(kind, this);
        MachineRecipe best = null;
        int bestCount = -1;
        for (var holder : server.recipeAccess().recipeMap().byType(ProductionRegistry.RECIPE_TYPE.get())) {
            MachineRecipe recipe = holder.value();
            if (!recipe.matches(inventory, level)) continue;
            int count = recipe.inputs().stream().mapToInt(MachineRecipe.Input::count).sum();
            if (count > bestCount) {
                best = recipe;
                bestCount = count;
            }
        }
        return best;
    }
    public void tick() {
        if (kind.instantaneous()) return;
        if (burnTime > 0) burnTime--;
        if (kind == MachineKind.DISTILLERY) fillCanister();
        MachineRecipe recipe = recipe();
        String signature = recipe == null ? "" : recipe.toString();
        if (!signature.equals(activeRecipe)) { progress = 0; activeRecipe = signature; }
        boolean canCraft = recipe != null && canOutput(recipe.result().create()) && diesel + recipe.diesel() <= 30000;
        if (canCraft && burnTime == 0 && level instanceof ServerLevel server) {
            ItemStack fuel = getItem(kind.fuel);
            int ticks = fuel.getBurnTime(null, server.fuelValues());
            if (ticks > 0) {
                burnTime = burnDuration = ticks;
                consume(kind.fuel, 1);
            }
        }
        if (canCraft && burnTime > 0) {
            if (++progress >= recipe.duration()) {
                progress = 0;
                for (var input : recipe.inputs()) consume(input.slot(), input.count());
                diesel += recipe.diesel();
                if (level.getRandom().nextInt(recipe.chance()) == 0) output(recipe.result().create());
            }
        } else progress = 0;
        if (getBlockState().getValue(MachineBlock.LIT) != (burnTime > 0))
            level.setBlock(worldPosition, getBlockState().setValue(MachineBlock.LIT, burnTime > 0), 3);
        setChanged();
    }
    private void fillCanister() {
        if (diesel < 1000 || !getItem(2).is(ProductionRegistry.item("empty_canister"))) return;
        ItemStack output = getItem(4);
        if (!output.isEmpty() && (!output.is(ProductionRegistry.item("diesel")) || output.getCount() >= output.getMaxStackSize())) return;
        getItem(2).shrink(1);
        if (output.isEmpty()) setItem(4, new ItemStack(ProductionRegistry.item("diesel")));
        else output.grow(1);
        diesel -= 1000;
    }
    public boolean canOutput(ItemStack result) {
        ItemStack current = getItem(kind.output);
        return current.isEmpty() || ItemStack.isSameItemSameComponents(current, result)
                && current.getCount() + result.getCount() <= current.getMaxStackSize();
    }
    private void output(ItemStack result) {
        if (getItem(kind.output).isEmpty()) setItem(kind.output, result.copy());
        else getItem(kind.output).grow(result.getCount());
    }
    public void consume(int slot, int count) {
        ItemStack source = getItem(slot);
        var template = source.getCraftingRemainder();
        ItemStack remainder = template == null ? ItemStack.EMPTY : template.create();
        source.shrink(count);
        if (source.isEmpty() && !remainder.isEmpty()) setItem(slot, remainder);
        setChanged();
    }
    @Override protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
        progress = input.getIntOr("CookTime", 0);
        burnTime = input.getIntOr("BurnTime", 0);
        burnDuration = input.getIntOr("BurnDuration", 0);
        diesel = Math.clamp(input.getIntOr("Diesel", 0), 0, 30000);
        activeRecipe = input.getStringOr("ActiveRecipe", "");
    }
    @Override protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        output.putInt("CookTime", progress);
        output.putInt("BurnTime", burnTime);
        output.putInt("BurnDuration", burnDuration);
        output.putInt("Diesel", diesel);
        output.putString("ActiveRecipe", activeRecipe);
    }
}
