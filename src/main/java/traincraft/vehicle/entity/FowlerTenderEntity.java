package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class FowlerTenderEntity extends TenderEntity {

    public FowlerTenderEntity(EntityType<? extends FowlerTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_FOWLER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Fowler 4F Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_FOWLER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.8F;
    }
}
