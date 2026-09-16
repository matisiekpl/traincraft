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

public class Gp38dash2LocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 3, new double[][] {{0.75, 1.4, 0.0}, {1.55, 1.4, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public Gp38dash2LocomotiveEntity(EntityType<? extends Gp38dash2LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_GP38DASH2;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_K3LA_3.get(), 0.8F,
                SoundRegistry.EMD_16_645E_NOTCH8.get(), 0.65F, 35,
                SoundRegistry.EMD_16_645E_IDLE.get(), 0.65F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD GP38-2");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_GP38DASH2.get();
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
        return driverSeat(passenger, 2.95, 0.15);
    }
}
