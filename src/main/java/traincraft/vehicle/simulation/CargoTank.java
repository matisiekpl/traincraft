package traincraft.vehicle.simulation;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

public final class CargoTank extends FluidStacksResourceHandler {

    private final IntSupplier capacity;
    private final Predicate<Fluid> accepts;

    public CargoTank(IntSupplier capacity) {
        this(capacity, fluid -> fluid == Fluids.WATER);
    }

    public CargoTank(IntSupplier capacity, Predicate<Fluid> accepts) {
        super(1, 0);
        this.capacity = capacity;
        this.accepts = accepts;
    }

    @Override
    public boolean isValid(int index, FluidResource resource) {
        return accepts.test(resource.getFluid());
    }

    public Fluid fluid() {
        return getResource(0).getFluid();
    }

    @Override
    protected int getCapacity(int index, FluidResource resource) {
        return capacity.getAsInt();
    }

    public int amount() {
        return getAmountAsInt(0);
    }

    public void fill(int millibuckets) {
        fill(Fluids.WATER, millibuckets);
    }

    public void fill(Fluid fluid, int millibuckets) {
        try (Transaction transaction = Transaction.openRoot()) {
            insert(0, FluidResource.of(fluid), millibuckets, transaction);
            transaction.commit();
        }
    }

    public void drain(int millibuckets) {
        try (Transaction transaction = Transaction.openRoot()) {
            extract(0, getResource(0), millibuckets, transaction);
            transaction.commit();
        }
    }

    public int moveFrom(ResourceHandler<FluidResource> source, int millibuckets) {
        return move(source, this, millibuckets);
    }

    public int moveInto(ResourceHandler<FluidResource> target, int millibuckets) {
        return move(this, target, millibuckets);
    }

    private int move(
            ResourceHandler<FluidResource> source,
            ResourceHandler<FluidResource> target,
            int millibuckets) {
        try (Transaction transaction = Transaction.openRoot()) {
            int moved =
                    ResourceHandlerUtil.move(
                            source,
                            target,
                            resource -> accepts.test(resource.getFluid()),
                            millibuckets,
                            transaction);
            transaction.commit();
            return moved;
        }
    }

    public ItemStack drainContainer(Container container, int slot) {
        return exchange(container, slot, true);
    }

    public ItemStack fillContainer(Container container, int slot) {
        return exchange(container, slot, false);
    }

    /**
     * Exchanges one container from the slot, upstream's one bucket per call. A whole stack goes
     * through NeoForge's bucket handler only when every bucket in it can be converted at once,
     * which a tank with room for fewer never allows; a single split-off copy always can.
     */
    private ItemStack exchange(Container container, int slot, boolean drain) {
        SimpleContainer single = new SimpleContainer(container.getItem(slot).copyWithCount(1));
        ItemAccess access = ItemAccess.forHandlerIndexStrict(VanillaContainerWrapper.of(single), 0);
        ResourceHandler<FluidResource> held = access.getCapability(Capabilities.Fluid.ITEM);
        if (held == null) {
            return ItemStack.EMPTY;
        }
        int moved = drain ? moveFrom(held, Integer.MAX_VALUE) : moveInto(held, Integer.MAX_VALUE);
        if (moved == 0) {
            return ItemStack.EMPTY;
        }
        container.removeItem(slot, 1);
        return single.getItem(0);
    }
}
