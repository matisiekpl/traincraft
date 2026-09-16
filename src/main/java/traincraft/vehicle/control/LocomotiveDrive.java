package traincraft.vehicle.control;

/** Held controls and horizontal acceleration, in blocks per tick. */
public final class LocomotiveDrive {
    private static final double ACCELERATION_IMPULSE = 0.0075;

    public enum Control {
        FORWARD,
        BACKWARD,
        BRAKE
    }

    public record Motion(double x, double z) {}

    private boolean forward;
    private boolean backward;
    private boolean brake;

    public void setHeld(Control control, boolean held) {
        switch (control) {
            case FORWARD -> forward = held;
            case BACKWARD -> backward = held;
            case BRAKE -> brake = held;
        }
    }

    public boolean braking() {
        return brake;
    }

    public boolean throttling() {
        return forward || backward;
    }

    public Motion accelerate(double x, double z, float yaw, double rate) {
        if (!throttling()) return new Motion(x, z);
        double impulse = ACCELERATION_IMPULSE * rate * (forward ? 1 : -1);
        return switch (Math.floorMod(Math.round(yaw / 90.0F), 4)) {
            case 0 -> new Motion(x, z + impulse);
            case 1 -> new Motion(x - impulse, z);
            case 2 -> new Motion(x, z - impulse);
            default -> new Motion(x + impulse, z);
        };
    }
}
