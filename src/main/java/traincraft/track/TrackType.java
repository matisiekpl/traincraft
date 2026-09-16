package traincraft.track;

import java.util.HashMap;
import java.util.Map;

public enum TrackType {
    SMALL_STRAIGHT("SMALL_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x1"),
    MEDIUM_STRAIGHT("MEDIUM_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x3"),
    LONG_STRAIGHT("LONG_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x6"),
    VERY_LONG_STRAIGHT(
            "VERY_LONG_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x12"),
    SMALL_DIAGONAL_STRAIGHT(
            "SMALL_DIAGONAL_STRAIGHT", TrackCategory.DIAGONAL, TrackShapeGroup.DIAGONAL, "1x1"),
    MEDIUM_DIAGONAL_STRAIGHT(
            "MEDIUM_DIAGONAL_STRAIGHT", TrackCategory.DIAGONAL, TrackShapeGroup.DIAGONAL, "1x3"),
    LONG_DIAGONAL_STRAIGHT(
            "LONG_DIAGONAL_STRAIGHT", TrackCategory.DIAGONAL, TrackShapeGroup.DIAGONAL, "1x6"),
    VERY_LONG_DIAGONAL_STRAIGHT(
            "VERY_LONG_DIAGONAL_STRAIGHT",
            TrackCategory.DIAGONAL,
            TrackShapeGroup.DIAGONAL,
            "1x12"),
    TURN_1X1("TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "1x1"),
    LEFT_TURN_1X1("LEFT_TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    RIGHT_TURN_1X1("RIGHT_TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    MEDIUM_TURN("MEDIUM_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "3x3"),
    MEDIUM_RIGHT_TURN("MEDIUM_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    MEDIUM_LEFT_TURN("MEDIUM_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    LARGE_TURN("LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "5x5"),
    LARGE_RIGHT_TURN("LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    LARGE_LEFT_TURN("LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    VERY_LARGE_TURN("VERY_LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "10x10"),
    VERY_LARGE_RIGHT_TURN(
            "VERY_LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    VERY_LARGE_LEFT_TURN("VERY_LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    SUPER_LARGE_TURN("SUPER_LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "16x16"),
    SUPER_LARGE_LEFT_TURN(
            "SUPER_LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    SUPER_LARGE_RIGHT_TURN(
            "SUPER_LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    TURN_29X29("TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "29x29"),
    LEFT_TURN_29X29("LEFT_TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    RIGHT_TURN_29X29("RIGHT_TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    TURN_32X32("TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "32x32"),
    LEFT_TURN_32X32("LEFT_TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    RIGHT_TURN_32X32("RIGHT_TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    MEDIUM_45DEGREE_TURN(
            "MEDIUM_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "3x4 hold sneak to attach to the back of another curve"),
    MEDIUM_RIGHT_45DEGREE_TURN(
            "MEDIUM_RIGHT_45DEGREE_TURN", TrackCategory.SHARP_TURN, TrackShapeGroup.TURN_RIGHT, ""),
    MEDIUM_LEFT_45DEGREE_TURN(
            "MEDIUM_LEFT_45DEGREE_TURN", TrackCategory.SHARP_TURN, TrackShapeGroup.TURN_LEFT, ""),
    LARGE_45DEGREE_TURN(
            "LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "3x6 hold sneak to attach to the back of another curve"),
    LARGE_RIGHT_45DEGREE_TURN(
            "LARGE_RIGHT_45DEGREE_TURN", TrackCategory.SHARP_TURN, TrackShapeGroup.TURN_RIGHT, ""),
    LARGE_LEFT_45DEGREE_TURN(
            "LARGE_LEFT_45DEGREE_TURN", TrackCategory.SHARP_TURN, TrackShapeGroup.TURN_LEFT, ""),
    VERY_LARGE_45DEGREE_TURN(
            "VERY_LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "4x8 hold sneak to attach to the back of another curve"),
    VERY_LARGE_RIGHT_45DEGREE_TURN(
            "VERY_LARGE_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    VERY_LARGE_LEFT_45DEGREE_TURN(
            "VERY_LARGE_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    SUPER_LARGE_45DEGREE_TURN(
            "SUPER_LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "5x11 hold sneak to attach to the back of another curve"),
    SUPER_LARGE_RIGHT_45DEGREE_TURN(
            "SUPER_LARGE_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    SUPER_LARGE_LEFT_45DEGREE_TURN(
            "SUPER_LARGE_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    SMALL_PARALLEL_CURVE(
            "SMALL_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, "2x8"),
    SMALL_RIGHT_PARALLEL_CURVE(
            "SMALL_RIGHT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, ""),
    SMALL_LEFT_PARALLEL_CURVE(
            "SMALL_LEFT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_LEFT, ""),
    MEDIUM_PARALLEL_CURVE(
            "MEDIUM_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, "3x12"),
    MEDIUM_RIGHT_PARALLEL_CURVE(
            "MEDIUM_RIGHT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, ""),
    MEDIUM_LEFT_PARALLEL_CURVE(
            "MEDIUM_LEFT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_LEFT, ""),
    LARGE_PARALLEL_CURVE(
            "LARGE_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, "4x16"),
    LARGE_RIGHT_PARALLEL_CURVE(
            "LARGE_RIGHT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_RIGHT, ""),
    LARGE_LEFT_PARALLEL_CURVE(
            "LARGE_LEFT_PARALLEL_CURVE", TrackCategory.CURVE, TrackShapeGroup.TURN_LEFT, ""),
    TWO_WAYS_CROSSING("TWO_WAYS_CROSSING", TrackCategory.CROSSING, TrackShapeGroup.CROSSING, "3x3"),
    DIAMOND_CROSSING(
            "DIAMOND_CROSSING", TrackCategory.DIAGONAL_CROSSING, TrackShapeGroup.CROSSING, "3x3"),
    RIGHT_DIAMOND_CROSSING(
            "RIGHT_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    LEFT_DIAMOND_CROSSING(
            "LEFT_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    DOUBLE_DIAMOND_CROSSING(
            "DOUBLE_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    DIAGONAL_TWO_WAYS_CROSSING(
            "DIAGONAL_TWO_WAYS_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    FOUR_WAYS_CROSSING(
            "FOUR_WAYS_CROSSING", TrackCategory.DIAGONAL_CROSSING, TrackShapeGroup.CROSSING, "3x3"),
    MEDIUM_SWITCH("MEDIUM_RIGHT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "4x4"),
    MEDIUM_RIGHT_SWITCH("MEDIUM_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    MEDIUM_LEFT_SWITCH("MEDIUM_LEFT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    LARGE_SWITCH("LARGE_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "6x6"),
    LARGE_RIGHT_SWITCH(
            "LARGE_RIGHT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    LARGE_LEFT_SWITCH("LARGE_LEFT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    VERY_LARGE_SWITCH(
            "VERY_LARGE_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "11x11"),
    VERY_LARGE_RIGHT_SWITCH(
            "VERY_LARGE_RIGHT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    VERY_LARGE_LEFT_SWITCH(
            "VERY_LARGE_LEFT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    MEDIUM_PARALLEL_SWITCH(
            "MEDIUM_PARALLEL_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "4x11"),
    MEDIUM_RIGHT_PARALLEL_SWITCH(
            "MEDIUM_RIGHT_PARALLEL_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    MEDIUM_LEFT_PARALLEL_SWITCH(
            "MEDIUM_LEFT_PARALLEL_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    MEDIUM_45DEGREE_SWITCH(
            "MEDIUM_45DEGREE_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            "3x5 hold sneak to attach to the back of another curve"),
    MEDIUM_RIGHT_45DEGREE_SWITCH(
            "MEDIUM_RIGHT_45DEGREE_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    MEDIUM_LEFT_45DEGREE_SWITCH(
            "MEDIUM_LEFT_45DEGREE_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    SLOPE_WOOD("SLOPE_WOOD", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    LARGE_SLOPE_WOOD("LARGE_SLOPE_WOOD", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    VERY_LARGE_SLOPE_WOOD(
            "VERY_LARGE_SLOPE_WOOD", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x18"),
    SLOPE_GRAVEL("SLOPE_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    LARGE_SLOPE_GRAVEL("LARGE_SLOPE_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    VERY_LARGE_SLOPE_GRAVEL(
            "VERY_LARGE_SLOPE_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x18"),
    SLOPE_BALLAST("SLOPE_BALLAST", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    LARGE_SLOPE_BALLAST("LARGE_SLOPE_BALLAST", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    VERY_LARGE_SLOPE_BALLAST(
            "VERY_LARGE_SLOPE_BALLAST", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x18"),
    SLOPE_SNOW_GRAVEL("SLOPE_SNOW_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    LARGE_SLOPE_SNOW_GRAVEL(
            "LARGE_SLOPE_SNOW_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    VERY_LARGE_SLOPE_SNOW_GRAVEL(
            "VERY_LARGE_SLOPE_SNOW_GRAVEL", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x18"),
    SLOPE_DYNAMIC("SLOPE_DYNAMIC", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    LARGE_SLOPE_DYNAMIC("LARGE_SLOPE_DYNAMIC", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    VERY_LARGE_SLOPE_DYNAMIC(
            "VERY_LARGE_SLOPE_DYNAMIC", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x18"),
    LARGE_CURVED_SLOPE_DYNAMIC(
            "LARGE_CURVED_SLOPE_DYNAMIC", TrackCategory.CURVED_SLOPE, TrackShapeGroup.SLOPE, "5x5"),
    LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    VERY_LARGE_CURVED_SLOPE_DYNAMIC(
            "VERY_LARGE_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "9x9"),
    VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    SUPER_LARGE_CURVED_SLOPE_DYNAMIC(
            "SUPER_LARGE_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "16x16"),
    SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    EMBEDDED_SMALL_STRAIGHT(
            "EMBEDDED_SMALL_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x1"),
    EMBEDDED_MEDIUM_STRAIGHT(
            "EMBEDDED_MEDIUM_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x3"),
    EMBEDDED_LONG_STRAIGHT(
            "EMBEDDED_LONG_STRAIGHT", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x6"),
    EMBEDDED_VERY_LONG_STRAIGHT(
            "EMBEDDED_VERY_LONG_STRAIGHT",
            TrackCategory.STRAIGHT,
            TrackShapeGroup.STRAIGHT,
            "1x12"),
    EMBEDDED_SMALL_DIAGONAL_STRAIGHT(
            "EMBEDDED_SMALL_DIAGONAL_STRAIGHT",
            TrackCategory.DIAGONAL,
            TrackShapeGroup.DIAGONAL,
            "1x1"),
    EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT(
            "EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT",
            TrackCategory.DIAGONAL,
            TrackShapeGroup.DIAGONAL,
            "1x3"),
    EMBEDDED_LONG_DIAGONAL_STRAIGHT(
            "EMBEDDED_LONG_DIAGONAL_STRAIGHT",
            TrackCategory.DIAGONAL,
            TrackShapeGroup.DIAGONAL,
            "1x6"),
    EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT(
            "EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT",
            TrackCategory.DIAGONAL,
            TrackShapeGroup.DIAGONAL,
            "1x12"),
    EMBEDDED_TURN_1X1("EMBEDDED_TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "1x1"),
    EMBEDDED_LEFT_TURN_1X1(
            "EMBEDDED_LEFT_TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_RIGHT_TURN_1X1(
            "EMBEDDED_RIGHT_TURN_1X1", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_MEDIUM_TURN(
            "EMBEDDED_MEDIUM_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "3x3"),
    EMBEDDED_MEDIUM_RIGHT_TURN(
            "EMBEDDED_MEDIUM_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_MEDIUM_LEFT_TURN(
            "EMBEDDED_MEDIUM_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_LARGE_TURN(
            "EMBEDDED_LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "5x5"),
    EMBEDDED_LARGE_RIGHT_TURN(
            "EMBEDDED_LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_LARGE_LEFT_TURN(
            "EMBEDDED_LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_VERY_LARGE_TURN(
            "EMBEDDED_VERY_LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "10x10"),
    EMBEDDED_VERY_LARGE_RIGHT_TURN(
            "EMBEDDED_VERY_LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_VERY_LARGE_LEFT_TURN(
            "EMBEDDED_VERY_LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_SUPER_LARGE_TURN(
            "EMBEDDED_SUPER_LARGE_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "16x16"),
    EMBEDDED_SUPER_LARGE_RIGHT_TURN(
            "EMBEDDED_SUPER_LARGE_RIGHT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_SUPER_LARGE_LEFT_TURN(
            "EMBEDDED_SUPER_LARGE_LEFT_TURN", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_TURN_29X29(
            "EMBEDDED_TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "29x29"),
    EMBEDDED_LEFT_TURN_29X29(
            "EMBEDDED_LEFT_TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_RIGHT_TURN_29X29(
            "EMBEDDED_RIGHT_TURN_29X29", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_TURN_32X32(
            "EMBEDDED_TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, "32x32"),
    EMBEDDED_LEFT_TURN_32X32(
            "EMBEDDED_LEFT_TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_LEFT, ""),
    EMBEDDED_RIGHT_TURN_32X32(
            "EMBEDDED_RIGHT_TURN_32X32", TrackCategory.TURN, TrackShapeGroup.TURN_RIGHT, ""),
    EMBEDDED_MEDIUM_45DEGREE_TURN(
            "EMBEDDED_MEDIUM_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "3x4 hold sneak to attach to the back of another curve"),
    EMBEDDED_MEDIUM_RIGHT_45DEGREE_TURN(
            "EMBEDDED_MEDIUM_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_MEDIUM_LEFT_45DEGREE_TURN(
            "EMBEDDED_MEDIUM_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_LARGE_45DEGREE_TURN(
            "EMBEDDED_LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "3x6 hold sneak to attach to the back of another curve"),
    EMBEDDED_LARGE_RIGHT_45DEGREE_TURN(
            "EMBEDDED_LARGE_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_LARGE_LEFT_45DEGREE_TURN(
            "EMBEDDED_LARGE_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_VERY_LARGE_45DEGREE_TURN(
            "EMBEDDED_VERY_LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "4x8 hold sneak to attach to the back of another curve"),
    EMBEDDED_VERY_LARGE_RIGHT_45DEGREE_TURN(
            "EMBEDDED_VERY_LARGE_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_VERY_LARGE_LEFT_45DEGREE_TURN(
            "EMBEDDED_VERY_LARGE_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_SUPER_LARGE_45DEGREE_TURN(
            "EMBEDDED_SUPER_LARGE_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            "5x11 hold sneak to attach to the back of another curve"),
    EMBEDDED_SUPER_LARGE_RIGHT_45DEGREE_TURN(
            "EMBEDDED_SUPER_LARGE_RIGHT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_SUPER_LARGE_LEFT_45DEGREE_TURN(
            "EMBEDDED_SUPER_LARGE_LEFT_45DEGREE_TURN",
            TrackCategory.SHARP_TURN,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_SMALL_PARALLEL_CURVE(
            "EMBEDDED_SMALL_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            "2x8"),
    EMBEDDED_SMALL_RIGHT_PARALLEL_CURVE(
            "EMBEDDED_SMALL_RIGHT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_SMALL_LEFT_PARALLEL_CURVE(
            "EMBEDDED_SMALL_LEFT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_MEDIUM_PARALLEL_CURVE(
            "EMBEDDED_MEDIUM_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            "3x12"),
    EMBEDDED_MEDIUM_RIGHT_PARALLEL_CURVE(
            "EMBEDDED_MEDIUM_RIGHT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_MEDIUM_LEFT_PARALLEL_CURVE(
            "EMBEDDED_MEDIUM_LEFT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_LARGE_PARALLEL_CURVE(
            "EMBEDDED_LARGE_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            "4x16"),
    EMBEDDED_LARGE_RIGHT_PARALLEL_CURVE(
            "EMBEDDED_LARGE_RIGHT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_RIGHT,
            ""),
    EMBEDDED_LARGE_LEFT_PARALLEL_CURVE(
            "EMBEDDED_LARGE_LEFT_PARALLEL_CURVE",
            TrackCategory.CURVE,
            TrackShapeGroup.TURN_LEFT,
            ""),
    EMBEDDED_TWO_WAYS_CROSSING(
            "EMBEDDED_TWO_WAYS_CROSSING", TrackCategory.CROSSING, TrackShapeGroup.CROSSING, "3x3"),
    EMBEDDED_DIAGONAL_TWO_WAYS_CROSSING(
            "EMBEDDED_DIAGONAL_TWO_WAYS_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_FOUR_WAYS_CROSSING(
            "EMBEDDED_FOUR_WAYS_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_DIAMOND_CROSSING(
            "EMBEDDED_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_RIGHT_DIAMOND_CROSSING(
            "EMBEDDED_RIGHT_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_LEFT_DIAMOND_CROSSING(
            "EMBEDDED_LEFT_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_DOUBLE_DIAMOND_CROSSING(
            "EMBEDDED_DOUBLE_DIAMOND_CROSSING",
            TrackCategory.DIAGONAL_CROSSING,
            TrackShapeGroup.CROSSING,
            "3x3"),
    EMBEDDED_MEDIUM_SWITCH(
            "EMBEDDED_MEDIUM_RIGHT_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            "4x4"),
    EMBEDDED_MEDIUM_RIGHT_SWITCH(
            "EMBEDDED_MEDIUM_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    EMBEDDED_MEDIUM_LEFT_SWITCH(
            "EMBEDDED_MEDIUM_LEFT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    EMBEDDED_LARGE_SWITCH(
            "EMBEDDED_LARGE_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "6x6"),
    EMBEDDED_LARGE_RIGHT_SWITCH(
            "EMBEDDED_LARGE_RIGHT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, ""),
    EMBEDDED_LARGE_LEFT_SWITCH(
            "EMBEDDED_LARGE_LEFT_SWITCH", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_LEFT, ""),
    EMBEDDED_VERY_LARGE_SWITCH(
            "EMBEDDED_VERY_LARGE_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            "11x11"),
    EMBEDDED_VERY_LARGE_RIGHT_SWITCH(
            "EMBEDDED_VERY_LARGE_RIGHT_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            ""),
    EMBEDDED_VERY_LARGE_LEFT_SWITCH(
            "EMBEDDED_VERY_LARGE_LEFT_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_LEFT,
            ""),
    EMBEDDED_MEDIUM_PARALLEL_SWITCH(
            "EMBEDDED_", TrackCategory.SWITCH, TrackShapeGroup.SWITCH_RIGHT, "4x11"),
    EMBEDDED_MEDIUM_RIGHT_PARALLEL_SWITCH(
            "EMBEDDED_MEDIUM_RIGHT_PARALLEL_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            ""),
    EMBEDDED_MEDIUM_LEFT_PARALLEL_SWITCH(
            "EMBEDDED_MEDIUM_LEFT_PARALLEL_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_LEFT,
            ""),
    EMBEDDED_MEDIUM_45DEGREE_SWITCH(
            "EMBEDDED_MEDIUM_45DEGREE_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            "3x5 hold sneak to attach to the back of another curve"),
    EMBEDDED_MEDIUM_RIGHT_45DEGREE_SWITCH(
            "EMBEDDED_MEDIUM_RIGHT_45DEGREE_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_RIGHT,
            ""),
    EMBEDDED_MEDIUM_LEFT_45DEGREE_SWITCH(
            "EMBEDDED_MEDIUM_LEFT_45DEGREE_SWITCH",
            TrackCategory.SWITCH,
            TrackShapeGroup.SWITCH_LEFT,
            ""),
    EMBEDDED_SLOPE_DYNAMIC(
            "EMBEDDED_SLOPE_DYNAMIC", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x6"),
    EMBEDDED_LARGE_SLOPE_DYNAMIC(
            "EMBEDDED_LARGE_SLOPE_DYNAMIC", TrackCategory.SLOPE, TrackShapeGroup.SLOPE, "1x12"),
    EMBEDDED_VERY_LARGE_SLOPE_DYNAMIC(
            "EMBEDDED_VERY_LARGE_SLOPE_DYNAMIC",
            TrackCategory.SLOPE,
            TrackShapeGroup.SLOPE,
            "1x18"),
    EMBEDDED_LARGE_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_LARGE_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "5x5"),
    EMBEDDED_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    EMBEDDED_LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    EMBEDDED_VERY_LARGE_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_VERY_LARGE_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "9x9"),
    EMBEDDED_VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    EMBEDDED_VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    EMBEDDED_SUPER_LARGE_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_SUPER_LARGE_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "16x16"),
    EMBEDDED_SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE,
            "xxx"),
    EMBEDDED_SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC(
            "EMBEDDED_SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC",
            TrackCategory.CURVED_SLOPE,
            TrackShapeGroup.SLOPE_LEFT,
            "xxx"),
    SMALL_ROAD_CROSSING(
            "SMALL_ROAD_CROSSING", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x1"),
    SMALL_ROAD_CROSSING_1(
            "SMALL_ROAD_CROSSING_1", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x1"),
    SMALL_ROAD_CROSSING_2(
            "SMALL_ROAD_CROSSING_2", TrackCategory.STRAIGHT, TrackShapeGroup.STRAIGHT, "1x1");

    private static final Map<String, TrackType> BY_LABEL = buildLabelIndex();

    private final String label;
    private final TrackCategory category;
    private final TrackShapeGroup shapeGroup;
    private final String tooltip;

    TrackType(String label, TrackCategory category, TrackShapeGroup shapeGroup, String tooltip) {
        this.label = label;
        this.category = category;
        this.shapeGroup = shapeGroup;
        this.tooltip = tooltip;
    }

    /**
     * Indexed by first occurrence.
     *
     * <p>Not {@code Collectors.toMap}: the truncated {@code "EMBEDDED_"} label is shared, and a
     * merge-less collector would throw on the duplicate at class-initialisation time, taking the
     * whole mod down over a quirk that has to be tolerated rather than resolved.
     */
    private static Map<String, TrackType> buildLabelIndex() {
        Map<String, TrackType> index = new HashMap<>();
        for (TrackType type : values()) {
            index.putIfAbsent(type.label, type);
        }
        return Map.copyOf(index);
    }

    public String label() {
        return label;
    }

    public TrackCategory category() {
        return category;
    }

    /** Coarse family used for the blockstate property, collision shape and mesh selection. */
    public TrackShapeGroup shapeGroup() {
        return shapeGroup;
    }

    /** Footprint hint shown in the item tooltip, for example "3x3". Empty for variants. */
    public String tooltip() {
        return tooltip;
    }

    public static TrackType byLabel(String label) {
        return label == null ? null : BY_LABEL.get(label);
    }
}
