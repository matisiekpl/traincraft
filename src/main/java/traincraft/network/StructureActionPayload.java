package traincraft.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.structure.StructureBlockEntity;

public record StructureActionPayload(BlockPos pos, int value) implements CustomPacketPayload {

    public static final Type<StructureActionPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "structure_action"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StructureActionPayload> CODEC =
            StreamCodec.composite(BlockPos.STREAM_CODEC, StructureActionPayload::pos, ByteBufCodecs.VAR_INT, StructureActionPayload::value, StructureActionPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void openLantern(ServerPlayer player, BlockPos pos, int colour) {
        PacketDistributor.sendToPlayer(player, new StructureActionPayload(pos, colour));
    }

    public static void handleServer(StructureActionPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (player.distanceToSqr(net.minecraft.world.phys.Vec3.atCenterOf(payload.pos())) > 64.0) {
            return;
        }
        if (player.level().getBlockEntity(payload.pos()) instanceof StructureBlockEntity lantern) {
            lantern.setColour(payload.value() & 0xFFFFFF);
        }
    }

    public static void handleClient(StructureActionPayload payload, IPayloadContext context) {
        traincraft.client.ClientScreens.openLantern(payload.pos(), payload.value());
    }
}
