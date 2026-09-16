package traincraft.client.render.obj;

import net.minecraft.resources.Identifier;

import traincraft.track.TrackDefinitions;
import traincraft.track.TrackOrientation;
import traincraft.track.TrackType;

import java.util.*;

/** Immutable render variants prepared from the track catalog during resource reload. */
public final class TrackMeshRegistry {
    public record Part(ObjMesh mesh, Identifier texture) {}

    private static volatile Map<TrackType, List<List<Part>>> baked = Map.of();

    private TrackMeshRegistry() {}

    public static List<Part> get(TrackType type, int facing, boolean switchActive) {
        TrackOrientation.fromIndex(facing);
        var variants = type == null ? null : baked.get(type);
        return variants == null ? List.of() : variants.get(facing + (switchActive ? 4 : 0));
    }

    static void bake(ObjMeshManager meshes) {
        Map<TrackType, List<List<Part>>> result = new EnumMap<>(TrackType.class);
        for (var entry : TrackDefinitions.all().entrySet()) {
            List<List<Part>> variants = new ArrayList<>();
            for (var variant : entry.getValue().variants()) {
                List<Part> parts = new ArrayList<>();
                for (var definition : variant) {
                    ObjMesh mesh = meshes.get(definition.mesh());
                    if (mesh == null)
                        throw new IllegalStateException("Missing track mesh: " + definition.mesh());
                    parts.add(
                            new Part(
                                    mesh.transformed(
                                            definition.quarterTurns(),
                                            definition.x(),
                                            definition.y(),
                                            definition.z()),
                                    Identifier.parse(definition.texture())));
                }
                variants.add(List.copyOf(parts));
            }
            result.put(entry.getKey(), List.copyOf(variants));
        }
        baked = Map.copyOf(result);
    }
}
