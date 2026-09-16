package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class RoundHopperFreightEntity extends FreightEntity {

    public RoundHopperFreightEntity(EntityType<? extends RoundHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_ROUND_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Round Covered Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_ROUND_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.1F;
    }
}
