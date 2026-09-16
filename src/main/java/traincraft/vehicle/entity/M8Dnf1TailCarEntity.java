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

public class M8Dnf1TailCarEntity extends PassengerEntity {

    public M8Dnf1TailCarEntity(EntityType<? extends M8Dnf1TailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_M8_DNF1_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("M8DNF1Tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_M8_DNF1_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.0F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.1, -0.25);
    }
}
