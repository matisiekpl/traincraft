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

public class RheingoldPanoramaCarEntity extends PassengerEntity {

    public RheingoldPanoramaCarEntity(EntityType<? extends RheingoldPanoramaCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_RHEINGOLD_PANORAMA;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Rheingold Panorama");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_RHEINGOLD_PANORAMA.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.95F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.5, 0.4);
    }
}
