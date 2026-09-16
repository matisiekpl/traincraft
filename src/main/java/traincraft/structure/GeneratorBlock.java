package traincraft.structure;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;

import org.jspecify.annotations.Nullable;

public class GeneratorBlock extends StructureBlock {

    public static final MapCodec<GeneratorBlock> CODEC = simpleCodec(properties -> new GeneratorBlock(GeneratorBlockEntity.Kind.WATER_WHEEL, properties));

    private final GeneratorBlockEntity.Kind kind;

    public GeneratorBlock(GeneratorBlockEntity.Kind kind, Properties properties) {
        super(properties);
        this.kind = kind;
    }

    @Override
    protected MapCodec<? extends StructureBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return kind == GeneratorBlockEntity.Kind.DIESEL ? new DieselGeneratorBlockEntity(pos, state) : new GeneratorBlockEntity(kind, pos, state);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? null : (world, pos, blockState, entity) -> ((GeneratorBlockEntity) entity).tick();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof DieselGeneratorBlockEntity generator) {
            if (player instanceof ServerPlayer server) {
                server.openMenu(generator, buffer -> buffer.writeBlockPos(pos));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.getBlockEntity(pos) instanceof DieselGeneratorBlockEntity generator) {
            generator.setPowered(level.hasNeighborSignal(pos));
        }
    }
}
