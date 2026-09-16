package traincraft.vehicle.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CouplingSpringTest {

    @Test
    void aStretchedCouplingPullsTheCartsTogether() {
        CouplingSpring spring = CouplingSpring.spring(3.0, 0.0, 2.4);
        // Separation is cart one minus cart two, and the force is added to cart one, so a link
        // that is too long pulls the leading cart backwards.
        assertTrue(spring.x() < 0.0);
        assertEquals(0.0, spring.z(), 0.0);
        assertEquals(0.1 * (3.0 - 2.4) * 3.0 * -1.0, spring.x(), 1.0E-9);
    }

    @Test
    void aCompressedCouplingPushesThemApart() {
        assertTrue(CouplingSpring.spring(1.0, 0.0, 2.4).x() > 0.0);
    }

    @Test
    void theSpringUsesTheRawSeparationAndTheDamperTheUnitVector() {
        // Doubling the separation at the same stretch quadruples the spring, because the term
        // carries the separation itself rather than its direction.
        double near = CouplingSpring.spring(2.0, 0.0, 1.0).x();
        double far = CouplingSpring.spring(4.0, 0.0, 3.0).x();
        assertEquals(0.1 * 1.0 * 2.0 * -1.0, near, 1.0E-9);
        assertEquals(0.1 * 1.0 * 4.0 * -1.0, far, 1.0E-9);

        CouplingSpring damping = CouplingSpring.damping(0.5, 0.0, 1.0, 0.0);
        assertEquals(0.4 * 0.5 * -1.0, damping.x(), 1.0E-9);
    }

    @Test
    void noForceExceedsFourteen() {
        CouplingSpring spring = CouplingSpring.spring(1000.0, -1000.0, 0.0);
        assertEquals(-14.0, spring.x());
        assertEquals(14.0, spring.z());
    }
}
