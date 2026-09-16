package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BnsfGonFreightEntity extends FreightEntity {

    public BnsfGonFreightEntity(EntityType<? extends BnsfGonFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BNSF_GON;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BNSF Mill Gon");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BNSF_GON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.875F;
    }
}
