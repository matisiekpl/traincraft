package traincraft.item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import traincraft.Traincraft;
import traincraft.adminbook.AdminBookPayload;
import traincraft.adminbook.StockLog;

public class AdminBookItem extends Item {

    public AdminBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> lines, TooltipFlag flag) {
        lines.accept(Component.literal("This book is for Operators ONLY, and allows the following:"));
        lines.accept(Component.literal("- drop trains/rollingstock and their inventory lost during a crash"));
        lines.accept(Component.literal("- Lock or unlock trains/rollingstock"));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!(player instanceof ServerPlayer operator) || !operator.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)
                || !Files.isDirectory(StockLog.directory())) {
            return InteractionResult.SUCCESS;
        }
        StringBuilder data = new StringBuilder();
        try (Stream<Path> owners = Files.list(StockLog.directory())) {
            for (Path owner : owners.filter(Files::isDirectory).sorted(Comparator.comparing(path -> path.getFileName().toString().toLowerCase())).toList()) {
                try (Stream<Path> entries = Files.list(owner)) {
                    if (entries.findAny().isPresent()) {
                        data.append(owner.getFileName()).append(",");
                    }
                }
            }
        } catch (IOException exception) {
            Traincraft.LOGGER.warn("Admin book cannot list {}", StockLog.directory(), exception);
        }
        PacketDistributor.sendToPlayer(operator, new AdminBookPayload(data.toString()));
        return InteractionResult.SUCCESS;
    }
}
