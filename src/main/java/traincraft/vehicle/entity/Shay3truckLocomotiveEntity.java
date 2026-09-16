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

public class Shay3truckLocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 4, new double[][] {{2.15, 1.3, -0.17}},
                    ParticleTypes.POOF, 2, new double[][] {{0.9, -0.4, 0.6}});

    public Shay3truckLocomotiveEntity(EntityType<? extends Shay3truckLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_SHAY_3TRUCK;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LUKENHIMER_3CHIME.get(), 1.0F,
                SoundRegistry.STEAM_RUN.get(), 0.2F, 20,
                SoundRegistry.STEAM_RUN.get(), 0.2F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class 3-PC-13 3-Truck Shay");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_SHAY_3TRUCK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.7F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.0), 0.0);
    }
}
