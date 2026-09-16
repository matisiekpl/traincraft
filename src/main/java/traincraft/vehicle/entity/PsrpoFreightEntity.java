package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class PsrpoFreightEntity extends FreightEntity {

    public PsrpoFreightEntity(EntityType<? extends PsrpoFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_PSRPO;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Pullman Standard RPO");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_PSRPO.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.25F;
    }
}
