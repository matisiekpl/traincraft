package traincraft.track;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrackMovementTest {

    /**
     * A body with no world around it.
     *
     * <p>{@code moveBy} simply adds, because there is nothing to collide with. That is the right
     * simplification for testing the geometry: collision is Minecraft's, and what is under test is
     * where the track says to go.
     */
    private static final class TestBody implements TrackMovement.Body {
        double x;
        double y;
        double z;
        double mx;
        double my;
        double mz;
        float yaw;
        boolean locomotive;

        TestBody(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public double x() {
            return x;
        }

        @Override
        public double y() {
            return y;
        }

        @Override
        public double z() {
            return z;
        }

        @Override
        public void setPosition(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public double motionX() {
            return mx;
        }

        @Override
        public double motionY() {
            return my;
        }

        @Override
        public double motionZ() {
            return mz;
        }

        @Override
        public void setMotion(double x, double y, double z) {
            this.mx = x;
            this.my = y;
            this.mz = z;
        }

        @Override
        public void moveBy(double dx, double dy, double dz) {
            x += dx;
            y += dy;
            z += dz;
        }

        @Override
        public double yOffset() {
            return 0.65;
        }

        @Override
        public float yaw() {
            return yaw;
        }

        @Override
        public boolean isLocomotive() {
            return locomotive;
        }
    }

    @Test
    @DisplayName("a straight along Z pins X to the rail centreline and keeps the speed")
    void straightAlongZ() {
        TestBody body = new TestBody(0.9, 64.0, 4.0);
        body.mz = 0.2;
        body.mx = 0.05;

        TrackMovement.moveOnStraight(body, 64, 0, 0, 0);

        assertEquals(0.5, body.x, 1.0E-9, "pulled onto the centre of the rail column");
        assertEquals(64.0 + 0.2 + 0.65, body.y, 1.0E-9, "and onto the rail's own height");
        assertEquals(0.0, body.mx, 1.0E-9, "the cross-axis component is dropped");
        // The whole speed goes onto the axis of travel, so the sideways component is not lost but
        // turned: what went in as 0.2 forward and 0.05 across comes out as their magnitude.
        assertEquals(Math.hypot(0.2, 0.05), body.mz, 1.0E-9);
    }

    @Test
    @DisplayName("a straight along X pins Z instead")
    void straightAlongX() {
        TestBody body = new TestBody(4.0, 64.0, 7.3);
        body.mx = -0.15;

        TrackMovement.moveOnStraight(body, 64, 0, 7, 1);

        assertEquals(7.5, body.z, 1.0E-9);
        assertEquals(0.0, body.mz, 1.0E-9);
        assertEquals(-0.15, body.mx, 1.0E-9, "direction of travel is preserved");
    }

    @Test
    @DisplayName("a crossing keeps the body moving along the axis it arrived on")
    void crossingKeepsTheAxisOfTravel() {
        TestBody alongZ = new TestBody(0.5, 64.85, 4.0);
        alongZ.mz = 0.2;
        TrackMovement.moveOnCrossing(alongZ, 64);
        assertEquals(4.2, alongZ.z, 1.0E-9, "a body arriving along Z leaves along Z");
        assertEquals(0.5, alongZ.x, 1.0E-9);

        TestBody alongX = new TestBody(4.0, 64.85, 0.5);
        alongX.mx = -0.2;
        alongX.yaw = 90.0F;
        TrackMovement.moveOnCrossing(alongX, 64);
        assertEquals(3.8, alongX.x, 1.0E-9, "and one arriving along X leaves along X");
        assertEquals(0.5, alongX.z, 1.0E-9);
    }

    @Test
    @DisplayName("a turn holds the body on the circle for a full quarter, at any speed")
    void turnStaysOnTheCircle() {
        double r = 4.5;
        double cx = 5.0;
        double cz = 0.0;
        // Start on the circle, moving tangentially.
        TestBody body = new TestBody(cx - r, 64.0, cz);
        body.mz = 0.148;
        body.locomotive = true;

        double worst = 0.0;
        for (int tick = 0; tick < 400; tick++) {
            TrackMovement.moveOnTurn(body, 64, r, cx, cz);
            double distance = Math.hypot(body.x - cx, body.z - cz);
            worst = Math.max(worst, Math.abs(distance - r));
        }

        // The correction is applied before the move, so the body is at most one tick's travel off
        // the circle at the moment it is measured -- and never drifts further, which is the claim
        // that matters. An integrating solution would wander outwards without bound.
        assertTrue(worst < 0.2, "worst deviation from the radius was " + worst);
    }

    @Test
    @DisplayName("a turn does not change the speed, only its direction")
    void turnPreservesSpeed() {
        TestBody body = new TestBody(0.5, 64.0, 0.0);
        body.mz = 0.148;
        body.locomotive = true;

        for (int tick = 0; tick < 50; tick++) {
            TrackMovement.moveOnTurn(body, 64, 4.5, 5.0, 0.0);
            assertEquals(
                    0.148,
                    TrackMovement.planarSpeed(body),
                    1.0E-9,
                    "speed changed on tick " + tick);
        }
    }

    @Test
    @DisplayName("curve correction cannot reverse either tangent component")
    void curveCorrectionKeepsTravelDirection() {
        // A coupling can pull a contact slightly inside or outside the curve. Its radial
        // correction must not be interpreted as the direction in which it is travelling.
        for (double offset : new double[] {-0.1, 0.1}) {
            for (int direction : new int[] {-1, 1}) {
                double radius = 8.5;
                double angle = Math.PI / 4;
                TestBody body = new TestBody(
                        (radius + offset) * Math.cos(angle), 64,
                        (radius + offset) * Math.sin(angle));
                body.mx = -Math.sin(angle) * 0.01 * direction;
                body.mz = Math.cos(angle) * 0.01 * direction;
                TrackMovement.moveOnTurn(body, 64, radius, 0, 0);
                assertTrue(body.mx * direction < 0, "curve reversed X");
                assertTrue(body.mz * direction > 0, "curve reversed Z");
            }
        }
    }

    @Test
    @DisplayName("a slope lifts the body by the tangent of the angle times the distance run")
    void slopeFollowsTheRamp() {
        double angle = 0.1;
        TestBody body = new TestBody(0.5, 64.0, 3.0);
        body.mz = 0.1;
        body.locomotive = true;

        TrackMovement.moveOnSlope(body, 64, 0, 0, angle, 0);

        // Foot of the slope at z = 0, so three blocks along it.
        assertEquals(64 + Math.tan(angle * 3.0) + 0.65 + 0.3, body.y, 1.0E-9);
    }

    @Test
    @DisplayName("unpowered stock gains speed downhill and loses it uphill; a locomotive does not")
    void slopeAssistsOnlyUnpoweredStock() {
        TestBody wagon = new TestBody(0.5, 64.0, 3.0);
        wagon.mz = 0.1;
        TrackMovement.moveOnSlope(wagon, 64, 0, 0, 0.1, 0);

        TestBody loco = new TestBody(0.5, 64.0, 3.0);
        loco.mz = 0.1;
        loco.locomotive = true;
        TrackMovement.moveOnSlope(loco, 64, 0, 0, 0.1, 0);

        assertEquals(0.1, Math.abs(loco.mz), 1.0E-9, "a locomotive is unaffected by the grade");
        assertTrue(Math.abs(wagon.mz) != 0.1, "a wagon is not");
    }

    @Test
    @DisplayName("the track drags unpowered stock and leaves a locomotive alone")
    void dragAppliesOnlyToUnpoweredStock() {
        TestBody wagon = new TestBody(0, 64, 0);
        wagon.mz = 0.2;
        TrackMovement.limitSpeed(wagon, 3.0, true);
        assertEquals(0.18, wagon.mz, 1.0E-9, "a tenth off every tick");

        TestBody loco = new TestBody(0, 64, 0);
        loco.mz = 0.2;
        TrackMovement.limitSpeed(loco, 3.0, false);
        assertEquals(0.2, loco.mz, 1.0E-9, "a locomotive keeps what it has");
    }

    @Test
    @DisplayName("the speed ceiling clamps both axes and both directions")
    void speedIsClamped() {
        TestBody body = new TestBody(0, 64, 0);
        body.mx = 5.0;
        body.mz = -5.0;

        TrackMovement.limitSpeed(body, 0.148, false);

        assertEquals(0.148, body.mx, 1.0E-9);
        assertEquals(-0.148, body.mz, 1.0E-9);
    }

    @Test
    @DisplayName("a locomotive reaches its ceiling from a standstill, and in a plausible time")
    void throttleReachesTheCeiling() {
        // The acceleration model outside the entity: Alice's impulse of 0.0075 * 0.5 applied on
        // one tick in four, with no drag and a ceiling of 0.148. This is the arithmetic that made
        // the first attempt pin the locomotive at a fiftieth of its speed, so it is worth an
        // assertion rather than a screenshot.
        TestBody body = new TestBody(0, 64, 0);
        body.locomotive = true;
        double impulse = 0.0075 * 0.5;
        double ceiling = 32 * 0.2775 / 6.0 / 10.0;

        int ticks = 0;
        while (TrackMovement.planarSpeed(body) < ceiling - 1.0E-6 && ticks < 20 * 60) {
            if (ticks % 4 == 0) {
                body.mz += impulse;
            }
            TrackMovement.limitSpeed(body, ceiling, false);
            ticks++;
        }

        assertEquals(ceiling, TrackMovement.planarSpeed(body), 1.0E-6);
        assertTrue(ticks < 20 * 20, "took " + ticks + " ticks to reach full speed");
        assertTrue(ticks > 20, "reached full speed in " + ticks + " ticks, which is instant");
    }

    private static final TrackMovement.Solid NOTHING_SOLID = (x, y, z) -> false;

    /** Plain rail, no golden or detector behaviour, everything a full body would do. */
    private static boolean runVanilla(TestBody body, int i, int j, int k, int shape) {
        return TrackMovement.moveOnVanillaRail(
                body,
                i,
                j,
                k,
                shape,
                false,
                false,
                true,
                true,
                15.0,
                body.locomotive ? 1.0 : 0.99,
                body.locomotive ? 1.0 : 0.98,
                body.locomotive,
                0.46,
                NOTHING_SOLID);
    }

    @Test
    @DisplayName("a vanilla straight along Z pins X and settles at the rail's own height")
    void vanillaStraightAlongZ() {
        TestBody body = new TestBody(0.9, 64.85, 4.4);
        body.mz = 0.2;
        body.mx = 0.05;

        assertEquals(false, runVanilla(body, 0, 64, 4, 0));

        assertEquals(0.5, body.x, 1.0E-9, "pulled onto the centre of the rail column");
        assertEquals(64.85, body.y, 1.0E-9, "the rail top plus the offset every body carries");
        assertEquals(0.0, body.mx, 1.0E-9, "the cross-axis component is dropped");
        assertTrue(body.mz > 0.0, "still travelling the way it was");
    }

    @Test
    @DisplayName("a cart holds a vanilla curve; a locomotive above the threshold does not")
    void vanillaCurveDerails() {
        TestBody cart = new TestBody(0.5, 64.85, 4.9);
        cart.mz = 0.6;
        assertEquals(false, runVanilla(cart, 0, 64, 4, 6), "a cart is never thrown off");

        TestBody loco = new TestBody(0.5, 64.85, 4.9);
        loco.locomotive = true;
        loco.mz = 0.6;
        assertTrue(runVanilla(loco, 0, 64, 4, 6), "0.6 is over upstream's 0.46");

        TestBody slow = new TestBody(0.5, 64.85, 4.9);
        slow.locomotive = true;
        slow.mz = 0.2;
        assertEquals(false, runVanilla(slow, 0, 64, 4, 6), "0.2 is under it");
    }

    @Test
    @DisplayName("an unpowered golden rail stops anything already crawling")
    void unpoweredGoldenRailStops() {
        TestBody body = new TestBody(0.5, 64.85, 4.5);
        body.mz = 0.02;

        TrackMovement.moveOnVanillaRail(
                body, 0, 64, 4, 0, true, false, true, true, 15.0, 0.99, 0.98, false, 0.46,
                NOTHING_SOLID);

        assertEquals(0.0, body.mz, 1.0E-9);
        assertEquals(0.0, body.mx, 1.0E-9);
    }
}
