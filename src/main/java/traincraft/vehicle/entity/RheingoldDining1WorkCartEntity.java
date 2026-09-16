package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class RheingoldDining1WorkCartEntity extends WorkCartEntity {

    public RheingoldDining1WorkCartEntity(EntityType<? extends RheingoldDining1WorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_RHEINGOLD_DINING1;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Rheingold Dining");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_RHEINGOLD_DINING1.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.9F;
    }
}
