package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapDot11111000TankEntity extends TankCartEntity {

    public BapDot11111000TankEntity(EntityType<? extends BapDot11111000TankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_BAP_DOT11111000;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("11,000 Gallon Tank car");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_BAP_DOT11111000.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.9375F;
    }
}
