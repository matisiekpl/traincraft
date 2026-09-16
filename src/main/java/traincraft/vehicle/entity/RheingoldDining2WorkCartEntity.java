package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class RheingoldDining2WorkCartEntity extends WorkCartEntity {

    public RheingoldDining2WorkCartEntity(EntityType<? extends RheingoldDining2WorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_RHEINGOLD_DINING2;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Rheingold Dining Pantograph");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_RHEINGOLD_DINING2.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.87F;
    }
}
