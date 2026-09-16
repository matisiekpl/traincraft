package traincraft.vehicle.simulation;

/**
 * The coupling between two pieces of stock: a spring that pulls them back to their optimal
 * distance, and a damper that takes the difference out of their velocities.
 *
 * <p>Transcribed from Community Edition's {@code LinkHandler.StakePhysic}. Three details are
 * deliberate and must not be tidied: the spring term multiplies the raw separation vector while
 * the damper multiplies the unit vector, every component is clamped to fourteen, and the damper is
 * measured after the spring has already been applied -- which is why the two are separate calls
 * rather than one.
 */
public record CouplingSpring(double x, double z) {

    private static final double SPRING = 0.1;
    private static final double DAMPING = 0.4;
    private static final double LIMIT = 14.0;

    public static CouplingSpring spring(
            double separationX, double separationZ, double optimalDistance) {
        double stretch =
                Math.sqrt(separationX * separationX + separationZ * separationZ) - optimalDistance;
        return new CouplingSpring(
                limitForce(SPRING * stretch * separationX * -1.0),
                limitForce(SPRING * stretch * separationZ * -1.0));
    }

    public static CouplingSpring damping(
            double relativeMotionX, double relativeMotionZ, double unitX, double unitZ) {
        double dot = relativeMotionX * unitX + relativeMotionZ * unitZ;
        return new CouplingSpring(
                limitForce(DAMPING * dot * unitX * -1.0), limitForce(DAMPING * dot * unitZ * -1.0));
    }

    private static double limitForce(double force) {
        return Math.copySign(Math.abs(Math.min(Math.abs(force), LIMIT)), force);
    }
}
