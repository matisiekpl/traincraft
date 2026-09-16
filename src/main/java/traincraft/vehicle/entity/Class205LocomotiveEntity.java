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

public class Class205LocomotiveEntity extends DieselLocomotiveEntity {

    public Class205LocomotiveEntity(EntityType<? extends Class205LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_CLASS205;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.EU07_HORN.get(), 1.0F,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                SoundRegistry.CHME3_IDLE.get(), 0.65F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class 205 Engine");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_CLASS205.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.75F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.85, -0.1);
    }
}
