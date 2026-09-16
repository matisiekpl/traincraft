package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class CabooseLoggingPrrWorkCartEntity extends WorkCartEntity {

    public CabooseLoggingPrrWorkCartEntity(EntityType<? extends CabooseLoggingPrrWorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_CABOOSE_LOGGING_PRR;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Logging Caboose");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_CABOOSE_LOGGING_PRR.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.2F;
    }
}
