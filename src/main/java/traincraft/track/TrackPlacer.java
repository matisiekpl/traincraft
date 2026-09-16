package traincraft.track;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import traincraft.bootstrap.BlockRegistry;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlockEntity;

import java.util.ArrayList;
import java.util.List;

public final class TrackPlacer {

    private TrackPlacer() {}

    /**
     * Places the piece the player asked for.
     *
     * @param origin the block the track sits on top of; the plan's offsets are relative to it
     * @param facingMeta the player's quantised orientation, 0..3
     */
    public static PlacementResult place(
            Level level, BlockPos origin, TrackType type, int facingMeta) {
        String unsupported = TrackPlacementPlanner.whyUnsupported(type, facingMeta);
        if (unsupported != null) {
            return new PlacementResult.Unsupported(unsupported);
        }
        return apply(level, origin, TrackPlacementPlanner.plan(type, facingMeta));
    }

    /**
     * Whether this plan would go down here, without writing anything.
     *
     * <p>Reads only the world, so it answers the same on the client, which is what lets the preview
     * ghost show the refusal a player is about to get rather than a message after the fact.
     */
    public static PlacementResult check(Level level, BlockPos origin, TrackPlan plan) {
        for (Placement placement : plan.placements()) {
            BlockPos pos = origin.offset(placement.dx(), placement.dy(), placement.dz());
            if (!level.isInWorldBounds(pos)
                    || !level.hasChunkAt(pos)
                    || !level.getBlockState(pos).canBeReplaced()
                    || level.getBlockEntity(pos) != null) {
                return new PlacementResult.Blocked(pos);
            }
            if (!TrackSupport.isSupported(level, pos)) {
                return new PlacementResult.NoSupport(pos);
            }
        }
        return new PlacementResult.Placed();
    }

    /** Applies an already-resolved plan. Separated so tests can hand in a plan directly. */
    public static PlacementResult apply(Level level, BlockPos origin, TrackPlan plan) {
        if (level.isClientSide())
            return new PlacementResult.Failed(origin, "Server placement required");
        PlacementResult check = check(level, origin, plan);
        if (!check.placed()) {
            return check;
        }
        List<BlockState> previous = new ArrayList<>();
        List<BlockPos> positions = new ArrayList<>(plan.size());
        for (Placement placement : plan.placements()) {
            BlockPos pos = origin.offset(placement.dx(), placement.dy(), placement.dz());
            positions.add(pos);
            previous.add(level.getBlockState(pos));
        }

        int written = 0;
        try {
            BlockPos master = null;
            for (int i = 0; i < plan.size(); i++) {
                Placement placement = plan.get(i);
                BlockPos pos = positions.get(i);
                written = i + 1;
                if (placement.gag()) {
                    writeGag(level, pos, origin, placement);
                } else {
                    writeRail(level, pos, origin, placement);
                    if (placement.dropsItem()) {
                        master = pos;
                    }
                }
            }

            // The owning rail remembers what the player asked for and which way they were facing,
            // so
            // the footprint can be recomputed later to take the piece apart again. Neither is
            // recoverable from the placed blocks: a medium turn labels its own tiles as left turns.
            if (master != null && level.getBlockEntity(master) instanceof TrackBlockEntity owner) {
                owner.setPlacementOwner(plan.requested(), plan.facingMeta());
                owner.setChanged();
            }

            // Gags point at the rail whose geometry they follow; the breaker walks on from there
            // to the assembly owner. Only once every block exists is that position known to be
            // occupied.
            if (master != null) {
                for (int i = 0; i < plan.size(); i++) {
                    if (plan.get(i).gag()
                            && level.getBlockEntity(positions.get(i))
                                    instanceof TrackOccupancyBlockEntity gag) {
                        Placement owner = plan.ownerOf(plan.get(i));
                        gag.setOrigin(
                                origin.getX() + owner.dx(),
                                origin.getY() + owner.dy(),
                                origin.getZ() + owner.dz());
                        gag.setChanged();
                    } else if (level.getBlockEntity(positions.get(i))
                            instanceof TrackBlockEntity rail) {
                        // Rails that are not the owner point at it too. A switch has four of them,
                        // and
                        // breaking any one has to take the whole switch with it.
                        rail.setAssemblyOrigin(master);
                    }
                }
            }
        } catch (RuntimeException failure) {
            for (int i = written - 1; i >= 0; i--) {
                if (!level.setBlock(
                                positions.get(i),
                                previous.get(i),
                                Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE)
                        && !level.getBlockState(positions.get(i)).equals(previous.get(i))) {
                    throw new IllegalStateException(
                            "Could not restore placement at " + positions.get(i), failure);
                }
            }
            traincraft.Traincraft.LOGGER.error("Track placement failed at {}", origin, failure);
            return new PlacementResult.Failed(origin, failure.getMessage());
        }
        for (int i = 0; i < positions.size(); i++) {
            BlockPos pos = positions.get(i);
            BlockState state = level.getBlockState(pos);
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
            level.updateNeighborsAt(pos, level.getBlockState(pos).getBlock());
        }
        return new PlacementResult.Placed();
    }

    private static void writeRail(Level level, BlockPos pos, BlockPos origin, Placement placement) {
        BlockState state =
                BlockRegistry.TC_RAIL
                        .get()
                        .defaultBlockState()
                        .setValue(
                                TrackBlock.FACING, TrackBlock.facingFromMeta(placement.blockMeta()))
                        .setValue(TrackBlock.SHAPE, placement.type().shapeGroup());
        if (!level.setBlock(pos, state, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE)) {
            throw new IllegalStateException("Block write rejected at " + pos);
        }

        if (!(level.getBlockEntity(pos) instanceof TrackBlockEntity tile)) {
            throw new IllegalStateException("Missing track block entity at " + pos);
        }
        tile.initialize(origin, placement);
    }

    private static void writeGag(Level level, BlockPos pos, BlockPos origin, Placement placement) {
        BlockState state = BlockRegistry.TC_RAIL_GAG.get().defaultBlockState();
        if (!level.setBlock(pos, state, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE)) {
            throw new IllegalStateException("Block write rejected at " + pos);
        }
        if (level.getBlockEntity(pos) instanceof TrackOccupancyBlockEntity gag) {
            gag.setCollisionHeight(placement.gagHeight());
            gag.setTypeLabel(placement.type().label());
            // Provisional: overwritten once the assembly's master rail is known.
            gag.setOrigin(origin.getX(), origin.getY(), origin.getZ());
            gag.setChanged();
        } else {
            throw new IllegalStateException("Missing occupancy block entity at " + pos);
        }
    }
}
