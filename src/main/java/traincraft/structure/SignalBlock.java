package traincraft.structure;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;

import org.jspecify.annotations.Nullable;

import traincraft.vehicle.entity.RollingStockEntity;

public class SignalBlock extends StructureBlock {

    public static final MapCodec<SignalBlock> CODEC = simpleCodec(SignalBlock::new);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final int STOP_REACH = 9;

    private boolean stopsTrains;

    public SignalBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(POWERED, false));
    }

    public SignalBlock stopping() {
        stopsTrains = true;
        return this;
    }

    @Override
    protected MapCodec<? extends StructureBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        boolean powered = level.hasNeighborSignal(pos);
        if (powered != state.getValue(POWERED)) {
            level.setBlock(pos, state.setValue(POWERED, powered), 3);
        }
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!stopsTrains || level.isClientSide()) {
            return null;
        }
        return (world, pos, blockState, entity) -> holdTrains(world, pos, blockState);
    }

    private void holdTrains(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(POWERED)) {
            return;
        }
        BlockPos far = pos.relative(state.getValue(FACING), STOP_REACH);
        AABB box = new AABB(pos).minmax(new AABB(far)).inflate(1.0, 1.0, 1.0);
        for (RollingStockEntity stock : level.getEntitiesOfClass(RollingStockEntity.class, box)) {
            stock.setDeltaMovement(stock.getDeltaMovement().multiply(0.85, 1.0, 0.85));
        }
    }
}
