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

public class DelticLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 1, new double[][] {{2.3, 1.6, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public DelticLocomotiveEntity(EntityType<? extends DelticLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_DELTIC;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.BRITISH_TWO_TONE.get(), 0.8F,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Deltic");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_DELTIC.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 4.5, 0.55);
    }
}
