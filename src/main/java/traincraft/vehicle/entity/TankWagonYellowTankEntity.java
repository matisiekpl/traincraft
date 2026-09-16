package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TankWagonYellowTankEntity extends TankCartEntity {

    public TankWagonYellowTankEntity(EntityType<? extends TankWagonYellowTankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_TANK_WAGON_YELLOW;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tank cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_TANK_WAGON_YELLOW.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.7F;
    }
}
