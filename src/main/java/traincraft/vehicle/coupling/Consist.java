package traincraft.vehicle.coupling;

import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Every piece of stock reachable from one of them through its couplings.
 *
 * <p>Community Edition's {@code TrainHandler}, including its static list of every consist in the
 * world: membership is rebuilt from that list every forty ticks, and the list is what a piece of
 * stock consults to find out whether it already belongs to one.
 */
public final class Consist {

    public static final List<Consist> ALL = new ArrayList<>();

    private final List<RollingStockEntity> members = new ArrayList<>();
    private int trainPower;

    public Consist(RollingStockEntity first) {
        add(first);
        ALL.add(this);
    }

    public void add(RollingStockEntity rolling) {
        for (RollingStockEntity member : members) {
            if (member.equals(rolling)) {
                return;
            }
        }
        if (rolling instanceof LocomotiveEntity locomotive) {
            trainPower += locomotive.currentHorsePower();
        }
        members.add(rolling);
        rolling.consist = this;
        if (rolling.cartLinked1 != null) {
            add(rolling.cartLinked1);
        }
        if (rolling.cartLinked2 != null) {
            add(rolling.cartLinked2);
        }
    }

    public void reset() {
        for (RollingStockEntity member : members) {
            if (member != null) {
                member.consist = null;
            }
        }
        members.clear();
    }

    public List<RollingStockEntity> members() {
        return members;
    }

    public int trainPower() {
        return trainPower;
    }
}
