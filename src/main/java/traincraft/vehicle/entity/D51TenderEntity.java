package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class D51TenderEntity extends TenderEntity {

    public D51TenderEntity(EntityType<? extends D51TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_D51;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("D51 Tender [JNR]");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_D51.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.9F;
    }
}
