package traincraft.vehicle.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.item.TraincraftItems;
import traincraft.vehicle.inventory.VehicleInventory;
import traincraft.vehicle.inventory.ZeppelinMenu;

public class ZeppelinEntity extends VehicleEntity implements Container, MenuProvider, KeyControlled {

    public static final int SLOTS = 10;
    public static final int FUEL_SLOT = 0;
    public static final int MAX_FUEL = 1000;
    public static final int KEY_FORWARD = LocomotiveEntity.KEY_FORWARD;
    public static final int KEY_ASCEND = 3;
    public static final int KEY_DESCEND = 4;
    public static final int KEY_IDLE = 5;
    public static final int KEY_MENU = 6;
    public static final int KEY_BOMB = 7;
    private static final int BOMB_COOLDOWN = 100;
    private static final double THRUST = 0.07 * 0.05;
    private static final double SPEED_LIMIT = 0.3;

    private static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(ZeppelinEntity.class, EntityDataSerializers.INT);

    private final VehicleInventory cargo = new VehicleInventory(SLOTS);
    private final boolean twoBalloons;
    private final VehiclePart[] balloon;
    private final Vec3[] balloonOffsets;
    private boolean forward;
    private boolean altitude;
    private boolean idle;
    private int bombTimer;
    private final InterpolationHandler interpolation = new InterpolationHandler(this, 3);

    public ZeppelinEntity(EntityType<? extends ZeppelinEntity> type, Level level, boolean twoBalloons) {
        super(type, level);
        this.twoBalloons = twoBalloons;
        List<Vec3> offsets = new ArrayList<>();
        if (twoBalloons) {
            for (int i = 0; i < 4; i++) {
                offsets.add(new Vec3(0.9 + (i - 1.5) * 2.2, 2.62, -2.85));
                offsets.add(new Vec3(0.9 + (i - 1.5) * 2.2, 2.62, 2.85));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                offsets.add(new Vec3(0.35 + (i - 2) * 2.64, 1.24, 0.0));
            }
        }
        balloonOffsets = offsets.toArray(Vec3[]::new);
        balloon = new VehiclePart[balloonOffsets.length];
        for (int i = 0; i < balloon.length; i++) {
            balloon[i] = new VehiclePart(this, 0.0, twoBalloons ? 2.5F : 3.0F, twoBalloons ? 2.0F : 3.0F, true);
        }
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public VehiclePart[] getParts() {
        return balloon;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        for (int i = 0; i < balloon.length; i++) {
            balloon[i].setId(packet.getId() + i + 1);
        }
    }

    public boolean twoBalloons() {
        return twoBalloons;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
    }

    public int getFuel() {
        return entityData.get(FUEL);
    }

    /** CE's {@code pressKey}: climb and sink are latched modes, idle holds height, until another key changes it. */
    @Override
    public void setKeyHeld(int key, boolean held) {
        if (key == KEY_FORWARD) {
            forward = held;
            return;
        }
        if (!held) {
            return;
        }
        switch (key) {
            case KEY_ASCEND -> {
                if (getFuel() > 0 && getY() < level().getMaxY()) {
                    altitude = true;
                    idle = false;
                }
            }
            case KEY_DESCEND -> {
                altitude = false;
                idle = false;
            }
            case KEY_IDLE -> {
                altitude = false;
                idle = true;
            }
            case KEY_MENU -> {
                if (getFirstPassenger() instanceof ServerPlayer server) {
                    server.openMenu(this, buffer -> buffer.writeVarInt(getId()));
                }
            }
            case KEY_BOMB -> dropBomb();
            default -> {}
        }
    }

    private void dropBomb() {
        if (bombTimer > 0 || !(getFirstPassenger() instanceof LivingEntity rider)) {
            return;
        }
        for (int slot = 0; slot < SLOTS; slot++) {
            ItemStack stack = getItem(slot);
            if (!stack.is(Items.TNT)) {
                continue;
            }
            PrimedTnt tnt = new PrimedTnt(level(), getX(), getY() - 1.0, getZ(), rider);
            level().addFreshEntity(tnt);
            level().playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            bombTimer = BOMB_COOLDOWN;
            stack.shrink(1);
            return;
        }
    }

    public boolean climbing() {
        return altitude;
    }

    public boolean idling() {
        return idle;
    }

    @Override
    public boolean isPickable() {
        return !isRemoved();
    }

    @Override
    public boolean canBeCollidedWith(Entity other) {
        return !isRemoved();
    }

    @Override
    public boolean mayControl(Player player) {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        if (getFirstPassenger() != null && getFirstPassenger() != player) {
            return InteractionResult.PASS;
        }
        if (!level().isClientSide()) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return getPassengers().isEmpty();
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        double yaw = Math.toRadians(getYRot());
        return new Vec3(Math.cos(yaw) * 0.6, twoBalloons ? 0.4 : 0.04, Math.sin(yaw) * 0.6);
    }

    @Override
    public InterpolationHandler getInterpolation() {
        return interpolation;
    }

    private void alignBalloon() {
        double heading = Math.toRadians(getYRot());
        for (int i = 0; i < balloon.length; i++) {
            Vec3 offset = balloonOffsets[i];
            balloon[i].setPos(
                    getX() + Math.cos(heading) * offset.x - Math.sin(heading) * offset.z,
                    getY() + offset.y,
                    getZ() + Math.sin(heading) * offset.x + Math.cos(heading) * offset.z);
        }
    }

    /** What keeps hull or balloon from going here: a block, an entity, or the space to keep clear; null when nothing does. */
    public @Nullable String obstruction(AABB clearance) {
        alignBalloon();
        List<AABB> boxes = new ArrayList<>();
        boxes.add(getBoundingBox());
        for (VehiclePart part : balloon) {
            boxes.add(part.getBoundingBox());
        }
        for (AABB box : boxes) {
            for (BlockPos pos : BlockPos.betweenClosed(BlockPos.containing(box.minX, box.minY, box.minZ), BlockPos.containing(box.maxX, box.maxY, box.maxZ))) {
                BlockState state = level().getBlockState(pos);
                if (!state.getCollisionShape(level(), pos).isEmpty()) {
                    return state.getBlock().getName().getString() + " at " + pos.toShortString();
                }
            }
            List<Entity> entities = level().getEntities(this, box);
            if (!entities.isEmpty()) {
                return entities.getFirst().getName().getString() + " at " + entities.getFirst().blockPosition().toShortString();
            }
            if (box.intersects(clearance)) {
                return "the player";
            }
        }
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        interpolation.interpolate();
        resetFallDistance();
        alignBalloon();
        if (level().isClientSide()) {
            if (getFuel() > 0 && random.nextBoolean()) {
                level().addParticle(ParticleTypes.LARGE_SMOKE, getX() + random.nextFloat() * 2 - 1, getY() - 0.125, getZ() + random.nextFloat() * 2 - 1, 0.0, 0.0, 0.0);
            }
            return;
        }
        if (bombTimer > 0) {
            bombTimer--;
        }
        if (getHurtTime() > 0) {
            setHurtTime(getHurtTime() - 1);
        }
        if (getDamage() > 0.0F) {
            setDamage(getDamage() - 1.0F);
        }
        burnFuel();
        Vec3 motion = getDeltaMovement();
        double x = motion.x;
        double z = motion.z;
        double speed = motion.horizontalDistance();
        if (forward && getFirstPassenger() instanceof LivingEntity rider) {
            x += -Math.sin(Math.toRadians(rider.getYRot())) * THRUST;
            z += Math.cos(Math.toRadians(rider.getYRot())) * THRUST;
        }
        if (random.nextInt(4) == 0 && getFuel() > 0) {
            entityData.set(FUEL, getFuel() - 1);
        }
        double y = altitude && getY() < level().getMaxY() ? 0.051 : idle ? 0.0 : -0.021;
        x = Math.max(-SPEED_LIMIT, Math.min(SPEED_LIMIT, x));
        z = Math.max(-SPEED_LIMIT, Math.min(SPEED_LIMIT, z));
        if (onGround()) {
            x *= 0.5;
            y *= 0.5;
            z *= 0.5;
        }
        move(MoverType.SELF, new Vec3(x, y, z));
        if (!horizontalCollision && speed < 0.25) {
            x *= 0.99;
            y *= 0.95;
            z *= 0.99;
        }
        setDeltaMovement(x, y, z);
        for (ZeppelinEntity other : level().getEntitiesOfClass(ZeppelinEntity.class, getBoundingBox().inflate(0.2, 0.0, 0.2), other -> other != this)) {
            pushApart(other);
        }
        Vec3 travelled = position().subtract(xOld, yOld, zOld);
        if (travelled.horizontalDistanceSqr() > 0.001) {
            float target = (float) Math.toDegrees(Math.atan2(-travelled.z, -travelled.x));
            float delta = Math.max(-40.0F, Math.min(40.0F, net.minecraft.util.Mth.wrapDegrees(target - getYRot())));
            setYRot(getYRot() + delta);
        }
    }

    /** CE's {@code applyEntityCollision}: an inverse-distance shove that only zeppelins give each other. */
    private void pushApart(ZeppelinEntity other) {
        double dx = other.getX() - getX();
        double dz = other.getZ() - getZ();
        double distance = Math.max(Math.abs(dx), Math.abs(dz));
        if (distance < 0.01) {
            dx = 0.01;
            distance = 0.01;
        }
        distance = Math.sqrt(distance);
        dx /= distance;
        dz /= distance;
        double strength = Math.min(1.0, 1.0 / distance) * 0.05;
        dx *= strength;
        dz *= strength;
        push(-dx, 0.0, -dz);
        other.push(dx, 0.0, dz);
    }

    private void burnFuel() {
        ItemStack fuel = getItem(FUEL_SLOT);
        if (fuel.isEmpty()) {
            return;
        }
        int burn = fuel.getBurnTime(null, level().fuelValues());
        if (burn > 0 && burn * 0.05F + getFuel() < MAX_FUEL) {
            entityData.set(FUEL, getFuel() + burn);
            fuel.shrink(1);
        }
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (isInvulnerableToBase(source)) {
            return false;
        }
        setHurtDir(-getHurtDir());
        setHurtTime(10);
        markHurt();
        setDamage(getDamage() + damage * 10.0F);
        if (getDamage() > 40.0F) {
            ejectPassengers();
            discard();
            if (!(source.getEntity() instanceof Player player && player.getAbilities().instabuild)) {
                spawnAtLocation(level, new ItemStack(TraincraftItems.item(twoBalloons ? "airship" : "zeppelin")), 0.0F);
                net.minecraft.world.Containers.dropContents(level, blockPosition(), cargo);
            }
        }
        return true;
    }

    @Override
    protected Item getDropItem() {
        return TraincraftItems.item(twoBalloons ? "airship" : "zeppelin");
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(twoBalloons ? "Airship" : "Zeppelin");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ZeppelinMenu(containerId, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return cargo.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return cargo.getItem(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return cargo.removeItem(slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return cargo.removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        cargo.setItem(slot, stack);
    }

    @Override
    public void setChanged() {
        cargo.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return !isRemoved() && player.distanceToSqr(this) <= 64.0;
    }

    @Override
    public void clearContent() {
        cargo.clearContent();
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        cargo.load(input);
        entityData.set(FUEL, input.getIntOr("fuel", 0));
        altitude = input.getBooleanOr("altitude", false);
        idle = input.getBooleanOr("idle", false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        cargo.save(output);
        output.putInt("fuel", getFuel());
        output.putBoolean("altitude", altitude);
        output.putBoolean("idle", idle);
    }
}
