package traincraft.track.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import traincraft.Traincraft;
import traincraft.track.TrackDirection;
import traincraft.track.TrackFacing;
import traincraft.track.TrackPlacer;
import traincraft.track.TrackType;
import traincraft.track.block.TrackBlockEntity;

import java.util.function.Consumer;

public class TrackItem extends Item {

    private final TrackType type;

    public TrackItem(Properties properties, TrackType type) {
        super(properties);
        this.type = type;
    }

    public TrackType trackType() {
        return type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        // Upstream's getPlacementHeight, which works off Y alone and ignores the face: a click on
        // something replaceable -- tall grass, a flower -- puts the track where that was, and a
        // click on anything else puts it on top. Following the face instead is what let a click on
        // the top of an existing rail lay a second piece hanging a block up in the air.
        BlockPos pos = placementOrigin(level, context.getClickedPos());

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        int facing = facingFrom(player);
        if (type == TrackType.SMALL_STRAIGHT || type == TrackType.EMBEDDED_SMALL_STRAIGHT) {
            facing = alignedFacing(level, pos, facing);
        }

        TrackType placed =
                player == null ? type : TrackDirection.resolve(type, facing, player.getYRot());
        traincraft.track.PlacementResult result = TrackPlacer.place(level, pos, placed, facing);
        if (!result.placed()) {
            // Logged as well as told to the player: a refusal a player reads in chat is invisible
            // to a scripted capture, and "nothing happened" is the hardest kind of failure to
            // chase afterwards.
            Traincraft.LOGGER.info(
                    "refused to lay {} facing {} at {}: {}{}",
                    placed,
                    facing,
                    pos.toShortString(),
                    result.reason(),
                    switch (result) {
                        case traincraft.track.PlacementResult.Blocked blocked ->
                                " at " + blocked.position();
                        case traincraft.track.PlacementResult.NoSupport noSupport ->
                                " at " + noSupport.position();
                        default -> "";
                    });
            if (player != null) {
                player.sendSystemMessage(describe(result));
            }
            return InteractionResult.FAIL;
        }
        Traincraft.LOGGER.info("laid {} facing {} at {}", placed, facing, pos.toShortString());

        ItemStack stack = context.getItemInHand();
        if (player == null || !player.hasInfiniteMaterials()) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    /** Upstream's {@code getPlacementHeight}, as a position. See the note in {@code useOn}. */
    public static BlockPos placementOrigin(Level level, BlockPos clicked) {
        return level.getBlockState(clicked).canBeReplaced() ? clicked : clicked.above();
    }

    /** A one-block straight takes the axis of a straight rail beside it, the way a vanilla rail does. */
    public static int alignedFacing(Level level, BlockPos pos, int facing) {
        for (Direction side : Direction.Plane.HORIZONTAL) {
            if (level.getBlockEntity(pos.relative(side)) instanceof TrackBlockEntity rail
                    && rail.geometry().curve() == null
                    && rail.geometry().slope() == null
                    && TrackFacing.toDirection(rail.getFacing()).getAxis() == side.getAxis()) {
                return rail.getFacing();
            }
        }
        return facing;
    }

    public static int facingFrom(Player player) {
        if (player == null) {
            return 0;
        }
        return Math.floorMod((int) Math.floor(player.getYRot() * 4.0F / 360.0F + 0.5F), 4);
    }

    private static Component describe(traincraft.track.PlacementResult result) {
        if (result instanceof traincraft.track.PlacementResult.Blocked blocked) {
            BlockPos at = blocked.position();
            return Component.literal(
                            "Not enough room: something is in the way at "
                                    + at.getX()
                                    + ", "
                                    + at.getY()
                                    + ", "
                                    + at.getZ())
                    .withStyle(ChatFormatting.RED);
        }
        if (result instanceof traincraft.track.PlacementResult.NoSupport noSupport) {
            BlockPos at = noSupport.position();
            return Component.literal(
                            "There is nothing to lay this on at "
                                    + at.getX()
                                    + ", "
                                    + at.getY()
                                    + ", "
                                    + at.getZ())
                    .withStyle(ChatFormatting.RED);
        }
        return Component.literal("This track cannot be placed: " + result.reason())
                .withStyle(ChatFormatting.RED);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> lines,
            TooltipFlag flag) {
        // The footprint, because it is the thing a player needs to know before clicking: a very
        // large turn wants a hundred blocks of clear ground and refuses politely if it has less.
        lines.accept(Component.literal(type.tooltip()).withStyle(ChatFormatting.GRAY));
    }
}
