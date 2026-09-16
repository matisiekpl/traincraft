package traincraft.track;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class TrackPlanCatalog {

    private static final String RESOURCE = "/data/tc/track_placements.json";

    private static final class Loaded {
        private static final Map<String, TrackPlan> PLANS = loadResource();
    }

    private TrackPlanCatalog() {}

    public static @Nullable TrackPlan get(TrackType type, int facing) {
        TrackOrientation.fromIndex(facing);
        return Loaded.PLANS.get(type.name() + "|" + facing);
    }

    public static boolean has(TrackType type, int facing) {
        return get(type, facing) != null;
    }

    public static int entryCount() {
        return Loaded.PLANS.size();
    }

    private static Map<String, TrackPlan> loadResource() {
        try (InputStream stream = TrackPlanCatalog.class.getResourceAsStream(RESOURCE)) {
            if (stream == null)
                throw new IllegalStateException("Missing track definitions: " + RESOURCE);
            return read(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot load track definitions", exception);
        }
    }

    /** Validates the complete document before publishing an immutable catalog. */
    public static Map<String, TrackPlan> read(java.io.Reader reader) {
        JsonObject root = new Gson().fromJson(reader, JsonObject.class);
        JsonObject entries = root.getAsJsonObject("placements");
        if (entries == null || entries.size() == 0)
            throw new IllegalArgumentException("Empty track catalog");
        Map<String, TrackPlan> result = new java.util.LinkedHashMap<>();
        for (var entry : entries.entrySet()) {
            try {
                result.put(
                        entry.getKey(),
                        readPlan(entry.getKey(), entry.getValue().getAsJsonArray()));
            } catch (RuntimeException exception) {
                throw new IllegalArgumentException(
                        "Invalid track definition: " + entry.getKey(), exception);
            }
        }
        return java.util.Collections.unmodifiableMap(result);
    }

    private static @Nullable TrackPlan readPlan(String key, JsonArray blocks) {
        int bar = key.indexOf('|');
        if (bar < 0) throw new IllegalArgumentException("Missing orientation");
        TrackType type = TrackType.valueOf(key.substring(0, bar));
        int facing = Integer.parseInt(key.substring(bar + 1));

        List<Placement> placements = new ArrayList<>(blocks.size());
        for (JsonElement element : blocks) {
            Placement placement = readPlacement(element.getAsJsonObject(), type);
            if (placement != null) {
                placements.add(placement);
            }
        }
        return new TrackPlan(type, facing, placements);
    }

    private static @Nullable Placement readPlacement(JsonObject block, TrackType requested) {
        JsonArray offset = block.getAsJsonArray("offset");
        int dx = offset.get(0).getAsInt();
        int dy = offset.get(1).getAsInt();
        int dz = offset.get(2).getAsInt();
        boolean gag = block.get("gag").getAsBoolean();
        int blockMeta = block.get("blockMeta").getAsInt();

        JsonObject tile = block.getAsJsonObject("tile");
        if (tile == null) {
            return new Placement(
                    dx,
                    dy,
                    dz,
                    requested,
                    blockMeta,
                    blockMeta,
                    gag,
                    true,
                    false,
                    null,
                    Placement.DEFAULT_GAG_HEIGHT,
                    PlacementParameters.NONE);
        }

        // The tile stores the label, which for several types is not the requested type's name --
        // MEDIUM_TURN places MEDIUM_LEFT_TURN, and the two medium switches have swapped labels.
        TrackType placed = TrackType.byLabel(asString(tile, "type"));
        if (placed == null) {
            placed = requested;
        }

        JsonArray centre = tile.getAsJsonArray("centre");
        double r = tile.get("r").getAsDouble();
        double slopeHeight = tile.get("slopeHeight").getAsDouble();
        double slopeLength = tile.get("slopeLength").getAsDouble();
        double slopeAngle = tile.get("slopeAngle").getAsDouble();
        boolean hasCurve =
                r != 0.0
                        || centre.get(0).getAsDouble() != 0.0
                        || centre.get(2).getAsDouble() != 0.0;

        TrackGeometry.Curve curve =
                hasCurve
                        ? new TrackGeometry.Curve(
                                centre.get(0).getAsDouble(),
                                centre.get(1).getAsDouble(),
                                centre.get(2).getAsDouble(),
                                r)
                        : null;
        TrackGeometry.Slope slope =
                slopeLength > 0
                        ? new TrackGeometry.Slope(slopeHeight, slopeLength, slopeAngle)
                        : null;
        PlacementParameters params =
                new PlacementParameters(
                        new TrackGeometry(curve, slope),
                        tile.get("canTypeBeModifiedBySwitch").getAsBoolean());

        Placement.Link link = null;
        if (tile.has("linkedOffset")) {
            JsonArray linked = tile.getAsJsonArray("linkedOffset");
            link =
                    new Placement.Link(
                            linked.get(0).getAsInt(),
                            linked.get(1).getAsInt(),
                            linked.get(2).getAsInt());
        }

        int facing = gag ? blockMeta : tile.get("facingMeta").getAsInt();
        return new Placement(
                dx,
                dy,
                dz,
                placed,
                facing,
                blockMeta,
                gag,
                tile.get("hasModel").getAsBoolean(),
                tile.has("dropsItem") && tile.get("dropsItem").getAsBoolean(),
                link,
                tile.has("bbHeight")
                        ? tile.get("bbHeight").getAsDouble()
                        : Placement.DEFAULT_GAG_HEIGHT,
                params);
    }

    private static @Nullable String asString(JsonObject object, String name) {
        JsonElement element = object.get(name);
        return element == null || element.isJsonNull() ? null : element.getAsString();
    }
}
