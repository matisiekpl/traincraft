package traincraft.vehicle.coupling;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.simulation.CouplingSpring;

import java.util.ArrayList;
import java.util.List;

/**
 * Couples stock together and keeps coupled stock together.
 *
 * <p>Community Edition's {@code LinkHandler}. Two jobs share the class because they share the
 * distance measurement: the search for another piece of stock in attaching mode, and the spring
 * that runs every tick on a link that already exists.
 */
public final class LinkHandler {

    private static final double ATTACH_SEARCH = 15.0;
    private static final double LOST_LINK_DISTANCE = 20.0;

    public void handleStake(RollingStockEntity one) {
        if (one.isAttaching) {
            List<net.minecraft.world.entity.Entity> found =
                    new ArrayList<>(
                            one.level()
                                    .getEntities(
                                            one,
                                            one.getBoundingBox()
                                                    .inflate(ATTACH_SEARCH, 5.0, ATTACH_SEARCH)));
            Vec3 bogie = one.bogiePosition();
            if (bogie != null) {
                found.addAll(
                        one.level()
                                .getEntities(
                                        one,
                                        AABB.ofSize(
                                                        bogie,
                                                        one.getBbWidth(),
                                                        one.getBbHeight(),
                                                        one.getBbWidth())
                                                .inflate(7.0, 5.0, 7.0)));
            }
            for (var entity : found) {
                if (entity instanceof RollingStockEntity other && other.isAttaching) {
                    addStake(other, one, true);
                }
            }
        }
        if (one.cartLinked1 != null) {
            stakePhysic(one.cartLinked1, one, 1);
        }
        if (one.cartLinked2 != null) {
            stakePhysic(one.cartLinked2, one, 2);
        }
    }

    /**
     * Couples two pieces of stock that are both in attaching mode and close enough.
     *
     * <p>The reach is {@code cart1.getLinkageDistance(cart1)} -- upstream asks the first cart about
     * itself, not about the one it is being coupled to, so the second cart's length does not enter
     * into it.
     */
    public void addStake(RollingStockEntity cart1, RollingStockEntity cart2, boolean byPlayer) {
        if (cart1.level().isClientSide() || !cart2.isAttaching || !cart1.isAttaching) {
            return;
        }
        double reach = cart1.getLinkageDistance(cart1);
        Vec3 separation = nearestSeparation(cart1, cart2);
        if (Math.sqrt(separation.x * separation.x + separation.z * separation.z) > reach) {
            return;
        }

        if (cart1.link1 == 0.0 || cart1.link1 == -1.0) {
            cart1.link1 = cart2.getUniqueTrainID();
        } else if (cart1.link2 == 0.0 || cart1.link2 == -1.0) {
            cart1.link2 = cart2.getUniqueTrainID();
        }
        if (cart1.cartLinked1 == null) {
            cart1.cartLinked1 = cart2;
        } else if (cart1.cartLinked2 == null) {
            cart1.cartLinked2 = cart2;
        }

        if (cart2.link1 == 0.0 || cart2.link1 == -1.0) {
            cart2.link1 = cart1.getUniqueTrainID();
        } else if (cart2.link2 == 0.0 || cart2.link2 == -1.0) {
            cart2.link2 = cart1.getUniqueTrainID();
        }
        if (cart2.cartLinked1 == null) {
            cart2.cartLinked1 = cart1;
        } else if (cart2.cartLinked2 == null) {
            cart2.cartLinked2 = cart1;
        }

        cart2.isAttached = true;
        cart2.isAttaching = false;
        cart1.isAttaching = false;
        cart1.isAttached = true;

        // The neighbours' consists are thrown away rather than extended: the next pass of
        // handleTrain builds one that spans the new coupling.
        if (cart2.cartLinked1 != null && cart2.cartLinked1.consist != null) {
            Consist.ALL.remove(cart2.cartLinked1.consist);
            cart2.cartLinked1.consist.members().clear();
        }
        if (cart2.cartLinked2 != null && cart2.cartLinked2.consist != null) {
            Consist.ALL.remove(cart2.cartLinked2.consist);
            cart2.cartLinked2.consist.members().clear();
        }

        if (byPlayer) {
            Player player = cart1.level().getNearestPlayer(cart1, 20.0);
            if (player != null) {
                player.sendSystemMessage(Component.literal("attached!"));
            }
        }
    }

    public static boolean areLinked(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart2.getUniqueTrainID() == cart1.link1
                || cart2.getUniqueTrainID() == cart1.link2
                || cart1.getUniqueTrainID() == cart2.link1
                || cart1.getUniqueTrainID() == cart2.link2;
    }

    public static double optimalDistance(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart1.optimalDistance(cart2) + cart2.optimalDistance(cart1);
    }

    private static boolean canCartBeAdjustedBy(RollingStockEntity cart1, RollingStockEntity cart2) {
        return cart1 != cart2 && cart1.canBeAdjusted(cart2);
    }

    /**
     * One tick of the coupling between two pieces of stock.
     *
     * <p>Both have to have been on the rails for five ticks: a cart still being placed, or one
     * whose chunk has just loaded, has not yet been put where it belongs, and the distance between
     * them means nothing until it has.
     */
    private void stakePhysic(RollingStockEntity cart1, RollingStockEntity cart2, int linkIndex) {
        if (cart1.level().isClientSide() || cart1.updateTicks < 5 || cart2.updateTicks < 5) {
            return;
        }
        if (!cart2.isAttached || !cart1.isAttached || !areLinked(cart2, cart1)) {
            return;
        }
        boolean adjust1 = canCartBeAdjustedBy(cart1, cart2);
        boolean adjust2 = canCartBeAdjustedBy(cart2, cart1);
        Vec3 separation = nearestSeparation(cart1, cart2);
        double separationX = separation.x;
        double separationZ = separation.z;
        double distance = Math.sqrt(separationX * separationX + separationZ * separationZ);

        if (distance > LOST_LINK_DISTANCE) {
            Player player =
                    cart1.level()
                            .getNearestPlayer(cart1.getX(), cart1.getY(), cart1.getZ(), 300.0, false);
            if (player != null) {
                player.sendSystemMessage(
                        Component.literal(
                                String.format(
                                        "[TRAINCRAFT] The rolling stock at %d %d %d had a problem"
                                            + " loading and has lost its link. Attached cart was"
                                            + " too far away",
                                        (int) cart1.getX(),
                                        (int) cart1.getY(),
                                        (int) cart1.getZ())));
            }
            if (linkIndex == 1) {
                freeLink1(cart1);
                freeLink1(cart2);
            }
            if (linkIndex == 2) {
                freeLink2(cart1);
                freeLink2(cart2);
            }
            return;
        }

        double unitX = separationX / distance;
        double unitZ = separationZ / distance;
        CouplingSpring spring =
                CouplingSpring.spring(separationX, separationZ, optimalDistance(cart1, cart2));
        if (adjust1) {
            cart1.setDeltaMovement(cart1.getDeltaMovement().add(spring.x(), 0.0, spring.z()));
        }
        if (adjust2) {
            cart2.setDeltaMovement(cart2.getDeltaMovement().subtract(spring.x(), 0.0, spring.z()));
        }

        CouplingSpring damping =
                CouplingSpring.damping(
                        cart1.getDeltaMovement().x - cart2.getDeltaMovement().x,
                        cart1.getDeltaMovement().z - cart2.getDeltaMovement().z,
                        unitX,
                        unitZ);
        if (adjust1) {
            cart1.setDeltaMovement(cart1.getDeltaMovement().add(damping.x(), 0.0, damping.z()));
        }
        if (adjust2) {
            cart2.setDeltaMovement(cart2.getDeltaMovement().subtract(damping.x(), 0.0, damping.z()));
        }
    }

    private static void freeLink1(RollingStockEntity entity) {
        entity.link1 = 0.0;
        entity.cartLinked1 = null;
        if (entity.consist != null) {
            entity.consist.members().clear();
        }
    }

    private static void freeLink2(RollingStockEntity entity) {
        entity.link2 = 0.0;
        entity.cartLinked2 = null;
        if (entity.consist != null) {
            entity.consist.members().clear();
        }
    }

    /**
     * The shortest of the separations between the two carts' contact points.
     *
     * <p>A piece of stock with a bogie has two: its body and the bogie. Upstream measures every
     * combination and keeps the shortest, which is what lets a long locomotive couple by its
     * trailing end rather than its middle.
     */
    static Vec3 nearestSeparation(RollingStockEntity cart1, RollingStockEntity cart2) {
        Vec3 bogie1 = cart1.bogiePosition();
        Vec3 bogie2 = cart2.bogiePosition();
        if (bogie1 == null && bogie2 == null) {
            return new Vec3(cart1.getX() - cart2.getX(), 0.0, cart1.getZ() - cart2.getZ());
        }
        double[] separationX = new double[] {100.0, 100.0, 100.0, 100.0};
        double[] separationZ = new double[] {100.0, 100.0, 100.0, 100.0};
        separationX[0] = cart1.getX() - cart2.getX();
        separationZ[0] = cart1.getZ() - cart2.getZ();
        if (bogie1 != null) {
            separationX[1] = bogie1.x - cart2.getX();
            separationZ[1] = bogie1.z - cart2.getZ();
        } else {
            separationX[1] = cart1.getX() - bogie2.x;
            separationZ[1] = cart1.getZ() - bogie2.z;
        }
        if (bogie1 != null && bogie2 != null) {
            separationX[2] = cart1.getX() - bogie2.x;
            separationZ[2] = cart1.getZ() - bogie2.z;
            separationX[3] = bogie1.x - bogie2.x;
            separationZ[3] = bogie1.z - bogie2.z;
        }
        int nearest = 0;
        double shortest = Double.MAX_VALUE;
        for (int i = 0; i < separationX.length; i++) {
            double euclidian =
                    Math.sqrt(
                            separationX[i] * separationX[i] + separationZ[i] * separationZ[i]);
            if (i == 0) {
                shortest = euclidian;
                continue;
            }
            if (Math.abs(euclidian) < Math.abs(shortest)) {
                shortest = euclidian;
                nearest = i;
            }
        }
        return new Vec3(separationX[nearest], 0.0, separationZ[nearest]);
    }
}
