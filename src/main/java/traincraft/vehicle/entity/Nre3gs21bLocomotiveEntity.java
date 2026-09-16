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

public class Nre3gs21bLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 3, new double[][] {{0.15, 1.3, 0.0}, {1.05, 1.3, 0.0}, {2.0, 1.3, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public Nre3gs21bLocomotiveEntity(EntityType<? extends Nre3gs21bLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_NRE3GS21B;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_K3HA.get(), 2.5F,
                SoundRegistry.QSK19C_NOTCH8.get(), 0.65F, 40,
                SoundRegistry.QSK19C_IDLE.get(), 0.65F, 50,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("NRE 3GS21B Genset");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_NRE3GS21B.get();
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
        return driverSeat(passenger, 2.95, 0.35);
    }
}
