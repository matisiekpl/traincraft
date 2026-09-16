package traincraft.client.render.obj;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjMeshTest {
    @Test
    void callersCannotMutateMeshStorage() {
        float[] positions = new float[12];
        float[] uvs = new float[8];
        float[] normals = new float[12];
        ObjMesh mesh = new ObjMesh(positions, uvs, normals, 1);
        positions[0] = uvs[0] = normals[0] = 9;
        mesh.positions()[0] = 7;
        mesh.uvs()[0] = 7;
        mesh.normals()[0] = 7;
        assertEquals(0, mesh.posX(0));
        assertEquals(0, mesh.u(0));
        assertEquals(0, mesh.normX(0));
        assertEquals(0, mesh.transformed(1, 0, 0, 0).u(0));
    }

    @Test
    void rejectsInconsistentArrayLengths() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ObjMesh(new float[12], new float[7], new float[12], 1));
    }
}
