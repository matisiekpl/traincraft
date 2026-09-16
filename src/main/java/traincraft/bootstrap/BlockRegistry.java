package traincraft.bootstrap;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackOccupancyBlock;

public final class BlockRegistry {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Traincraft.MODID);

    public static final DeferredBlock<TrackBlock> TC_RAIL =
            BLOCKS.registerBlock(
                    "tc_rail",
                    TrackBlock::new,
                    () ->
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.METAL)
                                    .strength(1.0F)
                                    .sound(SoundType.METAL)
                                    .noOcclusion()
                                    .dynamicShape());

    /**
     * The filler block of a multi-block piece. Same material as the rail so it breaks the same way,
     * but it never renders and its collision height comes from its block entity.
     */
    public static final DeferredBlock<TrackOccupancyBlock> TC_RAIL_GAG =
            BLOCKS.registerBlock(
                    "tc_rail_gag",
                    TrackOccupancyBlock::new,
                    () ->
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.METAL)
                                    .strength(1.0F)
                                    .sound(SoundType.METAL)
                                    .noOcclusion()
                                    .dynamicShape());

    private BlockRegistry() {}
}
