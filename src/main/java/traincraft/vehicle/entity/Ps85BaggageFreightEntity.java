package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Ps85BaggageFreightEntity extends FreightEntity {

    public Ps85BaggageFreightEntity(EntityType<? extends Ps85BaggageFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_PS85_BAGGAGE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Pullman Standard 85' Baggage");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_PS85_BAGGAGE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.0F;
    }
}
