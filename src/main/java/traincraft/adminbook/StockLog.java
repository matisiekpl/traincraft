package traincraft.adminbook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import traincraft.Traincraft;
import traincraft.vehicle.entity.RollingStockEntity;

/**
 * Community Edition's {@code ServerLogger}: every piece of stock writes itself to
 * {@code config/traincraft/<owner>/<item>_<uuid>.txt} so the admin book can hand back what a
 * crash lost.
 */
public final class StockLog {

    private StockLog() {}

    public static Path directory() {
        return FMLPaths.CONFIGDIR.get().resolve("traincraft");
    }

    private static Path file(RollingStockEntity stock) {
        String owner = stock.getOwner().isBlank() ? "unknown_player" : stock.getOwner().toLowerCase();
        String item = BuiltInRegistries.ITEM.getKey(stock.getPickResult().getItem()).toString().replace(":", "~");
        return directory().resolve(owner).resolve(item + "_" + stock.getUUID().toString().toLowerCase() + ".txt");
    }

    public static void write(RollingStockEntity stock) {
        StringBuilder document = new StringBuilder();
        document.append("<xmlRoot>\n   <uuid>").append(stock.getUUID());
        document.append("</uuid>\n   <delegate>").append(BuiltInRegistries.ITEM.getKey(stock.getPickResult().getItem()));
        document.append("</delegate>\n   <pos_x>").append(stock.getX());
        document.append("</pos_x>\n   <pos_y>").append(stock.getY());
        document.append("</pos_y>\n   <pos_z>").append(stock.getZ());
        document.append("</pos_z>\n    <inventory>\n");
        if (stock instanceof Container container) {
            for (int slot = 0; slot < container.getContainerSize(); slot++) {
                appendItem(document, container.getItem(slot));
            }
        }
        ResourceHandler<FluidResource> tank = stock.tank();
        if (tank != null) {
            for (int index = 0; index < tank.size(); index++) {
                for (int fill = 1000; fill < tank.getAmountAsInt(index); fill += 1000) {
                    appendItem(document, new ItemStack(tank.getResource(index).getFluid().getBucket()));
                }
            }
        }
        document.append("   </inventory>\n</xmlRoot>");
        try {
            Path file = file(stock);
            Files.createDirectories(file.getParent());
            Files.writeString(file, document.toString(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            Traincraft.LOGGER.warn("Cannot log {}", stock.getUUID(), exception);
        }
    }

    private static void appendItem(StringBuilder document, ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }
        document.append("        <ItemStack>\n            <delegate>").append(BuiltInRegistries.ITEM.getKey(stack.getItem()));
        document.append("</delegate>\n            <StackSize>").append(stack.getCount());
        document.append("</StackSize>\n        </ItemStack>\n");
    }

    public static void delete(RollingStockEntity stock) {
        try {
            Files.deleteIfExists(file(stock));
        } catch (IOException exception) {
            Traincraft.LOGGER.warn("Cannot delete the log of {}", stock.getUUID(), exception);
        }
    }

    public static List<ItemStack> items(String document) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.parse(between(document, "<delegate>", "</delegate>")))));
        stacks.addAll(stacks(document));
        return stacks;
    }

    public static List<ItemStack> stacks(String document) {
        List<ItemStack> stacks = new ArrayList<>();
        while (document.contains("<ItemStack>")) {
            String entry = between(document, "<ItemStack>", "</ItemStack>");
            stacks.add(new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.parse(between(entry, "<delegate>", "</delegate>"))),
                    Integer.parseInt(between(entry, "<StackSize>", "</StackSize>"))));
            document = document.substring(document.indexOf("</ItemStack>") + "</ItemStack>".length());
        }
        return stacks;
    }

    public static String between(String document, String open, String close) {
        int start = document.indexOf(open) + open.length();
        return document.substring(start, document.indexOf(close, start));
    }
}
