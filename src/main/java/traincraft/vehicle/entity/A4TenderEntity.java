package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class A4TenderEntity extends TenderEntity {

    public A4TenderEntity(EntityType<? extends A4TenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_A4;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("A4 Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_A4.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.1F;
    }
}
