package traincraft;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class AssetIdentifierTest {

    /** {@code Identifier.fromNamespaceAndPath(<anything>, "some/path.png")}. */
    private static final Pattern IDENTIFIER =
            Pattern.compile("fromNamespaceAndPath\\s*\\([^,]+,\\s*\"([^\"]+)\"");

    /** The game's own rule, from {@code Identifier.assertValidPath}. */
    private static final Pattern VALID_PATH = Pattern.compile("[a-z0-9/._-]+");

    @Test
    void everyIdentifierLiteralIsWellFormedAndPresent() throws IOException {
        Path source = TestPaths.projectDir().resolve("src/main/java");
        Path resources = TestPaths.projectDir().resolve("src/main/resources/assets/tc");
        List<String> problems = new ArrayList<>();
        int checked = 0;

        try (Stream<Path> files = Files.walk(source)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".java")).toList()) {
                String text = Files.readString(file, StandardCharsets.UTF_8);
                Matcher matcher = IDENTIFIER.matcher(text);
                while (matcher.find()) {
                    String path = matcher.group(1);
                    checked++;
                    if (!VALID_PATH.matcher(path).matches()) {
                        problems.add(
                                file.getFileName()
                                        + ": \""
                                        + path
                                        + "\" has a character the game rejects; identifier paths"
                                        + " are [a-z0-9/._-] only, so the asset has to be renamed");
                    } else if (looksLikeAFile(path)
                            && !Files.isRegularFile(resources.resolve(path))) {
                        problems.add(
                                file.getFileName()
                                        + ": \""
                                        + path
                                        + "\" names no file under assets/tc");
                    }
                }
            }
        }

        assertTrue(problems.isEmpty(), String.join("\n", problems));
        assertTrue(
                checked > 10,
                "found only "
                        + checked
                        + " identifier literals; the pattern has probably stopped matching");
    }

    /**
     * Only paths with an extension are looked up. An identifier is also how registry names, render
     * pipelines and reload listeners are spelt, and those name no file.
     */
    private static boolean looksLikeAFile(String path) {
        int dot = path.lastIndexOf('.');
        return dot > 0 && dot < path.length() - 1 && path.indexOf('/') >= 0;
    }
}
