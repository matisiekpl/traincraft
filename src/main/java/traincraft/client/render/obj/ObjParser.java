package traincraft.client.render.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ObjParser {

    private ObjParser() {}

    public static ObjMesh parse(InputStream in) throws IOException {
        List<float[]> positions = new ArrayList<>();
        List<float[]> uvs = new ArrayList<>();
        List<float[]> normals = new ArrayList<>();

        // Face vertex slots, flattened. Grown as faces are read, then compacted.
        List<float[]> outPos = new ArrayList<>();
        List<float[]> outUv = new ArrayList<>();
        List<float[]> outNorm = new ArrayList<>();

        try (BufferedReader r =
                new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.charAt(0) == '#') {
                    continue;
                }
                int sp = line.indexOf(' ');
                if (sp < 0) {
                    continue;
                }
                String head = line.substring(0, sp);
                String rest = line.substring(sp + 1).trim();

                switch (head) {
                    case "v" -> positions.add(floats(rest, 3));
                    case "vt" -> uvs.add(flipV(floats(rest, 2)));
                    case "vn" -> normals.add(floats(rest, 3));
                    case "f" -> emitFace(rest, positions, uvs, normals, outPos, outUv, outNorm);
                    default -> {
                        /* o, g, s, usemtl, mtllib: intentionally ignored */
                    }
                }
            }
        }

        int slots = outPos.size();
        float[] p = new float[slots * 3];
        float[] u = new float[slots * 2];
        float[] n = new float[slots * 3];
        for (int i = 0; i < slots; i++) {
            System.arraycopy(outPos.get(i), 0, p, i * 3, 3);
            System.arraycopy(outUv.get(i), 0, u, i * 2, 2);
            System.arraycopy(outNorm.get(i), 0, n, i * 3, 3);
        }
        return new ObjMesh(p, u, n, slots / ObjMesh.VERTS_PER_FACE);
    }

    private static void emitFace(
            String rest,
            List<float[]> positions,
            List<float[]> uvs,
            List<float[]> normals,
            List<float[]> outPos,
            List<float[]> outUv,
            List<float[]> outNorm) {
        String[] toks = rest.split("\s+");
        int n = toks.length;
        if (n < 3) {
            return;
        }

        float[][] fp = new float[n][];
        float[][] fu = new float[n][];
        float[][] fn = new float[n][];

        for (int i = 0; i < n; i++) {
            String[] bits = toks[i].split("/", -1);
            fp[i] = positions.get(resolve(bits[0], positions.size()));
            fu[i] =
                    (bits.length > 1 && !bits[1].isEmpty())
                            ? uvs.get(resolve(bits[1], uvs.size()))
                            : new float[] {0f, 0f};
            if (bits.length > 2 && !bits[2].isEmpty()) {
                float[] supplied = normals.get(resolve(bits[2], normals.size()));

                if (supplied[0] != 0f || supplied[1] != 0f || supplied[2] != 0f) {
                    fn[i] = supplied;
                }
            }
        }

        // Always computed, not only when the file supplies no normals at all: two Community
        // Edition models mix usable normals with zero-length ones on the same face, so the
        // fallback has to be available per corner rather than per face.
        float[] flat = faceNormal(fp[0], fp[1], fp[2]);

        if (n == 4) {
            push(outPos, outUv, outNorm, fp, fu, fn, flat, 0, 1, 2, 3);
        } else if (n == 3) {
            // A triangle enters a QUADS buffer by repeating its last vertex.
            push(outPos, outUv, outNorm, fp, fu, fn, flat, 0, 1, 2, 2);
        } else {

            for (int t = 0; t + 2 < n; t++) {
                push(outPos, outUv, outNorm, fp, fu, fn, flat, 0, t + 1, t + 2, t + 2);
            }
        }
    }

    private static float[] flipV(float[] uv) {
        uv[1] = 1.0F - uv[1];
        return uv;
    }

    private static void push(
            List<float[]> outPos,
            List<float[]> outUv,
            List<float[]> outNorm,
            float[][] fp,
            float[][] fu,
            float[][] fn,
            float[] flat,
            int a,
            int b,
            int c,
            int d) {
        for (int i : new int[] {a, b, c, d}) {
            outPos.add(fp[i]);
            outUv.add(fu[i]);
            outNorm.add(fn[i] != null ? fn[i] : flat);
        }
    }

    /** Resolves a 1-based or negative OBJ index into a 0-based list index. */
    private static int resolve(String token, int size) {
        int i = Integer.parseInt(token);
        return i > 0 ? i - 1 : size + i;
    }

    private static float[] faceNormal(float[] a, float[] b, float[] c) {
        float ux = b[0] - a[0], uy = b[1] - a[1], uz = b[2] - a[2];
        float vx = c[0] - a[0], vy = c[1] - a[1], vz = c[2] - a[2];
        float nx = uy * vz - uz * vy;
        float ny = uz * vx - ux * vz;
        float nz = ux * vy - uy * vx;
        float len = (float) Math.sqrt(nx * nx + ny * ny + nz * nz);
        if (len < 1.0e-8f) {
            return new float[] {0f, 1f, 0f};
        }
        return new float[] {nx / len, ny / len, nz / len};
    }

    private static float[] floats(String s, int count) {
        String[] parts = s.split("\s+");
        float[] out = new float[count];
        for (int i = 0; i < count; i++) {
            out[i] = i < parts.length ? Float.parseFloat(parts[i]) : 0f;
        }
        return out;
    }
}
