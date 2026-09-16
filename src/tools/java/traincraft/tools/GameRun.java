package traincraft.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Runs a Minecraft client through Gradle and refuses to wait for it forever.
 *
 * <p>A hung game is the normal failure of this kind of harness, not an exceptional one: the client
 * can sit at a dialog nobody will click, fail to finish generating a world, or deadlock on a
 * driver. None of those exit, and none of them print anything further. Left alone the run occupies
 * a Gradle daemon and several hundred megabytes until somebody notices by hand — which is exactly
 * what happened before this existed.
 *
 * <p>Two independent limits, because they catch different failures:
 *
 * <ul>
 *   <li>a <strong>stall</strong> limit — the log has not grown for this long, so the game is alive
 *       but not progressing. This is the one that catches a hang, and it catches it quickly.
 *   <li>an <strong>overall</strong> limit — a run that keeps logging but never finishes.
 * </ul>
 *
 * <p>Killing is done depth-first over the process tree. Gradle launches the game as a child, so
 * destroying only the process we started leaves the actual client running.
 */
public final class GameRun {

    private static final long STALL_SECONDS = Long.getLong("tc.run.stallSeconds", 120);
    private static final long OVERALL_SECONDS = Long.getLong("tc.run.overallSeconds", 600);
    private static final long POLL_MILLIS = 2000;

    public enum Outcome {
        EXITED,
        STALLED,
        TIMED_OUT
    }

    public record Result(Outcome outcome, int exitCode, long seconds) {
        public boolean ok() {
            return outcome == Outcome.EXITED && exitCode == 0;
        }
    }

    private GameRun() {}

    /**
     * @param command the full command line, first element being the executable
     * @param directory working directory for the child
     * @param log file the child's output is redirected to; also the progress signal
     * @param onLine called with each new line matching {@code echoFilter}, for live feedback
     */
    public static Result run(
            List<String> command,
            Path directory,
            Path log,
            String echoFilter,
            java.util.function.Consumer<String> onLine)
            throws IOException, InterruptedException {
        return run(command, directory, log, echoFilter, onLine, STALL_SECONDS, OVERALL_SECONDS);
    }

    public static Result run(
            List<String> command,
            Path directory,
            Path log,
            String echoFilter,
            java.util.function.Consumer<String> onLine,
            long stallSeconds,
            long overallSeconds)
            throws IOException, InterruptedException {
        Files.createDirectories(log.getParent());
        Files.deleteIfExists(log);

        ProcessBuilder pb =
                new ProcessBuilder(command)
                        .directory(directory.toFile())
                        .redirectErrorStream(true)
                        .redirectOutput(log.toFile());
        pb.environment().put("JAVA_HOME", Repo.javaHome());

        long startedAt = System.nanoTime();
        Process process = pb.start();

        long lastSize = -1;
        long lastGrowthNanos = System.nanoTime();
        long echoed = 0;

        while (process.isAlive()) {
            Thread.sleep(POLL_MILLIS);

            long size = Files.exists(log) ? Files.size(log) : 0;
            if (size != lastSize) {
                lastSize = size;
                lastGrowthNanos = System.nanoTime();
                echoed = echo(log, echoed, echoFilter, onLine);
            }

            long stalledFor = (System.nanoTime() - lastGrowthNanos) / 1_000_000_000L;
            if (stalledFor >= stallSeconds) {
                System.err.println("run: no output for " + stalledFor + "s, treating as hung");
                kill(process);
                return new Result(Outcome.STALLED, -1, elapsed(startedAt));
            }
            if (elapsed(startedAt) >= overallSeconds) {
                System.err.println("run: exceeded " + overallSeconds + "s overall, killing");
                kill(process);
                return new Result(Outcome.TIMED_OUT, -1, elapsed(startedAt));
            }
        }

        echo(log, echoed, echoFilter, onLine);
        return new Result(Outcome.EXITED, process.exitValue(), elapsed(startedAt));
    }

    private static long elapsed(long startedAtNanos) {
        return (System.nanoTime() - startedAtNanos) / 1_000_000_000L;
    }

    /**
     * Destroys the process and everything it started. Gradle runs the game as a child, so killing
     * only the process we launched would leave the client alive and holding the world lock.
     */
    private static void kill(Process process) throws InterruptedException {
        process.descendants().forEach(ProcessHandle::destroyForcibly);
        process.destroyForcibly();
        if (!process.waitFor(30, TimeUnit.SECONDS)) {
            System.err.println("run: process did not die within 30s of being killed");
        }
    }

    /** Echoes new matching lines and returns the number of lines consumed so far. */
    private static long echo(
            Path log,
            long alreadyEchoed,
            String filter,
            java.util.function.Consumer<String> onLine) {
        if (onLine == null || !Files.exists(log)) {
            return alreadyEchoed;
        }
        try (var lines = Files.lines(log, java.nio.charset.StandardCharsets.UTF_8)) {
            long index = 0;
            long consumed = 0;
            for (String line : (Iterable<String>) lines::iterator) {
                index++;
                consumed = index;
                if (index <= alreadyEchoed) {
                    continue;
                }
                if (filter == null || line.contains(filter)) {
                    onLine.accept(line);
                }
            }
            return consumed;
        } catch (IOException | java.io.UncheckedIOException e) {
            // The log is being written to; a partial read is expected and not worth reporting.
            return alreadyEchoed;
        }
    }
}
