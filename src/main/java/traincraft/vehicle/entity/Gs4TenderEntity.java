package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Gs4TenderEntity extends TenderEntity {

    public Gs4TenderEntity(EntityType<? extends Gs4TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_GS4;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GS4 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_GS4.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.1F;
    }
}
