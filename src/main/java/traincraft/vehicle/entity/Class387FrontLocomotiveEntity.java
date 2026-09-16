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

public class Class387FrontLocomotiveEntity extends ElectricLocomotiveEntity {

    public Class387FrontLocomotiveEntity(EntityType<? extends Class387FrontLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_CLASS387_FRONT;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.BRITISH_TWO_TONE.get(), 1.0F,
                SoundRegistry.VL10_IDLE.get(), 0.8F, 10,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class 387 Front");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_CLASS387_FRONT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.85F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 4.25, -0.24);
    }
}
