package traincraft.vehicle.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import traincraft.vehicle.inventory.WorkCartMenu;

public abstract class WorkCartEntity extends FreightEntity {

    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public static final int COOK_TIME = 200;

    private static final EntityDataAccessor<Integer> COOK =
            SynchedEntityData.defineId(WorkCartEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BURN =
            SynchedEntityData.defineId(WorkCartEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BURN_TOTAL =
            SynchedEntityData.defineId(WorkCartEntity.class, EntityDataSerializers.INT);

    protected WorkCartEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected int cargoSlots() {
        return 3;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COOK, 0);
        builder.define(BURN, 0);
        builder.define(BURN_TOTAL, 0);
    }

    public int cookProgress(int pixels) {
        return entityData.get(COOK) * pixels / COOK_TIME;
    }

    public int burnRemaining(int pixels) {
        int total = entityData.get(BURN_TOTAL);
        return total == 0 ? 0 : entityData.get(BURN) * pixels / total;
    }

    public boolean isBurning() {
        return entityData.get(BURN) > 0;
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new WorkCartMenu(containerId, inventory, this);
    }

    @Override
    public void tick() {
        super.tick();
        if (!(level() instanceof ServerLevel server)) {
            return;
        }
        int burn = entityData.get(BURN);
        if (burn > 0) {
            burn--;
        }
        ItemStack result = smeltingResult(server);
        if (burn == 0 && !result.isEmpty() && fits(result)) {
            ItemStack fuel = getItem(FUEL_SLOT);
            int ticks = fuel.getBurnTime(null, server.fuelValues());
            if (ticks > 0) {
                burn = ticks;
                entityData.set(BURN_TOTAL, ticks);
                var remainder = fuel.getItem().getCraftingRemainder();
                fuel.shrink(1);
                if (fuel.isEmpty() && remainder != null) {
                    setItem(FUEL_SLOT, remainder.create());
                }
            }
        }
        int cook = entityData.get(COOK);
        if (burn > 0 && !result.isEmpty() && fits(result)) {
            cook++;
            if (cook >= COOK_TIME) {
                cook = 0;
                ItemStack output = getItem(OUTPUT_SLOT);
                if (output.isEmpty()) {
                    setItem(OUTPUT_SLOT, result.copy());
                } else {
                    output.grow(result.getCount());
                }
                getItem(INPUT_SLOT).shrink(1);
                setChanged();
            }
        } else {
            cook = 0;
        }
        entityData.set(BURN, burn);
        entityData.set(COOK, cook);
    }

    private ItemStack smeltingResult(ServerLevel server) {
        ItemStack input = getItem(INPUT_SLOT);
        if (input.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return server.recipeAccess()
                .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), server)
                .map(holder -> holder.value().assemble(new SingleRecipeInput(input)))
                .orElse(ItemStack.EMPTY);
    }

    private boolean fits(ItemStack result) {
        ItemStack output = getItem(OUTPUT_SLOT);
        return output.isEmpty()
                || ItemStack.isSameItemSameComponents(output, result)
                        && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(BURN, input.getIntOr("furnaceBurnTime", 0));
        entityData.set(BURN_TOTAL, input.getIntOr("currentItemBurnTime", 0));
        entityData.set(COOK, input.getIntOr("furnaceCookTime", 0));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("furnaceBurnTime", entityData.get(BURN));
        output.putInt("currentItemBurnTime", entityData.get(BURN_TOTAL));
        output.putInt("furnaceCookTime", entityData.get(COOK));
    }
}
