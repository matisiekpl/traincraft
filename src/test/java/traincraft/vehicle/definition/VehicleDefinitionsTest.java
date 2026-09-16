package traincraft.vehicle.definition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class VehicleDefinitionsTest {

    private static final int ROWS = 502;

    @Test
    void theWholeTableLoads() {
        assertEquals(ROWS, VehicleDefinitions.all().size());
    }

    @Test
    void everyNameIsDistinct() {
        Set<String> seen = new HashSet<>();
        for (VehicleDefinition spec : VehicleDefinitions.all()) {
            assertTrue(seen.add(spec.entryName()), "two rows named " + spec.entryName());
        }
    }

    @Test
    void everyRowIsPopulated() {
        for (VehicleDefinition spec : VehicleDefinitions.all()) {
            assertFalse(spec.entryName().isBlank(), "a row with no name");
            assertFalse(spec.displayName().isBlank(), spec.entryName() + " has no display name");
            assertFalse(spec.trainType().isBlank(), spec.entryName() + " has no type");
        }
    }

    @Test
    void poweredStockHasPowerAndPulledStockHasNone() {
        long powered =
                VehicleDefinitions.all().stream()
                        .filter(s -> s.horsePower() > 0 && s.maxSpeed() > 0)
                        .count();
        assertTrue(powered > 150, "only " + powered + " rows have an engine");
        for (VehicleDefinition spec : VehicleDefinitions.all()) {
            if (spec.cargoCapacity() > 0) {
                assertEquals(
                        0,
                        spec.horsePower(),
                        spec.entryName() + " has both cargo capacity and horsepower");
            }
        }
    }

    @Test
    void theAliceIsUnchanged() {
        VehicleDefinition alice = VehicleDefinitions.get("locoSteamAlice");
        assertEquals("Loco Steam Alice", alice.displayName());
        assertEquals("steam", alice.trainType());
        assertEquals(200, alice.horsePower());
        assertEquals(32, alice.maxSpeed());
        assertEquals(0.0, alice.mass());
        assertEquals(60, alice.fuelConsumption());
        assertEquals(160, alice.waterConsumption());
        assertEquals(200, alice.heatingTime());
        assertEquals(0.5, alice.accelerationRate());
        assertEquals(0.968, alice.brakeRate());
        assertEquals(3750, alice.tankCapacity());
        assertEquals(15, alice.guiRenderScale());
        assertEquals(-2.0, alice.bogieLocoPosition());
        assertEquals(List.of(), alice.colours());
    }

    /** The BR 80, likewise, and it also pins the colour column. */
    @Test
    void theBR80IsUnchanged() {
        VehicleDefinition br80 = VehicleDefinitions.get("locoSteamBR80");
        assertEquals("Loco Steam BR80", br80.displayName());
        assertEquals(575, br80.horsePower());
        assertEquals(45, br80.maxSpeed());
        assertEquals(100, br80.fuelConsumption());
        assertEquals(130, br80.waterConsumption());
        assertEquals(135, br80.heatingTime());
        assertEquals(0.45, br80.accelerationRate());
        assertEquals(0.97, br80.brakeRate());
        assertEquals(7000, br80.tankCapacity());
        assertEquals(16, br80.guiRenderScale());
        assertEquals(-1.1, br80.bogieLocoPosition());
        assertEquals(List.of("Black", "Green"), br80.colours());
    }

    /**
     * The shorthand form, which carries a cargo capacity and no engine at all. It is the form
     * seventy-eight freight wagons are written in, and getting its column order wrong would put a
     * wagon's cargo capacity into its horsepower without anything noticing.
     */
    @Test
    void aFreightWagonReadsAsOne() {
        VehicleDefinition wagon = VehicleDefinitions.get("freightCartRed");
        assertEquals("Freight Cart Red", wagon.displayName());
        assertEquals("freight", wagon.trainType());
        assertEquals(3.0, wagon.mass());
        assertEquals(36, wagon.cargoCapacity());
        assertEquals(18, wagon.guiRenderScale());
        assertEquals(0, wagon.horsePower());
        assertEquals("Cargo: any", wagon.tooltip());
    }
}
