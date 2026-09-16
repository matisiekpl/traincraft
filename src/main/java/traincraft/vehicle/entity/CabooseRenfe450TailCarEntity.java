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

public class CabooseRenfe450TailCarEntity extends PassengerEntity {

    public CabooseRenfe450TailCarEntity(EntityType<? extends CabooseRenfe450TailCarEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public VehicleDefinition spec() {
        return VehicleDefinitions.PASSENGER_CABOOSE_RENFE450_TAIL;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Passenger Renfe 450 Tail");
    }

    @Override
    protected Item getDropItem() {
        return ItemRegistry.PASSENGER_CABOOSE_RENFE450_TAIL.get();
    }

    @Override
    public float optimalDistance(RollingStockEntity other) {
        return 2.6F;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity passenger, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, driverFeetOffset(passenger, 0.3), 0.0);
    }
}
