package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;

import traincraft.Traincraft;
import traincraft.vehicle.entity.TankCartEntity;
import traincraft.vehicle.inventory.TankMenu;

public class TankScreen extends FreightScreen<TankMenu> {

    private static final Identifier BACKGROUND =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_liquid.png");

    private static final int SHEET = 256;

    private static final int FUEL_COLOUR = 0xFF8A6D2B;
    private static final int LAVA_COLOUR = 0xFFD45A12;
    private static final int WATER_COLOUR = 0xFF3F76E4;
    private static final int OTHER_COLOUR = 0xFF7F9FAF;

    private final TankCartEntity tank;

    public TankScreen(TankMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 166);
        this.tank = menu.tank();
    }

    @Override
    protected String stockNoun() {
        return "tank cart";
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        String name = tank.getDisplayName().getString();
        for (int x = 63; x <= 65; x++) {
            for (int y = 1; y <= 3; y++) {
                graphics.text(font, name, x, y, 0xFF000000, false);
            }
        }
        graphics.text(font, name, 64, 2, 0xFFD3B800, false);
        if (intersectsWith(mouseX, mouseY)) {
            drawLockTooltip(graphics);
        }
    }

    @Override
    protected void extractBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, SHEET, SHEET);
        int capacity = tank.getTankCapacity();
        int level = capacity == 0 ? 0 : Math.min(50, tank.getAmount() * 50 / capacity);
        if (level > 0) {
            var fluid = tank.displayedFluid();
            int colour = fluid == Fluids.EMPTY ? FUEL_COLOUR : fluid.isSame(Fluids.LAVA) ? LAVA_COLOUR : fluid.isSame(Fluids.WATER) ? WATER_COLOUR : OTHER_COLOUR;
            graphics.fill(leftPos + 58, topPos + 67 - level, leftPos + 122, topPos + 67, colour);
        }
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos + 58, topPos + 17, 72, 167, 64, 50, SHEET, SHEET);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        super.extractContents(graphics, mouseX, mouseY, partial);
        if (mouseX >= leftPos + 57 && mouseX <= leftPos + 123 && mouseY >= topPos + 16 && mouseY <= topPos + 68) {
            String name = tank.getFluidName();
            String label = tank.getAmount() == 0 ? "0mb/" + tank.getTankCapacity() + "mb"
                    : (name.contains(":") ? Component.translatable(tank.displayedFluid().getFluidType().getDescriptionId()).getString() : name)
                            + ": " + tank.getAmount() + "mb/" + tank.getTankCapacity() + "mb";
            graphics.setTooltipForNextFrame(font, Component.literal(label), mouseX, mouseY);
        }
    }
}
