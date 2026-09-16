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

public class Metro3000CarEntity extends PassengerEntity {

    public Metro3000CarEntity(EntityType<? extends Metro3000CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_METRO3000;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Metro3000Passenger");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_METRO3000.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.25F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.3), 0.0);
    }
}
