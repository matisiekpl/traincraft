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

public class C415sLocomotiveEntity extends DieselLocomotiveEntity {

    public C415sLocomotiveEntity(EntityType<? extends C415sLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_C415S;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_S3LR.get(), 0.65F,
                SoundRegistry.ALCO_8_251F_NOTCH8.get(), 0.65F, 10,
                SoundRegistry.ALCO_8_251F_IDLE.get(), 0.8F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ALCo C415");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_C415S.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.2F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 0.95, 0.4);
    }
}
