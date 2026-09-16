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

public class DuewagGt6ErTailCarEntity extends PassengerEntity {

    public DuewagGt6ErTailCarEntity(EntityType<? extends DuewagGt6ErTailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_DUEWAG_GT6_ER_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("DuewagGT6ERTail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_DUEWAG_GT6_ER_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.35F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.5, 0.0);
    }
}
