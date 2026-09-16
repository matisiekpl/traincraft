package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class GwrBrakeVanWorkCartEntity extends WorkCartEntity {

    public GwrBrakeVanWorkCartEntity(EntityType<? extends GwrBrakeVanWorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_GWR_BRAKE_VAN;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GWR Brake Van");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_GWR_BRAKE_VAN.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 3.4F;
    }
}
