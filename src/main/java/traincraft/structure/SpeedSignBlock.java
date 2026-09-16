package traincraft.structure;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SpeedSignBlock extends StructureBlock {

    public static final MapCodec<SpeedSignBlock> CODEC = simpleCodec(SpeedSignBlock::new);
    public static final int SKINS = 5;

    public SpeedSignBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends StructureBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof StructureBlockEntity sign) {
            sign.setState((sign.state() + 1) % SKINS);
        }
        return InteractionResult.SUCCESS;
    }
}
