package traincraft.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import traincraft.production.ProductionRegistry;

public class DieselGeneratorBlockEntity extends GeneratorBlockEntity implements Container, MenuProvider {

    public static final int SLOTS = 2;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int TANK_CAPACITY = 30000;
    public static final int CANISTER_VOLUME = 1000;
    private static final int FUEL_PER_BURN = 50;
    private static final int BURN_TICKS = 8;
    private static final int ENERGY_PER_TICK = 70;

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);
    private int diesel;
    private int burnTime;
    private boolean powered;

    public DieselGeneratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(Kind.DIESEL, pos, blockState);
    }

    public int diesel() {
        return diesel;
    }

    public int burnTime() {
        return burnTime;
    }

    public void setPowered(boolean value) {
        powered = value;
        setChanged();
    }

    @Override
    protected void produce() {
        ItemStack input = getItem(INPUT_SLOT);
        if (input.is(ProductionRegistry.item("diesel")) && diesel + CANISTER_VOLUME <= TANK_CAPACITY) {
            ItemStack output = getItem(OUTPUT_SLOT);
            ItemStack empty = new ItemStack(ProductionRegistry.item("empty_canister"));
            if (output.isEmpty() || ItemStack.isSameItemSameComponents(output, empty) && output.getCount() < output.getMaxStackSize()) {
                diesel += CANISTER_VOLUME;
                input.shrink(1);
                if (output.isEmpty()) {
                    setItem(OUTPUT_SLOT, empty);
                } else {
                    output.grow(1);
                }
                setChanged();
            }
        }
        if (burnTime > 0) {
            burnTime--;
            receive(ENERGY_PER_TICK);
        }
        if (powered && burnTime == 0 && diesel >= FUEL_PER_BURN && kind().capacity - stored() >= ENERGY_PER_TICK) {
            burnTime = BURN_TICKS;
            diesel -= FUEL_PER_BURN;
            setChanged();
        }
        if (spinning() != burnTime > 0) {
            setState(burnTime > 0 ? 1 : 0);
        }
    }

    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Diesel Generator");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new DieselGeneratorMenu(containerId, inventory, this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
        diesel = input.getIntOr("diesel", 0);
        burnTime = input.getIntOr("burnTime", 0);
        powered = input.getBooleanOr("powered", false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        output.putInt("diesel", diesel);
        output.putInt("burnTime", burnTime);
        output.putBoolean("powered", powered);
    }
}
