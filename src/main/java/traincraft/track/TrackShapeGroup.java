package traincraft.track;

import net.minecraft.util.StringRepresentable;

public enum TrackShapeGroup implements StringRepresentable {
    CROSSING("crossing"),
    DIAGONAL("diagonal"),
    GAG("gag"),
    SLOPE("slope"),
    SLOPE_LEFT("slope_left"),
    STRAIGHT("straight"),
    SWITCH_LEFT("switch_left"),
    SWITCH_RIGHT("switch_right"),
    TURN_LEFT("turn_left"),
    TURN_RIGHT("turn_right");

    private final String name;

    TrackShapeGroup(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
