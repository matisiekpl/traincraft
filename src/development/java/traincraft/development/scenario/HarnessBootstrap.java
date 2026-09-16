package traincraft.development.scenario;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

import org.jspecify.annotations.Nullable;

import traincraft.Traincraft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Activates the capture harness when the game is launched with {@code -Dtc.harness.scenario=<path>}
 * (see the {@code runHarness} Gradle run config).
 *
 * <p>Inert in a normal client: with the property unset nothing is parsed and no listener does any
 * work, so shipping the harness in the same source set costs a null check per tick.
 */
@EventBusSubscriber(modid = traincraft.development.DevelopmentMod.MODID, value = Dist.CLIENT)
public final class HarnessBootstrap {

    public static final String SCENARIO_PROPERTY = "tc.harness.scenario";
    public static final String OUT_PROPERTY = "tc.harness.out";

    private static @Nullable HarnessDriver driver;
    private static boolean initialised;

    private HarnessBootstrap() {}

    private static @Nullable HarnessDriver driver() {
        if (!initialised) {
            initialised = true;
            driver = create();
        }
        return driver;
    }

    private static @Nullable HarnessDriver create() {
        String scenarioPath = System.getProperty(SCENARIO_PROPERTY, "").trim();
        if (scenarioPath.isEmpty()) {
            return null;
        }
        try {
            Scenario scenario = Scenario.load(resolve(scenarioPath));
            String out = System.getProperty(OUT_PROPERTY, "").trim();
            Path outDir =
                    out.isEmpty()
                            ? Minecraft.getInstance()
                                    .gameDirectory
                                    .toPath()
                                    .resolve("capture")
                                    .resolve(scenario.id)
                            : Path.of(out);
            Files.createDirectories(outDir);
            Traincraft.LOGGER.info("Harness armed: scenario={} out={}", scenario.id, outDir);
            return new HarnessDriver(scenario, outDir);
        } catch (IOException | RuntimeException e) {
            // Sitting at the title screen with an error buried in the log looks like a hang, and
            // the run then has to be killed by hand. A scenario that was asked for and cannot be
            // read is a failed run, so say so where it cannot be missed and stop the client.
            Traincraft.LOGGER.error("Could not arm harness from {}", scenarioPath, e);
            Minecraft.getInstance().stop();
            throw new IllegalStateException("harness could not load scenario " + scenarioPath, e);
        }
    }

    /**
     * Finds a scenario given a path that may be relative to anything.
     *
     * <p>The client runs in {@code mod/run-harness}, so a path written relative to the project root
     * -- which is how every command line and every script writes it -- misses by two directories.
     * Rather than make the caller know where the game directory happens to be, a relative path that
     * does not resolve is retried against each ancestor of the game directory.
     */
    private static Path resolve(String scenarioPath) {
        Path direct = Path.of(scenarioPath);
        if (Files.isRegularFile(direct)) {
            return direct;
        }
        if (direct.isAbsolute()) {
            return direct;
        }
        for (Path dir = Minecraft.getInstance().gameDirectory.toPath().toAbsolutePath();
                dir != null;
                dir = dir.getParent()) {
            Path candidate = dir.resolve(scenarioPath).normalize();
            if (Files.isRegularFile(candidate)) {
                return candidate;
            }
        }
        return direct;
    }

    @SubscribeEvent
    static void onClientTick(ClientTickEvent.Post event) {
        HarnessDriver d = driver();
        if (d != null && !d.isFinished()) {
            d.tick(Minecraft.getInstance());
        }
    }

    @SubscribeEvent
    static void onFrameEnd(RenderFrameEvent.Post event) {
        HarnessDriver d = driver();
        if (d != null && !d.isFinished()) {
            d.onFrameEnd(Minecraft.getInstance());
        }
    }
}
