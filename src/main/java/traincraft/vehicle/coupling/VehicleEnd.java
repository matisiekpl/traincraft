package traincraft.vehicle.coupling;

/**
 * Which end of a piece of rolling stock a coupling sits on.
 *
 * <p>The names are the model's own: {@code FRONT} is the end at {@code VehicleBounds.front()},
 * which is the end the body's parts run towards, not the direction the yaw points in.
 */
public enum VehicleEnd {
    FRONT,
    BACK;

    public static VehicleEnd byOrdinal(int ordinal) {
        return values()[Math.floorMod(ordinal, values().length)];
    }

    public String displayName() {
        return this == FRONT ? "Front" : "Rear";
    }
}
