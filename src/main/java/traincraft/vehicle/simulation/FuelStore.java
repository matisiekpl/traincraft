package traincraft.vehicle.simulation;

/** Fuel measured in furnace burn ticks. A refill consumes an item only when its full value fits. */
public final class FuelStore {
    public static final int CAPACITY = 20_000;
    private static final int CONSUMPTION_INTERVAL = 100;
    private int amount;
    private int elapsedTicks;

    public int amount() {
        return amount;
    }

    public void restore(int amount) {
        this.amount = Math.clamp(amount, 0, CAPACITY);
        elapsedTicks = 0;
    }

    public boolean refill(int burnTicks) {
        if (burnTicks <= 0 || burnTicks > CAPACITY - amount) return false;
        amount += burnTicks;
        return true;
    }

    /** Returns true when an empty firebox should apply coasting resistance. */
    public boolean tick(boolean engineOn, int consumption) {
        if (consumption < 0) throw new IllegalArgumentException("Negative fuel consumption");
        if (++elapsedTicks < CONSUMPTION_INTERVAL) return false;
        elapsedTicks = 0;
        if (amount == 0) return true;
        if (engineOn) amount = Math.max(0, amount - consumption);
        return false;
    }
}
