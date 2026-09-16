package traincraft.tools;

import java.awt.image.BufferedImage;

public final class Mask {

    /** Per-channel difference above which a pixel counts as "changed". */
    private static final int THRESHOLD = 10;

    private final boolean[] set;
    private final int width;
    private final int height;
    private int count;
    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;
    private long sumX;
    private long sumY;

    private Mask(int width, int height) {
        this.width = width;
        this.height = height;
        this.set = new boolean[width * height];
    }

    public static Mask difference(BufferedImage subject, BufferedImage empty) {
        int w = Math.min(subject.getWidth(), empty.getWidth());
        int h = Math.min(subject.getHeight(), empty.getHeight());
        Mask mask = new Mask(w, h);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int a = subject.getRGB(x, y);
                int b = empty.getRGB(x, y);
                int dr = Math.abs(((a >> 16) & 0xFF) - ((b >> 16) & 0xFF));
                int dg = Math.abs(((a >> 8) & 0xFF) - ((b >> 8) & 0xFF));
                int db = Math.abs((a & 0xFF) - (b & 0xFF));
                if (dr > THRESHOLD || dg > THRESHOLD || db > THRESHOLD) {
                    mask.mark(x, y);
                }
            }
        }
        return mask;
    }

    private void mark(int x, int y) {
        set[y * width + x] = true;
        count++;
        sumX += x;
        sumY += y;
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
    }

    public boolean get(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height && set[y * width + x];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int[] boundingBox() {
        return isEmpty() ? new int[] {0, 0, 0, 0} : new int[] {minX, minY, maxX, maxY};
    }

    public double[] centroid() {
        return isEmpty()
                ? new double[] {0, 0}
                : new double[] {(double) sumX / count, (double) sumY / count};
    }

    public java.util.List<Piece> pieces(java.awt.image.BufferedImage image, int minPixels) {
        int[] label = new int[width * height];
        java.util.List<Piece> found = new java.util.ArrayList<>();
        int[] stack = new int[width * height];

        for (int start = 0; start < set.length; start++) {
            if (!set[start] || label[start] != 0) {
                continue;
            }
            int top = 0;
            stack[top++] = start;
            label[start] = 1;

            int pixels = 0;
            int bMinX = Integer.MAX_VALUE;
            int bMinY = Integer.MAX_VALUE;
            int bMaxX = Integer.MIN_VALUE;
            int bMaxY = Integer.MIN_VALUE;
            long sx = 0;
            long sy = 0;

            while (top > 0) {
                int index = stack[--top];
                int x = index % width;
                int y = index / width;
                pixels++;
                sx += x;
                sy += y;
                bMinX = Math.min(bMinX, x);
                bMinY = Math.min(bMinY, y);
                bMaxX = Math.max(bMaxX, x);
                bMaxY = Math.max(bMaxY, y);

                // 8-connected: a diagonal gap between two rails is still one piece.
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx < 0 || ny < 0 || nx >= width || ny >= height) {
                            continue;
                        }
                        int n = ny * width + nx;
                        if (set[n] && label[n] == 0) {
                            label[n] = 1;
                            stack[top++] = n;
                        }
                    }
                }
            }

            if (pixels >= minPixels) {
                found.add(
                        Piece.measure(
                                image,
                                this,
                                bMinX,
                                bMinY,
                                bMaxX,
                                bMaxY,
                                pixels,
                                (double) sx / pixels,
                                (double) sy / pixels));
            }
        }
        found.sort((a, b) -> Double.compare(a.centroidX(), b.centroidX()));
        return found;
    }

    /**
     * Intersection over union against another mask. 1.0 means the two silhouettes coincide exactly;
     * 0.0 means they do not overlap at all.
     */
    public static double iou(Mask a, Mask b) {
        int w = Math.min(a.width, b.width);
        int h = Math.min(a.height, b.height);
        long intersection = 0;
        long union = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                boolean pa = a.get(x, y);
                boolean pb = b.get(x, y);
                if (pa && pb) {
                    intersection++;
                }
                if (pa || pb) {
                    union++;
                }
            }
        }
        return union == 0 ? 1.0 : (double) intersection / union;
    }
}
