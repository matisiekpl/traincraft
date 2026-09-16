package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.ItemRegistry;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.definition.VehicleDefinitions;

public class BrMk2FBsoCarEntity extends PassengerEntity {

    public BrMk2FBsoCarEntity(EntityType<? extends BrMk2FBsoCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_BR_MK2_F_BSO;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("BR_MK2F_BSO");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_BR_MK2_F_BSO.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.69F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, -0.1), 0.0);
    }
}
