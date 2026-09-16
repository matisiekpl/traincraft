package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapWoodchipHopperFreightEntity extends FreightEntity {

    public BapWoodchipHopperFreightEntity(EntityType<? extends BapWoodchipHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_WOODCHIP_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("55 Foot Woodchip Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_WOODCHIP_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.8F;
    }
}
