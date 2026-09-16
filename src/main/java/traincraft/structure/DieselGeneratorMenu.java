package traincraft.structure;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DieselGeneratorMenu extends AbstractContainerMenu {

    private final DieselGeneratorBlockEntity generator;
    private final ContainerData data;

    public DieselGeneratorMenu(int containerId, Inventory inventory, DieselGeneratorBlockEntity generator) {
        super(StructureRegistry.DIESEL_GENERATOR_MENU.get(), containerId);
        this.generator = generator;
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return index == 0 ? generator.diesel() : index == 1 ? generator.burnTime() : generator.stored();
            }

            @Override
            public void set(int index, int value) {}

            @Override
            public int getCount() {
                return 3;
            }
        };
        addSlot(new Slot(generator, DieselGeneratorBlockEntity.INPUT_SLOT, 56, 17));
        addSlot(new Slot(generator, DieselGeneratorBlockEntity.OUTPUT_SLOT, 56, 53));
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inventory, column, 8 + column * 18, 142));
        }
        addDataSlots(data);
    }

    public int diesel() {
        return data.get(0);
    }

    public int burnTime() {
        return data.get(1);
    }

    public int energy() {
        return data.get(2);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        if (index < DieselGeneratorBlockEntity.SLOTS) {
            if (!moveItemStackTo(stack, DieselGeneratorBlockEntity.SLOTS, slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, 0, DieselGeneratorBlockEntity.INPUT_SLOT + 1, false)) {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return original;
    }

    @Override
    public boolean stillValid(Player player) {
        return generator.stillValid(player);
    }
}
