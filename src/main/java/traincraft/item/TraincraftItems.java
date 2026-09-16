package traincraft.item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.neoforged.neoforge.registries.DeferredHolder;

import traincraft.Traincraft;
import traincraft.bootstrap.ItemRegistry;

public final class TraincraftItems {

    public static final Map<String, DeferredHolder<Item, Item>> ITEMS = new LinkedHashMap<>();

    private static final ArmorMaterial WORK_CLOTHES = material(5, 1, 2, 2, 1, 25, "work_clothes");
    private static final ArmorMaterial COMPOSITE = material(70, 2, 6, 5, 2, 50, "composite");

    private static ArmorMaterial material(int durability, int helmet, int chest, int legs, int boots, int enchantment, String name) {
        return new ArmorMaterial(durability, Map.of(ArmorType.HELMET, helmet, ArmorType.CHESTPLATE, chest, ArmorType.LEGGINGS, legs, ArmorType.BOOTS, boots),
                enchantment, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, asset(name));
    }

    private static ResourceKey<EquipmentAsset> asset(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Traincraft.MODID, name));
    }

    private static DeferredHolder<Item, Item> register(String name, Function<Item.Properties, Item> factory) {
        DeferredHolder<Item, Item> holder = ItemRegistry.ITEMS.register(name, id -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
        ITEMS.put(name, holder);
        return holder;
    }

    private static DeferredHolder<Item, Item> plain(String name) {
        return register(name, Item::new);
    }

    private static DeferredHolder<Item, Item> armour(String name, ArmorMaterial material, ArmorType type, String asset, int colour) {
        return register(name, properties -> {
            Item.Properties armour = properties.humanoidArmor(withAsset(material, asset), type);
            if (colour != 0) {
                armour = armour.component(DataComponents.DYED_COLOR, new DyedItemColor(colour));
            }
            return material == COMPOSITE ? new CompositeSuitItem(armour.rarity(Rarity.EPIC)) : new Item(armour);
        });
    }

    private static ArmorMaterial withAsset(ArmorMaterial material, String asset) {
        return new ArmorMaterial(material.durability(), material.defense(), material.enchantmentValue(), material.equipSound(), material.toughness(),
                material.knockbackResistance(), material.repairIngredient(), asset(asset));
    }

    static {
        for (String name : new String[] {"steel_dust", "balloon", "propeller", "steam_engine", "copper_ingot", "copper_rail", "steel_rail",
                "reinforced_plastic", "reinforced_plates", "bolt", "wireless_transmitter", "ato_card"}) {
            plain(name);
        }
        register("chunk_loader_activator", properties -> new Item(properties.stacksTo(1).durability(10)));
        register("whistle", properties -> new WhistleItem(properties));
        register("recipe_book", properties -> new RecipeBookItem(properties.stacksTo(1)));
        register("admin_book", properties -> new AdminBookItem(properties.stacksTo(1)));
        register("track_debugger", properties -> new TrackDebuggerItem(properties.stacksTo(1)));
        register("composite_wrench", properties -> new WrenchItem(properties.stacksTo(1)));
        register("paintbrush", properties -> new PaintbrushItem(properties));
        register("airship", properties -> new ZeppelinItem(properties.stacksTo(5), true));
        register("zeppelin", properties -> new ZeppelinItem(properties.stacksTo(5), false));
        armour("overalls", WORK_CLOTHES, ArmorType.LEGGINGS, "overalls", 0);
        armour("jacket", WORK_CLOTHES, ArmorType.CHESTPLATE, "jacket", 0);
        armour("hat", WORK_CLOTHES, ArmorType.HELMET, "hat", 0);
        armour("ticket_man_hat", WORK_CLOTHES, ArmorType.HELMET, "ticket_man", 10465205);
        armour("ticket_man_jacket", WORK_CLOTHES, ArmorType.CHESTPLATE, "ticket_man", 11483);
        armour("ticket_man_pants", WORK_CLOTHES, ArmorType.LEGGINGS, "ticket_man", 14606046);
        armour("driver_hat", WORK_CLOTHES, ArmorType.HELMET, "driver", 1337817);
        armour("driver_jacket", WORK_CLOTHES, ArmorType.CHESTPLATE, "driver", 1337817);
        armour("driver_pants", WORK_CLOTHES, ArmorType.LEGGINGS, "driver", 1390036);
        armour("composite_helmet", COMPOSITE, ArmorType.HELMET, "composite", 1337817);
        armour("composite_jacket", COMPOSITE, ArmorType.CHESTPLATE, "composite", 1337817);
        armour("composite_pants", COMPOSITE, ArmorType.LEGGINGS, "composite", 1390036);
        armour("composite_boots", COMPOSITE, ArmorType.BOOTS, "composite", 1390036);
    }

    public static Item item(String name) {
        return ITEMS.get(name).get();
    }

    public static void touch() {}

    private TraincraftItems() {}
}
