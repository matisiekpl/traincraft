package traincraft.tools;

import java.nio.file.Files;
import java.nio.file.Path;

/** Locates the repository layout and toolchain without hardcoding anyone's home directory. */
public final class Repo {

    private Repo() {}

    /**
     * Repository root, i.e. the directory holding {@code mod/}, {@code scenarios/}, {@code docs/}.
     */
    public static Path root() {
        Path here = Path.of("").toAbsolutePath();
        for (Path p = here; p != null; p = p.getParent()) {
            if (Files.isDirectory(p.resolve("scenarios")) && Files.isDirectory(p.resolve("mod"))) {
                return p;
            }
        }
        throw new IllegalStateException("Could not find the repository root from " + here);
    }

    public static Path modDir() {
        return root().resolve("mod");
    }

    public static Path artifactsDir() {
        return root().resolve("artifacts");
    }

    public static Path gradleWrapper(Path projectDir) {
        boolean windows = System.getProperty("os.name", "").toLowerCase().contains("win");
        return projectDir.resolve(windows ? "gradlew.bat" : "gradlew");
    }

    /**
     * The JDK the child Gradle build should run on. Inherits this process's JDK by default, which
     * is already the project's Java 25 toolchain when invoked through Gradle.
     */
    public static String javaHome() {
        return System.getProperty("tc.javaHome", System.getProperty("java.home"));
    }
}
