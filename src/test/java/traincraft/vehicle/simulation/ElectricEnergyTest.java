package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ElectricEnergyTest {

    @Test
    void redstoneFitsOnlyWhenItsWholeValueFits() {
        FuelStore energy = new FuelStore();
        energy.restore(FuelStore.CAPACITY - 2000);
        assertFalse(energy.refill(18000));
        assertTrue(energy.refill(2000));
        assertEquals(FuelStore.CAPACITY, energy.amount());
    }

    @Test
    void drawIsATenthOfTheHeadroomCappedAtTwoHundred() {
        assertEquals(20, Math.min(200, FuelStore.CAPACITY - 0) / 10);
        assertEquals(15, Math.min(200, FuelStore.CAPACITY - 19850) / 10);
        assertEquals(0, Math.min(200, FuelStore.CAPACITY - 19995) / 10);
    }
}
