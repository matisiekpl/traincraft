package traincraft.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import traincraft.Traincraft;
import traincraft.bootstrap.BlockRegistry;
import traincraft.bootstrap.ItemRegistry;

public final class FluidRegistry {

    public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Traincraft.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Traincraft.MODID);

    public record Entry(DeferredHolder<FluidType, FluidType> type, DeferredHolder<Fluid, FlowingFluid> source, DeferredHolder<Fluid, FlowingFluid> flowing,
                        DeferredBlock<LiquidBlock> block, DeferredHolder<Item, Item> bucket) {}

    public static final Entry DIESEL = fluid("diesel", 300);
    public static final Entry REFINED_FUEL = fluid("refined_fuel", 300);

    private static Entry fluid(String name, int temperature) {
        DeferredHolder<FluidType, FluidType> type = TYPES.register(name, () -> new FluidType(FluidType.Properties.create().temperature(temperature).viscosity(1500).density(900)));
        DeferredHolder<Fluid, FlowingFluid>[] holders = new DeferredHolder[2];
        DeferredBlock<LiquidBlock>[] blocks = new DeferredBlock[1];
        DeferredHolder<Item, Item>[] buckets = new DeferredHolder[1];
        java.util.function.Supplier<BaseFlowingFluid.Properties> properties = () -> new BaseFlowingFluid.Properties(type, holders[0], holders[1]).block(blocks[0]).bucket(buckets[0]);
        holders[0] = FLUIDS.register(name, () -> new BaseFlowingFluid.Source(properties.get()));
        holders[1] = FLUIDS.register("flowing_" + name, () -> new BaseFlowingFluid.Flowing(properties.get()));
        blocks[0] = BlockRegistry.BLOCKS.registerBlock(name, blockProperties -> new LiquidBlock(holders[0].get(), blockProperties),
                () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).replaceable().noCollision().strength(100.0F).liquid().noLootTable());
        buckets[0] = ItemRegistry.ITEMS.register(name + "_bucket", id -> new BucketItem(holders[0].get(),
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, id))));
        return new Entry(type, holders[0], holders[1], blocks[0], buckets[0]);
    }

    public static void register(IEventBus bus) {
        TYPES.register(bus);
        FLUIDS.register(bus);
    }

    private FluidRegistry() {}
}
