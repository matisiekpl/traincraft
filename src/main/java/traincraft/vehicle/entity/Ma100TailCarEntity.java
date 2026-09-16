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

public class Ma100TailCarEntity extends PassengerEntity {

    public Ma100TailCarEntity(EntityType<? extends Ma100TailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_MA100_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("MA100_Tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_MA100_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.0), 0.0);
    }
}
