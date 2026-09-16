package traincraft.vehicle.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import traincraft.vehicle.inventory.TenderMenu;
import traincraft.vehicle.simulation.CargoTank;

public abstract class TenderEntity extends FreightEntity {

    public static final int SLOTS = 16;
    public static final int WATER_SLOT = 0;
    public static final int DRAW = 100;

    private static final EntityDataAccessor<Integer> WATER =
            SynchedEntityData.defineId(TenderEntity.class, EntityDataSerializers.INT);

    private final CargoTank tank = new CargoTank(() -> spec().tankCapacity());

    protected TenderEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected int cargoSlots() {
        return SLOTS;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(WATER, 0);
    }

    public int getWater() {
        return entityData.get(WATER);
    }

    public int getTankCapacity() {
        return spec().tankCapacity();
    }

    public CargoTank tank() {
        return tank;
    }

    public void fillWater(int millibuckets) {
        tank.fill(millibuckets);
        entityData.set(WATER, tank.amount());
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TenderMenu(containerId, inventory, this);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            return;
        }
        entityData.set(WATER, tank.amount());
        if (!getItem(WATER_SLOT).isEmpty() && tickCount % 8 == 0) {
            ItemStack emptied = tank.drainContainer(this, WATER_SLOT);
            if (!emptied.isEmpty() && !placeInCargo(this, WATER_SLOT + 1, emptied)) {
                spawnAtLocation((net.minecraft.server.level.ServerLevel) level(), emptied, 1.0F);
            }
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
