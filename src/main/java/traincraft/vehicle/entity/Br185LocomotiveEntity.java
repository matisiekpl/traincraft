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

public class Br185LocomotiveEntity extends ElectricLocomotiveEntity {

    public Br185LocomotiveEntity(EntityType<? extends Br185LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_BR185;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(SoundRegistry.MG_HORN.get(), 1.0F,
                SoundRegistry.VL10_IDLE.get(), 0.8F, 10,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50, false);
    }

    @Override
    public Component getDisplayName() {

        return Component.literal("BR 185");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_BR185.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.7F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        double distance = 4.5;
        double pitchRadians = Math.toRadians(getXRot());
        double heading = Math.toRadians(getYRot() + 90.0F);
        return new Vec3(
                Math.cos(heading) * distance,
                driverFeetOffset(passenger, 0.1) + Math.tan(pitchRadians) * distance,
                Math.sin(heading) * distance);
    }
}
