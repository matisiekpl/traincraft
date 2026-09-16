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

public class KofIiiMLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 4, new double[][] {{0.5, 1.0, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public KofIiiMLocomotiveEntity(EntityType<? extends KofIiiMLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_KOF_III_M;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.CHME3_HORN.get(), 0.8F,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Kof III Modernized");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_KOF_III_M.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.3, -0.1);
    }
}
