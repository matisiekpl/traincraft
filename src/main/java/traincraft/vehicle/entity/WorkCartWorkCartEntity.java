package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class WorkCartWorkCartEntity extends WorkCartEntity {

    public WorkCartWorkCartEntity(EntityType<? extends WorkCartWorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_WORK_CART;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Work cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_WORK_CART.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.8F;
    }
}
