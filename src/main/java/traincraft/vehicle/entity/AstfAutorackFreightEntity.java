package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class AstfAutorackFreightEntity extends FreightEntity {

    public AstfAutorackFreightEntity(EntityType<? extends AstfAutorackFreightEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.FREIGHT_ASTF_AUTORACK;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ASTF ft-41 Auto Rack");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.FREIGHT_ASTF_AUTORACK.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 4.35F;
    }
}
