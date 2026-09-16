package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class HeavyweightFreightEntity extends FreightEntity {

    public HeavyweightFreightEntity(EntityType<? extends HeavyweightFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HEAVYWEIGHT;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Heavyweight Mailcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HEAVYWEIGHT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.2F;
    }
}
