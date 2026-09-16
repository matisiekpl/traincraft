package traincraft.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

final class SideBySide {

    private static final int GAP = 4;

    private SideBySide() {}

    static BufferedImage build(BufferedImage left, BufferedImage right) {
        int width = left.getWidth() + GAP + right.getWidth();
        int height = Math.max(left.getHeight(), right.getHeight());
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = out.createGraphics();
        try {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, width, height);
            g.drawImage(left, 0, 0, null);
            g.drawImage(right, left.getWidth() + GAP, 0, null);
        } finally {
            g.dispose();
        }
        return out;
    }
}
