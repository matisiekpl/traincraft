package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import traincraft.vehicle.coupling.Coupling;
import traincraft.vehicle.inventory.FreightMenu;
import traincraft.vehicle.inventory.VehicleInventory;

/**
 * A cart that carries cargo.
 *
 * <p>Community Edition's {@code Freight} and the common half of {@code EntityFreightCart}: the
 * inventory is nine slots wide and as many rows as the row's cargo capacity divides into, the whole
 * lot is dropped when the cart is destroyed, and a blow that gets through is counted twice.
 */
public abstract class FreightEntity extends RollingStockEntity implements Container, MenuProvider {

    private static final EntityDataAccessor<Integer> FILLED = SynchedEntityData.defineId(FreightEntity.class, EntityDataSerializers.INT);

    private final VehicleInventory cargo;

    protected FreightEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.cargo = new VehicleInventory(cargoSlots());
    }

    protected int cargoSlots() {
        return inventoryRows() * 9;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FILLED, 0);
    }

    @Override
    public int filledSlots() {
        return entityData.get(FILLED);
    }

    private void countFilled() {
        entityData.set(FILLED, (int) cargo.contents().stream().filter(stack -> !stack.isEmpty()).count());
    }

    /** Upstream's {@code getInventoryRows}: the cargo capacity, scaled by a ninth. */
    public int inventoryRows() {
        return (int) (spec().cargoCapacity() * 0.1111111111112);
    }

    // --- Interaction ------------------------------------------------------------------------

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        if (player.getVehicle() == this) {
            return InteractionResult.PASS;
        }
        ItemStack held = player.getItemInHand(hand);
        if (yieldsToPaintbrush(player, held)) {
            return InteractionResult.PASS;
        }
        if (level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (toggleLock(player, held)) {
            return InteractionResult.SUCCESS;
        }
        if (refusesLocked(player, held)) {
            return InteractionResult.SUCCESS;
        }
        if (paint(player, held)) {
            return InteractionResult.SUCCESS;
        }
        if (held.getItem() instanceof DyeItem) {
            player.sendSystemMessage(Component.literal("No other colors available"));
        }
        if (Coupling.onClickWithStake(this, held, player, hand)) {
            return InteractionResult.SUCCESS;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this, buffer -> buffer.writeVarInt(getId()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(spec().displayName());
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FreightMenu(containerId, inventory, this);
    }

    // --- Damage -----------------------------------------------------------------------------

    /**
     * Upstream counts the same blow twice: once in the rolling stock's own handler and once here,
     * so a cart takes three swings from a bare hand where a locomotive takes five. Deliberate; it
     * is what a player sees.
     *
     * <p>The common half never removes anything: upstream removes a passenger car there and leaves
     * a freight cart to the second half, below.
     */
    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (canBeDestroyedByPlayer(source)) {
            return true;
        }
        super.hurtServer(level, source, damage);

        setHurtDir(-getHurtDir());
        setHurtTime(10);
        markHurt();
        setDamage(getDamage() + damage * 10.0F);
        if (getDamage() > 40.0F) {
            ejectPassengers();
            discard();
            announceRemoval(source);
            // Only a player leaves anything behind, and a creative one leaves nothing.
            if (source.getEntity() instanceof Player player) {
                dropCart(level, player.getAbilities().instabuild);
            }
        }
        return true;
    }

    /** Upstream puts only the cart itself behind the creative test; the cargo always drops. */
    private void dropCart(ServerLevel level, boolean creative) {
        if (!creative) {
            spawnAtLocation(level, dropStack());
        }
        for (ItemStack stack : cargo.contents()) {
            if (!stack.isEmpty()) {
                spawnAtLocation(level, stack);
            }
        }
    }

    // --- Container --------------------------------------------------------------------------

    @Override
    public int getContainerSize() {
        return cargo.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return cargo.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return cargo.getItem(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return cargo.removeItem(slot, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return cargo.removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        cargo.setItem(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public void setChanged() {
        countFilled();
    }

    @Override
    public boolean stillValid(Player player) {
        return !isRemoved() && player.distanceToSqr(this) <= 64.0;
    }

    @Override
    public void clearContent() {
        cargo.clearContent();
    }

    // --- Persistence ------------------------------------------------------------------------

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        cargo.clearContent();
        cargo.load(input);
        countFilled();
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        cargo.save(output);
    }
}
