package traincraft.vehicle.simulation;

/**
 * What happens along the rail when two uncoupled pieces of stock meet buffer to buffer.
 *
 * <p>Everything here is one-dimensional: speeds are measured along the axis from the first body
 * to the second, so a positive closing speed means they are running into each other. Masses come
 * in as their inverses, which lets a body that is held in place -- a locomotive with its brake on
 * -- take part as an infinitely heavy one with an inverse of zero.
 */
public final class ContactResponse {

    /** Ten kilometres an hour, in blocks per tick: the speed at which two trains stop bouncing. */
    public static final double DERAIL_CLOSING_SPEED = 10.0 / 216.0;

    /**
     * The share of its own speed a train gives up each tick to the loose stock it pushes, scaled
     * by how much of the moving mass that stock is. Small: loose stock rolls easily.
     */
    public static final double PUSH_RESISTANCE = 0.01;

    private ContactResponse() {}

    public record Axial(double first, double second) {}

    /**
     * The speeds after a buffer contact with no bounce.
     *
     * <p>The contact is speculative: the bodies may still close by {@code gap} this tick, which is
     * what lets them come to rest exactly buffer to buffer instead of stopping short of each
     * other or overlapping first and being pushed apart afterwards.
     */
    public static Axial plastic(
            double first, double second, double inverseFirst, double inverseSecond, double gap) {
        double total = inverseFirst + inverseSecond;
        double excess = (first - second) - Math.max(gap, 0.0);
        if (total <= 0.0 || excess <= 0.0) {
            return new Axial(first, second);
        }
        double impulse = excess / total;
        return new Axial(first - impulse * inverseFirst, second + impulse * inverseSecond);
    }

    /** How far each body moves out of an overlap: back for the first, forward for the second. */
    public static Axial split(double overlap, double inverseFirst, double inverseSecond) {
        double total = inverseFirst + inverseSecond;
        if (overlap <= 0.0 || total <= 0.0) {
            return new Axial(0.0, 0.0);
        }
        return new Axial(overlap * inverseFirst / total, overlap * inverseSecond / total);
    }

    public static boolean derails(double closingSpeed) {
        return closingSpeed >= DERAIL_CLOSING_SPEED;
    }

    /** The factor a pushing train's speed is multiplied by for the loose stock ahead of it. */
    public static double pushingFactor(double trainMass, double pushedMass) {
        if (pushedMass <= 0.0) {
            return 1.0;
        }
        return 1.0 - PUSH_RESISTANCE * pushedMass / (trainMass + pushedMass);
    }
}
