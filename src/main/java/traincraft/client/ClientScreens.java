package traincraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import traincraft.client.screen.AdminBookScreen;
import traincraft.client.screen.CouplingScreen;
import traincraft.client.screen.LanternScreen;
import traincraft.client.screen.LiveryScreen;
import traincraft.client.screen.EngineNumberScreen;
import traincraft.client.screen.RecipeBookScreen;
import traincraft.network.RecipeBookPayload;
import traincraft.vehicle.entity.RollingStockEntity;

public final class ClientScreens {

    private ClientScreens() {}

    public static void openLantern(BlockPos pos, int colour) {
        Minecraft.getInstance().gui.setScreen(new LanternScreen(pos, colour));
    }

    public static void openCoupling(RollingStockEntity stock) {
        Minecraft.getInstance().gui.setScreen(new CouplingScreen(stock));
    }

    public static void openLivery(RollingStockEntity stock) {
        Minecraft.getInstance().gui.setScreen(new LiveryScreen(stock));
    }

    public static void openEngineNumber(RollingStockEntity stock) {
        Minecraft.getInstance().gui.setScreen(new EngineNumberScreen(stock));
    }

    public static void openAdminBook(String data) {
        Minecraft.getInstance().gui.setScreen(new AdminBookScreen(data));
    }

    public static void openRecipeBook(RecipeBookPayload payload) {
        Minecraft.getInstance().gui.setScreen(new RecipeBookScreen(payload));
    }
}
