package traincraft.vehicle.simulation;

/**
 * Community Edition's {@code SpeedHandler}: what a piece of stock is allowed to do on the rail it
 * is standing on, in blocks per tick.
 *
 * <p>Only a locomotive is rated against its own top speed; everything else is handed fifteen, which
 * is far above anything a cart reaches and is upstream's way of saying "no ceiling here". The three
 * bands come from the rails upstream could be running on: vanilla rail reports 0.4 and is taken as
 * the ceiling when the locomotive would exceed it, Railcraft's high-speed track reports between
 * 0.45 and 1.1 and earns a fifth of a block a tick on top, and Traincraft's own track reports three,
 * which leaves the locomotive on its own figure.
 */
public final class SpeedHandler {

    private SpeedHandler() {}

    public static double handleSpeed(
            double railMaxSpeed, boolean locomotive, double locomotiveMaxSpeedKmH) {
        return handleSpeed(railMaxSpeed, locomotive, locomotiveMaxSpeedKmH, false);
    }

    /** {@code realTrainSpeed} is CE's {@code REAL_TRAIN_SPEED}: a third of the scaling, three times the speed. */
    public static double handleSpeed(
            double railMaxSpeed, boolean locomotive, double locomotiveMaxSpeedKmH, boolean realTrainSpeed) {
        if (!locomotive) {
            return 15.0;
        }
        double converted = convertSpeed(locomotiveMaxSpeedKmH, realTrainSpeed);
        if (railMaxSpeed < 0.4) {
            return Math.min(converted, railMaxSpeed);
        }
        if (railMaxSpeed > 0.45 && railMaxSpeed < 1.1) {
            return converted + 0.2;
        }
        return converted;
    }

    /** Kilometres an hour to blocks a tick, on upstream's scale. */
    public static double convertSpeed(double kilometresPerHour, boolean realTrainSpeed) {
        return kilometresPerHour * 0.2775 / (realTrainSpeed ? 2.0 : 6.0) / 10.0;
    }
}
