package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Hopper6260FreightEntity extends FreightEntity {

    public Hopper6260FreightEntity(EntityType<? extends Hopper6260FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HOPPER6260;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("6260 Cubic Foot Jumbo Hopper");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HOPPER6260.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.75F;
    }
}
