package traincraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlockEntity;

public class TrackDebuggerItem extends Item {

    public TrackDebuggerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        if (player == null || context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        var entity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (entity instanceof TrackBlockEntity rail) {
            player.sendSystemMessage(Component.literal("TileTCRail").withStyle(ChatFormatting.RED));
            line(player, "Name", rail.getTrackType().name());
            line(player, "Facing", rail.getBlockState().getValue(traincraft.track.block.TrackBlock.FACING).getSerializedName());
            line(player, "SwitchState", String.valueOf(rail.isSwitchActive()));
            line(player, "Model", String.valueOf(rail.hasModel()));
            return InteractionResult.SUCCESS;
        }
        if (entity instanceof TrackOccupancyBlockEntity gag) {
            player.sendSystemMessage(Component.literal("TileTCRailGag").withStyle(ChatFormatting.RED));
            line(player, "Origin", String.valueOf(gag.origin()));
            return InteractionResult.SUCCESS;
        }
        player.sendSystemMessage(Component.literal("Not a Traincraft track").withStyle(ChatFormatting.GRAY));
        return InteractionResult.SUCCESS;
    }

    private static void line(net.minecraft.world.entity.player.Player player, String label, String value) {
        player.sendSystemMessage(Component.literal(label + ": ").withStyle(ChatFormatting.GOLD).append(Component.literal(value).withStyle(ChatFormatting.WHITE)));
    }
}
