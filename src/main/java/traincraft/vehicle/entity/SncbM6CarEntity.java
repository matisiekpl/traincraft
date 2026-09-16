package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class SncbM6CarEntity extends PassengerEntity {

    public SncbM6CarEntity(EntityType<? extends SncbM6CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_SNCB_M6;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("SNCB_M6");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_SNCB_M6.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.15F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.1, 0.15);
    }
}
