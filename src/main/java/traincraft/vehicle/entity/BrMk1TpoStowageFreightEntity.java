package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BrMk1TpoStowageFreightEntity extends FreightEntity {

    public BrMk1TpoStowageFreightEntity(EntityType<? extends BrMk1TpoStowageFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_BR_MK1_TPO_STOWAGE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR Mk1 TPO Stowage");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_BR_MK1_TPO_STOWAGE.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.7F;
    }
}
