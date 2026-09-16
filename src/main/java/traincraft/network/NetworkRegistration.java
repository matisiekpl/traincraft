package traincraft.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import traincraft.Traincraft;
import traincraft.adminbook.AdminBookPayload;
import traincraft.adminbook.AdminBookRequestPayload;
import traincraft.production.ProductionRegistry;

/**
 * Payload registration.
 *
 * <p>Both payloads travel client to server only. Nothing goes the other way: the gauges the client
 * needs are on the entity's synched data, which is already replicated to everyone tracking it, and
 * a second channel carrying the same numbers would be one more thing to keep in step.
 */
@EventBusSubscriber(modid = Traincraft.MODID)
public final class NetworkRegistration {

    private NetworkRegistration() {}

    @SubscribeEvent
    static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                LocomotiveKeyPayload.TYPE,
                LocomotiveKeyPayload.CODEC,
                LocomotiveKeyPayload::handle);
        registrar.playToServer(
                LocomotiveActionPayload.TYPE,
                LocomotiveActionPayload.CODEC,
                LocomotiveActionPayload::handle);
        registrar.playToServer(
                StockLockPayload.TYPE, StockLockPayload.CODEC, StockLockPayload::handle);
        registrar.playToServer(LiveryPayload.TYPE, LiveryPayload.CODEC, LiveryPayload::handle);
        registrar.playToServer(EngineNumberPayload.TYPE, EngineNumberPayload.CODEC, EngineNumberPayload::handle);
        registrar.playToClient(RecipeBookPayload.TYPE, RecipeBookPayload.CODEC, RecipeBookPayload::handle);
        registrar.playToClient(AdminBookPayload.TYPE, AdminBookPayload.CODEC, AdminBookPayload::handle);
        registrar.playToServer(AdminBookRequestPayload.TYPE, AdminBookRequestPayload.CODEC, AdminBookRequestPayload::handle);
        registrar.playBidirectional(StructureActionPayload.TYPE, StructureActionPayload.CODEC,
                StructureActionPayload::handleServer, StructureActionPayload::handleClient);
    }

    @SubscribeEvent
    static void sendRecipes(OnDatapackSyncEvent event) {
        event.sendRecipes(ProductionRegistry.RECIPE_TYPE.get());
    }
}
