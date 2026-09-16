package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Ps73BaggageFreightEntity extends FreightEntity {

    public Ps73BaggageFreightEntity(EntityType<? extends Ps73BaggageFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_PS73_BAGGAGE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Pullman Standard 73' Baggage");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_PS73_BAGGAGE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.25F;
    }
}
