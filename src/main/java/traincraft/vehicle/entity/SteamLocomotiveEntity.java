package traincraft.vehicle.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import traincraft.vehicle.simulation.CargoTank;

public abstract class SteamLocomotiveEntity extends LocomotiveEntity {

    public static final int FUEL_SLOTS = 1;
    public static final int WATER_SLOTS = 1;
    public static final int CARGO_SLOTS = 9;

    public static final int WATER_SLOT = 1;

    private static final EntityDataAccessor<Integer> WATER =
            SynchedEntityData.defineId(SteamLocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> FLUID =
            SynchedEntityData.defineId(SteamLocomotiveEntity.class, EntityDataSerializers.STRING);

    private final CargoTank tank = new CargoTank(() -> spec().tankCapacity());

    protected SteamLocomotiveEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean startsLit() {
        return true;
    }

    @Override
    public int inventorySize() {
        return FUEL_SLOTS + WATER_SLOTS + CARGO_SLOTS;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(WATER, 0);
        builder.define(FLUID, "");
    }

    public int getWater() {
        return entityData.get(WATER);
    }

    public int getTankCapacity() {
        return spec().tankCapacity();
    }

    /** The fluid in the tank, for the gauge's colour and its tooltip. Empty when dry. */
    public Fluid getFluid() {
        String id = entityData.get(FLUID);
        return id.isEmpty()
                ? Fluids.EMPTY
                : BuiltInRegistries.FLUID.getOptional(Identifier.parse(id)).orElse(Fluids.EMPTY);
    }

    public ResourceHandler<FluidResource> tank() {
        return tank;
    }

    /** Seeds the tank. Used by the capture harness so a shot is not of a dry locomotive. */
    public void fillWater(int millibuckets) {
        tank.fill(millibuckets);
        entityData.set(WATER, tank.amount());
    }

    /** The water gauge, scaled to {@code height} pixels. */
    public int getWaterScaled(int height) {
        int capacity = getTankCapacity();
        return capacity == 0 ? 0 : Math.abs(getWater() * height / capacity);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            return;
        }

        FluidResource resource = tank.getResource(0);
        int amount = tank.getAmountAsInt(0);
        entityData.set(WATER, amount);
        entityData.set(
                FLUID,
                resource.isEmpty()
                        ? ""
                        : BuiltInRegistries.FLUID.getKey(resource.getFluid()).toString());

        if (isFuelled() && amount <= 1) {
            // Fuelled but dry: the fire is lit and there is nothing to boil.
            setDeltaMovement(getDeltaMovement().multiply(0.94, 1.0, 0.94));
        }

        if (getRandom().nextInt(100) == 0 && amount > 0 && isFuelled()) {
            tank.drain(spec().waterConsumption() / 5);
        }
        emptyWaterContainerInSlot();
    }

    @Override
    protected void refuelFromSlot() {
        if (!drawFromTender()) {
            super.refuelFromSlot();
        }
    }

    private boolean drawFromTender() {
        if (!isEngineOn() || tickCount % 10 != 0) {
            return false;
        }
        TenderEntity tender =
                cartLinked1 instanceof TenderEntity first
                        ? first
                        : cartLinked2 instanceof TenderEntity second ? second : null;
        if (tender == null || !(level() instanceof ServerLevel server)) {
            return false;
        }
        if (tank.amount() + TenderEntity.DRAW <= spec().tankCapacity()) {
            tank.moveFrom(tender.tank(), TenderEntity.DRAW);
        }
        for (int slot = TenderEntity.WATER_SLOT + 1; slot < tender.getContainerSize(); slot++) {
            ItemStack stack = tender.getItem(slot);
            if (!stack.isEmpty() && fuel.refill(server.fuelValues().burnDuration(stack))) {
                stack.shrink(1);
                tender.setChanged();
                return true;
            }
        }
        return false;
    }

    private void emptyWaterContainerInSlot() {
        if (getItem(WATER_SLOT).isEmpty() || tickCount % 8 != 0) {
            return;
        }
        ItemStack emptied = tank.drainContainer(this, WATER_SLOT);
        if (!emptied.isEmpty() && !placeInCargo(this, WATER_SLOT + 1, emptied)) {
            spawnAtLocation((net.minecraft.server.level.ServerLevel) level(), emptied, 1.0F);
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        input.child("tank").ifPresent(tank::deserialize);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        tank.serialize(output.child("tank"));
    }
}
