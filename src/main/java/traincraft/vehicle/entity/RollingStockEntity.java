package traincraft.vehicle.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import org.jspecify.annotations.Nullable;

import traincraft.adminbook.StockLog;
import traincraft.TraincraftConfig;
import traincraft.item.AdminBookItem;
import traincraft.item.TraincraftItems;
import traincraft.item.WrenchItem;
import traincraft.track.TrackCategory;
import traincraft.track.TrackMovement;
import traincraft.track.TrackType;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlock;
import traincraft.track.block.TrackOccupancyBlockEntity;
import traincraft.vehicle.animation.WheelAnimation;
import traincraft.vehicle.control.StockAccess;
import traincraft.vehicle.coupling.Consist;
import traincraft.vehicle.coupling.LinkHandler;
import traincraft.vehicle.coupling.StockCollision;
import traincraft.vehicle.definition.VehicleBounds;
import traincraft.vehicle.definition.VehicleDefinition;
import traincraft.vehicle.simulation.BogieState;
import traincraft.vehicle.simulation.SpeedHandler;
import traincraft.vehicle.simulation.VehicleOrientation;

import java.util.ArrayList;
import java.util.List;

public abstract class RollingStockEntity extends VehicleEntity implements TrackMovement.Body {

    public static final double Y_OFFSET = 0.65;

    public static final float WIDTH = 0.98F;
    public static final float HEIGHT = 1.98F;

    /** What Traincraft's own track reports as its ceiling, before the speed handler reads it. */
    private static final double TC_RAIL_MAX_SPEED = 3.0;

    /** Forge's default for a vanilla rail. */
    private static final double VANILLA_RAIL_MAX_SPEED = 0.4;

    private final VehicleBounds bounds;
    private final VehiclePart[] parts;

    protected RollingStockEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
        bounds = VehicleBounds.get(BuiltInRegistries.ENTITY_TYPE.getKey(type).getPath());
        double length = bounds.front() - bounds.back();
        int count = (int) Math.ceil(length / bounds.width());
        double step = length / count;
        parts = new VehiclePart[count];
        for (int i = 0; i < count; i++) {
            parts[i] = new VehiclePart(this, bounds.back() + step * (i + 0.5), (float) bounds.width(), (float) Math.max(bounds.top() - bounds.bottom(), 1.0), true);
        }
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public VehiclePart[] getParts() {
        return parts;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        for (int i = 0; i < parts.length; i++) {
            parts[i].setId(packet.getId() + i + 1);
        }
    }

    @Override
    protected AABB makeBoundingBox(Vec3 position) {
        // CE's position includes yOffset; its collision box starts at position.y - yOffset.
        return super.makeBoundingBox(position.add(0.0, -Y_OFFSET, 0.0));
    }

    private final StockCollision collisions = new StockCollision();

    public StockCollision collisions() {
        return collisions;
    }

    // --- Ownership --------------------------------------------------------------------------

    private static final EntityDataAccessor<Boolean> LOCKED =
            SynchedEntityData.defineId(RollingStockEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> OWNER =
            SynchedEntityData.defineId(RollingStockEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> ENGINE_NUMBER =
            SynchedEntityData.defineId(RollingStockEntity.class, EntityDataSerializers.STRING);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LOCKED, false);
        builder.define(OWNER, "");
        builder.define(ENGINE_NUMBER, "");
        builder.define(COLOUR, defaultColour());
    }

    public boolean isLocked() {
        return entityData.get(LOCKED);
    }

    public void setLocked(boolean locked) {
        entityData.set(LOCKED, locked);
    }

    public String getOwner() {
        return entityData.get(OWNER);
    }

    public void setOwner(String owner) {
        entityData.set(OWNER, owner);
    }

    public StockAccess access() {
        return new StockAccess(getOwner(), isLocked());
    }

    public boolean supportsEngineNumber() {
        return false;
    }

    public String getEngineNumber() {
        return entityData.get(ENGINE_NUMBER);
    }

    public void setEngineNumber(String number) {
        if (!supportsEngineNumber()) {
            throw new IllegalStateException(spec().entryName() + " has no text overlay");
        }
        entityData.set(ENGINE_NUMBER, sanitizeEngineNumber(number));
    }

    public static String sanitizeEngineNumber(String number) {
        if (number == null) {
            return "";
        }
        String clean = number.replaceAll("[^A-Za-z0-9 ._-]", "").strip();
        return clean.substring(0, Math.min(12, clean.length()));
    }

    /** True when this player may work this piece of stock: unlocked, or theirs. */
    public boolean mayControl(Player player) {
        return access().mayControl(player.getGameProfile().name());
    }

    /** CE's wrench/admin-book click: the owner or an operator toggles the persistent lock. */
    protected boolean toggleLock(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof WrenchItem || stack.getItem() instanceof AdminBookItem)) {
            return false;
        }
        boolean operator = player.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
        if (!getOwner().isEmpty()
                && !access().isOwner(player.getGameProfile().name())
                && !operator) {
            player.sendSystemMessage(Component.literal("You are not the owner!"));
            return true;
        }
        setLocked(!isLocked());
        player.sendSystemMessage(Component.literal(isLocked() ? "Locked." : "Unlocked."));
        return true;
    }

    /** Only CE's two stock cars automatically accept nearby mobs. */
    public boolean acceptsLivestock() {
        return false;
    }

    /**
     * Whether a locked piece of stock still takes a passenger who does not own it.
     *
     * <p>True for a locomotive upstream, which is the whole of the difference: a stranger may climb
     * aboard a locked locomotive and find that nothing they do reaches the controls.
     */
    protected boolean canBeRiddenWhileLocked() {
        return false;
    }

    /**
     * Upstream's refusal in {@code func_130002_c}: a locked piece of stock a stranger may not ride
     * says so and swallows the click, and a locked locomotive says so again when the stranger is
     * holding dye.
     */
    // --- Livery --------------------------------------------------------------------------

    private static final EntityDataAccessor<String> COLOUR =
            SynchedEntityData.defineId(RollingStockEntity.class, EntityDataSerializers.STRING);

    /** CE's default livery is the first entry of the spec's colour list. */
    public String defaultColour() {
        return spec().colours().isEmpty() ? "" : spec().colours().getFirst();
    }

    public String getColour() {
        return entityData.get(COLOUR);
    }

    public void setColour(String colour) {
        if (!spec().colours().contains(colour)) {
            throw new IllegalArgumentException("Unknown " + spec().entryName() + " colour: " + colour);
        }
        entityData.set(COLOUR, colour);
    }

    /** CE's dye paint: the matching dye sets the livery, any other lists the choices. */
    protected boolean paint(Player player, ItemStack stack) {
        var dye = stack.get(net.minecraft.core.component.DataComponents.DYE);
        if (dye == null || spec().colours().isEmpty()) {
            return false;
        }
        if (level().isClientSide() || refusesLocked(player, stack)) {
            return true;
        }
        String colour = switch (dye) {
            case GRAY -> "Grey";
            case LIGHT_GRAY -> "LightGrey";
            case LIGHT_BLUE -> "LightBlue";
            default -> {
                String name = dye.getSerializedName();
                yield Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }
        };
        if (spec().colours().contains(colour)) {
            setColour(colour);
            stack.shrink(1);
        } else if (TraincraftConfig.POSSIBLE_COLOURS_IN_CHAT.get()) {
            player.sendSystemMessage(Component.literal("Possible colors: " + String.join(", ", spec().colours())));
        }
        return true;
    }

    private ItemStack withColour(ItemStack stack) {
        if (!getColour().isEmpty()) {
            net.minecraft.world.item.component.CustomData.update(
                    net.minecraft.core.component.DataComponents.CUSTOM_DATA,
                    stack,
                    tag -> tag.putString("trainColor", getColour()));
        }
        return stack;
    }

    /**
     * A sneaking paintbrush click is the item's, not the stock's: Minecraft only reaches
     * {@code Item.use} when the entity under the crosshair declines the click.
     */
    protected boolean yieldsToPaintbrush(Player player, ItemStack held) {
        return player.isShiftKeyDown() && held.getItem() instanceof traincraft.item.PaintbrushItem;
    }

    protected boolean refusesLocked(Player player, net.minecraft.world.item.ItemStack held) {
        if (!isLocked() || access().isOwner(player.getGameProfile().name())) {
            return false;
        }
        if (!canBeRiddenWhileLocked()
                || (held.getItem() instanceof net.minecraft.world.item.DyeItem
                        && this instanceof LocomotiveEntity)) {
            player.sendSystemMessage(Component.literal("Train is locked"));
            return true;
        }
        return false;
    }

    /** The row this piece of stock was built from. */
    public abstract VehicleDefinition spec();

    /**
     * The name upstream shows in chat.
     *
     * <p>Not the row's display name: `ItemRollingStock` writes the **item's** localised name onto
     * the stock as it is placed, so Community Edition says "0-4-0 Alice" and "Freight Car" where
     * the table says "Loco Steam Alice" and "Freight Cart Yellow".
     */
    public net.minecraft.network.chat.Component getTrainName() {
        return new net.minecraft.world.item.ItemStack(getDropItem()).getHoverName();
    }

    /** Upstream's mass in kilograms: the table's mass column, times ten. */
    public float weightKg() {
        return (float) spec().mass() * 10.0F;
    }

    // --- Couplings --------------------------------------------------------------------------

    public boolean isAttached;
    public boolean isAttaching;
    public double link1;
    public double link2;
    public @Nullable RollingStockEntity cartLinked1;
    public @Nullable RollingStockEntity cartLinked2;
    public @Nullable Consist consist;

    /** Upstream's own tick counter, which the coupling spring waits five of before it pulls. */
    public int updateTicks;

    private int uniqueID = -1;

    private final LinkHandler linkHandler = new LinkHandler();

    public int getUniqueTrainID() {
        return uniqueID;
    }

    /**
     * Takes a fresh coupling id.
     *
     * <p>Upstream increments a copy of its argument before storing it, so the id is the entity id
     * plus one. It is not the entity id, and stored links are compared against it.
     */
    public void setNewUniqueID(int number) {
        uniqueID = number + 1;
    }

    /** Restores an id carried on the item, which is what lets a coupling survive being picked up. */
    public void setUniqueID(int id) {
        uniqueID = id;
    }

    /** Never true: only Railcraft's linkage sets upstream's flag, and that is not ported. */
    public boolean isLinked() {
        return false;
    }

    public float getLinkageDistance(RollingStockEntity other) {
        return optimalDistance(other) + 2.4F;
    }

    /** Half the gap this piece of stock keeps to whatever it is coupled to. */
    public abstract float optimalDistance(@Nullable RollingStockEntity other);

    protected boolean placeInCargo(net.minecraft.world.Container cargo, int fromSlot, ItemStack stack) {
        for (int slot = fromSlot; slot < cargo.getContainerSize(); slot++) {
            ItemStack there = cargo.getItem(slot);
            if (there.isEmpty()) {
                cargo.setItem(slot, stack);
                return true;
            }
            if (ItemStack.isSameItemSameComponents(there, stack)
                    && there.getCount() + stack.getCount() <= there.getMaxStackSize()) {
                there.grow(stack.getCount());
                cargo.setChanged();
                return true;
            }
        }
        return false;
    }

    /** Whether the coupling may move this piece of stock. A locomotive answers with its mode. */
    public boolean canBeAdjusted(RollingStockEntity other) {
        return true;
    }

    /** The trailing contact's position, or null for stock that has only one. */
    public @Nullable Vec3 bogiePosition() {
        return bogie == null ? null : new Vec3(bogie.x(), bogie.y(), bogie.z());
    }

    /** Breaks both of this piece of stock's couplings, from both ends. */
    public void unLink() {
        if (!isAttached) {
            return;
        }
        for (RollingStockEntity neighbour : new RollingStockEntity[] {cartLinked1, cartLinked2}) {
            if (neighbour == null) {
                continue;
            }
            if (neighbour.link1 == uniqueID) {
                neighbour.link1 = 0.0;
                neighbour.cartLinked1 = null;
                if (neighbour.consist != null) {
                    neighbour.consist.members().clear();
                }
            } else if (neighbour.link2 == uniqueID) {
                neighbour.link2 = 0.0;
                neighbour.cartLinked2 = null;
                if (neighbour.consist != null) {
                    neighbour.consist.members().clear();
                }
            }
        }
        cartLinked1 = null;
        cartLinked2 = null;
        isAttached = false;
    }

    /** Drops the live references to coupled stock but keeps the saved ids, so a reload relinks. */
    private void forgetNeighbours() {
        for (RollingStockEntity neighbour : new RollingStockEntity[] {cartLinked1, cartLinked2}) {
            if (neighbour == null) {
                continue;
            }
            if (neighbour.cartLinked1 == this) {
                neighbour.cartLinked1 = null;
            } else if (neighbour.cartLinked2 == this) {
                neighbour.cartLinked2 = null;
            }
        }
        cartLinked1 = null;
        cartLinked2 = null;
        if (consist != null) {
            Consist.ALL.remove(consist);
            consist.reset();
        }
    }

    /**
     * Rebuilds the consist this piece of stock belongs to, every forty ticks.
     *
     * <p>Membership is not maintained as couplings are made and broken; it is thrown away and
     * worked out again from the links, which is why a newly coupled cart takes up to two seconds
     * to start counting against the locomotive's power.
     */
    private void handleTrain() {
        if (this instanceof LocomotiveEntity locomotive
                && consist != null
                && locomotive.canBeAdjusted(locomotive)
                && !locomotive.canBePulled()) {
            locomotive.setCanBeAdjusted(false);
        }
        if (updateTicks % 40 != 0) {
            return;
        }
        boolean coupled = cartLinked1 != null || cartLinked2 != null;
        if (Consist.ALL.isEmpty()) {
            if (coupled) {
                consist = new Consist(this);
            }
            return;
        }
        if ((consist == null || consist.members().isEmpty()) && coupled) {
            if (cartLinked1 != null
                    && cartLinked1.consist != null
                    && !cartLinked1.consist.members().isEmpty()) {
                consist = cartLinked1.consist;
                return;
            }
            if (cartLinked2 != null
                    && cartLinked2.consist != null
                    && !cartLinked2.consist.members().isEmpty()) {
                consist = cartLinked2.consist;
                return;
            }
            consist = new Consist(this);
        }
    }

    /** Finds the stock a saved link points at, by the id stored with it. */
    private void relinkFromIds() {
        if ((cartLinked1 != null || link1 == 0.0) && (cartLinked2 != null || link2 == 0.0)) {
            return;
        }
        for (Entity entity : level().getEntities(this, getBoundingBox().inflate(15.0, 15.0, 15.0))) {
            if (entity instanceof RollingStockEntity stock) {
                if (stock.getUniqueTrainID() == link1) {
                    cartLinked1 = stock;
                } else if (stock.getUniqueTrainID() == link2) {
                    cartLinked2 = stock;
                }
            }
        }
    }

    protected double driverFeetOffset(Entity passenger, double seatHeight) {
        return seatHeight - 1.0 + passenger.getVehicleAttachmentPoint(this).y;
    }

    protected Vec3 driverSeat(Entity passenger, double distance, double seatHeight) {
        double pitchRadians = Math.toRadians(getXRot());
        double heading = Math.toRadians(getYRot() + 90.0F);
        return new Vec3(
                Math.cos(heading) * distance,
                driverFeetOffset(passenger, seatHeight) + Math.tan(pitchRadians) * distance,
                Math.sin(heading) * distance);
    }

    public void shiftAlongTrack(Vec3 offset) {
        if (offset.lengthSqr() == 0.0) return;
        setPos(getX() + offset.x, getY(), getZ() + offset.z);
        if (bogie != null) {
            bogie.moveBy(offset.x, 0.0, offset.z);
        }
    }

    /** Both contacts participate, including the bogie which is no longer a world entity. */
    private void pushNeighbours() {
        collisions.tick(this);
    }

    @Override
    public void push(Entity other) {
        StockCollision.apply(this, other);
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!reason.shouldDestroy()) {
            forgetNeighbours();
            super.remove(reason);
            return;
        }
        if (!level().isClientSide()) {
            StockLog.delete(this);
        }
        unLink();
        if (consist != null) {
            for (RollingStockEntity member : consist.members()) {
                if (member instanceof LocomotiveEntity) {
                    member.cartLinked1 = null;
                    member.link1 = 0.0;
                    member.cartLinked2 = null;
                    member.link2 = 0.0;
                }
                if (member != this && member.consist != null) {
                    member.consist.members().clear();
                }
            }
            if (consist.members().size() <= 1) {
                consist.members().clear();
                Consist.ALL.remove(consist);
            }
        }
        super.remove(reason);
    }

    // --- TrackMovement.Body -----------------------------------------------------------------

    @Override
    public double x() {
        return getX();
    }

    @Override
    public double y() {
        return getY();
    }

    @Override
    public double z() {
        return getZ();
    }

    @Override
    public void setPosition(double x, double y, double z) {
        setPos(x, y, z);
    }

    @Override
    public double motionX() {
        return getDeltaMovement().x;
    }

    @Override
    public double motionY() {
        return getDeltaMovement().y;
    }

    @Override
    public double motionZ() {
        return getDeltaMovement().z;
    }

    @Override
    public void setMotion(double x, double y, double z) {
        setDeltaMovement(x, y, z);
    }

    /**
     * Stock that runs into a block carrying track a level up passes into it instead of stopping:
     * the tunnel builder lays a climb that way, and once the centre reaches that column the track
     * above is the one driven over, which lifts the stock onto it.
     */
    @Override
    public void moveBy(double dx, double dy, double dz) {
        Vec3 from = position();
        move(MoverType.SELF, new Vec3(dx, dy, dz));
        if (horizontalCollision && climbAhead(from, dx, dz)) {
            setPos(from.x + dx, from.y + dy, from.z + dz);
        }
    }

    private boolean climbAhead(Vec3 from, double dx, double dz) {
        double length = Math.sqrt(dx * dx + dz * dz);
        if (length < 1.0E-6) {
            return false;
        }
        double reach = getBbWidth() / 2.0 + 0.5;
        BlockPos above = BlockPos.containing(from.x + dx / length * reach, Math.floor(from.y - yOffset() + 0.1) + 1.0, from.z + dz / length * reach);
        BlockState state = level().getBlockState(above);
        return BaseRailBlock.isRail(state) || state.getBlock() instanceof TrackBlock;
    }

    @Override
    public double yOffset() {
        return Y_OFFSET;
    }

    @Override
    public float yaw() {
        return getYRot();
    }

    @Override
    public boolean isLocomotive() {
        return false;
    }

    // --- Movement ---------------------------------------------------------------------------

    /** The ceiling on Traincraft's own track, in blocks per tick. */
    protected double tcRailSpeedCap() {
        return SpeedHandler.handleSpeed(TC_RAIL_MAX_SPEED, false, 0.0);
    }

    /** The ceiling on a vanilla rail, which reports its own figure. */
    protected double vanillaRailSpeedCap(double railMaxSpeed) {
        return SpeedHandler.handleSpeed(railMaxSpeed, false, 0.0);
    }

    protected boolean hasTrackDrag() {
        return true;
    }

    /**
     * Spreads each position update over the ticks until the next one is due.
     *
     * <p>Without this the client applies a position packet with a bare {@code setPos} -- which is
     * what {@code Entity.moveOrInterpolateTo} does when there is no handler -- and the locomotive
     * stands still for two ticks and jumps on the third, because the entity type sends every third.
     * The motion trace measured exactly that: three ticks at the same z, then 0.18 blocks at once,
     * for a train travelling a steady 0.06 blocks a tick. It reads as a shudder that gets worse the
     * faster the train goes.
     *
     * <p>Three steps, matching the type's {@code updateInterval}. Any more and the drawn position
     * lags the real one; any fewer and there is a gap at the end of each interval.
     */
    private final InterpolationHandler interpolation = new InterpolationHandler(this, 3);

    @Override
    public InterpolationHandler getInterpolation() {
        return interpolation;
    }

    private final WheelAnimation wheelAnimation = new WheelAnimation();

    /** CE's DataWatcher 22: how many inventory slots hold something, for the models whose load shows. */
    public int filledSlots() {
        return 0;
    }

    public float wheelAngle(float partialTicks) {
        return wheelAnimation.sample(partialTicks);
    }

    /** Resolve rail geometry before the spawn packet exposes the placement pose to clients. */
    public void alignToTrackOnPlacement() {
        BlockPos pos = BlockPos.containing(getX(), Math.floor(getY() - yOffset() + 0.1), getZ());
        boolean onTrack =
                driveOverTrackAt(pos)
                        || driveOverTrackAt(pos.below())
                        || driveOverTrackAt(pos.above());
        // Placement establishes a resting pose, even on rails that provide acceleration.
        setDeltaMovement(Vec3.ZERO);
        if (onTrack) {
            if (bogieShift() != 0.0) {
                updateBogie();
            } else {
                updateOrientationFromMotion();
            }
        }
        // Seed the previous pose too, so the first frame cannot interpolate from the old angle.
        snapTo(getX(), getY(), getZ(), getYRot(), getXRot());
    }

    @Override
    public void tick() {
        super.tick();
        // Server side this does nothing -- nothing ever starts an interpolation there -- so it
        // sits above the split rather than inside the client branch, next to the super call it
        // belongs with.
        interpolation.interpolate();
        wheelAnimation.tick(xo != getX() ? xo - getX() : zo - getZ());
        double heading = Math.toRadians(getYRot());
        for (VehiclePart part : parts) {
            part.setPos(getX() + Math.sin(heading) * part.offset(), getY() + bounds.bottom(), getZ() - Math.cos(heading) * part.offset());
        }
        if (level().isClientSide()) {
            // The server owns the position; the client interpolates towards what it is sent. The
            // alternative, running the same physics on both, drifts as soon as one tick is missed.
            return;
        }

        if (uniqueID == -1) {
            // Not the entity id: that restarts from zero with the server, and the id is saved and
            // compared against saved links, so a cart placed after a restart would couple to the
            // links of one placed before it.
            setNewUniqueID(getRandom().nextInt(Integer.MAX_VALUE - 1));
        }
        relinkFromIds();
        if (tickCount % 120 == 0 && TraincraftConfig.TRANSPORT_LOGGING.get()) {
            StockLog.write(this);
        }

        BlockPos pos = BlockPos.containing(getX(), Math.floor(getY() - yOffset() + 0.1), getZ());
        double before = TrackMovement.planarSpeed(this);
        boolean on =
                driveOverTrackAt(pos)
                        || driveOverTrackAt(pos.below())
                        || driveOverTrackAt(pos.above());
        if (!on) {
            fallOffTrack();
        }
        if (traincraft.Traincraft.TRACE && tickCount % 5 == 0) {
            traincraft.Traincraft.LOGGER.info(
                    "t{} onTrack={} speed {} -> {} pos {},{},{}",
                    tickCount,
                    on,
                    before,
                    TrackMovement.planarSpeed(this),
                    getX(),
                    getY(),
                    getZ());
        }
        if (bogieShift() != 0.0) {
            updateBogie();
        } else {
            updateOrientationFromMotion();
        }
        updateTicks++;
        pushNeighbours();
        handleTrain();
        linkHandler.handleStake(this);
        if (getHurtTime() > 0) {
            setHurtTime(getHurtTime() - 1);
        }
        if (getDamage() > 0.0F) {
            setDamage(getDamage() - 1.0F);
        }
    }

    /**
     * Drives one tick over whatever track is at {@code pos}.
     *
     * <p>Filler blocks resolve to the rail that owns them, which is what lets a train run over the
     * middle of a curve: only one block of a multi-block piece carries the geometry.
     *
     * @return false when there is no Traincraft track there
     */
    protected double bogieShift() {
        return 0.0;
    }

    /** The trailing contact, created the first tick it is needed. Null for single-contact stock. */
    protected @org.jspecify.annotations.Nullable BogieState bogie;

    /**
     * Runs the bogie over its own stretch of track and takes the body's angle from the chord.
     *
     * <p>This is what makes a long locomotive sit correctly on a curve. Taking the angle from the
     * direction of travel instead points the body along the tangent at its front contact, which on
     * anything with a wheelbase is visibly wrong -- the body leans into the curve rather than
     * spanning it.
     */
    private void updateBogie() {
        double shift = bogieShift();
        if (shift == 0.0) {
            return;
        }
        if (bogie == null) {
            updateOrientationFromMotion();
            bogie = new BogieState(yOffset());
            bogie.placeRelativeTo(getX(), getY(), getZ(), getYRot(), shift);
        }
        // The bogie is dragged, so it inherits the body's velocity and then the track decides
        // where that actually takes it.
        bogie.setMotion(motionX(), motionY(), motionZ());
        BlockPos at =
                BlockPos.containing(
                        bogie.x(), Math.floor(bogie.y() - bogie.yOffset() + 0.1), bogie.z());
        boolean bogieOnTrack =
                driveBodyOverTrackAt(bogie, at) || driveBodyOverTrackAt(bogie, at.below());
        if (traincraft.Traincraft.TRACE && tickCount % 5 == 0) {
            traincraft.Traincraft.LOGGER.info(
                    "t{} bogie onTrack={} body {},{} bogie {},{} yaw {}",
                    tickCount,
                    bogieOnTrack,
                    getX(),
                    getZ(),
                    bogie.x(),
                    bogie.z(),
                    getYRot());
        }
        if (!bogieOnTrack && (motionX() != 0.0 || motionZ() != 0.0)) {
            // Upstream's isDerail: once a bogie has been dragged off the rail under power it never
            // counts as being on one again, and that is what takes the locomotive's couplings and
            // its curves with it.
            bogieDerailed = true;
        }
        if (!bogieOnTrack) {
            // Off the end of the line -- or, at the start of a run, not yet on it: a locomotive
            // placed at the first block of a straight trails its bogie behind the rail's own
            // beginning. Dragged along by the body rather than left where it is, because leaving
            // it froze it there permanently: only the track moves the bogie, so a bogie that is
            // off the track can never get back on, and the body's angle is computed from a chord
            // to a point it left minutes ago. The yaw is not touched while this is the case; a
            // chord to a bogie that is not following anything is not worth believing.
            updateOrientationFromMotion();
            bogie.placeRelativeTo(getX(), getY(), getZ(), getYRot(), shift);
            return;
        }

        double dx = bogie.x() - getX();
        double dz = bogie.z() - getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        if (horizontal < 1.0E-4) {
            return;
        }

        float yaw = VehicleOrientation.fromDirection(dx, dz);
        setYRot(yaw);
        setYHeadRot(yaw);
        setXRot((float) Math.toDegrees(Math.atan((bogie.y() - getY()) / horizontal)));
    }

    private boolean driveOverTrackAt(BlockPos pos) {
        return driveBodyOverTrackAt(this, pos);
    }

    private boolean driveBodyOverTrackAt(TrackMovement.Body body, BlockPos pos) {
        BlockState state = level().getBlockState(pos);
        if (state.getBlock() instanceof BaseRailBlock railBlock) {
            driveOnVanillaRail(body, pos, state, railBlock);
            return true;
        }
        TrackBlockEntity rail = null;
        if (state.getBlock() instanceof TrackBlock) {
            if (level().getBlockEntity(pos) instanceof TrackBlockEntity tile) {
                rail = tile;
            }
        } else if (state.getBlock() instanceof TrackOccupancyBlock) {
            if (level().getBlockEntity(pos) instanceof TrackOccupancyBlockEntity gag
                    && level().getBlockEntity(gag.origin()) instanceof TrackBlockEntity owner) {
                rail = owner;
            }
        }
        if (rail == null || rail.getTrackType() == null) {
            return false;
        }

        if (body == this && bogie != null && bogieDerailed) {
            derailSpeed = 0.0;
        }
        TrackMovement.limitSpeed(body, tcRailSpeedCap(), hasTrackDrag());
        driveOver(body, rail, pos.getY());
        return true;
    }

    /**
     * Upstream's {@code derailSpeed}: the speed above which a curve throws the stock off.
     *
     * <p>Private in upstream too, never restored, never saved and not configurable. Once a bogie has
     * left the rail it is set to zero for good, and from then on the piece of stock cannot take a
     * curve at all.
     */
    private double derailSpeed = 0.46;

    /** Latched once the trailing contact has been dragged off the rail while under way. */
    private boolean bogieDerailed;

    /** Runs one tick along a Minecraft rail, which is not the same geometry as a Traincraft one. */
    private void driveOnVanillaRail(
            TrackMovement.Body body, BlockPos pos, BlockState state, BaseRailBlock railBlock) {
        RailShape shape = state.getValue(railBlock.getShapeProperty());
        boolean poweredRail = state.is(Blocks.POWERED_RAIL);
        // Upstream reads the raw metadata and calls anything above two powered, which takes in every
        // sloped golden rail whether it is switched on or not. Kept, quirk and all.
        boolean powered =
                poweredRail && (shape.isSlope() || state.getValue(PoweredRailBlock.POWERED));

        if (state.is(Blocks.DETECTOR_RAIL) && !state.getValue(DetectorRailBlock.POWERED)) {
            // 26.2's detector rail only looks for minecarts, so nothing of ours would ever set it.
            BlockState powered_ = state.setValue(DetectorRailBlock.POWERED, true);
            level().setBlock(pos, powered_, Block.UPDATE_ALL);
            level().updateNeighborsAt(pos, railBlock);
            level().updateNeighborsAt(pos.below(), railBlock);
            level().scheduleTick(pos, railBlock, 20);
        }

        boolean fullBody = body == this;
        if (fullBody && bogie != null && bogieDerailed) {
            derailSpeed = 0.0;
            unLink();
        }

        boolean derailed =
                TrackMovement.moveOnVanillaRail(
                        body,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        shape.ordinal(),
                        poweredRail,
                        powered,
                        fullBody,
                        fullBody,
                        fullBody ? vanillaRailSpeedCap(VANILLA_RAIL_MAX_SPEED) : BOGIE_MAX_SPEED,
                        fullBody && !isLocomotive() ? 0.99 : 1.0,
                        isLocomotive() ? 1.0 : 0.98,
                        fullBody && (isLocomotive() || bogieShift() != 0.0),
                        derailSpeed,
                        (x, y, z) -> {
                            BlockPos at = new BlockPos(x, y, z);
                            return level().getBlockState(at).isRedstoneConductor(level(), at);
                        });
        if (fullBody) {
            railYaw =
                    switch (shape) {
                        case NORTH_SOUTH, ASCENDING_NORTH, ASCENDING_SOUTH -> 0.0F;
                        case EAST_WEST, ASCENDING_EAST, ASCENDING_WEST -> 90.0F;
                        case SOUTH_EAST, NORTH_WEST -> 45.0F;
                        case SOUTH_WEST, NORTH_EAST -> -45.0F;
                    };
        }
        if (derailed
                && getFirstPassenger() instanceof Player driver
                && level() instanceof ServerLevel server) {
            server.getServer()
                    .getPlayerList()
                    .broadcastSystemMessage(
                            Component.literal(
                                    driver.getGameProfile().name()
                                            + " derailed "
                                            + getOwner()
                                            + "'s locomotive"),
                            false);
        }
    }

    /** Upstream's {@code EntityBogie.getMaxCartSpeedOnRail}. */
    private static final double BOGIE_MAX_SPEED = 1.8;

    private @org.jspecify.annotations.Nullable Float railYaw;

    private Vec3 slopeRise = Vec3.ZERO;

    private void driveOver(TrackMovement.Body body, TrackBlockEntity rail, int j) {
        TrackType type = rail.getTrackType();
        BlockPos owner = rail.getBlockPos();
        int meta = railMeta(rail);
        BlockPos trailedSwitch = trailedSwitch(body, rail, type, meta);
        if (body == this) {
            railYaw = null;
            slopeRise = Vec3.ZERO;
            switch (type.category()) {
                case STRAIGHT, SLOPE -> railYaw = (meta == 0 || meta == 2) ? 0.0F : 90.0F;
                case TURN, SWITCH -> {
                    var curve = rail.geometry().curve();
                    if (curve != null
                            && curve.radius() > 0
                            && trailedSwitch == null
                            && (type.category() != TrackCategory.SWITCH || rail.isSwitchActive())) {
                        railYaw =
                                VehicleOrientation.fromDirection(
                                        -(getZ() - curve.centerZ()), getX() - curve.centerX());
                    } else {
                        railYaw = (meta == 0 || meta == 2) ? 0.0F : 90.0F;
                    }
                }
                default -> {}
            }
            var slope = rail.geometry().slope();
            if (type.category() == TrackCategory.SLOPE && slope != null) {
                double grade = slope.height() / slope.length();
                slopeRise =
                        switch (meta) {
                            case 0 -> new Vec3(0.0, 0.0, grade);
                            case 2 -> new Vec3(0.0, 0.0, -grade);
                            case 1 -> new Vec3(-grade, 0.0, 0.0);
                            default -> new Vec3(grade, 0.0, 0.0);
                        };
            }
        }
        switch (type.category()) {
            case STRAIGHT ->
                    TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
            case TURN, SWITCH -> {
                if (derailSpeed == 0.0) {
                    // Derailed stock cannot hold a curve at all: upstream breaks its couplings
                    // again every tick and runs it along the straight the curve stands in for.
                    unLink();
                    TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
                    break;
                }
                if (trailedSwitch != null) {
                    if (level().getBlockEntity(trailedSwitch) instanceof TrackBlockEntity pointwork) {
                        pointwork.setManualOverride(true);
                        pointwork.setSwitchActive(false);
                    }
                    TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
                    break;
                }
                if (traincraft.Traincraft.TRACE) {
                    traincraft.Traincraft.LOGGER.info(
                            "over {} at {}: modifiable {} active {}",
                            type,
                            owner.toShortString(),
                            rail.switchControlled(),
                            rail.isSwitchActive());
                }
                // A switch set against the branch behaves as the straight it is standing in for.
                // Only a switch: the rails a thrown switch rewrites into turns carry the same
                // canTypeBeModifiedBySwitch flag and a switchActive of their own that nothing ever
                // sets, and reading the flag on those turned every branch back into a straight --
                // which is exactly what a locomotive did when driven through a thrown switch.
                if (type.category() == TrackCategory.SWITCH && !rail.isSwitchActive()) {
                    TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
                } else if (rail.geometry().curve() != null
                        && rail.geometry().curve().radius() > 0.0) {
                    if (traincraft.Traincraft.TRACE) {
                        traincraft.Traincraft.LOGGER.info(
                                "turn at {} type {} r {} c {},{}",
                                rail.getBlockPos().toShortString(),
                                type,
                                rail.geometry().curve().radius(),
                                rail.geometry().curve().centerX(),
                                rail.geometry().curve().centerZ());
                    }
                    TrackMovement.moveOnTurn(
                            body,
                            j,
                            rail.geometry().curve().radius(),
                            rail.geometry().curve().centerX(),
                            rail.geometry().curve().centerZ());
                } else {
                    TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
                }
            }
            case SLOPE ->
                    TrackMovement.moveOnSlope(
                            body,
                            j,
                            owner.getX(),
                            owner.getZ(),
                            (rail.geometry().slope() == null ? 0 : rail.geometry().slope().angle()),
                            meta);
            case CROSSING -> TrackMovement.moveOnCrossing(body, j);
            default -> TrackMovement.moveOnStraight(body, j, owner.getX(), owner.getZ(), meta);
        }
    }

    /**
     * Upstream's {@code shouldIgnoreSwitch}: the switch behind a rail it has rewritten into a
     * turn, when the body is running along that rail's straight axis back towards it. Wheels
     * trailing through thrown points push them over rather than being thrown onto the branch,
     * so the switch is reset and the body runs straight.
     *
     * @return the switch's position, or null when the rail is not being trailed through
     */
    private @Nullable BlockPos trailedSwitch(
            TrackMovement.Body body, TrackBlockEntity rail, TrackType type, int meta) {
        if (type.category() != TrackCategory.TURN || !rail.switchControlled()) {
            return null;
        }
        boolean alongZ = Math.abs(body.motionX()) < 0.01;
        boolean alongX = Math.abs(body.motionZ()) < 0.01;
        BlockPos at = rail.getBlockPos();
        return switch (meta) {
            case 0 -> alongZ && body.motionZ() < 0 ? at.offset(0, 0, -1) : null;
            case 2 -> alongZ && body.motionZ() > 0 ? at.offset(0, 0, 1) : null;
            case 1 -> alongX && body.motionX() > 0 ? at.offset(1, 0, 0) : null;
            default -> alongX && body.motionX() < 0 ? at.offset(-1, 0, 0) : null;
        };
    }

    private int railMeta(TrackBlockEntity rail) {
        BlockState state = level().getBlockState(rail.getBlockPos());
        if (state.getBlock() instanceof TrackBlock) {
            return TrackBlock.metaFromFacing(state.getValue(TrackBlock.FACING));
        }
        return rail.getFacing();
    }

    /**
     * Upstream's {@code moveMinecartOffRail}: gravity, a lateral clamp, a halving on the ground and
     * air drag off it. The clamp is Forge's {@code maxSpeedAirLateral} default.
     */
    protected void fallOffTrack() {
        slopeRise = Vec3.ZERO;
        if (!isNoGravity()) {
            setDeltaMovement(getDeltaMovement().add(0.0, -0.04, 0.0));
        }
        double lateral = MAX_SPEED_AIR_LATERAL;
        setDeltaMovement(
                Math.clamp(getDeltaMovement().x, -lateral, lateral),
                getDeltaMovement().y,
                Math.clamp(getDeltaMovement().z, -lateral, lateral));
        if (onGround()) {
            setDeltaMovement(getDeltaMovement().multiply(0.5, 0.5, 0.5));
        }
        move(MoverType.SELF, getDeltaMovement());
        if (!onGround()) {
            double drag = isLocomotive() ? 1.0 : 0.98;
            setDeltaMovement(getDeltaMovement().multiply(drag, drag, drag));
        }
    }

    private static final double MAX_SPEED_AIR_LATERAL = 0.4;

    /**
     * Points the body along its travel, or along the rail when it is not travelling.
     *
     * <p>At rest the direction of travel is meaningless -- recomputing it from a zero vector snaps
     * the body to face east -- so a stationary piece of stock takes the axis of the rail it is
     * standing on instead. On a curve there is no single axis, and it keeps the angle it had.
     */
    protected void updateOrientationFromMotion() {
        double speed = TrackMovement.planarSpeed(this);
        if (speed < 1.0E-4) {
            if (railYaw != null) {
                // The rail gives an axis, not a direction. Taking it literally turns a piece of
                // stock that was placed facing one way round to face the other the moment it
                // stops, so the end nearer the angle it already has is the one it keeps.
                float yaw = VehicleOrientation.nearestDirection(railYaw, getYRot());
                setYRot(yaw);
                setYHeadRot(yaw);
            }
            setXRot(slopePitch());
            return;
        }

        float yaw =
                VehicleOrientation.nearestDirection(
                        VehicleOrientation.fromDirection(motionX(), motionZ()), getYRot());
        setYRot(yaw);
        setYHeadRot(yaw);
        setXRot(slopePitch());
    }

    private float slopePitch() {
        double heading = Math.toRadians(getYRot());
        double rise = -Math.sin(heading) * slopeRise.x + Math.cos(heading) * slopeRise.z;
        return (float) Math.toDegrees(Math.atan(rise));
    }

    public int speedKmH() {

        return (int) (TrackMovement.planarSpeed(this) * 6.0 * 36.0);
    }

    // --- Persistence ------------------------------------------------------------------------

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        setLocked(input.getBooleanOr("locked", false));
        setOwner(input.getString("trainOwner").orElse(""));
        if (supportsEngineNumber()) {
            setEngineNumber(input.getString("engineNumber").orElse(""));
        }
        uniqueID = input.getIntOr("uniqueID", -1);
        isAttached = input.getBooleanOr("isAttached", false);
        link1 = input.getDoubleOr("Link1", 0.0);
        link2 = input.getDoubleOr("Link2", 0.0);
        String colour = input.getStringOr("trainColor", defaultColour());
        entityData.set(COLOUR, spec().colours().contains(colour) ? colour : defaultColour());
        isAttaching = false;
        cartLinked1 = null;
        cartLinked2 = null;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putBoolean("locked", isLocked());
        output.putString("trainOwner", getOwner());
        if (supportsEngineNumber()) {
            output.putString("engineNumber", getEngineNumber());
        }
        output.putInt("uniqueID", uniqueID);
        output.putBoolean("isAttached", isAttached);
        output.putDouble("Link1", link1);
        output.putDouble("Link2", link2);
        output.putString("trainColor", getColour());
    }

    // --- Damage -----------------------------------------------------------------------------

    /**
     * Whether a locked piece of stock ignores this blow.
     *
     * <p>Upstream: locked stock takes nothing but a projectile from a non-player source, and tells
     * a player who is not its owner so.
     */
    /**
     * Upstream's common half of {@code attackEntityFrom}: the blow is registered and the damage is
     * counted; unlike upstream, a creative blow counts the same as any other. Removal is the
     * subclass's, because upstream only removes a passenger car here.
     */
    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (isRemoved()) {
            return true;
        }
        if (source.getEntity() instanceof Player player
                && !source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE)) {
            setHurtDir(-getHurtDir());
            setHurtTime(10);
            markHurt();
            setDamage(getDamage() + damage * 10.0F);
        }
        return true;
    }

    /** CE told an operator in creative whose train they took apart; here it is said once the stock actually goes. */
    protected void announceRemoval(DamageSource source) {
        if (TraincraftConfig.WAGON_REMOVAL_NOTICES.get()
                && source.getEntity() instanceof net.minecraft.server.level.ServerPlayer operator
                && operator.getAbilities().instabuild
                && operator.permissions().hasPermission(net.minecraft.server.permissions.Permissions.COMMANDS_GAMEMASTER)) {
            operator.sendSystemMessage(Component.literal("Operator removed train owned by " + getOwner()));
        }
    }

    /**
     * The item this piece of stock leaves behind, carrying its coupling id.
     *
     * <p>Upstream writes the id onto the stack so that a coupled cart picked up and put back down
     * again is still the cart its neighbour's link points at.
     */
    protected net.minecraft.world.item.ItemStack dropStack() {
        net.minecraft.world.item.ItemStack stack =
                new net.minecraft.world.item.ItemStack(getDropItem());
        if (uniqueID != -1) {
            net.minecraft.world.item.component.CustomData.update(
                    net.minecraft.core.component.DataComponents.CUSTOM_DATA,
                    stack,
                    tag -> tag.putInt("uniqueID", uniqueID));
        }
        if (supportsEngineNumber() && !getEngineNumber().isEmpty()) {
            net.minecraft.world.item.component.CustomData.update(
                    net.minecraft.core.component.DataComponents.CUSTOM_DATA,
                    stack,
                    tag -> tag.putString("engineNumber", getEngineNumber()));
        }
        return withColour(stack);
    }

    protected boolean canBeDestroyedByPlayer(DamageSource source) {
        if (!isLocked()) {
            return false;
        }
        if (!(source.getEntity() instanceof Player player)) {
            return !source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE);
        }
        if (player.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)
                && player.getMainHandItem().is(TraincraftItems.item("composite_wrench"))) {
            player.sendSystemMessage(Component.literal("Removing the train using OP permission"));
            return false;
        }
        if (!player.getGameProfile().name().equalsIgnoreCase(getOwner())) {
            player.sendSystemMessage(Component.literal("You are not the owner!"));
            return true;
        }
        return false;
    }

    /**
     * Upstream's {@code canBePushed}: ordinary entities may push freight, but locomotives override
     * it. Train-to-train contacts are resolved explicitly by StockCollision for both types.
     */
    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return !isRemoved();
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(getDropItem());
    }

    /** The fluid this piece of stock carries, for the admin book's log; null for dry stock. */
    public @Nullable ResourceHandler<FluidResource> tank() {
        return null;
    }

    @Override
    public boolean canBeCollidedWith(net.minecraft.world.entity.Entity other) {
        // Rail vehicles exchange impulses; a solid entity box would stop them before contact.
        return !(other instanceof RollingStockEntity)
                && !(other instanceof net.minecraft.world.entity.vehicle.minecart.AbstractMinecart)
                && (other == null || !isPassengerOfSameVehicle(other));
    }

    @Override
    public boolean canCollideWith(Entity other) {
        return !(other instanceof RollingStockEntity)
                && !(other instanceof net.minecraft.world.entity.vehicle.minecart.AbstractMinecart)
                && super.canCollideWith(other);
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
            Entity entity, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0, dimensions.height() * 0.5, 0.0);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        double heading = Math.toRadians(getYRot());
        double distance = bounds.width() / 2.0 + passenger.getBbWidth();
        Vec3 side = new Vec3(Math.cos(heading) * distance, 0.0, Math.sin(heading) * distance);
        double facing = Math.toRadians(passenger.getYRot());
        if (-Math.sin(facing) * side.x + Math.cos(facing) * side.z < 0.0) {
            side = side.scale(-1.0);
        }
        for (Vec3 offset : List.of(side, side.scale(-1.0))) {
            double targetX = getX() + offset.x;
            double targetZ = getZ() + offset.z;
            BlockPos targetBlockPos = BlockPos.containing(targetX, getBoundingBox().maxY, targetZ);
            List<Vec3> targets = new ArrayList<>();
            for (BlockPos candidate : List.of(targetBlockPos, targetBlockPos.below(), targetBlockPos.below(2))) {
                double floor = level().getBlockFloorHeight(candidate);
                if (DismountHelper.isBlockFloorValid(floor)) {
                    targets.add(new Vec3(targetX, candidate.getY() + floor, targetZ));
                }
            }
            for (Pose dismountPose : passenger.getDismountPoses()) {
                for (Vec3 target : targets) {
                    if (DismountHelper.canDismountTo(level(), target, passenger, dismountPose)) {
                        passenger.setPose(dismountPose);
                        return target;
                    }
                }
            }
        }
        return super.getDismountLocationForPassenger(passenger);
    }
}
