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

public class BeepLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 4, new double[][] {{1.45, 1.25, 0.0}, {1.65, 1.25, 0.0}, {1.05, 1.25, 0.0}, {1.25, 1.25, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public BeepLocomotiveEntity(EntityType<? extends BeepLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_BEEP;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_S3LR.get(), 2.5F,
                SoundRegistry.EMD_16_567BC_IDLE.get(), 0.7F, 40,
                SoundRegistry.EMD_16_567BC_NOTCH8.get(), 0.7F, 60,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ATSF Beep");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_BEEP.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.05F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.4, 0.15);
    }
}
