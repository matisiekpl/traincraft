package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Berk1225TenderEntity extends TenderEntity {

    public Berk1225TenderEntity(EntityType<? extends Berk1225TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_BERK_1225;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Berkshire 1225 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_BERK_1225.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.875F;
    }
}
