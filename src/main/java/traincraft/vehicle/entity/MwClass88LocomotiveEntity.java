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

public class MwClass88LocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 2, new double[][] {{2.5, 1.5, 0.0}},
                    ParticleTypes.POOF, 2, new double[][] {{2.5, 0.05, 0.65}});

    public MwClass88LocomotiveEntity(EntityType<? extends MwClass88LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_MW_CLASS_88;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.AMERICAN_STEAM_HORN.get(), 1.5F,
                SoundRegistry.STEAM_RUN.get(), 0.6F, 25,
                SoundRegistry.ADLER_RUN.get(), 0.5F, 17,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("MW Class88 BTE");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_MW_CLASS_88.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.6F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 0.35, 0.2);
    }
}
