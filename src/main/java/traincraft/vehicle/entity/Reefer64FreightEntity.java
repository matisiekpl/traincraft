package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Reefer64FreightEntity extends FreightEntity {

    public Reefer64FreightEntity(EntityType<? extends Reefer64FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_REEFER64;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Trinity 64' Mechanical Reefer");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_REEFER64.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.5F;
    }
}
