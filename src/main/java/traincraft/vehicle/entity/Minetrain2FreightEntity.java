package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class Minetrain2FreightEntity extends FreightEntity {

    public Minetrain2FreightEntity(EntityType<? extends Minetrain2FreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_MINETRAIN_2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Minecart");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_MINETRAIN_2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 0.7F;
    }
}
