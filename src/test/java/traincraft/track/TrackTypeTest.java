package traincraft.track;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import traincraft.TestPaths;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class TrackTypeTest {

    private record Row(
            int ordinal, String name, String label, String category, String item, String tooltip) {}

    private static List<Row> canonical() throws IOException {
        Path tsv = TestPaths.testResource("tracktypes.tsv");
        assertTrue(Files.exists(tsv), "Missing track identity fixture: " + tsv.toAbsolutePath());
        List<Row> rows = new ArrayList<>();
        for (String line : Files.readAllLines(tsv, StandardCharsets.UTF_8)) {
            if (line.isBlank() || line.startsWith("#")) {
                continue;
            }
            String[] f = line.split("\t", -1);
            rows.add(
                    new Row(
                            Integer.parseInt(f[0]),
                            f[1],
                            f[2],
                            f[3],
                            f[4],
                            f.length > 5 ? f[5] : ""));
        }
        return rows;
    }

    @Test
    void hasExactlyTheModsConstantsInOrder() throws IOException {
        List<Row> rows = canonical();
        assertTrue(rows.size() > 100, "Incomplete track catalog: " + rows.size());
        assertEquals(rows.size(), TrackType.values().length, "constant count differs from the mod");

        for (Row row : rows) {
            TrackType type = TrackType.values()[row.ordinal()];
            assertEquals(
                    row.name(), type.name(), "constant at ordinal " + row.ordinal() + " differs");
            assertEquals(row.label(), type.label(), "label of " + row.name() + " differs");
            assertEquals(
                    row.category(),
                    type.category().name(),
                    "category of " + row.name() + " differs");
            assertEquals(row.tooltip(), type.tooltip(), "tooltip of " + row.name() + " differs");
        }
    }

    /**
     * Three label quirks that look like typos and are not. Labels are string-compared when a tile
     * resolves its render model, so tidying any of them draws the affected pieces as something else
     * entirely.
     */
    @Test
    void preservesTheLabelQuirks() {
        assertEquals("MEDIUM_RIGHT_SWITCH", TrackType.MEDIUM_SWITCH.label());
        assertEquals("MEDIUM_SWITCH", TrackType.MEDIUM_RIGHT_SWITCH.label());
        assertEquals("EMBEDDED_MEDIUM_RIGHT_SWITCH", TrackType.EMBEDDED_MEDIUM_SWITCH.label());
        assertEquals("EMBEDDED_MEDIUM_SWITCH", TrackType.EMBEDDED_MEDIUM_RIGHT_SWITCH.label());
        // Genuinely truncated in the mod, not a transcription slip on this side.
        assertEquals("EMBEDDED_", TrackType.EMBEDDED_MEDIUM_PARALLEL_SWITCH.label());
    }

    @Test
    void labelsResolveBackToATypeWithThatLabel() {
        for (TrackType type : TrackType.values()) {
            TrackType resolved = TrackType.byLabel(type.label());
            assertNotNull(resolved, "no type resolves the label of " + type);

            assertEquals(
                    type.label(),
                    resolved.label(),
                    "byLabel returned a type with a different label for " + type);
        }
    }

    @Test
    void unknownAndNullLabelsResolveToNullRatherThanThrowing() {

        assertEquals(null, TrackType.byLabel("NOT_A_TRACK"));
        assertEquals(null, TrackType.byLabel(null));
    }

    @Test
    void everyTypeHasACategoryAndShapeGroup() {
        for (TrackType type : TrackType.values()) {
            assertNotNull(type.category(), type + " has no category");
            assertNotNull(type.shapeGroup(), type + " has no shape group");
        }
    }

    @Test
    void shapeGroupsFollowTheCategory() {
        for (TrackType type : TrackType.values()) {
            TrackShapeGroup group = type.shapeGroup();
            switch (type.category()) {
                case SLOPE, CURVED_SLOPE ->
                        assertTrue(
                                group == TrackShapeGroup.SLOPE
                                        || group == TrackShapeGroup.SLOPE_LEFT,
                                type + " is a slope but grouped as " + group);
                case TURN, SHARP_TURN, CURVE ->
                        assertTrue(
                                group == TrackShapeGroup.TURN_LEFT
                                        || group == TrackShapeGroup.TURN_RIGHT,
                                type + " is a turn but grouped as " + group);
                case SWITCH ->
                        assertTrue(
                                group == TrackShapeGroup.SWITCH_LEFT
                                        || group == TrackShapeGroup.SWITCH_RIGHT,
                                type + " is a switch but grouped as " + group);
                case CROSSING, DIAGONAL_CROSSING -> assertEquals(TrackShapeGroup.CROSSING, group);
                case DIAGONAL -> assertEquals(TrackShapeGroup.DIAGONAL, group);
                case STRAIGHT -> assertEquals(TrackShapeGroup.STRAIGHT, group);
            }
        }
    }

    /**
     * For the families that have a mirrored form, the left/right split decides which mesh is drawn,
     * so it must follow the constant name.
     *
     * <p>Crossings are exempt: they are symmetric and have a single group, yet some are named
     * LEFT_DIAMOND_CROSSING because the name describes which way the branch leaves, not a separate
     * model.
     */
    @Test
    void leftVariantsOfMirroredFamiliesAreGroupedLeft() {
        for (TrackType type : TrackType.values()) {
            if (!type.name().contains("LEFT")) {
                continue;
            }
            if (type.category() == TrackCategory.CROSSING
                    || type.category() == TrackCategory.DIAGONAL_CROSSING) {
                continue;
            }
            TrackShapeGroup group = type.shapeGroup();
            assertTrue(
                    group == TrackShapeGroup.TURN_LEFT
                            || group == TrackShapeGroup.SWITCH_LEFT
                            || group == TrackShapeGroup.SLOPE_LEFT,
                    type + " names LEFT but is grouped " + group);
        }
    }
}
