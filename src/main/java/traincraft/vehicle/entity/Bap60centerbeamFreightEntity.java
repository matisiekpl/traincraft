package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Bap60centerbeamFreightEntity extends FreightEntity {

    public Bap60centerbeamFreightEntity(EntityType<? extends Bap60centerbeamFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP60CENTERBEAM;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("60' Center Beam");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP60CENTERBEAM.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.3F;
    }
}
