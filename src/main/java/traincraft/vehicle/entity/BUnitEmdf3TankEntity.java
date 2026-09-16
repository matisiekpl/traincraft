package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BUnitEmdf3TankEntity extends TankCartEntity {

    public BUnitEmdf3TankEntity(EntityType<? extends BUnitEmdf3TankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_B_UNIT_EMDF3;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("EMD F3 B-Unit");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_B_UNIT_EMDF3.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.2F;
    }

    @Override
    protected boolean fuelOnly() {
        return true;
    }
}
