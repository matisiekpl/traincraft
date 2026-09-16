package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Acfgnrpo30FreightEntity extends FreightEntity {

    public Acfgnrpo30FreightEntity(EntityType<? extends Acfgnrpo30FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_ACFGNRPO_30;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("American Car & Foundry GN RPO (30' mail section)");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_ACFGNRPO_30.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.0F;
    }
}
