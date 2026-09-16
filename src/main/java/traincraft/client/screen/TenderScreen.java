package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import traincraft.Traincraft;
import traincraft.vehicle.entity.TenderEntity;
import traincraft.vehicle.inventory.TenderMenu;

public class TenderScreen extends FreightScreen<TenderMenu> {

    private static final Identifier BACKGROUND =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tender.png");

    private static final int SHEET = 256;

    private static final int NAME_COLOUR = 0xFF000000 | 13871360;

    private final TenderEntity tender;

    public TenderScreen(TenderMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 166);
        this.tender = menu.tender();
    }

    @Override
    protected String stockNoun() {
        return "tender";
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        String name = tender.getDisplayName().getString();
        for (int x = 34; x <= 36; x++) {
            for (int y = 1; y <= 3; y++) {
                graphics.text(font, name, x, y, 0xFF000000, false);
            }
        }
        graphics.text(font, name, 35, 2, NAME_COLOUR, false);
        if (intersectsWith(mouseX, mouseY)) {
            drawLockTooltip(graphics);
        }
    }

    @Override
    protected void extractBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                BACKGROUND,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                imageHeight,
                SHEET,
                SHEET);
        int capacity = tender.getTankCapacity();
        int level = capacity == 0 ? 0 : Math.min(50, tender.getWater() * 50 / capacity);
        if (level > 0) {
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    BACKGROUND,
                    leftPos + 143,
                    topPos + 69 - level,
                    190,
                    69 - level,
                    18,
                    level,
                    SHEET,
                    SHEET);
        }
    }

    @Override
    public void extractContents(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        super.extractContents(graphics, mouseX, mouseY, partial);
        int capacity = tender.getTankCapacity();
        if (mouseX > leftPos + 143 && mouseX < leftPos + 161
                && mouseY > topPos + 18 && mouseY < topPos + 68) {
            graphics.setTooltipForNextFrame(
                    font,
                    Component.literal("Water: " + tender.getWater() + "mb / " + capacity + "mb"),
                    mouseX,
                    mouseY);
        }
    }
}
