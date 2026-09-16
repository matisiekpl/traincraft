package traincraft.village;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import org.junit.jupiter.api.Test;

import traincraft.TestItems;
import traincraft.TestPaths;

/** The station chief's trades and the station template name only things the mod registers. */
class VillageDataTest {

    private static final Path DATA = TestPaths.mainResource("data/tc");

    private static List<Path> json(Path directory) throws IOException {
        try (Stream<Path> files = Files.walk(directory)) {
            return files.filter(path -> path.toString().endsWith(".json")).sorted().toList();
        }
    }

    private static void checkItem(JsonElement side, Set<String> items, List<String> problems, Path path) {
        JsonObject object = side.getAsJsonObject();
        String id = object.get("id").getAsString();
        if (id.startsWith("tc:") && !items.contains(id.substring(3))) {
            problems.add(path + ": " + id + " is not registered");
        }
        JsonElement count = object.get("count");
        if (count != null && count.isJsonPrimitive() && count.getAsInt() > 64) {
            problems.add(path + ": count " + count + " above a stack");
        }
    }

    @Test
    void everyTradeNamesRegisteredItems() throws IOException {
        Set<String> items = TestItems.ownItems();
        List<String> problems = new ArrayList<>();
        Set<String> trades = new HashSet<>();
        for (Path path : json(DATA.resolve("villager_trade"))) {
            JsonObject trade = new Gson().fromJson(Files.readString(path), JsonObject.class);
            checkItem(trade.get("wants"), items, problems, path);
            checkItem(trade.get("gives"), items, problems, path);
            trades.add("tc:" + DATA.resolve("villager_trade").relativize(path).toString().replace(".json", ""));
        }
        for (int level = 1; level <= 5; level++) {
            JsonObject tag = new Gson().fromJson(Files.readString(DATA.resolve("tags/villager_trade/station_chief/level_" + level + ".json")), JsonObject.class);
            for (JsonElement value : tag.getAsJsonArray("values")) {
                String entry = value.getAsString();
                if (entry.startsWith("#")) {
                    assertTrue(Files.exists(DATA.resolve("tags/villager_trade/" + entry.substring(4) + ".json")), entry);
                } else if (!trades.contains(entry)) {
                    problems.add("level " + level + " lists " + entry + " which has no trade file");
                }
            }
            JsonObject set = new Gson().fromJson(Files.readString(DATA.resolve("trade_set/station_chief/level_" + level + ".json")), JsonObject.class);
            assertEquals("#tc:station_chief/level_" + level, set.get("trades").getAsString());
        }
        assertTrue(trades.size() > 1000, "CE priced every item: " + trades.size());
        assertEquals(List.of(), problems);
    }

    @Test
    void stationTemplateIsTheCeStation() throws IOException {
        CompoundTag template = NbtIo.readCompressed(DATA.resolve("structure/village/station.nbt"), NbtAccounter.unlimitedHeap());
        ListTag size = template.getListOrEmpty("size");
        assertEquals(List.of(9, 11, 10), List.of(size.getIntOr(0, 0), size.getIntOr(1, 0), size.getIntOr(2, 0)));
        Set<String> items = TestItems.ownItems();
        List<String> names = new ArrayList<>();
        ListTag palette = template.getListOrEmpty("palette");
        for (int index = 0; index < palette.size(); index++) {
            names.add(palette.getCompoundOrEmpty(index).getStringOr("Name", ""));
        }
        assertTrue(names.contains("minecraft:jigsaw"), names.toString());
        for (String name : names) {
            if (name.startsWith("tc:")) {
                assertTrue(items.contains(name.substring(3)), name);
            }
        }
        assertTrue(names.containsAll(List.of("tc:lantern", "tc:train_workbench", "minecraft:rail", "minecraft:bookshelf")), names.toString());
        ListTag entities = template.getListOrEmpty("entities");
        assertEquals(3, entities.size());
        CompoundTag villager = entities.getCompoundOrEmpty(0).getCompoundOrEmpty("nbt");
        assertEquals("minecraft:villager", villager.getStringOr("id", ""));
        assertEquals("tc:station_chief", villager.getCompoundOrEmpty("VillagerData").getStringOr("profession", ""));
        for (int index = 1; index < entities.size(); index++) {
            CompoundTag cart = entities.getCompoundOrEmpty(index).getCompoundOrEmpty("nbt");
            assertTrue(items.contains(cart.getStringOr("id", "").substring(3)), cart.toString());
            assertEquals("VillagerJoe", cart.getStringOr("trainOwner", ""));
        }
    }
}
