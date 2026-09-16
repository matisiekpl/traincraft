package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class CenterBeamWood2FreightEntity extends FreightEntity {

    public CenterBeamWood2FreightEntity(EntityType<? extends CenterBeamWood2FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_CENTER_BEAM_WOOD2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Freight cart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_CENTER_BEAM_WOOD2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.6F;
    }
}
