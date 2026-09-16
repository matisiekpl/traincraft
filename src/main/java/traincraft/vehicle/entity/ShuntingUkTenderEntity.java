package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class ShuntingUkTenderEntity extends TenderEntity {

    public ShuntingUkTenderEntity(EntityType<? extends ShuntingUkTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_SHUNTING_UK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Shunting Tender UK");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_SHUNTING_UK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
