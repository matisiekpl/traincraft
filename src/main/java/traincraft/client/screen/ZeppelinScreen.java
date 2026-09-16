package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import traincraft.Traincraft;
import traincraft.vehicle.entity.ZeppelinEntity;
import traincraft.vehicle.inventory.ZeppelinMenu;

public class ZeppelinScreen extends AbstractContainerScreen<ZeppelinMenu> {

    private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_zeppelin.png");
    private static final int SHEET = 256;
    private static final int LABEL_COLOUR = 0xFF404040;

    private final ZeppelinEntity zeppelin;

    public ZeppelinScreen(ZeppelinMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 166);
        this.zeppelin = menu.zeppelin();
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(font, zeppelin.getDisplayName().getString(), 60, 6, LABEL_COLOUR, false);
        graphics.text(font, "Cargo:", 36, 22, LABEL_COLOUR, false);
        graphics.text(font, "Inventory", 8, imageHeight - 96 + 2, LABEL_COLOUR, false);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, SHEET, SHEET);
        int level = zeppelin.getFuel() * 12 / ZeppelinEntity.MAX_FUEL;
        if (level > 0) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 8, topPos + 48 - level, 176, 12 - level, 14, level + 2, SHEET, SHEET);
        }
        super.extractContents(graphics, mouseX, mouseY, partial);
    }
}
