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

public class C415lLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 4, new double[][] {{1.85, 1.55, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public C415lLocomotiveEntity(EntityType<? extends C415lLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_C415L;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_S3LR.get(), 0.65F,
                SoundRegistry.ALCO_8_251F_NOTCH8.get(), 0.65F, 10,
                SoundRegistry.ALCO_8_251F_IDLE.get(), 0.8F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ALCo C415 Low Clearance");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_C415L.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.2F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 0.95, 0.35);
    }
}
