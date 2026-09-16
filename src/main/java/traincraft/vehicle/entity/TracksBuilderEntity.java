package traincraft.vehicle.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.production.ProductionRegistry;
import traincraft.track.PlacementResult;
import traincraft.track.TrackPlacer;
import traincraft.track.TrackType;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlock;
import traincraft.track.block.TrackOccupancyBlockEntity;
import traincraft.track.item.TrackItem;
import traincraft.vehicle.inventory.TracksBuilderMenu;

public abstract class TracksBuilderEntity extends FreightEntity {

    public static final int FUEL_SLOT = 0;
    public static final int MATERIALS_FROM = 1;
    public static final int MATERIAL_SLOTS = 27;
    public static final int DUG_FROM = MATERIALS_FROM + MATERIAL_SLOTS;
    public static final int DUG_SLOTS = 27;
    public static final int SLOTS = DUG_FROM + DUG_SLOTS;
    public static final int TEMPLATE_WIDTH = 5;
    public static final int TEMPLATE_HEIGHT = 6;
    public static final int TEMPLATE_CELLS = TEMPLATE_WIDTH * TEMPLATE_HEIGHT;
    /** Row one, middle column: the rail itself, drawn for reference and never marked. */
    public static final int RAIL_CELL = TEMPLATE_WIDTH + 2;
    public static final int PROFILE_WIDTH = 3;
    public static final int PROFILE_HEIGHT = 3;
    private static final int SLOPE_LENGTH = 6;
    public static final int MAX_FUEL = 5000;

    private static final int COAL_FUEL = 300;
    private static final int REDSTONE_FUEL = 100;
    private static final int DIESEL_FUEL = 1200;
    private static final double PUSH = 0.029;

    private static final EntityDataAccessor<Integer> FUEL =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PLANNED_HEIGHT =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STATUS =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> STATUS_DETAIL =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> STARTED =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> TEMPLATE =
            SynchedEntityData.defineId(TracksBuilderEntity.class, EntityDataSerializers.STRING);

    private int fuel;
    private Vec3 push = Vec3.ZERO;
    private boolean skipTick = true;
    private int lastRailLevel = Integer.MIN_VALUE;

    public enum Status {
        STOPPED("Stopped"),
        WORKING("Working"),
        NO_FUEL("Out of fuel"),
        NO_TRACK("No track"),
        NO_FLOOR("Nothing under the track"),
        NO_MATERIAL("Missing "),
        NO_ROOM("No room for the slope"),
        WATER("Water ahead"),
        BLOCKED("Cannot dig ");

        public final String label;

        Status(String label) {
            this.label = label;
        }
    }

    protected TracksBuilderEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected int cargoSlots() {
        return SLOTS;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
        builder.define(PLANNED_HEIGHT, 0);
        builder.define(STATUS, Status.STOPPED.ordinal());
        builder.define(STATUS_DETAIL, "");
        builder.define(STARTED, false);
        builder.define(TEMPLATE, ",".repeat(TEMPLATE_CELLS - 1));
    }

    public int getFuel() {
        return entityData.get(FUEL);
    }

    public boolean isWorking() {
        return status() == Status.WORKING;
    }

    public Status status() {
        return Status.values()[entityData.get(STATUS)];
    }

    /** The status line as the screen shows it: the label, plus the block it is missing or cannot dig. */
    public String statusText() {
        return status().label + entityData.get(STATUS_DETAIL);
    }

    private void setStatus(Status status, String detail) {
        entityData.set(STATUS, status.ordinal());
        entityData.set(STATUS_DETAIL, detail);
    }

    public boolean isStarted() {
        return entityData.get(STARTED);
    }

    /** Turns the builder round on the spot: the other way becomes the way it digs and drives. */
    public void reverse() {
        float yaw = getYRot() + 180.0F;
        setYRot(yaw);
        setYHeadRot(yaw);
        push = Direction.fromYRot(yaw).getUnitVec3();
        setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
    }

    /** Start drives the way the builder faces, which is the way it was placed, whatever shoved it before. */
    public void setStarted(boolean started) {
        entityData.set(STARTED, started);
        if (started) {
            push = Direction.fromYRot(getYRot()).getUnitVec3();
            setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
        }
    }

    public int getPlannedHeight() {
        return entityData.get(PLANNED_HEIGHT);
    }

    public void setPlannedHeight(int height) {
        entityData.set(PLANNED_HEIGHT, height);
    }

    /** The template as twenty blocks, bottom row first, null where the builder digs to air. */
    public List<Block> templateBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (String id : entityData.get(TEMPLATE).split(",", -1)) {
            Identifier identifier = Identifier.tryParse(id);
            blocks.add(identifier == null ? null : BuiltInRegistries.BLOCK.getOptional(identifier).orElse(null));
        }
        while (blocks.size() < TEMPLATE_CELLS) {
            blocks.add(null);
        }
        return blocks;
    }

    public @Nullable Block templateBlock(int cell) {
        return templateBlocks().get(cell);
    }

    /** The space the train needs: the rail's column and one either side, from rail level up three. */
    public static boolean isProfileCell(int cell) {
        int column = cell % TEMPLATE_WIDTH;
        int row = cell / TEMPLATE_WIDTH;
        return row >= 1 && row <= PROFILE_HEIGHT && Math.abs(column - TEMPLATE_WIDTH / 2) < PROFILE_WIDTH / 2 + 1;
    }

    public void setTemplateBlock(int cell, @Nullable Block block) {
        List<Block> blocks = templateBlocks();
        blocks.set(cell, block);
        entityData.set(TEMPLATE, blocks.stream()
                .map(entry -> entry == null ? "" : BuiltInRegistries.BLOCK.getKey(entry).toString())
                .collect(Collectors.joining(",")));
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TracksBuilderMenu(containerId, inventory, this);
    }

    @Override
    protected boolean hasTrackDrag() {
        return !isWorking();
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            return;
        }
        if (getPlannedHeight() == 0) {
            setPlannedHeight(railPosition().getY());
        }
        burnFuel();
        if (!isStarted()) {
            updatePush();
        }
        if (skipTick) {
            skipTick = false;
            stop();
        } else if (!isStarted()) {
            setStatus(Status.STOPPED, "");
            stop();
        } else if (fuel <= 0) {
            setStatus(Status.NO_FUEL, "");
            stop();
        } else if (isInWater()) {
            setStatus(Status.WATER, "");
            stop();
        } else {
            setStatus(Status.WORKING, "");
            if (push.horizontalDistance() <= 0.01) {
                push = facing().getUnitVec3();
            }
            Vec3 motion = getDeltaMovement();
            if (motion.x * push.x + motion.z * push.z < 0.0) {
                setDeltaMovement(0.0, motion.y, 0.0);
            }
            applyPush();
            dig();
            fuel--;
        }
        entityData.set(FUEL, fuel);
        if (tickCount % 4 == 0) {
            refillFromLinked();
        }
    }

    private void stop() {
        setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
    }

    private void burnFuel() {
        ItemStack stack = getItem(FUEL_SLOT);
        int worth = fuelWorth(level(), stack);
        if (worth == 0 || fuel + worth >= MAX_FUEL) {
            return;
        }
        fuel += worth;
        if (isDieselCanister(stack)) {
            store(new ItemStack(ProductionRegistry.item("empty_canister")));
        }
        stack.shrink(1);
    }

    /** Coal and anything else a furnace burns, redstone, and a canister of diesel or refined fuel. */
    public static int fuelWorth(Level level, ItemStack stack) {
        if (stack.is(Items.REDSTONE)) {
            return REDSTONE_FUEL;
        }
        if (isDieselCanister(stack)) {
            return DIESEL_FUEL;
        }
        return level.fuelValues().burnDuration(stack) > 0 ? COAL_FUEL : 0;
    }

    private static boolean isDieselCanister(ItemStack stack) {
        return stack.is(ProductionRegistry.item("diesel")) || stack.is(ProductionRegistry.item("refined_fuel"));
    }

    private void refillFromLinked() {
        FreightEntity link =
                cartLinked1 instanceof FreightEntity first && !(first instanceof TracksBuilderEntity)
                        ? first
                        : cartLinked2 instanceof FreightEntity second && !(second instanceof TracksBuilderEntity)
                                ? second
                                : null;
        if (link == null) {
            return;
        }
        for (int slot = 0; slot < DUG_FROM; slot++) {
            ItemStack stack = getItem(slot);
            if (stack.isEmpty() || stack.getCount() >= stack.getMaxStackSize()) {
                continue;
            }
            for (int other = 0; other < link.getContainerSize(); other++) {
                if (ItemStack.isSameItemSameComponents(link.getItem(other), stack)) {
                    link.removeItem(other, 1);
                    stack.grow(1);
                    break;
                }
            }
        }
    }

    private void updatePush() {
        Vec3 motion = getDeltaMovement();
        if (push.horizontalDistance() <= 0.01 && motion.horizontalDistanceSqr() > 0.001) {
            push = new Vec3(motion.x, 0.0, motion.z);
        } else if (push.horizontalDistance() > 0.01 && motion.horizontalDistanceSqr() > 0.001) {
            push = push.normalize();
            push = push.x * motion.x + push.z * motion.z < 0.0 ? Vec3.ZERO : new Vec3(motion.x, 0.0, motion.z);
        }
    }

    private void applyPush() {
        Vec3 motion = getDeltaMovement();
        if (push.horizontalDistance() > 0.01) {
            push = push.normalize();
            setDeltaMovement(motion.x * 0.4 + push.x * PUSH, 0.0, motion.z * 0.4 + push.z * PUSH);
        } else {
            setDeltaMovement(motion.x * 0.7, 0.0, motion.z * 0.7);
        }
    }

    @Override
    public void push(net.minecraft.world.entity.Entity other) {
        super.push(other);
        if (other instanceof Player && !isWorking()) {
            push = new Vec3(getX() - other.getX(), 0.0, getZ() - other.getZ());
        }
    }

    private Direction facing() {
        Vec3 heading = push.horizontalDistance() > 0.01 ? push : getDeltaMovement();
        if (heading.horizontalDistanceSqr() < 1.0E-6) {
            return Direction.fromYRot(getYRot());
        }
        return Direction.fromYRot(Math.toDegrees(Math.atan2(-heading.x, heading.z)));
    }

    /** The block the builder's rail is in, the reference the track movement uses; the entity itself sits higher. */
    public BlockPos railPosition() {
        return BlockPos.containing(getX(), Math.floor(getY() - yOffset() + 0.1), getZ());
    }

    private int heightToGo() {
        return getPlannedHeight() - railPosition().getY();
    }

    /** One step ahead: the profile is always dug, a marked cell is built, the rest is left alone. */
    private void dig() {
        Direction ahead = facing();
        Direction side = ahead.getClockWise();
        BlockPos here = railPosition();
        BlockPos target = here.relative(ahead);
        List<Block> template = templateBlocks();
        int climb = Integer.signum(heightToGo());
        boolean traincraftTrack = railStack().getItem() instanceof TrackItem;
        if (traincraft.Traincraft.TRACE && tickCount % 5 == 0) {
            traincraft.Traincraft.LOGGER.info(
                    "builder t{} here {} target {} trackAt={} target/above/below {} / {} / {} climb {} status {} rails {}",
                    tickCount, here.toShortString(), target.toShortString(), trackAt(target),
                    level().getBlockState(target).getBlock().getName().getString(),
                    level().getBlockState(target.above()).getBlock().getName().getString(),
                    level().getBlockState(target.below()).getBlock().getName().getString(),
                    climb, statusText(), railStack().getCount());
        }
        if (trackAt(target)) {
            if (!slopeAt(target)) {
                if (lastRailLevel != Integer.MIN_VALUE) {
                    setPlannedHeight(getPlannedHeight() + here.getY() - lastRailLevel);
                }
                lastRailLevel = here.getY();
                if (isRail(level().getBlockState(target)) && !shapeColumn(target, side, template)) {
                    stop();
                }
            }
            return;
        }
        lastRailLevel = here.getY();
        if (railStack().isEmpty()) {
            setStatus(Status.NO_TRACK, "");
            stop();
            return;
        }
        if (traincraftTrack && climb != 0) {
            laySlope(target, ahead, side, climb, template);
            return;
        }
        if (!traincraftTrack) {
            int togo = heightToGo();
            if (togo < 0 && isRail(level().getBlockState(here.below()))) {
                target = target.below();
                togo++;
            } else if (togo > 0 && isRail(level().getBlockState(here))) {
                target = target.above();
                togo--;
            }
            climb = Integer.signum(togo);
            target = target.above(climb);
            if (climb > 0) {
                target = target.relative(ahead);
            }
        }
        boolean cleared = shapeColumn(target, side, template);
        for (int across = -1; across <= 1; across++) {
            for (int up = PROFILE_HEIGHT; up < PROFILE_HEIGHT + Math.abs(climb); up++) {
                cleared &= replaceBlock(target.relative(side, across).above(up), null);
            }
        }
        if (level().getBlockState(target.below()).getBlock() instanceof Fallable
                && FallingBlock.isFree(level().getBlockState(target.below(2)))) {
            skipTick = true;
            stop();
            return;
        }
        if (!cleared || !placeRail(target, ahead)) {
            stop();
        }
    }

    /** The whole template around a rail cell: the floor row beneath it, then every row above. */
    private boolean shapeColumn(BlockPos rail, Direction side, List<Block> template) {
        boolean done = true;
        for (int row = 0; row < TEMPLATE_HEIGHT; row++) {
            done &= applyRow(rail.above(row - 1), side, template, row);
        }
        return done;
    }

    /** One template row at the height of {@code centre}: profile cells dug, marked cells laid, the rail cell left alone. */
    private boolean applyRow(BlockPos centre, Direction side, List<Block> template, int row) {
        boolean done = true;
        for (int column = 0; column < TEMPLATE_WIDTH; column++) {
            int cell = row * TEMPLATE_WIDTH + column;
            boolean profile = isProfileCell(cell);
            if (cell == RAIL_CELL || !profile && template.get(cell) == null) {
                continue;
            }
            done &= replaceBlock(centre.relative(side, column - TEMPLATE_WIDTH / 2), profile ? null : template.get(cell));
        }
        return done;
    }

    /**
     * A change of level with Traincraft track is the shortest slope, six blocks at the lower level
     * rising one, paid for with a track item per block. Every column gets the floor row beneath it, the
     * level itself cleared for the slope and the train, and the template from the upper level up.
     */
    private void laySlope(BlockPos target, Direction ahead, Direction side, int climb, List<Block> template) {
        if (railStack().getCount() < SLOPE_LENGTH) {
            setStatus(Status.NO_TRACK, "");
            stop();
            return;
        }
        for (int laid = 0; laid < SLOPE_LENGTH && isRail(level().getBlockState(target)); laid++) {
            target = target.relative(ahead);
        }
        int lower = climb > 0 ? target.getY() : target.getY() - 1;
        BlockPos foot = (climb > 0 ? target : target.relative(ahead, SLOPE_LENGTH - 1)).atY(lower);
        Direction rise = climb > 0 ? ahead : ahead.getOpposite();
        boolean cleared = true;
        for (int step = 0; step < SLOPE_LENGTH; step++) {
            BlockPos column = target.relative(ahead, step).atY(lower);
            cleared &= applyRow(column.below(), side, template, 0);
            cleared &= replaceBlock(column, null) & applyRow(column, side, template, 1);
            cleared &= replaceBlock(column.above(), null);
            for (int row = 1; row < TEMPLATE_HEIGHT; row++) {
                cleared &= applyRow(column.above(row), side, template, row);
            }
        }
        if (!cleared) {
            stop();
            return;
        }
        int facing = Math.floorMod((int) Math.floor(rise.toYRot() * 4.0F / 360.0F + 0.5F), 4);
        PlacementResult result = TrackPlacer.place(level(), foot, slopeType(template), facing);
        if (result.placed()) {
            railStack().shrink(SLOPE_LENGTH);
            if (traincraft.Traincraft.TRACE) {
                traincraft.Traincraft.LOGGER.info("builder t{} laid slope at {} rising {} rails left {}", tickCount, foot.toShortString(), rise, railStack().getCount());
            }
            return;
        }
        setStatus(result instanceof PlacementResult.NoSupport ? Status.NO_FLOOR : Status.NO_ROOM, "");
        stop();
    }

    private TrackType slopeType(List<Block> template) {
        Block floor = template.get(TEMPLATE_WIDTH / 2);
        if (floor != null && floor.defaultBlockState().is(BlockTags.PLANKS)) {
            return TrackType.SLOPE_WOOD;
        }
        if (floor != null && BuiltInRegistries.BLOCK.getKey(floor).getPath().equals("ballast")) {
            return TrackType.SLOPE_BALLAST;
        }
        return TrackType.SLOPE_GRAVEL;
    }

    /** Whether the track ahead belongs to a slope, through a filler's origin: a level change the target must not follow. */
    private boolean slopeAt(BlockPos pos) {
        for (BlockPos at : List.of(pos, pos.above(), pos.below())) {
            BlockEntity tile = level().getBlockEntity(at);
            if (tile instanceof TrackOccupancyBlockEntity filler) {
                tile = level().getBlockEntity(filler.origin());
            }
            if (tile instanceof TrackBlockEntity rail && rail.geometry().slope() != null) {
                return true;
            }
        }
        return false;
    }

    /** Track in the block ahead at this level or the one above or below: a route already laid, followed as it is. */
    private boolean trackAt(BlockPos pos) {
        return isRail(level().getBlockState(pos)) || isRail(level().getBlockState(pos.above())) || isRail(level().getBlockState(pos.below()));
    }

    private boolean replaceBlock(BlockPos pos, @Nullable Block block) {
        BlockState state = level().getBlockState(pos);
        if (block == null ? state.isAir() : state.is(block) || isRail(state)) {
            return true;
        }
        if (!state.getFluidState().isEmpty()) {
            setStatus(Status.WATER, "");
            return false;
        }
        if (state.getDestroySpeed(level(), pos) < 0.0F) {
            setStatus(Status.BLOCKED, state.getBlock().getName().getString());
            return false;
        }
        int slot = block == null ? -1 : materialSlot(block);
        if (block != null && slot < 0) {
            setStatus(Status.NO_MATERIAL, block.getName().getString());
            return false;
        }
        harvest(pos);
        level().setBlock(pos, block == null ? Blocks.AIR.defaultBlockState() : block.defaultBlockState(), 3);
        if (slot >= 0) {
            getItem(slot).shrink(1);
        }
        return true;
    }

    /** The first track item among the materials, or the empty stack. */
    public ItemStack railStack() {
        for (int slot = MATERIALS_FROM; slot < DUG_FROM; slot++) {
            if (isRailItem(getItem(slot))) {
                return getItem(slot);
            }
        }
        return ItemStack.EMPTY;
    }

    private int materialSlot(Block block) {
        for (int slot = MATERIALS_FROM; slot < DUG_FROM; slot++) {
            if (getItem(slot).getItem() instanceof BlockItem item && item.getBlock() == block) {
                return slot;
            }
        }
        return -1;
    }

    private void harvest(BlockPos pos) {
        BlockState state = level().getBlockState(pos);
        if (state.isAir()) {
            return;
        }
        for (ItemStack drop : Block.getDrops(state, (ServerLevel) level(), pos, level().getBlockEntity(pos))) {
            store(drop);
        }
        level().levelEvent(2001, pos, Block.getId(state));
    }

    private void store(ItemStack drop) {
        for (RollingStockEntity neighbour : new RollingStockEntity[] {cartLinked1, cartLinked2}) {
            if (neighbour instanceof FreightEntity freight
                    && !(freight instanceof TracksBuilderEntity)
                    && placeInCargo(freight, 0, drop)) {
                return;
            }
        }
        if (!placeInCargo(this, DUG_FROM, drop)) {
            spawnAtLocation((ServerLevel) level(), drop, 1.0F);
        }
    }

    private boolean placeRail(BlockPos pos, Direction ahead) {
        ItemStack rail = railStack();
        if (rail.isEmpty()) {
            return false;
        }
        if (isRail(level().getBlockState(pos))) {
            return true;
        }
        if (!Block.canSupportRigidBlock(level(), pos.below())) {
            setStatus(Status.NO_FLOOR, "");
            return false;
        }
        if (!level().getFluidState(pos).isEmpty()) {
            setStatus(Status.WATER, "");
            return false;
        }
        harvest(pos);
        level().removeBlock(pos, false);
        boolean placed;
        if (rail.getItem() instanceof TrackItem track) {
            int facing = Math.floorMod((int) Math.floor(ahead.toYRot() * 4.0F / 360.0F + 0.5F), 4);
            placed = TrackPlacer.place(level(), pos, track.trackType(), facing).placed();
        } else {
            placed = level().setBlock(pos, ((BlockItem) rail.getItem()).getBlock().defaultBlockState(), 3);
        }
        if (placed) {
            rail.shrink(1);
            if (traincraft.Traincraft.TRACE) {
                traincraft.Traincraft.LOGGER.info("builder t{} laid {} at {} rails left {}", tickCount, rail.getItem(), pos.toShortString(), rail.getCount());
            }
        }
        return placed;
    }

    private static boolean isRail(BlockState state) {
        return BaseRailBlock.isRail(state)
                || state.getBlock() instanceof TrackBlock
                || state.getBlock() instanceof TrackOccupancyBlock;
    }

    /** Vanilla rails and the one-block Traincraft straights; longer pieces cannot be laid a block at a time. */
    public static boolean isRailItem(ItemStack stack) {
        return stack.getItem() instanceof TrackItem track
                        && (track.trackType() == TrackType.SMALL_STRAIGHT || track.trackType() == TrackType.EMBEDDED_SMALL_STRAIGHT)
                || stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BaseRailBlock;
    }

    public static boolean canBeTunnel(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return false;
        }
        Block block = blockItem.getBlock();
        return !(block instanceof EntityBlock)
                && block.defaultBlockState().getRenderShape() == RenderShape.MODEL;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        fuel = input.getIntOr("fuel", 0);
        setPlannedHeight(input.getIntOr("plannedHeight", 0));
        setStarted(input.getBooleanOr("started", false));
        entityData.set(TEMPLATE, input.getStringOr("template", ",".repeat(TEMPLATE_CELLS - 1)));
        entityData.set(FUEL, fuel);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("fuel", fuel);
        output.putInt("plannedHeight", getPlannedHeight());
        output.putBoolean("started", isStarted());
        output.putString("template", entityData.get(TEMPLATE));
    }
}
