package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuelStoreTest {
    @Test
    void refillIsAllOrNothing() {
        FuelStore fuel = new FuelStore();
        fuel.restore(FuelStore.CAPACITY - 10);
        assertFalse(fuel.refill(11));
        assertFalse(fuel.refill(0));
        assertEquals(FuelStore.CAPACITY - 10, fuel.amount());
        assertTrue(fuel.refill(10));
        assertEquals(FuelStore.CAPACITY, fuel.amount());
    }

    @Test
    void consumesOnlyAtTheIntervalWhileTheEngineIsOn() {
        FuelStore fuel = new FuelStore();
        fuel.restore(50);
        for (int tick = 0; tick < 99; tick++) fuel.tick(true, 10);
        assertEquals(50, fuel.amount());
        fuel.tick(true, 10);
        assertEquals(40, fuel.amount());
        for (int tick = 0; tick < 100; tick++) fuel.tick(false, 10);
        assertEquals(40, fuel.amount());
    }

    @Test
    void restorationClampsFuelAndResetsTheConsumptionInterval() {
        FuelStore fuel = new FuelStore();
        fuel.restore(-1);
        assertEquals(0, fuel.amount());
        for (int tick = 0; tick < 99; tick++) fuel.tick(true, 1);
        fuel.restore(Integer.MAX_VALUE);
        fuel.tick(true, 1);
        assertEquals(FuelStore.CAPACITY, fuel.amount());
        assertThrows(IllegalArgumentException.class, () -> fuel.tick(true, -1));
    }
}
