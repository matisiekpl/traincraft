package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class SkeletonFreightEntity extends FreightEntity {

    public SkeletonFreightEntity(EntityType<? extends SkeletonFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_SKELETON;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("45' Skeleton Log Car");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_SKELETON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.05F;
    }
}
