package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.access.ItemAccess;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.SteamLocomotiveEntity;

public class LocomotiveMenu extends AbstractContainerMenu {

    private final LocomotiveEntity loco;

    public LocomotiveMenu(int containerId, Inventory playerInventory, LocomotiveEntity loco) {
        super(MenuRegistry.LOCO.get(), containerId);
        this.loco = loco;

        int slot = 0;
        addSlot(new FuelSlot(loco, slot++, 8, 53));
        if (loco instanceof SteamLocomotiveEntity) {
            addSlot(new WaterSlot(loco, slot++, 32, 53));
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < (loco instanceof traincraft.vehicle.entity.ElectricLocomotiveEntity ? 5 : 3); column++) {
                addSlot(new Slot(loco, slot++, 80 + column * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(
                        new Slot(
                                playerInventory,
                                column + row * 9 + 9,
                                8 + column * 18,
                                84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    public LocomotiveEntity loco() {
        return loco;
    }

    @Override
    public boolean stillValid(Player player) {
        return loco.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        int locoSlots = loco.getContainerSize();

        if (index < locoSlots) {
            if (!moveItemStackTo(stack, locoSlots, slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (acceptsFuel(loco, stack)) {
            if (!moveItemStackTo(
                    stack, LocomotiveEntity.FUEL_SLOT, LocomotiveEntity.FUEL_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (loco instanceof SteamLocomotiveEntity && isWaterContainer(stack)) {
            if (!moveItemStackTo(
                    stack,
                    SteamLocomotiveEntity.WATER_SLOT,
                    SteamLocomotiveEntity.WATER_SLOT + 1,
                    false)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, loco instanceof SteamLocomotiveEntity ? 2 : 1, locoSlots, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return original;
    }

    /**
     * Anything a furnace would burn, which is exactly what the firebox accepts.
     *
     * <p>The burn-time table has to come from the level. Passing null for it works on the server,
     * where the overload falls back to the running server's copy, and throws on the client, where
     * there is none to fall back to -- so dragging a stack across the fuel slot crashed the game.
     * The slot is asked this question on both sides, so both are given the table.
     */
    static boolean isFuel(Level level, ItemStack stack) {
        return !stack.isEmpty() && stack.getBurnTime(null, level.fuelValues()) > 0;
    }

    /** Anything that can hand over water: a bucket, or another mod's container. */
    static boolean isWaterContainer(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        var handler = ItemAccess.forStack(stack).getCapability(Capabilities.Fluid.ITEM);
        if (handler == null) {
            return false;
        }
        for (int i = 0; i < handler.size(); i++) {
            if (handler.getResource(i).getFluid() == Fluids.WATER) {
                return true;
            }
        }
        return false;
    }

    private static boolean acceptsFuel(LocomotiveEntity loco, ItemStack stack) {
        if (loco instanceof traincraft.vehicle.entity.ElectricLocomotiveEntity) {
            return traincraft.vehicle.entity.ElectricLocomotiveEntity.acceptsEnergy(stack);
        }
        if (loco instanceof traincraft.vehicle.entity.DieselLocomotiveEntity) {
            return traincraft.vehicle.entity.DieselLocomotiveEntity.acceptsFuel(stack);
        }
        return isFuel(loco.level(), stack);
    }

    private static class FuelSlot extends Slot {
        FuelSlot(LocomotiveEntity loco, int index, int x, int y) {
            super(loco, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return acceptsFuel((LocomotiveEntity) container, stack);
        }

        @Override
        public int getMaxStackSize() {
            return 64;
        }
    }

    private static class WaterSlot extends Slot {
        WaterSlot(LocomotiveEntity loco, int index, int x, int y) {
            super(loco, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return isWaterContainer(stack);
        }
    }
}
