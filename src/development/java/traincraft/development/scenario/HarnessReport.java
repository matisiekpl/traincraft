package traincraft.development.scenario;

import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;

import traincraft.Traincraft;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The machine-readable half of a capture run.
 *
 * <p>Everything that could make two runs differ is recorded here -- GPU, graphics backend, window
 * size, GUI scale, versions -- so that a screenshot which stops matching can be diagnosed instead
 * of merely re-baselined. Screenshot SHA-256 hashes make a stale or duplicated frame detectable.
 */
public final class HarnessReport {

    private final Scenario scenario;
    private final Path outDir;
    private final List<String> shots = new ArrayList<>();
    private final List<Map<String, Object>> failures = new ArrayList<>();
    private final List<String> optionDrift = new ArrayList<>();
    private final long startedAtNanos = System.nanoTime();

    public HarnessReport(Scenario scenario, Path outDir) {
        this.scenario = scenario;
        this.outDir = outDir;
    }

    public void recordShot(String fileName) {
        shots.add(fileName);
    }

    /**
     * Records a client setting the scenario asked for that the game did not actually apply. Not a
     * failure -- the capture is still usable -- but it must be visible, because a setting that
     * silently differs between runs is exactly what makes a screenshot comparison lie.
     */
    public void optionDrift(String message) {
        optionDrift.add(message);
    }

    public void fail(String stage, String message) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("stage", stage);
        row.put("message", message);
        failures.add(row);
    }

    public void finish(Minecraft mc, List<Map<String, Object>> assertions) {
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("scenario", scenario.id);
        root.put("description", scenario.description);
        root.put("seed", scenario.seed);
        root.put("durationMs", (System.nanoTime() - startedAtNanos) / 1_000_000L);

        Map<String, Object> env = new LinkedHashMap<>();
        env.put("minecraft", "26.2");
        env.put(
                "mod",
                ModList.get()
                        .getModContainerById(Traincraft.MODID)
                        .map(c -> c.getModInfo().getVersion().toString())
                        .orElse("unknown"));
        env.put("windowWidth", mc.getWindow().getWidth());
        env.put("windowHeight", mc.getWindow().getHeight());
        env.put("guiScale", mc.options.guiScale().get());
        env.put("fov", mc.options.fov().get());
        env.put("renderDistance", mc.options.renderDistance().get());
        env.put("graphicsPreset", mc.options.graphicsPreset().get().name());
        env.put("preferredGraphicsBackend", mc.options.preferredGraphicsBackend().get().name());
        env.put("hudHidden", mc.gui.hud.isHidden());
        root.put("env", env);

        List<Map<String, Object>> shotRows = new ArrayList<>();
        for (String name : shots) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("file", name);
            row.put("sha256", sha256(outDir.resolve("screenshots").resolve(name)));
            shotRows.add(row);
        }
        root.put("screenshots", shotRows);
        root.put("assertions", assertions);
        root.put("optionDrift", optionDrift);
        root.put("failures", failures);

        boolean pass =
                failures.isEmpty()
                        && assertions.stream().allMatch(a -> Boolean.TRUE.equals(a.get("pass")))
                        && shots.size() == scenario.steps.stream().filter(s -> s.shoot).count();
        root.put("pass", pass);

        try {
            Files.createDirectories(outDir);
            Files.writeString(
                    outDir.resolve("report.json"),
                    new GsonBuilder().setPrettyPrinting().create().toJson(root) + "\n",
                    StandardCharsets.UTF_8);
            Traincraft.LOGGER.info(
                    "Harness report written to {} (pass={})", outDir.resolve("report.json"), pass);
        } catch (IOException e) {
            Traincraft.LOGGER.error("Could not write harness report", e);
        }
    }

    private static String sha256(Path file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Files.readAllBytes(file));
            StringBuilder sb = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                sb.append(Character.forDigit((b >> 4) & 0xF, 16))
                        .append(Character.forDigit(b & 0xF, 16));
            }
            return sb.toString();
        } catch (Exception e) {
            return "unavailable: " + e;
        }
    }
}
