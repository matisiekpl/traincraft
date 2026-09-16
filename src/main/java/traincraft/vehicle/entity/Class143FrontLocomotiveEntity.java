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

public class Class143FrontLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 1, new double[][] {{-0.8, 1.25, 0.4}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public Class143FrontLocomotiveEntity(EntityType<? extends Class143FrontLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_CLASS143_FRONT;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.BRITISH_TWO_TONE.get(), 1.0F,
                SoundRegistry.FM_38D_6_NOTCH8.get(), 0.6F, 10,
                SoundRegistry.CHME3_IDLE.get(), 0.5F, 20,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class 143");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_CLASS143_FRONT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.97F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.25, 0.0);
    }
}
