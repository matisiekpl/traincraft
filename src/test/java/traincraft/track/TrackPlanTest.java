package traincraft.track;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class TrackPlanTest {
    @Test
    void footprintIsRelativeToTheOwnerRatherThanThePlacementOrigin() {
        Placement owner = placement(3, 1, -2, true);
        Placement member = placement(4, 2, -5, false);
        TrackPlan plan = new TrackPlan(TrackType.SMALL_STRAIGHT, 0, List.of(owner, member));

        assertEquals(new Placement.Link(0, 0, 0), plan.offsetFromOwner(owner));
        assertEquals(new Placement.Link(1, 1, -3), plan.offsetFromOwner(member));
        assertTrue(plan.containsOwnerRelativeOffset(1, 1, -3));
        assertFalse(plan.containsOwnerRelativeOffset(4, 2, -5));
        assertFalse(plan.containsOwnerRelativeOffset(-1, 0, 0));
    }

    @Test
    void lastDropCarryingRailOwnsTheAssembly() {
        Placement first = placement(0, 0, 0, true);
        Placement last = placement(0, 0, 3, true);
        TrackPlan plan = new TrackPlan(TrackType.LONG_STRAIGHT, 0, List.of(first, last));
        assertEquals(last, plan.master());
        assertEquals(new Placement.Link(0, 0, -3), plan.offsetFromOwner(first));
    }

    @Test
    void rejectsDuplicatePositions() {
        Placement owner = placement(0, 0, 0, true);
        assertThrows(
                IllegalArgumentException.class,
                () -> new TrackPlan(TrackType.SMALL_STRAIGHT, 0, List.of(owner, owner)));
    }

    @Test
    void fillerFollowsTheNearestRailOfItsOwnType() {
        Placement master = placement(1, 0, 3, TrackType.MEDIUM_LEFT_TURN, false, true);
        Placement reverse = placement(2, 0, 4, TrackType.MEDIUM_RIGHT_TURN, false, false);
        Placement filler = placement(3, 0, 8, TrackType.MEDIUM_RIGHT_TURN, true, false);
        Placement stray = placement(0, 0, 9, TrackType.MEDIUM_STRAIGHT, true, false);
        TrackPlan plan =
                new TrackPlan(
                        TrackType.MEDIUM_PARALLEL_SWITCH,
                        0,
                        List.of(master, reverse, filler, stray));

        assertEquals(reverse, plan.ownerOf(filler));
        assertEquals(master, plan.ownerOf(stray));
    }

    private static Placement placement(int x, int y, int z, boolean owner) {
        return placement(x, y, z, TrackType.SMALL_STRAIGHT, false, owner);
    }

    private static Placement placement(
            int x, int y, int z, TrackType type, boolean gag, boolean owner) {
        return new Placement(
                x,
                y,
                z,
                type,
                0,
                0,
                gag,
                !gag,
                owner,
                null,
                Placement.DEFAULT_GAG_HEIGHT,
                PlacementParameters.NONE);
    }
}
