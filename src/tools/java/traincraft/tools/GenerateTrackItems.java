package traincraft.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/** Generates client item resources from the same catalog used for runtime registration. */
public final class GenerateTrackItems {
    private GenerateTrackItems() {}

    public static void run() throws IOException {
        Path resources = Repo.modDir().resolve("src/main/resources");
        var catalog =
                Json.parseObject(
                        Files.readString(resources.resolve("data/tc/track_definitions.json")));
        Path language = resources.resolve("assets/tc/lang/en_us.json");
        Map<String, Object> names = new TreeMap<>(Json.parseObject(Files.readString(language)));
        Map<Path, String> outputs = new java.util.LinkedHashMap<>();
        for (var value : catalog.values()) {
            var definition = Json.asObject(value);
            var item = Json.asObject(definition.get("item"));
            if (item == null) continue;
            String id = (String) item.get("id");
            String texture = (String) item.get("texture");
            if (!id.matches("[a-z0-9_]+") || !texture.matches("tc:[a-z0-9_/]+")) {
                throw new IllegalArgumentException("Invalid track item definition: " + id);
            }
            if (!Files.isRegularFile(
                    resources.resolve("assets/tc/textures/" + texture.substring(3) + ".png"))) {
                throw new IllegalArgumentException("Missing track icon: " + texture);
            }
            outputs.put(
                    resources.resolve("assets/tc/models/item/" + id + ".json"),
                    Json.write(
                            Map.of(
                                    "parent",
                                    "minecraft:item/generated",
                                    "textures",
                                    Map.of("layer0", texture))));
            outputs.put(
                    resources.resolve("assets/tc/items/" + id + ".json"),
                    Json.write(
                            Map.of(
                                    "model",
                                    Map.of("type", "minecraft:model", "model", "tc:item/" + id))));
            names.put("item.tc." + id, item.get("displayName"));
        }
        outputs.put(language, Json.write(names));
        for (var output : outputs.entrySet()) Files.writeString(output.getKey(), output.getValue());
        System.out.println("Generated " + outputs.size() + " resource files");
    }
}
