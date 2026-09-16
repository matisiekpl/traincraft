package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Car5PlankFreightEntity extends FreightEntity {

    public Car5PlankFreightEntity(EntityType<? extends Car5PlankFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_5_PLANK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("5 Plank");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_5_PLANK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
