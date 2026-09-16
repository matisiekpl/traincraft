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

public class D51ShortLocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 3, new double[][] {{4.85, 1.85, 0.0}},
                    ParticleTypes.POOF, 2, new double[][] {{4.6, -0.2, 0.8}});

    public D51ShortLocomotiveEntity(EntityType<? extends D51ShortLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_D51_SHORT;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.CLASS62_HORN.get(), 0.8F,
                SoundRegistry.STEAM_RUN.get(), 0.2F, 20,
                SoundRegistry.STEAM_RUN.get(), 0.2F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("D51 [JNR]");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_D51_SHORT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.5F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.3), 0.0);
    }
}
