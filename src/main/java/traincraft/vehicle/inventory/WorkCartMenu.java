package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.WorkCartEntity;

public class WorkCartMenu extends FreightMenu {

    private final WorkCartEntity cart;

    public WorkCartMenu(int containerId, Inventory playerInventory, WorkCartEntity cart) {
        super(MenuRegistry.WORK_CART.get(), containerId, cart, 3);
        this.cart = cart;
        addSlot(new Slot(cart, WorkCartEntity.INPUT_SLOT, 56, 17));
        addSlot(
                new Slot(cart, WorkCartEntity.FUEL_SLOT, 56, 53) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.getBurnTime(null, playerInventory.player.level().fuelValues()) > 0;
                    }
                });
        addSlot(new FurnaceResultSlot(playerInventory.player, cart, WorkCartEntity.OUTPUT_SLOT, 116, 35));
        addPlayerSlots(playerInventory, 84);
    }

    public WorkCartEntity cart() {
        return cart;
    }
}
