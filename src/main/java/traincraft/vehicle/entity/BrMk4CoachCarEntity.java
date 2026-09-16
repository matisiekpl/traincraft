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

public class BrMk4CoachCarEntity extends PassengerEntity {

    public BrMk4CoachCarEntity(EntityType<? extends BrMk4CoachCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_BR_MK4_COACH;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR_MK4_Coach");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_BR_MK4_COACH.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.05F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.15), 0.0);
    }
}
