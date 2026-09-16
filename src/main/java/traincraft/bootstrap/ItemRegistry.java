package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.item.RollingStockItem;
import traincraft.vehicle.item.StakeItem;

import java.util.function.Supplier;

/** Item registry. */
public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Traincraft.MODID);

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_ALICE =
            ITEMS.register(
                    "loco_steam_alice",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_STEAM_ALICE.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BR80 =
            ITEMS.register(
                    "loco_steam_br80",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_STEAM_BR80.get()));

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_YELLOW =
            ITEMS.register(
                    "freight_cart_yellow",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.FREIGHT_CART_YELLOW.get()));

    public static final DeferredHolder<Item, StakeItem> STAKE =
            ITEMS.register(
                    "stake",
                    name ->
                            new StakeItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .durability(200)
                                            .setId(ResourceKey.create(Registries.ITEM, name))));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR185 =
            ITEMS.register(
                    "loco_electric_br185",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_ELECTRIC_BR185.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_ES44 =
            ITEMS.register(
                    "loco_diesel_es44",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_DIESEL_ES44.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SD40 =
            ITEMS.register(
                    "loco_diesel_sd40",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_DIESEL_SD40.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SD70 =
            ITEMS.register(
                    "loco_diesel_sd70",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_DIESEL_SD70.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_V60 =
            ITEMS.register(
                    "loco_diesel_v60",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_DIESEL_V60.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR_E69 =
            ITEMS.register(
                    "loco_electric_br_e69",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_ELECTRIC_BR_E69.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_E10 =
            ITEMS.register(
                    "loco_electric_e10",
                    name ->
                            new RollingStockItem(
                                    new Item.Properties()
                                            .stacksTo(1)
                                            .setId(ResourceKey.create(Registries.ITEM, name)),
                                    () -> EntityRegistry.LOCO_ELECTRIC_E10.get()));

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_A4 =
            rollingStock("loco_steam_a4", () -> EntityRegistry.LOCO_STEAM_A4.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_HALL_CLASS =
            rollingStock("loco_steam_hall_class", () -> EntityRegistry.LOCO_STEAM_HALL_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BERK_1225 =
            rollingStock("loco_steam_berk_1225", () -> EntityRegistry.LOCO_STEAM_BERK_1225.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BERK_765 =
            rollingStock("loco_steam_berk_765", () -> EntityRegistry.LOCO_STEAM_BERK_765.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_FOWLER =
            rollingStock("loco_steam_fowler", () -> EntityRegistry.LOCO_STEAM_FOWLER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_KING_CLASS =
            rollingStock("loco_steam_king_class", () -> EntityRegistry.LOCO_STEAM_KING_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_MILW_CLASS_A =
            rollingStock("loco_steam_milw_class_a", () -> EntityRegistry.LOCO_STEAM_MILW_CLASS_A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_CHEREPANOV =
            rollingStock("loco_steam_cherepanov", () -> EntityRegistry.LOCO_STEAM_CHEREPANOV.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_4_4_0 =
            rollingStock("loco_steam_4_4_0", () -> EntityRegistry.LOCO_STEAM_4_4_0.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SMALL =
            rollingStock("loco_steam_small", () -> EntityRegistry.LOCO_STEAM_SMALL.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_LSSP7 =
            rollingStock("loco_steam_lssp7", () -> EntityRegistry.LOCO_STEAM_LSSP7.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_HEAVY =
            rollingStock("loco_steam_heavy", () -> EntityRegistry.LOCO_STEAM_HEAVY.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_C62 =
            rollingStock("loco_steam_c62", () -> EntityRegistry.LOCO_STEAM_C62.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_D51_SHORT =
            rollingStock("loco_steam_d51_short", () -> EntityRegistry.LOCO_STEAM_D51_SHORT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_D51_LONG =
            rollingStock("loco_steam_d51_long", () -> EntityRegistry.LOCO_STEAM_D51_LONG.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BR01 =
            rollingStock("loco_steam_br01", () -> EntityRegistry.LOCO_STEAM_BR01.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_CORANATION_CLASS =
            rollingStock("loco_steam_coranation_class", () -> EntityRegistry.LOCO_STEAM_CORANATION_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GS4 =
            rollingStock("loco_steam_gs4", () -> EntityRegistry.LOCO_STEAM_GS4.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_ER_USSR =
            rollingStock("loco_steam_er_ussr", () -> EntityRegistry.LOCO_STEAM_ER_USSR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_C41 =
            rollingStock("loco_steam_c41", () -> EntityRegistry.LOCO_STEAM_C41.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_C41_080 =
            rollingStock("loco_steam_c41_080", () -> EntityRegistry.LOCO_STEAM_C41_080.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_ALCO_SC4 =
            rollingStock("loco_steam_alco_sc4", () -> EntityRegistry.LOCO_STEAM_ALCO_SC4.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SOUTHERN_1102 =
            rollingStock("loco_steam_southern_1102", () -> EntityRegistry.LOCO_STEAM_SOUTHERN_1102.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_USATC_US =
            rollingStock("loco_steam_usatc_us", () -> EntityRegistry.LOCO_STEAM_USATC_US.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_USATC_UK =
            rollingStock("loco_steam_usatc_uk", () -> EntityRegistry.LOCO_STEAM_USATC_UK.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_C41T =
            rollingStock("loco_steam_c41t", () -> EntityRegistry.LOCO_STEAM_C41T.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_FORNEY =
            rollingStock("loco_steam_forney", () -> EntityRegistry.LOCO_STEAM_FORNEY.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_MOGUL =
            rollingStock("loco_steam_mogul", () -> EntityRegistry.LOCO_STEAM_MOGUL.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SHAY =
            rollingStock("loco_steam_shay", () -> EntityRegistry.LOCO_STEAM_SHAY.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_VB_SHAY =
            rollingStock("loco_steam_vb_shay", () -> EntityRegistry.LOCO_STEAM_VB_SHAY.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_CLIMAX =
            rollingStock("loco_steam_climax", () -> EntityRegistry.LOCO_STEAM_CLIMAX.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_PANNIER =
            rollingStock("loco_steam_pannier", () -> EntityRegistry.LOCO_STEAM_PANNIER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GLYN =
            rollingStock("loco_steam_glyn", () -> EntityRegistry.LOCO_STEAM_GLYN.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_262T =
            rollingStock("loco_steam_262t", () -> EntityRegistry.LOCO_STEAM_262T.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_040VB =
            rollingStock("loco_steam_040vb", () -> EntityRegistry.LOCO_STEAM_040VB.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_ADLER =
            rollingStock("loco_steam_adler", () -> EntityRegistry.LOCO_STEAM_ADLER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_VB_SHAY_2TRUCK =
            rollingStock("loco_steam_vb_shay_2truck", () -> EntityRegistry.LOCO_STEAM_VB_SHAY_2TRUCK.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_CLIMAX_2TRUCK =
            rollingStock("loco_steam_climax_2truck", () -> EntityRegistry.LOCO_STEAM_CLIMAX_2TRUCK.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_C11 =
            rollingStock("loco_steam_c11", () -> EntityRegistry.LOCO_STEAM_C11.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_STAR_CLASS =
            rollingStock("loco_steam_star_class", () -> EntityRegistry.LOCO_STEAM_STAR_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_ONION =
            rollingStock("loco_steam_onion", () -> EntityRegistry.LOCO_STEAM_ONION.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_POLAR_EXPRESS =
            rollingStock("loco_steam_polar_express", () -> EntityRegistry.LOCO_STEAM_POLAR_EXPRESS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GARRATT_FRONT =
            rollingStock("loco_steam_garratt_front", () -> EntityRegistry.LOCO_STEAM_GARRATT_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GARRATT_REAR =
            rollingStock("loco_steam_garratt_rear", () -> EntityRegistry.LOCO_STEAM_GARRATT_REAR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GARRATT_MID =
            rollingStock("loco_steam_garratt_mid", () -> EntityRegistry.LOCO_STEAM_GARRATT_MID.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SKOOKUM =
            rollingStock("loco_steam_skookum", () -> EntityRegistry.LOCO_STEAM_SKOOKUM.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SHAY_3TRUCK =
            rollingStock("loco_steam_shay_3truck", () -> EntityRegistry.LOCO_STEAM_SHAY_3TRUCK.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BR_BLACK_5 =
            rollingStock("loco_steam_br_black_5", () -> EntityRegistry.LOCO_STEAM_BR_BLACK_5.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_RW_TYPE_3 =
            rollingStock("loco_steam_rw_type_3", () -> EntityRegistry.LOCO_STEAM_RW_TYPE_3.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_RW_TYPE_2 =
            rollingStock("loco_steam_rw_type_2", () -> EntityRegistry.LOCO_STEAM_RW_TYPE_2.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GWR_42XX =
            rollingStock("loco_steam_gwr_42xx", () -> EntityRegistry.LOCO_STEAM_GWR_42XX.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GWR_72XX =
            rollingStock("loco_steam_gwr_72xx", () -> EntityRegistry.LOCO_STEAM_GWR_72XX.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_GWR_101_CLASS =
            rollingStock("loco_steam_gwr_101_class", () -> EntityRegistry.LOCO_STEAM_GWR_101_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_WWCP_062T =
            rollingStock("loco_steam_wwcp_062t", () -> EntityRegistry.LOCO_STEAM_WWCP_062T.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_BR_BRITANNIA =
            rollingStock("loco_steam_br_britannia", () -> EntityRegistry.LOCO_STEAM_BR_BRITANNIA.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_J50 =
            rollingStock("loco_steam_j50", () -> EntityRegistry.LOCO_STEAM_J50.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SENTINEL_Y3 =
            rollingStock("loco_steam_sentinel_y3", () -> EntityRegistry.LOCO_STEAM_SENTINEL_Y3.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_MW_CLASS_88 =
            rollingStock("loco_steam_mw_class_88", () -> EntityRegistry.LOCO_STEAM_MW_CLASS_88.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_MR_COMPOUND =
            rollingStock("loco_steam_mr_compound", () -> EntityRegistry.LOCO_STEAM_MR_COMPOUND.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_SMALL =
            rollingStock("tender_small", () -> EntityRegistry.TENDER_SMALL.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_HEAVY =
            rollingStock("tender_heavy", () -> EntityRegistry.TENDER_HEAVY.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_GS4 =
            rollingStock("tender_gs4", () -> EntityRegistry.TENDER_GS4.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_GWR_4000_GALLON =
            rollingStock("tender_gwr_4000_gallon", () -> EntityRegistry.TENDER_GWR_4000_GALLON.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_FOWLER =
            rollingStock("tender_fowler", () -> EntityRegistry.TENDER_FOWLER.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_BERK_1225 =
            rollingStock("tender_berk_1225", () -> EntityRegistry.TENDER_BERK_1225.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_4_4_0 =
            rollingStock("tender_4_4_0", () -> EntityRegistry.TENDER_4_4_0.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_A4 =
            rollingStock("tender_a4", () -> EntityRegistry.TENDER_A4.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_BR01 =
            rollingStock("tender_br01", () -> EntityRegistry.TENDER_BR01.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_CORANATION_CLASS =
            rollingStock("tender_coranation_class", () -> EntityRegistry.TENDER_CORANATION_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_ER_USSR =
            rollingStock("tender_er_ussr", () -> EntityRegistry.TENDER_ER_USSR.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_C62 =
            rollingStock("tender_c62", () -> EntityRegistry.TENDER_C62.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_D51 =
            rollingStock("tender_d51", () -> EntityRegistry.TENDER_D51.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_ADLER =
            rollingStock("tender_adler", () -> EntityRegistry.TENDER_ADLER.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_C41 =
            rollingStock("tender_c41", () -> EntityRegistry.TENDER_C41.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_SOUTHERN_1102 =
            rollingStock("tender_southern_1102", () -> EntityRegistry.TENDER_SOUTHERN_1102.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_MILW =
            rollingStock("tender_milw", () -> EntityRegistry.TENDER_MILW.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_BR_BLACK_5 =
            rollingStock("tender_br_black_5", () -> EntityRegistry.TENDER_BR_BLACK_5.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_BR1 =
            rollingStock("tender_br1", () -> EntityRegistry.TENDER_BR1.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_RW_TYPE_2 =
            rollingStock("tender_rw_type_2", () -> EntityRegistry.TENDER_RW_TYPE_2.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_STAR_CLASS =
            rollingStock("tender_star_class", () -> EntityRegistry.TENDER_STAR_CLASS.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_ONION =
            rollingStock("tender_onion", () -> EntityRegistry.TENDER_ONION.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_POLAR_EXPRESS =
            rollingStock("tender_polar_express", () -> EntityRegistry.TENDER_POLAR_EXPRESS.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_SKOOKUM =
            rollingStock("tender_skookum", () -> EntityRegistry.TENDER_SKOOKUM.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_SHAY_3TRUCK =
            rollingStock("tender_shay_3truck", () -> EntityRegistry.TENDER_SHAY_3TRUCK.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_SHUNTING_UK =
            rollingStock("tender_shunting_uk", () -> EntityRegistry.TENDER_SHUNTING_UK.get());

    public static final DeferredHolder<Item, RollingStockItem> TENDER_MR_COMPOUND =
            rollingStock("tender_mr_compound", () -> EntityRegistry.TENDER_MR_COMPOUND.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_STEAM_SNOW_PLOW =
            rollingStock("loco_steam_snow_plow", () -> EntityRegistry.LOCO_STEAM_SNOW_PLOW.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_KOF =
            rollingStock("loco_diesel_kof", () -> EntityRegistry.LOCO_DIESEL_KOF.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP40 =
            rollingStock("loco_diesel_gp40", () -> EntityRegistry.LOCO_DIESEL_GP40.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CHME3 =
            rollingStock("loco_diesel_chme3", () -> EntityRegistry.LOCO_DIESEL_CHME3.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP7_RED =
            rollingStock("loco_diesel_gp7_red", () -> EntityRegistry.LOCO_DIESEL_GP7_RED.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SHUNTER =
            rollingStock("loco_diesel_shunter", () -> EntityRegistry.LOCO_DIESEL_SHUNTER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_IC4_DSB_MG =
            rollingStock("loco_diesel_ic4_dsb_mg", () -> EntityRegistry.LOCO_DIESEL_IC4_DSB_MG.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_MILW_H1044 =
            rollingStock("loco_diesel_milw_h1044", () -> EntityRegistry.LOCO_DIESEL_MILW_H1044.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDF7 =
            rollingStock("loco_diesel_emdf7", () -> EntityRegistry.LOCO_DIESEL_EMDF7.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDF3 =
            rollingStock("loco_diesel_emdf3", () -> EntityRegistry.LOCO_DIESEL_EMDF3.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EWS_CLASS66 =
            rollingStock("loco_diesel_ews_class66", () -> EntityRegistry.LOCO_DIESEL_EWS_CLASS66.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DELTIC =
            rollingStock("loco_diesel_deltic", () -> EntityRegistry.LOCO_DIESEL_DELTIC.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DD35A =
            rollingStock("loco_diesel_dd35a", () -> EntityRegistry.LOCO_DIESEL_DD35A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_44_TON_SWITCHER =
            rollingStock("loco_diesel_44_ton_switcher", () -> EntityRegistry.LOCO_DIESEL_44_TON_SWITCHER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAMBOO =
            rollingStock("loco_diesel_bamboo", () -> EntityRegistry.LOCO_DIESEL_BAMBOO.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_WLS40 =
            rollingStock("loco_diesel_wls40", () -> EntityRegistry.LOCO_DIESEL_WLS40.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_FOL_M1 =
            rollingStock("loco_diesel_fol_m1", () -> EntityRegistry.LOCO_DIESEL_FOL_M1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_FOL_M1B =
            rollingStock("loco_diesel_fol_m1b", () -> EntityRegistry.LOCO_DIESEL_FOL_M1B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CF7 =
            rollingStock("loco_diesel_cf7", () -> EntityRegistry.LOCO_DIESEL_CF7.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP15 =
            rollingStock("loco_diesel_gp15", () -> EntityRegistry.LOCO_DIESEL_GP15.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SW8 =
            rollingStock("loco_diesel_sw8", () -> EntityRegistry.LOCO_DIESEL_SW8.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CD814 =
            rollingStock("loco_diesel_cd814", () -> EntityRegistry.LOCO_DIESEL_CD814.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CD810 =
            rollingStock("loco_diesel_cd810", () -> EntityRegistry.LOCO_DIESEL_CD810.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SM42 =
            rollingStock("loco_diesel_sm42", () -> EntityRegistry.LOCO_DIESEL_SM42.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GE44TON =
            rollingStock("loco_diesel_ge44ton", () -> EntityRegistry.LOCO_DIESEL_GE44TON.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAP_F7A =
            rollingStock("loco_diesel_bap_f7a", () -> EntityRegistry.LOCO_DIESEL_BAP_F7A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAP_F7B =
            rollingStock("loco_diesel_bap_f7b", () -> EntityRegistry.LOCO_DIESEL_BAP_F7B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_H1044 =
            rollingStock("loco_diesel_h1044", () -> EntityRegistry.LOCO_DIESEL_H1044.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP13 =
            rollingStock("loco_diesel_gp13", () -> EntityRegistry.LOCO_DIESEL_GP13.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAP_B23 =
            rollingStock("loco_diesel_bap_b23", () -> EntityRegistry.LOCO_DIESEL_BAP_B23.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_C424 =
            rollingStock("loco_diesel_c424", () -> EntityRegistry.LOCO_DIESEL_C424.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_C425 =
            rollingStock("loco_diesel_c425", () -> EntityRegistry.LOCO_DIESEL_C425.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP7U =
            rollingStock("loco_diesel_gp7u", () -> EntityRegistry.LOCO_DIESEL_GP7U.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP7 =
            rollingStock("loco_diesel_gp7", () -> EntityRegistry.LOCO_DIESEL_GP7.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP7B =
            rollingStock("loco_diesel_gp7b", () -> EntityRegistry.LOCO_DIESEL_GP7B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP9 =
            rollingStock("loco_diesel_gp9", () -> EntityRegistry.LOCO_DIESEL_GP9.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP30 =
            rollingStock("loco_diesel_gp30", () -> EntityRegistry.LOCO_DIESEL_GP30.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP38DASH2 =
            rollingStock("loco_diesel_gp38dash2", () -> EntityRegistry.LOCO_DIESEL_GP38DASH2.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_KOF_III =
            rollingStock("loco_diesel_kof_iii", () -> EntityRegistry.LOCO_DIESEL_KOF_III.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_KOF_III_M =
            rollingStock("loco_diesel_kof_iii_m", () -> EntityRegistry.LOCO_DIESEL_KOF_III_M.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_U36C =
            rollingStock("loco_diesel_u36c", () -> EntityRegistry.LOCO_DIESEL_U36C.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP49 =
            rollingStock("loco_diesel_gp49", () -> EntityRegistry.LOCO_DIESEL_GP49.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAP_GP15 =
            rollingStock("loco_diesel_bap_gp15", () -> EntityRegistry.LOCO_DIESEL_BAP_GP15.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SD9 =
            rollingStock("loco_diesel_sd9", () -> EntityRegistry.LOCO_DIESEL_SD9.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SD40DASH2 =
            rollingStock("loco_diesel_sd40dash2", () -> EntityRegistry.LOCO_DIESEL_SD40DASH2.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_U23B =
            rollingStock("loco_diesel_u23b", () -> EntityRegistry.LOCO_DIESEL_U23B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_U18B =
            rollingStock("loco_diesel_u18b", () -> EntityRegistry.LOCO_DIESEL_U18B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_HH660 =
            rollingStock("loco_diesel_hh660", () -> EntityRegistry.LOCO_DIESEL_HH660.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_KRAUTT =
            rollingStock("loco_diesel_krautt", () -> EntityRegistry.LOCO_DIESEL_KRAUTT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DASH8_40B =
            rollingStock("loco_diesel_dash8_40b", () -> EntityRegistry.LOCO_DIESEL_DASH8_40B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS44 =
            rollingStock("loco_diesel_class44", () -> EntityRegistry.LOCO_DIESEL_CLASS44.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SW1500 =
            rollingStock("loco_diesel_sw1500", () -> EntityRegistry.LOCO_DIESEL_SW1500.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SW1 =
            rollingStock("loco_diesel_sw1", () -> EntityRegistry.LOCO_DIESEL_SW1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DASH8_40C =
            rollingStock("loco_diesel_dash8_40c", () -> EntityRegistry.LOCO_DIESEL_DASH8_40C.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SW1200 =
            rollingStock("loco_diesel_sw1200", () -> EntityRegistry.LOCO_DIESEL_SW1200.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_RSD15 =
            rollingStock("loco_diesel_rsd15", () -> EntityRegistry.LOCO_DIESEL_RSD15.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_SD70MAC =
            rollingStock("loco_diesel_sd70mac", () -> EntityRegistry.LOCO_DIESEL_SD70MAC.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DASH9_44CW =
            rollingStock("loco_diesel_dash9_44cw", () -> EntityRegistry.LOCO_DIESEL_DASH9_44CW.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DASH8_40BB =
            rollingStock("loco_diesel_dash8_40bb", () -> EntityRegistry.LOCO_DIESEL_DASH8_40BB.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DASH8_40BW =
            rollingStock("loco_diesel_dash8_40bw", () -> EntityRegistry.LOCO_DIESEL_DASH8_40BW.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_DH643 =
            rollingStock("loco_diesel_dh643", () -> EntityRegistry.LOCO_DIESEL_DH643.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAP_CF7 =
            rollingStock("loco_diesel_bap_cf7", () -> EntityRegistry.LOCO_DIESEL_BAP_CF7.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CF7ROUND =
            rollingStock("loco_diesel_cf7round", () -> EntityRegistry.LOCO_DIESEL_CF7ROUND.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GP38DASH9W =
            rollingStock("loco_diesel_gp38dash9w", () -> EntityRegistry.LOCO_DIESEL_GP38DASH9W.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_ALCO_S2 =
            rollingStock("loco_diesel_alco_s2", () -> EntityRegistry.LOCO_DIESEL_ALCO_S2.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BEEP =
            rollingStock("loco_diesel_beep", () -> EntityRegistry.LOCO_DIESEL_BEEP.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS158 =
            rollingStock("loco_diesel_class158", () -> EntityRegistry.LOCO_DIESEL_CLASS158.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS153 =
            rollingStock("loco_diesel_class153", () -> EntityRegistry.LOCO_DIESEL_CLASS153.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS156 =
            rollingStock("loco_diesel_class156", () -> EntityRegistry.LOCO_DIESEL_CLASS156.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS47 =
            rollingStock("loco_diesel_class47", () -> EntityRegistry.LOCO_DIESEL_CLASS47.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_ALCO_PA1 =
            rollingStock("loco_diesel_alco_pa1", () -> EntityRegistry.LOCO_DIESEL_ALCO_PA1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_ALCO_PB1 =
            rollingStock("loco_diesel_alco_pb1", () -> EntityRegistry.LOCO_DIESEL_ALCO_PB1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDE8A =
            rollingStock("loco_diesel_emde8a", () -> EntityRegistry.LOCO_DIESEL_EMDE8A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDE8B =
            rollingStock("loco_diesel_emde8b", () -> EntityRegistry.LOCO_DIESEL_EMDE8B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS43 =
            rollingStock("loco_diesel_class43", () -> EntityRegistry.LOCO_DIESEL_CLASS43.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_C415H =
            rollingStock("loco_diesel_c415h", () -> EntityRegistry.LOCO_DIESEL_C415H.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_C415S =
            rollingStock("loco_diesel_c415s", () -> EntityRegistry.LOCO_DIESEL_C415S.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_C415L =
            rollingStock("loco_diesel_c415l", () -> EntityRegistry.LOCO_DIESEL_C415L.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_GE25TON =
            rollingStock("loco_diesel_ge25ton", () -> EntityRegistry.LOCO_DIESEL_GE25TON.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_H24_66 =
            rollingStock("loco_diesel_h24_66", () -> EntityRegistry.LOCO_DIESEL_H24_66.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_H24_66L =
            rollingStock("loco_diesel_h24_66l", () -> EntityRegistry.LOCO_DIESEL_H24_66L.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDE7A =
            rollingStock("loco_diesel_emde7a", () -> EntityRegistry.LOCO_DIESEL_EMDE7A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_EMDE7B =
            rollingStock("loco_diesel_emde7b", () -> EntityRegistry.LOCO_DIESEL_EMDE7B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS175 =
            rollingStock("loco_diesel_class175", () -> EntityRegistry.LOCO_DIESEL_CLASS175.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_H16_66 =
            rollingStock("loco_diesel_h16_66", () -> EntityRegistry.LOCO_DIESEL_H16_66.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS34 =
            rollingStock("loco_diesel_class34", () -> EntityRegistry.LOCO_DIESEL_CLASS34.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS121_BUBBLECAR =
            rollingStock("loco_diesel_class121_bubblecar", () -> EntityRegistry.LOCO_DIESEL_CLASS121_BUBBLECAR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS117 =
            rollingStock("loco_diesel_class117", () -> EntityRegistry.LOCO_DIESEL_CLASS117.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS143_FRONT =
            rollingStock("loco_diesel_class143_front", () -> EntityRegistry.LOCO_DIESEL_CLASS143_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CD754 =
            rollingStock("loco_diesel_cd754", () -> EntityRegistry.LOCO_DIESEL_CD754.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS142_FRONT =
            rollingStock("loco_diesel_class142_front", () -> EntityRegistry.LOCO_DIESEL_CLASS142_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_BAGNALL =
            rollingStock("loco_diesel_bagnall", () -> EntityRegistry.LOCO_DIESEL_BAGNALL.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_CLASS205 =
            rollingStock("loco_diesel_class205", () -> EntityRegistry.LOCO_DIESEL_CLASS205.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_MINE_TRAIN =
            rollingStock("loco_electric_mine_train", () -> EntityRegistry.LOCO_ELECTRIC_MINE_TRAIN.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_SPEED_ZERO_ED =
            rollingStock("loco_electric_speed_zero_ed", () -> EntityRegistry.LOCO_ELECTRIC_SPEED_ZERO_ED.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_ICE1 =
            rollingStock("loco_electric_ice1", () -> EntityRegistry.LOCO_ELECTRIC_ICE1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_TRAM_YELLOW =
            rollingStock("loco_electric_tram_yellow", () -> EntityRegistry.LOCO_ELECTRIC_TRAM_YELLOW.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_TRAM_NY =
            rollingStock("loco_electric_tram_ny", () -> EntityRegistry.LOCO_ELECTRIC_TRAM_NY.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_E103 =
            rollingStock("loco_electric_e103", () -> EntityRegistry.LOCO_ELECTRIC_E103.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS85 =
            rollingStock("loco_electric_class85", () -> EntityRegistry.LOCO_ELECTRIC_CLASS85.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CD151 =
            rollingStock("loco_electric_cd151", () -> EntityRegistry.LOCO_ELECTRIC_CD151.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BP4 =
            rollingStock("loco_electric_bp4", () -> EntityRegistry.LOCO_ELECTRIC_BP4.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_RENFE446_MOTOR =
            rollingStock("loco_electric_renfe446_motor", () -> EntityRegistry.LOCO_ELECTRIC_RENFE446_MOTOR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_PCH120 =
            rollingStock("loco_electric_pch120", () -> EntityRegistry.LOCO_ELECTRIC_PCH120.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_LU_ENGINE =
            rollingStock("loco_electric_lu_engine", () -> EntityRegistry.LOCO_ELECTRIC_LU_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DSTOCK_ENGINE =
            rollingStock("loco_electric_dstock_engine", () -> EntityRegistry.LOCO_ELECTRIC_DSTOCK_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS345 =
            rollingStock("loco_electric_class345", () -> EntityRegistry.LOCO_ELECTRIC_CLASS345.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BNLRV_A =
            rollingStock("loco_electric_bnlrv_a", () -> EntityRegistry.LOCO_ELECTRIC_BNLRV_A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_TW305 =
            rollingStock("loco_electric_tw305", () -> EntityRegistry.LOCO_ELECTRIC_TW305.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_METRO2000 =
            rollingStock("loco_electric_metro2000", () -> EntityRegistry.LOCO_ELECTRIC_METRO2000.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_RENFE450_MOTOR =
            rollingStock("loco_electric_renfe450_motor", () -> EntityRegistry.LOCO_ELECTRIC_RENFE450_MOTOR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR155 =
            rollingStock("loco_electric_br155", () -> EntityRegistry.LOCO_ELECTRIC_BR155.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_440R_FRONT =
            rollingStock("loco_electric_440r_front", () -> EntityRegistry.LOCO_ELECTRIC_440R_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DB143 =
            rollingStock("loco_electric_db143", () -> EntityRegistry.LOCO_ELECTRIC_DB143.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_EF1 =
            rollingStock("loco_electric_ef1", () -> EntityRegistry.LOCO_ELECTRIC_EF1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_EF1B =
            rollingStock("loco_electric_ef1b", () -> EntityRegistry.LOCO_ELECTRIC_EF1B.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_EP1A =
            rollingStock("loco_electric_ep1a", () -> EntityRegistry.LOCO_ELECTRIC_EP1A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_ILMA =
            rollingStock("loco_electric_ilma", () -> EntityRegistry.LOCO_ELECTRIC_ILMA.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_ILMB =
            rollingStock("loco_electric_ilmb", () -> EntityRegistry.LOCO_ELECTRIC_ILMB.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_FEVE3300FRONT =
            rollingStock("loco_electric_feve3300front", () -> EntityRegistry.LOCO_ELECTRIC_FEVE3300FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_EU07 =
            rollingStock("loco_electric_eu07", () -> EntityRegistry.LOCO_ELECTRIC_EU07.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_GM6C =
            rollingStock("loco_electric_gm6c", () -> EntityRegistry.LOCO_ELECTRIC_GM6C.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS319_ENGINE =
            rollingStock("loco_electric_class319_engine", () -> EntityRegistry.LOCO_ELECTRIC_CLASS319_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_KVB_2300 =
            rollingStock("loco_electric_kvb_2300", () -> EntityRegistry.LOCO_ELECTRIC_KVB_2300.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR_MK2F_DBSO =
            rollingStock("loco_electric_br_mk2f_dbso", () -> EntityRegistry.LOCO_ELECTRIC_BR_MK2F_DBSO.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR_MK3_DVT =
            rollingStock("loco_electric_br_mk3_dvt", () -> EntityRegistry.LOCO_ELECTRIC_BR_MK3_DVT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_BR_MK4_DVT =
            rollingStock("loco_electric_br_mk4_dvt", () -> EntityRegistry.LOCO_ELECTRIC_BR_MK4_DVT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS90 =
            rollingStock("loco_electric_class90", () -> EntityRegistry.LOCO_ELECTRIC_CLASS90.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS91 =
            rollingStock("loco_electric_class91", () -> EntityRegistry.LOCO_ELECTRIC_CLASS91.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS321 =
            rollingStock("loco_electric_class321", () -> EntityRegistry.LOCO_ELECTRIC_CLASS321.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_NMBS_HLE_18 =
            rollingStock("loco_electric_nmbs_hle_18", () -> EntityRegistry.LOCO_ELECTRIC_NMBS_HLE_18.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_FGV4300_MOTOR =
            rollingStock("loco_electric_fgv4300_motor", () -> EntityRegistry.LOCO_ELECTRIC_FGV4300_MOTOR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_INTERURBAN_SERIES_100 =
            rollingStock("loco_electric_interurban_series_100", () -> EntityRegistry.LOCO_ELECTRIC_INTERURBAN_SERIES_100.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_METRO3000 =
            rollingStock("loco_electric_metro3000", () -> EntityRegistry.LOCO_ELECTRIC_METRO3000.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CQ310 =
            rollingStock("loco_electric_cq310", () -> EntityRegistry.LOCO_ELECTRIC_CQ310.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS162_ENGINE =
            rollingStock("loco_electric_class162_engine", () -> EntityRegistry.LOCO_ELECTRIC_CLASS162_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_METAL_TRAM =
            rollingStock("loco_electric_metal_tram", () -> EntityRegistry.LOCO_ELECTRIC_METAL_TRAM.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_B80C_A =
            rollingStock("loco_electric_b80c_a", () -> EntityRegistry.LOCO_ELECTRIC_B80C_A.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_MA100_LOCO =
            rollingStock("loco_electric_ma100_loco", () -> EntityRegistry.LOCO_ELECTRIC_MA100_LOCO.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS390_FRONT =
            rollingStock("loco_electric_class390_front", () -> EntityRegistry.LOCO_ELECTRIC_CLASS390_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DUEWAG_T4ER =
            rollingStock("loco_electric_duewag_t4er", () -> EntityRegistry.LOCO_ELECTRIC_DUEWAG_T4ER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DUEWAG_GT6ZR =
            rollingStock("loco_electric_duewag_gt6zr", () -> EntityRegistry.LOCO_ELECTRIC_DUEWAG_GT6ZR.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_M8C =
            rollingStock("loco_electric_m8c", () -> EntityRegistry.LOCO_ELECTRIC_M8C.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS416_LOCO =
            rollingStock("loco_electric_class416_loco", () -> EntityRegistry.LOCO_ELECTRIC_CLASS416_LOCO.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DB420_LOCO =
            rollingStock("loco_electric_db420_loco", () -> EntityRegistry.LOCO_ELECTRIC_DB420_LOCO.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS401_ENGINE =
            rollingStock("loco_electric_class401_engine", () -> EntityRegistry.LOCO_ELECTRIC_CLASS401_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS230_ENGINE =
            rollingStock("loco_electric_class230_engine", () -> EntityRegistry.LOCO_ELECTRIC_CLASS230_ENGINE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_DUEWAG_GT6ER =
            rollingStock("loco_electric_duewag_gt6er", () -> EntityRegistry.LOCO_ELECTRIC_DUEWAG_GT6ER.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS374_FRONT =
            rollingStock("loco_electric_class374_front", () -> EntityRegistry.LOCO_ELECTRIC_CLASS374_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS387_FRONT =
            rollingStock("loco_electric_class387_front", () -> EntityRegistry.LOCO_ELECTRIC_CLASS387_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS378_FRONT =
            rollingStock("loco_electric_class378_front", () -> EntityRegistry.LOCO_ELECTRIC_CLASS378_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS389_FRONT =
            rollingStock("loco_electric_class389_front", () -> EntityRegistry.LOCO_ELECTRIC_CLASS389_FRONT.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_CLASS442_DTS =
            rollingStock("loco_electric_class442_dts", () -> EntityRegistry.LOCO_ELECTRIC_CLASS442_DTS.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_M8_DNF1 =
            rollingStock("loco_electric_m8_dnf1", () -> EntityRegistry.LOCO_ELECTRIC_M8_DNF1.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_ELECTRIC_VL10 =
            rollingStock("loco_electric_vl10", () -> EntityRegistry.LOCO_ELECTRIC_VL10.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CART_BLUE =
            rollingStock("passenger_cart_blue", () -> EntityRegistry.PASSENGER_CART_BLUE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CART_BLACK_SMALL =
            rollingStock("passenger_cart_black_small", () -> EntityRegistry.PASSENGER_CART_BLACK_SMALL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_LONG_GREEN =
            rollingStock("passenger_long_green", () -> EntityRegistry.PASSENGER_LONG_GREEN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_SHORT_GREEN =
            rollingStock("passenger_short_green", () -> EntityRegistry.PASSENGER_SHORT_GREEN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_1CLASS_DB =
            rollingStock("passenger_1class_db", () -> EntityRegistry.PASSENGER_1CLASS_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_2CLASS_DB =
            rollingStock("passenger_2class_db", () -> EntityRegistry.PASSENGER_2CLASS_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_HIGH_SPEED_ZERO_ED =
            rollingStock("passenger_high_speed_zero_ed", () -> EntityRegistry.PASSENGER_HIGH_SPEED_ZERO_ED.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_TRAM_NY =
            rollingStock("passenger_tram_ny", () -> EntityRegistry.PASSENGER_TRAM_NY.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_ADLER =
            rollingStock("passenger_adler", () -> EntityRegistry.PASSENGER_ADLER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DB_ORIENTAL =
            rollingStock("passenger_db_oriental", () -> EntityRegistry.PASSENGER_DB_ORIENTAL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_IC4_DSB_FG =
            rollingStock("passenger_ic4_dsb_fg", () -> EntityRegistry.PASSENGER_IC4_DSB_FG.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_IC4_DSB_FH =
            rollingStock("passenger_ic4_dsb_fh", () -> EntityRegistry.PASSENGER_IC4_DSB_FH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_ICE1_CLASS1 =
            rollingStock("passenger_ice1_class1", () -> EntityRegistry.PASSENGER_ICE1_CLASS1.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_ICE1_CLASS2 =
            rollingStock("passenger_ice1_class2", () -> EntityRegistry.PASSENGER_ICE1_CLASS2.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_ICE1_RESTAURANT =
            rollingStock("passenger_ice1_restaurant", () -> EntityRegistry.PASSENGER_ICE1_RESTAURANT.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_GS4 =
            rollingStock("passenger_gs4", () -> EntityRegistry.PASSENGER_GS4.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_GS4_OBSERVATORY =
            rollingStock("passenger_gs4_observatory", () -> EntityRegistry.PASSENGER_GS4_OBSERVATORY.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_GS4_TAIL =
            rollingStock("passenger_gs4_tail", () -> EntityRegistry.PASSENGER_GS4_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DENVER_RIO_GRANGE =
            rollingStock("passenger_denver_rio_grange", () -> EntityRegistry.PASSENGER_DENVER_RIO_GRANGE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DENVER_RIO_GRANDE_COMBO =
            rollingStock("passenger_denver_rio_grande_combo", () -> EntityRegistry.PASSENGER_DENVER_RIO_GRANDE_COMBO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_RHEINGOLD =
            rollingStock("passenger_rheingold", () -> EntityRegistry.PASSENGER_RHEINGOLD.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_RHEINGOLD_PANORAMA =
            rollingStock("passenger_rheingold_panorama", () -> EntityRegistry.PASSENGER_RHEINGOLD_PANORAMA.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_MILW =
            rollingStock("passenger_milw", () -> EntityRegistry.PASSENGER_MILW.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_MILW_TAIL =
            rollingStock("passenger_milw_tail", () -> EntityRegistry.PASSENGER_MILW_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BAMBOO =
            rollingStock("passenger_bamboo", () -> EntityRegistry.PASSENGER_BAMBOO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_RENFE446_COACH =
            rollingStock("passenger_renfe446_coach", () -> EntityRegistry.PASSENGER_RENFE446_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CABOOSE_RENFE446_TAIL =
            rollingStock("passenger_caboose_renfe446_tail", () -> EntityRegistry.PASSENGER_CABOOSE_RENFE446_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PCH120COACH =
            rollingStock("passenger_pch120coach", () -> EntityRegistry.PASSENGER_PCH120COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_L_UPASSENGER =
            rollingStock("passenger_l_upassenger", () -> EntityRegistry.PASSENGER_L_UPASSENGER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DSTOCK_PASSENGER =
            rollingStock("passenger_dstock_passenger", () -> EntityRegistry.PASSENGER_DSTOCK_PASSENGER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS345_COACH =
            rollingStock("passenger_class345_coach", () -> EntityRegistry.PASSENGER_CLASS345_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS52_SEAT_COACH =
            rollingStock("passenger_ps52_seat_coach", () -> EntityRegistry.PASSENGER_PS52_SEAT_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_P_SCENTER_DINER =
            rollingStock("passenger_p_scenter_diner", () -> EntityRegistry.PASSENGER_P_SCENTER_DINER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS_ANOTHER_DINER =
            rollingStock("passenger_ps_another_diner", () -> EntityRegistry.PASSENGER_PS_ANOTHER_DINER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BNLRV_B =
            rollingStock("passenger_bnlrv_b", () -> EntityRegistry.PASSENGER_BNLRV_B.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BW305 =
            rollingStock("passenger_bw305", () -> EntityRegistry.PASSENGER_BW305.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_METRO2000 =
            rollingStock("passenger_metro2000", () -> EntityRegistry.PASSENGER_METRO2000.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_RENFE450_COACH =
            rollingStock("passenger_renfe450_coach", () -> EntityRegistry.PASSENGER_RENFE450_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CABOOSE_RENFE450_TAIL =
            rollingStock("passenger_caboose_renfe450_tail", () -> EntityRegistry.PASSENGER_CABOOSE_RENFE450_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CD014 =
            rollingStock("passenger_cd014", () -> EntityRegistry.PASSENGER_CD014.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CD914 =
            rollingStock("passenger_cd914", () -> EntityRegistry.PASSENGER_CD914.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CD010 =
            rollingStock("passenger_cd010", () -> EntityRegistry.PASSENGER_CD010.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_AMFLEET =
            rollingStock("passenger_amfleet", () -> EntityRegistry.PASSENGER_AMFLEET.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_AMFLEET2 =
            rollingStock("passenger_amfleet2", () -> EntityRegistry.PASSENGER_AMFLEET2.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_STAR_CAR_FAT =
            rollingStock("passenger_star_car_fat", () -> EntityRegistry.PASSENGER_STAR_CAR_FAT.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_STAR_CAR_NOT_FAT =
            rollingStock("passenger_star_car_not_fat", () -> EntityRegistry.PASSENGER_STAR_CAR_NOT_FAT.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_440_R_MID =
            rollingStock("passenger_440_r_mid", () -> EntityRegistry.PASSENGER_440_R_MID.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_440_R_REAR =
            rollingStock("passenger_440_r_rear", () -> EntityRegistry.PASSENGER_440_R_REAR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FEVE3300REAR =
            rollingStock("passenger_feve3300rear", () -> EntityRegistry.PASSENGER_FEVE3300REAR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS158_COACH =
            rollingStock("passenger_class158_coach", () -> EntityRegistry.PASSENGER_CLASS158_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS153_COACH =
            rollingStock("passenger_class153_coach", () -> EntityRegistry.PASSENGER_CLASS153_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS_SLEEPER565 =
            rollingStock("passenger_ps_sleeper565", () -> EntityRegistry.PASSENGER_PS_SLEEPER565.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS_SLEEPER565_DRGW =
            rollingStock("passenger_ps_sleeper565_drgw", () -> EntityRegistry.PASSENGER_PS_SLEEPER565_DRGW.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_SNCB_M6 =
            rollingStock("passenger_sncb_m6", () -> EntityRegistry.PASSENGER_SNCB_M6.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_SNCB_M6_TAIL =
            rollingStock("passenger_sncb_m6_tail", () -> EntityRegistry.PASSENGER_SNCB_M6_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS319_MIDDLE =
            rollingStock("passenger_class319_middle", () -> EntityRegistry.PASSENGER_CLASS319_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS319_PANTO =
            rollingStock("passenger_class319_panto", () -> EntityRegistry.PASSENGER_CLASS319_PANTO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS319_TAIL =
            rollingStock("passenger_class319_tail", () -> EntityRegistry.PASSENGER_CLASS319_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_KVB_2300_B =
            rollingStock("passenger_kvb_2300_b", () -> EntityRegistry.PASSENGER_KVB_2300_B.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK2_C_BSO =
            rollingStock("passenger_br_mk2_c_bso", () -> EntityRegistry.PASSENGER_BR_MK2_C_BSO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK2_C_COACH =
            rollingStock("passenger_br_mk2_c_coach", () -> EntityRegistry.PASSENGER_BR_MK2_C_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK2_F_BSO =
            rollingStock("passenger_br_mk2_f_bso", () -> EntityRegistry.PASSENGER_BR_MK2_F_BSO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK2_F_COACH =
            rollingStock("passenger_br_mk2_f_coach", () -> EntityRegistry.PASSENGER_BR_MK2_F_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK3_BUFFET =
            rollingStock("passenger_br_mk3_buffet", () -> EntityRegistry.PASSENGER_BR_MK3_BUFFET.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK3_COACH =
            rollingStock("passenger_br_mk3_coach", () -> EntityRegistry.PASSENGER_BR_MK3_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK3A_COACH =
            rollingStock("passenger_br_mk3a_coach", () -> EntityRegistry.PASSENGER_BR_MK3A_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK3_PANTOGRAPH =
            rollingStock("passenger_br_mk3_pantograph", () -> EntityRegistry.PASSENGER_BR_MK3_PANTOGRAPH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK4_COACH =
            rollingStock("passenger_br_mk4_coach", () -> EntityRegistry.PASSENGER_BR_MK4_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK4_BUFFET =
            rollingStock("passenger_br_mk4_buffet", () -> EntityRegistry.PASSENGER_BR_MK4_BUFFET.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS321_MOTOR =
            rollingStock("passenger_class321_motor", () -> EntityRegistry.PASSENGER_CLASS321_MOTOR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS321_COACH =
            rollingStock("passenger_class321_coach", () -> EntityRegistry.PASSENGER_CLASS321_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_MINETRAIN =
            rollingStock("passenger_minetrain", () -> EntityRegistry.PASSENGER_MINETRAIN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS_LUNCH_COUNTER_LOUNGE =
            rollingStock("passenger_ps_lunch_counter_lounge", () -> EntityRegistry.PASSENGER_PS_LUNCH_COUNTER_LOUNGE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS30_SEAT_PARLOR =
            rollingStock("passenger_ps30_seat_parlor", () -> EntityRegistry.PASSENGER_PS30_SEAT_PARLOR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS54_SEAT_COACH_LOUNGE =
            rollingStock("passenger_ps54_seat_coach_lounge", () -> EntityRegistry.PASSENGER_PS54_SEAT_COACH_LOUNGE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS58_SEAT_COACH_OBSERVATION =
            rollingStock("passenger_ps58_seat_coach_observation", () -> EntityRegistry.PASSENGER_PS58_SEAT_COACH_OBSERVATION.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PSBM56_SEAT_COACH =
            rollingStock("passenger_psbm56_seat_coach", () -> EntityRegistry.PASSENGER_PSBM56_SEAT_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PSBM_COMBINE =
            rollingStock("passenger_psbm_combine", () -> EntityRegistry.PASSENGER_PSBM_COMBINE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PSBM_DINER_LOUNGE =
            rollingStock("passenger_psbm_diner_lounge", () -> EntityRegistry.PASSENGER_PSBM_DINER_LOUNGE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK1_BSO =
            rollingStock("passenger_br_mk1_bso", () -> EntityRegistry.PASSENGER_BR_MK1_BSO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK1_TSO =
            rollingStock("passenger_br_mk1_tso", () -> EntityRegistry.PASSENGER_BR_MK1_TSO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK1_BUFFET =
            rollingStock("passenger_br_mk1_buffet", () -> EntityRegistry.PASSENGER_BR_MK1_BUFFET.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_MK1_BAGGAGE =
            rollingStock("passenger_br_mk1_baggage", () -> EntityRegistry.PASSENGER_BR_MK1_BAGGAGE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS175_COACH =
            rollingStock("passenger_class175_coach", () -> EntityRegistry.PASSENGER_CLASS175_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_ACFGN60_SEAT_COACH =
            rollingStock("passenger_acfgn60_seat_coach", () -> EntityRegistry.PASSENGER_ACFGN60_SEAT_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FGV4300_COACH =
            rollingStock("passenger_fgv4300_coach", () -> EntityRegistry.PASSENGER_FGV4300_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FGV4300_TAIL =
            rollingStock("passenger_fgv4300_tail", () -> EntityRegistry.PASSENGER_FGV4300_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_METRO3000 =
            rollingStock("passenger_metro3000", () -> EntityRegistry.PASSENGER_METRO3000.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_METRO3000_TAIL =
            rollingStock("passenger_metro3000_tail", () -> EntityRegistry.PASSENGER_METRO3000_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS162_COACH_B =
            rollingStock("passenger_class162_coach_b", () -> EntityRegistry.PASSENGER_CLASS162_COACH_B.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS162_COACH_A =
            rollingStock("passenger_class162_coach_a", () -> EntityRegistry.PASSENGER_CLASS162_COACH_A.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS162_TAIL =
            rollingStock("passenger_class162_tail", () -> EntityRegistry.PASSENGER_CLASS162_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_METAL_TRAM_COACH =
            rollingStock("passenger_metal_tram_coach", () -> EntityRegistry.PASSENGER_METAL_TRAM_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_B80_C_B =
            rollingStock("passenger_b80_c_b", () -> EntityRegistry.PASSENGER_B80_C_B.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_WOODEN_TRAM_COACH =
            rollingStock("passenger_wooden_tram_coach", () -> EntityRegistry.PASSENGER_WOODEN_TRAM_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_MA100_TAIL =
            rollingStock("passenger_ma100_tail", () -> EntityRegistry.PASSENGER_MA100_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS390_COACH =
            rollingStock("passenger_class390_coach", () -> EntityRegistry.PASSENGER_CLASS390_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS390_PANTO =
            rollingStock("passenger_class390_panto", () -> EntityRegistry.PASSENGER_CLASS390_PANTO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS121_TRAILER =
            rollingStock("passenger_class121_trailer", () -> EntityRegistry.PASSENGER_CLASS121_TRAILER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS117_MIDDLE =
            rollingStock("passenger_class117_middle", () -> EntityRegistry.PASSENGER_CLASS117_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BR_BRAKE_VAN =
            rollingStock("passenger_br_brake_van", () -> EntityRegistry.PASSENGER_BR_BRAKE_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DUEWAG_GT6_ZR_TAIL =
            rollingStock("passenger_duewag_gt6_zr_tail", () -> EntityRegistry.PASSENGER_DUEWAG_GT6_ZR_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_M8_C_TAIL =
            rollingStock("passenger_m8_c_tail", () -> EntityRegistry.PASSENGER_M8_C_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS416_TAIL =
            rollingStock("passenger_class416_tail", () -> EntityRegistry.PASSENGER_CLASS416_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DB420_MIDDLE =
            rollingStock("passenger_db420_middle", () -> EntityRegistry.PASSENGER_DB420_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DB420_TAIL =
            rollingStock("passenger_db420_tail", () -> EntityRegistry.PASSENGER_DB420_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS401_TAIL =
            rollingStock("passenger_class401_tail", () -> EntityRegistry.PASSENGER_CLASS401_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_10TON_BRAKE_VAN =
            rollingStock("passenger_10ton_brake_van", () -> EntityRegistry.PASSENGER_10TON_BRAKE_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS230_MIDDLE =
            rollingStock("passenger_class230_middle", () -> EntityRegistry.PASSENGER_CLASS230_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DUEWAG_GT6_ER_TAIL =
            rollingStock("passenger_duewag_gt6_er_tail", () -> EntityRegistry.PASSENGER_DUEWAG_GT6_ER_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS143_REAR =
            rollingStock("passenger_class143_rear", () -> EntityRegistry.PASSENGER_CLASS143_REAR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS143_MIDDLE =
            rollingStock("passenger_class143_middle", () -> EntityRegistry.PASSENGER_CLASS143_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS374_PREMIER_PANTO =
            rollingStock("passenger_class374_premier_panto", () -> EntityRegistry.PASSENGER_CLASS374_PREMIER_PANTO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS374_STANDARD_PANTO =
            rollingStock("passenger_class374_standard_panto", () -> EntityRegistry.PASSENGER_CLASS374_STANDARD_PANTO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS374_BUFFET =
            rollingStock("passenger_class374_buffet", () -> EntityRegistry.PASSENGER_CLASS374_BUFFET.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS387_COACH =
            rollingStock("passenger_class387_coach", () -> EntityRegistry.PASSENGER_CLASS387_COACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS387_PANTO =
            rollingStock("passenger_class387_panto", () -> EntityRegistry.PASSENGER_CLASS387_PANTO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS387_TAIL =
            rollingStock("passenger_class387_tail", () -> EntityRegistry.PASSENGER_CLASS387_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS378_MIDDLE =
            rollingStock("passenger_class378_middle", () -> EntityRegistry.PASSENGER_CLASS378_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS378_TAIL =
            rollingStock("passenger_class378_tail", () -> EntityRegistry.PASSENGER_CLASS378_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS142_TAIL =
            rollingStock("passenger_class142_tail", () -> EntityRegistry.PASSENGER_CLASS142_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS389_MIDDLE =
            rollingStock("passenger_class389_middle", () -> EntityRegistry.PASSENGER_CLASS389_MIDDLE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS389_TAIL =
            rollingStock("passenger_class389_tail", () -> EntityRegistry.PASSENGER_CLASS389_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS442_TS =
            rollingStock("passenger_class442_ts", () -> EntityRegistry.PASSENGER_CLASS442_TS.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS442_MBLS =
            rollingStock("passenger_class442_mbls", () -> EntityRegistry.PASSENGER_CLASS442_MBLS.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS205TSO =
            rollingStock("passenger_class205tso", () -> EntityRegistry.PASSENGER_CLASS205TSO.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CLASS205_TAIL =
            rollingStock("passenger_class205_tail", () -> EntityRegistry.PASSENGER_CLASS205_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_M8_DNF1_MIDDLELONG =
            rollingStock("passenger_m8_dnf1_middlelong", () -> EntityRegistry.PASSENGER_M8_DNF1_MIDDLELONG.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_M8_DNF1_MIDDLESHORT =
            rollingStock("passenger_m8_dnf1_middleshort", () -> EntityRegistry.PASSENGER_M8_DNF1_MIDDLESHORT.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_M8_DNF1_TAIL =
            rollingStock("passenger_m8_dnf1_tail", () -> EntityRegistry.PASSENGER_M8_DNF1_TAIL.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_RED =
            rollingStock("freight_cart_red", () -> EntityRegistry.FREIGHT_CART_RED.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_WOOD =
            rollingStock("freight_wood", () -> EntityRegistry.FREIGHT_WOOD.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HOPPER =
            rollingStock("freight_hopper", () -> EntityRegistry.FREIGHT_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_K_CLASS_RAIL_BOX =
            rollingStock("freight_k_class_rail_box", () -> EntityRegistry.FREIGHT_K_CLASS_RAIL_BOX.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_SHORT_COVERED_HOPPER =
            rollingStock("freight_short_covered_hopper", () -> EntityRegistry.FREIGHT_SHORT_COVERED_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_LONG_COVERED_HOPPER =
            rollingStock("freight_long_covered_hopper", () -> EntityRegistry.FREIGHT_LONG_COVERED_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_OPEN_WAGON =
            rollingStock("freight_open_wagon", () -> EntityRegistry.FREIGHT_OPEN_WAGON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HOPPER_US =
            rollingStock("freight_hopper_us", () -> EntityRegistry.FREIGHT_HOPPER_US.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_100_TON_HOPPER =
            rollingStock("freight_100_ton_hopper", () -> EntityRegistry.FREIGHT_100_TON_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_FLAT_CART_WOOD_US =
            rollingStock("freight_flat_cart_wood_us", () -> EntityRegistry.FREIGHT_FLAT_CART_WOOD_US.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BULKHEAD_FLAT_CART_WOOD =
            rollingStock("freight_bulkhead_flat_cart_wood", () -> EntityRegistry.FREIGHT_BULKHEAD_FLAT_CART_WOOD.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_US =
            rollingStock("freight_cart_us", () -> EntityRegistry.FREIGHT_CART_US.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BOX_CART_US =
            rollingStock("freight_box_cart_us", () -> EntityRegistry.FREIGHT_BOX_CART_US.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BOX_CART_PRR =
            rollingStock("freight_box_cart_prr", () -> EntityRegistry.FREIGHT_BOX_CART_PRR.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_SMALL =
            rollingStock("freight_cart_small", () -> EntityRegistry.FREIGHT_CART_SMALL.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_MINETRAIN_2 =
            rollingStock("freight_minetrain_2", () -> EntityRegistry.FREIGHT_MINETRAIN_2.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_GTNG =
            rollingStock("freight_gtng", () -> EntityRegistry.FREIGHT_GTNG.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_FLAT_CART_WOOD_LOGS =
            rollingStock("freight_flat_cart_wood_logs", () -> EntityRegistry.FREIGHT_FLAT_CART_WOOD_LOGS.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CLOSED_RED_BROWN =
            rollingStock("freight_closed_red_brown", () -> EntityRegistry.FREIGHT_CLOSED_RED_BROWN.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_OPEN_RED_BROWN =
            rollingStock("freight_open_red_brown", () -> EntityRegistry.FREIGHT_OPEN_RED_BROWN.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_WAGEN_DB =
            rollingStock("freight_wagen_db", () -> EntityRegistry.FREIGHT_WAGEN_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_FLAT_CAR_RAILS_DB =
            rollingStock("freight_flat_car_rails_db", () -> EntityRegistry.FREIGHT_FLAT_CAR_RAILS_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_ASTF_AUTORACK =
            rollingStock("freight_astf_autorack", () -> EntityRegistry.FREIGHT_ASTF_AUTORACK.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_FLAT_CAR_LOGS_DB =
            rollingStock("freight_flat_car_logs_db", () -> EntityRegistry.FREIGHT_FLAT_CAR_LOGS_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_SLATE_WAGON =
            rollingStock("freight_slate_wagon", () -> EntityRegistry.FREIGHT_SLATE_WAGON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_ICE_WAGON =
            rollingStock("freight_ice_wagon", () -> EntityRegistry.FREIGHT_ICE_WAGON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_GS4 =
            rollingStock("freight_cart_gs4", () -> EntityRegistry.FREIGHT_CART_GS4.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_GONDOLA_DB =
            rollingStock("freight_gondola_db", () -> EntityRegistry.FREIGHT_GONDOLA_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CENTER_BEAM_EMPTY =
            rollingStock("freight_center_beam_empty", () -> EntityRegistry.FREIGHT_CENTER_BEAM_EMPTY.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CENTER_BEAM_WOOD1 =
            rollingStock("freight_center_beam_wood1", () -> EntityRegistry.FREIGHT_CENTER_BEAM_WOOD1.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CENTER_BEAM_WOOD2 =
            rollingStock("freight_center_beam_wood2", () -> EntityRegistry.FREIGHT_CENTER_BEAM_WOOD2.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_WELLCAR =
            rollingStock("freight_wellcar", () -> EntityRegistry.FREIGHT_WELLCAR.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_TRAILER =
            rollingStock("freight_trailer", () -> EntityRegistry.FREIGHT_TRAILER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_DENVER_RIO_GRANGE_2 =
            rollingStock("freight_denver_rio_grange_2", () -> EntityRegistry.FREIGHT_DENVER_RIO_GRANGE_2.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_MILW_BAGGAGE =
            rollingStock("freight_milw_baggage", () -> EntityRegistry.FREIGHT_MILW_BAGGAGE.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HEAVYWEIGHT =
            rollingStock("freight_heavyweight", () -> EntityRegistry.FREIGHT_HEAVYWEIGHT.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CART_BAMBOO =
            rollingStock("freight_cart_bamboo", () -> EntityRegistry.FREIGHT_CART_BAMBOO.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_GERMAN_POST =
            rollingStock("freight_german_post", () -> EntityRegistry.FREIGHT_GERMAN_POST.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_DEPRESSED_FLATBED =
            rollingStock("freight_depressed_flatbed", () -> EntityRegistry.FREIGHT_DEPRESSED_FLATBED.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_CAR_L =
            rollingStock("freight_car_l", () -> EntityRegistry.FREIGHT_CAR_L.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HEAVYWEIGHT_2 =
            rollingStock("freight_heavyweight_2", () -> EntityRegistry.FREIGHT_HEAVYWEIGHT_2.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_ROUND_HOPPER =
            rollingStock("freight_round_hopper", () -> EntityRegistry.FREIGHT_ROUND_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_RIBBED_HOPPER =
            rollingStock("freight_ribbed_hopper", () -> EntityRegistry.FREIGHT_RIBBED_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP40HIGHCUBE =
            rollingStock("freight_bap40highcube", () -> EntityRegistry.FREIGHT_BAP40HIGHCUBE.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_WOODCHIP_HOPPER =
            rollingStock("freight_bap_woodchip_hopper", () -> EntityRegistry.FREIGHT_BAP_WOODCHIP_HOPPER.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_ORE_JENNY =
            rollingStock("freight_bap_ore_jenny", () -> EntityRegistry.FREIGHT_BAP_ORE_JENNY.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_MILL_GONDOLA =
            rollingStock("freight_bap_mill_gondola", () -> EntityRegistry.FREIGHT_BAP_MILL_GONDOLA.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_MILW40BOXCAR =
            rollingStock("freight_bap_milw40boxcar", () -> EntityRegistry.FREIGHT_BAP_MILW40BOXCAR.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP60CENTERBEAM =
            rollingStock("freight_bap60centerbeam", () -> EntityRegistry.FREIGHT_BAP60CENTERBEAM.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP66CENTERBEAM =
            rollingStock("freight_bap66centerbeam", () -> EntityRegistry.FREIGHT_BAP66CENTERBEAM.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP73CENTERBEAM =
            rollingStock("freight_bap73centerbeam", () -> EntityRegistry.FREIGHT_BAP73CENTERBEAM.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_PS140 =
            rollingStock("freight_bap_ps140", () -> EntityRegistry.FREIGHT_BAP_PS140.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_PS150 =
            rollingStock("freight_bap_ps150", () -> EntityRegistry.FREIGHT_BAP_PS150.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_PS160 =
            rollingStock("freight_bap_ps160", () -> EntityRegistry.FREIGHT_BAP_PS160.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_VERSA_LONGI =
            rollingStock("freight_bap_versa_longi", () -> EntityRegistry.FREIGHT_BAP_VERSA_LONGI.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BAP_VERSA_TRANS =
            rollingStock("freight_bap_versa_trans", () -> EntityRegistry.FREIGHT_BAP_VERSA_TRANS.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HICUBE60FOOT =
            rollingStock("freight_hicube60foot", () -> EntityRegistry.FREIGHT_HICUBE60FOOT.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BNSF_GON =
            rollingStock("freight_bnsf_gon", () -> EntityRegistry.FREIGHT_BNSF_GON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HOPPER5201 =
            rollingStock("freight_hopper5201", () -> EntityRegistry.FREIGHT_HOPPER5201.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HOPPER6260 =
            rollingStock("freight_hopper6260", () -> EntityRegistry.FREIGHT_HOPPER6260.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_SKELETON =
            rollingStock("freight_skeleton", () -> EntityRegistry.FREIGHT_SKELETON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_PS73_BAGGAGE =
            rollingStock("freight_ps73_baggage", () -> EntityRegistry.FREIGHT_PS73_BAGGAGE.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_PS85_BAGGAGE =
            rollingStock("freight_ps85_baggage", () -> EntityRegistry.FREIGHT_PS85_BAGGAGE.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_REEFER64 =
            rollingStock("freight_reefer64", () -> EntityRegistry.FREIGHT_REEFER64.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_PSRPOPM =
            rollingStock("freight_psrpopm", () -> EntityRegistry.FREIGHT_PSRPOPM.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_PSRPO =
            rollingStock("freight_psrpo", () -> EntityRegistry.FREIGHT_PSRPO.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BOULDER_WAGON =
            rollingStock("freight_boulder_wagon", () -> EntityRegistry.FREIGHT_BOULDER_WAGON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_GSI60_FOOT_BULKHEAD =
            rollingStock("freight_gsi60_foot_bulkhead", () -> EntityRegistry.FREIGHT_GSI60_FOOT_BULKHEAD.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_GSC60_FOOT_FLATCAR =
            rollingStock("freight_gsc60_foot_flatcar", () -> EntityRegistry.FREIGHT_GSC60_FOOT_FLATCAR.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_5_PLANK =
            rollingStock("freight_5_plank", () -> EntityRegistry.FREIGHT_5_PLANK.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_BR_MK1_TPO_STOWAGE =
            rollingStock("freight_br_mk1_tpo_stowage", () -> EntityRegistry.FREIGHT_BR_MK1_TPO_STOWAGE.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_ACFGNRPO_30 =
            rollingStock("freight_acfgnrpo_30", () -> EntityRegistry.FREIGHT_ACFGNRPO_30.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_HOPPER_UK =
            rollingStock("freight_hopper_uk", () -> EntityRegistry.FREIGHT_HOPPER_UK.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_EXPRESS_FREIGHT_VAN =
            rollingStock("freight_express_freight_van", () -> EntityRegistry.FREIGHT_EXPRESS_FREIGHT_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_TIPPER_UK =
            rollingStock("freight_tipper_uk", () -> EntityRegistry.FREIGHT_TIPPER_UK.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_MINERALWAGON =
            rollingStock("freight_mineralwagon", () -> EntityRegistry.FREIGHT_MINERALWAGON.get());

    public static final DeferredHolder<Item, RollingStockItem> FREIGHT_VENTILATED_VAN =
            rollingStock("freight_ventilated_van", () -> EntityRegistry.FREIGHT_VENTILATED_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_WAGON_DB =
            rollingStock("tank_tank_wagon_db", () -> EntityRegistry.TANK_TANK_WAGON_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_THREE_DOME =
            rollingStock("tank_tank_three_dome", () -> EntityRegistry.TANK_TANK_THREE_DOME.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_WAGON_US =
            rollingStock("tank_tank_wagon_us", () -> EntityRegistry.TANK_TANK_WAGON_US.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_WAGON_GREY =
            rollingStock("tank_tank_wagon_grey", () -> EntityRegistry.TANK_TANK_WAGON_GREY.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_CART_LAVA =
            rollingStock("tank_tank_cart_lava", () -> EntityRegistry.TANK_TANK_CART_LAVA.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_WAGON_YELLOW =
            rollingStock("tank_tank_wagon_yellow", () -> EntityRegistry.TANK_TANK_WAGON_YELLOW.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_BAP_DOT11111000 =
            rollingStock("tank_bap_dot11111000", () -> EntityRegistry.TANK_BAP_DOT11111000.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_BAP_DOT11120600 =
            rollingStock("tank_bap_dot11120600", () -> EntityRegistry.TANK_BAP_DOT11120600.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_BAP_DOT11129080 =
            rollingStock("tank_bap_dot11129080", () -> EntityRegistry.TANK_BAP_DOT11129080.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_TANK_TANKER_UK =
            rollingStock("tank_tank_tanker_uk", () -> EntityRegistry.TANK_TANK_TANKER_UK.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_RHEINGOLD_DINING1 =
            rollingStock("work_rheingold_dining1", () -> EntityRegistry.WORK_RHEINGOLD_DINING1.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_RHEINGOLD_DINING2 =
            rollingStock("work_rheingold_dining2", () -> EntityRegistry.WORK_RHEINGOLD_DINING2.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_GWR_BRAKE_VAN =
            rollingStock("work_gwr_brake_van", () -> EntityRegistry.WORK_GWR_BRAKE_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_WORK_CART =
            rollingStock("work_work_cart", () -> EntityRegistry.WORK_WORK_CART.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_WORK_CABOOSE =
            rollingStock("work_work_caboose", () -> EntityRegistry.WORK_WORK_CABOOSE.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_CABOOSE_LOGGING =
            rollingStock("work_caboose_logging", () -> EntityRegistry.WORK_CABOOSE_LOGGING.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_CABOOSE_LOGGING_PRR =
            rollingStock("work_caboose_logging_prr", () -> EntityRegistry.WORK_CABOOSE_LOGGING_PRR.get());

    public static final DeferredHolder<Item, RollingStockItem> WORK_MAIL_WAGEN_DB =
            rollingStock("work_mail_wagen_db", () -> EntityRegistry.WORK_MAIL_WAGEN_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_STOCK_CAR =
            rollingStock("passenger_stock_car", () -> EntityRegistry.PASSENGER_STOCK_CAR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DRWG_STOCK_CAR =
            rollingStock("passenger_drwg_stock_car", () -> EntityRegistry.PASSENGER_DRWG_STOCK_CAR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_JUKE_BOX_CART =
            rollingStock("passenger_juke_box_cart", () -> EntityRegistry.PASSENGER_JUKE_BOX_CART.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_TRACKS_BUILDER =
            rollingStock("passenger_tracks_builder", () -> EntityRegistry.PASSENGER_TRACKS_BUILDER.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CATTLE_VAN =
            rollingStock("passenger_cattle_van", () -> EntityRegistry.PASSENGER_CATTLE_VAN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CABOOSE_RED =
            rollingStock("passenger_caboose_red", () -> EntityRegistry.PASSENGER_CABOOSE_RED.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CABOOSE_BLACK =
            rollingStock("passenger_caboose_black", () -> EntityRegistry.PASSENGER_CABOOSE_BLACK.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BAP_W_VCABOOSE =
            rollingStock("passenger_bap_w_vcaboose", () -> EntityRegistry.PASSENGER_BAP_W_VCABOOSE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_DRGW_CABOOSE =
            rollingStock("passenger_drgw_caboose", () -> EntityRegistry.PASSENGER_DRGW_CABOOSE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FLAT_CART =
            rollingStock("passenger_flat_cart", () -> EntityRegistry.PASSENGER_FLAT_CART.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FLAT_CART_SU =
            rollingStock("passenger_flat_cart_su", () -> EntityRegistry.PASSENGER_FLAT_CART_SU.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FLAT_CART_US =
            rollingStock("passenger_flat_cart_us", () -> EntityRegistry.PASSENGER_FLAT_CART_US.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_FLAT_CAR_DB =
            rollingStock("passenger_flat_car_db", () -> EntityRegistry.PASSENGER_FLAT_CAR_DB.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PROPAGANDA_US =
            rollingStock("passenger_propaganda_us", () -> EntityRegistry.PASSENGER_PROPAGANDA_US.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PROPAGANDA_USSR =
            rollingStock("passenger_propaganda_ussr", () -> EntityRegistry.PASSENGER_PROPAGANDA_USSR.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PROPAGANDA_JAPAN =
            rollingStock("passenger_propaganda_japan", () -> EntityRegistry.PASSENGER_PROPAGANDA_JAPAN.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PROPAGANDA_BRITAIN =
            rollingStock("passenger_propaganda_britain", () -> EntityRegistry.PASSENGER_PROPAGANDA_BRITAIN.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_B_UNIT_EMDF7 =
            rollingStock("tank_b_unit_emdf7", () -> EntityRegistry.TANK_B_UNIT_EMDF7.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_B_UNIT_EMDF3 =
            rollingStock("tank_b_unit_emdf3", () -> EntityRegistry.TANK_B_UNIT_EMDF3.get());

    public static final DeferredHolder<Item, RollingStockItem> TANK_B_UNIT_DD35 =
            rollingStock("tank_b_unit_dd35", () -> EntityRegistry.TANK_B_UNIT_DD35.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BAP_P_ECOACH =
            rollingStock("passenger_bap_p_ecoach", () -> EntityRegistry.PASSENGER_BAP_P_ECOACH.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_BAP_P_EOBSERVE =
            rollingStock("passenger_bap_p_eobserve", () -> EntityRegistry.PASSENGER_BAP_P_EOBSERVE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_PS_COMBINE =
            rollingStock("passenger_ps_combine", () -> EntityRegistry.PASSENGER_PS_COMBINE.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_LIGHT_CRANE =
            rollingStock("passenger_light_crane", () -> EntityRegistry.PASSENGER_LIGHT_CRANE.get());

    public static final DeferredHolder<Item, RollingStockItem> LOCO_DIESEL_NRE3GS21B =
            rollingStock("loco_diesel_nre3gs21b", () -> EntityRegistry.LOCO_DIESEL_NRE3GS21B.get());

    public static final DeferredHolder<Item, RollingStockItem> PASSENGER_CQ310_PASSENGER =
            rollingStock("passenger_cq310_passenger", () -> EntityRegistry.PASSENGER_CQ310_PASSENGER.get());

    private static DeferredHolder<Item, RollingStockItem> rollingStock(
            String name, Supplier<EntityType<? extends RollingStockEntity>> type) {
        return ITEMS.register(
                name,
                id ->
                        new RollingStockItem(
                                new Item.Properties()
                                        .stacksTo(1)
                                        .setId(ResourceKey.create(Registries.ITEM, id)),
                                type));
    }

    private ItemRegistry() {}
}
