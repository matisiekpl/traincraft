package traincraft.vehicle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class DecorativeEntity extends PassengerEntity {

    protected DecorativeEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean rideable() {
        return false;
    }
}
