package traincraft.vehicle.simulation;

import traincraft.vehicle.definition.VehicleDefinition;

/**
 * What the rest of the consist costs the locomotive pulling it.
 *
 * <p>Community Edition's {@code HandleMaxAttachedCarts.PullPhysic}, as a value: the caller hands
 * over the mass on the drawbar and the power available, and gets back the speed, acceleration,
 * brake and fuel rate the locomotive runs at until the next time it asks.
 *
 * <p>The mass arrives as the sum of {@code weightKg() * 0.1} over the consist, which for the
 * definition table is the mass column itself. A single unattached locomotive is a consist of one
 * and gets its specification back untouched.
 */
public record ConsistLoad(
        int cartsPulled,
        double massPulled,
        double speedSlowDown,
        double accelSlowDown,
        double brakeSlowDown,
        double fuelConsumptionChange,
        double customSpeed,
        double accelerate,
        double brake,
        int fuelRate) {

    public static ConsistLoad of(
            VehicleDefinition spec, double totalMass, int members, double power, double maxSpeed) {
        int cartsPulled = members == 0 ? 0 : members - 1;
        double massPulled = members == 0 ? 0.0 : Math.round(totalMass * 10.0);
        totalMass = totalMass < 0.0 ? 0.0 : totalMass * 0.745;

        double speedSlowDown = 0.0;
        double customSpeed = maxSpeed;
        if (members > 1) {
            speedSlowDown = totalMass == 0.0 ? 0.0 : totalMass / (power / 74.57);
            customSpeed = Math.max(maxSpeed - speedSlowDown, 0.1);
        }

        double scaledPower = scalePower(totalMass == 0.0 ? 0.0 : totalMass / (power / 745.7));
        boolean loaded = power * totalMass > 0.0;

        double accelerate = spec.accelerationRate();
        if (loaded) {
            accelerate -= scaledPower * totalMass * 1.13;
        }
        double brake = spec.brakeRate() + scaledPower * totalMass;
        if (brake > 1.0) {
            brake = 0.998;
        }
        int fuelRate = spec.fuelConsumption() - (int) (scaledPower * totalMass * 100.0);

        return new ConsistLoad(
                cartsPulled,
                massPulled,
                speedSlowDown,
                loaded ? scaledPower * (totalMass * 1.13) : 0.0,
                loaded ? scaledPower * totalMass : 0.0,
                loaded ? fuelRate : 0.0,
                customSpeed,
                // Upstream only writes the value back when it is still positive, so a consist
                // heavy enough to drive one of these to zero leaves the locomotive on the
                // previous figure rather than stopping it.
                accelerate > 0.0 ? accelerate : spec.accelerationRate(),
                brake > 0.0 && loaded ? brake : spec.brakeRate(),
                fuelRate > 0 ? fuelRate : spec.fuelConsumption());
    }

    private static double scalePower(double power) {
        return power == 0.0 ? 0.0 : power / 1000.0 * 0.8;
    }
}
