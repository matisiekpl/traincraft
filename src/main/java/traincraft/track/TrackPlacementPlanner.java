package traincraft.track;

import org.jspecify.annotations.Nullable;

public final class TrackPlacementPlanner {

    private TrackPlacementPlanner() {}

    /** Whether a plan exists for this type and facing, so a caller can ask before committing. */
    public static boolean canPlan(TrackType type, int facingMeta) {
        return TrackPlanCatalog.has(type, facingMeta);
    }

    public static TrackPlan plan(TrackType type, int facingMeta) {
        if (facingMeta < 0 || facingMeta > 3) {
            throw new IllegalArgumentException("facingMeta must be 0..3, got " + facingMeta);
        }
        TrackPlan plan = TrackPlanCatalog.get(type, facingMeta);
        if (plan == null) {
            throw new UnsupportedOperationException(describeMissing(type, facingMeta));
        }
        return plan;
    }

    /**
     * @return null when this type and facing can be placed, otherwise why it cannot
     */
    public static @Nullable String whyUnsupported(TrackType type, int facingMeta) {
        return TrackPlanCatalog.has(type, facingMeta) ? null : describeMissing(type, facingMeta);
    }

    public static int coverage() {
        return TrackPlanCatalog.entryCount();
    }

    private static String describeMissing(TrackType type, int facingMeta) {
        return "Track placement is unavailable for " + type.name() + " facing " + facingMeta;
    }
}
