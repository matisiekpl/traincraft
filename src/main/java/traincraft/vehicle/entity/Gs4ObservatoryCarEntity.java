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

public class Gs4ObservatoryCarEntity extends PassengerEntity {

    public Gs4ObservatoryCarEntity(EntityType<? extends Gs4ObservatoryCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_GS4_OBSERVATORY;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Passenger GS4 Observatory");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_GS4_OBSERVATORY.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.05, 0.15);
    }
}
