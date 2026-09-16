package traincraft.structure;

import java.util.Set;
import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.bootstrap.BlockRegistry;
import traincraft.bootstrap.ItemRegistry;
import traincraft.bootstrap.MenuRegistry;

public final class StructureRegistry {

    public static final java.util.Map<String, DeferredBlock<? extends Block>> BLOCKS = new java.util.LinkedHashMap<>();
    public static final java.util.Map<String, DeferredHolder<Item, Item>> ITEMS = new java.util.LinkedHashMap<>();
    public static final Set<String> MODEL_BLOCKS = new java.util.LinkedHashSet<>();

    private static BlockBehaviour.Properties stone(float strength) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(strength).sound(SoundType.STONE);
    }

    private static BlockBehaviour.Properties metal(float strength) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(strength).sound(SoundType.METAL).noOcclusion();
    }

    private static BlockBehaviour.Properties wood(float strength) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(strength).sound(SoundType.WOOD).noOcclusion();
    }

    private static <B extends Block> DeferredBlock<B> block(String name, java.util.function.Function<BlockBehaviour.Properties, B> factory, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<B> block = BlockRegistry.BLOCKS.registerBlock(name, factory, properties);
        BLOCKS.put(name, block);
        ITEMS.put(name, ItemRegistry.ITEMS.register(name, id -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id)))));
        return block;
    }

    private static <B extends StructureBlock> DeferredBlock<B> model(String name, java.util.function.Function<BlockBehaviour.Properties, B> factory, Supplier<BlockBehaviour.Properties> properties) {
        MODEL_BLOCKS.add(name);
        return block(name, factory, properties);
    }

    public static final DeferredBlock<SignalBlock> SIGNAL = model("signal", properties -> new SignalBlock(properties).stopping().shaped(3, 0, 3, 13, 42, 13), () -> metal(1.7F).lightLevel(state -> 15));
    public static final DeferredBlock<StructureBlock> STOPPER = model("stopper", properties -> new StructureBlock(properties).shaped(0, 0, 0, 16, 12, 16), () -> metal(1.7F));
    public static final DeferredBlock<StructureBlock> EMBEDDED_STOPPER = model("embedded_stopper", properties -> new StructureBlock(properties).shaped(0, 0, 0, 16, 12, 16), () -> metal(1.7F));
    public static final DeferredBlock<StructureBlock> AMERICAN_STOPPER = model("american_stopper", properties -> new StructureBlock(properties).shaped(0, 0, 0, 16, 12, 16), () -> metal(1.7F));
    public static final DeferredBlock<LanternBlock> LANTERN = model("lantern", properties -> new LanternBlock(properties).shaped(3.2, 0, 3.2, 12.8, 14.4, 12.8), () -> metal(1.7F).lightLevel(state -> 15));
    public static final DeferredBlock<SwitchStandBlock> SWITCH_STAND = model("switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> metal(1.7F));
    public static final DeferredBlock<SignalBlock> WIG_WAG = model("wig_wag", properties -> new SignalBlock(properties).shaped(4, 0, 4, 12, 40, 12), () -> metal(2.5F));
    public static final DeferredBlock<GeneratorBlock> WATER_WHEEL = model("water_wheel", properties -> new GeneratorBlock(GeneratorBlockEntity.Kind.WATER_WHEEL, properties), () -> wood(1.7F));
    public static final DeferredBlock<GeneratorBlock> WIND_MILL = model("wind_mill", properties -> new GeneratorBlock(GeneratorBlockEntity.Kind.WIND_MILL, properties).shaped(0, 0, 0, 16, 32, 16), () -> wood(1.7F));
    public static final DeferredBlock<GeneratorBlock> DIESEL_GENERATOR = model("diesel_generator", properties -> new GeneratorBlock(GeneratorBlockEntity.Kind.DIESEL, properties), () -> metal(1.7F));
    public static final DeferredBlock<StructureBlock> BRIDGE_PILLAR = model("bridge_pillar", properties -> new StructureBlock(properties), () -> wood(3.5F));
    public static final DeferredBlock<SwitchStandBlock> MILW_SWITCH_STAND = model("milw_switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> stone(1.0F).noOcclusion());
    public static final DeferredBlock<SwitchStandBlock> AUTO_SWITCH_STAND = model("auto_switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> stone(1.0F).noOcclusion());
    public static final DeferredBlock<SwitchStandBlock> OWO_SWITCH_STAND = model("owo_switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> metal(2.0F));
    public static final DeferredBlock<SwitchStandBlock> CIRCLE_SWITCH_STAND = model("circle_switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> stone(2.0F).noOcclusion());
    public static final DeferredBlock<SwitchStandBlock> OWO_YARD_SWITCH_STAND = model("owo_yard_switch_stand", properties -> new SwitchStandBlock(properties).shaped(2, 0, 2, 14, 16, 14), () -> stone(4.0F).noOcclusion());
    public static final DeferredBlock<SwitchStandBlock> OVERHEAD_WIRE = model("overhead_wire", properties -> new SwitchStandBlock(properties).shaped(0, 0, 0, 16, 16, 16), () -> metal(2.0F));
    public static final DeferredBlock<StructureBlock> OVERHEAD_WIRE_DOUBLE = model("overhead_wire_double", properties -> new StructureBlock(properties), () -> metal(2.0F));
    public static final DeferredBlock<SignalBlock> SPANISH_SIGNAL = model("spanish_signal", properties -> new SignalBlock(properties).shaped(3, 0, 3, 13, 32, 13), () -> stone(1.0F).noOcclusion().lightLevel(state -> 3));
    public static final DeferredBlock<SignalBlock> K_SIGNAL = model("k_signal", properties -> new SignalBlock(properties).shaped(3, 0, 3, 13, 32, 13), () -> stone(1.0F).noOcclusion().lightLevel(state -> 3));
    public static final DeferredBlock<StructureBlock> METRO_MADRID_POLE = model("metro_madrid_pole", properties -> new StructureBlock(properties), () -> metal(2.0F));
    public static final DeferredBlock<ContainerBlock> FORTY_FOOT_CONTAINER = model("forty_foot_container", properties -> new ContainerBlock(properties), () -> metal(2.0F));
    public static final DeferredBlock<SpeedSignBlock> SPEED_SIGN = model("speed_sign", properties -> new SpeedSignBlock(properties).shaped(2, 0, 2, 14, 24, 14), () -> metal(1.0F));

    public static final DeferredBlock<Block> POWERED_GRAVEL = block("powered_gravel", PoweredGravelBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> SNOW_GRAVEL = block("snow_gravel", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(1.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> DIRTY_BALLAST = block("dirty_ballast", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(1.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> DIRTIER_BALLAST = block("dirtier_ballast", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> ASPHALT = block("asphalt", Block::new, () -> stone(2.0F).explosionResistance(10.0F));
    public static final DeferredBlock<Block> HIGH_SPEED_BALLAST = block("high_speed_ballast", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F).explosionResistance(10.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> MTC_SPEED_TRANSMITTER = block("mtc_speed_transmitter", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> MTC_TRANSMITTER = block("mtc_transmitter", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> ATO_STOP_TRANSMITTER = block("ato_stop_transmitter", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> MTC_RECEIVER = block("mtc_receiver", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> DESTINATION_RECEIVER = block("destination_receiver", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> PDM_RADIO = block("pdm_radio", Block::new, () -> stone(3.5F));
    public static final DeferredBlock<Block> COPPER_ORE = block("copper_ore", Block::new, () -> stone(3.0F).explosionResistance(5.0F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> OIL_SANDS = block("oil_sands", Block::new, () -> stone(3.0F).explosionResistance(5.0F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> PETROLEUM_ORE = block("petroleum_ore", Block::new, () -> stone(3.0F).explosionResistance(5.0F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BALLAST = block("ballast", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F).sound(SoundType.GRAVEL));
    public static final DeferredBlock<SlabBlock> ASPHALT_SLAB = block("asphalt_slab", SlabBlock::new, () -> stone(2.0F).explosionResistance(10.0F));
    public static final DeferredBlock<StairBlock> ASPHALT_STAIRS = block("asphalt_stairs", properties -> new StairBlock(ASPHALT.get().defaultBlockState(), properties), () -> stone(2.0F).explosionResistance(10.0F));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StructureBlockEntity>> STRUCTURE =
            BlockEntityRegistry.TYPES.register("structure", () -> new BlockEntityType<>(StructureBlockEntity::new, Set.of(
                    SIGNAL.get(), STOPPER.get(), EMBEDDED_STOPPER.get(), AMERICAN_STOPPER.get(), LANTERN.get(), SWITCH_STAND.get(), WIG_WAG.get(),
                    BRIDGE_PILLAR.get(), MILW_SWITCH_STAND.get(), AUTO_SWITCH_STAND.get(), OWO_SWITCH_STAND.get(), CIRCLE_SWITCH_STAND.get(),
                    OWO_YARD_SWITCH_STAND.get(), OVERHEAD_WIRE.get(), OVERHEAD_WIRE_DOUBLE.get(), SPANISH_SIGNAL.get(), K_SIGNAL.get(),
                    METRO_MADRID_POLE.get(), SPEED_SIGN.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeneratorBlockEntity>> GENERATOR =
            BlockEntityRegistry.TYPES.register("generator", () -> new BlockEntityType<>(
                    (pos, state) -> state.getBlock() == DIESEL_GENERATOR.get() ? new DieselGeneratorBlockEntity(pos, state)
                            : new GeneratorBlockEntity(state.getBlock() == WIND_MILL.get() ? GeneratorBlockEntity.Kind.WIND_MILL : GeneratorBlockEntity.Kind.WATER_WHEEL, pos, state),
                    Set.of(WATER_WHEEL.get(), WIND_MILL.get(), DIESEL_GENERATOR.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ContainerBlockEntity>> CONTAINER =
            BlockEntityRegistry.TYPES.register("container", () -> new BlockEntityType<>(ContainerBlockEntity::new, Set.of(FORTY_FOOT_CONTAINER.get())));

    public static final DeferredHolder<MenuType<?>, MenuType<DieselGeneratorMenu>> DIESEL_GENERATOR_MENU =
            MenuRegistry.TYPES.register("diesel_generator", () -> net.neoforged.neoforge.common.extensions.IMenuTypeExtension.create(
                    (IContainerFactory<DieselGeneratorMenu>) (containerId, inventory, buffer) -> {
                        if (inventory.player.level().getBlockEntity(buffer.readBlockPos()) instanceof DieselGeneratorBlockEntity generator) {
                            return new DieselGeneratorMenu(containerId, inventory, generator);
                        }
                        return null;
                    }));

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Energy.BLOCK, GENERATOR.get(), (generator, side) -> generator.handler());
    }

    public static Block block(String name) {
        return BLOCKS.get(name).get();
    }

    public static void touch() {}

    private StructureRegistry() {}
}
