package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class C62TenderEntity extends TenderEntity {

    public C62TenderEntity(EntityType<? extends C62TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_C62;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("C62 Class Tender [JNR]");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_C62.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.0F;
    }
}
