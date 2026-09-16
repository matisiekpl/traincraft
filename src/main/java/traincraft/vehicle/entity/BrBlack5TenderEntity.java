package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BrBlack5TenderEntity extends TenderEntity {

    public BrBlack5TenderEntity(EntityType<? extends BrBlack5TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_BR_BLACK_5;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR Black 5 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_BR_BLACK_5.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.54F;
    }
}
