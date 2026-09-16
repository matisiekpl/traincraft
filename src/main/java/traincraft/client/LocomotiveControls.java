package traincraft.client;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.Traincraft;
import traincraft.network.LocomotiveKeyPayload;
import traincraft.vehicle.entity.KeyControlled;
import traincraft.vehicle.entity.LocomotiveEntity;

@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class LocomotiveControls {

    private static boolean forwardWas;
    private static boolean backwardWas;
    private static boolean brakeWas;

    private LocomotiveControls() {}

    @SubscribeEvent
    static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.gui.screen() != null) {
            // With a screen open the keys belong to the screen. Releasing them here rather than
            // simply ignoring them stops a locomotive accelerating forever because the player
            // opened their inventory mid-throttle.
            releaseAll(minecraft);
            return;
        }
        if (!(minecraft.player.getVehicle() instanceof KeyControlled loco)) {
            releaseAll(minecraft);
            return;
        }

        forwardWas =
                send(
                        loco,
                        LocomotiveEntity.KEY_FORWARD,
                        minecraft.options.keyUp.isDown(),
                        forwardWas);
        backwardWas =
                send(
                        loco,
                        LocomotiveEntity.KEY_BACKWARD,
                        minecraft.options.keyDown.isDown(),
                        backwardWas);
        brakeWas =
                send(
                        loco,
                        LocomotiveEntity.KEY_BRAKE,
                        minecraft.options.keyJump.isDown(),
                        brakeWas);
    }

    private static boolean send(KeyControlled loco, int key, boolean isDown, boolean was) {
        if (isDown != was) {
            ClientPacketDistributor.sendToServer(
                    new LocomotiveKeyPayload(((net.minecraft.world.entity.Entity) loco).getId(), key, isDown));
        }
        return isDown;
    }

    /** Lets go of everything, and tells the server once rather than every tick. */
    private static void releaseAll(Minecraft minecraft) {
        if (!forwardWas && !backwardWas && !brakeWas) {
            return;
        }
        if (minecraft.player != null
                && minecraft.player.getVehicle() instanceof KeyControlled loco) {
            if (forwardWas) {
                ClientPacketDistributor.sendToServer(
                        new LocomotiveKeyPayload(
                                ((net.minecraft.world.entity.Entity) loco).getId(), LocomotiveEntity.KEY_FORWARD, false));
            }
            if (backwardWas) {
                ClientPacketDistributor.sendToServer(
                        new LocomotiveKeyPayload(
                                ((net.minecraft.world.entity.Entity) loco).getId(), LocomotiveEntity.KEY_BACKWARD, false));
            }
            if (brakeWas) {
                ClientPacketDistributor.sendToServer(
                        new LocomotiveKeyPayload(((net.minecraft.world.entity.Entity) loco).getId(), LocomotiveEntity.KEY_BRAKE, false));
            }
        }
        forwardWas = false;
        backwardWas = false;
        brakeWas = false;
    }
}
