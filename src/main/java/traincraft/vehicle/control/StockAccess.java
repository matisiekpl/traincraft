package traincraft.vehicle.control;

/** Access rules shared by vehicle interaction and network commands. */
public record StockAccess(String owner, boolean locked) {

    /**
     * Upstream compares the player's name against the owner without regard to case, and an
     * unclaimed piece of stock belongs to nobody rather than to everybody.
     */
    public boolean isOwner(String playerName) {
        return owner.equalsIgnoreCase(playerName);
    }

    public boolean mayControl(String playerName) {
        return !locked || isOwner(playerName);
    }
}
