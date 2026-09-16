package traincraft.track;

import java.util.List;

public record TrackPlan(TrackType requested, int facingMeta, List<Placement> placements) {

    public TrackPlan {
        java.util.Objects.requireNonNull(requested);
        TrackOrientation.fromIndex(facingMeta);
        placements = List.copyOf(placements);
        if (placements.isEmpty()) throw new IllegalArgumentException("Empty track plan");
        var offsets = new java.util.HashSet<Placement.Link>();
        for (Placement placement : placements) {
            if (!offsets.add(new Placement.Link(placement.dx(), placement.dy(), placement.dz()))) {
                throw new IllegalArgumentException("Duplicate placement offset");
            }
        }
    }

    public int size() {
        return placements.size();
    }

    public Placement get(int index) {
        return placements.get(index);
    }

    public Placement master() {
        for (Placement placement : placements.reversed()) {
            if (placement.dropsItem()) {
                return placement;
            }
        }
        throw new IllegalStateException("plan for " + requested + " has no drop-carrying rail");
    }

    /**
     * The rail a filler block takes its geometry from: the nearest rail of the filler's own type,
     * which on a piece with two curves is the curve the filler lies on, and the owner otherwise.
     */
    public Placement ownerOf(Placement gag) {
        return placements.stream()
                .filter(placement -> !placement.gag() && placement.type() == gag.type())
                .min(java.util.Comparator.comparingInt(placement -> distanceSquared(placement, gag)))
                .orElseGet(this::master);
    }

    private static int distanceSquared(Placement a, Placement b) {
        int dx = a.dx() - b.dx();
        int dy = a.dy() - b.dy();
        int dz = a.dz() - b.dz();
        return dx * dx + dy * dy + dz * dz;
    }

    /** Converts a placement offset to the coordinate system anchored at the owning rail. */
    public Placement.Link offsetFromOwner(Placement placement) {
        Placement owner = master();
        return new Placement.Link(
                placement.dx() - owner.dx(),
                placement.dy() - owner.dy(),
                placement.dz() - owner.dz());
    }

    public boolean containsOwnerRelativeOffset(int x, int y, int z) {
        Placement owner = master();
        return placements.stream()
                .anyMatch(
                        placement ->
                                placement.dx() - owner.dx() == x
                                        && placement.dy() - owner.dy() == y
                                        && placement.dz() - owner.dz() == z);
    }
}
