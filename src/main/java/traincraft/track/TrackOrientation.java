package traincraft.track;

/** Horizontal orientation in quarter turns: south, west, north, east. */
public enum TrackOrientation {
    SOUTH,
    WEST,
    NORTH,
    EAST;

    public int index() {
        return ordinal();
    }

    public static TrackOrientation fromIndex(int index) {
        if (index < 0 || index > 3)
            throw new IllegalArgumentException("Orientation must be in 0..3: " + index);
        return values()[index];
    }
}
