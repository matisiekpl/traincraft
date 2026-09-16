package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Hicube60footFreightEntity extends FreightEntity {

    public Hicube60footFreightEntity(EntityType<? extends Hicube60footFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_HICUBE60FOOT;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Gunderson 60' Hi-Cube Double Door Boxcar");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_HICUBE60FOOT.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.9F;
    }
}
