package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Shay3truckTenderEntity extends TenderEntity {

    public Shay3truckTenderEntity(EntityType<? extends Shay3truckTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_SHAY_3TRUCK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Class 3-PC-13 3-Truck Shay Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_SHAY_3TRUCK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.7F;
    }
}
