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

public class Ps54SeatCoachLoungeCarEntity extends PassengerEntity {

    public Ps54SeatCoachLoungeCarEntity(EntityType<? extends Ps54SeatCoachLoungeCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_PS54_SEAT_COACH_LOUNGE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("PS 54 Seat Coach-Lounge");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_PS54_SEAT_COACH_LOUNGE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.0F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.1), 0.0);
    }
}
