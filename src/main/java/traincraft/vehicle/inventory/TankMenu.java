package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.TankCartEntity;

public class TankMenu extends FreightMenu {

    private final TankCartEntity tank;

    public TankMenu(int containerId, Inventory playerInventory, TankCartEntity tank) {
        super(MenuRegistry.TANK.get(), containerId, tank, 3);
        this.tank = tank;
        addSlot(new Slot(tank, TankCartEntity.INPUT_SLOT, 8, 53));
        addSlot(new Slot(tank, TankCartEntity.OUTPUT_SLOT, 153, 53));
        addPlayerSlots(playerInventory, 84);
    }

    public TankCartEntity tank() {
        return tank;
    }
}
