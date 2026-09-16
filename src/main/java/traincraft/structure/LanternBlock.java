package traincraft.structure;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import traincraft.item.TraincraftItems;
import traincraft.network.StructureActionPayload;

public class LanternBlock extends StructureBlock {

    public static final MapCodec<LanternBlock> CODEC = simpleCodec(LanternBlock::new);

    public LanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends StructureBlock> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.22, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
        level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.22, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isShiftKeyDown() || !(level.getBlockEntity(pos) instanceof StructureBlockEntity lantern)) {
            return InteractionResult.PASS;
        }
        var dye = stack.get(net.minecraft.core.component.DataComponents.DYE);
        if (dye != null) {
            lantern.setColour(dye.getTextColor() & 0xFFFFFF);
            return InteractionResult.SUCCESS;
        }
        if (stack.is(TraincraftItems.item("composite_wrench")) && player instanceof ServerPlayer server) {
            StructureActionPayload.openLantern(server, pos, lantern.colour());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
