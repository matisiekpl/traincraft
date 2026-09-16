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

public class Bw305CarEntity extends PassengerEntity {

    public Bw305CarEntity(EntityType<? extends Bw305CarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_BW305;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BW305");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_BW305.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.05F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.0), 0.0);
    }
}
