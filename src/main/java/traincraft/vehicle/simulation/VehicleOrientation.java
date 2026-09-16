package traincraft.vehicle.simulation;

/** Minecraft yaw: south is zero, west is ninety degrees. */
public final class VehicleOrientation {
    private VehicleOrientation() {}

    public static float fromDirection(double x, double z) {
        return (float) Math.toDegrees(Math.atan2(-x, z));
    }

    public static float nearestDirection(float axis, float current) {
        float difference = (float) Math.IEEEremainder(axis - current, 360.0);
        if (difference > 90.0F) difference -= 180.0F;
        if (difference < -90.0F) difference += 180.0F;
        return current + difference;
    }
}
