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

public class Cf7LocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 6, new double[][] {{0.6, 1.4, 0.0}, {1.1, 1.4, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public Cf7LocomotiveEntity(EntityType<? extends Cf7LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_CF7;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_K3LA.get(), 0.65F,
                SoundRegistry.EMD_16_567BC_NOTCH8.get(), 0.65F, 10,
                SoundRegistry.EMD_16_567BC_IDLE.get(), 0.8F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("CF7");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_CF7.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.0F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.7, 0.2);
    }
}
