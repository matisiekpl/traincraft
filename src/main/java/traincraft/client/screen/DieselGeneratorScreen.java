package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import traincraft.Traincraft;
import traincraft.structure.DieselGeneratorBlockEntity;
import traincraft.structure.DieselGeneratorMenu;

public class DieselGeneratorScreen extends AbstractContainerScreen<DieselGeneratorMenu> {

    private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_generator_diesel.png");
    private static final int SHEET = 256;
    private static final int LABEL_COLOUR = 0xFF404040;

    public DieselGeneratorScreen(DieselGeneratorMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 166);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(font, "Diesel Generator", 8, 6, LABEL_COLOUR, false);
        graphics.text(font, "Inventory", 8, imageHeight - 96 + 2, LABEL_COLOUR, false);
        if (mouseX >= leftPos + 145 && mouseX <= leftPos + 163 && mouseY >= topPos + 7 && mouseY <= topPos + 57) {
            graphics.setTooltipForNextFrame(font, Component.literal(menu.diesel() + "/" + DieselGeneratorBlockEntity.TANK_CAPACITY + " mB, " + menu.energy() + " RF"), mouseX, mouseY);
        }
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, SHEET, SHEET);
        int liquid = menu.diesel() * 50 / DieselGeneratorBlockEntity.TANK_CAPACITY;
        if (liquid > 0) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 145, topPos + 57 - liquid, 177, 107 - liquid, 18, liquid, SHEET, SHEET);
        }
        if (menu.burnTime() > 0) {
            int flame = menu.burnTime() * 12 / 8;
            graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 56, topPos + 48 - flame, 176, 12 - flame, 14, flame + 2, SHEET, SHEET);
        }
        super.extractContents(graphics, mouseX, mouseY, partial);
    }
}
