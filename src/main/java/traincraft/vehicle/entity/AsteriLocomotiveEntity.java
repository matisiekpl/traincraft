package traincraft.vehicle.entity;

import java.util.List;

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
import traincraft.vehicle.sound.LocomotiveSounds;

/**
 * Community Edition's {@code EntityLocoSteamasteri}: an easter egg with a model, a texture and a
 * song, but no {@code EnumTrains} row, no item and no recipe. Only {@code /summon} reaches it, and
 * it drops the Forney when broken because it has nothing of its own to drop.
 */
public class AsteriLocomotiveEntity extends SteamLocomotiveEntity {

    public static final VehicleDefinition SPEC = new VehicleDefinition(
            "asteri", "RickRoll", "steam", 600, 70, 0, 60, 160, 130, 0.44, 0.968, 8000, 0, 17, -1.8,
            List.of(), "");

    public AsteriLocomotiveEntity(EntityType<? extends AsteriLocomotiveEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return SPEC;
    }

    @Override
    public LocomotiveSounds sounds() {
        return new LocomotiveSounds(
                SoundRegistry.ASTERI.get(), 1.0F,
                SoundRegistry.ASTERI.get(), 0.2F, 212,
                SoundRegistry.MG_IDLE.get(), 0.6F, 50,
                false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("RickRoll");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.LOCO_STEAM_FORNEY.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.6F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return driverSeat(passenger, -0.1, 0.15);
    }
}
