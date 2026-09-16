package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class CartGs4FreightEntity extends FreightEntity {

    public CartGs4FreightEntity(EntityType<? extends CartGs4FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_CART_GS4;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GS4 Baggage cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_CART_GS4.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.1F;
    }
}
