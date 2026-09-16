package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class DepressedFlatbedFreightEntity extends FreightEntity {

    public DepressedFlatbedFreightEntity(EntityType<? extends DepressedFlatbedFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_DEPRESSED_FLATBED;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_DEPRESSED_FLATBED.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.8F;
    }
}
