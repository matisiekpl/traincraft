package traincraft.client;

import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import traincraft.Traincraft;
import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.bootstrap.EntityRegistry;
import traincraft.bootstrap.MenuRegistry;
import traincraft.client.render.AliceRenderer;
import traincraft.client.render.Br80Renderer;
import traincraft.client.render.RollingStockRenderer;
import traincraft.client.render.TrackRenderer;
import traincraft.client.render.obj.ObjMeshManager;
import traincraft.client.screen.LocomotiveHud;
import traincraft.client.screen.FreightScreen;
import traincraft.vehicle.inventory.FreightMenu;
import traincraft.client.screen.LocomotiveScreen;

/**
 * Client-side registration. Reached only through {@link TraincraftClient}, so nothing here is ever
 * classloaded on a dedicated server.
 */
@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class ClientRegistration {

    private static final Identifier OBJ_MODELS =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "obj_models");

    private ClientRegistration() {}

    @SubscribeEvent
    static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.TC_RAIL.get(), TrackRenderer::new);
        event.registerBlockEntityRenderer(traincraft.structure.StructureRegistry.STRUCTURE.get(), traincraft.client.render.structure.StructureRenderer::new);
        event.registerBlockEntityRenderer(traincraft.structure.StructureRegistry.GENERATOR.get(), traincraft.client.render.structure.StructureRenderer::new);
        event.registerBlockEntityRenderer(traincraft.structure.StructureRegistry.CONTAINER.get(), traincraft.client.render.structure.StructureRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ZEPPELIN.get(), traincraft.client.render.structure.ZeppelinRenderer::new);
        event.registerEntityRenderer(EntityRegistry.AIRSHIP.get(), traincraft.client.render.structure.ZeppelinRenderer::new);
        // The gag block draws nothing, so it has no renderer: its geometry belongs to the rail
        // that owns the assembly.
        event.registerEntityRenderer(EntityRegistry.LOCO_STEAM_ALICE.get(), AliceRenderer::new);
        event.registerEntityRenderer(EntityRegistry.LOCO_STEAM_BR80.get(), Br80Renderer::new);
        event.registerEntityRenderer(EntityRegistry.LOCO_ELECTRIC_BR185.get(), traincraft.client.render.Br185Renderer::new);
        event.registerEntityRenderer(EntityRegistry.LOCO_DIESEL_ES44.get(), traincraft.client.render.Es44Renderer::new);
        event.registerEntityRenderer(EntityRegistry.LOCO_ELECTRIC_E10.get(), traincraft.client.render.E10Renderer::new);
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SD40.get(),
                context -> new RollingStockRenderer<>(context, "sd40", "locosd40_",
                        new float[] {-1.2F, -0.47F, 0.0F}, new float[] {180.0F, 90.0F, 0.0F}, new float[] {-1.0F, -1.0F, 1.0F}));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_SD70.get(),
                context -> new RollingStockRenderer<>(context, "sd70", "locosd70_",
                        new float[] {-1.2F, -0.44F, 0.0F}, new float[] {0.0F, 90.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F}));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_DIESEL_V60.get(),
                context -> new RollingStockRenderer<>(context, "v60", "locov60_db_",
                        new float[] {-0.75F, -0.44F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F}));
        event.registerEntityRenderer(
                EntityRegistry.LOCO_ELECTRIC_BR_E69.get(),
                context -> new RollingStockRenderer<>(context, "br_e69", "locobr_e69_",
                        new float[] {0.0F, -0.42F, 0.0F}, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F, 1.0F}));
        traincraft.client.render.FleetRenderers.register(event);
        event.registerEntityRenderer(
                EntityRegistry.FREIGHT_CART_YELLOW.get(),
                context ->
                        new RollingStockRenderer<>(
                                context,
                                "freightcart",
                                net.minecraft.resources.Identifier.fromNamespaceAndPath(
                                        Traincraft.MODID, "textures/trains/freightcart.png"),
                                new float[] {0.0F, -0.32F, 0.0F},
                                new float[] {0.0F, 0.0F, 0.0F},
                                new float[] {1.0F, 1.0F, 1.0F}));
    }

    /**
     * Screens are bound here rather than in the mod constructor because the menu type has to be
     * registered before anything can be attached to it.
     */
    @SubscribeEvent
    static void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(LocomotiveHud.ID, new LocomotiveHud());
    }

    @SubscribeEvent
    static void onRegisterScreens(RegisterMenuScreensEvent event) {
        event.register(MenuRegistry.LOCO.get(), LocomotiveScreen::new);
        event.register(MenuRegistry.MACHINE.get(), traincraft.client.screen.MachineScreen::new);
        event.register(MenuRegistry.FREIGHT.get(), FreightScreen<FreightMenu>::new);
        event.register(MenuRegistry.TENDER.get(), traincraft.client.screen.TenderScreen::new);
        event.register(MenuRegistry.TANK.get(), traincraft.client.screen.TankScreen::new);
        event.register(MenuRegistry.WORK_CART.get(), traincraft.client.screen.WorkCartScreen::new);
        event.register(MenuRegistry.TRACKS_BUILDER.get(), traincraft.client.screen.TracksBuilderScreen::new);
        event.register(MenuRegistry.ZEPPELIN.get(), traincraft.client.screen.ZeppelinScreen::new);
        event.register(traincraft.structure.StructureRegistry.DIESEL_GENERATOR_MENU.get(), traincraft.client.screen.DieselGeneratorScreen::new);
    }

    @SubscribeEvent
    static void onRegisterSpecialModelRenderers(net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent event) {
        event.register(traincraft.client.render.structure.StructureSpecialRenderer.ID, traincraft.client.render.structure.StructureSpecialRenderer.Unbaked.MAP_CODEC);
    }

    @SubscribeEvent
    static void onRegisterFluidModels(net.neoforged.neoforge.client.event.RegisterFluidModelsEvent event) {
        for (var entry : new traincraft.structure.FluidRegistry.Entry[] {traincraft.structure.FluidRegistry.DIESEL, traincraft.structure.FluidRegistry.REFINED_FUEL}) {
            String name = entry.source().getId().getPath();
            event.register(new net.minecraft.client.renderer.block.FluidModel.Unbaked(
                    new net.minecraft.client.resources.model.sprite.Material(Identifier.fromNamespaceAndPath(Traincraft.MODID, "block/" + name)),
                    new net.minecraft.client.resources.model.sprite.Material(Identifier.fromNamespaceAndPath(Traincraft.MODID, "block/" + name + "_flow")),
                    null, (net.neoforged.neoforge.client.fluid.FluidTintSource) null), entry.source(), entry.flowing());
        }
    }

    @SubscribeEvent
    static void onAddReloadListeners(AddClientReloadListenersEvent event) {
        // Every listener needs an Identifier key in 26.2. With no declared dependency this runs
        // after the vanilla listeners, which is what we want -- the meshes are independent of
        // the texture atlas.
        event.addListener(OBJ_MODELS, ObjMeshManager.INSTANCE);
        event.addListener(
                Identifier.fromNamespaceAndPath(Traincraft.MODID, "vehicle_models"),
                traincraft.client.render.models.VehicleModels.INSTANCE);
        event.addListener(
                Identifier.fromNamespaceAndPath(Traincraft.MODID, "structure_models"),
                traincraft.client.render.structure.StructureTransforms.INSTANCE);
    }
}
