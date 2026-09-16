package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TankCartLavaTankEntity extends TankCartEntity {

    public TankCartLavaTankEntity(EntityType<? extends TankCartLavaTankEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TANK_TANK_CART_LAVA;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Lava Tank cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TANK_TANK_CART_LAVA.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.85F;
    }

    @Override
    protected boolean lavaOnly() {
        return true;
    }
}
