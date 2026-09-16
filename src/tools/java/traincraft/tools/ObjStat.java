package traincraft.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Inventories every Wavefront OBJ the mod ships and writes the manifest that {@code ObjParserTest}
 * asserts against.
 *
 * <p>Two independent readers of the same files: this one counts, the runtime {@code ObjParser}
 * builds meshes. If the runtime parser starts dropping faces or mis-resolving indices, its counts
 * stop matching this manifest and the test fails -- which is the point, because a silent parsing
 * regression would otherwise surface much later as a mysterious modelling bug.
 *
 * <p>It also records the two facts ADR-002 rests on: whether any {@code .mtl} exists, and how many
 * models use UVs outside the unit square (which a texture atlas cannot represent).
 */
public final class ObjStat {

    private ObjStat() {}

    public static int run() throws IOException {
        Path models = Repo.modDir().resolve("src/main/resources/assets/tc/models");
        Path out = Repo.modDir().resolve("src/test/resources/obj-manifest.json");

        List<Path> objs = new ArrayList<>();
        List<String> mtls = new ArrayList<>();
        try (var files = Files.list(models)) {
            files.sorted()
                    .forEach(
                            p -> {
                                String name = p.getFileName().toString();
                                if (name.endsWith(".obj")) {
                                    objs.add(p);
                                } else if (name.endsWith(".mtl")) {
                                    mtls.add(name);
                                }
                            });
        }
        if (objs.isEmpty()) {
            System.err.println("objstat: no .obj files under " + models);
            return 1;
        }

        List<Map<String, Object>> entries = new ArrayList<>();
        List<String> withMtllib = new ArrayList<>();
        List<String> withUsemtl = new ArrayList<>();
        List<String> uvOutside = new ArrayList<>();
        List<String> negativeIndices = new ArrayList<>();
        List<String> missingNormals = new ArrayList<>();
        List<String> withNgons = new ArrayList<>();
        long totalTriangles = 0;
        int maxFaceSize = 0;

        for (Path obj : objs) {
            Map<String, Object> entry = inspect(obj);
            entries.add(entry);
            String name = (String) entry.get("file");
            if (!((List<?>) entry.get("mtllib")).isEmpty()) {
                withMtllib.add(name);
            }
            if (!((List<?>) entry.get("usemtl")).isEmpty()) {
                withUsemtl.add(name);
            }
            if (Boolean.TRUE.equals(entry.get("uv_outside_unit_square"))) {
                uvOutside.add(name);
            }
            if (Boolean.TRUE.equals(entry.get("negative_indices"))) {
                negativeIndices.add(name);
            }
            if (((Number) entry.get("normals")).intValue() == 0) {
                missingNormals.add(name);
            }
            @SuppressWarnings("unchecked")
            Map<String, Integer> faceSizes = (Map<String, Integer>) entry.get("face_sizes");
            for (String key : faceSizes.keySet()) {
                int size = Integer.parseInt(key);
                maxFaceSize = Math.max(maxFaceSize, size);
                if (size > 4) {
                    withNgons.add(name);
                }
            }
            totalTriangles += ((Number) entry.get("triangles")).longValue();
        }

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("obj_count", entries.size());
        summary.put("mtl_count", mtls.size());
        summary.put("files_referencing_mtllib", withMtllib);
        summary.put("files_with_usemtl", withUsemtl);
        summary.put("files_with_uvs_outside_unit_square", uvOutside);
        summary.put("files_with_negative_indices", negativeIndices);
        summary.put("files_missing_normals", missingNormals);
        summary.put("files_with_ngons", new ArrayList<>(new TreeSet<>(withNgons)));
        summary.put("total_triangles", totalTriangles);
        summary.put("max_face_size", maxFaceSize);

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("summary", summary);
        root.put("models", entries);

        Files.createDirectories(out.getParent());
        Files.writeString(out, Json.write(root), StandardCharsets.UTF_8);

        System.out.println("objstat: wrote " + out);
        System.out.println("  obj_count=" + entries.size() + " mtl_count=" + mtls.size());
        System.out.println(
                "  uvs outside [0,1]: "
                        + uvOutside.size()
                        + " of "
                        + entries.size()
                        + "  (this is what rules out the atlas-based OBJ loader, ADR-002)");
        System.out.println(
                "  missing normals: "
                        + missingNormals.size()
                        + "  n-gons: "
                        + withNgons.size()
                        + "  max face size: "
                        + maxFaceSize);
        System.out.println("  total triangles: " + totalTriangles);
        return 0;
    }

    private static Map<String, Object> inspect(Path path) throws IOException {
        int positions = 0;
        int uvs = 0;
        int normals = 0;
        int faces = 0;
        int quads = 0;
        long triangles = 0;
        boolean negative = false;
        int facesWithoutUv = 0;
        int facesWithoutNormal = 0;
        double minU = Double.MAX_VALUE;
        double minV = Double.MAX_VALUE;
        double maxU = -Double.MAX_VALUE;
        double maxV = -Double.MAX_VALUE;
        Map<String, Integer> faceSizes = new TreeMap<>();
        TreeSet<String> usemtl = new TreeSet<>();
        TreeSet<String> mtllib = new TreeSet<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.charAt(0) == '#') {
                    continue;
                }
                int space = line.indexOf(' ');
                if (space < 0) {
                    continue;
                }
                String head = line.substring(0, space);
                String rest = line.substring(space + 1).trim();
                switch (head) {
                    case "v" -> positions++;
                    case "vn" -> normals++;
                    case "vt" -> {
                        uvs++;
                        String[] parts = rest.split("\\s+");
                        double u = Double.parseDouble(parts[0]);
                        double v = parts.length > 1 ? Double.parseDouble(parts[1]) : 0;
                        minU = Math.min(minU, u);
                        maxU = Math.max(maxU, u);
                        minV = Math.min(minV, v);
                        maxV = Math.max(maxV, v);
                    }
                    case "f" -> {
                        String[] verts = rest.split("\\s+");
                        faces++;
                        faceSizes.merge(String.valueOf(verts.length), 1, Integer::sum);
                        if (verts.length >= 3) {
                            triangles += verts.length - 2;
                        }
                        // What the runtime parser will emit: a quad or a triangle is one entry in
                        // a QUADS buffer, an n-gon fans out into n-2. Recording it here is what
                        // lets the test compare like with like -- counting f lines instead made
                        // the one file with n-gons look as though the parser were dropping faces.
                        quads += verts.length <= 4 ? 1 : verts.length - 2;
                        boolean hasUv = true;
                        boolean hasNormal = true;
                        for (String token : verts) {
                            String[] bits = token.split("/", -1);
                            for (String bit : bits) {
                                if (bit.startsWith("-")) {
                                    negative = true;
                                }
                            }
                            if (bits.length < 2 || bits[1].isEmpty()) {
                                hasUv = false;
                            }
                            if (bits.length < 3 || bits[2].isEmpty()) {
                                hasNormal = false;
                            }
                        }
                        if (!hasUv) {
                            facesWithoutUv++;
                        }
                        if (!hasNormal) {
                            facesWithoutNormal++;
                        }
                    }
                    case "usemtl" -> usemtl.add(rest);
                    case "mtllib" -> mtllib.add(rest);
                    default -> {}
                }
            }
        }

        boolean outside =
                uvs > 0 && (minU < -1e-6 || minV < -1e-6 || maxU > 1 + 1e-6 || maxV > 1 + 1e-6);

        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("file", path.getFileName().toString());
        entry.put("bytes", Files.size(path));
        entry.put("positions", positions);
        entry.put("uvs", uvs);
        entry.put("normals", normals);
        entry.put("faces", faces);
        entry.put("quads", quads);
        entry.put("triangles", triangles);
        entry.put("face_sizes", faceSizes);
        entry.put("negative_indices", negative);
        entry.put("faces_without_uv", facesWithoutUv);
        entry.put("faces_without_normal", facesWithoutNormal);
        entry.put("uv_min", uvs == 0 ? null : List.of(minU, minV));
        entry.put("uv_max", uvs == 0 ? null : List.of(maxU, maxV));
        entry.put("uv_outside_unit_square", outside);
        entry.put("usemtl", new ArrayList<>(usemtl));
        entry.put("mtllib", new ArrayList<>(mtllib));
        return entry;
    }
}
