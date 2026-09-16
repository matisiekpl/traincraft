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

public class SentinelY3LocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 2, new double[][] {{-0.5, 1.5, 0.0}},
                    ParticleTypes.POOF, 2, new double[][] {});

    public SentinelY3LocomotiveEntity(EntityType<? extends SentinelY3LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_SENTINEL_Y3;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.ADLER_WHISTLE.get(), 1.0F,
                SoundRegistry.STEAM_RUN.get(), 0.75F, 10,
                SoundRegistry.STEAM_RUN.get(), 0.25F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Sentinel Y3");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_SENTINEL_Y3.get();
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
        return driverSeat(passenger, 0.05, 0.2);
    }
}
