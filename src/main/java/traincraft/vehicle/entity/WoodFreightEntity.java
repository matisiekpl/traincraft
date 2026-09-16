package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class WoodFreightEntity extends FreightEntity {

    public WoodFreightEntity(EntityType<? extends WoodFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_WOOD;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Wood transport");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_WOOD.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.85F;
    }
}
