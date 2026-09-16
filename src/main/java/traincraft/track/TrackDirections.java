package traincraft.track;

import java.util.EnumMap;
import java.util.Map;

final class TrackDirections {

    static final Map<TrackType, Map<String, TrackType>> VARIANTS = new EnumMap<>(TrackType.class);

    private TrackDirections() {}

    static {
        put(TrackType.TURN_1X1, TrackType.RIGHT_TURN_1X1, TrackType.LEFT_TURN_1X1);
        put(TrackType.MEDIUM_TURN, TrackType.MEDIUM_RIGHT_TURN, TrackType.MEDIUM_LEFT_TURN);
        put(TrackType.LARGE_TURN, TrackType.LARGE_RIGHT_TURN, TrackType.LARGE_LEFT_TURN);
        put(
                TrackType.VERY_LARGE_TURN,
                TrackType.VERY_LARGE_RIGHT_TURN,
                TrackType.VERY_LARGE_LEFT_TURN);
        put(
                TrackType.SUPER_LARGE_TURN,
                TrackType.SUPER_LARGE_RIGHT_TURN,
                TrackType.SUPER_LARGE_LEFT_TURN);
        put(TrackType.TURN_29X29, TrackType.RIGHT_TURN_29X29, TrackType.LEFT_TURN_29X29);
        put(TrackType.TURN_32X32, TrackType.RIGHT_TURN_32X32, TrackType.LEFT_TURN_32X32);
        put(
                TrackType.MEDIUM_45DEGREE_TURN,
                TrackType.MEDIUM_RIGHT_45DEGREE_TURN,
                TrackType.MEDIUM_LEFT_45DEGREE_TURN);
        put(
                TrackType.LARGE_45DEGREE_TURN,
                TrackType.LARGE_RIGHT_45DEGREE_TURN,
                TrackType.LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.VERY_LARGE_45DEGREE_TURN,
                TrackType.VERY_LARGE_RIGHT_45DEGREE_TURN,
                TrackType.VERY_LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.SUPER_LARGE_45DEGREE_TURN,
                TrackType.SUPER_LARGE_RIGHT_45DEGREE_TURN,
                TrackType.SUPER_LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.SMALL_PARALLEL_CURVE,
                TrackType.SMALL_RIGHT_PARALLEL_CURVE,
                TrackType.SMALL_LEFT_PARALLEL_CURVE);
        put(
                TrackType.MEDIUM_PARALLEL_CURVE,
                TrackType.MEDIUM_RIGHT_PARALLEL_CURVE,
                TrackType.MEDIUM_LEFT_PARALLEL_CURVE);
        put(
                TrackType.LARGE_PARALLEL_CURVE,
                TrackType.LARGE_RIGHT_PARALLEL_CURVE,
                TrackType.LARGE_LEFT_PARALLEL_CURVE);
        put(TrackType.MEDIUM_SWITCH, TrackType.MEDIUM_RIGHT_SWITCH, TrackType.MEDIUM_LEFT_SWITCH);
        put(TrackType.LARGE_SWITCH, TrackType.LARGE_RIGHT_SWITCH, TrackType.LARGE_LEFT_SWITCH);
        put(
                TrackType.VERY_LARGE_SWITCH,
                TrackType.VERY_LARGE_RIGHT_SWITCH,
                TrackType.VERY_LARGE_LEFT_SWITCH);
        put(
                TrackType.MEDIUM_PARALLEL_SWITCH,
                TrackType.MEDIUM_RIGHT_PARALLEL_SWITCH,
                TrackType.MEDIUM_LEFT_PARALLEL_SWITCH);
        put(
                TrackType.MEDIUM_45DEGREE_SWITCH,
                TrackType.MEDIUM_RIGHT_45DEGREE_SWITCH,
                TrackType.MEDIUM_LEFT_45DEGREE_SWITCH);
        put(
                TrackType.EMBEDDED_TURN_1X1,
                TrackType.EMBEDDED_RIGHT_TURN_1X1,
                TrackType.EMBEDDED_LEFT_TURN_1X1);
        put(
                TrackType.EMBEDDED_MEDIUM_TURN,
                TrackType.EMBEDDED_MEDIUM_RIGHT_TURN,
                TrackType.EMBEDDED_MEDIUM_LEFT_TURN);
        put(
                TrackType.EMBEDDED_LARGE_TURN,
                TrackType.EMBEDDED_LARGE_RIGHT_TURN,
                TrackType.EMBEDDED_LARGE_LEFT_TURN);
        put(
                TrackType.EMBEDDED_VERY_LARGE_TURN,
                TrackType.EMBEDDED_VERY_LARGE_RIGHT_TURN,
                TrackType.EMBEDDED_VERY_LARGE_LEFT_TURN);
        put(
                TrackType.EMBEDDED_SUPER_LARGE_TURN,
                TrackType.EMBEDDED_SUPER_LARGE_RIGHT_TURN,
                TrackType.EMBEDDED_SUPER_LARGE_LEFT_TURN);
        put(
                TrackType.EMBEDDED_TURN_32X32,
                TrackType.EMBEDDED_RIGHT_TURN_32X32,
                TrackType.EMBEDDED_LEFT_TURN_32X32);
        put(
                TrackType.EMBEDDED_MEDIUM_45DEGREE_TURN,
                TrackType.EMBEDDED_MEDIUM_RIGHT_45DEGREE_TURN,
                TrackType.EMBEDDED_MEDIUM_LEFT_45DEGREE_TURN);
        put(
                TrackType.EMBEDDED_LARGE_45DEGREE_TURN,
                TrackType.EMBEDDED_LARGE_RIGHT_45DEGREE_TURN,
                TrackType.EMBEDDED_LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.EMBEDDED_VERY_LARGE_45DEGREE_TURN,
                TrackType.EMBEDDED_VERY_LARGE_RIGHT_45DEGREE_TURN,
                TrackType.EMBEDDED_VERY_LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.EMBEDDED_SUPER_LARGE_45DEGREE_TURN,
                TrackType.EMBEDDED_SUPER_LARGE_RIGHT_45DEGREE_TURN,
                TrackType.EMBEDDED_SUPER_LARGE_LEFT_45DEGREE_TURN);
        put(
                TrackType.EMBEDDED_SMALL_PARALLEL_CURVE,
                TrackType.EMBEDDED_SMALL_RIGHT_PARALLEL_CURVE,
                TrackType.EMBEDDED_SMALL_LEFT_PARALLEL_CURVE);
        put(
                TrackType.EMBEDDED_MEDIUM_PARALLEL_CURVE,
                TrackType.EMBEDDED_MEDIUM_RIGHT_PARALLEL_CURVE,
                TrackType.EMBEDDED_MEDIUM_LEFT_PARALLEL_CURVE);
        put(
                TrackType.EMBEDDED_LARGE_PARALLEL_CURVE,
                TrackType.EMBEDDED_LARGE_RIGHT_PARALLEL_CURVE,
                TrackType.EMBEDDED_LARGE_LEFT_PARALLEL_CURVE);
        put(
                TrackType.EMBEDDED_MEDIUM_SWITCH,
                TrackType.EMBEDDED_MEDIUM_RIGHT_SWITCH,
                TrackType.EMBEDDED_MEDIUM_LEFT_SWITCH);
        put(
                TrackType.EMBEDDED_LARGE_SWITCH,
                TrackType.EMBEDDED_LARGE_RIGHT_SWITCH,
                TrackType.EMBEDDED_LARGE_LEFT_SWITCH);
        put(
                TrackType.EMBEDDED_VERY_LARGE_SWITCH,
                TrackType.EMBEDDED_VERY_LARGE_RIGHT_SWITCH,
                TrackType.EMBEDDED_VERY_LARGE_LEFT_SWITCH);
        put(
                TrackType.EMBEDDED_MEDIUM_PARALLEL_SWITCH,
                TrackType.EMBEDDED_MEDIUM_RIGHT_PARALLEL_SWITCH,
                TrackType.EMBEDDED_MEDIUM_LEFT_PARALLEL_SWITCH);
        put(
                TrackType.EMBEDDED_MEDIUM_45DEGREE_SWITCH,
                TrackType.EMBEDDED_MEDIUM_RIGHT_45DEGREE_SWITCH,
                TrackType.EMBEDDED_MEDIUM_LEFT_45DEGREE_SWITCH);
        put(
                TrackType.LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.EMBEDDED_LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.VERY_LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.EMBEDDED_VERY_LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.SUPER_LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.EMBEDDED_SUPER_LARGE_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC,
                TrackType.EMBEDDED_SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC);
        put(
                TrackType.DIAMOND_CROSSING,
                TrackType.RIGHT_DIAMOND_CROSSING,
                TrackType.LEFT_DIAMOND_CROSSING);
        put(
                TrackType.EMBEDDED_DIAMOND_CROSSING,
                TrackType.EMBEDDED_RIGHT_DIAMOND_CROSSING,
                TrackType.EMBEDDED_LEFT_DIAMOND_CROSSING);
    }

    private static void put(TrackType base, TrackType right, TrackType left) {
        VARIANTS.put(base, Map.of("right", right, "left", left));
    }
}
