package traincraft.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.vehicle.entity.RollingStockEntity;

public record LiveryPayload(int entityId, String colour) implements CustomPacketPayload {

    public static final Type<LiveryPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "livery"));

    public static final StreamCodec<RegistryFriendlyByteBuf, LiveryPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, LiveryPayload::entityId, ByteBufCodecs.STRING_UTF8, LiveryPayload::colour, LiveryPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(LiveryPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (!(player.level().getEntity(payload.entityId()) instanceof RollingStockEntity stock)) {
            return;
        }
        if (!stock.mayControl(player) || player.distanceToSqr(stock) > 64.0 || !stock.spec().colours().contains(payload.colour())) {
            return;
        }
        stock.setColour(payload.colour());
    }
}
