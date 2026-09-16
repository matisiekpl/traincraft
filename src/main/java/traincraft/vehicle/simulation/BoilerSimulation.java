package traincraft.vehicle.simulation;

import java.util.function.IntUnaryOperator;

/** Server-tick thermal simulation. Temperature is expressed relative to a vehicle's heat scale. */
public final class BoilerSimulation {
    public record Input(
            int temperature,
            int heatScale,
            HeatState state,
            int fuel,
            boolean engineOn,
            boolean braking,
            double speed,
            double brakingSpeed,
            int water,
            int tankCapacity) {}

    public record Output(int temperature, HeatState state) {}

    private int brakingTicks;

    public Output tick(Input input, IntUnaryOperator random) {
        if (input.heatScale() <= 0) return new Output(input.temperature(), input.state());
        int temperature = input.temperature();
        int average = operatingTemperature(input.heatScale());
        boolean broken = input.state() == HeatState.BROKEN;
        if (input.speed() <= 0.05
                && !input.braking()
                && temperature > average
                && random.applyAsInt(10) == 0
                && !broken) temperature--;
        if (input.speed() <= 0.10
                && !input.braking()
                && temperature > average
                && random.applyAsInt(10) == 0
                && !broken) temperature--;
        if (input.fuel() < 1 && temperature > 0 && random.applyAsInt(10) == 0 && !broken)
            temperature--;
        if (temperature > average && random.applyAsInt(30) == 0 && !broken) temperature--;
        if (input.engineOn()
                && input.fuel() > 1
                && temperature < average
                && random.applyAsInt(7) == 0) temperature++;

        brakingTicks = input.braking() ? brakingTicks + 1 : 0;
        if (input.braking()
                && brakingTicks > 40
                && input.brakingSpeed() > 0.05
                && random.applyAsInt(10) == 0) temperature += 2;
        if (input.tankCapacity() > 0) {
            if (input.water() < 1 && input.fuel() > 10 && random.applyAsInt(10) == 0)
                temperature += 3;
            if (input.water() > input.tankCapacity() - input.tankCapacity() / 2
                    && temperature > average
                    && !broken) temperature--;
        }
        return new Output(temperature, stateFor(input.heatScale(), temperature));
    }

    public static int operatingTemperature(int heatScale) {
        return (heatScale + 30) / 2;
    }

    public static HeatState stateFor(int heatScale, int temperature) {
        int average = operatingTemperature(heatScale);
        if (temperature > average + heatScale * 0.48) return HeatState.BROKEN;
        if (temperature > average + heatScale * 0.34) return HeatState.TOO_HOT;
        if (temperature > average + heatScale * 0.24) return HeatState.VERY_HOT;
        if (temperature > average - heatScale * 0.08) return HeatState.HOT;
        if (temperature > average - heatScale * 0.34) return HeatState.WARM;
        return HeatState.COLD;
    }
}
