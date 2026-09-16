package traincraft.development.scenario;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import traincraft.Traincraft;

/**
 * The player's side of the game, driven from the client.
 *
 * <p>Distinct from {@link PlayerActions}, which reaches the server directly. Everything here goes
 * through {@link MultiPlayerGameMode} -- the class a mouse button ends up in -- so it travels the
 * whole way: client screen, packet, server, and back. That difference is not academic. Reaching the
 * server directly cannot tell you whether an item is in the creative tab, whether it has an icon,
 * whether picking it puts it in your hand, or whether a right-click at a block face even reaches
 * the item's own code. Those are the first four things a player notices, and all four were broken
 * here at once while the server-side path reported success.
 *
 * <p>Not a simulation of the mouse: a headless client has no cursor, and the pixel a click lands on
 * is not what is under test. What a click <em>becomes</em> is a call into these methods with these
 * arguments, and that is what is driven.
 */
public final class ClientPlayerActions {

    private ClientPlayerActions() {}

    /**
     * Opens the creative inventory on a tab, by name.
     *
     * <p>The tab is chosen through the screen's own static selection, which is what its constructor
     * reads. There is no public way to ask for a tab -- see the one line in {@code
     * accesstransformer.cfg} and why it is there.
     */
    public static boolean openCreativeTab(Minecraft minecraft, String tabId) {
        LocalPlayer player = minecraft.player;
        if (player == null) {
            return false;
        }
        Identifier wanted = Identifier.parse(tabId);
        CreativeModeTab found = null;
        for (CreativeModeTab tab : CreativeModeTabs.allTabs()) {
            var key = BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab);
            if (wanted.equals(key)) {
                found = tab;
                break;
            }
        }
        if (found == null) {
            Traincraft.LOGGER.error(
                    "openCreativeTab: no tab {}; the mod may not have registered one", tabId);
            return false;
        }
        CreativeModeInventoryScreen.selectedTab = found;
        minecraft.gui.setScreen(
                new CreativeModeInventoryScreen(
                        player,
                        player.connection.enabledFeatures(),
                        player.canUseGameMasterBlocks()));
        Traincraft.LOGGER.info(
                "opened the creative tab {} with {} entries",
                tabId,
                found.getDisplayItems().size());
        return true;
    }

    /** Lists what the open tab is actually offering, which is the thing under test. */
    public static void logTabContents(String tabId) {
        for (CreativeModeTab tab : CreativeModeTabs.allTabs()) {
            if (Identifier.parse(tabId).equals(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab))) {
                StringBuilder sb = new StringBuilder();
                for (ItemStack stack : tab.getDisplayItems()) {
                    sb.append("\n    ")
                            .append(BuiltInRegistries.ITEM.getKey(stack.getItem()))
                            .append("  \"")
                            .append(stack.getHoverName().getString())
                            .append('"');
                }
                Traincraft.LOGGER.info("creative tab {} offers{}", tabId, sb);
                return;
            }
        }
    }

    /**
     * Takes an item from the creative menu into a hotbar slot, as dragging it there does.
     *
     * <p>Goes through {@code handleCreativeModeItemAdd}, the same call the screen makes, so the
     * server gets the same packet. Setting the inventory slot directly would leave the server's
     * copy empty and the next right-click would do nothing.
     */
    /**
     * Clicks a slot in whatever screen is open, the way a mouse does.
     *
     * <p>Goes through {@code handleContainerInput}, so the click travels to the server as a packet
     * and the menu's own {@code quickMoveStack} decides where the stack lands. Writing to the
     * container directly would test nothing: a slot that refuses the item, or a shift-click that
     * sends it to the wrong place, both look like success from the inside.
     *
     * @param slot index in the open menu, not in the player's inventory
     * @param quick true to shift-click
     */
    /**
     * Holds or releases one of the driving keys, or presses the menu key once.
     *
     * <p>Sets the same field the keyboard sets, so {@link traincraft.client.LocomotiveControls}
     * sees exactly what it would see from a player's hand and sends the same packets. Reaching into
     * the locomotive from here instead would prove only that the entity works, not that a key gets
     * to it.
     */
    public static boolean key(Minecraft minecraft, String which, boolean down) {
        KeyMapping mapping =
                switch (which) {
                    case "forward" -> minecraft.options.keyUp;
                    case "backward" -> minecraft.options.keyDown;
                    case "brake" -> minecraft.options.keyJump;
                    case "menu" -> traincraft.client.KeyBindings.OPEN_MENU;
                    default -> null;
                };
        if (mapping == null) {
            Traincraft.LOGGER.error("key: no binding called {}", which);
            return false;
        }
        if ("menu".equals(which)) {
            KeyMapping.click(mapping.getKey());
        } else {
            mapping.setDown(down);
        }
        Traincraft.LOGGER.info(
                "key {} {}", which, "menu".equals(which) ? "pressed" : (down ? "down" : "up"));
        return true;
    }

    /**
     * Presses a button on the open screen, found by the label it shows.
     *
     * <p>Through the widget's own {@code onPress}, so the payload, its guards and the state change
     * are all exercised; the label is what a player reads off the screen.
     */
    public static boolean clickButton(Minecraft minecraft, String label) {
        if (minecraft.gui.screen() == null) {
            Traincraft.LOGGER.error("clickButton: no screen open");
            return false;
        }
        StringBuilder seen = new StringBuilder();
        for (var child : minecraft.gui.screen().children()) {
            if (child instanceof AbstractButton button) {
                seen.append(" [").append(button.getMessage().getString()).append(']');
                if (button.getMessage().getString().equalsIgnoreCase(label)) {
                    button.onPress(null);
                    Traincraft.LOGGER.info("pressed the {} button", label);
                    return true;
                }
            }
        }
        Traincraft.LOGGER.error("clickButton: no button called {}; the screen has{}", label, seen);
        return false;
    }

    /**
     * Shift-clicks the first slot holding a named item.
     *
     * <p>Named rather than numbered because a menu's slot indices are an implementation detail that
     * a player does not know and a scenario should not have to: what the player does is find the
     * bucket and click it.
     */
    public static boolean clickItem(Minecraft minecraft, String itemId, boolean quick) {
        LocalPlayer player = minecraft.player;
        if (player == null || player.containerMenu == null) {
            return false;
        }
        Item item = BuiltInRegistries.ITEM.getOptional(Identifier.parse(itemId)).orElse(null);
        AbstractContainerMenu menu = player.containerMenu;
        for (int i = 0; i < menu.slots.size(); i++) {
            if (menu.slots.get(i).getItem().getItem() == item) {
                return clickSlot(minecraft, i, quick);
            }
        }
        Traincraft.LOGGER.error(
                "clickItem: nothing holding {} in a menu of {} slots", itemId, menu.slots.size());
        return false;
    }

    public static boolean clickSlot(Minecraft minecraft, int slot, boolean quick) {
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null) {
            return false;
        }
        AbstractContainerMenu menu = player.containerMenu;
        if (menu == null) {
            Traincraft.LOGGER.error("clickSlot: no menu open");
            return false;
        }
        if (slot < 0 || slot >= menu.slots.size()) {
            Traincraft.LOGGER.error(
                    "clickSlot: slot {} is outside a menu of {}", slot, menu.slots.size());
            return false;
        }
        ItemStack before = menu.slots.get(slot).getItem().copy();
        minecraft.gameMode.handleContainerInput(
                menu.containerId,
                slot,
                0,
                quick ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP,
                player);
        Traincraft.LOGGER.info(
                "clicked slot {} ({}{}), holding {}",
                slot,
                before.isEmpty() ? "empty" : before.getItem(),
                quick ? ", shift" : "",
                menu.getCarried().isEmpty() ? "nothing" : menu.getCarried().getItem());
        return true;
    }

    public static boolean pickIntoHotbar(Minecraft minecraft, String itemId, int hotbarSlot) {
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null) {
            return false;
        }
        Item item = BuiltInRegistries.ITEM.getOptional(Identifier.parse(itemId)).orElse(null);
        if (item == null) {
            Traincraft.LOGGER.error("pickIntoHotbar: no item {}", itemId);
            return false;
        }
        ItemStack stack = new ItemStack(item);
        player.getInventory().setItem(hotbarSlot, stack);
        // The inventory menu numbers the hotbar from 36; that is what the server expects.
        minecraft.gameMode.handleCreativeModeItemAdd(stack, 36 + hotbarSlot);
        player.getInventory().setSelectedSlot(hotbarSlot);
        Traincraft.LOGGER.info("picked {} into hotbar slot {}", itemId, hotbarSlot);
        return true;
    }

    /**
     * Stands next to a block and looks at it.
     *
     * <p>A separate action from the click, and it has to be: the server only learns where the
     * player is from a movement packet, and it does not process that until its next tick. Doing
     * both in one tick leaves the server thinking the player is wherever they were, and it then
     * refuses the click as out of reach -- while the client, which predicts locally, reports
     * success and shows nothing. Stand in one step, click in the next.
     */
    public static void standBy(Minecraft minecraft, BlockPos pos, float yaw) {
        LocalPlayer player = minecraft.player;
        if (player == null) {
            return;
        }
        Vec3 centre = Vec3.atCenterOf(pos);
        player.snapTo(centre.x, pos.getY() + 1.5, centre.z + 1.5, yaw, 20.0F);
        player.connection.send(
                new ServerboundMovePlayerPacket.PosRot(
                        player.getX(), player.getY(), player.getZ(), yaw, 20.0F, true, false));
        Traincraft.LOGGER.info(
                "standing at {},{},{} facing {}", player.getX(), player.getY(), player.getZ(), yaw);
    }

    /**
     * Right-clicks a block face with whatever is in hand.
     *
     * <p>Assumes the player is already standing in reach; see {@link #standBy}.
     */
    public static boolean useOnBlock(Minecraft minecraft, BlockPos pos, Direction face, float yaw) {
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null) {
            return false;
        }
        Vec3 centre = Vec3.atCenterOf(pos);

        Vec3 hit = centre.add(face.getStepX() * 0.5, face.getStepY() * 0.5, face.getStepZ() * 0.5);
        var result =
                minecraft.gameMode.useItemOn(
                        player,
                        InteractionHand.MAIN_HAND,
                        new BlockHitResult(hit, face, pos, false));
        Traincraft.LOGGER.info(
                "right-clicked {} on {} facing {} -> {}",
                player.getMainHandItem().getItem(),
                pos.toShortString(),
                yaw,
                result);
        return result.consumesAction();
    }

    /** Breaks a block, as holding the left button to completion does. */
    public static boolean breakBlock(Minecraft minecraft, BlockPos pos) {
        if (minecraft.gameMode == null) {
            return false;
        }
        boolean broken = minecraft.gameMode.destroyBlock(pos);
        Traincraft.LOGGER.info("broke {} -> {}", pos.toShortString(), broken);
        return broken;
    }

    /** Right-clicks an entity: mounting a locomotive, or opening its menu while crouching. */
    public static boolean useOnEntity(Minecraft minecraft, Entity entity, boolean crouching) {
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null) {
            return false;
        }
        player.setShiftKeyDown(crouching);
        var result =
                minecraft.gameMode.interact(
                        player, entity, new EntityHitResult(entity), InteractionHand.MAIN_HAND);
        player.setShiftKeyDown(false);
        Traincraft.LOGGER.info(
                "right-clicked {} crouching={} -> {}",
                entity.getType().toShortString(),
                crouching,
                result);
        return result.consumesAction();
    }

    /** Hits an entity, as a left click does. */
    public static void attackEntity(Minecraft minecraft, Entity entity) {
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null) {
            return;
        }
        minecraft.gameMode.attack(player, entity);
        Traincraft.LOGGER.info("hit {}", entity.getType().toShortString());
    }

    /** Closes whatever screen is open, as escape does. */
    public static void closeScreen(Minecraft minecraft) {
        if (minecraft.player != null) {
            minecraft.player.closeContainer();
        }
        minecraft.gui.setScreen(null);
    }
}
