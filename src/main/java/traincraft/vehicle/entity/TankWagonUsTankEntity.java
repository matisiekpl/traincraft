package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TankWagonUsTankEntity extends TankCartEntity {

    public TankWagonUsTankEntity(EntityType<? extends TankWagonUsTankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_TANK_WAGON_US;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tank cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_TANK_WAGON_US.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.6F;
    }
}
