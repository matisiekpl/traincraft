package traincraft.vehicle.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import traincraft.production.ProductionRegistry;
import traincraft.vehicle.inventory.TankMenu;
import traincraft.vehicle.simulation.CargoTank;

public abstract class TankCartEntity extends FreightEntity {

    public static final int SLOTS = 2;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int LAVA_TEMPERATURE = 1000;

    private static final EntityDataAccessor<Integer> AMOUNT =
            SynchedEntityData.defineId(TankCartEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> FLUID =
            SynchedEntityData.defineId(TankCartEntity.class, EntityDataSerializers.STRING);

    private final CargoTank tank = new CargoTank(() -> spec().tankCapacity(), this::accepts);
    private int fuelAmount;
    private String fuelName = "";

    protected TankCartEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    protected boolean lavaOnly() {
        return false;
    }

    protected boolean fuelOnly() {
        return false;
    }

    private boolean accepts(Fluid fluid) {
        if (fuelOnly()) {
            return false;
        }
        boolean hot = fluid.getFluidType().getTemperature() >= LAVA_TEMPERATURE;
        return hot == lavaOnly();
    }

    @Override
    protected int cargoSlots() {
        return SLOTS;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(AMOUNT, 0);
        builder.define(FLUID, "");
    }

    public int getAmount() {
        return entityData.get(AMOUNT);
    }

    public String getFluidName() {
        return entityData.get(FLUID);
    }

    public int getTankCapacity() {
        return spec().tankCapacity();
    }

    public CargoTank tank() {
        return tank;
    }

    public boolean holdsFuel() {
        return fuelAmount > 0;
    }

    public int drawFuel(String fluid, int millibuckets) {
        if (fuelAmount == 0 || !fuelName.equals(fluid)) {
            return 0;
        }
        int drawn = Math.min(millibuckets, fuelAmount);
        fuelAmount -= drawn;
        if (fuelAmount == 0) {
            fuelName = "";
        }
        return drawn;
    }

    public String fuelName() {
        return fuelName;
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TankMenu(containerId, inventory, this);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            return;
        }
        if (tickCount % 8 == 0) {
            exchangeContainers();
        }
        if (spec().colours().contains("Full")) {
            setColour(fuelAmount > 0 || tank.amount() > 0 ? "Full" : "Empty");
        }
        if (fuelAmount > 0) {
            entityData.set(AMOUNT, fuelAmount);
            entityData.set(FLUID, fuelName);
        } else {
            entityData.set(AMOUNT, tank.amount());
            Fluid fluid = tank.fluid();
            entityData.set(
                    FLUID,
                    tank.amount() == 0 ? "" : BuiltInRegistries.FLUID.getKey(fluid).toString());
        }
    }

    private void exchangeContainers() {
        ItemStack input = getItem(INPUT_SLOT);
        if (input.isEmpty()) {
            return;
        }
        ItemStack output = getItem(OUTPUT_SLOT);
        if (output.getCount() >= output.getMaxStackSize()) {
            return;
        }
        ItemStack exchanged = exchangeCanister(input);
        if (exchanged.isEmpty() && fuelAmount == 0) {
            exchanged = tank.drainContainer(this, INPUT_SLOT);
            if (exchanged.isEmpty()) {
                exchanged = tank.fillContainer(this, INPUT_SLOT);
            }
        }
        if (exchanged.isEmpty()) {
            return;
        }
        if (output.isEmpty()) {
            setItem(OUTPUT_SLOT, exchanged);
        } else if (ItemStack.isSameItemSameComponents(output, exchanged)) {
            output.grow(exchanged.getCount());
        } else if (getItem(INPUT_SLOT).isEmpty()) {
            setItem(INPUT_SLOT, exchanged);
            return;
        } else {
            spawnAtLocation((ServerLevel) level(), exchanged, 1.0F);
        }
        setChanged();
    }

    private ItemStack exchangeCanister(ItemStack input) {
        if (tank.amount() > 0) {
            return ItemStack.EMPTY;
        }
        String fluid = fuelOf(input);
        if (fluid != null) {
            boolean same = fuelAmount == 0 || fuelName.equals(fluid);
            if (!same || fuelAmount + DieselLocomotiveEntity.CANISTER_VOLUME > getTankCapacity()) {
                return ItemStack.EMPTY;
            }
            fuelAmount += DieselLocomotiveEntity.CANISTER_VOLUME;
            fuelName = fluid;
            input.shrink(1);
            return new ItemStack(ProductionRegistry.item("empty_canister"));
        }
        if (input.is(ProductionRegistry.item("empty_canister"))
                && fuelAmount >= DieselLocomotiveEntity.CANISTER_VOLUME) {
            ItemStack filled =
                    new ItemStack(
                            ProductionRegistry.item(fuelName.equals("Diesel") ? "diesel" : "refined_fuel"));
            drawFuel(fuelName, DieselLocomotiveEntity.CANISTER_VOLUME);
            input.shrink(1);
            return filled;
        }
        return ItemStack.EMPTY;
    }

    private static String fuelOf(ItemStack stack) {
        if (stack.is(ProductionRegistry.item("diesel"))) return "Diesel";
        if (stack.is(ProductionRegistry.item("refined_fuel"))) return "RefinedFuel";
        return null;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        input.child("tank").ifPresent(tank::deserialize);
        fuelAmount = input.getIntOr("fuelAmount", 0);
        fuelName = input.getStringOr("fuelName", "");
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        tank.serialize(output.child("tank"));
        output.putInt("fuelAmount", fuelAmount);
        output.putString("fuelName", fuelName);
    }

    public static Identifier fluidId(String name) {
        return Identifier.tryParse(name);
    }

    public Fluid displayedFluid() {
        Identifier id = fluidId(getFluidName());
        return id == null ? Fluids.EMPTY : BuiltInRegistries.FLUID.getOptional(id).orElse(Fluids.EMPTY);
    }
}
