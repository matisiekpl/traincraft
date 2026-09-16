package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VehicleOrientationTest {
    @Test
    void cardinalDirectionsUseMinecraftYaw() {
        assertEquals(0, VehicleOrientation.fromDirection(0, 1), 0.0001);
        assertEquals(90, VehicleOrientation.fromDirection(-1, 0), 0.0001);
        assertEquals(-90, VehicleOrientation.fromDirection(1, 0), 0.0001);
        assertEquals(180, Math.abs(VehicleOrientation.fromDirection(0, -1)), 0.0001);
    }

    @Test
    void reversingDoesNotTurnTheBodyAround() {
        assertEquals(0, VehicleOrientation.nearestDirection(180, 0));
        assertEquals(90, VehicleOrientation.nearestDirection(-90, 90));
        assertEquals(181, VehicleOrientation.nearestDirection(-179, 179));
    }

    @Test
    void bogiePlacementAndChordOrientationAgree() {
        for (float yaw : new float[] {0, 45, 90, 135, 180, -90, -45}) {
            BogieState bogie = new BogieState(0);
            bogie.placeRelativeTo(10, 2, -5, yaw, -2);
            float chordYaw = VehicleOrientation.fromDirection(bogie.x() - 10, bogie.z() + 5);
            assertEquals(0, Math.IEEEremainder(chordYaw - yaw, 360), 0.0001);
        }
    }
}
