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

public class MilwTailCarEntity extends PassengerEntity {

    public MilwTailCarEntity(EntityType<? extends MilwTailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_MILW_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Passenger MILW Tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_MILW_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.0F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.45, 0.0);
    }
}
