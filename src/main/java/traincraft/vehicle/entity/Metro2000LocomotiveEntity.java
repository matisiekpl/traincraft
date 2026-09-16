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

public class Metro2000LocomotiveEntity extends ElectricLocomotiveEntity {

    public Metro2000LocomotiveEntity(EntityType<? extends Metro2000LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_METRO2000;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.SOUND_4300_HORN.get(), 1.0F,
                SoundRegistry.METRO2000_RUNNING.get(), 1.5F, 70,
                SoundRegistry.CHME3_IDLE.get(), 1.0F, 20,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Metro Madrid 2000 Motor");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_METRO2000.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.65F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.2, -0.2);
    }
}
