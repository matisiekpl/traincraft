package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class MailWagenDbWorkCartEntity extends WorkCartEntity {

    public MailWagenDbWorkCartEntity(EntityType<? extends MailWagenDbWorkCartEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.WORK_MAIL_WAGEN_DB;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mail Wagen");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.WORK_MAIL_WAGEN_DB.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.84F;
    }
}
