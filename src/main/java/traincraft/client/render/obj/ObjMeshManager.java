package traincraft.client.render.obj;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import org.jspecify.annotations.Nullable;

import traincraft.Traincraft;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads every {@code assets/tc/models/*.obj} on resource reload and hands the parsed meshes to
 * {@link TrackMeshRegistry} to be baked into per-facing variants.
 *
 * <p>Parsing happens in {@code prepare}, which the reload framework runs off the render thread;
 * only the (cheap) hand-off happens in {@code apply}. Nothing here allocates per frame.
 */
public final class ObjMeshManager extends SimplePreparableReloadListener<Map<Identifier, ObjMesh>> {

    public static final ObjMeshManager INSTANCE = new ObjMeshManager();

    /** Directory scanned for models, relative to an asset namespace root. */
    private static final String MODEL_DIR = "models";

    private volatile Map<Identifier, ObjMesh> meshes = Map.of();

    private ObjMeshManager() {}

    public @Nullable ObjMesh get(Identifier id) {
        return meshes.get(id);
    }

    /** Looks up by bare file name, e.g. {@code track_normal.obj}. */
    public @Nullable ObjMesh get(String fileName) {
        return meshes.get(
                Identifier.fromNamespaceAndPath(Traincraft.MODID, MODEL_DIR + "/" + fileName));
    }

    public int size() {
        return meshes.size();
    }

    @Override
    protected Map<Identifier, ObjMesh> prepare(ResourceManager manager, ProfilerFiller profiler) {
        Map<Identifier, ObjMesh> loaded = new HashMap<>();
        Map<Identifier, Resource> found =
                manager.listResources(
                        MODEL_DIR,
                        id ->
                                Traincraft.MODID.equals(id.getNamespace())
                                        && id.getPath().endsWith(".obj"));

        for (Map.Entry<Identifier, Resource> entry : found.entrySet()) {
            try (InputStream in = entry.getValue().open()) {
                loaded.put(entry.getKey(), ObjParser.parse(in));
            } catch (IOException | RuntimeException e) {
                // One bad model must not take down the whole reload; the missing mesh shows up
                // as an unrendered track piece, which the capture harness will flag.
                Traincraft.LOGGER.error("Failed to parse OBJ model {}", entry.getKey(), e);
            }
        }
        return Map.copyOf(loaded);
    }

    @Override
    protected void apply(
            Map<Identifier, ObjMesh> prepared, ResourceManager manager, ProfilerFiller profiler) {
        this.meshes = prepared;
        Traincraft.LOGGER.info("Loaded {} OBJ models", prepared.size());
        TrackMeshRegistry.bake(this);
    }
}
