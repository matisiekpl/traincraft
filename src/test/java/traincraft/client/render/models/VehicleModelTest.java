package traincraft.client.render.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import traincraft.TestPaths;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;

class VehicleModelTest {
    @Test
    void bundledModelsContainTheirDeclaredGeometryAndAnimationGroups() throws IOException {
        for (String name : new String[] {"alice", "br80", "freightcart", "br185", "br185_bogie",
                "es44", "es44_truck", "sd40", "sd70", "v60", "br_e69", "e10", "e10_bogie"}) {
            try (var reader =
                    Files.newBufferedReader(
                            TestPaths.projectDir()
                                    .resolve(
                                            "src/main/resources/assets/tc/vehicle_models/"
                                                    + name
                                                    + ".json"))) {
                VehicleModel model = VehicleModel.read(reader);
                assertEquals(
                        switch (name) {
                            case "alice" -> 153;
                            case "br80" -> 73;
                            case "br185" -> 171;
                            case "br185_bogie" -> 13;
                            case "es44" -> 230;
                            case "es44_truck" -> 152;
                            case "sd40" -> 121;
                            case "sd70" -> 128;
                            case "v60" -> 91;
                            case "br_e69" -> 66;
                            case "e10" -> 157;
                            case "e10_bogie" -> 24;
                            default -> 26;
                        },
                        model.parts().size(),
                        name);
                assertEquals(
                        name.equals("br80"),
                        model.parts().stream()
                                .anyMatch(part -> part.group() == VehicleModel.Group.WHEELS),
                        name);
                assertEquals(
                        !java.util.Set.of("alice", "freightcart", "br185_bogie", "es44_truck", "e10_bogie").contains(name),
                        model.parts().stream().anyMatch(VehicleModel.Part::emissive),
                        name);
                for (var part : model.parts()) {
                    assertFalse(part.vertices().isEmpty(), part.name());
                    assertEquals(0, part.vertices().size() % 4, part.name());
                    assertThrows(
                            UnsupportedOperationException.class, () -> part.vertices().clear());
                }
                assertThrows(UnsupportedOperationException.class, () -> model.parts().clear());
            }
        }
    }

    @Test
    void sd40DetailsDoNotStickAboveTheRoof() throws IOException {
        var model = read("sd40");
        float highest = Float.NEGATIVE_INFINITY;
        for (var part : model.parts()) {
            var transform = new org.joml.Matrix4f().translation(
                    part.pivot().x(), part.pivot().y(), part.pivot().z());
            if (part.rotationOrder() == VehicleModel.RotationOrder.ZYX) {
                transform.rotateZ(part.rotation().z()).rotateY(part.rotation().y());
            } else {
                transform.rotateY(part.rotation().y()).rotateZ(part.rotation().z());
            }
            transform.rotateX(part.rotation().x());
            for (var vertex : part.vertices()) {
                var point = transform.transformPosition(
                        new org.joml.Vector3f(vertex.x(), vertex.y(), vertex.z()));
                highest = Math.max(highest, point.y);
            }
        }
        // The roof is 34 model pixels high. The degree/radian bug raised a handrail to 54.
        assertEquals(34.0F, highest, 0.001F);
    }

    @Test
    void e10PantographArmsRiseToTheirUpperJoints() throws IOException {
        var model = read("e10");
        for (String name : java.util.List.of("bodyModel[120]", "bodyModel[124]",
                "bodyModel[150]", "bodyModel[151]")) {
            var arm = model.parts().stream().filter(p -> p.name().equals(name)).findFirst().orElseThrow();
            double rise = 8 * Math.sin(arm.rotation().z());
            assertTrue(rise > 5 && rise < 5.2, name + " no longer reaches the upper pantograph");
        }
        // flipAll on a ModelConverter array changes rotations, not face winding.
        var body = model.parts().getFirst();
        var face = body.vertices().subList(0, 4);
        assertTrue(face.getFirst().nx() > 0, "E10 body was turned inside out");
    }

    @Test
    void everySupportedLiveryHasAReadableTexture() throws IOException {
        var prefixes = java.util.Map.of(
                "locoSteamBR80", "ported/locobr80_db_",
                "locoElectricBR185", "ported/br185_engine_",
                "ES44", "ported/es44_",
                "locoDieselSD40", "ported/locosd40_",
                "locoDieselSD70", "ported/locosd70_",
                "locoDieselV60_DB", "ported/locov60_db_",
                "locoElectricBR_E69", "ported/locobr_e69_",
                "locoDieselE10lDB", "ported/locoe10_db_");
        for (var entry : prefixes.entrySet()) {
            var definition = traincraft.vehicle.definition.VehicleDefinitions.get(entry.getKey());
            for (String colour : definition.colours()) {
                var path = TestPaths.projectDir().resolve("src/main/resources/assets/tc/textures/"
                        + entry.getValue() + colour.toLowerCase(java.util.Locale.ROOT) + ".png");
                var texture = javax.imageio.ImageIO.read(path.toFile());
                assertNotNull(texture, path.toString());
                assertTrue(texture.getWidth() > 0 && texture.getHeight() > 0, path.toString());
            }
        }
    }

    private static VehicleModel read(String name) throws IOException {
        try (var reader = Files.newBufferedReader(TestPaths.projectDir().resolve(
                "src/main/resources/assets/tc/vehicle_models/" + name + ".json"))) {
            return VehicleModel.read(reader);
        }
    }

    @Test
    void rejectsEmptyModels() {
        assertThrows(
                IllegalArgumentException.class,
                () -> VehicleModel.read(new StringReader("{\"parts\":[]}")));
    }
}
