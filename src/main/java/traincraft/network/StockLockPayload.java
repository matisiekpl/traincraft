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

/** The lock button in a cart's menu. Community Edition's {@code PacketSetTrainLockedToClient}. */
public record StockLockPayload(int entityId, boolean locked) implements CustomPacketPayload {

    public static final Type<StockLockPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "stock_lock"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StockLockPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    StockLockPayload::entityId,
                    ByteBufCodecs.BOOL,
                    StockLockPayload::locked,
                    StockLockPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(StockLockPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (!(player.level().getEntity(payload.entityId()) instanceof RollingStockEntity stock)) {
            return;
        }
        if (!stock.access().isOwner(player.getGameProfile().name())
                || player.distanceToSqr(stock) > 64.0) {
            return;
        }
        stock.setLocked(payload.locked());
    }
}
