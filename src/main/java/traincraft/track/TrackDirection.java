package traincraft.track;

import org.jspecify.annotations.Nullable;

import java.util.Map;

public final class TrackDirection {

    private TrackDirection() {}

    /**
     * The variant a player facing {@code yaw} means when they lay {@code base}.
     *
     * @param facing the quantised facing, {@code floor(yaw * 4 / 360 + 0.5) & 3}
     * @param yaw the player's yaw, wrapped to [-180, 180]
     * @return the left or right variant, or {@code base} when the type has no handedness
     */
    public static TrackType resolve(TrackType base, int facing, float yaw) {
        Map<String, TrackType> variants = TrackDirections.VARIANTS.get(base);
        if (variants == null) {
            return base;
        }
        String side = orientation(facing, wrapDegrees(yaw));
        TrackType resolved = variants.get(side);
        return resolved == null ? base : resolved;
    }

    /** True when this type bends, so a caller can tell a turn from a straight without a table. */
    public static boolean hasHandedness(TrackType base) {
        return TrackDirections.VARIANTS.containsKey(base);
    }

    /** The left or right variant, for callers that already know which side they want. */
    public static @Nullable TrackType variant(TrackType base, String side) {
        Map<String, TrackType> variants = TrackDirections.VARIANTS.get(base);
        return variants == null ? null : variants.get(side);
    }

    public static String orientation(int facing, float yaw) {
        if (facing == 2 && yaw >= -180.0F && yaw <= -135.0F) {
            return "right";
        } else if (facing == 2 && yaw <= 180.0F && yaw >= 135.0F) {
            return "left";
        } else if (facing == 3 && yaw > -135.0F && yaw <= -90.0F) {
            return "left";
        } else if (facing == 3 && yaw > -90.0F && yaw <= -45.0F) {
            return "right";
        } else if (facing == 0 && yaw > -45.0F && yaw <= 0.0F) {
            return "left";
        } else if (facing == 0 && yaw > 0.0F && yaw <= 45.0F) {
            return "right";
        } else if (facing == 1 && yaw > 45.0F && yaw <= 90.0F) {
            return "left";
        } else {
            return facing == 1 && yaw > 90.0F && yaw <= 135.0F ? "right" : "";
        }
    }

    public static float wrapDegrees(float yaw) {
        float wrapped = yaw % 360.0F;
        if (wrapped >= 180.0F) {
            wrapped -= 360.0F;
        }
        if (wrapped < -180.0F) {
            wrapped += 360.0F;
        }
        return wrapped;
    }
}
