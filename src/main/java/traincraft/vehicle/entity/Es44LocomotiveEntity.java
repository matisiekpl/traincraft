package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.ItemRegistry;
import traincraft.bootstrap.SoundRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;
import traincraft.vehicle.sound.LocomotiveSounds;

public class Es44LocomotiveEntity extends DieselLocomotiveEntity {

    public Es44LocomotiveEntity(EntityType<? extends Es44LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_ES44;
    }

    @Override
    public boolean supportsEngineNumber() {
        return true;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_K5HLL.get(), 1.0F,
                SoundRegistry.GE_GEVO_12_NOTCH8.get(), 0.65F, 20,
                SoundRegistry.GE_GEVO_12_IDLE.get(), 0.75F, 50,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GE ES44");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_ES44.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.3F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        double distance = 4.1;
        double pitchRadians = Math.toRadians(getXRot());
        double heading = Math.toRadians(getYRot() + 90.0F);
        return new Vec3(
                Math.cos(heading) * distance,
                driverFeetOffset(passenger, 0.4) + Math.tan(pitchRadians) * distance,
                Math.sin(heading) * distance);
    }
}
