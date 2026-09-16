package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class LongCoveredHopperFreightEntity extends FreightEntity {

    public LongCoveredHopperFreightEntity(EntityType<? extends LongCoveredHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_LONG_COVERED_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Long Covered Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_LONG_COVERED_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.05F;
    }
}
