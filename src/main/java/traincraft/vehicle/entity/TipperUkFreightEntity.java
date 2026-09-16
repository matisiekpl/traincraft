package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TipperUkFreightEntity extends FreightEntity {

    public TipperUkFreightEntity(EntityType<? extends TipperUkFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_TIPPER_UK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tipper UK");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_TIPPER_UK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
