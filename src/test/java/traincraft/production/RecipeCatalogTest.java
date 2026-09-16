package traincraft.production;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

import traincraft.TestItems;
import traincraft.TestPaths;

/** Every recipe JSON the mod ships is well formed and names only items and tags that exist. */
class RecipeCatalogTest {

    private static final Set<String> CRAFTING_TYPES = Set.of("minecraft:crafting_shaped", "minecraft:crafting_shapeless", "minecraft:smelting", "tc:machine");

    private static Path resources() {
        return TestPaths.projectDir().resolve("src/main/resources");
    }

    private static List<Path> recipes() throws IOException {
        try (Stream<Path> files = Files.walk(resources().resolve("data/tc/recipe"))) {
            return files.filter(path -> path.toString().endsWith(".json")).sorted().toList();
        }
    }

    private static void collectIngredients(JsonElement element, List<String> into) {
        if (element.isJsonPrimitive()) {
            into.add(element.getAsString());
        } else if (element.isJsonArray()) {
            element.getAsJsonArray().forEach(child -> collectIngredients(child, into));
        }
    }

    @Test
    void everyRecipeResolves() throws IOException {
        Set<String> items = TestItems.ownItems();
        Set<String> tags = TestItems.ownTags();
        List<String> problems = new ArrayList<>();
        int count = 0;
        for (Path path : recipes()) {
            JsonObject recipe = new Gson().fromJson(Files.readString(path), JsonObject.class);
            String type = recipe.get("type").getAsString();
            if (!CRAFTING_TYPES.contains(type)) {
                problems.add(path + ": unknown type " + type);
                continue;
            }
            count++;
            List<String> ingredients = new ArrayList<>();
            JsonObject result = recipe.getAsJsonObject("result");
            String resultId = result.get("id").getAsString();
            if (resultId.startsWith("tc:") && !items.contains(resultId.substring(3))) {
                problems.add(path + ": result " + resultId + " is not registered");
            }
            switch (type) {
                case "minecraft:crafting_shaped" -> {
                    JsonArray pattern = recipe.getAsJsonArray("pattern");
                    JsonObject key = recipe.getAsJsonObject("key");
                    Set<String> used = new HashSet<>();
                    for (JsonElement row : pattern) {
                        for (char symbol : row.getAsString().toCharArray()) {
                            if (symbol != ' ') {
                                used.add(String.valueOf(symbol));
                            }
                        }
                    }
                    if (!used.equals(key.keySet())) {
                        problems.add(path + ": pattern " + used + " and key " + key.keySet() + " differ");
                    }
                    key.entrySet().forEach(entry -> collectIngredients(entry.getValue(), ingredients));
                }
                case "minecraft:crafting_shapeless" -> collectIngredients(recipe.get("ingredients"), ingredients);
                case "minecraft:smelting" -> collectIngredients(recipe.get("ingredient"), ingredients);
                default -> {
                    for (JsonElement input : recipe.getAsJsonArray("inputs")) {
                        collectIngredients(input.getAsJsonObject().get("ingredient"), ingredients);
                    }
                }
            }
            for (String ingredient : ingredients) {
                if (ingredient.startsWith("#")) {
                    String tag = ingredient.substring(1);
                    if (tag.startsWith("tc:") && !tags.contains(tag)) {
                        problems.add(path + ": tag " + ingredient + " is not defined");
                    }
                } else if (ingredient.startsWith("tc:") && !items.contains(ingredient.substring(3))) {
                    problems.add(path + ": ingredient " + ingredient + " is not registered");
                }
            }
        }
        assertTrue(count > 2000, "Expected the fleet and CE recipes, found " + count);
        assertEquals(List.of(), problems);
    }

    @Test
    void everyCommunityEditionResultIsCraftable() throws IOException {
        Set<String> results = new HashSet<>();
        for (Path path : recipes()) {
            JsonObject recipe = new Gson().fromJson(Files.readString(path), JsonObject.class);
            results.add(recipe.getAsJsonObject("result").get("id").getAsString());
        }
        List<String> expected = List.of("tc:assembly_table_i", "tc:assembly_table_ii", "tc:assembly_table_iii", "tc:train_workbench", "tc:distillation_tower",
                "tc:open_hearth_furnace", "tc:lantern", "tc:stopper", "tc:embedded_stopper", "tc:american_stopper", "tc:switch_stand", "tc:wig_wag",
                "tc:water_wheel", "tc:wind_mill", "tc:diesel_generator", "tc:bridge_pillar", "tc:overalls", "tc:jacket", "tc:hat", "tc:recipe_book",
                "tc:chunk_loader_activator", "tc:bolt", "tc:composite_wrench", "tc:zeppelin", "tc:airship", "tc:balloon", "tc:propeller", "tc:steam_engine",
                "minecraft:copper_ingot", "tc:steel_ingot", "tc:asphalt", "tc:asphalt_slab", "tc:asphalt_stairs", "tc:ballast", "tc:speed_sign", "tc:k_signal",
                "tc:spanish_signal", "tc:metro_madrid_pole", "tc:overhead_wire", "tc:overhead_wire_double", "tc:track_small_straight", "tc:loco_steam_alice",
                "tc:loco_steam_br80", "tc:freight_cart_yellow", "tc:loco_diesel_sd40");
        List<String> missing = expected.stream().filter(result -> !results.contains(result)).toList();
        assertEquals(List.of(), missing);
    }
}
