package traincraft;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Locates committed test fixtures.
 *
 * <p>NeoForge's unit-test support runs the test JVM from {@code build/minecraft-junit} rather than
 * the project directory, so a plain relative path silently resolves to the wrong place. The Gradle
 * test task passes {@code tc.projectDir}; the walk-up is the fallback for running a test straight
 * from an IDE.
 */
public final class TestPaths {

    private TestPaths() {}

    public static Path projectDir() {
        String configured = System.getProperty("tc.projectDir");
        if (configured != null && !configured.isBlank()) {
            return Path.of(configured);
        }
        Path here = Path.of("").toAbsolutePath();
        for (Path p = here; p != null; p = p.getParent()) {
            if (Files.isRegularFile(p.resolve("build.gradle"))
                    && Files.isDirectory(p.resolve("src/main/resources/assets/tc"))) {
                return p;
            }
        }
        throw new IllegalStateException("Cannot locate the mod project directory from " + here);
    }

    public static Path testResource(String relative) {
        return projectDir().resolve("src/test/resources").resolve(relative);
    }

    public static Path mainResource(String relative) {
        return projectDir().resolve("src/main/resources").resolve(relative);
    }
}
