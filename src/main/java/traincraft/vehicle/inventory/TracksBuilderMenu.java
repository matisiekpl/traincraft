package traincraft.vehicle.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import traincraft.bootstrap.MenuRegistry;
import traincraft.vehicle.entity.TracksBuilderEntity;

public class TracksBuilderMenu extends FreightMenu {

    public static final int BUTTON_UP = 0;
    public static final int BUTTON_DOWN = 1;
    public static final int BUTTON_START = 2;
    public static final int BUTTON_STOP = 3;
    public static final int BUTTON_REVERSE = 4;
    public static final int CELL_BUTTONS = 10;

    public static final int TEMPLATE_X = 8;
    public static final int TEMPLATE_Y = 18;
    public static final int FUEL_X = 8;
    public static final int FUEL_Y = 142;
    public static final int STORAGE_X = 160;
    public static final int MATERIALS_Y = 18;
    public static final int DUG_Y = 96;
    public static final int INVENTORY_Y = 166;

    private final TracksBuilderEntity builder;

    public TracksBuilderMenu(int containerId, Inventory playerInventory, TracksBuilderEntity builder) {
        super(MenuRegistry.TRACKS_BUILDER.get(), containerId, builder, 3);
        this.builder = builder;
        addSlot(new Slot(builder, TracksBuilderEntity.FUEL_SLOT, FUEL_X + 1, FUEL_Y + 1) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return TracksBuilderEntity.fuelWorth(playerInventory.player.level(), stack) > 0;
            }
        });
        for (int index = 0; index < TracksBuilderEntity.MATERIAL_SLOTS; index++) {
            addSlot(new Slot(builder, TracksBuilderEntity.MATERIALS_FROM + index, STORAGE_X + 1 + index % 9 * 18, MATERIALS_Y + 1 + index / 9 * 18));
        }
        for (int index = 0; index < TracksBuilderEntity.DUG_SLOTS; index++) {
            addSlot(new Slot(builder, TracksBuilderEntity.DUG_FROM + index, STORAGE_X + 1 + index % 9 * 18, DUG_Y + 1 + index / 9 * 18));
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, STORAGE_X + 1 + column * 18, INVENTORY_Y + 1 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, STORAGE_X + 1 + column * 18, INVENTORY_Y + 59));
        }
    }

    public TracksBuilderEntity builder() {
        return builder;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (!builder.mayControl(player)) {
            return false;
        }
        switch (id) {
            case BUTTON_UP -> builder.setPlannedHeight(builder.getPlannedHeight() + 1);
            case BUTTON_DOWN -> builder.setPlannedHeight(builder.getPlannedHeight() - 1);
            case BUTTON_START -> builder.setStarted(true);
            case BUTTON_STOP -> builder.setStarted(false);
            case BUTTON_REVERSE -> builder.reverse();
            default -> {
                int cell = id - CELL_BUTTONS;
                if (cell < 0 || cell >= TracksBuilderEntity.TEMPLATE_CELLS || TracksBuilderEntity.isProfileCell(cell)) {
                    return false;
                }
                ItemStack carried = getCarried();
                if (builder.templateBlock(cell) != null) {
                    builder.setTemplateBlock(cell, null);
                } else if (carried.getItem() instanceof BlockItem item && TracksBuilderEntity.canBeTunnel(carried)) {
                    builder.setTemplateBlock(cell, item.getBlock());
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        boolean moved;
        if (index < TracksBuilderEntity.SLOTS) {
            moved = moveItemStackTo(stack, TracksBuilderEntity.SLOTS, slots.size(), true);
        } else if (TracksBuilderEntity.fuelWorth(player.level(), stack) > 0) {
            moved = moveItemStackTo(stack, TracksBuilderEntity.FUEL_SLOT, TracksBuilderEntity.FUEL_SLOT + 1, false);
        } else {
            moved = moveItemStackTo(stack, TracksBuilderEntity.MATERIALS_FROM, TracksBuilderEntity.DUG_FROM, false);
        }
        if (!moved) {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return original;
    }
}
