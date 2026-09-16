package traincraft.vehicle.coupling;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;

/**
 * What a stake does when it is used on a piece of rolling stock.
 *
 * <p>Community Edition's {@code TrainsOnClick.onClickWithStake}, called from the stock's own
 * interaction before anything else it might do -- opening a menu, or seating a driver.
 */
public final class Coupling {

    private Coupling() {}

    public static boolean onClickWithStake(
            RollingStockEntity train, ItemStack stack, Player player, InteractionHand hand) {
        if (!stack.is(ItemRegistry.STAKE.get()) || train.level().isClientSide()) {
            return false;
        }
        String owner = train.getOwner();
        if (train.isLinked() && !owner.isEmpty() && !owner.equals(player.getGameProfile().name())) {
            return false;
        }

        if (player.isShiftKeyDown() && train instanceof LocomotiveEntity locomotive) {
            if (!locomotive.canBeAdjusted(locomotive)) {
                player.sendSystemMessage(
                        Component.empty()
                                .append(locomotive.getTrainName())
                                .append(" can be pulled, don't forget to fuel it!"));
                player.sendSystemMessage(
                        Component.literal(
                                "Attach the BACK of this locomotive to the BACK of another"
                                    + " locomotive. Otherwise you will encounter weird problems on"
                                    + " turns"));
                locomotive.setCanBeAdjusted(true);
                locomotive.setCanBePulled(true);
            } else {
                player.sendSystemMessage(
                        Component.empty()
                                .append(locomotive.getTrainName())
                                .append(" can pull"));
                locomotive.setCanBeAdjusted(false);
                locomotive.setCanBePulled(false);
            }
            return true;
        }

        if (!train.isAttaching) {
            train.isAttaching = true;
            player.sendSystemMessage(
                    Component.literal("Attaching mode on for: ").append(train.getTrainName()));
            stack.hurtAndBreak(1, player, hand);
            return true;
        }

        player.sendSystemMessage(
                Component.literal("Reset, click again to couple new cart to this one"));
        train.link1 = -1.0;
        train.link2 = -1.0;
        if (train.cartLinked1 != null && train.cartLinked1.link1 == train.getUniqueTrainID()) {
            train.cartLinked1.link1 = -1.0;
        }
        if (train.cartLinked1 != null && train.cartLinked1.link2 == train.getUniqueTrainID()) {
            train.cartLinked1.link2 = -1.0;
        }
        if (train.cartLinked2 != null && train.cartLinked2.link1 == train.getUniqueTrainID()) {
            train.cartLinked2.link1 = -1.0;
        }
        if (train.cartLinked2 != null && train.cartLinked2.link2 == train.getUniqueTrainID()) {
            train.cartLinked2.link2 = -1.0;
        }
        if (train.cartLinked1 != null
                && train.cartLinked1.cartLinked1 != null
                && train.cartLinked1.cartLinked1.equals(train)) {
            train.cartLinked1.cartLinked1 = null;
        }
        if (train.cartLinked1 != null
                && train.cartLinked1.cartLinked2 != null
                && train.cartLinked1.cartLinked2.equals(train)) {
            train.cartLinked1.cartLinked2 = null;
        }
        // The second neighbour is cleared the other way round -- link two before link one --
        // exactly as upstream does it.
        if (train.cartLinked2 != null
                && train.cartLinked2.cartLinked2 != null
                && train.cartLinked2.cartLinked2.equals(train)) {
            train.cartLinked2.cartLinked2 = null;
        }
        if (train.cartLinked2 != null
                && train.cartLinked2.cartLinked1 != null
                && train.cartLinked2.cartLinked1.equals(train)) {
            train.cartLinked2.cartLinked1 = null;
        }
        train.cartLinked1 = null;
        train.cartLinked2 = null;
        train.isAttaching = false;
        train.isAttached = false;
        if (train.consist != null) {
            train.consist.reset();
        }
        if (train.consist != null && train.consist.members().size() <= 1) {
            Consist.ALL.remove(train.consist);
        }
        return true;
    }
}
