package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class CoranationClassTenderEntity extends TenderEntity {

    public CoranationClassTenderEntity(EntityType<? extends CoranationClassTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_CORANATION_CLASS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Coronation Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_CORANATION_CLASS.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
