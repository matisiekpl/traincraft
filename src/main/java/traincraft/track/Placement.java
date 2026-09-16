package traincraft.track;

public record Placement(
        int dx,
        int dy,
        int dz,
        TrackType type,
        int facing,
        int blockMeta,
        boolean gag,
        boolean hasModel,
        boolean dropsItem,
        Placement.Link link,
        double gagHeight,
        PlacementParameters params) {

    /** The default {@code TrackOccupancyBlockEntity.bbHeight}, which every non-slope gag keeps. */
    public Placement {
        java.util.Objects.requireNonNull(type);
        java.util.Objects.requireNonNull(params);
        TrackOrientation.fromIndex(facing);
        TrackOrientation.fromIndex(blockMeta);
        if (!Double.isFinite(gagHeight) || gagHeight < 0)
            throw new IllegalArgumentException("Invalid collision height");
    }

    public TrackOrientation routeOrientation() {
        return TrackOrientation.fromIndex(facing);
    }

    public TrackOrientation modelOrientation() {
        return TrackOrientation.fromIndex(blockMeta);
    }

    public static final double DEFAULT_GAG_HEIGHT = 0.125f;

    /** A pointer from one placement to another, in the same click-relative frame. */
    public record Link(int dx, int dy, int dz) {}

    public Placement withHasModel(boolean value) {
        return new Placement(
                dx, dy, dz, type, facing, blockMeta, gag, value, dropsItem, link, gagHeight,
                params);
    }

    public Placement withLink(Link value) {
        return new Placement(
                dx, dy, dz, type, facing, blockMeta, gag, hasModel, dropsItem, value, gagHeight,
                params);
    }
}
