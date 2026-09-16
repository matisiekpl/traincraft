package traincraft.client;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import traincraft.Traincraft;
import traincraft.network.LocomotiveActionPayload;
import traincraft.network.LocomotiveKeyPayload;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.ZeppelinEntity;

@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class KeyBindings {

    /** 26.2 identifies a controls-screen category by Identifier, not by translation key. */
    public static final KeyMapping.Category CATEGORY =
            new KeyMapping.Category(
                    net.minecraft.resources.Identifier.fromNamespaceAndPath(
                            Traincraft.MODID, "traincraft"));

    public static final KeyMapping OPEN_MENU =
            new KeyMapping(
                    "key.traincraft.inventory",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_R,
                    CATEGORY);

    public static final KeyMapping HORN =
            new KeyMapping(
                    "key.traincraft.horn",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_H,
                    CATEGORY);

    public static final KeyMapping UP =
            new KeyMapping("key.traincraft.up", InputConstants.Type.KEYSYM, InputConstants.KEY_Y, CATEGORY);

    public static final KeyMapping DOWN =
            new KeyMapping("key.traincraft.down", InputConstants.Type.KEYSYM, InputConstants.KEY_X, CATEGORY);

    public static final KeyMapping IDLE =
            new KeyMapping("key.traincraft.idle", InputConstants.Type.KEYSYM, InputConstants.KEY_C, CATEGORY);

    public static final KeyMapping BOMB =
            new KeyMapping("key.traincraft.bomb", InputConstants.Type.KEYSYM, InputConstants.KEY_B, CATEGORY);

    private KeyBindings() {}

    @SubscribeEvent
    static void register(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        event.register(OPEN_MENU);
        event.register(HORN);
        event.register(UP);
        event.register(DOWN);
        event.register(IDLE);
        event.register(BOMB);
    }

    @SubscribeEvent
    static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.gui.screen() != null) {
            return;
        }
        // consumeClick, not isDown: a press opens the menu once, and the menu that opens must not
        // see the key still held and close itself again on the next tick.
        while (OPEN_MENU.consumeClick()) {
            if (minecraft.player.getVehicle() instanceof LocomotiveEntity loco) {
                ClientPacketDistributor.sendToServer(
                        new LocomotiveActionPayload(
                                loco.getId(), LocomotiveActionPayload.ACTION_OPEN_MENU));
            }
            zeppelinKey(minecraft, ZeppelinEntity.KEY_MENU);
        }
        while (UP.consumeClick()) {
            zeppelinKey(minecraft, ZeppelinEntity.KEY_ASCEND);
        }
        while (DOWN.consumeClick()) {
            zeppelinKey(minecraft, ZeppelinEntity.KEY_DESCEND);
        }
        while (IDLE.consumeClick()) {
            zeppelinKey(minecraft, ZeppelinEntity.KEY_IDLE);
        }
        while (BOMB.consumeClick()) {
            zeppelinKey(minecraft, ZeppelinEntity.KEY_BOMB);
        }
        while (HORN.consumeClick()) {
            if (minecraft.player.getVehicle() instanceof LocomotiveEntity loco) {
                ClientPacketDistributor.sendToServer(
                        new LocomotiveActionPayload(
                                loco.getId(), LocomotiveActionPayload.ACTION_HORN));
            }
        }
    }

    private static void zeppelinKey(Minecraft minecraft, int key) {
        if (minecraft.player.getVehicle() instanceof ZeppelinEntity zeppelin) {
            ClientPacketDistributor.sendToServer(new LocomotiveKeyPayload(zeppelin.getId(), key, true));
        }
    }
}
