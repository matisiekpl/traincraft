package traincraft.vehicle.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.List;

/** Vehicle inventory with an explicit persistence boundary. */
public final class VehicleInventory extends SimpleContainer {
    public VehicleInventory(int slots) {
        super(slots);
    }

    public List<ItemStack> contents() {
        return java.util.stream.IntStream.range(0, getContainerSize())
                .mapToObj(this::getItem)
                .toList();
    }

    public void load(ValueInput input) {
        NonNullList<ItemStack> items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, items);
        for (int i = 0; i < items.size(); i++) setItem(i, items.get(i));
    }

    public void save(ValueOutput output) {
        NonNullList<ItemStack> items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < items.size(); i++) items.set(i, getItem(i));
        ContainerHelper.saveAllItems(output, items);
    }
}
