package traincraft.development.gametest;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.conditions.ICondition;

import traincraft.ConfigCondition;
import traincraft.production.MachineKind;
import traincraft.production.ProductionRegistry;
import traincraft.structure.StructureRegistry;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.village.VillageRegistry;

/** The village station, the station chief, CE's achievements and the config switches. */
final class VillageGameTests {

    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("station_joins_every_village", VillageGameTests::stationJoinsEveryVillage);
        tests.put("station_places_with_its_chief_and_carts", VillageGameTests::stationPlaces);
        tests.put("workbench_is_the_station_chief_job_site", VillageGameTests::jobSite);
        tests.put("every_achievement_is_an_advancement", VillageGameTests::advancements);
        tests.put("config_switches_hold_their_defaults", VillageGameTests::config);
    }

    private static void stationJoinsEveryVillage(GameTestHelper h) {
        var pools = h.getLevel().getServer().registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        for (String village : VillageRegistry.VILLAGES) {
            StructureTemplatePool pool = pools.getValue(Identifier.withDefaultNamespace("village/" + village + "/houses"));
            if (pool == null) h.fail("no " + village + " house pool");
            long stations = pool.getTemplates().stream().filter(pair -> pair.getFirst().toString().contains(VillageRegistry.STATION.toString())).count();
            if (stations != 1) h.fail(village + " houses list the station " + stations + " times");
        }
        h.succeed();
    }

    private static void stationPlaces(GameTestHelper h) {
        StructureTemplate template = h.getLevel().getServer().getStructureManager().get(VillageRegistry.STATION)
                .orElseThrow(() -> new AssertionError("no station template"));
        BlockPos origin = h.absolutePos(new BlockPos(3, 1, 3));
        template.placeInWorld(h.getLevel(), origin, origin, new StructurePlaceSettings(), h.getLevel().getRandom(), 3);
        h.assertBlock(new BlockPos(10, 3, 5), block -> block == ProductionRegistry.BLOCKS.get(MachineKind.WORKBENCH).get(), block -> Component.literal("workbench, not " + block));
        h.assertBlock(new BlockPos(3, 4, 10), block -> block == StructureRegistry.LANTERN.get(), block -> Component.literal("lantern, not " + block));
        AABB station = AABB.encapsulatingFullBlocks(origin, origin.offset(9, 11, 10));
        List<Villager> villagers = h.getLevel().getEntities(EntityTypes.VILLAGER, station, villager -> true);
        if (villagers.size() != 1 || !villagers.getFirst().getVillagerData().profession().is(VillageRegistry.STATION_CHIEF.getKey())) {
            h.fail("one station chief, not " + villagers);
        }
        List<RollingStockEntity> stock = h.getLevel().getEntitiesOfClass(RollingStockEntity.class, station);
        if (stock.size() != 2 || !stock.stream().allMatch(cart -> cart.getOwner().equals("VillagerJoe"))) {
            h.fail("two of VillagerJoe's carts, not " + stock);
        }
        h.succeed();
    }

    private static void jobSite(GameTestHelper h) {
        var poi = PoiTypes.forState(ProductionRegistry.BLOCKS.get(MachineKind.WORKBENCH).get().defaultBlockState());
        if (poi.isEmpty() || !poi.get().is(VillageRegistry.STATION_CHIEF_POI)) h.fail("the workbench is " + poi);
        if (!VillageRegistry.STATION_CHIEF.get().heldJobSite().test(poi.get())) h.fail("the station chief does not work there");
        h.succeed();
    }

    private static void advancements(GameTestHelper h) {
        var manager = h.getLevel().getServer().getAdvancements();
        List<AdvancementHolder> ours = manager.getAllAdvancements().stream().filter(holder -> holder.id().getNamespace().equals("tc")).toList();
        if (ours.size() != 45) h.fail("45 advancements, not " + ours.size());
        for (AdvancementHolder holder : ours) {
            if (holder.value().display().isEmpty()) h.fail(holder.id() + " has no display");
            if (holder.value().parent().isPresent() && manager.tree().get(holder.value().parent().get()) == null) h.fail(holder.id() + " hangs off nothing");
        }
        h.succeed();
    }

    private static void config(GameTestHelper h) {
        if (!new ConfigCondition("DISABLE_TRAIN_WORKBENCH", false).test(ICondition.IContext.EMPTY)) h.fail("the workbench is on by default");
        if (new ConfigCondition("MAKE_MODPACKS_GREAT_AGAIN", true).test(ICondition.IContext.EMPTY)) h.fail("modpack balance is off by default");
        var recipes = h.getLevel().recipeAccess().recipeMap();
        for (String gated : List.of("ce/train_workbench_1", "ce/open_hearth_furnace_1", "ce/coal_dust_1", "production/steel")) {
            if (recipes.byKey(ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("tc", gated))) == null) h.fail(gated + " did not load");
        }
        try {
            new ConfigCondition("NOT_A_SWITCH", true);
            h.fail("an unknown switch was accepted");
        } catch (IllegalArgumentException expected) {
            h.succeed();
        }
    }
}
