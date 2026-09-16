package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class C41TenderEntity extends TenderEntity {

    public C41TenderEntity(EntityType<? extends C41TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_C41;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("C41 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_C41.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.75F;
    }
}
