package traincraft.client;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import traincraft.Traincraft;
import traincraft.vehicle.entity.LocomotiveEntity;

@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class RidingCamera {

    private static Entity vehicleWas;
    private static CameraType cameraWas;

    private RidingCamera() {}

    @SubscribeEvent
    static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Entity vehicle = minecraft.player == null ? null : minecraft.player.getVehicle();
        if (vehicle == vehicleWas) {
            return;
        }
        if (cameraWas != null && minecraft.options.getCameraType() == CameraType.THIRD_PERSON_BACK) {
            minecraft.options.setCameraType(cameraWas);
        }
        cameraWas = null;
        vehicleWas = vehicle;
        if (!TraincraftClientConfig.AUTO_THIRD_PERSON.get()) {
            return;
        }
        if (vehicle instanceof LocomotiveEntity) {
            cameraWas = minecraft.options.getCameraType();
            minecraft.options.setCameraType(CameraType.THIRD_PERSON_BACK);
        }
    }
}
