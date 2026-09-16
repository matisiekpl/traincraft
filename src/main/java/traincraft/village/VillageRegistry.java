package traincraft.village;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.production.MachineKind;
import traincraft.production.ProductionRegistry;

/**
 * CE's village train station and its station chief. The station goes into every vanilla village
 * house pool at server start, because a datapack can only replace a pool, not add to one.
 */
@EventBusSubscriber(modid = Traincraft.MODID)
public final class VillageRegistry {

    public static final Identifier STATION = Identifier.fromNamespaceAndPath(Traincraft.MODID, "village/station");
    public static final int STATION_WEIGHT = 2;
    public static final List<String> VILLAGES = List.of("plains", "desert", "savanna", "snowy", "taiga");

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Traincraft.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, Traincraft.MODID);

    public static final ResourceKey<PoiType> STATION_CHIEF_POI = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(Traincraft.MODID, "station_chief"));

    public static final DeferredHolder<PoiType, PoiType> STATION_CHIEF_JOB_SITE = POI_TYPES.register("station_chief",
            () -> new PoiType(Set.copyOf(ProductionRegistry.BLOCKS.get(MachineKind.WORKBENCH).get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> STATION_CHIEF = PROFESSIONS.register("station_chief",
            () -> new VillagerProfession(
                    Component.translatable("entity.tc.villager.station_chief"),
                    poi -> poi.is(STATION_CHIEF_POI),
                    poi -> poi.is(STATION_CHIEF_POI),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    null,
                    Int2ObjectMap.ofEntries(
                            Int2ObjectMap.entry(1, tradeSet(1)),
                            Int2ObjectMap.entry(2, tradeSet(2)),
                            Int2ObjectMap.entry(3, tradeSet(3)),
                            Int2ObjectMap.entry(4, tradeSet(4)),
                            Int2ObjectMap.entry(5, tradeSet(5)))));

    private VillageRegistry() {}

    public static ResourceKey<TradeSet> tradeSet(int level) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(Traincraft.MODID, "station_chief/level_" + level));
    }

    public static void register(IEventBus bus) {
        POI_TYPES.register(bus);
        PROFESSIONS.register(bus);
    }

    @SubscribeEvent
    static void addStations(ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> pools = event.getServer().registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Holder<StructureProcessorList> processors = event.getServer().registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST)
                .getOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, Identifier.withDefaultNamespace("empty")));
        StructurePoolElement station = StructurePoolElement.legacy(STATION.toString(), processors).apply(StructureTemplatePool.Projection.RIGID);
        for (String village : VILLAGES) {
            StructureTemplatePool pool = pools.getValue(Identifier.withDefaultNamespace("village/" + village + "/houses"));
            if (pool == null) {
                continue;
            }
            List<Pair<StructurePoolElement, Integer>> templates = new ArrayList<>(pool.rawTemplates);
            templates.add(Pair.of(station, STATION_WEIGHT));
            pool.rawTemplates = templates;
            for (int i = 0; i < STATION_WEIGHT; i++) {
                pool.templates.add(station);
            }
        }
    }
}
