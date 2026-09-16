package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import org.jspecify.annotations.Nullable;

import traincraft.Traincraft;
import traincraft.track.TrackDefinitions;
import traincraft.track.TrackType;
import traincraft.track.item.TrackItem;

import java.util.EnumMap;
import java.util.Map;

public final class TrackItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Traincraft.MODID);
    private static final Map<TrackType, DeferredHolder<Item, TrackItem>> BY_TYPE = register();

    private TrackItemRegistry() {}

    private static Map<TrackType, DeferredHolder<Item, TrackItem>> register() {
        Map<TrackType, DeferredHolder<Item, TrackItem>> result = new EnumMap<>(TrackType.class);
        TrackDefinitions.all().entrySet().stream()
                .filter(entry -> entry.getValue().item() != null)
                .sorted(java.util.Comparator.comparing(entry -> entry.getKey().name()))
                .forEach(
                        entry ->
                                result.put(
                                        entry.getKey(),
                                        ITEMS.register(
                                                entry.getValue().item().id(),
                                                key ->
                                                        new TrackItem(
                                                                new Item.Properties()
                                                                        .setId(
                                                                                ResourceKey.create(
                                                                                        Registries
                                                                                                .ITEM,
                                                                                        key)),
                                                                entry.getKey()))));
        return Map.copyOf(result);
    }

    public static @Nullable Item itemFor(TrackType type) {
        var holder = BY_TYPE.get(type);
        return holder == null ? null : holder.get();
    }
}
