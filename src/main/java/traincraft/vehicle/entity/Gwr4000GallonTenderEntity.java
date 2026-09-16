package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Gwr4000GallonTenderEntity extends TenderEntity {

    public Gwr4000GallonTenderEntity(EntityType<? extends Gwr4000GallonTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_GWR_4000_GALLON;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_GWR_4000_GALLON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
