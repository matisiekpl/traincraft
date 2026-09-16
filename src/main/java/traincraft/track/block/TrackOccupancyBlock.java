package traincraft.track.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jspecify.annotations.Nullable;

import traincraft.track.TrackBreaker;
import traincraft.track.TrackSupport;
import traincraft.vehicle.entity.RollingStockEntity;

public class TrackOccupancyBlock extends Block implements EntityBlock {

    public static final MapCodec<TrackOccupancyBlock> CODEC = simpleCodec(TrackOccupancyBlock::new);

    public TrackOccupancyBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    /**
     * Height comes from the block entity, because a slope's gags each sit at a different point up
     * the ramp. Falls back to the flat rail height when the entity is not available, which happens
     * during world load before entities are attached.
     */
    @Override
    protected VoxelShape getShape(
            BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        double height =
                level.getBlockEntity(pos) instanceof TrackOccupancyBlockEntity gag
                        ? gag.collisionHeight()
                        : 0.125;
        return Block.box(0.0, 0.0, 0.0, 16.0, Math.max(height, 0.0625) * 16.0, 16.0);
    }

    /** Pedestrians stand on the track; rolling stock uses its own movement solver. */
    @Override
    protected VoxelShape getCollisionShape(
            BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Rolling stock follows track geometry through its own movement solver.
        if (context instanceof EntityCollisionContext entityContext
                && entityContext.getEntity() instanceof RollingStockEntity) {
            return Shapes.empty();
        }
        return getShape(state, level, pos, context);
    }

    /**
     * Removing any part of an assembly removes all of it.
     *
     * <p>The owning rail's own removal handler clears the gags; this handles the other direction,
     * where the player breaks a gag. Without it a curve could be left with a gap that still
     * reserved the space and still rendered its full model.
     */
    /**
     * A player breaking any block of a piece takes the whole piece, and gets back the one item it
     * was laid from.
     *
     * <p>Before removal, not after: the owner is recorded on a block entity and the after-removal
     * hook runs once that is gone.
     */
    @Override
    public BlockState playerWillDestroy(
            Level level, BlockPos pos, BlockState state, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            TrackBreaker.dismantle(serverLevel, pos, !player.hasInfiniteMaterials());
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void affectNeighborsAfterRemoval(
            BlockState state,
            net.minecraft.server.level.ServerLevel level,
            BlockPos pos,
            boolean movedByPiston) {
        // Whatever removed this block was not a player -- an explosion, a command, a piston. The
        // block entity has already gone, so the owner is found through a neighbour instead.
        TrackBreaker.dismantleFromNeighbour(level, pos, true);
        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TrackOccupancyBlockEntity(pos, state);
    }

    /**
     * Track that loses the ground under it comes down, and takes the rest of its piece with it.
     *
     * <p>Community Edition does this in {@code onNeighborBlockChange} on both the rail and its gag
     * blocks: no solid top surface below, and the block destroys itself. The whole assembly follows
     * because upstream's break cascade tears down the linked rail and its neighbourhood; here that
     * is {@link TrackBreaker#dismantle}, which also returns the one item the piece was laid from.
     *
     * <p>Not {@code canSurvive} and {@code updateShape}: the vanilla idiom removes the single block
     * with no drop and without going through the breaker, which would leave the rest of a curve
     * standing.
     */
    @Override
    protected void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block block,
            @Nullable Orientation orientation,
            boolean movedByPiston) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        // The state test is not belt and braces: 26.2 batches neighbour updates and can deliver one
        // for a block the dismantle has already cleared, which would then look unsupported and
        // start a second dismantle. Vanilla's own rail guards the same way.
        if (level instanceof ServerLevel serverLevel
                && serverLevel.getBlockState(pos).is(this)
                && !TrackSupport.isSupported(serverLevel, pos)) {
            TrackBreaker.dismantle(serverLevel, pos, true);
        }
    }
}
