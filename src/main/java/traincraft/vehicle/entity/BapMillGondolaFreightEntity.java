package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapMillGondolaFreightEntity extends FreightEntity {

    public BapMillGondolaFreightEntity(EntityType<? extends BapMillGondolaFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_MILL_GONDOLA;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("52' Mill Gondola");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_MILL_GONDOLA.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.4F;
    }
}
