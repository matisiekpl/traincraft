package traincraft.vehicle.animation;

/** Per-vehicle animation advanced once per game tick and sampled without mutation. */
public final class WheelAnimation {
    private double angle;
    private double step;

    public void tick(double signedDistance) {
        angle = Math.IEEEremainder(angle + step, Math.PI * 2);
        step = signedDistance * 0.1;
    }

    public float sample(float partialTick) {
        return (float) (angle + step * Math.clamp(partialTick, 0, 1));
    }
}
