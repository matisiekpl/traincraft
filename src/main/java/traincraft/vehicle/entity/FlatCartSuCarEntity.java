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

public class FlatCartSuCarEntity extends PassengerEntity {

    public FlatCartSuCarEntity(EntityType<? extends FlatCartSuCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_FLAT_CART_SU;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Flat Cart SU");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_FLAT_CART_SU.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.7F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.4), 0.0);
    }
}
