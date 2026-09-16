package traincraft.development;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;

import net.minecraft.gametest.framework.GlobalTestReporter;
import net.minecraft.gametest.framework.JUnitLikeTestReporter;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import traincraft.development.gametest.GameTestRegistry;

/** Registers test infrastructure only in development runs. */
@Mod(DevelopmentMod.MODID)
public final class DevelopmentMod {
    public static final String MODID = "tc_development";

    public DevelopmentMod(IEventBus bus) {
        GameTestRegistry.TYPES.register(bus);
        installJunitReporter();
    }

    private static void installJunitReporter() {
        String path = System.getProperty("tc.gametestReport");
        if (path == null) {
            return;
        }
        File destination = new File(path);
        destination.getParentFile().mkdirs();
        try {
            GlobalTestReporter.replaceWith(new JUnitLikeTestReporter(destination));
        } catch (ParserConfigurationException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
