package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.bootstrap.SoundRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;
import traincraft.vehicle.sound.LocomotiveSounds;

public class AliceLocomotiveEntity extends SteamLocomotiveEntity {

    public AliceLocomotiveEntity(EntityType<? extends AliceLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.LOCO_STEAM_ALICE;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.GERMAN_STEAM_HORN.get(),
                0.8F,
                SoundRegistry.STEAM_RUN.get(),
                0.4F,
                20,
                SoundRegistry.STEAM_RUN.get(),
                0.4F,
                20,
                true);
    }

    @Override
    public Component getDisplayName() {

        return Component.literal("0-4-0 Alice");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_ALICE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.9F;
    }

    @Override
    protected net.minecraft.world.phys.Vec3 getPassengerAttachmentPoint(
            net.minecraft.world.entity.Entity passenger,
            net.minecraft.world.entity.EntityDimensions dimensions,
            float scale) {
        return new net.minecraft.world.phys.Vec3(0.0, driverFeetOffset(passenger, 0.65), 0.0);
    }
}
