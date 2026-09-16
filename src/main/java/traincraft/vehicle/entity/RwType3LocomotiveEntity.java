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

public class RwType3LocomotiveEntity extends SteamLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.LARGE_SMOKE, 3, new double[][] {{2.4, 1.55, 0.0}},
                    ParticleTypes.POOF, 2, new double[][] {{2.9, 0.05, 0.65}});

    public RwType3LocomotiveEntity(EntityType<? extends RwType3LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_RW_TYPE_3;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.RW_TYPE_3.get(), 1.2F,
                SoundRegistry.STEAM_RUN.get(), 0.4F, 20,
                SoundRegistry.STEAM_RUN.get(), 0.4F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("RW Type 3");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_RW_TYPE_3.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.83F;
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
