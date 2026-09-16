package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BulkheadFlatCartWoodFreightEntity extends FreightEntity {

    public BulkheadFlatCartWoodFreightEntity(EntityType<? extends BulkheadFlatCartWoodFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BULKHEAD_FLAT_CART_WOOD;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Bulkhead Flat Cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BULKHEAD_FLAT_CART_WOOD.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.3F;
    }
}
