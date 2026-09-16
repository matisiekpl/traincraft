package traincraft.vehicle.definition;

public record VehicleDefinition(
        String entryName,
        String displayName,
        String trainType,
        int horsePower,
        int maxSpeed,
        double mass,
        int fuelConsumption,
        int waterConsumption,
        int heatingTime,
        double accelerationRate,
        double brakeRate,
        int tankCapacity,
        int cargoCapacity,
        int guiRenderScale,
        double bogieLocoPosition,
        java.util.List<String> colours,
        String tooltip) {
    public VehicleDefinition {
        if (entryName == null
                || entryName.isBlank()
                || displayName == null
                || displayName.isBlank()
                || trainType == null
                || trainType.isBlank())
            throw new IllegalArgumentException("Missing vehicle identity");
        if (horsePower < 0
                || maxSpeed < 0
                || mass < 0
                || !Double.isFinite(mass)
                || fuelConsumption < 0
                || waterConsumption < 0
                || heatingTime < 0
                || accelerationRate < 0
                || !Double.isFinite(accelerationRate)
                || brakeRate < 0
                || brakeRate > 1
                || !Double.isFinite(brakeRate)
                || tankCapacity < 0
                || cargoCapacity < 0
                || !Double.isFinite(bogieLocoPosition)) {
            throw new IllegalArgumentException("Invalid vehicle specification: " + entryName);
        }
        colours = java.util.List.copyOf(colours);
        tooltip = java.util.Objects.requireNonNullElse(tooltip, "");
    }
}
