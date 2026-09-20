package traincraft.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.vehicle.entity.LocomotiveEntity;

public record LocomotiveActionPayload(int entityId, int action) implements CustomPacketPayload {

    public static final int ACTION_PARKING_BRAKE = 0;
    public static final int ACTION_LOCK = 1;
    public static final int ACTION_ENGINE = 2;

    /** Not a button on the screen: the key that opens the screen in the first place. */
    public static final int ACTION_OPEN_MENU = 3;

    public static final int ACTION_HORN = 4;

    public static final int ACTION_PULL_MODE = 5;

    private static final int BRAKE_SPEED_LIMIT = 10;
    private static final int ENGINE_STOP_SPEED_LIMIT = 1;

    /** Squared blocks. A player who has walked away should not still be working the controls. */
    private static final double REACH_SQUARED = 64.0;

    public static final Type<LocomotiveActionPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "loco_action"));

    public static final StreamCodec<RegistryFriendlyByteBuf, LocomotiveActionPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    LocomotiveActionPayload::entityId,
                    ByteBufCodecs.VAR_INT,
                    LocomotiveActionPayload::action,
                    LocomotiveActionPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(LocomotiveActionPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (!(player.level().getEntity(payload.entityId()) instanceof LocomotiveEntity loco)) {
            return;
        }
        if (player.distanceToSqr(loco) > REACH_SQUARED) {
            return;
        }

        switch (payload.action()) {
            case ACTION_PARKING_BRAKE -> {
                if (loco.mayControl(player) && loco.getSpeedKmH() < BRAKE_SPEED_LIMIT) {
                    loco.setParkingBrake(!loco.isParkingBrakeOn());
                }
            }
            case ACTION_LOCK -> {
                if (loco.access().isOwner(player.getGameProfile().name())) {
                    loco.setLocked(!loco.isLocked());
                } else {
                    player.sendSystemMessage(Component.literal("You are not the owner"));
                }
            }
            case ACTION_ENGINE -> {
                if (!loco.mayControl(player)) {
                    return;
                }
                if (loco.isEngineOn()) {
                    if (loco.getSpeedKmH() <= ENGINE_STOP_SPEED_LIMIT) {
                        loco.setEngineOn(false);
                        loco.setParkingBrake(true);
                    } else {
                        player.sendSystemMessage(Component.literal("Stop before turning it off"));
                    }
                } else {
                    loco.setEngineOn(true);
                }
            }
            case ACTION_OPEN_MENU -> {
                if (player.getVehicle() != loco) {
                    return;
                }
                // Upstream gates the key that opens this without a word: a stranger aboard a
                // locked locomotive presses it and nothing happens.
                if (!loco.mayControl(player)) {
                    return;
                }
                loco.openMenuFor(player);
            }
            case ACTION_PULL_MODE -> {
                if (!loco.mayControl(player)) {
                    return;
                }
                // Upstream's sneak-click with a stake, which said the same two things in chat.
                boolean pulled = !loco.canBePulled();
                loco.setCanBePulled(pulled);
                loco.setCanBeAdjusted(pulled);
                player.sendSystemMessage(
                        Component.empty()
                                .append(loco.getTrainName())
                                .append(pulled ? " can be pulled" : " can pull"));
            }
            case ACTION_HORN -> {
                if (player.getVehicle() == loco) {
                    loco.soundHorn();
                }
            }
            default -> {}
        }
    }
}
