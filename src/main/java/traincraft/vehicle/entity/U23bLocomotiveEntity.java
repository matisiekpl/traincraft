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

public class U23bLocomotiveEntity extends DieselLocomotiveEntity {

    public U23bLocomotiveEntity(EntityType<? extends U23bLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_U23B;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_S3L.get(), 2.5F,
                SoundRegistry.GE_7FDL_12_NOTCH8.get(), 0.65F, 40,
                SoundRegistry.GE_7FDL_12_IDLE.get(), 0.65F, 50,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GE U23B");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_U23B.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.3, 0.3);
    }
}
