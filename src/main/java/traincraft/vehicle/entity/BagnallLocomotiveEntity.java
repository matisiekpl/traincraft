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

public class BagnallLocomotiveEntity extends DieselLocomotiveEntity {

    public BagnallLocomotiveEntity(EntityType<? extends BagnallLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_BAGNALL;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.V60_HORN.get(), 1.0F,
                SoundRegistry.CHME3_IDLE.get(), 0.8F, 10,
                SoundRegistry.CHME3_IDLE.get(), 0.5F, 20,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Bagnall Shunter");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_BAGNALL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.8F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 0.0, 0.45);
    }
}
