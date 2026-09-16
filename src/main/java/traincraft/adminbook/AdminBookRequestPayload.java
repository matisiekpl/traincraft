package traincraft.adminbook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;

/** CE's {@code PacketAdminBookClient}: browse a folder, open an entry, or {@code 0:} clone and {@code 1:} delete one. */
public record AdminBookRequestPayload(String id) implements CustomPacketPayload {

    public static final Type<AdminBookRequestPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "admin_book_request"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AdminBookRequestPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, AdminBookRequestPayload::id, AdminBookRequestPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AdminBookRequestPayload payload, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player) || !player.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)) {
            return;
        }
        String id = payload.id();
        int event = id.length() > 2 && id.startsWith("0:") ? 1 : id.length() > 2 && id.startsWith("1:") ? 2 : 0;
        if (event != 0) {
            id = id.substring(2);
        }
        Path file = StockLog.directory().resolve(id).normalize();
        if (!file.startsWith(StockLog.directory()) || !Files.exists(file)) {
            return;
        }
        try {
            StringBuilder data = new StringBuilder();
            if (Files.isDirectory(file)) {
                if (!id.isEmpty()) {
                    data.append(",");
                }
                try (Stream<Path> entries = Files.list(file)) {
                    for (Path entry : entries.sorted().toList()) {
                        data.append(id).append(id.isEmpty() ? "" : "/").append(entry.getFileName()).append(",");
                    }
                }
            } else if (event == 1) {
                for (ItemStack stack : StockLog.items(Files.readString(file, StandardCharsets.UTF_8))) {
                    ItemEntity drop = new ItemEntity(player.level(), player.getX(), player.getY() + 3.0, player.getZ(), stack);
                    drop.setPickUpDelay(120);
                    player.level().addFreshEntity(drop);
                }
            } else if (event == 2) {
                Files.delete(file);
            } else {
                String document = Files.readString(file, StandardCharsets.UTF_8);
                String uuid = StockLog.between(document, "<uuid>", "</uuid>");
                data.append("<").append(id).append(",").append(id, 0, id.indexOf("/") + 1).append(",");
                data.append("Delegate:,").append(StockLog.between(document, "<delegate>", "</delegate>"));
                data.append(",UUID:,").append(uuid, 0, 19).append(",").append(uuid.substring(19));
                data.append(",Last Known Position:,X:").append(StockLog.between(document, "<pos_x>", "</pos_x>"));
                data.append("- Y:").append(StockLog.between(document, "<pos_y>", "</pos_y>"));
                data.append("- Z:").append(StockLog.between(document, "<pos_z>", "</pos_z>"));
                if (document.contains("<inventory>")) {
                    data.append(",").append(document, document.indexOf("<inventory>"), document.indexOf("</inventory>") + "</inventory>".length());
                }
            }
            if (data.length() >= 5) {
                PacketDistributor.sendToPlayer(player, new AdminBookPayload(data.toString()));
            }
        } catch (IOException exception) {
            Traincraft.LOGGER.warn("Admin book cannot read {}", file, exception);
        }
    }
}
