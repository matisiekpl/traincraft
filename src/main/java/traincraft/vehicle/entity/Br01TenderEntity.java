package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Br01TenderEntity extends TenderEntity {

    public Br01TenderEntity(EntityType<? extends Br01TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_BR01;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR01's Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_BR01.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
