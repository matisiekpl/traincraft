package traincraft.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.network.LiveryPayload;
import traincraft.vehicle.entity.RollingStockEntity;

public class LiveryScreen extends Screen {

    private final RollingStockEntity stock;

    public LiveryScreen(RollingStockEntity stock) {
        super(Component.literal("Livery"));
        this.stock = stock;
    }

    @Override
    protected void init() {
        var colours = stock.spec().colours();
        int columns = 4;
        int buttonWidth = 90;
        int left = width / 2 - columns * (buttonWidth + 4) / 2;
        for (int index = 0; index < colours.size(); index++) {
            String colour = colours.get(index);
            addRenderableWidget(Button.builder(Component.literal(colour), button -> {
                ClientPacketDistributor.sendToServer(new LiveryPayload(stock.getId(), colour));
                onClose();
            }).bounds(left + index % columns * (buttonWidth + 4), 40 + index / columns * 24, buttonWidth, 20).build());
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
