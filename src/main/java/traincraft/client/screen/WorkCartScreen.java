package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import traincraft.Traincraft;
import traincraft.vehicle.entity.WorkCartEntity;
import traincraft.vehicle.inventory.WorkCartMenu;

public class WorkCartScreen extends FreightScreen<WorkCartMenu> {

    private static final Identifier BACKGROUND =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/furnace.png");

    private static final int SHEET = 256;

    private final WorkCartEntity cart;

    public WorkCartScreen(WorkCartMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 166);
        this.cart = menu.cart();
    }

    @Override
    protected String stockNoun() {
        return "work cart";
    }

    @Override
    protected void extractBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, SHEET, SHEET);
        if (cart.isBurning()) {
            int flame = cart.burnRemaining(12);
            graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 56, topPos + 36 + 12 - flame, 176, 12 - flame, 14, flame + 2, SHEET, SHEET);
        }
        int arrow = cart.cookProgress(24);
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 79, topPos + 34, 176, 14, arrow + 1, 16, SHEET, SHEET);
    }
}
