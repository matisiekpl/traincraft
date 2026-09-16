package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class StarClassTenderEntity extends TenderEntity {

    public StarClassTenderEntity(EntityType<? extends StarClassTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_STAR_CLASS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Star Class Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_STAR_CLASS.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.9F;
    }
}
