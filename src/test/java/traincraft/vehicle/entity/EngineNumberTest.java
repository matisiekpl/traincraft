package traincraft.vehicle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EngineNumberTest {

    @Test
    void keepsLettersDigitsAndSeparators() {
        assertEquals("BNSF 7434", RollingStockEntity.sanitizeEngineNumber("  BNSF 7434 "));
        assertEquals("ES44-9.1_x", RollingStockEntity.sanitizeEngineNumber("ES44-9.1_x"));
    }

    @Test
    void dropsMarkupAndTruncatesToTwelve() {
        assertEquals("1234567890AB", RollingStockEntity.sanitizeEngineNumber("§1234567890ABCDEF"));
        assertEquals("", RollingStockEntity.sanitizeEngineNumber(null));
        assertEquals("", RollingStockEntity.sanitizeEngineNumber("§§§"));
    }

    @Test
    void forneyBoostsOnlyTheTwoNamedDrivers() {
        assertEquals(2, ForneyLocomotiveEntity.BOOSTED_DRIVERS.size());
        assertEquals(true, ForneyLocomotiveEntity.BOOSTED_DRIVERS.contains("EternalBlueFlame"));
        assertEquals(false, ForneyLocomotiveEntity.BOOSTED_DRIVERS.contains("eternalblueflame"));
    }
}
