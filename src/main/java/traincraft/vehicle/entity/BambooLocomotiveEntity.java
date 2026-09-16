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

public class BambooLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 2, new double[][] {{-0.05, -0.05, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public BambooLocomotiveEntity(EntityType<? extends BambooLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_BAMBOO;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.GP_HORN.get(), 0.0F,
                SoundRegistry.CHME3_IDLE.get(), 0.2F, 40,
                SoundRegistry.CHME3_IDLE.get(), 0.1F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Bamboo Flatcar With Engine");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_BAMBOO.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.3F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.3, 0.0);
    }
}
