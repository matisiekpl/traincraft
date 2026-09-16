package traincraft.vehicle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class FreightCartYellowEntity extends FreightEntity {

    public FreightCartYellowEntity(
            EntityType<? extends FreightCartYellowEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_CART_YELLOW;
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.47F;
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_CART_YELLOW.get();
    }
}
