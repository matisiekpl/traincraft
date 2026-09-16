package traincraft.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import traincraft.Traincraft;

/**
 * Client-only entry point. NeoForge constructs this only on the physical client, so every class
 * reachable from here — renderers, models, TMT, GUI, the screenshot harness — is guaranteed never
 * to be classloaded on a dedicated server.
 *
 * <p>{@code runGameTestServer} and {@code runServer} are the automated guards for that invariant:
 * either will crash if common code reaches into this subtree.
 */
@Mod(value = Traincraft.MODID, dist = Dist.CLIENT)
public class TraincraftClient {
    public TraincraftClient(IEventBus modBus, ModContainer container) {
        Traincraft.LOGGER.info("Traincraft client loading");
        container.registerConfig(ModConfig.Type.CLIENT, TraincraftClientConfig.SPEC);
    }
}
