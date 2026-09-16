package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class GtngFreightEntity extends FreightEntity {

    public GtngFreightEntity(EntityType<? extends GtngFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_GTNG;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Minecart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_GTNG.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.025F;
    }
}
