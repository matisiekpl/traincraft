package traincraft.client.screen;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.network.CouplingActionPayload;
import traincraft.vehicle.coupling.VehicleEnd;
import traincraft.vehicle.entity.RollingStockEntity;

import java.util.function.Consumer;

/**
 * A piece of stock's coupling controls, as every screen that shows them lays them out.
 *
 * <p>Never more than two buttons, because the toggle that arms the vehicle and the releases are
 * not all wanted at once: a vehicle with both ends coupled has nothing left to arm. Two rows is
 * what the panels have room for above their own strip of buttons.
 *
 * <p>Neither control names an end. Most freight is symmetric, so "front" and "rear" tell a player
 * nothing about which half of the wagon they are looking at; arming takes every free end at once,
 * and a release is named after the vehicle on the other side of it.
 */
public final class CouplingButtons {

    public static final int HEIGHT = 12;

    /** Rows are laid out upwards from the anchor, so the bottom one never moves. */
    private static final int ROW = HEIGHT + 2;

    /** Room the button's own frame takes, before the label has anywhere to go. */
    private static final int PADDING = 8;

    private CouplingButtons() {}

    /** Builds the controls with their bottom row at {@code y}, growing upwards. */
    public static void add(
            RollingStockEntity stock,
            Font font,
            ClientLevel level,
            int x,
            int y,
            int width,
            Consumer<AbstractWidget> sink) {
        int row = 0;
        if (stock.coupledId(VehicleEnd.FRONT) == -1 || stock.coupledId(VehicleEnd.BACK) == -1) {
            sink.accept(
                    Button.builder(
                                    Component.literal(
                                            stock.isArmed() ? "Coupling on" : "Coupling off"),
                                    button -> send(stock, CouplingActionPayload.ACTION_ARM))
                            .bounds(x, y, width, HEIGHT)
                            .build());
            row++;
        }
        for (VehicleEnd end : VehicleEnd.values()) {
            if (stock.coupledId(end) == -1) {
                continue;
            }
            int action =
                    end == VehicleEnd.FRONT
                            ? CouplingActionPayload.ACTION_RELEASE_FRONT
                            : CouplingActionPayload.ACTION_RELEASE_BACK;
            sink.accept(
                    Button.builder(
                                    releaseLabel(stock, end, level, font, width),
                                    button -> send(stock, action))
                            .bounds(x, y - row * ROW, width, HEIGHT)
                            .build());
            row++;
        }
    }

    /** What the buttons were built to say, so a change coming back from the server is noticed. */
    public static long state(RollingStockEntity stock) {
        return (stock.isArmed() ? 1L : 0L)
                | (long) (stock.coupledId(VehicleEnd.FRONT) + 1) << 1
                | (long) (stock.coupledId(VehicleEnd.BACK) + 1) << 32;
    }

    /**
     * What a release button says.
     *
     * <p>The neighbour's name on its own is enough until a wagon has the same thing coupled to
     * both ends, which a rake of identical freight does constantly; then the compass bearing the
     * end points along separates them, and opposite ends always bear opposite ways. A label that
     * will not fit falls back to the bearing alone rather than being cut in half.
     */
    private static Component releaseLabel(
            RollingStockEntity stock, VehicleEnd end, ClientLevel level, Font font, int width) {
        String name = neighbourName(stock, end, level);
        VehicleEnd other = end == VehicleEnd.FRONT ? VehicleEnd.BACK : VehicleEnd.FRONT;
        if (!name.isEmpty() && !name.equals(neighbourName(stock, other, level))) {
            String label = "Uncouple " + name;
            if (font.width(label) <= width - PADDING) {
                return Component.literal(label);
            }
        }
        if (name.isEmpty()) {
            return Component.literal("Uncouple");
        }
        return Component.literal("Uncouple (" + bearing(stock, end) + ")");
    }

    private static String neighbourName(
            RollingStockEntity stock, VehicleEnd end, ClientLevel level) {
        int id = stock.coupledId(end);
        if (id <= 0) {
            return "";
        }
        var neighbour = level.getEntity(id);
        return neighbour == null ? "" : neighbour.getDisplayName().getString();
    }

    /**
     * Which way the end points.
     *
     * <p>The model's front lies opposite the yaw -- that is the axis the body's parts are laid out
     * along -- so the front end bears the other way round from the heading.
     */
    private static String bearing(RollingStockEntity stock, VehicleEnd end) {
        return Direction.fromYRot(stock.getYRot() + (end == VehicleEnd.FRONT ? 180.0F : 0.0F))
                .getName();
    }

    private static void send(RollingStockEntity stock, int action) {
        ClientPacketDistributor.sendToServer(new CouplingActionPayload(stock.getId(), action));
    }
}
