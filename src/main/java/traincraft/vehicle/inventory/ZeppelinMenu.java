package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.ZeppelinEntity;

public class ZeppelinMenu extends AbstractContainerMenu {

    private final ZeppelinEntity zeppelin;

    public ZeppelinMenu(int containerId, Inventory playerInventory, ZeppelinEntity zeppelin) {
        super(MenuRegistry.ZEPPELIN.get(), containerId);
        this.zeppelin = zeppelin;
        addSlot(new Slot(zeppelin, ZeppelinEntity.FUEL_SLOT, 8, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return playerInventory.player.level().fuelValues().burnDuration(stack) > 0;
            }
        });
        for (int slot = 1; slot < ZeppelinEntity.SLOTS; slot++) {
            int index = slot - 1;
            addSlot(new Slot(zeppelin, slot, 80 + index % 3 * 18, 18 + index / 3 * 18));
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    public ZeppelinEntity zeppelin() {
        return zeppelin;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        if (index < ZeppelinEntity.SLOTS) {
            if (!moveItemStackTo(stack, ZeppelinEntity.SLOTS, slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, 0, ZeppelinEntity.SLOTS, false)) {
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
        return zeppelin.stillValid(player);
    }
}
