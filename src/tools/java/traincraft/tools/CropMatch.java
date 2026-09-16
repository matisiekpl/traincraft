package traincraft.tools;

import java.awt.image.BufferedImage;

final class CropMatch {

    /** Patch size the comparison is done at. Small enough that resampling noise cancels out. */
    private static final int SIZE = 48;

    record Result(int quarterTurns, double[] correlations) {
        double best() {
            return correlations[quarterTurns];
        }

        /** How clearly the winner beat the runner-up; a small margin means "cannot tell". */
        double margin() {
            double best = Double.NEGATIVE_INFINITY;
            double second = Double.NEGATIVE_INFINITY;
            for (double c : correlations) {
                if (c > best) {
                    second = best;
                    best = c;
                } else if (c > second) {
                    second = c;
                }
            }
            return best - second;
        }

        String describe() {
            String turn =
                    switch (quarterTurns) {
                        case 0 -> "same orientation";
                        case 1 -> "current is a quarter turn clockwise";
                        case 2 -> "current is upside down (half turn)";
                        default -> "current is a quarter turn anticlockwise";
                    };
            String confidence =
                    margin() < 0.05
                            ? "  (LOW CONFIDENCE, margin "
                                    + Math.round(margin() * 1000) / 1000.0
                                    + ")"
                            : "";
            return turn + " r=" + Math.round(best() * 1000) / 1000.0 + confidence;
        }
    }

    private CropMatch() {}

    static Result compare(
            BufferedImage reference,
            Piece referencePiece,
            BufferedImage current,
            Piece currentPiece) {
        double[] ref = normalise(patch(reference, referencePiece));
        double[] prt = normalise(patch(current, currentPiece));

        double[] correlations = new double[4];
        for (int turns = 0; turns < 4; turns++) {
            correlations[turns] = correlate(rotate(ref, turns), prt);
        }
        int best = 0;
        for (int i = 1; i < 4; i++) {
            if (correlations[i] > correlations[best]) {
                best = i;
            }
        }
        return new Result(best, correlations);
    }

    /** Cuts the piece out and resamples it to a square patch of grayscale values. */
    private static double[] patch(BufferedImage image, Piece piece) {
        double[] out = new double[SIZE * SIZE];
        int w = piece.width();
        int h = piece.height();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int sx = piece.minX() + (int) ((x + 0.5) * w / SIZE);
                int sy = piece.minY() + (int) ((y + 0.5) * h / SIZE);
                sx = Math.min(Math.max(sx, 0), image.getWidth() - 1);
                sy = Math.min(Math.max(sy, 0), image.getHeight() - 1);
                int p = image.getRGB(sx, sy);
                out[y * SIZE + x] =
                        0.2126 * ((p >> 16) & 0xFF)
                                + 0.7152 * ((p >> 8) & 0xFF)
                                + 0.0722 * (p & 0xFF);
            }
        }
        return out;
    }

    /** Zero mean, unit variance: removes the two renderers' brightness and contrast difference. */
    private static double[] normalise(double[] patch) {
        double mean = 0;
        for (double v : patch) {
            mean += v;
        }
        mean /= patch.length;
        double variance = 0;
        for (double v : patch) {
            variance += (v - mean) * (v - mean);
        }
        variance = Math.sqrt(variance / patch.length);
        if (variance < 1.0e-6) {
            variance = 1;
        }
        double[] out = new double[patch.length];
        for (int i = 0; i < patch.length; i++) {
            out[i] = (patch[i] - mean) / variance;
        }
        return out;
    }

    private static double[] rotate(double[] patch, int quarterTurns) {
        if (quarterTurns % 4 == 0) {
            return patch;
        }
        double[] out = new double[patch.length];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int nx;
                int ny;
                switch (quarterTurns % 4) {
                    case 1 -> {
                        nx = SIZE - 1 - y;
                        ny = x;
                    }
                    case 2 -> {
                        nx = SIZE - 1 - x;
                        ny = SIZE - 1 - y;
                    }
                    default -> {
                        nx = y;
                        ny = SIZE - 1 - x;
                    }
                }
                out[ny * SIZE + nx] = patch[y * SIZE + x];
            }
        }
        return out;
    }

    private static double correlate(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum / a.length;
    }
}
