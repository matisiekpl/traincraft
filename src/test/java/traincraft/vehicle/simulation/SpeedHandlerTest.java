package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpeedHandlerTest {

    private static final double CONVERTED_60 = 60.0 * 0.2775 / 6.0 / 10.0;

    @Test
    @DisplayName("stock other than a locomotive is not limited by the rail")
    void cartIsUnlimited() {
        assertEquals(15.0, SpeedHandler.handleSpeed(0.4, false, 60.0));
        assertEquals(15.0, SpeedHandler.handleSpeed(3.0, false, 60.0));
    }

    @Test
    @DisplayName("a slow rail caps a locomotive that would exceed it")
    void slowRailCaps() {
        assertEquals(0.4, SpeedHandler.handleSpeed(0.4 - 1.0E-9, true, 200.0), 1.0E-6);
        assertEquals(CONVERTED_60, SpeedHandler.handleSpeed(0.3, true, 60.0), 1.0E-9);
    }

    @Test
    @DisplayName("high speed track adds a fifth of a block a tick")
    void highSpeedTrackAdds() {
        assertEquals(CONVERTED_60 + 0.2, SpeedHandler.handleSpeed(0.6, true, 60.0), 1.0E-9);
    }

    @Test
    @DisplayName("Traincraft track leaves the locomotive on its own figure")
    void ownTrackIsOpen() {
        assertEquals(CONVERTED_60, SpeedHandler.handleSpeed(3.0, true, 60.0), 1.0E-9);
    }

    @Test
    void realTrainSpeedScalesByTwoInsteadOfSix() {
        assertEquals(CONVERTED_60 * 3.0, SpeedHandler.handleSpeed(3.0, true, 60.0, true), 1.0E-9);
    }
}
