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

public class Class205TailCarEntity extends PassengerEntity {

    public Class205TailCarEntity(EntityType<? extends Class205TailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_CLASS205_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class205tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_CLASS205_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.8F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.1), 0.0);
    }
}
