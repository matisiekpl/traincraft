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

public class HighSpeedZeroEdCarEntity extends PassengerEntity {

    public HighSpeedZeroEdCarEntity(EntityType<? extends HighSpeedZeroEdCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_HIGH_SPEED_ZERO_ED;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Passenger High Speed Zero ED");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_HIGH_SPEED_ZERO_ED.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.17F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.0), 0.0);
    }
}
