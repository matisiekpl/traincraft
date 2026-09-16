package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapDot11129080TankEntity extends TankCartEntity {

    public BapDot11129080TankEntity(EntityType<? extends BapDot11129080TankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_BAP_DOT11129080;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("29,080 Gallon Tank car");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_BAP_DOT11129080.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.75F;
    }
}
