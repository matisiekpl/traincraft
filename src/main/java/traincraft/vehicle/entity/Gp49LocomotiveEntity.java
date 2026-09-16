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

public class Gp49LocomotiveEntity extends DieselLocomotiveEntity {

    public Gp49LocomotiveEntity(EntityType<? extends Gp49LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_GP49;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_P3_3.get(), 2.5F,
                SoundRegistry.EMD_12_645E3_NOTCH8.get(), 1.0F, 8,
                SoundRegistry.EMD_12_645E3_IDLE.get(), 1.0F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD GP49");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_GP49.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.2F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.95, 0.15);
    }
}
