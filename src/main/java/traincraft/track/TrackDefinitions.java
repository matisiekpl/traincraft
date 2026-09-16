package traincraft.track;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.jspecify.annotations.Nullable;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Placement, item and rendering metadata are joined by the track's stable type identifier. */
public final class TrackDefinitions {
    public record MeshPart(
            String mesh, String texture, int quarterTurns, float x, float y, float z) {
        public MeshPart {
            Objects.requireNonNull(mesh);
            Objects.requireNonNull(texture);
            TrackOrientation.fromIndex(quarterTurns);
            if (!Float.isFinite(x) || !Float.isFinite(y) || !Float.isFinite(z))
                throw new IllegalArgumentException("Invalid model offset");
        }
    }

    public record ItemDefinition(String id, String texture, String displayName) {
        public ItemDefinition {
            if (id == null || !id.matches("[a-z0-9_]+") || texture == null || displayName == null) {
                throw new IllegalArgumentException("Invalid track item definition");
            }
        }
    }

    public record Definition(List<List<MeshPart>> variants, @Nullable ItemDefinition item) {
        public Definition {
            variants = variants.stream().map(List::copyOf).toList();
            if (variants.size() != 8 || variants.stream().anyMatch(List::isEmpty)) {
                throw new IllegalArgumentException(
                        "A track needs four orientations for each switch state");
            }
        }
    }

    private static final Map<TrackType, Definition> ALL = load();

    private TrackDefinitions() {}

    public static Map<TrackType, Definition> all() {
        return ALL;
    }

    private static Map<TrackType, Definition> load() {
        try (var stream =
                TrackDefinitions.class.getResourceAsStream("/data/tc/track_definitions.json")) {
            if (stream == null) throw new IllegalStateException("Missing track definitions");
            var root =
                    JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                            .getAsJsonObject();
            Map<TrackType, Definition> definitions = new LinkedHashMap<>();
            var gson = new Gson();
            var itemIds = new HashSet<String>();
            for (var entry : root.entrySet()) {
                Definition definition = gson.fromJson(entry.getValue(), Definition.class);
                if (definition.item() != null && !itemIds.add(definition.item().id())) {
                    throw new IllegalArgumentException(
                            "Duplicate track item: " + definition.item().id());
                }
                definitions.put(TrackType.valueOf(entry.getKey()), definition);
            }
            for (var entry : definitions.entrySet()) {
                if (entry.getValue().item() == null) continue;
                for (int facing = 0; facing < 4; facing++) {
                    TrackPlan plan = TrackPlacementPlanner.plan(entry.getKey(), facing);
                    for (Placement placement : plan.placements()) {
                        if (!placement.gag()
                                && placement.hasModel()
                                && !definitions.containsKey(placement.type())) {
                            throw new IllegalArgumentException(
                                    "Missing model for " + placement.type());
                        }
                    }
                }
            }
            return Collections.unmodifiableMap(definitions);
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Cannot load track definitions", exception);
        }
    }
}
