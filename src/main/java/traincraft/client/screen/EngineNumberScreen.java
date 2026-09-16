package traincraft.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.network.EngineNumberPayload;
import traincraft.vehicle.entity.RollingStockEntity;

public final class EngineNumberScreen extends Screen {
    private final RollingStockEntity stock;
    private EditBox number;

    public EngineNumberScreen(RollingStockEntity stock) {
        super(Component.literal("Engine Number"));
        this.stock = stock;
    }

    @Override
    protected void init() {
        number = new EditBox(font, width / 2 - 75, height / 2 - 10, 150, 20,
                Component.literal("Engine Number"));
        number.setMaxLength(12);
        number.setValue(stock.getEngineNumber());
        addRenderableWidget(number);
        addRenderableWidget(Button.builder(Component.literal("Apply"), button -> apply())
                .bounds(width / 2 - 45, height / 2 + 18, 90, 20).build());
        setInitialFocus(number);
    }

    private void apply() {
        ClientPacketDistributor.sendToServer(
                new EngineNumberPayload(stock.getId(), RollingStockEntity.sanitizeEngineNumber(number.getValue())));
        onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
