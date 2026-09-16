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

public class H1666LocomotiveEntity extends DieselLocomotiveEntity {

    public H1666LocomotiveEntity(EntityType<? extends H1666LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_H16_66;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_A200_2.get(), 1.0F,
                SoundRegistry.FM_38D_6_NOTCH8.get(), 0.65F, 40,
                SoundRegistry.FM_38D_6_NOTCH8.get(), 0.45F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("FM H16-66");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_H16_66.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.2F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 3.15, 0.15);
    }
}
