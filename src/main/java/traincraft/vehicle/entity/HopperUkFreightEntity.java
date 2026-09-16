package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class HopperUkFreightEntity extends FreightEntity {

    public HopperUkFreightEntity(EntityType<? extends HopperUkFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HOPPER_UK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Hopper UK");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HOPPER_UK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.9F;
    }
}
