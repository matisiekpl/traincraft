package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class MrCompoundTenderEntity extends TenderEntity {

    public MrCompoundTenderEntity(EntityType<? extends MrCompoundTenderEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.TENDER_MR_COMPOUND;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("MR Compound Tender");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.TENDER_MR_COMPOUND.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.9F;
    }
}
