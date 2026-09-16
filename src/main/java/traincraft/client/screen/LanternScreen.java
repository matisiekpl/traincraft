package traincraft.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.network.StructureActionPayload;

public class LanternScreen extends Screen {

    private final BlockPos pos;
    private final int colour;
    private EditBox colourField;

    public LanternScreen(BlockPos pos, int colour) {
        super(Component.literal("Lantern colour"));
        this.pos = pos;
        this.colour = colour;
    }

    @Override
    protected void init() {
        colourField = new EditBox(font, width / 2 - 150, 60, 300, 20, Component.literal("Colour"));
        colourField.setValue(String.format("#%06X", colour));
        addRenderableWidget(colourField);
        addRenderableWidget(Button.builder(Component.literal("Done"), button -> done()).bounds(width / 2 - 100, 100, 200, 20).build());
        setInitialFocus(colourField);
    }

    private void done() {
        String text = colourField.getValue().trim().replaceFirst("^#", "");
        if (text.length() == 6) {
            try {
                ClientPacketDistributor.sendToServer(new StructureActionPayload(pos, Integer.parseInt(text, 16)));
            } catch (NumberFormatException ignored) {
                return;
            }
        }
        onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
