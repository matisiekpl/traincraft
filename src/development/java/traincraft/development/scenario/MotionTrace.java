package traincraft.development.scenario;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import traincraft.Traincraft;
import traincraft.vehicle.entity.LocomotiveEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * One row per rendered frame: where the client thinks the locomotive is, and how far it moved.
 *
 * <p>A screenshot cannot answer a question about motion. Every shot the harness takes is at the end
 * of a settle, so they are all at the same phase of a tick and a run that stutters between ticks
 * looks perfectly even across them. Reports of a train that "jumps" therefore cannot be confirmed
 * or dismissed from the contact sheet, which is why this exists: it samples the same position the
 * renderer uses, every frame, and writes it out as numbers.
 *
 * <p>What the columns are for:
 *
 * <ul>
 *   <li>{@code ms} -- wall clock since the first row. The gap between rows is the frame time, and
 *       an animation driven by elapsed time rather than by ticks depends on it.
 *   <li>{@code tick} and {@code partial} -- which tick the frame fell in and how far through it.
 *       Two frames in the same tick with the same partial mean the client is not interpolating.
 *   <li>{@code x,y,z} -- the interpolated position, which is what is drawn.
 *   <li>{@code stepZ} -- how far the drawn position moved since the previous frame. Even motion
 *       gives an even column; a client that waits for a position packet and then catches up gives a
 *       run of zeros followed by a spike, and that is what "jumping" looks like in numbers.
 *   <li>{@code moveX,moveZ} -- {@code xo - x}, the per-tick delta the wheel animation is driven by.
 *       It is piecewise constant within a tick.
 * </ul>
 *
 * <p>Written to {@code motion.csv} beside the screenshots.
 */
public final class MotionTrace {

    /** Enough for a couple of minutes at a high frame rate; a run that long is a different tool. */
    private static final int MAX_ROWS = 100_000;

    private final List<String> rows = new ArrayList<>();
    private long firstNanos;
    private double lastX = Double.NaN;
    private double lastZ = Double.NaN;

    public void record(Minecraft mc) {
        if (mc.level == null || rows.size() >= MAX_ROWS) {
            return;
        }
        Entity subject = null;
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity instanceof LocomotiveEntity) {
                subject = entity;
                break;
            }
        }
        if (subject == null) {
            return;
        }
        long now = System.nanoTime();
        if (rows.isEmpty()) {
            firstNanos = now;
        }
        // The renderer's own partial tick, not the tick counter's: this has to be the same number
        // the entity's position is interpolated with, or the trace describes a different frame
        // from the one that was drawn.
        float partial = mc.getDeltaTracker().getGameTimeDeltaPartialTick(true);
        // getPosition(partial), not getX(partial): Entity.getX(double) takes a fraction of the
        // bounding box width and returns a point on its side, which has nothing to do with time.
        // Reading it as an interpolated position gave a column that swept a block and a half every
        // tick and hid the real fault behind a much louder imaginary one.
        Vec3 drawn = subject.getPosition(partial);
        double x = drawn.x;
        double y = drawn.y;
        double z = drawn.z;
        double stepX = Double.isNaN(lastX) ? 0.0 : x - lastX;
        double stepZ = Double.isNaN(lastZ) ? 0.0 : z - lastZ;
        lastX = x;
        lastZ = z;
        rows.add(
                String.format(
                        java.util.Locale.ROOT,
                        "%.3f,%d,%.4f,%.5f,%.5f,%.5f,%.5f,%.5f,%.5f,%.5f,%.2f",
                        (now - firstNanos) / 1.0e6,
                        subject.tickCount,
                        partial,
                        x,
                        y,
                        z,
                        stepX,
                        stepZ,
                        subject.xo - subject.getX(),
                        subject.zo - subject.getZ(),
                        subject.getYRot()));
    }

    /** Writes the trace, or nothing at all if no frame ever saw a locomotive. */
    public void write(Path outDir) {
        if (rows.isEmpty()) {
            return;
        }
        StringBuilder text =
                new StringBuilder("ms,tick,partial,x,y,z,stepX,stepZ,moveX,moveZ,yaw\n");
        for (String row : rows) {
            text.append(row).append('\n');
        }
        try {
            Files.createDirectories(outDir);
            Files.writeString(
                    outDir.resolve("motion.csv"), text.toString(), StandardCharsets.UTF_8);
            Traincraft.LOGGER.info(
                    "motion trace: {} frames -> {}", rows.size(), outDir.resolve("motion.csv"));
        } catch (IOException e) {
            Traincraft.LOGGER.warn("could not write the motion trace", e);
        }
    }
}
