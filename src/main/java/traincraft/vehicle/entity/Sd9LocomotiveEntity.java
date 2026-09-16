package traincraft.vehicle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.ItemRegistry;
import traincraft.bootstrap.SoundRegistry;
import traincraft.vehicle.definition.Exhaust;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;
import traincraft.vehicle.sound.LocomotiveSounds;

public class Sd9LocomotiveEntity extends DieselLocomotiveEntity {

    public Sd9LocomotiveEntity(EntityType<? extends Sd9LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_SD9;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_M3.get(), 1.0F,
                SoundRegistry.EMD_16_567C_NOTCH8.get(), 0.9F, 8,
                SoundRegistry.EMD_16_567C_IDLE.get(), 0.7F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD SD9");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_SD9.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.7, 0.2);
    }
}
