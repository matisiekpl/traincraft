package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BUnitDd35TankEntity extends TankCartEntity {

    public BUnitDd35TankEntity(EntityType<? extends BUnitDd35TankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_B_UNIT_DD35;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("DD35 B-Unit");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_B_UNIT_DD35.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.1F;
    }

    @Override
    protected boolean fuelOnly() {
        return true;
    }
}
