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

public class PsSleeper565DrgwCarEntity extends PassengerEntity {

    public PsSleeper565DrgwCarEntity(EntityType<? extends PsSleeper565DrgwCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_PS_SLEEPER565_DRGW;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("PS 5-6-5 Sleeper DRGW Edition");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_PS_SLEEPER565_DRGW.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.0F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.1), 0.0);
    }
}
