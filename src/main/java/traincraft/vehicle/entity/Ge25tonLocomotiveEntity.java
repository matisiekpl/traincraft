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

public class Ge25tonLocomotiveEntity extends DieselLocomotiveEntity {

    private static final Exhaust EXHAUST =
            new Exhaust(
                    ParticleTypes.SMOKE, 3, new double[][] {{0.6, 1.1, 0.0}},
                    ParticleTypes.POOF, 0, new double[][] {});

    public Ge25tonLocomotiveEntity(EntityType<? extends Ge25tonLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_DIESEL_GE25TON;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.LESLIE_A125.get(), 1.0F,
                SoundRegistry.CAT_8_D17000_NOTCH8.get(), 0.65F, 40,
                SoundRegistry.CAT_8_D17000_IDLE.get(), 0.65F, 40,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GE 25 Ton Switcher");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_DIESEL_GE25TON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.6F;
    }

    @Override
    public Exhaust exhaust() {
        return EXHAUST;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, 0.0, -0.2);
    }
}
