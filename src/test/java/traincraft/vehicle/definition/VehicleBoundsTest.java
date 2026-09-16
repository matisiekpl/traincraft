package traincraft.vehicle.definition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VehicleBoundsTest {

    @Test
    void theA4SpansItsRenderedModel() {
        VehicleBounds a4 = VehicleBounds.get("loco_steam_a4");
        assertTrue(a4.back() < -6.0 && a4.front() > 1.0, "A4 length " + a4);
        assertTrue(a4.bottom() < 0.0 && a4.top() > 1.5, "A4 height " + a4);
        assertTrue(a4.width() > 1.0 && a4.width() < 1.5, "A4 width " + a4);
    }

    @Test
    void unknownStockGetsOneBlockAround() {
        assertEquals(3.0, VehicleBounds.get("nothing").front() - VehicleBounds.get("nothing").back());
    }
}
