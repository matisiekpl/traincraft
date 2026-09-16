package traincraft.client.render.obj;

/**
 * An immutable, render-ready mesh parsed from a Wavefront OBJ.
 *
 * <p>Geometry is stored as flat interleaved-friendly arrays rather than an object graph because
 * these meshes are static for the lifetime of a resource pack and are walked once per submitted
 * draw. There is no per-frame allocation anywhere in this class.
 *
 * <p>Faces are stored as <strong>quads</strong>: four vertex slots per face. A triangular face
 * repeats its third vertex in the fourth slot, which is the standard way to feed a triangle into
 * Minecraft's QUADS vertex format. Larger convex faces are fan-triangulated by the parser.
 *
 * <p>Contains no Minecraft types, deliberately: it is unit-testable without a game.
 */
public final class ObjMesh {

    /** Vertex slots per face. */
    public static final int VERTS_PER_FACE = 4;

    /** xyz per vertex slot, length = faceCount * 4 * 3. */
    private final float[] positions;

    /** uv per vertex slot, length = faceCount * 4 * 2. */
    private final float[] uvs;

    /** xyz normal per vertex slot, length = faceCount * 4 * 3. */
    private final float[] normals;

    private final int faceCount;

    ObjMesh(float[] positions, float[] uvs, float[] normals, int faceCount) {
        if (faceCount < 0
                || positions.length != (long) faceCount * VERTS_PER_FACE * 3
                || normals.length != positions.length
                || uvs.length != (long) faceCount * VERTS_PER_FACE * 2) {
            throw new IllegalArgumentException("Inconsistent mesh dimensions");
        }
        this.positions = positions.clone();
        this.uvs = uvs.clone();
        this.normals = normals.clone();
        this.faceCount = faceCount;
    }

    public int faceCount() {
        return faceCount;
    }

    public int vertexSlotCount() {
        return faceCount * VERTS_PER_FACE;
    }

    public float[] positions() {
        return positions.clone();
    }

    public float[] uvs() {
        return uvs.clone();
    }

    public float[] normals() {
        return normals.clone();
    }

    public float posX(int slot) {
        return positions[slot * 3];
    }

    public float posY(int slot) {
        return positions[slot * 3 + 1];
    }

    public float posZ(int slot) {
        return positions[slot * 3 + 2];
    }

    public float u(int slot) {
        return uvs[slot * 2];
    }

    public float v(int slot) {
        return uvs[slot * 2 + 1];
    }

    public float normX(int slot) {
        return normals[slot * 3];
    }

    public float normY(int slot) {
        return normals[slot * 3 + 1];
    }

    public float normZ(int slot) {
        return normals[slot * 3 + 2];
    }

    public ObjMesh transformed(int quarterTurns, float tx, float ty, float tz) {
        int q = Math.floorMod(quarterTurns, 4);
        if (q == 0 && tx == 0f && ty == 0f && tz == 0f) {
            return this;
        }
        // glRotatef(angle, 0,1,0) maps (x,z) -> (x*cos + z*sin, -x*sin + z*cos).
        // For quarter turns cos/sin are exactly 0 or +-1, so this stays bit-exact.
        int cos =
                switch (q) {
                    case 0 -> 1;
                    case 1 -> 0;
                    case 2 -> -1;
                    default -> 0;
                };
        int sin =
                switch (q) {
                    case 0 -> 0;
                    case 1 -> 1;
                    case 2 -> 0;
                    default -> -1;
                };

        int slots = vertexSlotCount();
        float[] p = new float[slots * 3];
        float[] n = new float[slots * 3];
        for (int i = 0; i < slots; i++) {
            float x = positions[i * 3], y = positions[i * 3 + 1], z = positions[i * 3 + 2];
            p[i * 3] = x * cos + z * sin + tx;
            p[i * 3 + 1] = y + ty;
            p[i * 3 + 2] = -x * sin + z * cos + tz;

            float nx = normals[i * 3], ny = normals[i * 3 + 1], nz = normals[i * 3 + 2];
            n[i * 3] = nx * cos + nz * sin;
            n[i * 3 + 1] = ny;
            n[i * 3 + 2] = -nx * sin + nz * cos;
        }
        return new ObjMesh(p, uvs, n, faceCount);
    }
}
