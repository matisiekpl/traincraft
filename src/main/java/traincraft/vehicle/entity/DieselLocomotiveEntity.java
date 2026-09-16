package traincraft.vehicle.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import traincraft.production.ProductionRegistry;

/** CE's DieselTrain: a fuel tank in millibuckets, filled from canisters, drained per interval. */
public abstract class DieselLocomotiveEntity extends LocomotiveEntity {

    public static final int FUEL_SLOTS = 1;
    public static final int CARGO_SLOTS = 9;
    public static final int CANISTER_VOLUME = 1000;

    private static final EntityDataAccessor<Integer> TANK =
            SynchedEntityData.defineId(DieselLocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> FLUID =
            SynchedEntityData.defineId(DieselLocomotiveEntity.class, EntityDataSerializers.STRING);

    private int fuelTicks;
    private int refuelTicks;

    protected DieselLocomotiveEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public int inventorySize() {
        return FUEL_SLOTS + CARGO_SLOTS;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TANK, 0);
        builder.define(FLUID, "");
    }

    public int getTankCapacity() {
        return spec().tankCapacity();
    }

    /** The fluid's display name, upstream's {@code getLiquidName}. Empty when dry. */
    public String getFluidName() {
        return entityData.get(FLUID);
    }

    @Override
    public int getFuel() {
        return entityData.get(TANK);
    }

    @Override
    public int getFuelScaled(int height) {
        return Math.abs(getFuel() * height / getTankCapacity());
    }

    @Override
    public void restoreFuel(int amount) {
        fill("Diesel", Math.clamp(amount, 0, getTankCapacity()) - getFuel());
    }

    private void fill(String fluid, int amount) {
        entityData.set(TANK, getFuel() + amount);
        entityData.set(FLUID, getFuel() > 0 ? fluid : "");
    }

    public static boolean acceptsFuel(ItemStack stack) {
        return fluidOf(stack) != null;
    }

    private static String fluidOf(ItemStack stack) {
        if (stack.is(ProductionRegistry.item("diesel"))) return "Diesel";
        if (stack.is(ProductionRegistry.item("refined_fuel"))) return "RefinedFuel";
        return null;
    }

    @Override
    protected void tickFuel(int consumption) {
        if (getFuel() <= 0) {
            setDeltaMovement(getDeltaMovement().multiply(0.88, 1.0, 0.88));
        } else if (isEngineOn() && getFuel() <= 1) {
            setDeltaMovement(getDeltaMovement().multiply(0.94, 1.0, 0.94));
        }
        if (++fuelTicks < 100) return;
        fuelTicks = 0;
        if (getFuel() > 1 && isEngineOn()) {
            int remaining = getFuel() - consumption;
            fill(getFluidName(), Math.max(0, remaining) - getFuel());
            if (remaining < 0) setEngineOn(false);
        }
    }

    /** CE's {@code liquidInSlot}: every eighth tick one canister empties into the tank. */
    @Override
    protected void refuelFromSlot() {
        drawFromTankCart();
        ItemStack stack = getItem(FUEL_SLOT);
        String fluid = fluidOf(stack);
        if (fluid == null || ++refuelTicks % 8 != 0 || !(level() instanceof ServerLevel server)) return;
        boolean sameFluid = getFluid().isEmpty() || getFluidName().equals(fluid);
        if (!sameFluid || getFuel() + CANISTER_VOLUME > getTankCapacity()) return;
        fill(fluid, CANISTER_VOLUME);
        stack.shrink(1);
        ItemStack canister = new ItemStack(ProductionRegistry.item("empty_canister"));
        if (!placeInCargo(this, FUEL_SLOT + 1, canister)) {
            spawnAtLocation(server, canister, 1.0F);
        }
        setChanged();
    }

    private String getFluid() {
        return getFluidName();
    }

    private int drawTicks;

    private void drawFromTankCart() {
        if (!isEngineOn() || ++drawTicks % 10 != 0 || getFuel() + TenderEntity.DRAW > getTankCapacity()) {
            return;
        }
        for (RollingStockEntity linked : new RollingStockEntity[] {cartLinked1, cartLinked2}) {
            if (!(linked instanceof TankCartEntity cart) || !cart.holdsFuel()) {
                continue;
            }
            if (!getFluid().isEmpty() && !getFluidName().equals(cart.fuelName())) {
                continue;
            }
            int drawn = cart.drawFuel(cart.fuelName(), TenderEntity.DRAW);
            if (drawn > 0) {
                fill(cart.fuelName(), drawn);
                return;
            }
        }
    }
    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(TANK, Math.clamp(input.getIntOr("tankAmount", 0), 0, getTankCapacity()));
        entityData.set(FLUID, getFuel() > 0 ? input.getStringOr("tankFluid", "Diesel") : "");
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("tankAmount", getFuel());
        output.putString("tankFluid", getFluidName());
    }
}
