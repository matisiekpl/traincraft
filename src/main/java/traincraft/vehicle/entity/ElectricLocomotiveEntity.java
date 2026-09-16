package traincraft.vehicle.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import traincraft.vehicle.simulation.HeatState;

/** CE's ElectricTrain: redstone storage and separate item/block energy conversion. */
public abstract class ElectricLocomotiveEntity extends LocomotiveEntity {
    private int energyTicks;

    protected ElectricLocomotiveEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override public int inventorySize() { return 16; }
    @Override public boolean canOverheat() { return false; }
    @Override protected void tickHeat() { setState(HeatState.HOT); }

    @Override protected void tickFuel(int consumption) {
        if (++energyTicks < 100) return;
        energyTicks = 0;
        if (isEngineOn()) {
            int remaining = fuel.amount() - consumption;
            fuel.restore(remaining);
            // CE turns off only when subtraction crosses zero, not when it reaches zero.
            if (remaining < 0) setEngineOn(false);
        }
    }

    public static boolean acceptsEnergy(ItemStack stack) {
        return stack.is(Items.REDSTONE) || stack.is(Items.REDSTONE_BLOCK)
                || ItemAccess.forStack(stack).getCapability(Capabilities.Energy.ITEM) != null;
    }

    @Override protected void refuelFromSlot() {
        ItemStack stack = getItem(FUEL_SLOT);
        int value = stack.is(Items.REDSTONE) ? 2000 : stack.is(Items.REDSTONE_BLOCK) ? 18000 : 0;
        if (value > 0) {
            if (fuel.refill(value)) {
                stack.shrink(1);
                setChanged();
            }
        } else if (!stack.isEmpty()) {
            var access = ItemAccess.forHandlerIndex(VanillaContainerWrapper.of(this), FUEL_SLOT);
            drawEnergy(access.getCapability(Capabilities.Energy.ITEM), 10);
        }
        // Order and coordinates are those of ElectricTrain, including flooring negative positions.
        for (int offset : new int[] {-1, 2, 3, 4}) {
            BlockPos pos = BlockPos.containing(getX(), getY() + offset, getZ());
            if (!level().hasChunkAt(pos)) continue;
            for (Direction side : Direction.values()) {
                if (drawEnergy(level().getCapability(Capabilities.Energy.BLOCK, pos, side), 1)) return;
            }
        }
    }

    private boolean drawEnergy(EnergyHandler source, int multiplier) {
        int draw = Math.min(200, MAX_FUEL - fuel.amount()) / 10;
        if (source == null || draw == 0) return false;
        int extracted;
        try (Transaction transaction = Transaction.openRoot()) {
            extracted = source.extract(draw, transaction);
            if (extracted == 0) return false;
            transaction.commit();
        }
        fuel.refill(extracted * multiplier);
        setChanged();
        return true;
    }
}
