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

public class Sw1500LocomotiveEntity extends DieselLocomotiveEntity {

    public Sw1500LocomotiveEntity(EntityType<? extends Sw1500LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_SW1500;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_P3.get(), 0.65F,
                SoundRegistry.EMD_12_645E_NOTCH8.get(), 0.65F, 10,
                SoundRegistry.EMD_12_645E_IDLE.get(), 0.8F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD SW1500");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_SW1500.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.1, 0.3);
    }
}
