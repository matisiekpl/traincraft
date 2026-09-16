package traincraft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

/** CE's 44 achievements, each under a parent that exists, with an icon, items and titles the mod has. */
class AdvancementCatalogTest {

    private static final Path ADVANCEMENTS = TestPaths.mainResource("data/tc/advancement");

    private static Map<Path, JsonObject> advancements() throws IOException {
        Map<Path, JsonObject> result = new java.util.TreeMap<>();
        try (Stream<Path> files = Files.walk(ADVANCEMENTS)) {
            for (Path path : files.filter(path -> path.toString().endsWith(".json")).toList()) {
                result.put(path, new Gson().fromJson(Files.readString(path), JsonObject.class));
            }
        }
        return result;
    }

    @Test
    void everyAdvancementResolves() throws IOException {
        Set<String> items = TestItems.ownItems();
        Map<?, ?> lang = new Gson().fromJson(Files.readString(TestPaths.mainResource("assets/tc/lang/en_us.json")), Map.class);
        Map<Path, JsonObject> advancements = advancements();
        Set<String> ids = new HashSet<>();
        advancements.keySet().forEach(path -> ids.add("tc:" + path.getFileName().toString().replace(".json", "")));
        List<String> problems = new ArrayList<>();
        for (Map.Entry<Path, JsonObject> entry : advancements.entrySet()) {
            Path path = entry.getKey();
            JsonObject advancement = entry.getValue();
            String id = "tc:" + path.getFileName().toString().replace(".json", "");
            if (id.equals("tc:root") != !advancement.has("parent")) {
                problems.add(path + ": only the root has no parent");
            }
            if (advancement.has("parent") && !ids.contains(advancement.get("parent").getAsString())) {
                problems.add(path + ": parent " + advancement.get("parent") + " missing");
            }
            JsonObject display = advancement.getAsJsonObject("display");
            String icon = display.getAsJsonObject("icon").get("id").getAsString();
            if (icon.startsWith("tc:") && !items.contains(icon.substring(3))) {
                problems.add(path + ": icon " + icon + " is not registered");
            }
            for (String text : List.of("title", "description")) {
                String key = display.getAsJsonObject(text).get("translate").getAsString();
                if (!lang.containsKey(key)) {
                    problems.add(path + ": no lang entry " + key);
                }
            }
            JsonObject criteria = advancement.getAsJsonObject("criteria");
            for (Map.Entry<String, JsonElement> criterion : criteria.entrySet()) {
                JsonObject conditions = criterion.getValue().getAsJsonObject().getAsJsonObject("conditions");
                if (conditions == null) {
                    continue;
                }
                for (JsonElement predicate : conditions.getAsJsonArray("items")) {
                    String item = predicate.getAsJsonObject().get("items").getAsString();
                    if (item.startsWith("tc:") && !items.contains(item.substring(3))) {
                        problems.add(path + ": " + criterion.getKey() + " wants " + item + " which is not registered");
                    }
                }
            }
            Set<String> required = new HashSet<>();
            advancement.getAsJsonArray("requirements").forEach(group -> group.getAsJsonArray().forEach(name -> required.add(name.getAsString())));
            if (!required.equals(criteria.keySet())) {
                problems.add(path + ": requirements " + required + " and criteria " + criteria.keySet() + " differ");
            }
        }
        assertEquals(List.of(), problems);
        assertEquals(45, advancements.size());
        assertTrue(ids.containsAll(List.of("tc:root", "tc:train_wb", "tc:open_hearth", "tc:distilation_tower", "tc:engineer", "tc:assembly_table")));
    }
}
