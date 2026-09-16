package traincraft.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public final class ContactSheet {

    private static final int THUMB_WIDTH = 640;
    private static final int COLUMNS = 2;
    private static final int LABEL_HEIGHT = 18;

    private ContactSheet() {}

    public static int run(Path compareDir) throws IOException {
        List<Path> panels = new ArrayList<>();
        try (var files = Files.list(compareDir)) {
            files.filter(p -> p.getFileName().toString().endsWith(".sbs.png"))
                    .sorted()
                    .forEach(panels::add);
        }
        if (panels.isEmpty()) {
            System.err.println("sheet: no .sbs.png panels in " + compareDir);
            return 2;
        }

        List<BufferedImage> thumbs = new ArrayList<>();
        for (Path panel : panels) {
            thumbs.add(ImageIO.read(panel.toFile()));
        }
        int cellHeight = 0;
        for (BufferedImage thumb : thumbs) {
            cellHeight =
                    Math.max(
                            cellHeight,
                            (int)
                                    Math.round(
                                            thumb.getHeight()
                                                    * (THUMB_WIDTH / (double) thumb.getWidth())));
        }

        int rows = (thumbs.size() + COLUMNS - 1) / COLUMNS;
        BufferedImage sheet =
                new BufferedImage(
                        COLUMNS * THUMB_WIDTH,
                        rows * (cellHeight + LABEL_HEIGHT),
                        BufferedImage.TYPE_INT_RGB);
        Graphics2D g = sheet.createGraphics();
        try {
            g.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, sheet.getWidth(), sheet.getHeight());
            for (int i = 0; i < thumbs.size(); i++) {
                int x = (i % COLUMNS) * THUMB_WIDTH;
                int y = (i / COLUMNS) * (cellHeight + LABEL_HEIGHT);
                g.setColor(Color.WHITE);
                String label =
                        panels.get(i).getFileName().toString().replace(".sbs.png", "")
                                + "   left: baseline, right: current";
                g.drawString(label, x + 6, y + 13);
                g.drawImage(thumbs.get(i), x, y + LABEL_HEIGHT, THUMB_WIDTH, cellHeight, null);
            }
        } finally {
            g.dispose();
        }

        Path out = compareDir.resolve("contactsheet.png");
        ImageIO.write(sheet, "png", out.toFile());
        System.out.println("sheet: " + out + " (" + thumbs.size() + " panels)");
        return 0;
    }
}
