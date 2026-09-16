package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Gsi60FootBulkheadFreightEntity extends FreightEntity {

    public Gsi60FootBulkheadFreightEntity(EntityType<? extends Gsi60FootBulkheadFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_GSI60_FOOT_BULKHEAD;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GSI 60' Bulkhead Flatcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_GSI60_FOOT_BULKHEAD.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.9F;
    }
}
