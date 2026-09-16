package traincraft.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.adminbook.AdminBookRequestPayload;
import traincraft.adminbook.StockLog;

/** Community Edition's {@code GUIAdminBook}: the logged stock by owner, and one entry with its inventory. */
public class AdminBookScreen extends Screen {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private final String[] list;
    private final boolean entry;
    private final List<ItemStack> items;
    private int guiLeft;
    private int guiTop;
    private int page;

    public AdminBookScreen(String data) {
        super(Component.literal("Admin book"));
        list = data.split(",");
        entry = data.startsWith("<");
        items = entry && list.length > 9 ? StockLog.stacks(list[9]) : List.of();
    }

    private void request(String id) {
        ClientPacketDistributor.sendToServer(new AdminBookRequestPayload(id));
    }

    @Override
    protected void init() {
        guiLeft = (width - 176) / 2;
        guiTop = (height - 166) / 2;
        if (entry) {
            String id = list[0].substring(1);
            addRenderableWidget(Button.builder(Component.literal("clone inventory"), button -> request("0:" + id)).bounds(guiLeft + 85, guiTop + 140, 90, 20).build());
            addRenderableWidget(Button.builder(Component.literal("delete entry"), button -> request("1:" + id)).bounds(guiLeft + 5, guiTop + 140, 70, 20).build());
            addRenderableWidget(Button.builder(Component.literal("clone & delete"), button -> {
                request("0:" + id);
                request("1:" + id);
            }).bounds(guiLeft + 180, guiTop + 140, 80, 20).build());
            addRenderableWidget(Button.builder(Component.literal("back"), button -> request(list[1])).bounds(guiLeft - 70, guiTop + 140, 70, 20).build());
            return;
        }
        int index = 0;
        for (int i = 6 * page; i < 6 + 6 * page && i < list.length; i++) {
            String id = list[i];
            String label = id.isEmpty() ? "Back" : id.contains("_") ? id.substring(id.indexOf("~") + 1, id.lastIndexOf("_")) : id;
            addRenderableWidget(Button.builder(Component.literal(label), button -> request(id)).bounds(guiLeft - 80, guiTop + 20 + index * 18, 140, 20).build());
            if (id.lastIndexOf("_") > 0 && id.indexOf(".txt") > 0) {
                addRenderableWidget(Button.builder(Component.literal(id.substring(id.lastIndexOf("_") + 1, id.indexOf(".txt"))), button -> request(id))
                        .bounds(guiLeft + 70, guiTop + 20 + index * 18, 220, 20).build());
            }
            index++;
        }
        if (list.length - 6 - page * 6 > 0) {
            addRenderableWidget(Button.builder(Component.literal("next page"), button -> turn(1)).bounds(guiLeft - 70, guiTop + 140, 70, 20).build());
        }
        if (page > 0) {
            addRenderableWidget(Button.builder(Component.literal("back"), button -> turn(-1)).bounds(guiLeft + 10, guiTop + 140, 70, 20).build());
        }
    }

    private void turn(int delta) {
        page += delta;
        rebuildWidgets();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        super.extractRenderState(graphics, mouseX, mouseY, partial);
        if (!entry) {
            return;
        }
        for (int i = 2; i < Math.min(list.length, 9); i++) {
            outlined(graphics, list[i], guiLeft - 70, guiTop - 16 + i * 16);
        }
        outlined(graphics, Component.translatable("container.inventory").getString(), guiLeft + 80, guiTop + 10);
        for (int index = 0; index < items.size() && index < 54; index++) {
            graphics.item(items.get(index), guiLeft + 80 + index % 9 * 16, guiTop + 26 + index / 9 * 16);
            graphics.itemDecorations(font, items.get(index), guiLeft + 80 + index % 9 * 16, guiTop + 26 + index / 9 * 16);
        }
    }

    private void outlined(GuiGraphicsExtractor graphics, String text, int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                graphics.text(font, text, x + dx, y + dy, BLACK, false);
            }
        }
        graphics.text(font, text, x, y, WHITE, false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
