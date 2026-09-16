package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class DenverRioGrange2FreightEntity extends FreightEntity {

    public DenverRioGrange2FreightEntity(EntityType<? extends DenverRioGrange2FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_DENVER_RIO_GRANGE_2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("DRG Baggage");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_DENVER_RIO_GRANGE_2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.15F;
    }
}
