package traincraft.vehicle.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import net.neoforged.neoforge.entity.PartEntity;

import org.jspecify.annotations.Nullable;

/** One clickable segment of a vehicle; every interaction lands on the parent. A solid part also blocks movement. */
public class VehiclePart extends PartEntity<Entity> {

    private final double offset;
    private final EntityDimensions size;
    private final boolean solid;

    public VehiclePart(Entity parent, double offset, float width, float height, boolean solid) {
        super(parent);
        this.offset = offset;
        this.size = EntityDimensions.scalable(width, height);
        this.solid = solid;
        refreshDimensions();
    }

    public double offset() {
        return offset;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {}

    @Override
    protected void readAdditionalSaveData(ValueInput input) {}

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {}

    @Override
    public boolean isPickable() {
        return getParent().isPickable();
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        return getParent().getPickResult();
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        return getParent().interact(player, hand, location.add(position()).subtract(getParent().position()));
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        return getParent().hurtServer(level, source, damage);
    }

    @Override
    public boolean hurtClient(DamageSource source) {
        return getParent().hurtClient(source);
    }

    @Override
    public boolean canBeCollidedWith(Entity other) {
        return solid && other != getParent() && !getParent().hasPassenger(other) && getParent().canBeCollidedWith(other);
    }

    @Override
    public boolean is(Entity other) {
        return this == other || getParent() == other;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
