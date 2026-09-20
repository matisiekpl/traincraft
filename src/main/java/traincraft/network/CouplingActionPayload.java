package traincraft.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.vehicle.coupling.VehicleEnd;
import traincraft.vehicle.entity.RollingStockEntity;

/** One click on a piece of stock's coupling controls. */
public record CouplingActionPayload(int entityId, int action) implements CustomPacketPayload {

    public static final int ACTION_ARM = 0;
    public static final int ACTION_RELEASE_FRONT = 1;
    public static final int ACTION_RELEASE_BACK = 2;

    /** Squared blocks. A player who has walked away should not still be working the couplings. */
    private static final double REACH_SQUARED = 64.0;

    public static final Type<CouplingActionPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "coupling_action"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CouplingActionPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    CouplingActionPayload::entityId,
                    ByteBufCodecs.VAR_INT,
                    CouplingActionPayload::action,
                    CouplingActionPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CouplingActionPayload payload, IPayloadContext context) {
        Player player = context.player();
        if (!(player.level().getEntity(payload.entityId()) instanceof RollingStockEntity stock)) {
            return;
        }
        if (player.distanceToSqr(stock) > REACH_SQUARED || !stock.mayControl(player)) {
            return;
        }
        switch (payload.action()) {
            case ACTION_ARM -> stock.toggleArming();
            case ACTION_RELEASE_FRONT -> stock.decouple(VehicleEnd.FRONT);
            case ACTION_RELEASE_BACK -> stock.decouple(VehicleEnd.BACK);
            default -> {}
        }
    }
}
