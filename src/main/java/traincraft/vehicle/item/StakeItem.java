package traincraft.vehicle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * The tool that couples rolling stock.
 *
 * <p>Community Edition's {@code ItemStacked}. It has no use of its own: the stock answers the
 * click, through {@code Coupling.onClickWithStake}.
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
                Component.literal("Right click on a rolling stock").withStyle(ChatFormatting.GRAY));
        lines.accept(Component.literal(" to enter attaching mode.").withStyle(ChatFormatting.GRAY));
        lines.accept(
                Component.literal("Click a few time to reset links.")
                        .withStyle(ChatFormatting.GRAY));
        lines.accept(
                Component.literal("Sneak+Right click on a locomotive")
                        .withStyle(ChatFormatting.GRAY));
        lines.accept(
                Component.literal(" to set mode: 'Can pull/Can be pulled'")
                        .withStyle(ChatFormatting.GRAY));
    }
}
