package traincraft.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class CaptureRunner {

    private CaptureRunner() {}

    public static int run(Path scenario, Path outDir) throws IOException, InterruptedException {
        Path modDir = Repo.modDir();
        Files.createDirectories(outDir);
        Path log = outDir.resolve("run.log");

        List<String> command =
                List.of(
                        Repo.gradleWrapper(modDir).toString(),
                        "runHarness",
                        "--console=plain",
                        "-Pscenario=" + forward(scenario),
                        "-Pout=" + forward(outDir));

        System.out.println("capture: " + scenario.getFileName() + " -> " + outDir);
        GameRun.Result result =
                GameRun.run(
                        command,
                        modDir,
                        log,
                        "[Traincraft/]",
                        line -> {
                            int marker = line.indexOf("[Traincraft/]");
                            System.out.println(
                                    "  "
                                            + (marker < 0
                                                    ? line
                                                    : line.substring(marker + 14).trim()));
                        });
        System.out.println("capture: " + result.outcome() + " in " + result.seconds() + "s");

        if (result.outcome() != GameRun.Outcome.EXITED) {

            return 3;
        }

        Path report = outDir.resolve("report.json");
        if (!Files.exists(report)) {
            System.err.println("capture: no report.json produced; see " + log);
            return 2;
        }
        boolean pass =
                Boolean.TRUE.equals(
                        Json.parseObject(Files.readString(report, StandardCharsets.UTF_8))
                                .get("pass"));
        System.out.println("capture: report pass=" + pass);
        return pass ? 0 : 1;
    }

    /** Gradle on Windows is happier with forward slashes in property values. */
    private static String forward(Path path) {
        return path.toAbsolutePath().toString().replace(java.io.File.separatorChar, '/');
    }
}
