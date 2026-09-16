package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class FlatCarRailsDbFreightEntity extends FreightEntity {

    public FlatCarRailsDbFreightEntity(EntityType<? extends FlatCarRailsDbFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_FLAT_CAR_RAILS_DB;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight Flat Cart Rails DB");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_FLAT_CAR_RAILS_DB.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.84F;
    }

    @Override
    protected int cargoSlots() {
        return 36;
    }
}
