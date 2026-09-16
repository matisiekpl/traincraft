package traincraft.tools;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * One visible subject in a capture — a single track piece, a locomotive — together with the
 * properties that survive a change of renderer.
 *
 * <p>These are the facts the comparison is actually about. Two Minecraft versions will never agree
 * pixel for pixel, but they can be checked to agree that a piece is square, that its internal
 * structure runs along the image's X axis, and that it sits third from the left with even spacing.
 * See {@code docs/decisions/ADR-003-verification-is-semantic.md}.
 */
public record Piece(
        int minX,
        int minY,
        int maxX,
        int maxY,
        double centroidX,
        double centroidY,
        int pixels,
        double aspect,
        String structureAxis,
        double structureRatio) {

    public int width() {
        return maxX - minX + 1;
    }

    public int height() {
        return maxY - minY + 1;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("bbox", java.util.List.of(minX, minY, maxX, maxY));
        m.put("size", java.util.List.of(width(), height()));
        m.put("centroid", java.util.List.of(round(centroidX), round(centroidY)));
        m.put("pixels", pixels);
        m.put("aspect", round(aspect));
        m.put("structureAxis", structureAxis);
        m.put("structureRatio", round(structureRatio));
        return m;
    }

    private static double round(double v) {
        return Math.round(v * 1000.0) / 1000.0;
    }

    /**
     * Determines which way the subject's internal detail runs.
     *
     * <p>A track piece seen from above is roughly square whichever way it faces, so its outline
     * says nothing about orientation. Its <em>structure</em> does: sleepers and rails produce
     * strong banding across one axis and near-uniformity along the other. Measuring the variance of
     * column means against the variance of row means inside the piece captures exactly that, and it
     * is invariant to scale, position and overall brightness — so it compares meaningfully across
     * two renderers that agree on nothing else.
     *
     * @return {@code "x"} when detail varies along the image X axis (vertical banding), {@code "y"}
     *     for horizontal banding, {@code "none"} when neither dominates
     */
    public static Piece measure(
            BufferedImage image,
            Mask mask,
            int minX,
            int minY,
            int maxX,
            int maxY,
            int pixels,
            double centroidX,
            double centroidY) {
        int w = maxX - minX + 1;
        int h = maxY - minY + 1;

        double[] columnMean = new double[w];
        int[] columnCount = new int[w];
        double[] rowMean = new double[h];
        int[] rowCount = new int[h];

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (!mask.get(x, y)) {
                    continue;
                }
                int p = image.getRGB(x, y);
                double luma =
                        0.2126 * ((p >> 16) & 0xFF)
                                + 0.7152 * ((p >> 8) & 0xFF)
                                + 0.0722 * (p & 0xFF);
                columnMean[x - minX] += luma;
                columnCount[x - minX]++;
                rowMean[y - minY] += luma;
                rowCount[y - minY]++;
            }
        }

        double columnVariance = variance(columnMean, columnCount);
        double rowVariance = variance(rowMean, rowCount);

        String axis;
        double ratio;
        if (columnVariance <= 1.0e-6 && rowVariance <= 1.0e-6) {
            axis = "none";
            ratio = 1.0;
        } else if (columnVariance >= rowVariance) {
            axis = "x";
            ratio = rowVariance <= 1.0e-6 ? Double.POSITIVE_INFINITY : columnVariance / rowVariance;
        } else {
            axis = "y";
            ratio =
                    columnVariance <= 1.0e-6
                            ? Double.POSITIVE_INFINITY
                            : rowVariance / columnVariance;
        }
        // Banding has to actually dominate before it means anything; a near-tie is "none".
        if (Double.isFinite(ratio) && ratio < 1.25) {
            axis = "none";
        }

        return new Piece(
                minX,
                minY,
                maxX,
                maxY,
                centroidX,
                centroidY,
                pixels,
                (double) w / h,
                axis,
                Double.isFinite(ratio) ? ratio : 999.0);
    }

    private static double variance(double[] sums, int[] counts) {
        int n = 0;
        double total = 0;
        for (int i = 0; i < sums.length; i++) {
            if (counts[i] > 0) {
                sums[i] /= counts[i];
                total += sums[i];
                n++;
            }
        }
        if (n < 2) {
            return 0;
        }
        double mean = total / n;
        double sum = 0;
        for (int i = 0; i < sums.length; i++) {
            if (counts[i] > 0) {
                double d = sums[i] - mean;
                sum += d * d;
            }
        }
        return sum / n;
    }
}
