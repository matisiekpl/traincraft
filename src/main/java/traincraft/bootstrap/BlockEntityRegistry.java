package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlockEntity;

import java.util.Set;

/**
 * Block entity type registry.
 *
 * <p>There is no {@code DeferredRegister.createBlockEntities} helper, and 26.2 dropped {@code
 * BlockEntityType.Builder} in favour of a plain constructor taking the factory and the set of valid
 * blocks -- hence the explicit {@code DeferredRegister.create}.
 */
public final class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Traincraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrackBlockEntity>>
            TC_RAIL =
                    TYPES.register(
                            "tc_rail",
                            () ->
                                    new BlockEntityType<>(
                                            TrackBlockEntity::new,
                                            Set.of(BlockRegistry.TC_RAIL.get())));

    public static final DeferredHolder<
                    BlockEntityType<?>, BlockEntityType<TrackOccupancyBlockEntity>>
            TC_RAIL_GAG =
                    TYPES.register(
                            "tc_rail_gag",
                            () ->
                                    new BlockEntityType<>(
                                            TrackOccupancyBlockEntity::new,
                                            Set.of(BlockRegistry.TC_RAIL_GAG.get())));

    private BlockEntityRegistry() {}
}
