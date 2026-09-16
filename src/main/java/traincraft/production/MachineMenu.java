package traincraft.production;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import traincraft.bootstrap.MenuRegistry;

public final class MachineMenu extends AbstractContainerMenu {
    public final MachineBlockEntity machine;
    private final SimpleContainer preview = new SimpleContainer(1);
    private MachineRecipe selected;
    public final ContainerData data;

    public MachineMenu(int id, Inventory inventory, MachineBlockEntity machine) {
        super(MenuRegistry.MACHINE.get(), id);
        this.machine = machine;
        var kind = machine.kind;
        int[][] coordinates = kind.coordinates();
        for (int index = 0; index < kind.slots; index++) {
            int slotIndex = index;
            int x = coordinates[index][0], y = coordinates[index][1];
            if (kind.instantaneous() && index == kind.output) {
                addSlot(new Slot(preview, 0, x, y) {
                    @Override public boolean mayPlace(ItemStack stack) { return false; }
                    @Override public boolean mayPickup(Player player) {
                        if (preview.isEmpty()) return false;
                        if (player.level().isClientSide()) return true;
                        if (selected != null && selected.matches(new MachineRecipe.Inventory(kind, machine), player.level())) return true;
                        updatePreview();
                        return false;
                    }
                    @Override public void onTake(Player player, ItemStack stack) {
                        if (!player.level().isClientSide()) {
                            for (var input : selected.inputs()) machine.consume(input.slot(), input.count());
                        }
                        super.onTake(player, stack);
                        updatePreview();
                    }
                });
            } else {
                addSlot(new Slot(machine, index, x, y) {
                    @Override public boolean mayPlace(ItemStack stack) {
                        if (slotIndex == kind.output || kind == MachineKind.DISTILLERY && slotIndex == 4
                                || kind.assembly() && slotIndex >= 11 && slotIndex < 18) return false;
                        return true;
                    }
                });
            }
        }
        int playerY = kind.assembly() ? 174 : 84;
        for (int row=0; row<3; row++) for (int col=0; col<9; col++)
            addSlot(new Slot(inventory, 9+row*9+col, 8+col*18, playerY+row*18));
        for (int col=0; col<9; col++) addSlot(new Slot(inventory, col, 8+col*18, playerY+58));
        data = new ContainerData() {
            @Override public int get(int index) { return switch(index) { case 0 -> machine.progress; case 1 -> machine.burnTime; case 2 -> machine.burnDuration; default -> machine.diesel; }; }
            @Override public void set(int index, int value) { switch(index) { case 0 -> machine.progress=value; case 1 -> machine.burnTime=value; case 2 -> machine.burnDuration=value; default -> machine.diesel=value; } }
            @Override public int getCount() { return 4; }
        };
        addDataSlots(data);
        updatePreview();
    }
    private void updatePreview() {
        if (!machine.kind.instantaneous() || machine.getLevel().isClientSide()) return;
        selected = machine.recipe();
        preview.setItem(0, selected == null ? ItemStack.EMPTY : selected.assemble(new MachineRecipe.Inventory(machine.kind, machine)));
    }
    @Override public void broadcastChanges() { updatePreview(); super.broadcastChanges(); }
    @Override public boolean stillValid(Player player) { return machine.stillValid(player); }
    @Override public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size()) return ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasItem() || !slot.mayPickup(player)) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem(), copy = stack.copy();
        int machineSlots = machine.kind.slots;
        if (index < machineSlots) {
            if (!moveItemStackTo(stack, machineSlots, slots.size(), true)) return ItemStack.EMPTY;
        } else if (!moveItemStackTo(stack, 0, machineSlots, false)) return ItemStack.EMPTY;
        if (stack.getCount() == copy.getCount()) return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY); else slot.setChanged();
        slot.onTake(player, copy);
        return copy;
    }
}
