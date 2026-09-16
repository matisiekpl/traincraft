package traincraft.adminbook;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;

/** CE's {@code PacketAdminBook}: a comma-separated listing, or a {@code <}-prefixed entry, for the admin book screen. */
public record AdminBookPayload(String data) implements CustomPacketPayload {

    public static final Type<AdminBookPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "admin_book"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AdminBookPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, AdminBookPayload::data, AdminBookPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AdminBookPayload payload, IPayloadContext context) {
        traincraft.client.ClientScreens.openAdminBook(payload.data());
    }
}
