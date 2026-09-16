package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Hopper5201FreightEntity extends FreightEntity {

    public Hopper5201FreightEntity(EntityType<? extends Hopper5201FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HOPPER5201;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("5201 Cubic Foot Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HOPPER5201.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.4F;
    }
}
