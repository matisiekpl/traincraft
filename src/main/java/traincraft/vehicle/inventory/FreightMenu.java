package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.FreightEntity;

/** Community Edition's {@code InventoryFreight}: nine slots a row, the player below them. */
public class FreightMenu extends AbstractContainerMenu {

    private final FreightEntity freight;
    private final int rows;
    private final int cartSlots;

    protected FreightMenu(MenuType<?> type, int containerId, FreightEntity freight, int rows) {
        super(type, containerId);
        this.freight = freight;
        this.rows = rows;
        this.cartSlots = freight.getContainerSize();
    }

    public FreightMenu(int containerId, Inventory playerInventory, FreightEntity freight) {
        this(MenuRegistry.FREIGHT.get(), containerId, freight, freight.getContainerSize() / 9);
        // Four rows is the size the background was drawn for; a taller cart pushes the player's
        // own inventory down by the difference.
        int offset = (rows - 4) * 18;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(freight, column + row * 9, 8 + column * 18, 18 + row * 18));
            }
        }
        addPlayerSlots(playerInventory, 103 + offset);
    }

    protected void addPlayerSlots(Inventory playerInventory, int top) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(
                        new Slot(
                                playerInventory,
                                column + row * 9 + 9,
                                8 + column * 18,
                                top + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, top + 58));
        }
    }

    public FreightEntity freight() {
        return freight;
    }

    public int rows() {
        return rows;
    }

    @Override
    public boolean stillValid(Player player) {
        return freight.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        if (index < cartSlots) {
            if (!moveItemStackTo(stack, cartSlots, slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, 0, cartSlots, false)) {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return original;
    }
}
