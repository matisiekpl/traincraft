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

public class BrMk2fDbsoLocomotiveEntity extends ElectricLocomotiveEntity {

    public BrMk2fDbsoLocomotiveEntity(EntityType<? extends BrMk2fDbsoLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_BR_MK2F_DBSO;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.MG_HORN.get(), 1.0F,
                SoundRegistry.CLASS868790THRASH.get(), 0.8F, 10,
                SoundRegistry.CLASS90IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR Mk2f DBSO");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_BR_MK2F_DBSO.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.7F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 4.05, -0.1);
    }
}
