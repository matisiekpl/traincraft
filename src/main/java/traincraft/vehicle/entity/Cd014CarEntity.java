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

public class Cd014CarEntity extends PassengerEntity {

    public Cd014CarEntity(EntityType<? extends Cd014CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_CD014;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("CD014 Trailer");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_CD014.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.15F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.3), 0.0);
    }
}
