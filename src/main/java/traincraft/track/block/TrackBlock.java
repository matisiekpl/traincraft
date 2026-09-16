package traincraft.track.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jspecify.annotations.Nullable;

import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.track.TrackBreaker;
import traincraft.track.TrackSupport;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.track.TrackShapeGroup;
import traincraft.track.TrackType;

public class TrackBlock extends Block implements EntityBlock {

    public static final MapCodec<TrackBlock> CODEC = simpleCodec(TrackBlock::new);

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<TrackShapeGroup> SHAPE =
            EnumProperty.create("shape", TrackShapeGroup.class);

    private static final VoxelShape RAIL_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

    public TrackBlock(Properties properties) {
        super(properties);
        registerDefaultState(
                getStateDefinition()
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(SHAPE, TrackShapeGroup.STRAIGHT));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE);
    }

    /**
     * Recomputes the derived blockstate for a block entity's current type and facing. Called from
     * {@link TrackBlockEntity#syncToClients()} so the cache can never drift.
     */
    public static BlockState withDerivedState(
            BlockState current, @Nullable TrackType type, int facingMeta) {
        if (!(current.getBlock() instanceof TrackBlock)) {
            return current;
        }
        TrackShapeGroup group = type == null ? TrackShapeGroup.STRAIGHT : type.shapeGroup();
        return current.setValue(FACING, facingFromMeta(facingMeta)).setValue(SHAPE, group);
    }

    public static Direction facingFromMeta(int meta) {
        return Direction.from2DDataValue(meta & 3);
    }

    public static int metaFromFacing(Direction direction) {
        return direction.get2DDataValue();
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected VoxelShape getShape(
            BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return RAIL_SHAPE;
    }

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
     * Breaking any rail of a piece takes the whole piece, and returns the one item it was laid
     * from.
     *
     * <p>This hook rather than {@code playerWillDestroy} because it fires for every route a block
     * can leave the world -- a pickaxe, an explosion, a command -- and a curve left half-standing
     * by any of them is equally broken.
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
            BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        // Whatever removed this block was not a player -- an explosion, a command, a piston. The
        // block entity has already gone, so the owner is found through a neighbour instead.
        TrackBreaker.dismantleFromNeighbour(level, pos, true);
        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
    }

    /**
     * Only on the server, and only for the rails that can throw a switch.
     *
     * <p>The client has nothing to decide here: it is told the switch state through the block
     * entity's own sync, and the two models are swapped by the renderer from that.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        // No createTickerHelper on a plain Block in 26.2 -- that lives on BaseEntityBlock. The
        // check it performs is the whole of it: only tick the type this block actually has.
        return type == BlockEntityRegistry.TC_RAIL.get()
                ? (BlockEntityTicker<T>)
                        (BlockEntityTicker<TrackBlockEntity>) TrackBlockEntity::serverTick
                : null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TrackBlockEntity(pos, state);
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
