package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapPs160FreightEntity extends FreightEntity {

    public BapPs160FreightEntity(EntityType<? extends BapPs160FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_PS160;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Pullman Standard 60' Boxcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_PS160.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.8125F;
    }
}
