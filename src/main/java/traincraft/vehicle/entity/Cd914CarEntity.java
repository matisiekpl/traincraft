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

public class Cd914CarEntity extends PassengerEntity {

    public Cd914CarEntity(EntityType<? extends Cd914CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_CD914;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("CD914 Driving Trailer");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_CD914.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.2F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.3), 0.0);
    }
}
