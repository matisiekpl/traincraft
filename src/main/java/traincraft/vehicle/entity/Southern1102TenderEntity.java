package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Southern1102TenderEntity extends TenderEntity {

    public Southern1102TenderEntity(EntityType<? extends Southern1102TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_SOUTHERN_1102;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Baldwin 4-6-0 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_SOUTHERN_1102.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
