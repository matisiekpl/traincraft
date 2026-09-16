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

public class AlcoPa1LocomotiveEntity extends DieselLocomotiveEntity {

    public AlcoPa1LocomotiveEntity(EntityType<? extends AlcoPa1LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_ALCO_PA1;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_A200_2.get(), 1.0F,
                SoundRegistry.ALCO_16_244_NOTCH8.get(), 0.25F, 40,
                SoundRegistry.ALCO_16_244_IDLE.get(), 0.4F, 40,
                true);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Alco PA-1");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_ALCO_PA1.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.45F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.3, 0.14);
    }
}
