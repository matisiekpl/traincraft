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

public class IlmaLocomotiveEntity extends ElectricLocomotiveEntity {

    public IlmaLocomotiveEntity(EntityType<? extends IlmaLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_ILMA;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.SUBWAY_HORN.get(), 0.8F,
                SoundRegistry.MILW_NOTCH8.get(), 0.6F, 50,
                SoundRegistry.MILW_IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("I.L.M.A.");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_ILMA.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.2F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 1.0, 0.0);
    }
}
