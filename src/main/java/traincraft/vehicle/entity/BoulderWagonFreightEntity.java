package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BoulderWagonFreightEntity extends FreightEntity {

    public BoulderWagonFreightEntity(EntityType<? extends BoulderWagonFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BOULDER_WAGON;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Boulder Wagon");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BOULDER_WAGON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.8F;
    }
}
