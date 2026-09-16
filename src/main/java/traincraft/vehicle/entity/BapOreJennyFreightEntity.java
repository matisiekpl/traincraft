package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BapOreJennyFreightEntity extends FreightEntity {

    public BapOreJennyFreightEntity(EntityType<? extends BapOreJennyFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BAP_ORE_JENNY;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Ore Jenni lul bean sus");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BAP_ORE_JENNY.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.1F;
    }
}
