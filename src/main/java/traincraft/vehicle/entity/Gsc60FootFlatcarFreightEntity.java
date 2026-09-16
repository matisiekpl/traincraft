package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Gsc60FootFlatcarFreightEntity extends FreightEntity {

    public Gsc60FootFlatcarFreightEntity(EntityType<? extends Gsc60FootFlatcarFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_GSC60_FOOT_FLATCAR;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GSI 60' Flatcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_GSC60_FOOT_FLATCAR.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.9F;
    }
}
