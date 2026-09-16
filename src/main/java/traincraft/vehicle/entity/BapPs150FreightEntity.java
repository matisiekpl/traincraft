package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapPs150FreightEntity extends FreightEntity {

    public BapPs150FreightEntity(EntityType<? extends BapPs150FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_PS150;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Pullman Standard 50' Boxcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_PS150.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.3125F;
    }
}
