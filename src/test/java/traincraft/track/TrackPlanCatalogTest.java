package traincraft.track;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

import traincraft.TestPaths;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

class TrackPlanCatalogTest {

    private static Path goldens() {
        return TestPaths.testResource("goldens");
    }

    @Test
    void tableLoads() {
        assertTrue(TrackPlanCatalog.entryCount() > 0, "The bundled placement catalog is empty");
    }

    @Test
    void everyEntryRoundTripsFromItsFixture() throws IOException {
        Path dir = goldens();
        assumeTrue(Files.isDirectory(dir), "no fixtures checked out at " + dir);

        int compared = 0;
        try (var files = Files.list(dir)) {
            for (Path file :
                    files.filter(p -> p.getFileName().toString().endsWith(".json")).toList()) {
                // A fixture is named for what was *asked for*, plus which way the player leaned:
                // NAME_facing_l or NAME_facing_r. What it actually laid is inside, under
                // "resolvedType" -- one item lays three different pieces -- and that is the key
                // the table is built on, so it is the key this compares against.
                String json = Files.readString(file, StandardCharsets.UTF_8);
                String typeName = fieldValue(json, "resolvedType");
                if (typeName == null) {
                    continue;
                }
                int facing = (int) Double.parseDouble(fieldValue(json, "facing"));

                TrackType type;
                try {
                    type = TrackType.valueOf(typeName);
                } catch (IllegalArgumentException e) {
                    continue;
                }

                int blocks = countBlocks(json);
                TrackPlan plan = TrackPlanCatalog.get(type, facing);

                if (blocks == 0) {

                    assertEquals(
                            null,
                            plan,
                            typeName
                                    + " facing "
                                    + facing
                                    + " has an empty fixture but a nonempty catalog entry");
                    continue;
                }
                assertNotNull(
                        plan,
                        typeName
                                + " facing "
                                + facing
                                + " placed "
                                + blocks
                                + " blocks in the fixture but is missing from the catalog");
                assertEquals(
                        blocks,
                        plan.size(),
                        typeName
                                + " facing "
                                + facing
                                + " has a different number of placements than the fixture");
                compared++;
            }
        }
        assertTrue(compared > 50, "only " + compared + " fixtures compared; expected far more");
    }

    /** Pulls one scalar field out of a fixture without a JSON library on the test classpath. */
    private static String fieldValue(String json, String field) {
        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile(
                                java.util.regex.Pattern.quote("\"" + field + "\"")
                                        + "[ ]*:[ ]*\"?([A-Za-z0-9_.-]+)\"?")
                        .matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Test
    void offsetsWithinAPlanAreDistinct() {
        for (TrackType type : TrackType.values()) {
            for (int facing = 0; facing < 4; facing++) {
                TrackPlan plan = TrackPlanCatalog.get(type, facing);
                if (plan == null) {
                    continue;
                }
                Set<String> seen = new HashSet<>();
                for (Placement placement : plan.placements()) {
                    String key = placement.dx() + "," + placement.dy() + "," + placement.dz();
                    // A repeat would mean the capture recorded one position twice, which would
                    // make the plan write the same block twice and lose whichever came first.
                    assertTrue(
                            seen.add(key),
                            type + " facing " + facing + " has two placements at " + key);
                }
            }
        }
    }

    @Test
    void curvedPlacementsSitOnTheirOwnCircle() {
        for (TrackType type : TrackType.values()) {
            for (int facing = 0; facing < 4; facing++) {
                TrackPlan plan = TrackPlanCatalog.get(type, facing);
                if (plan == null) {
                    continue;
                }
                for (Placement placement : plan.placements()) {
                    TrackGeometry.Curve params = placement.params().geometry().curve();
                    if (params == null || params.radius() <= 0) {
                        continue;
                    }
                    double dx = placement.dx() + 0.5 - params.centerX();
                    double dz = placement.dz() + 0.5 - params.centerZ();
                    double distance = Math.hypot(dx, dz);

                    double tolerance = Math.max(3.0, params.radius() * 0.15);
                    assertTrue(
                            Math.abs(distance - params.radius()) < tolerance,
                            type
                                    + " facing "
                                    + facing
                                    + " at "
                                    + placement.dx()
                                    + ","
                                    + placement.dz()
                                    + " is "
                                    + String.format("%.2f", distance)
                                    + " from its centre but its radius is "
                                    + params.radius()
                                    + " (tolerance "
                                    + String.format("%.2f", tolerance)
                                    + ")");
                }
            }
        }
    }

    @Test
    void unsupportedTypesFailLoudly() {
        // Nothing may be placed on a guess: a wrong footprint corrupts the world, so a type with
        // no recorded placement has to refuse rather than improvise.
        for (TrackType type : TrackType.values()) {
            for (int facing = 0; facing < 4; facing++) {
                if (TrackPlacementPlanner.canPlan(type, facing)) {
                    assertEquals(null, TrackPlacementPlanner.whyUnsupported(type, facing));
                    continue;
                }
                assertNotNull(
                        TrackPlacementPlanner.whyUnsupported(type, facing),
                        "unsupported " + type + " gives no reason");
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> TrackPlacementPlanner.plan(type, 0));
                return;
            }
        }
    }

    @Test
    void facingMustBeInRange() {
        TrackType any = TrackType.values()[0];
        assertThrows(IllegalArgumentException.class, () -> TrackPlacementPlanner.plan(any, 4));
        assertThrows(IllegalArgumentException.class, () -> TrackPlacementPlanner.plan(any, -1));
    }

    /** Counts {@code "offset"} keys, one per recorded block, without a JSON dependency. */
    private static int countBlocks(String json) {
        int count = 0;
        int at = json.indexOf("\"offset\"");
        while (at >= 0) {
            count++;
            at = json.indexOf("\"offset\"", at + 1);
        }
        return count;
    }
}
