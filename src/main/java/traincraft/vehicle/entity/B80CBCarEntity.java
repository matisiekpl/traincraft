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

public class B80CBCarEntity extends PassengerEntity {

    public B80CBCarEntity(EntityType<? extends B80CBCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_B80_C_B;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("B80C_B");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_B80_C_B.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.75F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.8, -0.1);
    }
}
