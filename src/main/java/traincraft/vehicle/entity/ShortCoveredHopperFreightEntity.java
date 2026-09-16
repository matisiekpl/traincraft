package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class ShortCoveredHopperFreightEntity extends FreightEntity {

    public ShortCoveredHopperFreightEntity(EntityType<? extends ShortCoveredHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_SHORT_COVERED_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Short Covered Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_SHORT_COVERED_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
