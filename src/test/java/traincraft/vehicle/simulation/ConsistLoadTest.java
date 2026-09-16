package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

class ConsistLoadTest {

    private static final VehicleDefinition ALICE = VehicleDefinitions.LOCO_STEAM_ALICE;

    @Test
    void aLocomotiveOnItsOwnRunsToItsSpecification() {
        ConsistLoad load = ConsistLoad.of(ALICE, 0.0, 0, 0.0, ALICE.maxSpeed());
        assertEquals(0, load.cartsPulled());
        assertEquals(0.0, load.massPulled());
        assertEquals(ALICE.maxSpeed(), load.customSpeed());
        assertEquals(ALICE.accelerationRate(), load.accelerate());
        assertEquals(ALICE.brakeRate(), load.brake());
        assertEquals(ALICE.fuelConsumption(), load.fuelRate());
    }

    @Test
    void oneUncoupledLocomotiveInAConsistOfOneIsStillUnloaded() {
        // A consist of one has no power figure at all upstream, and every rate is left alone.
        ConsistLoad load = ConsistLoad.of(ALICE, ALICE.mass(), 1, 0.0, ALICE.maxSpeed());
        assertEquals(0, load.cartsPulled());
        assertEquals(ALICE.accelerationRate(), load.accelerate());
        assertEquals(ALICE.fuelConsumption(), load.fuelRate());
    }

    @Test
    void aWagonCostsSpeedAccelerationAndFuel() {
        VehicleDefinition wagon = VehicleDefinitions.FREIGHT_CART_YELLOW;
        double totalMass = ALICE.mass() + wagon.mass();
        double power = ALICE.horsePower();
        ConsistLoad load = ConsistLoad.of(ALICE, totalMass, 2, power, ALICE.maxSpeed());

        assertEquals(1, load.cartsPulled());
        assertEquals(Math.round(totalMass * 10.0), load.massPulled());

        double drawbar = totalMass * 0.745;
        assertEquals(drawbar / (power / 74.57), load.speedSlowDown(), 1.0E-9);
        assertEquals(ALICE.maxSpeed() - load.speedSlowDown(), load.customSpeed(), 1.0E-9);

        double scaled = drawbar / (power / 745.7) / 1000.0 * 0.8;
        assertEquals(ALICE.accelerationRate() - scaled * drawbar * 1.13, load.accelerate(), 1.0E-9);
        assertEquals(ALICE.brakeRate() + scaled * drawbar, load.brake(), 1.0E-9);
        assertEquals(
                ALICE.fuelConsumption() - (int) (scaled * drawbar * 100.0), load.fuelRate());
        assertTrue(load.accelerate() < ALICE.accelerationRate());
    }

    @Test
    void aBrakeDrivenPastOneIsPinnedJustShortOfIt() {
        ConsistLoad load = ConsistLoad.of(ALICE, 4000.0, 40, ALICE.horsePower(), ALICE.maxSpeed());
        assertEquals(0.998, load.brake());
        // Speed cannot fall below the floor upstream keeps a stalled train at.
        assertEquals(0.1, load.customSpeed());
    }
}
