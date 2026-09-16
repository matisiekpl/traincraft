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

public class Br80LocomotiveEntity extends SteamLocomotiveEntity {

    public Br80LocomotiveEntity(EntityType<? extends Br80LocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_BR80;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.GERMAN_STEAM_HORN.get(),
                0.8F,
                SoundRegistry.STEAM_RUN.get(),
                0.2F,
                20,
                SoundRegistry.STEAM_RUN.get(),
                0.2F,
                20,
                true);
    }

    @Override
    public Component getDisplayName() {

        return Component.literal("BR80");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_BR80.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        double distance = -0.1;
        double pitchRadians = Math.toRadians(getXRot());
        double heading = Math.toRadians(getYRot() + 90.0F);
        return new Vec3(
                Math.cos(heading) * distance,
                driverFeetOffset(passenger, 0.35) + Math.tan(pitchRadians) * distance,
                Math.sin(heading) * distance);
    }
}
