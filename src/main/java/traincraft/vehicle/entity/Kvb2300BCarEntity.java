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

public class Kvb2300BCarEntity extends PassengerEntity {

    public Kvb2300BCarEntity(EntityType<? extends Kvb2300BCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_KVB_2300_B;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("KVB_2300_B");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_KVB_2300_B.get();
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
