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

public class Emde8aLocomotiveEntity extends DieselLocomotiveEntity {

    public Emde8aLocomotiveEntity(EntityType<? extends Emde8aLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_EMDE8A;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_A200_2.get(), 1.0F,
                SoundRegistry.EMD_12_567B_NOTCH8.get(), 0.45F, 15,
                SoundRegistry.EMD_16_567B_IDLE.get(), 0.75F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD E8A");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_EMDE8A.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.6, 0.19);
    }
}
