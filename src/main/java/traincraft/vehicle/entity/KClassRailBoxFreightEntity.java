package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class KClassRailBoxFreightEntity extends FreightEntity {

    public KClassRailBoxFreightEntity(EntityType<? extends KClassRailBoxFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_K_CLASS_RAIL_BOX;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("K Class Rail Box");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_K_CLASS_RAIL_BOX.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.725F;
    }
}
