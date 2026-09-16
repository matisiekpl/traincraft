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

public class Class442TsCarEntity extends PassengerEntity {

    public Class442TsCarEntity(EntityType<? extends Class442TsCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_CLASS442_TS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class442TS");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_CLASS442_TS.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.05F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.1), 0.0);
    }
}
