package traincraft.client.screen;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.gui.GuiLayer;

import traincraft.Traincraft;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.SteamLocomotiveEntity;

public class LocomotiveHud implements GuiLayer {

    public static final Identifier ID =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "loco_hud");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/loco_hud_steam.png");

    private static final int SHEET = 256;

    private static final int TEXT_COLOUR = 0xFFFFFFFF;

    /** The dial's full scale, shared by every locomotive. */
    private static final double SPEEDOMETER_FULL_SCALE = 280.0;

    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null
                || !(minecraft.player.getVehicle() instanceof LocomotiveEntity loco)) {
            return;
        }

        Identifier texture = loco instanceof SteamLocomotiveEntity ? TEXTURE
                : Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/locohud.png");
        int base = graphics.guiHeight() - 100;
        graphics.blit(
                RenderPipelines.GUI_TEXTURED, texture, 10, base, 0, 150, 137, 90, SHEET, SHEET);

        int fuel = Math.clamp(loco.getFuelScaled(70), 0, 70);
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                texture,
                34,
                base + 17,
                154,
                170 + fuel,
                9,
                70 - fuel,
                SHEET,
                SHEET);

        if (loco instanceof SteamLocomotiveEntity steam) {
            int water = Math.clamp(steam.getWaterScaled(49), 0, 49);
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    texture,
                    70,
                    base + 17,
                    190,
                    169 + water,
                    6,
                    49 - water,
                    SHEET,
                    SHEET);

            if (steam.getWater() <= 1 && steam.isFuelled()) {
                graphics.fillGradient(
                        0, 0, graphics.guiWidth(), base + 100, 0x60520000, 0xA0770000);
            }
        }

        // The heat bar, drawn only for a locomotive that has a boiler. Its full scale is the
        // heating time plus thirty -- the same thirty that floors the average -- so every
        // locomotive's needle sits in the same place when it is running properly, whatever its
        // own heating time.
        if (loco.canOverheat()) {
            int full = loco.getOverheatTime() + 30;
            int heat = Math.clamp(Math.min(loco.getOverheatLevel(), full) * 49 / full, 0, 49);
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    texture,
                    56,
                    base + 17,
                    176,
                    169 + heat,
                    5,
                    49 - heat,
                    SHEET,
                    SHEET);
        }

        int speed = Math.abs(loco.getSpeedKmH());
        int needle = (int) Math.min(49.0, speed * 49.0 / SPEEDOMETER_FULL_SCALE);
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                texture,
                84,
                base + 57 - needle,
                177,
                149,
                16,
                8,
                SHEET,
                SHEET);

        int textTop = base + 15;
        graphics.text(minecraft.font, "Speed:", 106, textTop + 7, TEXT_COLOUR, false);
        graphics.text(minecraft.font, "  " + speed, 106, textTop + 18, TEXT_COLOUR, false);
        graphics.text(minecraft.font, " Km/h", 106, textTop + 29, TEXT_COLOUR, false);

        if (loco.canOverheat()) {
            graphics.text(
                    minecraft.font,
                    "State: " + loco.getState().displayName(),
                    50,
                    base + 80,
                    TEXT_COLOUR,
                    false);
        }
    }
}
