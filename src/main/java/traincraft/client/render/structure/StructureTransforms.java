package traincraft.client.render.structure;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import traincraft.Traincraft;

public final class StructureTransforms extends SimplePreparableReloadListener<Map<String, StructureTransforms.Entry>> {

    public static final StructureTransforms INSTANCE = new StructureTransforms();
    private static final Identifier FILE = Identifier.fromNamespaceAndPath(Traincraft.MODID, "structure_models/transforms.json");

    public record Arm(String model, List<Double> pivot) {}

    public record Wheel(String obj, List<List<Object>> base, Integer tint, String spin) {}

    public record Entry(List<List<Object>> base, Map<String, List<List<Object>>> facing, Map<String, String> textures, Map<String, String> models,
                        Map<String, String> obj, List<List<Object>> objOps, Map<String, Arm> arms, Integer tint, String spin, Boolean tinted, Wheel wheel,
                        List<List<Object>> item) {}

    private volatile Map<String, Entry> entries = Map.of();

    private StructureTransforms() {}

    public Entry get(String name) {
        Entry entry = entries.get(name);
        if (entry == null) {
            throw new IllegalStateException("Missing structure transform: " + name);
        }
        return entry;
    }

    @Override
    protected Map<String, Entry> prepare(ResourceManager manager, ProfilerFiller profiler) {
        try (var reader = manager.getResourceOrThrow(FILE).openAsReader()) {
            return new Gson().fromJson(reader, new TypeToken<Map<String, Entry>>() {}.getType());
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Cannot load " + FILE, exception);
        }
    }

    @Override
    protected void apply(Map<String, Entry> prepared, ResourceManager manager, ProfilerFiller profiler) {
        entries = Map.copyOf(prepared);
    }
}
