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

public class Gs4CarEntity extends PassengerEntity {

    public Gs4CarEntity(EntityType<? extends Gs4CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_GS4;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Passenger GS4");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_GS4.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.5, 0.15);
    }
}
