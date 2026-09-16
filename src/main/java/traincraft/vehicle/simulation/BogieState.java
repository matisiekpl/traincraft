package traincraft.vehicle.simulation;

import traincraft.track.TrackMovement;

public final class BogieState implements TrackMovement.Body {

    private final double yOffset;

    private double x;
    private double y;
    private double z;
    private double motionX;
    private double motionY;
    private double motionZ;

    public BogieState(double yOffset) {
        this.yOffset = yOffset;
    }

    public void placeRelativeTo(double bodyX, double bodyY, double bodyZ, float yaw, double shift) {
        double radians = Math.toRadians(yaw);
        x = bodyX + Math.sin(radians) * shift;
        y = bodyY;
        z = bodyZ - Math.cos(radians) * shift;
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
        return motionX;
    }

    @Override
    public double motionY() {
        return motionY;
    }

    @Override
    public double motionZ() {
        return motionZ;
    }

    @Override
    public void setMotion(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    /**
     * Moves without consulting the world.
     *
     * <p>Deliberate: the bogie is inside the body's own footprint, and a collision check here would
     * have it stop against the locomotive it belongs to. The track keeps it honest -- it is snapped
     * onto the rail every tick by the same code that snaps the body.
     */
    @Override
    public void moveBy(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    @Override
    public double yOffset() {
        return yOffset;
    }

    @Override
    public float yaw() {
        return 0.0F;
    }

    /** Exempt from the slope's rolling assistance, like the locomotive it belongs to. */
    @Override
    public boolean isLocomotive() {
        return true;
    }
}
