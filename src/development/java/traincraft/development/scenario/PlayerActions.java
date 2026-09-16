package traincraft.development.scenario;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.Traincraft;

/**
 * What a player does, done the way a player does it.
 *
 * <p>Every action here goes through the server's own interaction path -- the same {@code
 * useItemOn}, {@code destroyBlock}, {@code interactOn} and {@code attack} a click arrives at. That
 * is the point. Calling {@code TrackPlacer} directly, which is what the harness did before, proves
 * the placement maths and nothing else: it cannot tell you that the item exists, that it is in the
 * creative tab, that it has a model, that right-clicking the ground reaches it, that the piece
 * drops something you can put back down, or that breaking one block of a curve takes the rest with
 * it. Those are the failures a player meets first.
 *
 * <p>Not a simulation of the mouse. The click itself is the client's, and a headless client has no
 * mouse; what a click *becomes* by the time it reaches the server is a packet, and this is that
 * packet's destination with the same arguments.
 */
public final class PlayerActions {

    private PlayerActions() {}

    /** The harness player, or null when the server has none yet. */
    public static @Nullable ServerPlayer player(MinecraftServer server) {
        return server.getPlayerList().getPlayers().isEmpty()
                ? null
                : server.getPlayerList().getPlayers().getFirst();
    }

    /** Puts an item in the player's hand, as a creative-mode pick would. */
    public static boolean hold(ServerPlayer player, String itemId, int count) {
        Item item = BuiltInRegistries.ITEM.getOptional(Identifier.parse(itemId)).orElse(null);
        if (item == null) {
            Traincraft.LOGGER.error("hold: no such item {}", itemId);
            return false;
        }
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(item, count));
        return true;
    }

    /**
     * Right-clicks a block face while holding an item.
     *
     * <p>The player is put within arm's reach and turned to the requested yaw first, because both
     * matter to the result: reach is checked, and a track item reads the yaw to decide which way
     * the piece points.
     */
    public static boolean useItemOn(
            ServerLevel level,
            ServerPlayer player,
            String itemId,
            BlockPos pos,
            Direction face,
            float yaw) {
        if (!hold(player, itemId, 1)) {
            return false;
        }
        Vec3 centre = Vec3.atCenterOf(pos);
        player.snapTo(centre.x + 1.5, pos.getY() + 1, centre.z, yaw, 0.0F);

        Vec3 hit = centre.add(face.getStepX() * 0.5, face.getStepY() * 0.5, face.getStepZ() * 0.5);
        BlockHitResult hitResult = new BlockHitResult(hit, face, pos, false);
        var result =
                player.gameMode.useItemOn(
                        player,
                        level,
                        player.getItemInHand(InteractionHand.MAIN_HAND),
                        InteractionHand.MAIN_HAND,
                        hitResult);
        Traincraft.LOGGER.info(
                "useItemOn {} at {} face {} yaw {} -> {}",
                itemId,
                pos.toShortString(),
                face,
                yaw,
                result);
        return result.consumesAction();
    }

    /** Breaks a block, as a completed pickaxe swing does. */
    public static boolean breakBlock(ServerPlayer player, BlockPos pos) {
        boolean broken = player.gameMode.destroyBlock(pos);
        Traincraft.LOGGER.info("breakBlock {} -> {}", pos.toShortString(), broken);
        return broken;
    }

    /** Right-clicks an entity: mounting a locomotive, or opening its menu while crouching. */
    public static boolean interactWith(ServerPlayer player, Entity entity, boolean crouching) {
        player.setShiftKeyDown(crouching);
        var result = player.interactOn(entity, InteractionHand.MAIN_HAND, Vec3.ZERO);
        player.setShiftKeyDown(false);
        Traincraft.LOGGER.info(
                "interact with {} crouching={} -> {}",
                entity.getType().toShortString(),
                crouching,
                result);
        return result.consumesAction();
    }

    /** Hits an entity, as a left click does. */
    public static void attack(ServerPlayer player, Entity entity) {
        player.attack(entity);
        Traincraft.LOGGER.info(
                "attacked {} -> removed={}", entity.getType().toShortString(), entity.isRemoved());
    }

    /** What the player is carrying, for a report line that says whether a drop was picked up. */
    public static String describeInventory(ServerPlayer player) {
        StringBuilder sb = new StringBuilder();
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (!stack.isEmpty()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(stack.getCount())
                        .append("x ")
                        .append(BuiltInRegistries.ITEM.getKey(stack.getItem()));
            }
        }
        return sb.isEmpty() ? "(empty)" : sb.toString();
    }
}
