package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class FlatCartWoodLogsFreightEntity extends FreightEntity {

    public FlatCartWoodLogsFreightEntity(EntityType<? extends FlatCartWoodLogsFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_FLAT_CART_WOOD_LOGS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Wood transport");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_FLAT_CART_WOOD_LOGS.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.4F;
    }
}
