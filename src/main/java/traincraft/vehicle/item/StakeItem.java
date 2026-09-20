package traincraft.vehicle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * The stake.
 *
 * <p>Community Edition's {@code ItemStacked}. Coupling moved to the stock's own screen, so the
 * item is left as the crafted token it always was and no longer answers a click.
 */
public class StakeItem extends Item {

    public StakeItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> lines,
            net.minecraft.world.item.TooltipFlag flag) {
        lines.accept(
                Component.literal("Rolling stock couples from its own screen,")
                        .withStyle(ChatFormatting.GRAY));
        lines.accept(
                Component.literal(" one toggle per end, front and rear.")
                        .withStyle(ChatFormatting.GRAY));
    }
}
