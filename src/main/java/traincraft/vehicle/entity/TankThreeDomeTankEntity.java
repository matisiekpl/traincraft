package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TankThreeDomeTankEntity extends TankCartEntity {

    public TankThreeDomeTankEntity(EntityType<? extends TankThreeDomeTankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_TANK_THREE_DOME;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tank cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_TANK_THREE_DOME.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.7F;
    }
}
