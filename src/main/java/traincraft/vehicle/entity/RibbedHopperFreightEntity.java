package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class RibbedHopperFreightEntity extends FreightEntity {

    public RibbedHopperFreightEntity(EntityType<? extends RibbedHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_RIBBED_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Ribbed Covered Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_RIBBED_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.6F;
    }
}
