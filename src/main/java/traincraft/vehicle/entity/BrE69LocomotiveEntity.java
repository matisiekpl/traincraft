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

public class BrE69LocomotiveEntity extends ElectricLocomotiveEntity {

    public BrE69LocomotiveEntity(EntityType<? extends BrE69LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_BR_E69;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.EU07_HORN.get(), 0.8F,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR E69");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_BR_E69.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.55F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.45), 0.0);
    }
}
