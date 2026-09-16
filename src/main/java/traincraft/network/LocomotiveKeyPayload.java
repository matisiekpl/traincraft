package traincraft.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.vehicle.entity.KeyControlled;

public record LocomotiveKeyPayload(int entityId, int key, boolean held)
        implements CustomPacketPayload {

    public static final Type<LocomotiveKeyPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "loco_key"));

    public static final StreamCodec<RegistryFriendlyByteBuf, LocomotiveKeyPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    LocomotiveKeyPayload::entityId,
                    ByteBufCodecs.VAR_INT,
                    LocomotiveKeyPayload::key,
                    ByteBufCodecs.BOOL,
                    LocomotiveKeyPayload::held,
                    LocomotiveKeyPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(LocomotiveKeyPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (!(player.level().getEntity(payload.entityId()) instanceof KeyControlled loco)) {
            return;
        }
        // Only the driver, and only a driver allowed to drive: a locked locomotive stays put even
        // for someone sitting in it.
        if (player.getVehicle() != loco || !loco.mayControl(player)) {
            return;
        }
        loco.setKeyHeld(payload.key(), payload.held());
    }
}
