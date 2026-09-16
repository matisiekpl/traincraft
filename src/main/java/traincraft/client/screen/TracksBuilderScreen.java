package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import traincraft.Traincraft;
import traincraft.vehicle.entity.TracksBuilderEntity;
import traincraft.vehicle.inventory.TracksBuilderMenu;

public class TracksBuilderScreen extends FreightScreen<TracksBuilderMenu> {

    private static final Identifier BACKGROUND =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tunnel_builder.png");

    private static final int SHEET_WIDTH = 336;
    private static final int SHEET_HEIGHT = 256;
    private static final int LABEL_COLOUR = 0xFF404040;
    private static final int FUEL_COLOUR = 0xFFE07020;
    private static final int PROFILE_SHADE = 0x90404040;

    private final TracksBuilderEntity builder;

    public TracksBuilderScreen(TracksBuilderMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 330, 246);
        this.builder = menu.builder();
    }

    @Override
    protected String stockNoun() {
        return "builder";
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(Button.builder(Component.literal("Start"), button -> click(TracksBuilderMenu.BUTTON_START))
                .bounds(leftPos + 8, topPos + 166, 40, 18).build());
        addRenderableWidget(Button.builder(Component.literal("Stop"), button -> click(TracksBuilderMenu.BUTTON_STOP))
                .bounds(leftPos + 50, topPos + 166, 40, 18).build());
        addRenderableWidget(Button.builder(Component.literal("Reverse"), button -> click(TracksBuilderMenu.BUTTON_REVERSE))
                .bounds(leftPos + 92, topPos + 166, 60, 18).build());
        addRenderableWidget(Button.builder(Component.literal("Up"), button -> click(TracksBuilderMenu.BUTTON_UP))
                .bounds(leftPos + 8, topPos + 188, 40, 18).build());
        addRenderableWidget(Button.builder(Component.literal("Down"), button -> click(TracksBuilderMenu.BUTTON_DOWN))
                .bounds(leftPos + 50, topPos + 188, 40, 18).build());
    }

    private void click(int id) {
        if (minecraft != null && minecraft.gameMode != null) {
            minecraft.gameMode.handleInventoryButtonClick(menu.containerId, id);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int cell = cellAt(event.x(), event.y());
        if (cell >= 0 && !TracksBuilderEntity.isProfileCell(cell)) {
            click(TracksBuilderMenu.CELL_BUTTONS + cell);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    /** The template cell under the mouse, bottom row first like the entity, or -1. */
    private int cellAt(double mouseX, double mouseY) {
        int column = (int) Math.floor((mouseX - leftPos - TracksBuilderMenu.TEMPLATE_X) / 18.0);
        int screenRow = (int) Math.floor((mouseY - topPos - TracksBuilderMenu.TEMPLATE_Y) / 18.0);
        if (column < 0 || column >= TracksBuilderEntity.TEMPLATE_WIDTH || screenRow < 0 || screenRow >= TracksBuilderEntity.TEMPLATE_HEIGHT) {
            return -1;
        }
        return (TracksBuilderEntity.TEMPLATE_HEIGHT - 1 - screenRow) * TracksBuilderEntity.TEMPLATE_WIDTH + column;
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(font, "Template", TracksBuilderMenu.TEMPLATE_X, 7, LABEL_COLOUR, false);
        graphics.text(font, "Fuel", TracksBuilderMenu.FUEL_X, 132, LABEL_COLOUR, false);
        graphics.text(font, "Materials", TracksBuilderMenu.STORAGE_X, 7, LABEL_COLOUR, false);
        graphics.text(font, "Excavated", TracksBuilderMenu.STORAGE_X, 85, LABEL_COLOUR, false);
        graphics.text(font, "Inventory", TracksBuilderMenu.STORAGE_X, 155, LABEL_COLOUR, false);
        graphics.text(font, "Elevation " + builder.railPosition().getY(), 8, 210, LABEL_COLOUR, false);
        graphics.text(font, "Target " + builder.getPlannedHeight(), 8, 222, LABEL_COLOUR, false);
        graphics.text(font, builder.statusText(), 8, 234, LABEL_COLOUR, false);
        if (intersectsWith(mouseX, mouseY)) {
            drawLockTooltip(graphics);
        }
    }

    @Override
    protected void extractBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, SHEET_WIDTH, SHEET_HEIGHT);
        int level = builder.getFuel() * 18 / TracksBuilderEntity.MAX_FUEL;
        if (level > 0) {
            int x = leftPos + TracksBuilderMenu.FUEL_X + 20;
            int bottom = topPos + TracksBuilderMenu.FUEL_Y + 18;
            graphics.fill(x, bottom - level, x + 6, bottom, FUEL_COLOUR);
        }
        for (int cell = 0; cell < TracksBuilderEntity.TEMPLATE_CELLS; cell++) {
            int x = leftPos + TracksBuilderMenu.TEMPLATE_X + 1 + cell % TracksBuilderEntity.TEMPLATE_WIDTH * 18;
            int y = topPos + TracksBuilderMenu.TEMPLATE_Y + 1 + (TracksBuilderEntity.TEMPLATE_HEIGHT - 1 - cell / TracksBuilderEntity.TEMPLATE_WIDTH) * 18;
            if (TracksBuilderEntity.isProfileCell(cell)) {
                if (cell == TracksBuilderEntity.RAIL_CELL) {
                    graphics.item(new ItemStack(Items.RAIL), x, y);
                }
                graphics.fill(x - 1, y - 1, x + 17, y + 17, PROFILE_SHADE);
                continue;
            }
            Block block = builder.templateBlock(cell);
            if (block != null) {
                graphics.item(new ItemStack(block), x, y);
            }
        }
    }
}
