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

public class Rsd15LocomotiveEntity extends DieselLocomotiveEntity {

    public Rsd15LocomotiveEntity(EntityType<? extends Rsd15LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_RSD15;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_RS5T.get(), 1.0F,
                SoundRegistry.ALCO_16_251C_NOTCH8.get(), 0.5F, 40,
                SoundRegistry.ALCO_16_251C_IDLE.get(), 0.65F, 40,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ALCo RSD-15");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_RSD15.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.4F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.6, 0.2);
    }
}
