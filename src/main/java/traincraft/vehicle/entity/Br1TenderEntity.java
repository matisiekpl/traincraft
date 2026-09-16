package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Br1TenderEntity extends TenderEntity {

    public Br1TenderEntity(EntityType<? extends Br1TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_BR1;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR1 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_BR1.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.58F;
    }
}
