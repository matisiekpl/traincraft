package traincraft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.gson.Gson;

/** The item and tag ids the mod registers, read from the sources, for tests that check data files. */
public final class TestItems {

    private static final Pattern REGISTER = Pattern.compile("register\\(\\s*\"([a-z0-9_]+)\"");
    private static final Pattern PLAIN = Pattern.compile("\"([a-z0-9_]+)\"");

    private TestItems() {}

    public static Set<String> ownItems() throws IOException {
        Set<String> items = new HashSet<>();
        Path java = TestPaths.projectDir().resolve("src/main/java/traincraft");
        for (String file : List.of("bootstrap/ItemRegistry.java", "bootstrap/SoundRegistry.java", "production/ProductionRegistry.java", "production/MachineKind.java",
                "item/TraincraftItems.java", "structure/StructureRegistry.java", "structure/FluidRegistry.java")) {
            if (file.endsWith("ItemRegistry.java")) {
                Matcher stock = Pattern.compile("rollingStock\\(\\s*\"([a-z0-9_]+)\"").matcher(Files.readString(java.resolve(file)));
                while (stock.find()) {
                    items.add(stock.group(1));
                }
            }
            String source = Files.readString(java.resolve(file));
            Matcher matcher = REGISTER.matcher(source);
            while (matcher.find()) {
                items.add(matcher.group(1));
            }
            if (file.endsWith("ProductionRegistry.java") || file.endsWith("TraincraftItems.java") || file.endsWith("StructureRegistry.java")) {
                Matcher plain = PLAIN.matcher(source);
                while (plain.find()) {
                    items.add(plain.group(1));
                }
            }
        }
        Map<?, ?> tracks = new Gson().fromJson(Files.readString(TestPaths.mainResource("").resolve("data/tc/track_definitions.json")), Map.class);
        for (Object definition : tracks.values()) {
            Object item = ((Map<?, ?>) definition).get("item");
            if (item instanceof Map<?, ?> map) {
                items.add((String) map.get("id"));
            }
        }
        for (String kind : List.of("assembly_table_i", "assembly_table_ii", "assembly_table_iii", "train_workbench", "open_hearth_furnace", "distillation_tower",
                "diesel_bucket", "refined_fuel_bucket")) {
            items.add(kind);
        }
        return items;
    }

    public static Set<String> ownTags() throws IOException {
        Set<String> tags = new HashSet<>();
        Path data = TestPaths.mainResource("").resolve("data");
        try (Stream<Path> files = Files.walk(data)) {
            for (Path path : files.filter(p -> p.toString().contains("/tags/item/") && p.toString().endsWith(".json")).toList()) {
                Path relative = data.relativize(path);
                String namespace = relative.getName(0).toString();
                String tag = relative.subpath(3, relative.getNameCount()).toString().replace(".json", "");
                tags.add(namespace + ":" + tag);
            }
        }
        return tags;
    }

}
