package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class OnionTenderEntity extends TenderEntity {

    public OnionTenderEntity(EntityType<? extends OnionTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_ONION;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Onion's Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_ONION.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.0F;
    }
}
