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

public class E10LocomotiveEntity extends ElectricLocomotiveEntity {

    public E10LocomotiveEntity(EntityType<? extends E10LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_E10;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.MG_HORN.get(), 1.0F,
                SoundRegistry.MG_RUN.get(), 0.6F, 8,
                SoundRegistry.MG_IDLE.get(), 0.4F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("E10 (DB)");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_E10.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.0F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        double distance = 3.75;
        double pitchRadians = Math.toRadians(getXRot());
        double heading = Math.toRadians(getYRot() + 90.0F);
        return new Vec3(
                Math.cos(heading) * distance,
                driverFeetOffset(passenger, 0.1) + Math.tan(pitchRadians) * distance,
                Math.sin(heading) * distance);
    }
}
