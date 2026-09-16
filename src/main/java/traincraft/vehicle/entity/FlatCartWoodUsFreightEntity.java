package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class FlatCartWoodUsFreightEntity extends FreightEntity {

    public FlatCartWoodUsFreightEntity(EntityType<? extends FlatCartWoodUsFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_FLAT_CART_WOOD_US;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Wood transport");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_FLAT_CART_WOOD_US.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.74F;
    }

    @Override
    protected int cargoSlots() {
        return 27;
    }
}
