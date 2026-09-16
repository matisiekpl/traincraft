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

public class Db420TailCarEntity extends PassengerEntity {

    public Db420TailCarEntity(EntityType<? extends Db420TailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_DB420_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("DB420Tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_DB420_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.965F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.5, -0.2);
    }
}
