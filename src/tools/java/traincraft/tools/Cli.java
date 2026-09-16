package traincraft.tools;

import java.nio.file.Path;
import java.util.Arrays;

public final class Cli {

    private Cli() {}

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage();
            System.exit(2);
        }
        String command = args[0];
        String[] rest = Arrays.copyOfRange(args, 1, args.length);
        int exit =
                switch (command) {
                    case "capture" ->
                            CaptureRunner.run(
                                    Path.of(need(rest, 0, "scenario")),
                                    Path.of(need(rest, 1, "outDir")));
                    case "compare" ->
                            Compare.run(
                                    Path.of(need(rest, 0, "currentDir")),
                                    Path.of(need(rest, 1, "referenceDir")),
                                    Path.of(need(rest, 2, "outDir")));
                    case "sheet" -> ContactSheet.run(Path.of(need(rest, 0, "compareDir")));
                    case "structure" ->
                            MakeStructure.run(
                                    Integer.parseInt(need(rest, 0, "sizeX")),
                                    Integer.parseInt(need(rest, 1, "sizeY")),
                                    Integer.parseInt(need(rest, 2, "sizeZ")),
                                    Repo.root()
                                            .resolve(
                                                    "mod/src/development/resources/data/tc/structure/empty.nbt"));
                    case "objstat" -> ObjStat.run();
                    case "trackitems" -> {
                        GenerateTrackItems.run();
                        yield 0;
                    }
                    default -> {
                        System.err.println("Unknown command: " + command);
                        usage();
                        yield 2;
                    }
                };
        System.exit(exit);
    }

    private static String need(String[] args, int index, String name) {
        if (index >= args.length) {
            throw new IllegalArgumentException("missing argument <" + name + ">");
        }
        return args[index];
    }

    private static void usage() {
        System.err.println(
                """
                usage:
                  capture <scenario.json> <outDir>          run one scenario through the 26.2 harness
                  compare <currentDir> <referenceDir> <out> compare two capture runs

                  sheet   <compareDir>                      build contact sheets from a comparison
                  objstat                                   regenerate the OBJ manifest

                  trackitems                                regenerate the track items, models and lang entries
                """);
    }
}
