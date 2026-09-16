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

public record EngineNumberPayload(int entityId, String number) implements CustomPacketPayload {
    public static final Type<EngineNumberPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "engine_number"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EngineNumberPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, EngineNumberPayload::entityId,
                    ByteBufCodecs.STRING_UTF8, EngineNumberPayload::number, EngineNumberPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EngineNumberPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (player.level().getEntity(payload.entityId()) instanceof RollingStockEntity stock
                && stock.supportsEngineNumber()
                && stock.mayControl(player)
                && player.distanceToSqr(stock) <= 64.0) {
            stock.setEngineNumber(payload.number());
        }
    }
}
