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

public class Dh643LocomotiveEntity extends DieselLocomotiveEntity {

    public Dh643LocomotiveEntity(EntityType<? extends Dh643LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_DH643;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_P3_2.get(), 2.5F,
                SoundRegistry.ALCO_12_251C_NOTCH8.get(), 0.5F, 20,
                SoundRegistry.ALCO_12_251C_IDLE.get(), 0.5F, 20,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ALCo DH643");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_DH643.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 4.5, 0.2);
    }
}
