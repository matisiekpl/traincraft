package traincraft.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

public class WrenchItem extends Item {

    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        BlockState rotated = state.rotate(Rotation.CLOCKWISE_90);
        if (rotated == state) {
            return InteractionResult.PASS;
        }
        if (!context.getLevel().isClientSide()) {
            context.getLevel().setBlock(context.getClickedPos(), rotated, 3);
        }
        return InteractionResult.SUCCESS;
    }
}
