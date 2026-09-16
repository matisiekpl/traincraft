package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class ExpressFreightVanFreightEntity extends FreightEntity {

    public ExpressFreightVanFreightEntity(EntityType<? extends ExpressFreightVanFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_EXPRESS_FREIGHT_VAN;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Express Freight Van");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_EXPRESS_FREIGHT_VAN.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.9F;
    }
}
