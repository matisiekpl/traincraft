package traincraft.track;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;

/**
 * The one rule about what track may rest on.
 *
 * <p>Community Edition asks {@code World.doesBlockHaveSolidTopSurface} of the block below every
 * cell a piece occupies -- gag blocks included -- both when the piece is laid and every time a
 * neighbour changes afterwards. Track never hangs in the air there.
 *
 * <p>The predicate is {@link Block#canSupportRigidBlock}, which is {@code SupportType.RIGID} and is
 * what vanilla's own {@code BaseRailBlock.canSurvive} asks. That is the honest reading of 1.7.10's
 * {@code Block.isSideSolid(..., UP)}: a full top yes, a fence post or another rail no. The default
 * {@code isFaceSturdy} is {@code SupportType.FULL}, which is stricter than upstream -- it would
 * refuse a hopper that 1.7.10 accepts.
 *
 * <p>Upstream exempts its bridge pillar. That block is not ported, so the exemption is not either;
 * it goes back in with the pillar.
 */
public final class TrackSupport {

    private TrackSupport() {}

    public static boolean isSupported(BlockGetter level, BlockPos pos) {
        return Block.canSupportRigidBlock(level, pos.below());
    }
}
