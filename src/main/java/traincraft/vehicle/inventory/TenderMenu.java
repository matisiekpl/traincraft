package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.access.ItemAccess;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.TenderEntity;

public class TenderMenu extends FreightMenu {

    private final TenderEntity tender;

    public TenderMenu(int containerId, Inventory playerInventory, TenderEntity tender) {
        super(MenuRegistry.TENDER.get(), containerId, tender, 3);
        this.tender = tender;
        addSlot(new Slot(tender, TenderEntity.WATER_SLOT, 8, 53));
        for (int slot = 1; slot < TenderEntity.SLOTS; slot++) {
            int index = slot - 1;
            addSlot(
                    new Slot(tender, slot, 44 + index % 5 * 18, 18 + index / 5 * 18) {
                        @Override
                        public boolean mayPlace(ItemStack stack) {
                            return ItemAccess.forStack(stack).getCapability(Capabilities.Fluid.ITEM) != null
                                    || playerInventory.player.level().fuelValues().burnDuration(stack) > 0;
                        }
                    });
        }
        addPlayerSlots(playerInventory, 84);
    }

    public TenderEntity tender() {
        return tender;
    }
}
