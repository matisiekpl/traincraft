package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Bap40highcubeFreightEntity extends FreightEntity {

    public Bap40highcubeFreightEntity(EntityType<? extends Bap40highcubeFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP40HIGHCUBE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("40 foot Highcube Boxcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP40HIGHCUBE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
