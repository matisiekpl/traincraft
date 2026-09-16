package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Tender440TenderEntity extends TenderEntity {

    public Tender440TenderEntity(EntityType<? extends Tender440TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_4_4_0;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_4_4_0.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.15F;
    }
}
