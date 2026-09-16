package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BoxCartUsFreightEntity extends FreightEntity {

    public BoxCartUsFreightEntity(EntityType<? extends BoxCartUsFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BOX_CART_US;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight Box Cart US");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BOX_CART_US.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.65F;
    }

    @Override
    protected int cargoSlots() {
        return 45;
    }
}
