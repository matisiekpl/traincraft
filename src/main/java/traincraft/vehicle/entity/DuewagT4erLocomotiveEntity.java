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

public class DuewagT4erLocomotiveEntity extends ElectricLocomotiveEntity {

    public DuewagT4erLocomotiveEntity(EntityType<? extends DuewagT4erLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_ELECTRIC_DUEWAG_T4ER;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.TRAM_BELL.get(), 1.0F,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50,
                SoundRegistry.VL10_IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("DuewagT4ER");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_ELECTRIC_DUEWAG_T4ER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.3F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.4, -0.1);
    }
}
