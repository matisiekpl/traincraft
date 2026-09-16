package traincraft.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.Traincraft;
import traincraft.network.LocomotiveActionPayload;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.SteamLocomotiveEntity;
import traincraft.vehicle.inventory.LocomotiveMenu;

public class LocomotiveScreen extends AbstractContainerScreen<LocomotiveMenu> {

    private Identifier background =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_loco_steam.png");

    private static final int SHEET = 256;

    private static final int TITLE_COLOUR = 0xFF000000 | 13871360;

    /** Opaque black, for the outline. Plain 0 is transparent in 26.2. */
    private static final int OUTLINE_COLOUR = 0xFF000000;

    /** Opaque white, for the readout. */
    private static final int READOUT_COLOUR = 0xFFFFFFFF;

    private final LocomotiveEntity loco;

    public LocomotiveScreen(LocomotiveMenu menu, Inventory inventory, Component title) {

        super(menu, inventory, title, 176, 166);
        this.loco = menu.loco();
        if (loco instanceof traincraft.vehicle.entity.DieselLocomotiveEntity) {
            background = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_loco_diesel.png");
        }
        if (loco instanceof traincraft.vehicle.entity.ElectricLocomotiveEntity) {
            background = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tram.png");
        }

        this.titleLabelX = 40;
        this.titleLabelY = 6;
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();

        boolean braked = loco.isParkingBrakeOn();
        builtBraked = braked;
        builtLocked = loco.isLocked();
        builtEngineOn = loco.isEngineOn();
        addRenderableWidget(
                new CustomButton(
                        leftPos + (braked ? 0 : 31),
                        topPos - 13,
                        40,
                        13,
                        braked ? 0 : 41,
                        13,
                        Component.literal(braked ? "Brake on" : "Brake off"),
                        () -> send(LocomotiveActionPayload.ACTION_PARKING_BRAKE)));
        addRenderableWidget(
                Button.builder(
                                Component.literal(loco.isLocked() ? "Locked" : "Unlocked"),
                                button -> send(LocomotiveActionPayload.ACTION_LOCK))
                        .bounds(leftPos + 108, topPos - 10, 67, 10)
                        .build());
        if (!(loco instanceof SteamLocomotiveEntity)) {
            addRenderableWidget(
                    Button.builder(
                                    Component.literal(
                                            loco.isEngineOn() ? "Stop Engine" : "Start Engine"),
                                    button -> send(LocomotiveActionPayload.ACTION_ENGINE))
                            .bounds(leftPos + 108, topPos - 22, 67, 12)
                            .build());
        }
    }

    private void send(int action) {
        ClientPacketDistributor.sendToServer(new LocomotiveActionPayload(loco.getId(), action));
    }

    /** What the buttons on screen were built to say, so a change can be noticed. */
    private boolean builtBraked;

    private boolean builtLocked;
    private boolean builtEngineOn;

    /**
     * Rebuilds the buttons when the locomotive's state changes, and not before.
     *
     * <p>Rebuilding straight after sending -- which is what this did -- rebuilds from the state the
     * client still has, because the server has not answered yet. The label then never changes at
     * all: a brake put on goes on reading "Brake off" until the screen is reopened. Waiting for the
     * synched flag to actually turn is both correct and simpler.
     */
    @Override
    protected void containerTick() {
        super.containerTick();
        if (builtBraked != loco.isParkingBrakeOn()
                || builtLocked != loco.isLocked()
                || builtEngineOn != loco.isEngineOn()) {
            rebuildWidgets();
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

        String name = loco.getDisplayName().getString();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    graphics.text(
                            font, name, titleLabelX + dx, titleLabelY + dy, OUTLINE_COLOUR, false);
                }
            }
        }
        graphics.text(font, name, titleLabelX, titleLabelY, TITLE_COLOUR, false);
    }

    @Override
    public void extractContents(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                background,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                imageHeight,
                SHEET,
                SHEET);

        if (loco instanceof SteamLocomotiveEntity steam && steam.getWater() > 0) {
            int h = Math.min(50, steam.getWaterScaled(50));
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    background,
                    leftPos + 143,
                    topPos + 68 - h,
                    190,
                    69 - h,
                    18,
                    h + 1,
                    SHEET,
                    SHEET);
        }
        if (loco instanceof traincraft.vehicle.entity.DieselLocomotiveEntity diesel) {
            int h = Math.min(50, diesel.getFuelScaled(50));
            graphics.blit(RenderPipelines.GUI_TEXTURED, background, leftPos + 143, topPos + 68 - h, 192, 120 - h, 18, h, SHEET, SHEET);
            if (diesel.isFuelled()) {
                int l = Math.min(12, diesel.getFuelScaled(12));
                graphics.blit(RenderPipelines.GUI_TEXTURED, background, leftPos + 10, topPos + 36 + 13 - l, 178, 12 - l, 14, l + 2, SHEET, SHEET);
            }
        } else if (loco.isFuelled()) {
            int h = Math.min(12, loco.getFuelScaled(12));
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    background,
                    leftPos + 8,
                    topPos + 36 + 12 - h,
                    176,
                    12 - h,
                    14,
                    h + 2,
                    SHEET,
                    SHEET);
        }

        super.extractContents(graphics, mouseX, mouseY, partial);
        extractReadout(graphics);
        if (overLockButton(mouseX, mouseY)) {
            extractLockHelp(graphics);
        }
        if (loco instanceof traincraft.vehicle.entity.DieselLocomotiveEntity diesel
                && mouseX > leftPos + 143 && mouseX < leftPos + 161
                && mouseY > topPos + 18 && mouseY < topPos + 68) {
            String tip = diesel.getFuel() == 0
                    ? "Fuel: 0mb / " + diesel.getTankCapacity() + "mb"
                    : diesel.getFluidName() + " " + diesel.getFuel() + "mb / " + diesel.getTankCapacity() + "mb";
            graphics.setTooltipForNextFrame(font, Component.literal(tip), mouseX, mouseY);
        }
    }

    /**
     * Community Edition's readout, in its order and at its coordinates.
     *
     * <p>Two of upstream's lines are printed wrong and are corrected here: the speed reduction
     * carried "km/h" twice because the synched string already had it, and the figure in brackets
     * after the maximum speed was the two numbers written next to each other rather than subtracted.
     */
    private void extractReadout(GuiGraphicsExtractor graphics) {
        String fuelRate = "" + loco.getFuelConsumption() * 0.2;
        String[] lines = {
            "Carts pulled: " + loco.getCartsPulled(),
            "Mass pulled: " + (int) loco.getMassPulled() + " tons",
            "Speed reduction: " + (int) loco.getSpeedSlowDown() + " km/h",
            "Accel reduction: " + Math.round(loco.getAccelSlowDown() * 1000.0) / 1000.0,
            "Brake reduction: " + Math.round(loco.getBrakeSlowDown() * 1000.0) / 1000.0,
            "Fuel consumption: "
                    + fuelRate.substring(0, Math.min(fuelRate.length(), 4))
                    + " mB/s",
            "Fuel: " + loco.getFuel(),
            "Power: " + loco.currentHorsePower() + " Mhp",
            "State: " + loco.getState().displayName(),
            "Heat level: " + loco.getOverheatLevel(),
            "Maximum Speed: "
                    + Math.round(loco.getMaxSpeedGauge())
                    + " km/h ("
                    + Math.round(loco.getMaxSpeedGauge() - loco.getSpeedSlowDown())
                    + ")",
            "Destination: "
        };
        for (int i = 0; i < lines.length; i++) {
            graphics.text(font, lines[i], 1, 10 + i * 10, READOUT_COLOUR, false);
        }
    }

    /** Upstream's {@code intersectsWith}: the strip the lock button occupies. */
    private boolean overLockButton(int mouseX, int mouseY) {
        return mouseX >= leftPos + 124
                && mouseX <= leftPos + 174
                && mouseY >= topPos - 10
                && mouseY <= topPos;
    }

    /** Upstream's lock tooltip, panel and all. */
    private void extractLockHelp(GuiGraphicsExtractor graphics) {
        String widest = "the GUI, change speed, destroy it.";
        int textWidth = font.width(widest);
        int startX = 90;
        int startY = 5;
        int background = 0xF0100010;
        int border = 0x505000FF;
        int borderFade = (border & 0xFEFEFE) >> 1 | border & 0xFF000000;
        graphics.fillGradient(
                startX - 3, startY - 4, startX + textWidth + 3, startY + 52, background, background);
        graphics.fillGradient(
                startX - 4, startY - 3, startX + textWidth + 4, startY + 51, background, background);
        graphics.fillGradient(
                startX - 3, startY - 3, startX + textWidth + 3, startY + 51, border, borderFade);
        graphics.fillGradient(
                startX - 2, startY - 2, startX + textWidth + 2, startY + 50, background, background);

        String[] lines = {
            "When a locomotive is locked,",
            "only its owner can open",
            widest,
            "Current state: " + (loco.isLocked() ? "Locked" : "Unlocked"),
            "Owner: " + loco.getOwner().trim()
        };
        for (int i = 0; i < lines.length; i++) {
            graphics.text(font, lines[i], startX, startY + i * 10, READOUT_COLOUR, false);
        }
    }
}
