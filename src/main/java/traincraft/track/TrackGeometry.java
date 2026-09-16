package traincraft.track;

import org.jspecify.annotations.Nullable;

/** Curve centre uses the same coordinate frame as its containing placement or block entity. */
public record TrackGeometry(@Nullable Curve curve, @Nullable Slope slope) {
    public static final TrackGeometry STRAIGHT = new TrackGeometry(null, null);

    public record Curve(double centerX, double centerY, double centerZ, double radius) {
        public Curve {
            if (!Double.isFinite(centerX)
                    || !Double.isFinite(centerY)
                    || !Double.isFinite(centerZ)
                    || !Double.isFinite(radius)
                    || radius < 0) throw new IllegalArgumentException("Invalid curve");
        }
    }

    public record Slope(double height, double length, double angle) {
        public Slope {
            if (!Double.isFinite(height)
                    || !Double.isFinite(length)
                    || !Double.isFinite(angle)
                    || length <= 0) throw new IllegalArgumentException("Invalid slope");
        }
    }

    public TrackGeometry translated(double x, double y, double z) {
        return curve == null
                ? this
                : new TrackGeometry(
                        new Curve(
                                curve.centerX() + x,
                                curve.centerY() + y,
                                curve.centerZ() + z,
                                curve.radius()),
                        slope);
    }
}
