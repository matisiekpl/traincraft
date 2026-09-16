package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class TracksBuilderCarEntity extends TracksBuilderEntity {

    public TracksBuilderCarEntity(EntityType<? extends TracksBuilderCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_TRACKS_BUILDER;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tracks Builder");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_TRACKS_BUILDER.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 1.0F;
    }
}
