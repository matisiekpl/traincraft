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

public class BapF7aLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 3, new double[][] {{0.9375, 1.3, 0.0}, {0.25, 1.3, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public BapF7aLocomotiveEntity(EntityType<? extends BapF7aLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_BAP_F7A;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_A200_2.get(), 1.0F,
                SoundRegistry.EMD_16_567B_NOTCH8.get(), 0.45F, 15,
                SoundRegistry.EMD_16_567B_IDLE.get(), 0.75F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD F7A");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_BAP_F7A.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.82F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.2, 0.19);
    }
}
