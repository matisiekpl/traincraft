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

public class KrauttLocomotiveEntity extends DieselLocomotiveEntity {

    public KrauttLocomotiveEntity(EntityType<? extends KrauttLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_KRAUTT;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.NATHAN_P3_2.get(), 2.5F,
                SoundRegistry.MAYBACH_MD870_16_NOTCH8.get(), 0.7F, 40,
                SoundRegistry.MAYBACH_MD870_16_IDLE.get(), 0.7F, 60,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Krauss-Maffei ML-4000");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_KRAUTT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.3F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.4, 0.2);
    }
}
