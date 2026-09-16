package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapMilw40boxcarFreightEntity extends FreightEntity {

    public BapMilw40boxcarFreightEntity(EntityType<? extends BapMilw40boxcarFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_MILW40BOXCAR;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("MILW 40' Boxcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_MILW40BOXCAR.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
