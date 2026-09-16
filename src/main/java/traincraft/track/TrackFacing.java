package traincraft.track;

import net.minecraft.core.Direction;

public final class TrackFacing {

    private TrackFacing() {}

    public static Direction toDirection(int facingMeta) {
        return Direction.from2DDataValue(facingMeta & 3);
    }

    public static int toMeta(Direction direction) {
        return direction.get2DDataValue();
    }

    public static int fromYaw(float yaw) {
        return (int) Math.floor(yaw * 4.0F / 360.0F + 0.5D) & 3;
    }
}
