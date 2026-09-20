package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.Traincraft;
import traincraft.network.StockLockPayload;
import traincraft.vehicle.entity.FreightEntity;
import traincraft.vehicle.inventory.FreightMenu;

public class FreightScreen<M extends FreightMenu> extends AbstractContainerScreen<M> {

    private static final Identifier BACKGROUND =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/container.png");

    private static final int SHEET = 256;

    /** Upstream's 4210752, which in 26.2 has to carry its own alpha. */
    private static final int LABEL_COLOUR = 0xFF000000 | 4210752;

    private static final int TOOLTIP_BACKGROUND = 0xF0100010;
    private static final int TOOLTIP_BORDER_TOP = 0x505000FF;
    private static final int TOOLTIP_BORDER_BOTTOM =
            (TOOLTIP_BORDER_TOP & 0xFEFEFE) >> 1 | TOOLTIP_BORDER_TOP & 0xFF000000;

    protected final FreightEntity freight;
    private final int rows;

    public FreightScreen(M menu, Inventory inventory, Component title) {
        this(menu, inventory, title, 222 - 108 + menu.rows() * 18);
    }

    protected FreightScreen(M menu, Inventory inventory, Component title, int height) {
        this(menu, inventory, title, 176, height);
    }

    protected FreightScreen(M menu, Inventory inventory, Component title, int width, int height) {
        super(menu, inventory, title, width, height);
        this.freight = menu.freight();
        this.rows = menu.rows();
    }

    protected String stockNoun() {
        return "freight";
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();
        builtLocked = freight.isLocked();
        builtCoupling = CouplingButtons.state(freight);
        CouplingButtons.add(
                freight,
                font,
                minecraft.level,
                leftPos,
                topPos - 24,
                imageWidth,
                this::addRenderableWidget);
        if (!builtLocked) {
            addRenderableWidget(
                    Button.builder(Component.literal("Unlocked"), button -> toggleLock())
                            .bounds(leftPos + 124, topPos - 10, 51, 10)
                            .build());
        } else {
            addRenderableWidget(
                    Button.builder(Component.literal("Locked"), button -> toggleLock())
                            .bounds(leftPos + 130, topPos - 10, 43, 10)
                            .build());
        }
    }

    private boolean builtLocked;
    private long builtCoupling;

    private void toggleLock() {
        if (minecraft == null || minecraft.player == null) {
            return;
        }
        if (!minecraft.player.getGameProfile().name().equalsIgnoreCase(freight.getOwner())) {
            minecraft.player.sendSystemMessage(Component.literal("You are not the owner"));
            return;
        }
        ClientPacketDistributor.sendToServer(
                new StockLockPayload(freight.getId(), !freight.isLocked()));
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        if (builtLocked != freight.isLocked()
                || builtCoupling != CouplingButtons.state(freight)) {
            rebuildWidgets();
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(font, freight.getDisplayName().getString(), 10, 6, LABEL_COLOUR, false);
        graphics.text(font, "Inventory", 8, imageHeight - 96 + 2, LABEL_COLOUR, false);
        if (intersectsWith(mouseX, mouseY)) {
            drawLockTooltip(graphics);
        }
    }

    /** The lock button's own tooltip, drawn in the panel's coordinates as upstream does. */
    protected void drawLockTooltip(GuiGraphicsExtractor graphics) {
        int width = font.width("the GUI, change speed, destroy it.");
        int x = 90;
        int y = 5;
        graphics.fill(x - 3, y - 4, x + width + 3, y + 52, TOOLTIP_BACKGROUND);
        graphics.fill(x - 4, y - 3, x + width + 4, y + 51, TOOLTIP_BACKGROUND);
        graphics.fillGradient(
                x - 3, y - 3, x + width + 3, y + 51, TOOLTIP_BORDER_TOP, TOOLTIP_BORDER_BOTTOM);
        graphics.fill(x - 2, y - 2, x + width + 2, y + 50, TOOLTIP_BACKGROUND);
        graphics.text(font, "When a " + stockNoun() + " is locked,", x, y, -1, false);
        graphics.text(font, "only its owner can open", x, y + 10, -1, false);
        graphics.text(font, "the GUI and destroy it.", x, y + 20, -1, false);
        graphics.text(
                font,
                "Current state: " + (freight.isLocked() ? "Locked" : "Unlocked"),
                x,
                y + 30,
                -1,
                false);
        graphics.text(font, "Owner: " + freight.getOwner().trim(), x, y + 40, -1, false);
    }

    protected boolean intersectsWith(int mouseX, int mouseY) {
        return mouseX >= leftPos + 124
                && mouseX <= leftPos + 174
                && mouseY >= topPos - 10
                && mouseY <= topPos;
    }

    @Override
    public void extractContents(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        extractBackground(graphics);
        super.extractContents(graphics, mouseX, mouseY, partial);
    }

    protected void extractBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                BACKGROUND,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                rows * 18 + 17,
                SHEET,
                SHEET);
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                BACKGROUND,
                leftPos,
                topPos + rows * 18 + 17,
                0,
                126,
                imageWidth,
                96,
                SHEET,
                SHEET);
    }
}
