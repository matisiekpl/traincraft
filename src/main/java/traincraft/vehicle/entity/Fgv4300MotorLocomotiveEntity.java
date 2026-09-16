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

public class Fgv4300MotorLocomotiveEntity extends ElectricLocomotiveEntity {

    public Fgv4300MotorLocomotiveEntity(EntityType<? extends Fgv4300MotorLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_FGV4300_MOTOR;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.SOUND_4300_HORN.get(), 1.0F,
                SoundRegistry.VL10_IDLE.get(), 0.65F, 10,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("FGV 4300 Motor");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_FGV4300_MOTOR.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.75F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.0, -0.1);
    }
}
