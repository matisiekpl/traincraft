package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TankTankerUkTankEntity extends TankCartEntity {

    public TankTankerUkTankEntity(EntityType<? extends TankTankerUkTankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_TANK_TANKER_UK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tanker UK");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_TANK_TANKER_UK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.55F;
    }
}
