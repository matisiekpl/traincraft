package traincraft.client.render.obj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import traincraft.TestPaths;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

class ObjParserTest {

    private static final Path MODELS = TestPaths.mainResource("assets/tc/models");
    private static final Path MANIFEST = TestPaths.testResource("obj-manifest.json");

    private static JsonObject manifest() throws IOException {
        assertTrue(
                Files.exists(MANIFEST),
                "missing " + MANIFEST.toAbsolutePath() + " -- regenerate with: gradlew objstat");
        return new Gson()
                .fromJson(Files.readString(MANIFEST, StandardCharsets.UTF_8), JsonObject.class);
    }

    static Stream<String> modelNames() throws IOException {
        return manifest().getAsJsonArray("models").asList().stream()
                .map(e -> e.getAsJsonObject().get("file").getAsString());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("modelNames")
    void parsesEveryModelToTheExpectedTriangleCount(String fileName) throws IOException {
        JsonObject expected =
                manifest().getAsJsonArray("models").asList().stream()
                        .map(com.google.gson.JsonElement::getAsJsonObject)
                        .filter(o -> o.get("file").getAsString().equals(fileName))
                        .findFirst()
                        .orElseThrow();

        ObjMesh mesh;
        try (InputStream in = Files.newInputStream(MODELS.resolve(fileName))) {
            mesh = ObjParser.parse(in);
        }
        assertNotNull(mesh);

        // Quads stay quads, triangles become degenerate quads, and an n-gon of n corners fans
        // out into n-2. The manifest records that count as "quads"; comparing against "faces",
        // the number of f lines, made the one model with n-gons look as though the parser were
        // dropping geometry.
        assertEquals(
                expected.get("quads").getAsInt(),
                mesh.faceCount(),
                fileName + ": face count differs from the generated inventory");
        assertEquals(mesh.faceCount() * ObjMesh.VERTS_PER_FACE, mesh.vertexSlotCount());
        assertEquals(mesh.vertexSlotCount() * 3, mesh.positions().length);
        assertEquals(mesh.vertexSlotCount() * 2, mesh.uvs().length);
        assertEquals(mesh.vertexSlotCount() * 3, mesh.normals().length);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("modelNames")
    void producesFiniteCoordinatesAndUnitNormals(String fileName) throws IOException {
        ObjMesh mesh;
        try (InputStream in = Files.newInputStream(MODELS.resolve(fileName))) {
            mesh = ObjParser.parse(in);
        }
        for (float f : mesh.positions()) {
            assertTrue(Float.isFinite(f), fileName + ": non-finite position");
        }
        for (float f : mesh.uvs()) {
            assertTrue(Float.isFinite(f), fileName + ": non-finite uv");
        }
        for (int slot = 0; slot < mesh.vertexSlotCount(); slot++) {
            double len =
                    Math.sqrt(
                            mesh.normX(slot) * mesh.normX(slot)
                                    + mesh.normY(slot) * mesh.normY(slot)
                                    + mesh.normZ(slot) * mesh.normZ(slot));
            assertTrue(
                    Math.abs(len - 1.0) < 1.0e-3,
                    fileName + ": normal is not unit length (" + len + ")");
        }
    }

    @Test
    void adrTwoStillHolds() throws IOException {
        JsonObject summary = manifest().getAsJsonObject("summary");
        assertEquals(
                0,
                summary.get("mtl_count").getAsInt(),
                "an .mtl appeared; NeoForge's OBJ loader may now be viable, revisit ADR-002");
        assertTrue(
                summary.getAsJsonArray("files_with_uvs_outside_unit_square").size() > 20,
                "UVs no longer leave the unit square; revisit ADR-002");

        assertEquals(
                1,
                summary.getAsJsonArray("files_with_ngons").size(),
                "the set of models with n-gons changed; fan triangulation assumes convex faces");
        assertEquals(
                12,
                summary.get("max_face_size").getAsInt(),
                "face sizes changed; the QUADS assumption in the renderer needs rechecking");
    }

    @Test
    void quarterTurnRotationIsExactAndReversible() throws IOException {
        ObjMesh mesh;
        try (InputStream in = Files.newInputStream(MODELS.resolve("track_normal.obj"))) {
            mesh = ObjParser.parse(in);
        }
        ObjMesh full = mesh.transformed(4, 0f, 0f, 0f);
        // Four quarter turns must be bit-identical, not merely close: the rotation is baked once
        // per facing at load time, so any drift becomes a permanent geometry offset.
        float[] expected = mesh.positions();
        float[] actual = full.positions();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    expected[i],
                    actual[i],
                    0.0f,
                    "four quarter turns changed the geometry at index " + i);
        }
    }

    @Test
    void rotationMatchesTheOriginalGlRotatefConvention() throws IOException {
        ObjMesh mesh;
        try (InputStream in = Files.newInputStream(MODELS.resolve("track_normal.obj"))) {
            mesh = ObjParser.parse(in);
        }
        ObjMesh turned = mesh.transformed(1, 0f, 0f, 0f);
        // glRotatef(90, 0, 1, 0) maps (x, z) -> (z, -x).
        for (int slot = 0; slot < mesh.vertexSlotCount(); slot++) {
            assertEquals(mesh.posZ(slot), turned.posX(slot), 1.0e-6f);
            assertEquals(-mesh.posX(slot), turned.posZ(slot), 1.0e-6f);
            assertEquals(mesh.posY(slot), turned.posY(slot), 1.0e-6f);
        }
    }

    @Test
    void trianglesAreEmittedAsDegenerateQuads() throws IOException {
        String obj =
                """
                v 0 0 0
                v 1 0 0
                v 0 1 0
                vt 0 0
                vt 1 0
                vt 0 1
                f 1/1 2/2 3/3
                """;
        ObjMesh mesh =
                ObjParser.parse(
                        new java.io.ByteArrayInputStream(obj.getBytes(StandardCharsets.UTF_8)));
        assertEquals(1, mesh.faceCount());
        assertEquals(4, mesh.vertexSlotCount());
        // The fourth slot repeats the third so the triangle fits a QUADS buffer.
        assertEquals(mesh.posX(2), mesh.posX(3));
        assertEquals(mesh.posY(2), mesh.posY(3));
        assertEquals(mesh.posZ(2), mesh.posZ(3));
    }

    @Test
    void synthesisesFlatNormalsWhenTheFileHasNone() throws IOException {
        String obj =
                """
                v 0 0 0
                v 1 0 0
                v 1 0 1
                v 0 0 1
                f 1 2 3 4
                """;
        ObjMesh mesh =
                ObjParser.parse(
                        new java.io.ByteArrayInputStream(obj.getBytes(StandardCharsets.UTF_8)));
        assertEquals(1, mesh.faceCount());
        // A face wound clockwise when viewed from +Y has a downward normal by the right-hand rule.
        for (int slot = 0; slot < mesh.vertexSlotCount(); slot++) {
            assertEquals(0f, mesh.normX(slot), 1.0e-6f);
            assertEquals(-1f, mesh.normY(slot), 1.0e-6f);
            assertEquals(0f, mesh.normZ(slot), 1.0e-6f);
        }
    }

    @Test
    void resolvesNegativeIndices() throws IOException {
        String obj =
                """
                v 0 0 0
                v 1 0 0
                v 1 1 0
                f -3 -2 -1
                """;
        ObjMesh mesh =
                ObjParser.parse(
                        new java.io.ByteArrayInputStream(obj.getBytes(StandardCharsets.UTF_8)));
        assertEquals(1, mesh.faceCount());
        assertEquals(0f, mesh.posX(0));
        assertEquals(1f, mesh.posX(1));
    }

    @Test
    void manifestCoversEveryShippedModel() throws IOException {
        List<String> listed = modelNames().toList();
        try (Stream<Path> files = Files.list(MODELS)) {
            List<String> onDisk =
                    files.map(p -> p.getFileName().toString())
                            .filter(n -> n.endsWith(".obj"))
                            .sorted()
                            .toList();
            assertEquals(
                    onDisk.size(),
                    listed.size(),
                    "obj-manifest.json is stale -- regenerate with: gradlew objstat");
            assertFalse(onDisk.isEmpty());
        }
    }
}
