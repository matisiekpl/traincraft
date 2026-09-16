package traincraft.vehicle.definition;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/** Extent of the rendered model in vehicle space: X along the track, Y up, Z across. */
public record VehicleBounds(double back, double front, double bottom, double top, double width) {
    private static final String RESOURCE = "/data/tc/vehicle_bounds.json";
    private static final Map<String, VehicleBounds> BY_NAME = load();

    public static VehicleBounds get(String entityName) {
        return BY_NAME.getOrDefault(entityName, new VehicleBounds(-1.5, 1.5, -0.65, 1.33, 1.0));
    }

    private static Map<String, VehicleBounds> load() {
        try (var stream = VehicleBounds.class.getResourceAsStream(RESOURCE)) {
            if (stream == null)
                throw new IllegalStateException("Missing vehicle bounds: " + RESOURCE);
            return new Gson().fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), new TypeToken<Map<String, VehicleBounds>>() {}.getType());
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Cannot load vehicle bounds", exception);
        }
    }
}
