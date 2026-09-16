package traincraft.production;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.bootstrap.BlockRegistry;
import traincraft.bootstrap.ItemRegistry;

public final class ProductionRegistry {
    public static final Map<MachineKind, DeferredHolder<net.minecraft.world.level.block.Block, MachineBlock>> BLOCKS = new EnumMap<>(MachineKind.class);
    public static final Map<String, DeferredHolder<Item, Item>> ITEMS = new LinkedHashMap<>();
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, "tc");
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, "tc");
    public static final DeferredHolder<RecipeType<?>, RecipeType<MachineRecipe>> RECIPE_TYPE = RECIPE_TYPES.register("machine", () -> new RecipeType<>() {});
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MachineRecipe>> SERIALIZER = SERIALIZERS.register("machine", () -> new RecipeSerializer<>(MachineRecipe.CODEC, ByteBufCodecs.fromCodecWithRegistries(MachineRecipe.CODEC.codec())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MachineBlockEntity>> MACHINE;
    static {
        for (MachineKind kind : MachineKind.values()) {
            var block = BlockRegistry.BLOCKS.registerBlock(kind.id, properties -> new MachineBlock(kind, properties),
                    () -> net.minecraft.world.level.block.state.BlockBehaviour.Properties.of().strength(3.5F).noOcclusion());
            BLOCKS.put(kind, block);
            ITEMS.put(kind.id, ItemRegistry.ITEMS.register(kind.id, id -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id)))));
        }
        for (String name : new String[] {"steel_ingot", "coal_dust", "graphite", "raw_plastic", "fine_copper_wire", "electronic_circuit", "controls", "bogie", "steel_frame", "steel_cab", "transformer", "electric_motor", "firebox", "diesel", "refined_fuel", "empty_canister", "steel_chimney", "piston", "camshaft", "cylinder", "diesel_engine", "generator", "transmission", "boiler", "iron_boiler", "iron_firebox", "iron_chimney", "iron_bogie", "iron_frame", "iron_cab", "wooden_bogie", "wooden_frame", "wooden_cab", "seats"}) {
            ITEMS.put(name, ItemRegistry.ITEMS.register(name, id -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)))));
        }
        MACHINE = BlockEntityRegistry.TYPES.register("machine", () -> new BlockEntityType<>(MachineBlockEntity::new,
                Set.copyOf(BLOCKS.values().stream().map(DeferredHolder::get).toList())));
    }
    public static Item item(String id) { return ITEMS.get(id).get(); }
    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
        SERIALIZERS.register(bus);
    }
    private ProductionRegistry() {}
}
