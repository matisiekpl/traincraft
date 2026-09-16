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

public class Ef1bLocomotiveEntity extends ElectricLocomotiveEntity {

    public Ef1bLocomotiveEntity(EntityType<? extends Ef1bLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_EF1B;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.SILENCE.get(), 0.0F,
                SoundRegistry.MILW_NOTCH8.get(), 0.65F, 40,
                SoundRegistry.MILW_IDLE.get(), 0.65F, 50,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GE MILW EF-1B");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_EF1B.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1875F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 2.8775, 0.25);
    }
}
