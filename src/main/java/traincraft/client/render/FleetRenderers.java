package traincraft.client.render;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import traincraft.bootstrap.EntityRegistry;

import java.util.List;
import java.util.Map;

public final class FleetRenderers {

    private FleetRenderers() {}

    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_A4.get(),
                context -> new RollingStockRenderer<>(context, "a4", RollingStockRenderer.liveried("locoa4_uk_"),
                        new float[] {-3.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.975F, 0.975F, 0.975F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_HALL_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "hall_class", RollingStockRenderer.liveried("hall_class_locomotive_"),
                        new float[] {-0.65F, 0.15F, -0.25F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_BERK_1225.get(),
                context -> new RollingStockRenderer<>(context, "berk_1225", RollingStockRenderer.ported("berk1225_locomotive"),
                        new float[] {-2.0F, -0.65F, 0.0625F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_BERK_765.get(),
                context -> new RollingStockRenderer<>(context, "berk_765", RollingStockRenderer.ported("berk765_locomotive"),
                        new float[] {-2.0F, -0.65F, 0.0625F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_FOWLER.get(),
                context -> new RollingStockRenderer<>(context, "fowler", RollingStockRenderer.ported("loco_fowler"),
                        new float[] {-3.0F, -0.51585F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_KING_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "king_class", RollingStockRenderer.liveried("king_class_locomotive_"),
                        new float[] {-1.75F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_MILW_CLASS_A.get(),
                context -> new RollingStockRenderer<>(context, "milw_class_a", RollingStockRenderer.ported("locomilw_classa"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_CHEREPANOV.get(),
                context -> new RollingStockRenderer<>(context, "cherepanov", RollingStockRenderer.ported("lococherepanov"),
                        new float[] {-0.875F, -0.47F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_4_4_0.get(),
                context -> new RollingStockRenderer<>(context, "4_4_0", RollingStockRenderer.liveried("4-4-0-loco_"),
                        new float[] {-0.66F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SMALL.get(),
                context -> new RollingStockRenderer<>(context, "small", RollingStockRenderer.liveried("loco3_"),
                        new float[] {-0.99F, -0.5F, 0.0F}, new float[] {0.0F, -90.0F, 0.0F}, new float[] {0.8F, 0.8F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_LSSP7.get(),
                context -> new RollingStockRenderer<>(context, "lssp7", RollingStockRenderer.ported("lssp7"),
                        new float[] {-1.0F, -0.2F, 0.8F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_HEAVY.get(),
                context -> new RollingStockRenderer<>(context, "heavy", RollingStockRenderer.liveried("heavysteam_"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_C62.get(),
                context -> new RollingStockRenderer<>(context, "c62", RollingStockRenderer.liveried("c62_engine_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("c62_front_bogie", RollingStockRenderer.texture("c62_front_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -5.75F, -0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("c62_rear_bogie", RollingStockRenderer.texture("c62_back_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("c62_front_bogie", RollingStockRenderer.texture("c62_front_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -5.75F, -0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("c62_rear_bogie", RollingStockRenderer.texture("c62_back_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_D51_SHORT.get(),
                context -> new RollingStockRenderer<>(context, "d51_short", RollingStockRenderer.liveried("d51_short_"),
                        new float[] {0.0F, -0.5F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("d51_front_bogie", RollingStockRenderer.texture("d51_front_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("d51_rear_bogie", RollingStockRenderer.texture("d51_rear_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_D51_LONG.get(),
                context -> new RollingStockRenderer<>(context, "d51_long", RollingStockRenderer.liveried("d51_long_"),
                        new float[] {0.0F, -0.5F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("d51_front_bogie", RollingStockRenderer.texture("d51_front_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("d51_rear_bogie", RollingStockRenderer.texture("d51_rear_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_BR01.get(),
                context -> new RollingStockRenderer<>(context, "br01", RollingStockRenderer.winter("locobr01_db_winter", RollingStockRenderer.liveried("locobr01_db_")),
                        new float[] {-1.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), new float[] {-3.08F, 1.3F, 0.0F}));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_CORANATION_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "coranation_class", RollingStockRenderer.ported("coranationclass"),
                        new float[] {-3.8F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GS4.get(),
                context -> new RollingStockRenderer<>(context, "gs4", RollingStockRenderer.liveried("gs4_engine_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.95F, 0.95F, 0.95F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_ER_USSR.get(),
                context -> new RollingStockRenderer<>(context, "er_ussr", RollingStockRenderer.ported("locoer_ussr"),
                        new float[] {-0.75F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), new float[] {-3.08F, 1.3F, 0.0F}));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_C41.get(),
                context -> new RollingStockRenderer<>(context, "c41", RollingStockRenderer.ported("lococ41"),
                        new float[] {-3.5F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_C41_080.get(),
                context -> new RollingStockRenderer<>(context, "c41_080", RollingStockRenderer.ported("lococ41_080"),
                        new float[] {-3.5F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_ALCO_SC4.get(),
                context -> new RollingStockRenderer<>(context, "alco_sc4", RollingStockRenderer.ported("alcosc4"),
                        new float[] {-3.5F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SOUTHERN_1102.get(),
                context -> new RollingStockRenderer<>(context, "southern_1102", RollingStockRenderer.ported("southern1102"),
                        new float[] {-3.5F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_USATC_US.get(),
                context -> new RollingStockRenderer<>(context, "usatc_us", RollingStockRenderer.ported("usatcus"),
                        new float[] {-2.0F, -0.2F, 0.8375F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_USATC_UK.get(),
                context -> new RollingStockRenderer<>(context, "usatc_uk", RollingStockRenderer.ported("usatcuk"),
                        new float[] {-2.0F, -0.2F, 0.8375F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_C41T.get(),
                context -> new RollingStockRenderer<>(context, "c41t", RollingStockRenderer.ported("lococ41t"),
                        new float[] {-3.5F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_FORNEY.get(),
                context -> new RollingStockRenderer<>(context, "forney", RollingStockRenderer.liveried("locoforney_"),
                        new float[] {-1.3F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_ASTERI.get(),
                context -> new RollingStockRenderer<>(context, "asteri", RollingStockRenderer.ported("asteri"),
                        new float[] {-1.0F, 0.16F, 0.0F}, new float[] {5.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_MOGUL.get(),
                context -> new RollingStockRenderer<>(context, "mogul", RollingStockRenderer.liveried("locomogul_"),
                        new float[] {-0.6F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SHAY.get(),
                context -> new RollingStockRenderer<>(context, "shay", RollingStockRenderer.ported("locoshay"),
                        new float[] {-0.4F, -0.45F, -0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_VB_SHAY.get(),
                context -> new RollingStockRenderer<>(context, "vb_shay", RollingStockRenderer.ported("vbshay"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_CLIMAX.get(),
                context -> new RollingStockRenderer<>(context, "climax", RollingStockRenderer.ported("loco_climax"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_PANNIER.get(),
                context -> new RollingStockRenderer<>(context, "pannier", RollingStockRenderer.ported("locopannier"),
                        new float[] {0.15F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GLYN.get(),
                context -> new RollingStockRenderer<>(context, "glyn", RollingStockRenderer.ported("0-4-2-loco-glyn"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_262T.get(),
                context -> new RollingStockRenderer<>(context, "262t", RollingStockRenderer.ported("262t"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_040VB.get(),
                context -> new RollingStockRenderer<>(context, "040vb", RollingStockRenderer.ported("loco040vb"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_ADLER.get(),
                context -> new RollingStockRenderer<>(context, "adler", RollingStockRenderer.ported("locoadler"),
                        new float[] {-0.8F, 1.05F, 0.0F}, new float[] {180.0F, -90.0F, 0.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_VB_SHAY_2TRUCK.get(),
                context -> new RollingStockRenderer<>(context, "vb_shay_2truck", RollingStockRenderer.ported("vbshay2"),
                        new float[] {-0.4F, -1.15F, -0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_CLIMAX_2TRUCK.get(),
                context -> new RollingStockRenderer<>(context, "climax_2truck", RollingStockRenderer.ported("climax_grey"),
                        new float[] {-0.4F, -1.15F, -0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_C11.get(),
                context -> new RollingStockRenderer<>(context, "c11", RollingStockRenderer.ported("lococ11"),
                        new float[] {-1.5F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("loco_c11truck_front", RollingStockRenderer.texture("lococ11truckfront"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("loco_c11truck_rear", RollingStockRenderer.texture("lococ11truckrear"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_STAR_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "star_class", RollingStockRenderer.ported("starloco"),
                        new float[] {-0.8F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_ONION.get(),
                context -> new RollingStockRenderer<>(context, "onion", RollingStockRenderer.liveried("onion_"),
                        new float[] {-0.7F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_POLAR_EXPRESS.get(),
                context -> new RollingStockRenderer<>(context, "polar_express", RollingStockRenderer.ported("peloco"),
                        new float[] {-1.5F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("pe_loco_front_truck", RollingStockRenderer.texture("peleading"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("pe_loco_rear_truck", RollingStockRenderer.texture("petrailing"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GARRATT_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "garratt_front", RollingStockRenderer.ported("garratttexture"),
                        new float[] {3.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GARRATT_REAR.get(),
                context -> new RollingStockRenderer<>(context, "garratt_rear", RollingStockRenderer.ported("garratttexture"),
                        new float[] {3.25F, 0.155F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GARRATT_MID.get(),
                context -> new RollingStockRenderer<>(context, "garratt_mid", RollingStockRenderer.ported("garratttexture"),
                        new float[] {-2.25F, 0.155F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SKOOKUM.get(),
                context -> new RollingStockRenderer<>(context, "skookum", RollingStockRenderer.ported("skookum"),
                        new float[] {-1.15F, 0.15F, 0.07F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SHAY_3TRUCK.get(),
                context -> new RollingStockRenderer<>(context, "shay_3truck", RollingStockRenderer.liveried("3truckshay_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_BR_BLACK_5.get(),
                context -> new RollingStockRenderer<>(context, "br_black_5", RollingStockRenderer.liveried("br_black_5_"),
                        new float[] {-1.75F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_RW_TYPE_3.get(),
                context -> new RollingStockRenderer<>(context, "rw_type_3", RollingStockRenderer.liveried("rw_type_3_"),
                        new float[] {-1.25F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_RW_TYPE_2.get(),
                context -> new RollingStockRenderer<>(context, "rw_type_2", RollingStockRenderer.liveried("type_2_"),
                        new float[] {-1.25F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GWR_42XX.get(),
                context -> new RollingStockRenderer<>(context, "gwr_42xx", RollingStockRenderer.liveried("2-8-0_"),
                        new float[] {-2.1F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GWR_72XX.get(),
                context -> new RollingStockRenderer<>(context, "gwr_72xx", RollingStockRenderer.liveried("72xx_"),
                        new float[] {-2.5F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_GWR_101_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "gwr_101_class", RollingStockRenderer.liveried("gwr_101_"),
                        new float[] {-1.25F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_WWCP_062T.get(),
                context -> new RollingStockRenderer<>(context, "wwcp_062t", RollingStockRenderer.liveried("wwcp_standard_0-6-2t_"),
                        new float[] {-2.5F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_BR_BRITANNIA.get(),
                context -> new RollingStockRenderer<>(context, "br_britannia", RollingStockRenderer.liveried("br_britannia_"),
                        new float[] {-2.3F, -0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_J50.get(),
                context -> new RollingStockRenderer<>(context, "j50", RollingStockRenderer.liveried("j50_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SENTINEL_Y3.get(),
                context -> new RollingStockRenderer<>(context, "sentinel_y3", RollingStockRenderer.liveried("sentinel_y3_"),
                        new float[] {-0.5F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_MW_CLASS_88.get(),
                context -> new RollingStockRenderer<>(context, "mw_class_88", RollingStockRenderer.liveried("0-8-0_box_tank_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_MR_COMPOUND.get(),
                context -> new RollingStockRenderer<>(context, "mr_compound", RollingStockRenderer.liveried("lms_4p_"),
                        new float[] {-1.95F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_SMALL.get(),
                context -> new RollingStockRenderer<>(context, "tender_small", RollingStockRenderer.liveried("tender2_"),
                        new float[] {0.0F, -0.5F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {0.75F, 0.75F, 0.75F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_HEAVY.get(),
                context -> new RollingStockRenderer<>(context, "tender_heavy", RollingStockRenderer.liveried("heavytender_"),
                        new float[] {0.0F, -0.4F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_GS4.get(),
                context -> new RollingStockRenderer<>(context, "tender_gs4", RollingStockRenderer.liveried("gs4_tender_"),
                        new float[] {0.0F, 0.15F, -0.05F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("gs4_tender_bogie", RollingStockRenderer.texture("gs4_tender_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, 0.0F, -0.1F, 1.0F)), new RollingStockRenderer.Attachment("gs4_tender_bogie", RollingStockRenderer.texture("gs4_tender_bogie"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.7F, 0.0F, -0.1F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_GWR_4000_GALLON.get(),
                context -> new RollingStockRenderer<>(context, "tender_gwr_4000_gallon", RollingStockRenderer.liveried("gwr_tender_"),
                        new float[] {-4.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_FOWLER.get(),
                context -> new RollingStockRenderer<>(context, "tender_fowler", RollingStockRenderer.ported("fowler_4f_tender"),
                        new float[] {-5.25F, -0.51585F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_BERK_1225.get(),
                context -> new RollingStockRenderer<>(context, "tender_berk_1225", RollingStockRenderer.liveried("berkshire_tender_"),
                        new float[] {2.75F, -0.65F, 0.0625F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_4_4_0.get(),
                context -> new RollingStockRenderer<>(context, "tender_4_4_0", RollingStockRenderer.liveried("4-4-0-loco_tender_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_A4.get(),
                context -> new RollingStockRenderer<>(context, "tender_a4", RollingStockRenderer.liveried("tendera4_uk_"),
                        new float[] {-0.2F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_BR01.get(),
                context -> new RollingStockRenderer<>(context, "tender_br01", RollingStockRenderer.ported("tenderbr01_db"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_CORANATION_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "tender_coranation_class", RollingStockRenderer.ported("coranationclasstender"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_ER_USSR.get(),
                context -> new RollingStockRenderer<>(context, "tender_er_ussr", RollingStockRenderer.ported("tenderer_ussr"),
                        new float[] {0.06F, -0.44F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_C62.get(),
                context -> new RollingStockRenderer<>(context, "tender_c62", RollingStockRenderer.liveried("c62_tender_"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.25F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.1F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.25F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.1F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_D51.get(),
                context -> new RollingStockRenderer<>(context, "tender_d51", RollingStockRenderer.ported("d51_tender"),
                        new float[] {-3.0F, 0.35F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 5.25F, 0.8F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("c62_tender_bogie", RollingStockRenderer.texture("c62_tender_bogie_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 3.25F, 0.8F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_ADLER.get(),
                context -> new RollingStockRenderer<>(context, "tender_adler", RollingStockRenderer.ported("tender_adler"),
                        new float[] {0.0F, 1.05F, 0.0F}, new float[] {180.0F, -90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_C41.get(),
                context -> new RollingStockRenderer<>(context, "tender_c41", RollingStockRenderer.ported("c41tender"),
                        new float[] {0.1F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_SOUTHERN_1102.get(),
                context -> new RollingStockRenderer<>(context, "tender_southern_1102", RollingStockRenderer.ported("southern1102tender"),
                        new float[] {-5.25F, -0.5F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_MILW.get(),
                context -> new RollingStockRenderer<>(context, "tender_milw", RollingStockRenderer.ported("milw_tender"),
                        new float[] {0.15F, 0.055F, 0.025F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_BR_BLACK_5.get(),
                context -> new RollingStockRenderer<>(context, "tender_br_black_5", RollingStockRenderer.liveried("br_black_5_tender_"),
                        new float[] {-0.05F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_BR1.get(),
                context -> new RollingStockRenderer<>(context, "tender_br1", RollingStockRenderer.liveried("br1_tender_"),
                        new float[] {0.0F, -0.01F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_RW_TYPE_2.get(),
                context -> new RollingStockRenderer<>(context, "tender_rw_type_2", RollingStockRenderer.liveried("type_2_"),
                        new float[] {0.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_STAR_CLASS.get(),
                context -> new RollingStockRenderer<>(context, "tender_star_class", RollingStockRenderer.ported("startender"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_ONION.get(),
                context -> new RollingStockRenderer<>(context, "tender_onion", RollingStockRenderer.liveried("onion_tender_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_POLAR_EXPRESS.get(),
                context -> new RollingStockRenderer<>(context, "tender_polar_express", RollingStockRenderer.ported("petender"),
                        new float[] {0.1F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("buckeye3axletruck", RollingStockRenderer.texture("buckeye_3axle_black_friccbearing"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("buckeye3axletruck", RollingStockRenderer.texture("buckeye_3axle_black_friccbearing"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_SKOOKUM.get(),
                context -> new RollingStockRenderer<>(context, "tender_skookum", RollingStockRenderer.ported("skookumtender"),
                        new float[] {0.1F, 0.08F, 0.13F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("skookum_tender_trucc", RollingStockRenderer.texture("skookumtendertrucc_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -0.85F, -0.05F, 0.135F, 1.0F)), new RollingStockRenderer.Attachment("skookum_tender_trucc", RollingStockRenderer.texture("skookumtendertrucc_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.73F, -0.05F, 0.135F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_SHAY_3TRUCK.get(),
                context -> new RollingStockRenderer<>(context, "tender_shay_3truck", RollingStockRenderer.liveried("3truckshay_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_SHUNTING_UK.get(),
                context -> new RollingStockRenderer<>(context, "tender_shunting_uk", RollingStockRenderer.liveried("shunting_tender_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TENDER_MR_COMPOUND.get(),
                context -> new RollingStockRenderer<>(context, "tender_mr_compound", RollingStockRenderer.liveried("lms_4p_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_STEAM_SNOW_PLOW.get(),
                context -> new RollingStockRenderer<>(context, "snow_plow", RollingStockRenderer.ported("train_snowplow"),
                        new float[] {-2.0F, 0.12F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 1.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -1.5F, 0.6F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 1.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, 1.5F, 0.6F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_KOF.get(),
                context -> new RollingStockRenderer<>(context, "kof", RollingStockRenderer.liveried("locokof_db_"),
                        new float[] {-1.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP40.get(),
                context -> new RollingStockRenderer<>(context, "gp40", RollingStockRenderer.liveried("cd742_"),
                        new float[] {-0.8F, 0.75F, 0.0F}, new float[] {180.0F, 180.0F, 0.0F}, new float[] {0.8F, 0.8F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CHME3.get(),
                context -> new RollingStockRenderer<>(context, "chme3", RollingStockRenderer.ported("chme3"),
                        new float[] {-0.5F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP7_RED.get(),
                context -> new RollingStockRenderer<>(context, "gp7_red", RollingStockRenderer.liveried("gp7_"),
                        new float[] {-0.8F, -0.47F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SHUNTER.get(),
                context -> new RollingStockRenderer<>(context, "shunter", RollingStockRenderer.liveried("shunter_"),
                        new float[] {-1.2F, -0.451F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_IC4_DSB_MG.get(),
                context -> new RollingStockRenderer<>(context, null, RollingStockRenderer.liveried("ic4_dsb_mg_"),
                        new float[] {-0.8F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("ic4_dsb_mg_part1", RollingStockRenderer.texture("ic4_dsb_mg_red"), RollingStockRenderer.transform(0.0F, 0.0F, 0.7F, 0.0F, 0.0F, -0.9F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -6.0F, 1.4F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ic4_dsb_mg_part1", RollingStockRenderer.texture("ic4_dsb_mg_white"), RollingStockRenderer.transform(0.0F, 0.0F, 0.7F, 0.0F, 0.0F, -0.9F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -6.0F, 1.4F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_MILW_H1044.get(),
                context -> new RollingStockRenderer<>(context, null, RollingStockRenderer.liveried("milw_h1044_"),
                        new float[] {0.0F, 0.06F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_yellow"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("Black", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_black"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_red"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_blue"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_grey"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("LightBlue", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_lightblue"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), Map.entry("Green", List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_green"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("milw_h1044_part1", RollingStockRenderer.texture("milw_h1044_orange"), RollingStockRenderer.transform(1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -4.3F, 0.55F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("milw_h1044_bogie", RollingStockRenderer.texture("milw_h1044_bogie"), RollingStockRenderer.transform(1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 1.45F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, -0.65F, 0.55F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDF7.get(),
                context -> new RollingStockRenderer<>(context, "emdf7", RollingStockRenderer.liveried("emdf7_"),
                        new float[] {-2.2F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 2.9F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -0.4F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDF3.get(),
                context -> new RollingStockRenderer<>(context, "emdf3", RollingStockRenderer.liveried("emdf3_"),
                        new float[] {-2.2F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 2.9F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -0.4F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EWS_CLASS66.get(),
                context -> new RollingStockRenderer<>(context, "ews_class66", RollingStockRenderer.liveried("class66_"),
                        new float[] {-3.0F, 0.65F, 0.0F}, new float[] {0.0F, 90.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ewsclass66_bogie", RollingStockRenderer.texture("class66bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, 0.0F, 0.3F, -2.6F, 1.0F)), new RollingStockRenderer.Attachment("ewsclass66_bogie", RollingStockRenderer.texture("class66bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.0F, 0.0F, 0.3F, 2.1F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DELTIC.get(),
                context -> new RollingStockRenderer<>(context, "deltic", RollingStockRenderer.ported("deltic"),
                        new float[] {-2.25F, 0.8F, 0.0F}, new float[] {0.0F, 90.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("deltic_bogie", RollingStockRenderer.texture("deltic_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.06F, 0.25F, -2.9F, 1.0F)), new RollingStockRenderer.Attachment("deltic_bogie", RollingStockRenderer.texture("deltic_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.06F, 0.25F, 1.9F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DD35A.get(),
                context -> new RollingStockRenderer<>(context, "dd35a", RollingStockRenderer.liveried("dd35a_"),
                        new float[] {-5.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_44_TON_SWITCHER.get(),
                context -> new RollingStockRenderer<>(context, "44_ton_switcher", RollingStockRenderer.liveried("loco_44tonswitcher_"),
                        new float[] {-2.75F, -0.425F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAMBOO.get(),
                context -> new RollingStockRenderer<>(context, "bamboo", RollingStockRenderer.liveried("loco_bamboo_"),
                        new float[] {-1.0F, 0.0F, 0.0F}, new float[] {180.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_WLS40.get(),
                context -> new RollingStockRenderer<>(context, "wls40", RollingStockRenderer.ported("wls40"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_FOL_M1.get(),
                context -> new RollingStockRenderer<>(context, "fol_m1", RollingStockRenderer.liveried("fol_m1_"),
                        new float[] {-2.8F, 0.05F, 0.9F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.2F, 0.0F, 1.0F, 1.0F)), new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 3.45F, 0.0F, 1.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.2F, 0.0F, 1.0F, 1.0F)), new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 3.45F, 0.0F, 1.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_FOL_M1B.get(),
                context -> new RollingStockRenderer<>(context, "fol_m1b", RollingStockRenderer.liveried("fol_m1b_"),
                        new float[] {-2.2F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.725F, 0.0F, 0.11F, 1.0F)), new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.7F, 0.0F, 0.11F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.725F, 0.0F, 0.11F, 1.0F)), new RollingStockRenderer.Attachment("fol_m1_bogie", RollingStockRenderer.texture("fol_m1_bogie_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.7F, 0.0F, 0.11F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CF7.get(),
                context -> new RollingStockRenderer<>(context, "cf7", RollingStockRenderer.liveried("cf7_"),
                        new float[] {-1.5F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("blomberg_b", RollingStockRenderer.texture("classic_blomberg_b_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_b", RollingStockRenderer.texture("classic_blomberg_b_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP15.get(),
                context -> new RollingStockRenderer<>(context, "gp15", RollingStockRenderer.liveried("gp15_"),
                        new float[] {-1.3F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("blomberg_b", RollingStockRenderer.texture("classic_blomberg_b_harbor_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_b", RollingStockRenderer.texture("classic_blomberg_b_harbor_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.07F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SW8.get(),
                context -> new RollingStockRenderer<>(context, "sw8", RollingStockRenderer.liveried("sw8_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CD814.get(),
                context -> new RollingStockRenderer<>(context, "cd814", RollingStockRenderer.liveried("cd814_"),
                        new float[] {-0.8F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CD810.get(),
                context -> new RollingStockRenderer<>(context, "cd810", RollingStockRenderer.liveried("cd810_"),
                        new float[] {-0.8F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SM42.get(),
                context -> new RollingStockRenderer<>(context, "sm42", RollingStockRenderer.liveried("sm42_"),
                        new float[] {-2.0F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("sm42_bogie", RollingStockRenderer.texture("sm42_bogies"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 2.2F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("sm42_bogie", RollingStockRenderer.texture("sm42_bogies"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.1F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GE44TON.get(),
                context -> new RollingStockRenderer<>(context, "ge44ton", RollingStockRenderer.liveried("44_ton_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("getonner_truck", RollingStockRenderer.texture("tonnertruck_black_friction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.95F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("getonner_truck", RollingStockRenderer.texture("tonnertruck_black_friction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.95F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAP_F7A.get(),
                context -> new RollingStockRenderer<>(context, "bap_f7a", RollingStockRenderer.liveried("f7a_"),
                        new float[] {-1.375F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_espee"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_espee"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Brown", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), Map.entry("Skin16", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.01F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAP_F7B.get(),
                context -> new RollingStockRenderer<>(context, "bap_f7b", RollingStockRenderer.liveried("f7b_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_espee"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_espee"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), Map.entry("Brown", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.01F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_H1044.get(),
                context -> new RollingStockRenderer<>(context, "h1044", RollingStockRenderer.liveried("fm_h10-44_"),
                        new float[] {-1.1F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.1F, 0.0F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.1F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.1F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP13.get(),
                context -> new RollingStockRenderer<>(context, "gp13", RollingStockRenderer.liveried("gp13_"),
                        new float[] {-0.8F, -0.47F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAP_B23.get(),
                context -> new RollingStockRenderer<>(context, "bap_b23", RollingStockRenderer.liveried("b23_"),
                        new float[] {-1.7F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_up"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_up"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_bnsf_h1"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_bnsf_h1"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.33F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_C424.get(),
                context -> new RollingStockRenderer<>(context, "c424", RollingStockRenderer.liveried("c424_"),
                        new float[] {-1.6F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, 0.25F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_C425.get(),
                context -> new RollingStockRenderer<>(context, "c425", RollingStockRenderer.liveried("c425_"),
                        new float[] {-1.6F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, 0.25F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP7U.get(),
                context -> new RollingStockRenderer<>(context, "gp7u", RollingStockRenderer.liveried("gp7u_"),
                        new float[] {-1.5F, -0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP7.get(),
                context -> new RollingStockRenderer<>(context, "gp7", RollingStockRenderer.liveried("bettergp7_"),
                        new float[] {-1.5F, -0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.11F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.45F, 0.11F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, -0.25F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP7B.get(),
                context -> new RollingStockRenderer<>(context, "gp7b", RollingStockRenderer.liveried("bettergp7b_"),
                        new float[] {-1.5F, -0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.11F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.45F, 0.11F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP9.get(),
                context -> new RollingStockRenderer<>(context, "gp9", RollingStockRenderer.liveried("gp9_"),
                        new float[] {-1.5F, -0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP30.get(),
                context -> new RollingStockRenderer<>(context, "gp30", RollingStockRenderer.liveried("gp30_"),
                        new float[] {-1.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.3F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.3F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.3F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.3F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP38DASH2.get(),
                context -> new RollingStockRenderer<>(context, "gp38dash2", RollingStockRenderer.liveried("gp38dash2_"),
                        new float[] {-1.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_KOF_III.get(),
                context -> new RollingStockRenderer<>(context, "kof_iii", RollingStockRenderer.liveried("kofiii_"),
                        new float[] {-0.8F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_KOF_III_M.get(),
                context -> new RollingStockRenderer<>(context, "kof_iii_m", RollingStockRenderer.liveried("kofiiim_"),
                        new float[] {-0.8F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_U36C.get(),
                context -> new RollingStockRenderer<>(context, "u36c", RollingStockRenderer.liveried("u36c_"),
                        new float[] {-2.3125F, -0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.375F, -0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3125F, -0.25F, 0.0F, 1.0F)))), Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.375F, -0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3125F, -0.25F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.375F, -0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3125F, -0.25F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP49.get(),
                context -> new RollingStockRenderer<>(context, "gp49", RollingStockRenderer.ported("gp49_arr_2807"),
                        new float[] {-1.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAP_GP15.get(),
                context -> new RollingStockRenderer<>(context, "bap_gp15", RollingStockRenderer.liveried("gp15_bap_"),
                        new float[] {-1.3F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.07F, 0.15F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_spooki_up_trash"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_spooki_up_trash"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.07F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SD9.get(),
                context -> new RollingStockRenderer<>(context, "sd9", RollingStockRenderer.liveried("sd9_"),
                        new float[] {-1.6F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Black", List.of(new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.63F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.49F, 0.0F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.63F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.49F, 0.0F, 0.0F, 1.0F)))), Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexi_c1_bnsf_h1"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.63F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexi_c1_bnsf_h1"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.49F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.63F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c1", RollingStockRenderer.texture("flexicoil_c1_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.49F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SD40DASH2.get(),
                context -> new RollingStockRenderer<>(context, "sd40dash2", RollingStockRenderer.liveried("sd40_"),
                        new float[] {-1.9F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_beansniff"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_beansniff"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.95F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.95F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_U23B.get(),
                context -> new RollingStockRenderer<>(context, "u23b", RollingStockRenderer.liveried("u23b_"),
                        new float[] {-1.7F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.335F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.335F, 0.0F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.335F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.17F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.52F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.53F, 0.335F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_U18B.get(),
                context -> new RollingStockRenderer<>(context, "u18b", RollingStockRenderer.liveried("u18b_"),
                        new float[] {-1.3F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Black", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("Pink", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F)))), Map.entry("Magenta", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("Brown", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("LightBlue", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black_fnm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black_fnm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.27F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("typeb_2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, -0.025F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_csxblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_csxblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.025F, 0.0F, 1.0F)))), Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_csxblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_csxblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F)))), Map.entry("Skin16", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F)))), Map.entry("Skin19", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.335F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.28F, 0.335F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blomberg_bnew", RollingStockRenderer.texture("blombergb_2_blac"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, -0.025F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_HH660.get(),
                context -> new RollingStockRenderer<>(context, "hh660", RollingStockRenderer.liveried("hh600_"),
                        new float[] {-1.0F, -0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.95F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.75F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_KRAUTT.get(),
                context -> new RollingStockRenderer<>(context, "krautt", RollingStockRenderer.liveried("km_ml4000_"),
                        new float[] {-1.6F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("krautt_trucc", RollingStockRenderer.texture("km_ml4000_truckfront"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.73F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("krautt_trucc", RollingStockRenderer.texture("km_ml4000_truckrear"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.73F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DASH8_40B.get(),
                context -> new RollingStockRenderer<>(context, "dash8_40b", RollingStockRenderer.liveried("dash8_40b_"),
                        new float[] {-1.6F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.62F, 0.33F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.62F, 0.33F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS44.get(),
                context -> new RollingStockRenderer<>(context, "class44", RollingStockRenderer.liveried("class44_"),
                        new float[] {-2.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SW1500.get(),
                context -> new RollingStockRenderer<>(context, "sw1500", RollingStockRenderer.liveried("sw1500_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Black", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.05F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.08F, -0.03F, 0.0F, 1.0F)))), Map.entry("Green", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.05F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.08F, -0.03F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.05F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.08F, 0.33F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SW1.get(),
                context -> new RollingStockRenderer<>(context, "sw1", RollingStockRenderer.liveried("sw1_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_brown"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.9F, 0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_brown"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.93F, 0.25F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DASH8_40C.get(),
                context -> new RollingStockRenderer<>(context, "dash8_40c", RollingStockRenderer.liveried("dash8_40c_"),
                        new float[] {-1.6F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_upmoment"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.75F, 0.07F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_upmoment"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.77F, 0.07F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.75F, 0.07F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb3", RollingStockRenderer.texture("fb3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.77F, 0.07F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SW1200.get(),
                context -> new RollingStockRenderer<>(context, "sw1200", RollingStockRenderer.liveried("sw1200_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_sp"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_sp"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), Map.entry("Lime", List.of(new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.33F, 0.0F, 1.0F)))), Map.entry("Brown", List.of(new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil2", RollingStockRenderer.texture("flexicoil2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.33F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_RSD15.get(),
                context -> new RollingStockRenderer<>(context, "rsd15", RollingStockRenderer.liveried("rsd15_"),
                        new float[] {-1.5F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("trimount_trucc", RollingStockRenderer.texture("trimount2_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("trimount_trucc", RollingStockRenderer.texture("trimount2_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.65F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SD70MAC.get(),
                context -> new RollingStockRenderer<>(context, "sd70mac", RollingStockRenderer.liveried("sd70mac_"),
                        new float[] {-2.1F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.8F, -0.03F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.8F, -0.03F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.8F, -0.03F, 0.0F, 1.0F)))), Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_lightgrey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.8F, -0.03F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("htsctruck", RollingStockRenderer.texture("htsctruck_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.8F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DASH9_44CW.get(),
                context -> new RollingStockRenderer<>(context, "dash9_44cw", RollingStockRenderer.liveried("d9-44cw_"),
                        new float[] {-2.2F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_lightgrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_lightgrey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("d9_cab_square_window", RollingStockRenderer.texture("gewidecab_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)))), Map.entry("Green", List.of(new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("d9_cab_square_window", RollingStockRenderer.texture("gewidecab_green"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("newgevotruck", RollingStockRenderer.texture("newgevotruck_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("d9_cab4_window", RollingStockRenderer.texture("cab4window_furrx"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DASH8_40BB.get(),
                context -> new RollingStockRenderer<>(context, "dash8_40bb", RollingStockRenderer.liveried("dash8_40b_b_"),
                        new float[] {-1.6F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.62F, 0.33F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DASH8_40BW.get(),
                context -> new RollingStockRenderer<>(context, "dash8_40bw", RollingStockRenderer.liveried("dash8_40bw_"),
                        new float[] {-1.6F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, 0.33F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fb2", RollingStockRenderer.texture("fb2_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.62F, 0.33F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_DH643.get(),
                context -> new RollingStockRenderer<>(context, "dh643", RollingStockRenderer.ported("alco_dh423_not_dumb"),
                        new float[] {-2.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("trimount_trucc", RollingStockRenderer.texture("trimount1_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 2.0F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("trimount_trucc", RollingStockRenderer.texture("trimount1_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.0F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAP_CF7.get(),
                context -> new RollingStockRenderer<>(context, "bap_cf7", RollingStockRenderer.liveried("cf7_angle_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_darkergrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_darkergrey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.325F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.325F, 0.0F, 1.0F)))), Map.entry("Skin17", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("Skin19", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("Skin20", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("Skin21", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("Magenta", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CF7ROUND.get(),
                context -> new RollingStockRenderer<>(context, "cf7round", RollingStockRenderer.liveried("cf7_round_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3F, 0.325F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_b", RollingStockRenderer.texture("typeb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.325F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.28F, 0.17F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.27F, 0.17F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GP38DASH9W.get(),
                context -> new RollingStockRenderer<>(context, "gp38dash9w", RollingStockRenderer.ported("gp38dash9w_green"),
                        new float[] {-1.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.8F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_ALCO_S2.get(),
                context -> new RollingStockRenderer<>(context, "alco_s2", RollingStockRenderer.liveried("alcos2_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.85F, 0.25F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("blunt_truck", RollingStockRenderer.texture("blunttruck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.98F, 0.25F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BEEP.get(),
                context -> new RollingStockRenderer<>(context, "beep", RollingStockRenderer.ported("atsfbeep"),
                        new float[] {-1.1F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.1F, -0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_a", RollingStockRenderer.texture("typea_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.15F, -0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS158.get(),
                context -> new RollingStockRenderer<>(context, "class158", RollingStockRenderer.liveried("class_158_"),
                        new float[] {-1.85F, -0.45F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS153.get(),
                context -> new RollingStockRenderer<>(context, "class153", RollingStockRenderer.liveried("class_153_"),
                        new float[] {-2.1F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS156.get(),
                context -> new RollingStockRenderer<>(context, "class156", RollingStockRenderer.liveried("class_156_"),
                        new float[] {-1.3F, -0.57F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS47.get(),
                context -> new RollingStockRenderer<>(context, "class47", RollingStockRenderer.liveried("class_47_"),
                        new float[] {-4.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_ALCO_PA1.get(),
                context -> new RollingStockRenderer<>(context, "alco_pa1", RollingStockRenderer.liveried("alcopa1_"),
                        new float[] {-1.6F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), Map.entry("Pink", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucknkpblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucknkpblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucknkpblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucknkpblue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.4F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_ALCO_PB1.get(),
                context -> new RollingStockRenderer<>(context, "alco_pb1", RollingStockRenderer.liveried("alcopb1_"),
                        new float[] {-1.55F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Black", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.55F, 0.0F, 0.0F, 1.0F)))), Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.55F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.5F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("alco_patruck", RollingStockRenderer.texture("alcopatrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.55F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDE8A.get(),
                context -> new RollingStockRenderer<>(context, "emde8a", RollingStockRenderer.liveried("emde8a_"),
                        new float[] {-1.85F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("Pink", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDE8B.get(),
                context -> new RollingStockRenderer<>(context, "emde8b", RollingStockRenderer.liveried("emde8b_"),
                        new float[] {-1.75F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblack"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS43.get(),
                context -> new RollingStockRenderer<>(context, "class43", RollingStockRenderer.liveried("class_43_"),
                        new float[] {-0.65F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_C415H.get(),
                context -> new RollingStockRenderer<>(context, "c415h", RollingStockRenderer.liveried("c415h_"),
                        new float[] {-1.2F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.13F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("aar_typeb_v3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, -0.01F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("type_bnew", RollingStockRenderer.texture("aar_typeb_v3_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.15F, -0.01F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_C415S.get(),
                context -> new RollingStockRenderer<>(context, "c415s", RollingStockRenderer.liveried("c415s_"),
                        new float[] {-1.2F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.13F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_C415L.get(),
                context -> new RollingStockRenderer<>(context, "c415l", RollingStockRenderer.liveried("c415l_"),
                        new float[] {-1.2F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.2F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("hi_ad", RollingStockRenderer.texture("hiad_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.13F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_GE25TON.get(),
                context -> new RollingStockRenderer<>(context, "ge25ton", RollingStockRenderer.liveried("25ton_"),
                        new float[] {-0.5F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_H24_66.get(),
                context -> new RollingStockRenderer<>(context, "h24_66", RollingStockRenderer.liveried("fm_h24-66_"),
                        new float[] {-1.9F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_silver"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.7F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.68F, -0.03F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_sp_grey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.7F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_sp_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.68F, -0.03F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.7F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.68F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_H24_66L.get(),
                context -> new RollingStockRenderer<>(context, "h24_66l", RollingStockRenderer.liveried("fm_h24-66_l_"),
                        new float[] {-1.9F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.7F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.68F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDE7A.get(),
                context -> new RollingStockRenderer<>(context, "emde7a", RollingStockRenderer.liveried("emde7a_"),
                        new float[] {-1.85F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblackfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblackfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_EMDE7B.get(),
                context -> new RollingStockRenderer<>(context, "emde7b", RollingStockRenderer.liveried("emde7b_"),
                        new float[] {-1.75F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atrucksilverfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblackfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("new_blomberg_a1_atruck", RollingStockRenderer.texture("newblomberga1atruckblackfriction"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS175.get(),
                context -> new RollingStockRenderer<>(context, "class175", RollingStockRenderer.liveried("class_175_"),
                        new float[] {-2.25F, -0.127F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_H16_66.get(),
                context -> new RollingStockRenderer<>(context, "h16_66", RollingStockRenderer.liveried("fm_h16-66_"),
                        new float[] {-1.7F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_tva_blue"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.52F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_tva_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.48F, -0.03F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.52F, -0.03F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("fmtrimount_truck", RollingStockRenderer.texture("fm_trimount_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.48F, -0.03F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS34.get(),
                context -> new RollingStockRenderer<>(context, "class34", RollingStockRenderer.liveried("class_34_"),
                        new float[] {-2.75F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS121_BUBBLECAR.get(),
                context -> new RollingStockRenderer<>(context, "class121_bubblecar", RollingStockRenderer.liveried("bubble_car_"),
                        new float[] {-2.1F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS117.get(),
                context -> new RollingStockRenderer<>(context, "class117", RollingStockRenderer.liveried("bubble_car_"),
                        new float[] {-2.1F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS143_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class143_front", RollingStockRenderer.liveried("class143_"),
                        new float[] {-1.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CD754.get(),
                context -> new RollingStockRenderer<>(context, "cd754", RollingStockRenderer.liveried("cd754_"),
                        new float[] {-2.5F, 0.2F, 0.0F}, new float[] {180.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS142_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class142_front", RollingStockRenderer.liveried("class_142_"),
                        new float[] {-1.8F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_BAGNALL.get(),
                context -> new RollingStockRenderer<>(context, "bagnall", RollingStockRenderer.liveried("bagnall_shunter_0-4-0_"),
                        new float[] {-0.85F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_CLASS205.get(),
                context -> new RollingStockRenderer<>(context, "class205", RollingStockRenderer.liveried("class_205_"),
                        new float[] {-2.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_MINE_TRAIN.get(),
                context -> new RollingStockRenderer<>(context, "mine_train", RollingStockRenderer.ported("locominetrain"),
                        new float[] {-0.8F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_SPEED_ZERO_ED.get(),
                context -> new RollingStockRenderer<>(context, "speed_zero_ed", RollingStockRenderer.ported("locohighspeedzeroed"),
                        new float[] {-1.7F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_ICE1.get(),
                context -> new RollingStockRenderer<>(context, "ice1", RollingStockRenderer.liveried("ice1_engine_"),
                        new float[] {0.0F, 0.18F, 0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_TRAM_YELLOW.get(),
                context -> new RollingStockRenderer<>(context, "tram_yellow", RollingStockRenderer.ported("tram"),
                        new float[] {-0.8F, -0.44F, 0.0F}, new float[] {0.0F, 180.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_TRAM_NY.get(),
                context -> new RollingStockRenderer<>(context, "tram_ny", RollingStockRenderer.ported("locotramny"),
                        new float[] {-1.5F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_E103.get(),
                context -> new RollingStockRenderer<>(context, "e103", RollingStockRenderer.liveried("e103_"),
                        new float[] {-2.0F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("e103_bogie", RollingStockRenderer.texture("e103bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.35F, 0.1F, 0.06F, 1.0F)), new RollingStockRenderer.Attachment("e103_bogie", RollingStockRenderer.texture("e103bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, 0.1F, 0.06F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS85.get(),
                context -> new RollingStockRenderer<>(context, "class85", RollingStockRenderer.liveried("class85_"),
                        new float[] {-3.0F, 0.65F, 0.0F}, new float[] {0.0F, 90.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("deitic_bogie", RollingStockRenderer.texture("class85_bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.1F, -1.8F, 1.0F)), new RollingStockRenderer.Attachment("deitic_bogie", RollingStockRenderer.texture("class85_bogie"), RollingStockRenderer.transform(0.8F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.1F, 1.7F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CD151.get(),
                context -> new RollingStockRenderer<>(context, "cd151", RollingStockRenderer.liveried("cd151_"),
                        new float[] {-2.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("cd151_bogie", RollingStockRenderer.texture("cd151_front_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.15F, 0.3F, -0.4F, 1.0F)), new RollingStockRenderer.Attachment("cd151_bogie", RollingStockRenderer.texture("cd151_front_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.95F, 0.3F, -0.4F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BP4.get(),
                context -> new RollingStockRenderer<>(context, "bp4", RollingStockRenderer.liveried("bp4_"),
                        new float[] {-2.2F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("bp4_bogie", RollingStockRenderer.texture("bp4bogie_purple"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bp4_bogie", RollingStockRenderer.texture("bp4bogie_purple"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bp4_bogie", RollingStockRenderer.texture("bp4bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bp4_bogie", RollingStockRenderer.texture("bp4bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_RENFE446_MOTOR.get(),
                context -> new RollingStockRenderer<>(context, "renfe446_motor", RollingStockRenderer.liveried("446_"),
                        new float[] {-1.9F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.35F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_PCH120.get(),
                context -> new RollingStockRenderer<>(context, "pch120", RollingStockRenderer.liveried("pch120commute_"),
                        new float[] {-1.3F, 0.01F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("pchbogie", RollingStockRenderer.texture("pch120_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.3F, 0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("pchbogie", RollingStockRenderer.texture("pch120_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_LU_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "lu_engine", RollingStockRenderer.ported("lu_engine"),
                        new float[] {-1.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DSTOCK_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "dstock_engine", RollingStockRenderer.liveried("d_stock_engine_"),
                        new float[] {-1.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS345.get(),
                context -> new RollingStockRenderer<>(context, "class345", RollingStockRenderer.liveried("class345_"),
                        new float[] {-1.5F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BNLRV_A.get(),
                context -> new RollingStockRenderer<>(context, "bnlrv_a", RollingStockRenderer.liveried("bnlrv_"),
                        new float[] {-2.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_TW305.get(),
                context -> new RollingStockRenderer<>(context, "tw305", RollingStockRenderer.liveried("tw_"),
                        new float[] {-1.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_METRO2000.get(),
                context -> new RollingStockRenderer<>(context, "metro2000", RollingStockRenderer.liveried("metro_2000_"),
                        new float[] {-1.5F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_RENFE450_MOTOR.get(),
                context -> new RollingStockRenderer<>(context, "renfe450_motor", RollingStockRenderer.liveried("450_loco_"),
                        new float[] {-1.9F, 0.1F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BR155.get(),
                context -> new RollingStockRenderer<>(context, "br155", RollingStockRenderer.liveried("br155_"),
                        new float[] {-1.7F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("br155_bogie", RollingStockRenderer.texture("br155_bogies_noised"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("br155_bogie", RollingStockRenderer.texture("br155_bogies_noised"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.6F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_440R_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "440r_front", RollingStockRenderer.liveried("440r_"),
                        new float[] {-1.9F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.0F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DB143.get(),
                context -> new RollingStockRenderer<>(context, "db143", RollingStockRenderer.ported("db143"),
                        new float[] {-1.7F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("db143_bogis", RollingStockRenderer.texture("143_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.6F, 0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("db143_bogis", RollingStockRenderer.texture("143_bogie"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.6F, 0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_EF1.get(),
                context -> new RollingStockRenderer<>(context, "ef1", RollingStockRenderer.liveried("ef1_"),
                        new float[] {-1.6F, 0.1875F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ef1d", RollingStockRenderer.texture("ef1d_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ef1tp", RollingStockRenderer.texture("ef1tp_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3125F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_EF1B.get(),
                context -> new RollingStockRenderer<>(context, "ef1b", RollingStockRenderer.liveried("ef1b_"),
                        new float[] {-1.15635F, 0.1875F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ef1d", RollingStockRenderer.texture("ef1d_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0625F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ef1d", RollingStockRenderer.texture("ef1d_black"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, -1.0625F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_EP1A.get(),
                context -> new RollingStockRenderer<>(context, "ep1a", RollingStockRenderer.liveried("ep1a_"),
                        new float[] {-1.6F, 0.1875F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ef1d", RollingStockRenderer.texture("ef1d_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.5F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ef1tp", RollingStockRenderer.texture("ef1tp_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.3125F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_ILMA.get(),
                context -> new RollingStockRenderer<>(context, "ilma", RollingStockRenderer.liveried("ilma_"),
                        new float[] {-0.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_ILMB.get(),
                context -> new RollingStockRenderer<>(context, "ilmb", RollingStockRenderer.liveried("ilmb_"),
                        new float[] {-0.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_FEVE3300FRONT.get(),
                context -> new RollingStockRenderer<>(context, "feve3300front", RollingStockRenderer.liveried("feve3300_"),
                        new float[] {-1.8F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_EU07.get(),
                context -> new RollingStockRenderer<>(context, "eu07", RollingStockRenderer.liveried("eu07_"),
                        new float[] {-1.7F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_GM6C.get(),
                context -> new RollingStockRenderer<>(context, "gm6c", RollingStockRenderer.ported("gm6c_demo"),
                        new float[] {-1.9F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_grey"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, 0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("flexicoil_c2_h", RollingStockRenderer.texture("flexicoil_c2h_grey"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.9F, 0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS319_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "class319_engine", RollingStockRenderer.liveried("br_319_"),
                        new float[] {-1.65F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_KVB_2300.get(),
                context -> new RollingStockRenderer<>(context, "kvb_2300", RollingStockRenderer.liveried("kvb_2300_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BR_MK2F_DBSO.get(),
                context -> new RollingStockRenderer<>(context, "br_mk2f_dbso", RollingStockRenderer.liveried("br_mk2f_dbso_"),
                        new float[] {-1.5F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BR_MK3_DVT.get(),
                context -> new RollingStockRenderer<>(context, "br_mk3_dvt", RollingStockRenderer.liveried("br_mk3_dvt_"),
                        new float[] {-1.5F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BR_MK4_DVT.get(),
                context -> new RollingStockRenderer<>(context, "br_mk4_dvt", RollingStockRenderer.liveried("br_mk4_dvt_"),
                        new float[] {-1.9F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS90.get(),
                context -> new RollingStockRenderer<>(context, "class90", RollingStockRenderer.liveried("class_90_"),
                        new float[] {-0.55F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS91.get(),
                context -> new RollingStockRenderer<>(context, "class91", RollingStockRenderer.liveried("class_91_"),
                        new float[] {-1.2F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS321.get(),
                context -> new RollingStockRenderer<>(context, "class321", RollingStockRenderer.liveried("class_321_"),
                        new float[] {-1.2F, -0.455F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_NMBS_HLE_18.get(),
                context -> new RollingStockRenderer<>(context, "nmbs_hle_18", RollingStockRenderer.liveried("hle19_"),
                        new float[] {-1.5F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_FGV4300_MOTOR.get(),
                context -> new RollingStockRenderer<>(context, "fgv4300_motor", RollingStockRenderer.ported("4300_motorcar"),
                        new float[] {-1.3F, -0.03F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_INTERURBAN_SERIES_100.get(),
                context -> new RollingStockRenderer<>(context, "interurban_series_100", RollingStockRenderer.liveried("interurban_tram_"),
                        new float[] {-1.5F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_METRO3000.get(),
                context -> new RollingStockRenderer<>(context, "metro3000", RollingStockRenderer.liveried("mm3000_"),
                        new float[] {-1.5F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CQ310.get(),
                context -> new RollingStockRenderer<>(context, "cq310", RollingStockRenderer.liveried("cq_310_"),
                        new float[] {-1.5F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS162_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "class162_engine", RollingStockRenderer.liveried("class_162_engine_"),
                        new float[] {-2.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_METAL_TRAM.get(),
                context -> new RollingStockRenderer<>(context, "metal_tram", RollingStockRenderer.liveried("metal_tram_"),
                        new float[] {-1.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_B80C_A.get(),
                context -> new RollingStockRenderer<>(context, "b80c_a", RollingStockRenderer.liveried("b80c_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_MA100_LOCO.get(),
                context -> new RollingStockRenderer<>(context, "ma100_loco", RollingStockRenderer.liveried("ma100_"),
                        new float[] {-0.95F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS390_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class390_front", RollingStockRenderer.liveried("class_390_front_"),
                        new float[] {-2.3F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DUEWAG_T4ER.get(),
                context -> new RollingStockRenderer<>(context, "duewag_t4er", RollingStockRenderer.liveried("duewag_t4_"),
                        new float[] {-1.25F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DUEWAG_GT6ZR.get(),
                context -> new RollingStockRenderer<>(context, "duewag_gt6zr", RollingStockRenderer.liveried("duewag_gt6_"),
                        new float[] {-0.85F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_M8C.get(),
                context -> new RollingStockRenderer<>(context, "m8c", RollingStockRenderer.liveried("m8c_"),
                        new float[] {-1.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS416_LOCO.get(),
                context -> new RollingStockRenderer<>(context, "class416_loco", RollingStockRenderer.liveried("class_205_"),
                        new float[] {-2.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DB420_LOCO.get(),
                context -> new RollingStockRenderer<>(context, "db420_loco", RollingStockRenderer.liveried("db420_"),
                        new float[] {-1.5F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS401_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "class401_engine", RollingStockRenderer.liveried("2-bil_"),
                        new float[] {-1.7F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS230_ENGINE.get(),
                context -> new RollingStockRenderer<>(context, "class230_engine", RollingStockRenderer.liveried("class_230_"),
                        new float[] {-1.3F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_DUEWAG_GT6ER.get(),
                context -> new RollingStockRenderer<>(context, "duewag_gt6er", RollingStockRenderer.liveried("duewag_gt6er_"),
                        new float[] {-0.85F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS374_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class374_front", RollingStockRenderer.liveried("class_374_front_"),
                        new float[] {-2.6F, 0.162F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS387_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class387_front", RollingStockRenderer.liveried("class_387_"),
                        new float[] {-2.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS378_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class378_front", RollingStockRenderer.liveried("class_378_"),
                        new float[] {-1.9F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS389_FRONT.get(),
                context -> new RollingStockRenderer<>(context, "class389_front", RollingStockRenderer.liveried("class_389_"),
                        new float[] {-2.4F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_CLASS442_DTS.get(),
                context -> new RollingStockRenderer<>(context, "class442_dts", RollingStockRenderer.liveried("class_442_dts_"),
                        new float[] {-2.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_M8_DNF1.get(),
                context -> new RollingStockRenderer<>(context, "m8_dnf1", RollingStockRenderer.liveried("m8d_nf1_"),
                        new float[] {-0.65F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_VL10.get(),
                context -> new RollingStockRenderer<>(context, "vl10", RollingStockRenderer.ported("vl10"),
                        new float[] {-1.2F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CART_BLUE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cart_blue", RollingStockRenderer.liveried("passenger_"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CART_BLACK_SMALL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cart_black_small", RollingStockRenderer.ported("passenger3"),
                        new float[] {0.0F, -0.32F, -0.1F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_LONG_GREEN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_long_green", RollingStockRenderer.liveried("passenger5_"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_SHORT_GREEN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_short_green", RollingStockRenderer.ported("passenger7"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_1CLASS_DB.get(),
                context -> new RollingStockRenderer<>(context, "passenger_1class_db", RollingStockRenderer.ported("passenger_1class_db"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_2CLASS_DB.get(),
                context -> new RollingStockRenderer<>(context, "passenger_2class_db", RollingStockRenderer.liveried("passenger_2class_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_HIGH_SPEED_ZERO_ED.get(),
                context -> new RollingStockRenderer<>(context, "passenger_high_speed_zero_ed", RollingStockRenderer.ported("passengerhighspeedcarzeroed"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_TRAM_NY.get(),
                context -> new RollingStockRenderer<>(context, "passenger_tram_ny", RollingStockRenderer.ported("locotramny"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_ADLER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_adler", RollingStockRenderer.ported("passengeradler"),
                        new float[] {0.0F, 1.04F, 0.0F}, new float[] {180.0F, -90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DB_ORIENTAL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_db_oriental", RollingStockRenderer.liveried("passenger_db_oriental_"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {180.0F, 0.0F, 0.0F}, new float[] {-1.0F, -1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Blue", List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F)))), Map.entry("White", List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_white"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_white"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F)))), Map.entry("Green", List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_green"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_green"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F)))), Map.entry("Purple", List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_purple"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_purple"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F)))), Map.entry("Red", List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_yellow"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("passenger_db_oriental_bogie", RollingStockRenderer.texture("passenger_db_oriental_yellow"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.15F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_IC4_DSB_FG.get(),
                context -> new RollingStockRenderer<>(context, null, RollingStockRenderer.ported("ic4_dsb_fg"),
                        new float[] {-0.8F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("passenger_ic4_dsb_fg_part1", RollingStockRenderer.texture("ic4_dsb_fg"), RollingStockRenderer.transform(0.0F, 0.0F, 0.7F, 0.0F, 0.0F, -0.9F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -2.6F, 1.4F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_IC4_DSB_FH.get(),
                context -> new RollingStockRenderer<>(context, null, RollingStockRenderer.ported("ic4_dsb_fh"),
                        new float[] {-0.8F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("passenger_ic4_dsb_fh_part1", RollingStockRenderer.texture("ic4_dsb_fh"), RollingStockRenderer.transform(0.0F, 0.0F, 0.7F, 0.0F, 0.0F, -0.9F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -2.6F, 1.4F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_ICE1_CLASS1.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ice1_class1", RollingStockRenderer.liveried("ice1_1st_class_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_ICE1_CLASS2.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ice1_class2", RollingStockRenderer.liveried("ice1_2nd_class_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_ICE1_RESTAURANT.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ice1_restaurant", RollingStockRenderer.liveried("ice1_restaurant_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_GS4.get(),
                context -> new RollingStockRenderer<>(context, "passenger_gs4", RollingStockRenderer.liveried("gs4_passenger_"),
                        new float[] {0.0F, 0.03F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_GS4_OBSERVATORY.get(),
                context -> new RollingStockRenderer<>(context, "passenger_gs4_observatory", RollingStockRenderer.liveried("gs4_tavern_"),
                        new float[] {0.0F, 0.025F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_GS4_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_gs4_tail", RollingStockRenderer.liveried("gs4_tail_"),
                        new float[] {-0.2F, 0.025F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DENVER_RIO_GRANGE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_denver_rio_grange", RollingStockRenderer.liveried("drg_passenger_"),
                        new float[] {0.0F, 0.14F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DENVER_RIO_GRANDE_COMBO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_denver_rio_grande_combo", RollingStockRenderer.liveried("drg_combo_"),
                        new float[] {0.0F, 0.14F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_RHEINGOLD.get(),
                context -> new RollingStockRenderer<>(context, "passenger_rheingold", RollingStockRenderer.liveried("rheingold_passenger_"),
                        new float[] {1.55F, 0.15F, -0.6F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.625F, 0.2F, -1.045F, 1.0F)), new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.695F, 0.2F, -1.045F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_RHEINGOLD_PANORAMA.get(),
                context -> new RollingStockRenderer<>(context, "passenger_rheingold_panorama", RollingStockRenderer.liveried("rheingold_passenger_panorama_"),
                        new float[] {-0.1F, 0.15F, 0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.945F, 0.2F, -0.26F, 1.0F)), new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.375F, 0.2F, -0.26F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_MILW.get(),
                context -> new RollingStockRenderer<>(context, "passenger_milw", RollingStockRenderer.ported("milw_passenger"),
                        new float[] {-0.1F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_MILW_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_milw_tail", RollingStockRenderer.ported("milw_passenger_tail"),
                        new float[] {0.1F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BAMBOO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bamboo", RollingStockRenderer.liveried("passenger_bamboo_"),
                        new float[] {0.1F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_RENFE446_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_renfe446_coach", RollingStockRenderer.liveried("446_"),
                        new float[] {-0.3F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.35F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CABOOSE_RENFE446_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_caboose_renfe446_tail", RollingStockRenderer.liveried("446_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.35F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PCH120COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_pch120coach", RollingStockRenderer.liveried("pch120car_"),
                        new float[] {0.0F, 0.01F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("pchbogie", RollingStockRenderer.texture("pch120_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.1F, 0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("pchbogie", RollingStockRenderer.texture("pch120_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_L_UPASSENGER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_l_upassenger", RollingStockRenderer.ported("lu_passenger"),
                        new float[] {0.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DSTOCK_PASSENGER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_dstock_passenger", RollingStockRenderer.liveried("d_stock_passenger_"),
                        new float[] {0.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS345_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class345_coach", RollingStockRenderer.liveried("class345_passenger_"),
                        new float[] {0.0F, 0.05F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS52_SEAT_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps52_seat_coach", RollingStockRenderer.liveried("ps_lightweight_52seat_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), Map.entry("LightBlue", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), Map.entry("Black", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), Map.entry("Cyan", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_P_SCENTER_DINER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_p_scenter_diner", RollingStockRenderer.liveried("ps_lightweight_center_diner_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS_ANOTHER_DINER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps_another_diner", RollingStockRenderer.liveried("ps_lightweight_center_diner_2_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BNLRV_B.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bnlrv_b", RollingStockRenderer.liveried("bnlrv_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BW305.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bw305", RollingStockRenderer.liveried("tw_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_METRO2000.get(),
                context -> new RollingStockRenderer<>(context, "passenger_metro2000", RollingStockRenderer.liveried("metro_2000_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_RENFE450_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_renfe450_coach", RollingStockRenderer.liveried("450_passenger_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.8F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.7F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CABOOSE_RENFE450_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_caboose_renfe450_tail", RollingStockRenderer.liveried("450_passenger_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("renfebogie", RollingStockRenderer.texture("446_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.65F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CD014.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cd014", RollingStockRenderer.liveried("cd014_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CD914.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cd914", RollingStockRenderer.liveried("cd914_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CD010.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cd010", RollingStockRenderer.liveried("cd010_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_AMFLEET.get(),
                context -> new RollingStockRenderer<>(context, "passenger_amfleet", RollingStockRenderer.liveried("amfleet_"),
                        new float[] {0.0F, -0.4F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_AMFLEET2.get(),
                context -> new RollingStockRenderer<>(context, "passenger_amfleet2", RollingStockRenderer.liveried("amfleet2_"),
                        new float[] {0.0F, -0.4F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_STAR_CAR_FAT.get(),
                context -> new RollingStockRenderer<>(context, "passenger_star_car_fat", RollingStockRenderer.ported("gwrclosed"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_STAR_CAR_NOT_FAT.get(),
                context -> new RollingStockRenderer<>(context, "passenger_star_car_not_fat", RollingStockRenderer.ported("gwropen"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_440_R_MID.get(),
                context -> new RollingStockRenderer<>(context, "passenger_440_r_mid", RollingStockRenderer.liveried("440r_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.95F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.95F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_440_R_REAR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_440_r_rear", RollingStockRenderer.liveried("440r_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.85F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("440_r_truck", RollingStockRenderer.texture("440_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.0F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FEVE3300REAR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_feve3300rear", RollingStockRenderer.liveried("feve3300_"),
                        new float[] {-0.0F, 0.18F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS158_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class158_coach", RollingStockRenderer.liveried("class_158_"),
                        new float[] {0.5F, -0.45F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS153_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class153_coach", RollingStockRenderer.liveried("class_153_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS_SLEEPER565.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps_sleeper565", RollingStockRenderer.liveried("ps_lightweight_5-6-5_sleeper_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightBlue", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("bap_41-n-11_truck_nkp_blue"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS_SLEEPER565_DRGW.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps_sleeper565_drgw", RollingStockRenderer.ported("ps_drgw_lightweight_5-6-5_sleeper"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_SNCB_M6.get(),
                context -> new RollingStockRenderer<>(context, "passenger_sncb_m6", RollingStockRenderer.liveried("sncb_m6_"),
                        new float[] {-2.0F, 0.095F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_SNCB_M6_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_sncb_m6_tail", RollingStockRenderer.liveried("sncb_m6_tail_"),
                        new float[] {-2.3F, 0.04F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS319_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class319_middle", RollingStockRenderer.liveried("br_319_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS319_PANTO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class319_panto", RollingStockRenderer.liveried("br_319_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS319_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class319_tail", RollingStockRenderer.liveried("br_319_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_KVB_2300_B.get(),
                context -> new RollingStockRenderer<>(context, "passenger_kvb_2300_b", RollingStockRenderer.liveried("kvb_2300_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK2_C_BSO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk2_c_bso", RollingStockRenderer.liveried("br_mk2c_bso_"),
                        new float[] {0.485F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK2_C_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk2_c_coach", RollingStockRenderer.liveried("br_mk2c_"),
                        new float[] {0.485F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK2_F_BSO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk2_f_bso", RollingStockRenderer.liveried("br_mk2f_bso_"),
                        new float[] {0.485F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK2_F_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk2_f_coach", RollingStockRenderer.liveried("br_mk2f_"),
                        new float[] {0.485F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK3_BUFFET.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk3_buffet", RollingStockRenderer.liveried("br_mk3_buffet_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK3_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk3_coach", RollingStockRenderer.liveried("br_mk3_coach_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK3A_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk3a_coach", RollingStockRenderer.liveried("br_mk3a_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK3_PANTOGRAPH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk3_pantograph", RollingStockRenderer.liveried("br_mk3_pantograph_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK4_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk4_coach", RollingStockRenderer.liveried("br_mk4_"),
                        new float[] {0.2F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK4_BUFFET.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk4_buffet", RollingStockRenderer.liveried("br_mk4_buffet_"),
                        new float[] {0.22F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS321_MOTOR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class321_motor", RollingStockRenderer.liveried("class_321_panto_car_"),
                        new float[] {0.6F, -0.455F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS321_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class321_coach", RollingStockRenderer.liveried("class_321_trailer_car_"),
                        new float[] {0.6F, -0.455F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_MINETRAIN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_minetrain", RollingStockRenderer.ported("minetrain"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS_LUNCH_COUNTER_LOUNGE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps_lunch_counter_lounge", RollingStockRenderer.liveried("ps_lightweight_lunchcounter_lounge_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS30_SEAT_PARLOR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps30_seat_parlor", RollingStockRenderer.liveried("ps_lightweight_30seatparlor_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS54_SEAT_COACH_LOUNGE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps54_seat_coach_lounge", RollingStockRenderer.liveried("ps_lightweight_54seatcoach_lounge_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS58_SEAT_COACH_OBSERVATION.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps58_seat_coach_observation", RollingStockRenderer.liveried("ps_lightweight_58seatcoach_observation_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PSBM56_SEAT_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_psbm56_seat_coach", RollingStockRenderer.liveried("ps_lightweight_bm56seatcoach_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PSBM_COMBINE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_psbm_combine", RollingStockRenderer.liveried("ps_lightweight_bmcombine_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PSBM_DINER_LOUNGE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_psbm_diner_lounge", RollingStockRenderer.liveried("ps_lightweight_bmdiner_lounge_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK1_BSO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk1_bso", RollingStockRenderer.liveried("br_mk1_bso_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK1_TSO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk1_tso", RollingStockRenderer.liveried("br_mk1_tso_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK1_BUFFET.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk1_buffet", RollingStockRenderer.liveried("br_mk1_buffet_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_MK1_BAGGAGE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_mk1_baggage", RollingStockRenderer.liveried("br_mk1_bg_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS175_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class175_coach", RollingStockRenderer.liveried("class_175_middle_car_"),
                        new float[] {0.0F, -0.127F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_ACFGN60_SEAT_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_acfgn60_seat_coach", RollingStockRenderer.liveried("acf_lightweight_gn60seatcoach_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FGV4300_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_fgv4300_coach", RollingStockRenderer.ported("4300_middle"),
                        new float[] {0.0F, -0.03F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FGV4300_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_fgv4300_tail", RollingStockRenderer.ported("4300_motorcar"),
                        new float[] {0.0F, -0.03F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_METRO3000.get(),
                context -> new RollingStockRenderer<>(context, "passenger_metro3000", RollingStockRenderer.liveried("mm3000_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_METRO3000_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_metro3000_tail", RollingStockRenderer.liveried("mm3000_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS162_COACH_B.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class162_coach_b", RollingStockRenderer.liveried("class_162_coach_b_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS162_COACH_A.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class162_coach_a", RollingStockRenderer.liveried("class_162_coach_a_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS162_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class162_tail", RollingStockRenderer.liveried("class_162_engine_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_METAL_TRAM_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_metal_tram_coach", RollingStockRenderer.liveried("metal_tram_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_B80_C_B.get(),
                context -> new RollingStockRenderer<>(context, "passenger_b80_c_b", RollingStockRenderer.liveried("b80c_"),
                        new float[] {-1.25F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_WOODEN_TRAM_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_wooden_tram_coach", RollingStockRenderer.liveried("wooden_tram_coach_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_MA100_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ma100_tail", RollingStockRenderer.liveried("ma100_"),
                        new float[] {0.0F, 0.16F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS390_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class390_coach", RollingStockRenderer.liveried("class_390_coach_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS390_PANTO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class390_panto", RollingStockRenderer.liveried("class_390_panto_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS121_TRAILER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class121_trailer", RollingStockRenderer.liveried("bubble_car_"),
                        new float[] {0.0F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS117_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class117_middle", RollingStockRenderer.liveried("bubble_car_"),
                        new float[] {0.0F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BR_BRAKE_VAN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_br_brake_van", RollingStockRenderer.liveried("br_brake_van_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DUEWAG_GT6_ZR_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_duewag_gt6_zr_tail", RollingStockRenderer.liveried("duewag_gt6_"),
                        new float[] {-0.85F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_M8_C_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_m8_c_tail", RollingStockRenderer.liveried("m8c_"),
                        new float[] {-1.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS416_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class416_tail", RollingStockRenderer.liveried("class_205_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DB420_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_db420_middle", RollingStockRenderer.liveried("db420_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DB420_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_db420_tail", RollingStockRenderer.liveried("db420_"),
                        new float[] {-1.5F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS401_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class401_tail", RollingStockRenderer.liveried("2-bil_"),
                        new float[] {0.02F, 0.17F, 0.03F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_10TON_BRAKE_VAN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_10ton_brake_van", RollingStockRenderer.liveried("10ton_brakevan_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS230_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class230_middle", RollingStockRenderer.liveried("class_230_"),
                        new float[] {0.0F, 0.16F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DUEWAG_GT6_ER_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_duewag_gt6_er_tail", RollingStockRenderer.liveried("duewag_gt6er_"),
                        new float[] {-0.85F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS143_REAR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class143_rear", RollingStockRenderer.liveried("class143_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS143_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class143_middle", RollingStockRenderer.liveried("class143_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS374_PREMIER_PANTO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class374_premier_panto", RollingStockRenderer.liveried("class_374_premier_panto_"),
                        new float[] {0.0F, 0.162F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS374_STANDARD_PANTO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class374_standard_panto", RollingStockRenderer.liveried("class_374_standard_panto_"),
                        new float[] {0.0F, 0.162F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS374_BUFFET.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class374_buffet", RollingStockRenderer.liveried("class_374_buffet_"),
                        new float[] {0.0F, 0.162F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS387_COACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class387_coach", RollingStockRenderer.liveried("class_387_middle_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS387_PANTO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class387_panto", RollingStockRenderer.liveried("class_387_middle_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS387_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class387_tail", RollingStockRenderer.liveried("class_387_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS378_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class378_middle", RollingStockRenderer.liveried("class_378_middle_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS378_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class378_tail", RollingStockRenderer.liveried("class_378_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS142_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class142_tail", RollingStockRenderer.liveried("class_142_"),
                        new float[] {-0.53F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS389_MIDDLE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class389_middle", RollingStockRenderer.liveried("class_389_middle_"),
                        new float[] {-0.31F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS389_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class389_tail", RollingStockRenderer.liveried("class_389_"),
                        new float[] {-0.31F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS442_TS.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class442_ts", RollingStockRenderer.liveried("class_442_ts_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS442_MBLS.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class442_mbls", RollingStockRenderer.liveried("class_442_mbls_"),
                        new float[] {0.15F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS205TSO.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class205tso", RollingStockRenderer.liveried("class_205_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CLASS205_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_class205_tail", RollingStockRenderer.liveried("class_205_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_M8_DNF1_MIDDLELONG.get(),
                context -> new RollingStockRenderer<>(context, "passenger_m8_dnf1_middlelong", RollingStockRenderer.liveried("m8d_nf1_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_M8_DNF1_MIDDLESHORT.get(),
                context -> new RollingStockRenderer<>(context, "passenger_m8_dnf1_middleshort", RollingStockRenderer.liveried("m8d_nf1_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_M8_DNF1_TAIL.get(),
                context -> new RollingStockRenderer<>(context, "passenger_m8_dnf1_tail", RollingStockRenderer.liveried("m8d_nf1_"),
                        new float[] {-0.65F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_RED.get(),
                context -> new RollingStockRenderer<>(context, "freight_cart_red", RollingStockRenderer.ported("freightcart2"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_WOOD.get(),
                context -> new RollingStockRenderer<>(context, "freight_wood", RollingStockRenderer.ported("wood_full"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_hopper", RollingStockRenderer.ported("hopper"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_K_CLASS_RAIL_BOX.get(),
                context -> new RollingStockRenderer<>(context, "freight_k_class_rail_box", RollingStockRenderer.liveried("kclassrailbox_"),
                        new float[] {-1.5F, 0.1F, 0.125F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.375F, 0.45F, -0.2025F, 1.0F)), new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 2.5F, 0.45F, -0.2025F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_SHORT_COVERED_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_short_covered_hopper", RollingStockRenderer.liveried("shortcoveredhopper_"),
                        new float[] {-2.2F, -0.25F, 0.7F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.8F, 0.125F, 0.315F, 1.0F)), new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 3.45F, 0.125F, 0.315F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_LONG_COVERED_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_long_covered_hopper", RollingStockRenderer.liveried("longcoveredhopper_"),
                        new float[] {-1.0F, -0.25F, 0.65F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.35F, 0.125F, 0.315F, 1.0F)), new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 3.45F, 0.125F, 0.315F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_OPEN_WAGON.get(),
                context -> new RollingStockRenderer<>(context, "freight_open_wagon", RollingStockRenderer.ported("openwagon"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HOPPER_US.get(),
                context -> new RollingStockRenderer<>(context, "freight_hopper_us", RollingStockRenderer.liveried("freighthopperus_"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_100_TON_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_100_ton_hopper", RollingStockRenderer.liveried("freight_100tonhopper_"),
                        new float[] {-0.1F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.9F, -0.16F, -0.19F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.4F, -0.16F, -0.19F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_FLAT_CART_WOOD_US.get(),
                context -> new RollingStockRenderer<>(context, "freight_flat_cart_wood_us", RollingStockRenderer.ported("flatcartwoodus"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BULKHEAD_FLAT_CART_WOOD.get(),
                context -> new RollingStockRenderer<>(context, "freight_bulkhead_flat_cart_wood", RollingStockRenderer.liveried("bulkheadflat_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.1F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.8F, 0.16F, -0.35F, 1.0F)), new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.1F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.6F, 0.15F, -0.35F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_US.get(),
                context -> new RollingStockRenderer<>(context, "freight_cart_us", RollingStockRenderer.liveried("freightcartus_"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BOX_CART_US.get(),
                context -> new RollingStockRenderer<>(context, "freight_box_cart_us", RollingStockRenderer.liveried("boxcartus_"),
                        new float[] {0.0F, -0.45F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BOX_CART_PRR.get(),
                context -> new RollingStockRenderer<>(context, "freight_box_cart_prr", RollingStockRenderer.ported("prr_x31a"),
                        new float[] {0.0F, -0.38F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bettendorf_trucks", RollingStockRenderer.texture("bettendorf_trucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.75F, 0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bettendorf_trucks", RollingStockRenderer.texture("bettendorf_trucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.75F, 0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_SMALL.get(),
                context -> new RollingStockRenderer<>(context, "freight_cart_small", RollingStockRenderer.ported("freightcartsmall"),
                        new float[] {0.0F, -0.2F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_MINETRAIN_2.get(),
                context -> new RollingStockRenderer<>(context, "freight_minetrain_2", RollingStockRenderer.ported("minetrain"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_GTNG.get(),
                context -> new RollingStockRenderer<>(context, "freight_gtng", RollingStockRenderer.ported("gtngorewagon"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_FLAT_CART_WOOD_LOGS.get(),
                context -> new RollingStockRenderer<>(context, "freight_flat_cart_wood_logs", RollingStockRenderer.ported("flatcartwood2"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CLOSED_RED_BROWN.get(),
                context -> new RollingStockRenderer<>(context, "freight_closed_red_brown", RollingStockRenderer.ported("freightclosed"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_OPEN_RED_BROWN.get(),
                context -> new RollingStockRenderer<>(context, "freight_open_red_brown", RollingStockRenderer.ported("freightopen2"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_WAGEN_DB.get(),
                context -> new RollingStockRenderer<>(context, "freight_wagen_db", RollingStockRenderer.liveried("freightwagen_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_FLAT_CAR_RAILS_DB.get(),
                context -> new RollingStockRenderer<>(context, "freight_flat_car_rails_db", RollingStockRenderer.liveried("flatcarrails_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_ASTF_AUTORACK.get(),
                context -> new RollingStockRenderer<>(context, "freight_astf_autorack", RollingStockRenderer.ported("astf_autorack"),
                        new float[] {-1.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.65F, 0.6F, -0.35F, 1.0F)), new RollingStockRenderer.Attachment("american_freight_trucks", RollingStockRenderer.texture("americanfreighttrucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 4.25F, 0.6F, -0.35F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_FLAT_CAR_LOGS_DB.get(),
                context -> new RollingStockRenderer<>(context, "freight_flat_car_logs_db", RollingStockRenderer.liveried("flatcarlogs_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_SLATE_WAGON.get(),
                context -> new RollingStockRenderer<>(context, "freight_slate_wagon", RollingStockRenderer.ported("freightslatewagon"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_ICE_WAGON.get(),
                context -> new RollingStockRenderer<>(context, "freight_ice_wagon", RollingStockRenderer.ported("icewagon"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_GS4.get(),
                context -> new RollingStockRenderer<>(context, "freight_cart_gs4", RollingStockRenderer.liveried("gs4_baggage_"),
                        new float[] {0.0F, 0.025F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.8F, 1.0F, 0.8F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("gs4_bogie", RollingStockRenderer.texture("gs4_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_GONDOLA_DB.get(),
                context -> new RollingStockRenderer<>(context, "freight_gondola_db", RollingStockRenderer.liveried("freightgondola_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("freight_gondola_db_part1", RollingStockRenderer.texture("freightgondola_db_green"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("freight_gondola_db_part1", RollingStockRenderer.texture("freightgondola_db_red"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CENTER_BEAM_EMPTY.get(),
                context -> new RollingStockRenderer<>(context, "freight_center_beam_empty", RollingStockRenderer.liveried("freight_centerbeam_empty_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CENTER_BEAM_WOOD1.get(),
                context -> new RollingStockRenderer<>(context, "freight_center_beam_wood1", RollingStockRenderer.liveried("freight_centerbeam_wood_1_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CENTER_BEAM_WOOD2.get(),
                context -> new RollingStockRenderer<>(context, "freight_center_beam_wood2", RollingStockRenderer.liveried("freight_centerbeam_wood_2_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_WELLCAR.get(),
                context -> new RollingStockRenderer<>(context, "freight_wellcar", RollingStockRenderer.liveried("wellcar_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.4F, 0.1F, -0.3375F, 1.0F)), new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.2F, 0.1F, -0.3375F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_TRAILER.get(),
                context -> new RollingStockRenderer<>(context, "freight_trailer", RollingStockRenderer.liveried("freighttrailer_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_DENVER_RIO_GRANGE_2.get(),
                context -> new RollingStockRenderer<>(context, "freight_denver_rio_grange_2", RollingStockRenderer.liveried("drg_baggage_"),
                        new float[] {0.0F, 0.14F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_MILW_BAGGAGE.get(),
                context -> new RollingStockRenderer<>(context, "freight_milw_baggage", RollingStockRenderer.ported("milw_baggage"),
                        new float[] {0.1F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 0.9F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HEAVYWEIGHT.get(),
                context -> new RollingStockRenderer<>(context, "freight_heavyweight", RollingStockRenderer.ported("heavyweight_mailcar"),
                        new float[] {0.1F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_BAMBOO.get(),
                context -> new RollingStockRenderer<>(context, "freight_cart_bamboo", RollingStockRenderer.liveried("bamboo_freight_"),
                        new float[] {0.1F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_GERMAN_POST.get(),
                context -> new RollingStockRenderer<>(context, "freight_german_post", RollingStockRenderer.liveried("german_post_"),
                        new float[] {-1.0F, 0.15F, -0.075F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.75F, 0.2F, -0.5F, 1.0F)), new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 3.57F, 0.2F, -0.55F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_DEPRESSED_FLATBED.get(),
                context -> new RollingStockRenderer<>(context, "freight_depressed_flatbed", RollingStockRenderer.liveried("depressed_flatbed_"),
                        new float[] {-0.1F, 0.15F, -0.075F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.15F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("ft17", RollingStockRenderer.texture("ft17"), RollingStockRenderer.transform(0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.5F, 0.04F, -0.0425F, 1.0F)))), Map.entry("LightGrey", List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.15F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("char_b1", RollingStockRenderer.texture("charb1"), RollingStockRenderer.transform(0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.9F, 0.14F, -0.0425F, 1.0F)))), Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.15F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("gp7_attachment", RollingStockRenderer.texture("gp7_yellow"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.3F, 0.45F, -0.1F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.15F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3F, 0.0F, -0.25F, 1.0F)), new RollingStockRenderer.Attachment("panzer_i", RollingStockRenderer.texture("panzeri"), RollingStockRenderer.transform(0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.55F, 0.0F, 0.45F, 0.025F, -0.11F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CAR_L.get(),
                context -> new RollingStockRenderer<>(context, "freight_car_l", RollingStockRenderer.liveried("freightcarl_"),
                        new float[] {0.0F, 0.2F, 0.825F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.0F, 0.3F, 0.45F, 1.0F)), new RollingStockRenderer.Attachment("wellcar_bogie", RollingStockRenderer.texture("wellcar_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 0.3F, 0.45F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HEAVYWEIGHT_2.get(),
                context -> new RollingStockRenderer<>(context, "freight_heavyweight_2", RollingStockRenderer.liveried("heavyweightboxcar_"),
                        new float[] {0.0F, 0.1F, -0.05F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("heavyweight_bogie", RollingStockRenderer.texture("heavyweightbogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.7F, -0.025F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("heavyweight_bogie", RollingStockRenderer.texture("heavyweightbogie"), RollingStockRenderer.transform(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 3.8F, -0.025F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_ROUND_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_round_hopper", RollingStockRenderer.liveried("roundedhopper_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_truck", RollingStockRenderer.texture("70truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.85F, 0.0F, -2.4F, 0.55F, -0.31875F, 1.0F)), new RollingStockRenderer.Attachment("70_truck", RollingStockRenderer.texture("70truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.85F, 0.0F, 2.33F, 0.55F, -0.31875F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_RIBBED_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_ribbed_hopper", RollingStockRenderer.liveried("ribbed_hopper_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_truck", RollingStockRenderer.texture("70truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -2.13F, 0.5F, -0.36F, 1.0F)), new RollingStockRenderer.Attachment("70_truck", RollingStockRenderer.texture("70truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.87F, 0.5F, -0.333F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP40HIGHCUBE.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap40highcube", RollingStockRenderer.liveried("40highcube_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Brown", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.05F, -0.05F, 0.0F, 1.0F)))), Map.entry("Grey", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.05F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.0F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.05F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_WOODCHIP_HOPPER.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_woodchip_hopper", RollingStockRenderer.liveried("woodchiphopper_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.87F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.86F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_ORE_JENNY.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_ore_jenny", RollingStockRenderer.liveried("orejenny_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Red", List.of(new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.57F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, -0.05F, 0.0F, 1.0F)))), Map.entry("Pink", List.of(new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.57F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2_journal_boxes"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.57F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ore_jenny_truck2", RollingStockRenderer.texture("ore_jenny_truck2_journal_boxes"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.6F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_MILL_GONDOLA.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_mill_gondola", RollingStockRenderer.liveried("millgondola_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.6F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.6F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_MILW40BOXCAR.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_milw40boxcar", RollingStockRenderer.liveried("milw40_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.18F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.19F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.18F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.19F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP60CENTERBEAM.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap60centerbeam", RollingStockRenderer.liveried("60ftcb_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.23F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.22F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.23F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.22F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP66CENTERBEAM.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap66centerbeam", RollingStockRenderer.liveried("66ftcb_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.57F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.58F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.57F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.58F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP73CENTERBEAM.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap73centerbeam", RollingStockRenderer.liveried("73ftcb_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Green", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.73F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.72F, -0.05F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.73F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.72F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_PS140.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_ps140", RollingStockRenderer.liveried("ps140_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.13F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.12F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_PS150.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_ps150", RollingStockRenderer.liveried("ps150_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Lime", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.435F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.435F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.435F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.435F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_PS160.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_ps160", RollingStockRenderer.liveried("ps160_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.65F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.6F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_VERSA_LONGI.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_versa_longi", RollingStockRenderer.liveried("versa_longi_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.25F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BAP_VERSA_TRANS.get(),
                context -> new RollingStockRenderer<>(context, "freight_bap_versa_trans", RollingStockRenderer.liveried("versa_trans_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.25F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HICUBE60FOOT.get(),
                context -> new RollingStockRenderer<>(context, "freight_hicube60foot", RollingStockRenderer.liveried("hicube60_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Pink", List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BNSF_GON.get(),
                context -> new RollingStockRenderer<>(context, "freight_bnsf_gon", RollingStockRenderer.liveried("bnsfmillgon_"),
                        new float[] {0.09375F, 0.1875F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.65625F, 0.0F, -0.1875F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.4375F, 0.0F, -0.1875F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HOPPER5201.get(),
                context -> new RollingStockRenderer<>(context, "freight_hopper5201", RollingStockRenderer.liveried("5201_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.58F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.55F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HOPPER6260.get(),
                context -> new RollingStockRenderer<>(context, "freight_hopper6260", RollingStockRenderer.liveried("6260_"),
                        new float[] {-0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.93F, -0.05F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.94F, -0.05F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_SKELETON.get(),
                context -> new RollingStockRenderer<>(context, "freight_skeleton", RollingStockRenderer.liveried("skeletonlogcar_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("friction_truck", RollingStockRenderer.texture("frictiontruck_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.25F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("friction_truck", RollingStockRenderer.texture("frictiontruck_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_PS73_BAGGAGE.get(),
                context -> new RollingStockRenderer<>(context, "freight_ps73_baggage", RollingStockRenderer.liveried("ps_baggage_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.1F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_PS85_BAGGAGE.get(),
                context -> new RollingStockRenderer<>(context, "freight_ps85_baggage", RollingStockRenderer.liveried("ps_lightweight_85_baggage_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_REEFER64.get(),
                context -> new RollingStockRenderer<>(context, "freight_reefer64", RollingStockRenderer.liveried("reefer_64_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.9F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.85F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_PSRPOPM.get(),
                context -> new RollingStockRenderer<>(context, "freight_psrpopm", RollingStockRenderer.liveried("psrpopm_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.1F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_PSRPO.get(),
                context -> new RollingStockRenderer<>(context, "freight_psrpo", RollingStockRenderer.liveried("psrpo_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.1F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.1F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.1F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BOULDER_WAGON.get(),
                context -> new RollingStockRenderer<>(context, "freight_boulder_wagon", RollingStockRenderer.liveried("boulderwagon_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_GSI60_FOOT_BULKHEAD.get(),
                context -> new RollingStockRenderer<>(context, "freight_gsi60_foot_bulkhead", RollingStockRenderer.liveried("gsi_60bulk_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck_early", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.05F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck_early", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.05F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_GSC60_FOOT_FLATCAR.get(),
                context -> new RollingStockRenderer<>(context, "freight_gsc60_foot_flatcar", RollingStockRenderer.liveried("gsc_60flat_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck_early", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.05F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck_early", RollingStockRenderer.texture("70ton_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.05F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_5_PLANK.get(),
                context -> new RollingStockRenderer<>(context, "freight_5_plank", RollingStockRenderer.liveried("5_plank_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_BR_MK1_TPO_STOWAGE.get(),
                context -> new RollingStockRenderer<>(context, "freight_br_mk1_tpo_stowage", RollingStockRenderer.liveried("br_mk1_tpo_"),
                        new float[] {0.0F, 0.0F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_ACFGNRPO_30.get(),
                context -> new RollingStockRenderer<>(context, "freight_acfgnrpo_30", RollingStockRenderer.liveried("acf_lightweight_gnrpo30_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_HOPPER_UK.get(),
                context -> new RollingStockRenderer<>(context, "freight_hopper_uk", RollingStockRenderer.liveried("hopper_uk_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_EXPRESS_FREIGHT_VAN.get(),
                context -> new RollingStockRenderer<>(context, "freight_express_freight_van", RollingStockRenderer.liveried("express_freight_van_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_TIPPER_UK.get(),
                context -> new RollingStockRenderer<>(context, "freight_tipper_uk", RollingStockRenderer.liveried("tipper_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_MINERALWAGON.get(),
                context -> new RollingStockRenderer<>(context, "freight_mineralwagon", RollingStockRenderer.liveried("mineral_wagon_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_VENTILATED_VAN.get(),
                context -> new RollingStockRenderer<>(context, "freight_ventilated_van", RollingStockRenderer.liveried("ventilated_van_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_WAGON_DB.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_wagon_db", RollingStockRenderer.liveried("tankwagon_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_THREE_DOME.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_three_dome", RollingStockRenderer.liveried("tanker_three_dome_"),
                        new float[] {0.0F, 0.17F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.6F, 0.0F, -0.175F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.2F, 0.0F, -0.175F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_WAGON_US.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_wagon_us", RollingStockRenderer.liveried("tankwagonus_"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_WAGON_GREY.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_wagon_grey", RollingStockRenderer.ported("tankwagon2"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_CART_LAVA.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_cart_lava", RollingStockRenderer.liveried("lavacar_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_WAGON_YELLOW.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_wagon_yellow", RollingStockRenderer.ported("tankwagon"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_BAP_DOT11111000.get(),
                context -> new RollingStockRenderer<>(context, "tank_bap_dot11111000", RollingStockRenderer.liveried("dot11k_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -0.87F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.86F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_BAP_DOT11120600.get(),
                context -> new RollingStockRenderer<>(context, "tank_bap_dot11120600", RollingStockRenderer.liveried("dot206k_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.13F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.12F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_BAP_DOT11129080.get(),
                context -> new RollingStockRenderer<>(context, "tank_bap_dot11129080", RollingStockRenderer.liveried("dot290k_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.73F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("70_ton_truck2", RollingStockRenderer.texture("70ton_greyish"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.72F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_TANK_TANKER_UK.get(),
                context -> new RollingStockRenderer<>(context, "tank_tank_tanker_uk", RollingStockRenderer.liveried("tanker_uk_"),
                        new float[] {0.0F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_RHEINGOLD_DINING1.get(),
                context -> new RollingStockRenderer<>(context, "work_rheingold_dining1", RollingStockRenderer.liveried("rheingold_passenger_dining1_"),
                        new float[] {1.7F, 0.15F, -0.6F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.55F, 0.2F, -1.045F, 1.0F)), new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.7F, 0.2F, -1.045F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_RHEINGOLD_DINING2.get(),
                context -> new RollingStockRenderer<>(context, "work_rheingold_dining2", RollingStockRenderer.liveried("rheingold_passenger_dining2_"),
                        new float[] {0.05F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -3.0F, 0.2F, -0.375F, 1.0F)), new RollingStockRenderer.Attachment("rheingold_bogie", RollingStockRenderer.texture("rheingold_bogie"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.3F, 0.2F, -0.375F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_GWR_BRAKE_VAN.get(),
                context -> new RollingStockRenderer<>(context, "work_gwr_brake_van", RollingStockRenderer.ported("gwrbrakevan"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_WORK_CART.get(),
                context -> new RollingStockRenderer<>(context, "work_work_cart", RollingStockRenderer.ported("workcart"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_WORK_CABOOSE.get(),
                context -> new RollingStockRenderer<>(context, "work_work_caboose", RollingStockRenderer.ported("workcaboose"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_CABOOSE_LOGGING.get(),
                context -> new RollingStockRenderer<>(context, "work_caboose_logging", RollingStockRenderer.liveried("cablogging_"),
                        new float[] {0.0F, -0.45F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_CABOOSE_LOGGING_PRR.get(),
                context -> new RollingStockRenderer<>(context, "work_caboose_logging_prr", RollingStockRenderer.liveried("prrcaboose_"),
                        new float[] {0.0F, -0.38F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bettendorf_trucks", RollingStockRenderer.texture("bettendorf_trucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.25F, 0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bettendorf_trucks", RollingStockRenderer.texture("bettendorf_trucks"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.25F, 0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.WORK_MAIL_WAGEN_DB.get(),
                context -> new RollingStockRenderer<>(context, "work_mail_wagen_db", RollingStockRenderer.ported("mailwagen_db"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_STOCK_CAR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_stock_car", RollingStockRenderer.liveried("stockcar_"),
                        new float[] {-0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DRWG_STOCK_CAR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_drwg_stock_car", RollingStockRenderer.ported("drwgstockcar"),
                        new float[] {1.0F, -0.4F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_JUKE_BOX_CART.get(),
                context -> new RollingStockRenderer<>(context, "passenger_juke_box_cart", RollingStockRenderer.ported("jukebox"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_TRACKS_BUILDER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_tracks_builder", RollingStockRenderer.working("builder2", "builder"),
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("passenger_tracks_builder_part1", RollingStockRenderer.texture("builder_rotor"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CATTLE_VAN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cattle_van", RollingStockRenderer.ported("cattle_van"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CABOOSE_RED.get(),
                context -> new RollingStockRenderer<>(context, "passenger_caboose_red", RollingStockRenderer.ported("caboose"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CABOOSE_BLACK.get(),
                context -> new RollingStockRenderer<>(context, "passenger_caboose_black", RollingStockRenderer.ported("caboose3"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BAP_W_VCABOOSE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bap_w_vcaboose", RollingStockRenderer.liveried("wvcaboose_"),
                        new float[] {0.0F, 0.1675F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("caboosetruck", RollingStockRenderer.texture("cb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.03125F, 0.5625F, -0.36F, 1.0F)), new RollingStockRenderer.Attachment("caboosetruck", RollingStockRenderer.texture("cb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.78125F, 0.5625F, -0.333F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_DRGW_CABOOSE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_drgw_caboose", RollingStockRenderer.liveried("bap_drgw_01400_"),
                        new float[] {-0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("caboose_truck2", RollingStockRenderer.texture("swing-motion_caboose_truck"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.26F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("caboose_truck2", RollingStockRenderer.texture("swing-motion_caboose_truck_left-generator"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.26F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FLAT_CART.get(),
                context -> new RollingStockRenderer<>(context, "passenger_flat_cart", RollingStockRenderer.ported("flatcart"),
                        new float[] {0.0F, -0.32F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FLAT_CART_SU.get(),
                context -> new RollingStockRenderer<>(context, "passenger_flat_cart_su", RollingStockRenderer.ported("flatcarsu"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FLAT_CART_US.get(),
                context -> new RollingStockRenderer<>(context, "passenger_flat_cart_us", RollingStockRenderer.ported("flatcartus"),
                        new float[] {0.0F, -0.47F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_FLAT_CAR_DB.get(),
                context -> new RollingStockRenderer<>(context, "passenger_flat_car_db", RollingStockRenderer.liveried("flatcar_db_"),
                        new float[] {0.0F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PROPAGANDA_US.get(),
                context -> new RollingStockRenderer<>(context, "passenger_propaganda_us", RollingStockRenderer.liveried("propaganda_us_"),
                        new float[] {1.1F, 0.2F, -0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.5F, 0.0F, -0.27F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.9F, 0.0F, -0.27F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PROPAGANDA_USSR.get(),
                context -> new RollingStockRenderer<>(context, "passenger_propaganda_ussr", RollingStockRenderer.liveried("propaganda_ussr_"),
                        new float[] {1.1F, 0.2F, -0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.5F, 0.0F, -0.27F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.9F, 0.0F, -0.27F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PROPAGANDA_JAPAN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_propaganda_japan", RollingStockRenderer.liveried("propaganda_japan_"),
                        new float[] {1.1F, 0.2F, -0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.5F, 0.0F, -0.27F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.9F, 0.0F, -0.27F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PROPAGANDA_BRITAIN.get(),
                context -> new RollingStockRenderer<>(context, "passenger_propaganda_britain", RollingStockRenderer.liveried("propaganda_britain_"),
                        new float[] {1.1F, 0.2F, -0.1F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -4.5F, 0.0F, -0.27F, 1.0F)), new RollingStockRenderer.Attachment("freight_truck_m", RollingStockRenderer.texture("freighttruckm"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.9F, 0.0F, -0.27F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_B_UNIT_EMDF7.get(),
                context -> new RollingStockRenderer<>(context, "tank_b_unit_emdf7", RollingStockRenderer.liveried("emdf7b_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.8F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_B_UNIT_EMDF3.get(),
                context -> new RollingStockRenderer<>(context, "tank_b_unit_emdf3", RollingStockRenderer.liveried("emdf3b_"),
                        new float[] {0.0F, 0.1F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.8F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bloomberg_trucks", RollingStockRenderer.texture("blomberg_b_trucks"), RollingStockRenderer.transform(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.TANK_B_UNIT_DD35.get(),
                context -> new RollingStockRenderer<>(context, "tank_b_unit_dd35", RollingStockRenderer.liveried("dd35b_"),
                        new float[] {0.0F, 0.18F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {0.9F, 1.0F, 0.9F},
                        RollingStockRenderer.attachments(List.of()), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BAP_P_ECOACH.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bap_p_ecoach", RollingStockRenderer.liveried("pecoach_"),
                        new float[] {0.0F, 0.105F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("petrucc", RollingStockRenderer.texture("pe_truccs"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.18F, -0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("petrucc", RollingStockRenderer.texture("pe_truccs"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.12F, -0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_BAP_P_EOBSERVE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_bap_p_eobserve", RollingStockRenderer.liveried("peobserve_"),
                        new float[] {0.0F, 0.105F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("petrucc", RollingStockRenderer.texture("pe_truccs"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.18F, -0.1F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("petrucc", RollingStockRenderer.texture("pe_truccs"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.12F, -0.1F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_PS_COMBINE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_ps_combine", RollingStockRenderer.liveried("ps_lightweight_baggage-coach_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Orange", List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_silver"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -2.7F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("ps_truck", RollingStockRenderer.texture("41-n-11_truck_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 2.8F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_LIGHT_CRANE.get(),
                context -> new RollingStockRenderer<>(context, "passenger_light_crane", RollingStockRenderer.liveried("lightcrane2_"),
                        new float[] {0.0F, 0.15F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of(new RollingStockRenderer.Attachment("buckeye3axletruck", RollingStockRenderer.texture("buckeye_3axle_black_friccbearing"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, -1.0F, 0.0F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("buckeye3axletruck", RollingStockRenderer.texture("buckeye_3axle_black_friccbearing"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_NRE3GS21B.get(),
                context -> new RollingStockRenderer<>(context, "nre3gs21b", RollingStockRenderer.liveried("3gs21b_"),
                        new float[] {-1.5F, 0.155F, 0.0F}, new float[] {0.0F, 180.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.byColour(Map.ofEntries(Map.entry("Yellow", List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_spooki_up_trash"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_spooki_up_trash"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.45F, 0.15F, 0.0F, 1.0F))))), List.of(new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, -1.55F, 0.15F, 0.0F, 1.0F)), new RollingStockRenderer.Attachment("bap_blomberg_b", RollingStockRenderer.texture("blombergb_black"), RollingStockRenderer.transform(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.45F, 0.15F, 0.0F, 1.0F)))), null));
        event.registerEntityRenderer(
                EntityRegistry.PASSENGER_CQ310_PASSENGER.get(),
                context -> new RollingStockRenderer<>(context, "passenger_cq310_passenger", RollingStockRenderer.liveried("cq_310_"),
                        new float[] {0.0F, 0.2F, 0.0F}, new float[] {0.0F, 0.0F, 180.0F}, new float[] {1.0F, 1.0F, 1.0F},
                        RollingStockRenderer.attachments(List.of()), null));
    }
}
