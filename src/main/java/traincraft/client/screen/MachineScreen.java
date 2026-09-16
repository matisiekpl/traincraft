package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import traincraft.production.MachineKind;
import traincraft.production.MachineMenu;

public final class MachineScreen extends AbstractContainerScreen<MachineMenu> {
    private final Identifier texture;
    public MachineScreen(MachineMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, menu.machine.kind.assembly() ? 256 : 166);
        texture = Identifier.fromNamespaceAndPath("tc", "textures/gui/" + menu.machine.kind.texture + ".png");
        inventoryLabelY = imageHeight - 94;
    }
    private void blit(GuiGraphicsExtractor g, int x, int y, int u, int v, int w, int h) {
        g.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos+x, topPos+y, u,v,w,h,256,256);
    }
    @Override public void extractContents(GuiGraphicsExtractor g, int mouseX, int mouseY, float partial) {
        blit(g,0,0,0,0,imageWidth,imageHeight);
        var kind = menu.machine.kind;
        if (!kind.instantaneous()) {
            int burn = menu.data.get(2) == 0 ? 0 : Math.clamp(menu.data.get(1)*12/menu.data.get(2), 0, 12);
            if (burn > 0) {
                blit(g,56,48-burn,176,12-burn,14,burn+2);
                if (kind == MachineKind.HEARTH) blit(g,36,48-burn,176,12-burn,14,burn+2);
            }
            if (kind == MachineKind.HEARTH) blit(g,79,34,176,14,Math.clamp(menu.data.get(0)*24/1000,0,24)+1,16);
            else {
                blit(g,87,36,184,15,Math.clamp(menu.data.get(0)*22/400,0,22),41);
                int h = Math.clamp(menu.data.get(3)*50/30000,0,50);
                blit(g,145,57-h,177,107-h,18,h);
            }
        }
        super.extractContents(g,mouseX,mouseY,partial);
    }
}
