package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Heavyweight2FreightEntity extends FreightEntity {

    public Heavyweight2FreightEntity(EntityType<? extends Heavyweight2FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HEAVYWEIGHT_2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Heavyweight Baggage");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HEAVYWEIGHT_2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 5.0F;
    }
}
