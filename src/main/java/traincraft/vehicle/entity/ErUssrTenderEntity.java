package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class ErUssrTenderEntity extends TenderEntity {

    public ErUssrTenderEntity(EntityType<? extends ErUssrTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_ER_USSR;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("USSR 0-5-0's Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_ER_USSR.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.67F;
    }
}
