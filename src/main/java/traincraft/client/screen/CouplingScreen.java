package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;

import traincraft.vehicle.entity.RollingStockEntity;

/**
 * The couplings of a piece of stock that has no panel of its own.
 *
 * <p>A passenger car carries nothing and drives nothing, so there is nothing to hang the coupling
 * controls off; this is the whole of what it opens. Stock that does have a panel shows the same
 * controls in it rather than behind a second click.
 */
public final class CouplingScreen extends Screen {

    private static final int WIDTH = 160;
    private static final int LABEL_COLOUR = 0xFFFFFFFF;

    /** Squared blocks, matching the reach the server checks before it acts on a click. */
    private static final double REACH_SQUARED = 64.0;

    private final RollingStockEntity stock;
    private long builtCoupling;

    public CouplingScreen(RollingStockEntity stock) {
        super(stock.getDisplayName());
        this.stock = stock;
    }

    @Override
    protected void init() {
        builtCoupling = CouplingButtons.state(stock);
        CouplingButtons.add(
                stock,
                font,
                minecraft.level,
                width / 2 - WIDTH / 2,
                height / 2,
                WIDTH,
                this::addRenderableWidget);
    }

    @Override
    public void tick() {
        super.tick();
        if (stock.isRemoved()
                || minecraft == null
                || minecraft.player == null
                || minecraft.player.distanceToSqr(stock) > REACH_SQUARED) {
            onClose();
            return;
        }
        if (builtCoupling != CouplingButtons.state(stock)) {
            rebuildWidgets();
        }
    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        super.extractRenderState(graphics, mouseX, mouseY, partial);
        String name = stock.getDisplayName().getString();
        graphics.text(
                font,
                name,
                width / 2 - font.width(name) / 2,
                height / 2 - CouplingButtons.HEIGHT - 20,
                LABEL_COLOUR,
                true);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
