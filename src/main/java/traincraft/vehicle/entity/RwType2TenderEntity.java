package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class RwType2TenderEntity extends TenderEntity {

    public RwType2TenderEntity(EntityType<? extends RwType2TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_RW_TYPE_2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("RW Type 2 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_RW_TYPE_2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.35F;
    }
}
