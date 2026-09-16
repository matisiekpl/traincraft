package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import traincraft.Traincraft;

public class CustomButton extends AbstractButton {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/custombutton.png");

    private static final int SHEET = 256;

    private final Identifier texture;
    private final int u;
    private final int v;
    private final int hoverU;
    private final Runnable action;

    public CustomButton(
            int x,
            int y,
            int width,
            int height,
            int u,
            int v,
            Component narration,
            Runnable action) {
        this(TEXTURE, x, y, width, height, u, v, u, narration, action);
    }

    public CustomButton(
            Identifier texture,
            int x,
            int y,
            int width,
            int height,
            int u,
            int v,
            int hoverU,
            Component narration,
            Runnable action) {
        super(x, y, width, height, narration);
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.hoverU = hoverU;
        this.action = action;
    }

    @Override
    public void onPress(net.minecraft.client.input.InputWithModifiers input) {
        action.run();
    }

    @Override
    protected void extractContents(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                texture,
                getX(),
                getY(),
                isHoveredOrFocused() ? hoverU : u,
                v,
                width,
                height,
                SHEET,
                SHEET);
    }

    @Override
    protected void updateWidgetNarration(
            net.minecraft.client.gui.narration.NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}
