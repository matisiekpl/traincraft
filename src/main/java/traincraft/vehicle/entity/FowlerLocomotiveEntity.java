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

public class FowlerLocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 20, new double[][] {{3.2, 1.6, 0.0}},
                    ParticleTypes.POOF, 2, new double[][] {{2.0, -0.4, 0.0}});

    public FowlerLocomotiveEntity(EntityType<? extends FowlerLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_FOWLER;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.STEAM_HORN.get(), 0.6F,
                SoundRegistry.STEAM_RUN.get(), 0.4F, 20,
                SoundRegistry.STEAM_RUN.get(), 0.4F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Fowler 4F");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_FOWLER.get();
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
        return driverSeat(passenger, -0.25, 0.25);
    }
}
