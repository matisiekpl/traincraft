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

public class U36cLocomotiveEntity extends DieselLocomotiveEntity {

    public U36cLocomotiveEntity(EntityType<? extends U36cLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_U36C;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_S3LR.get(), 2.5F,
                SoundRegistry.GE_FDL16_NOTCH8.get(), 0.5F, 10,
                SoundRegistry.GE_FDL16_IDLE.get(), 0.5F, 3,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GE U36C");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_U36C.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.375F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.75, 0.3);
    }
}
