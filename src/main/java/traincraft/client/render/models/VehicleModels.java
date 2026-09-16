package traincraft.client.render.models;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import traincraft.Traincraft;

import java.util.HashMap;
import java.util.Map;

/** Publishes complete model snapshots on resource reload. */
public final class VehicleModels extends SimplePreparableReloadListener<Map<String, VehicleModel>> {
    public static final VehicleModels INSTANCE = new VehicleModels();
    private volatile Map<String, VehicleModel> models = Map.of();

    private VehicleModels() {}

    public VehicleModel find(String name) {
        return models.get(name);
    }

    public VehicleModel get(String name) {
        VehicleModel model = models.get(name);
        if (model == null) throw new IllegalStateException("Missing vehicle model: " + name);
        return model;
    }

    @Override
    protected Map<String, VehicleModel> prepare(ResourceManager manager, ProfilerFiller profiler) {
        Map<String, VehicleModel> result = new HashMap<>();
        for (String directory : java.util.List.of("vehicle_models", "structure_models")) {
            manager.listResources(directory, id -> Traincraft.MODID.equals(id.getNamespace()) && id.getPath().endsWith(".json") && !id.getPath().endsWith("transforms.json"))
                    .forEach((id, resource) -> {
                        String name = id.getPath().substring(directory.length() + 1, id.getPath().length() - 5);
                        try (var reader = resource.openAsReader()) {
                            result.put(name, VehicleModel.read(reader));
                        } catch (java.io.IOException | RuntimeException exception) {
                            throw new IllegalStateException("Cannot load model " + id, exception);
                        }
                    });
        }
        return Map.copyOf(result);
    }

    @Override
    protected void apply(
            Map<String, VehicleModel> prepared, ResourceManager manager, ProfilerFiller profiler) {
        models = prepared;
    }
}
