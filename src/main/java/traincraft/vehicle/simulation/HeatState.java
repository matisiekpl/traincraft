package traincraft.vehicle.simulation;

public enum HeatState {
    COLD("cold"),
    WARM("warm"),
    HOT("hot"),
    VERY_HOT("very hot"),
    TOO_HOT("too hot"),
    BROKEN("broken");

    private final String displayName;

    HeatState(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
