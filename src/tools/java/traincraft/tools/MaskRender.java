package traincraft.tools;

import java.awt.image.BufferedImage;

final class MaskRender {

    private MaskRender() {}

    static BufferedImage overlay(BufferedImage base, Mask current, Mask reference) {
        int w = base.getWidth();
        int h = base.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                boolean inCurrent = current.get(x, y);
                boolean inReference = reference.get(x, y);
                int rgb;
                if (inCurrent && inReference) {
                    rgb = 0x808080;
                } else if (inCurrent) {
                    rgb = 0xFF00FF;
                } else if (inReference) {
                    rgb = 0x00FF00;
                } else {
                    // Darkened source, so the overlay reads clearly without losing the scene.
                    int p = base.getRGB(x, y);
                    rgb =
                            (((p >> 16) & 0xFF) / 4) << 16
                                    | (((p >> 8) & 0xFF) / 4) << 8
                                    | ((p & 0xFF) / 4);
                }
                out.setRGB(x, y, rgb);
            }
        }
        return out;
    }
}
