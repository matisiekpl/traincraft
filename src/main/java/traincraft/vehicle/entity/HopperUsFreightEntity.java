package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class HopperUsFreightEntity extends FreightEntity {

    public HopperUsFreightEntity(EntityType<? extends HopperUsFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HOPPER_US;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HOPPER_US.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.8F;
    }
}
