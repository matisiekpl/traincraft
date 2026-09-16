package traincraft.track;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import org.jspecify.annotations.Nullable;

import traincraft.bootstrap.TrackItemRegistry;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlock;
import traincraft.track.block.TrackOccupancyBlockEntity;

import java.util.ArrayList;
import java.util.List;

public final class TrackBreaker {

    /**
     * Guards against the recursion the removal itself causes.
     *
     * <p>Removing a block fires the same hook that got us here, once per block of the piece.
     * Per-thread because the server may be taking two pieces apart at once on different worlds, and
     * a shared flag would let one swallow the other's removals.
     */
    private static final ThreadLocal<Boolean> DISMANTLING = ThreadLocal.withInitial(() -> false);

    private TrackBreaker() {}

    /**
     * Removes the piece that owns {@code pos}, dropping its item once.
     *
     * <p>Must be called <strong>before</strong> the block goes, because the owner is read from a
     * block entity and by the time the after-removal hook runs there is none. That is not obvious
     * and it is exactly how this failed the first time: the hook fired, found nothing, and left the
     * rest of the switch standing with no item to show for it.
     *
     * @param drop whether to drop the item; false when the player is in creative
     */
    public static void dismantle(ServerLevel level, BlockPos pos, boolean drop) {
        if (DISMANTLING.get()) {
            return;
        }
        BlockPos origin = ownerOf(level, pos);
        if (origin == null) {
            return;
        }
        if (!(level.getBlockEntity(origin) instanceof TrackBlockEntity owner)) {
            // The owner has already gone -- this is one of its blocks being cleared by the pass
            // that removed it, or the piece predates the field. Nothing to do and nothing to drop.
            return;
        }

        TrackType type = owner.dropType();
        int facing = owner.placedFacing();
        DISMANTLING.set(true);
        try {
            if (type != null && TrackPlacementPlanner.canPlan(type, facing)) {
                TrackPlan plan = TrackPlacementPlanner.plan(type, facing);
                // Resolve membership before removing any rails: filler blocks reach the
                // assembly owner through their geometry rail, which may be cleared first.
                List<BlockPos> members = new ArrayList<>(plan.size());
                for (Placement placement : plan.placements()) {
                    Placement.Link offset = plan.offsetFromOwner(placement);
                    BlockPos member = origin.offset(offset.dx(), offset.dy(), offset.dz());
                    if (origin.equals(ownerOf(level, member))) {
                        members.add(member);
                    }
                }
                for (BlockPos member : members) {
                    clear(level, member);
                }
            } else {
                // No remembered plan: take at least the block itself and the owner, so a piece
                // from an older world does not become impossible to remove.
                clear(level, pos);
                clear(level, origin);
            }
            if (drop && type != null) {
                Item item = TrackItemRegistry.itemFor(type);
                if (item != null) {
                    Containers.dropItemStack(
                            level,
                            origin.getX() + 0.5,
                            origin.getY() + 0.5,
                            origin.getZ() + 0.5,
                            new ItemStack(item));
                }
            }
        } finally {
            DISMANTLING.set(false);
        }
    }

    /** Removes one block of a piece, leaving anything that is not ours alone. */
    private static void clear(ServerLevel level, BlockPos pos) {
        Block block = level.getBlockState(pos).getBlock();
        if (block instanceof TrackBlock || block instanceof TrackOccupancyBlock) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    /**
     * Removes the piece a block belonged to, after that block has already gone.
     *
     * <p>The owner cannot be read from the block itself any more, so it is read from a neighbour:
     * every other block of a piece knows the same owner, and a piece is contiguous. A piece one
     * block across has no neighbour to ask, and there is also nothing left of it to remove.
     *
     * <p>This is the path an explosion or a {@code /setblock} takes. A pickaxe goes through {@link
     * #dismantle} before the block is removed, which is exact.
     */
    public static void dismantleFromNeighbour(ServerLevel level, BlockPos pos, boolean drop) {
        if (DISMANTLING.get()) {
            return;
        }
        for (Direction direction : Direction.values()) {
            BlockPos neighbour = pos.relative(direction);
            BlockPos origin = ownerOf(level, neighbour);
            if (origin != null
                    && level.getBlockEntity(origin) instanceof TrackBlockEntity owner
                    && owner.dropType() != null
                    && TrackPlacementPlanner.canPlan(owner.dropType(), owner.placedFacing())) {
                TrackPlan plan = TrackPlacementPlanner.plan(owner.dropType(), owner.placedFacing());
                if (plan.containsOwnerRelativeOffset(
                        pos.getX() - origin.getX(),
                        pos.getY() - origin.getY(),
                        pos.getZ() - origin.getZ())) {
                    dismantle(level, neighbour, drop);
                    return;
                }
            }
        }
    }

    /** The block that owns the piece {@code pos} belongs to, or null if it belongs to none. */
    public static @Nullable BlockPos ownerOf(ServerLevel level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof TrackOccupancyBlockEntity gag) {
            return level.getBlockEntity(gag.origin()) instanceof TrackBlockEntity rail
                    ? rail.assemblyOrigin()
                    : gag.origin();
        }
        if (level.getBlockEntity(pos) instanceof TrackBlockEntity rail) {
            return rail.assemblyOrigin();
        }
        return null;
    }
}
