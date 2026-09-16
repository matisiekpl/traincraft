package traincraft.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

final class PieceSheet {

    private static final int CELL = 220;
    private static final int PADDING = 8;
    private static final int LABEL = 16;

    /** Grow each crop slightly so the piece is not cut flush against its own edge. */
    private static final int MARGIN = 4;

    private PieceSheet() {}

    static BufferedImage build(
            BufferedImage reference,
            List<Piece> referencePieces,
            BufferedImage current,
            List<Piece> currentPieces) {
        int columns = Math.max(referencePieces.size(), currentPieces.size());
        if (columns == 0) {
            return null;
        }

        int width = PADDING + columns * (CELL + PADDING);
        int height = PADDING + 2 * (CELL + LABEL + PADDING);
        BufferedImage sheet = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = sheet.createGraphics();
        try {
            // Nearest neighbour on purpose: these are pixel-art textures magnified several times,
            // and smoothing them would blur away the very detail being judged.
            g.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setColor(new Color(0x181818));
            g.fillRect(0, 0, width, height);

            drawRow(g, reference, referencePieces, 0, "baseline");
            drawRow(g, current, currentPieces, 1, "current");
        } finally {
            g.dispose();
        }
        return sheet;
    }

    private static void drawRow(
            Graphics2D g, BufferedImage source, List<Piece> pieces, int row, String label) {
        int y = PADDING + row * (CELL + LABEL + PADDING);
        g.setColor(Color.WHITE);
        g.drawString(label, PADDING, y + 12);
        int top = y + LABEL;

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            int x = PADDING + i * (CELL + PADDING);

            int cropX = Math.max(0, piece.minX() - MARGIN);
            int cropY = Math.max(0, piece.minY() - MARGIN);
            int cropW = Math.min(source.getWidth() - cropX, piece.width() + 2 * MARGIN);
            int cropH = Math.min(source.getHeight() - cropY, piece.height() + 2 * MARGIN);
            if (cropW <= 0 || cropH <= 0) {
                continue;
            }
            BufferedImage crop = source.getSubimage(cropX, cropY, cropW, cropH);

            // Letterbox rather than stretch: distorting the aspect ratio would destroy the one
            // property being compared.
            double scale = Math.min(CELL / (double) cropW, CELL / (double) cropH);
            int drawW = (int) Math.round(cropW * scale);
            int drawH = (int) Math.round(cropH * scale);
            int drawX = x + (CELL - drawW) / 2;
            int drawY = top + (CELL - drawH) / 2;

            g.setColor(new Color(0x303030));
            g.fillRect(x, top, CELL, CELL);
            g.drawImage(crop, drawX, drawY, drawW, drawH, null);
            g.setColor(new Color(0x606060));
            g.drawRect(x, top, CELL, CELL);
            g.setColor(Color.WHITE);
            g.drawString("#" + i + " " + piece.structureAxis(), x + 4, top + CELL - 5);
        }
    }
}
