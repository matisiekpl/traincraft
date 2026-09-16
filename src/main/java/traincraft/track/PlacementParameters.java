package traincraft.track;

import java.util.Objects;

public record PlacementParameters(TrackGeometry geometry, boolean switchControlled) {
    public static final PlacementParameters NONE =
            new PlacementParameters(TrackGeometry.STRAIGHT, false);

    public PlacementParameters {
        Objects.requireNonNull(geometry);
    }
}
