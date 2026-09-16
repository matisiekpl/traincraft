package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Car100TonHopperFreightEntity extends FreightEntity {

    public Car100TonHopperFreightEntity(EntityType<? extends Car100TonHopperFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_100_TON_HOPPER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_100_TON_HOPPER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.9F;
    }
}
