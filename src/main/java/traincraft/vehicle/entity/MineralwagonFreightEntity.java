package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class MineralwagonFreightEntity extends FreightEntity {

    public MineralwagonFreightEntity(EntityType<? extends MineralwagonFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_MINERALWAGON;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mineral Wagon");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_MINERALWAGON.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.5F;
    }
}
