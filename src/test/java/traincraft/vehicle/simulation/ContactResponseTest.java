package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ContactResponseTest {

    @Test
    void aBufferContactKeepsTheMomentumAndDoesNotBounce() {
        ContactResponse.Axial after = ContactResponse.plastic(0.1, 0.0, 1.0 / 300.0, 1.0 / 100.0, 0.0);
        assertEquals(300.0 * 0.1, 300.0 * after.first() + 100.0 * after.second(), 1.0E-9);
        assertEquals(after.first(), after.second(), 1.0E-9);
        assertEquals(0.075, after.first(), 1.0E-9);
    }

    @Test
    void theGapMayStillCloseThisTick() {
        ContactResponse.Axial after = ContactResponse.plastic(0.1, 0.0, 1.0, 1.0, 0.04);
        assertEquals(0.04, after.first() - after.second(), 1.0E-9);
        ContactResponse.Axial untouched = ContactResponse.plastic(0.1, 0.0, 1.0, 1.0, 0.2);
        assertEquals(0.1, untouched.first());
        assertEquals(0.0, untouched.second());
    }

    @Test
    void separatingBodiesAreLeftAlone() {
        ContactResponse.Axial after = ContactResponse.plastic(-0.1, 0.1, 1.0, 1.0, -0.3);
        assertEquals(-0.1, after.first());
        assertEquals(0.1, after.second());
    }

    @Test
    void aHeldBodyTakesNothing() {
        ContactResponse.Axial after = ContactResponse.plastic(0.1, 0.0, 1.0, 0.0, 0.0);
        assertEquals(0.0, after.second());
        assertEquals(0.0, after.first(), 1.0E-9);
        assertEquals(0.0, ContactResponse.plastic(0.1, 0.0, 0.0, 0.0, 0.0).second());
    }

    @Test
    void anOverlapIsSharedByInverseMass() {
        ContactResponse.Axial split = ContactResponse.split(0.3, 1.0, 2.0);
        assertEquals(0.3, split.first() + split.second(), 1.0E-9);
        assertEquals(0.2, split.second(), 1.0E-9);
        assertEquals(0.0, ContactResponse.split(0.3, 0.0, 1.0).first());
    }

    @Test
    void trainsDerailFromTenKilometresAnHour() {
        assertFalse(ContactResponse.derails(9.0 / 216.0));
        assertTrue(ContactResponse.derails(11.0 / 216.0));
    }

    @Test
    void pushingCostsLittle() {
        assertEquals(1.0, ContactResponse.pushingFactor(100.0, 0.0));
        double factor = ContactResponse.pushingFactor(100.0, 100.0);
        assertTrue(factor < 1.0 && factor > 0.99);
    }
}
