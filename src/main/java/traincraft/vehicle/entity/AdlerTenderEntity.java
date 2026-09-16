package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class AdlerTenderEntity extends TenderEntity {

    public AdlerTenderEntity(EntityType<? extends AdlerTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_ADLER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Adler's Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_ADLER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.55F;
    }
}
