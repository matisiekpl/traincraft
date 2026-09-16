package traincraft.tools;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public final class Compare {

    /** A shot named {@code foo} is masked against the shot named {@code foo__empty}. */
    public static final String EMPTY_SUFFIX = "__empty";

    /** Blobs smaller than this are anti-aliasing and shadow fringe, not subjects. */
    private static final int MIN_PIECE_PIXELS = 60;

    private Compare() {}

    public static int run(Path currentDir, Path referenceDir, Path outDir) throws IOException {
        Path currentShots = currentDir.resolve("screenshots");
        Path referenceShots = referenceDir.resolve("screenshots");
        if (!Files.isDirectory(currentShots)) {
            System.err.println("compare: no screenshots in " + currentDir);
            return 2;
        }
        if (!Files.isDirectory(referenceShots)) {
            System.err.println("compare: no screenshots in " + referenceDir);
            return 2;
        }
        Files.createDirectories(outDir);

        List<Map<String, Object>> rows = new ArrayList<>();
        boolean produced = true;

        for (String shot : subjectShots(currentShots)) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("shot", shot);

            Path curSubject = currentShots.resolve(shot + ".png");
            Path curEmpty = currentShots.resolve(shot + EMPTY_SUFFIX + ".png");
            Path refSubject = referenceShots.resolve(shot + ".png");
            Path refEmpty = referenceShots.resolve(shot + EMPTY_SUFFIX + ".png");

            if (!Files.exists(refSubject)) {
                row.put("verdict", "cannot-tell");
                row.put("reason", "no matching reference capture");
                rows.add(row);
                produced = false;
                continue;
            }
            if (!Files.exists(curEmpty) || !Files.exists(refEmpty)) {
                // Not a failure, and treating it as one made four scenarios permanently red. A
                // silhouette needs the subject removed from an otherwise identical frame, and the
                // player journeys and the camera probe have no such step by design -- they are read
                // as pictures and as the gauge lines they print, not as masks. So: no metrics, but
                // the side-by-side panel is still written, because that panel is the whole point of
                // those scenarios and the contact sheet is built from it.
                row.put("verdict", "unmasked");
                row.put(
                        "reason",
                        "no <name>"
                                + EMPTY_SUFFIX
                                + " step, so there is no silhouette to"
                                + " measure; the side-by-side panel is the comparison");
                ImageIO.write(
                        SideBySide.build(
                                ImageIO.read(refSubject.toFile()),
                                ImageIO.read(curSubject.toFile())),
                        "png",
                        outDir.resolve(shot + ".sbs.png").toFile());
                rows.add(row);
                continue;
            }

            BufferedImage cur = ImageIO.read(curSubject.toFile());
            BufferedImage ref = ImageIO.read(refSubject.toFile());
            Mask curMask = Mask.difference(cur, ImageIO.read(curEmpty.toFile()));
            Mask refMask = Mask.difference(ref, ImageIO.read(refEmpty.toFile()));

            List<Piece> curPieces = curMask.pieces(cur, MIN_PIECE_PIXELS);
            List<Piece> refPieces = refMask.pieces(ref, MIN_PIECE_PIXELS);

            row.put("semantics", semantics(ref, refPieces, cur, curPieces));
            row.put("reference", pieceMaps(refPieces));
            row.put("current", pieceMaps(curPieces));
            row.put("advisory", advisory(cur, ref, curMask, refMask));

            ImageIO.write(
                    MaskRender.overlay(cur, curMask, refMask),
                    "png",
                    outDir.resolve(shot + ".mask.png").toFile());
            ImageIO.write(
                    SideBySide.build(ref, cur), "png", outDir.resolve(shot + ".sbs.png").toFile());
            BufferedImage crops = PieceSheet.build(ref, refPieces, cur, curPieces);
            if (crops != null) {
                ImageIO.write(crops, "png", outDir.resolve(shot + ".pieces.png").toFile());
            }
            rows.add(row);
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("current", currentDir.toString());
        root.put("reference", referenceDir.toString());
        root.put(
                "note",
                "Metrics are advisory. The verdict is a judgement made by reading the "
                        + "semantics block and looking at the .pieces.png crops. See ADR-003.");
        root.put("shots", rows);
        root.put("artefactsProduced", produced);
        Files.writeString(outDir.resolve("metrics.json"), Json.write(root), StandardCharsets.UTF_8);

        System.out.println(
                "compare: " + rows.size() + " shots -> " + outDir.resolve("metrics.json"));
        for (Map<String, Object> row : rows) {
            System.out.println("  " + row.get("shot"));
            Object sem = row.get("semantics");
            if (sem instanceof Map<?, ?> m) {
                for (Map.Entry<?, ?> e : m.entrySet()) {
                    System.out.println("      " + e.getKey() + ": " + e.getValue());
                }
            } else {
                System.out.println("      " + row.get("verdict") + " -- " + row.get("reason"));
            }
        }
        return produced ? 0 : 2;
    }

    private static Map<String, Object> semantics(
            BufferedImage referenceImage,
            List<Piece> reference,
            BufferedImage currentImage,
            List<Piece> current) {
        Map<String, Object> s = new LinkedHashMap<>();
        s.put(
                "pieceCount",
                reference.size()
                        + " reference vs "
                        + current.size()
                        + " current"
                        + (reference.size() == current.size() ? "  AGREE" : "  DIFFER"));

        if (reference.size() != current.size() || reference.isEmpty()) {
            s.put("note", "piece counts differ, so per-piece comparison is not meaningful");
            return s;
        }

        List<String> orientation = new ArrayList<>();
        for (int i = 0; i < reference.size(); i++) {
            CropMatch.Result match =
                    CropMatch.compare(
                            referenceImage, reference.get(i), currentImage, current.get(i));
            orientation.add(
                    "#"
                            + i
                            + " "
                            + match.describe()
                            + (match.quarterTurns() == 0 ? "  AGREE" : "  DIFFER"));
        }
        s.put("orientation", orientation);
        s.put("spacing", spacing(reference, current));
        return s;
    }

    private static String spacing(List<Piece> reference, List<Piece> current) {
        if (reference.size() < 3) {
            return "fewer than three pieces; spacing carries no information";
        }
        List<Double> refGaps = gaps(reference);
        List<Double> currentGaps = gaps(current);
        // Normalise each rig's gaps by its own first gap: this compares the *pattern* of spacing
        // rather than its size, so a difference in zoom cannot register as a layout difference.
        double refBase = refGaps.get(0);
        double portBase = currentGaps.get(0);
        StringBuilder sb = new StringBuilder();
        boolean agree = true;
        for (int i = 0; i < refGaps.size(); i++) {
            double r = refGaps.get(i) / refBase;
            double p = currentGaps.get(i) / portBase;
            boolean same = Math.abs(r - p) < 0.08;
            agree &= same;
            sb.append(String.format("gap%d %.3f vs %.3f%s  ", i, r, p, same ? "" : " DIFFER"));
        }
        return sb.toString().trim() + (agree ? "  AGREE" : "");
    }

    private static List<Double> gaps(List<Piece> pieces) {
        List<Double> gaps = new ArrayList<>();
        for (int i = 1; i < pieces.size(); i++) {
            gaps.add(pieces.get(i).centroidX() - pieces.get(i - 1).centroidX());
        }
        return gaps;
    }

    private static List<Map<String, Object>> pieceMaps(List<Piece> pieces) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Piece piece : pieces) {
            out.add(piece.toMap());
        }
        return out;
    }

    private static Map<String, Object> advisory(
            BufferedImage cur, BufferedImage ref, Mask curMask, Mask refMask) {
        Map<String, Object> a = new LinkedHashMap<>();
        a.put("silhouetteIoU", round(Mask.iou(curMask, refMask)));
        a.put("maskPixels", List.of(refMask.count(), curMask.count()));
        int[] rb = refMask.boundingBox();
        int[] cb = curMask.boundingBox();
        a.put("bboxDelta", List.of(cb[0] - rb[0], cb[1] - rb[1], cb[2] - rb[2], cb[3] - rb[3]));
        double[] rc = refMask.centroid();
        double[] cc = curMask.centroid();
        a.put("centroidDelta", List.of(round(cc[0] - rc[0]), round(cc[1] - rc[1])));
        return a;
    }

    private static Iterable<String> subjectShots(Path dir) throws IOException {
        TreeSet<String> names = new TreeSet<>();
        try (var files = Files.list(dir)) {
            files.map(p -> p.getFileName().toString())
                    .filter(n -> n.endsWith(".png"))
                    .map(n -> n.substring(0, n.length() - 4))
                    .filter(n -> !n.endsWith(EMPTY_SUFFIX))
                    .forEach(names::add);
        }
        return names;
    }

    private static double round(double v) {
        return Math.round(v * 1000.0) / 1000.0;
    }
}
