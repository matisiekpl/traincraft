package traincraft.track.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

import org.jspecify.annotations.Nullable;

import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.track.Placement;
import traincraft.track.TrackGeometry;
import traincraft.track.TrackOrientation;
import traincraft.track.TrackPlacementPlanner;
import traincraft.track.TrackPlan;
import traincraft.track.TrackType;

public class TrackBlockEntity extends BlockEntity {

    // --- slope geometry ---

    private int facingMeta;
    private TrackGeometry geometry = TrackGeometry.STRAIGHT;

    private @Nullable String type = TrackType.SMALL_STRAIGHT.label();

    private @Nullable TrackType trackType;

    private boolean isLinkedToRail = false;
    private int linkedX;
    private int linkedY;
    private int linkedZ;

    private boolean hasModel = true;

    private boolean switchActive = false;
    private boolean previousRedstoneState;
    private boolean canTypeBeModifiedBySwitch = false;
    private boolean manualOverride = false;
    private boolean hasRotated = false;

    private @org.jspecify.annotations.Nullable TrackType dropType;

    /** The facing the piece was placed with, so its footprint can be recomputed to remove it. */
    private int placedFacing;

    /**
     * The block that owns the piece this rail belongs to.
     *
     * <p>A switch lays four separate rails; breaking any one of them has to take the whole switch
     * with it, and only the owner knows what the whole switch is. Points at itself when this rail
     * is the owner.
     */
    private int originX;

    private int originY = Integer.MIN_VALUE;
    private int originZ;

    public void setAssemblyOrigin(net.minecraft.core.BlockPos pos) {
        originX = pos.getX();
        originY = pos.getY();
        originZ = pos.getZ();
        invalidateRenderBounds();
        setChanged();
    }

    /** The owner of this rail's piece, or this rail itself when it was placed on its own. */
    public net.minecraft.core.BlockPos assemblyOrigin() {
        return originY == Integer.MIN_VALUE
                ? getBlockPos()
                : new net.minecraft.core.BlockPos(originX, originY, originZ);
    }

    /**
     * The volume this rail draws into, which is the whole piece rather than this one block.
     *
     * <p>A track piece is one block entity drawing geometry that may reach a dozen blocks down the
     * line and, on a large curve, well to the side of it. The default render bounding box is the
     * single block the entity sits in, so as soon as that block leaves the view frustum the entire
     * piece stops being drawn -- track that vanishes and comes back as the player turns their head,
     * which is exactly how it looks in play.
     *
     * <p>The footprint is the placement plan's, which the owning rail already remembers so that the
     * piece can be taken apart again; a rail that is not the owner asks the owner, the same way
     * {@link traincraft.track.TrackBreaker} does. Inflated by two blocks afterwards because the
     * plan describes the blocks a piece occupies and not the mesh drawn over them -- a curve's
     * ballast sweeps outside its own block footprint.
     *
     * <p>Computed once. The renderer asks for this on every frame for every rail in view, and
     * planning a piece is not free.
     *
     * <p>It lives here rather than on the renderer because the plan is what defines it and the plan
     * is server-side data the tile already holds; 26.2 moved the hook itself onto {@code
     * BlockEntityRenderer}, so {@link traincraft.client.render.TrackRenderer} calls this.
     */
    public AABB pieceRenderBounds() {
        AABB cached = renderBounds;
        if (cached != null) {
            return cached;
        }
        AABB bounds = computeRenderBounds();
        renderBounds = bounds;
        return bounds;
    }

    private @Nullable AABB renderBounds;

    /** Dropped whenever anything that could move the geometry arrives from the server. */
    private void invalidateRenderBounds() {
        renderBounds = null;
    }

    private AABB computeRenderBounds() {
        BlockPos origin = assemblyOrigin();
        TrackBlockEntity owner =
                level != null && level.getBlockEntity(origin) instanceof TrackBlockEntity found
                        ? found
                        : this;
        TrackType type = owner.dropType;
        int facing = owner.placedFacing;
        if (type == null || !TrackPlacementPlanner.canPlan(type, facing)) {
            // No remembered plan -- a piece from an older world, or one still being placed. Two
            // blocks in every direction is not the right answer but it is a much better wrong one
            // than a single block, and it costs nothing.
            return new AABB(getBlockPos()).inflate(FALLBACK_RENDER_REACH);
        }
        TrackPlan plan = TrackPlacementPlanner.plan(type, facing);
        Placement master = plan.master();
        origin = origin.offset(-master.dx(), -master.dy(), -master.dz());
        int minX = 0;
        int minY = 0;
        int minZ = 0;
        int maxX = 0;
        int maxY = 0;
        int maxZ = 0;
        for (Placement placement : plan.placements()) {
            minX = Math.min(minX, placement.dx());
            minY = Math.min(minY, placement.dy());
            minZ = Math.min(minZ, placement.dz());
            maxX = Math.max(maxX, placement.dx());
            maxY = Math.max(maxY, placement.dy());
            maxZ = Math.max(maxZ, placement.dz());
        }
        return new AABB(
                        origin.getX() + minX,
                        origin.getY() + minY,
                        origin.getZ() + minZ,
                        origin.getX() + maxX + 1,
                        origin.getY() + maxY + 1,
                        origin.getZ() + maxZ + 1)
                .inflate(MESH_MARGIN);
    }

    /** How far a mesh may reach outside the blocks its piece occupies. */
    private static final double MESH_MARGIN = 2.0;

    /** Used when the plan is not known; see {@link #computeRenderBounds()}. */
    private static final double FALLBACK_RENDER_REACH = 2.0;

    public TrackBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.TC_RAIL.get(), pos, state);
    }

    // ------------------------------------------------------------------ accessors

    public TrackGeometry geometry() {
        return geometry;
    }

    public boolean hasModel() {
        return hasModel;
    }

    public boolean switchControlled() {
        return canTypeBeModifiedBySwitch;
    }

    public @Nullable TrackType dropType() {
        return dropType;
    }

    public int placedFacing() {
        return placedFacing;
    }

    public void setPlacementOwner(TrackType requested, int facing) {
        dropType = java.util.Objects.requireNonNull(requested);
        placedFacing = TrackOrientation.fromIndex(facing).index();
        invalidateRenderBounds();
        setChanged();
    }

    /** Installs a complete placement before exposing it to renderers or clients. */
    public void initialize(BlockPos origin, Placement placement) {
        geometry =
                placement
                        .params()
                        .geometry()
                        .translated(origin.getX(), origin.getY(), origin.getZ());
        facingMeta = placement.routeOrientation().index();
        hasModel = placement.hasModel();
        canTypeBeModifiedBySwitch = placement.params().switchControlled();
        Placement.Link link = placement.link();
        isLinkedToRail = link != null;
        linkedX = link == null ? 0 : origin.getX() + link.dx();
        linkedY = link == null ? 0 : origin.getY() + link.dy();
        linkedZ = link == null ? 0 : origin.getZ() + link.dz();
        trackType = placement.type();
        type = trackType.label();
        invalidateRenderBounds();
        setChanged();
    }

    public int getFacing() {
        return facingMeta;
    }

    public void setFacing(int facing) {
        this.facingMeta = TrackOrientation.fromIndex(facing).index();
    }

    public @Nullable String getTypeLabel() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
        this.trackType = TrackType.byLabel(type);
        syncToClients();
    }

    public void setTrackType(@Nullable TrackType trackType) {
        this.trackType = trackType;
        this.type = trackType == null ? null : trackType.label();
        syncToClients();
    }

    public @Nullable TrackType getTrackType() {
        if (trackType == null && hasModel && type != null) {
            trackType = TrackType.byLabel(type);
        }
        return trackType;
    }

    public boolean isSwitchActive() {
        return switchActive;
    }

    private static final int SWITCH_POLL_TICKS = 11;

    private int switchPollTicks;

    public static void serverTick(
            net.minecraft.world.level.Level level,
            net.minecraft.core.BlockPos pos,
            net.minecraft.world.level.block.state.BlockState state,
            TrackBlockEntity rail) {
        if (!rail.canTypeBeModifiedBySwitch) {
            return;
        }
        if (++rail.switchPollTicks % SWITCH_POLL_TICKS != 0) {
            return;
        }
        rail.switchPollTicks = 0;

        net.minecraft.core.BlockPos ahead =
                switch (rail.getFacing() & 3) {
                    case 0 -> pos.offset(0, 0, -1);
                    case 1 -> pos.offset(1, 0, 0);
                    case 2 -> pos.offset(0, 0, 1);
                    default -> pos.offset(-1, 0, 0);
                };
        if (!(level.getBlockEntity(ahead) instanceof TrackBlockEntity target)
                || !target.canTypeBeModifiedBySwitch) {
            return;
        }

        boolean powered = level.hasNeighborSignal(pos);
        if (powered != rail.previousRedstoneState && !level.hasNeighborSignal(ahead)) {
            target.setSwitchActive(!target.isSwitchActive());
            rail.previousRedstoneState = powered;
        }
    }

    /**
     * Sets the switch, and tells everyone watching.
     *
     * <p>The two are one operation. The renderer chooses between the switch's open and closed
     * models from this flag, and a flag changed on the server alone leaves every client drawing the
     * old one -- a switch that works and looks as though it does not.
     */
    public void setSwitchActive(boolean active) {
        if (this.switchActive == active) {
            return;
        }
        this.switchActive = active;
        // Debug rather than info: a train running a busy junction throws switches constantly, and
        // this is only worth reading when chasing one that is not moving.
        traincraft.Traincraft.LOGGER.debug(
                "switch at {} thrown {}", getBlockPos().toShortString(), active ? "over" : "back");
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(
                    getBlockPos(),
                    getBlockState(),
                    getBlockState(),
                    net.minecraft.world.level.block.Block.UPDATE_ALL);
        }
        rewriteRouteAhead(active);
    }

    private void rewriteRouteAhead(boolean active) {
        if (level == null || level.isClientSide()) {
            return;
        }
        TrackType type = getTrackType();
        TrackType turn = SWITCH_BRANCH.get(type);
        if (turn == null) {
            return;
        }
        net.minecraft.core.BlockPos step =
                switch (getFacing() & 3) {
                    case 0 -> new net.minecraft.core.BlockPos(0, 0, 1);
                    case 1 -> new net.minecraft.core.BlockPos(-1, 0, 0);
                    case 2 -> new net.minecraft.core.BlockPos(0, 0, -1);
                    default -> new net.minecraft.core.BlockPos(1, 0, 0);
                };
        int rails = WIDE_SWITCHES.contains(type) ? 2 : 1;
        for (int i = 1; i <= rails; i++) {
            net.minecraft.core.BlockPos at =
                    getBlockPos().offset(step.getX() * i, 0, step.getZ() * i);
            if (level.getBlockEntity(at) instanceof TrackBlockEntity rail) {
                rail.setTrackType(active ? turn : TrackType.SMALL_STRAIGHT);
                rail.setChanged();
                level.sendBlockUpdated(
                        at,
                        rail.getBlockState(),
                        rail.getBlockState(),
                        net.minecraft.world.level.block.Block.UPDATE_ALL);
            }
        }
    }

    private static final java.util.Map<TrackType, TrackType> SWITCH_BRANCH =
            java.util.Map.of(
                    TrackType.MEDIUM_LEFT_SWITCH, TrackType.MEDIUM_LEFT_TURN,
                    TrackType.MEDIUM_RIGHT_SWITCH, TrackType.MEDIUM_RIGHT_TURN,
                    TrackType.MEDIUM_LEFT_PARALLEL_SWITCH, TrackType.MEDIUM_LEFT_TURN,
                    TrackType.MEDIUM_RIGHT_PARALLEL_SWITCH, TrackType.MEDIUM_RIGHT_TURN,
                    TrackType.LARGE_LEFT_SWITCH, TrackType.LARGE_LEFT_TURN,
                    TrackType.LARGE_RIGHT_SWITCH, TrackType.LARGE_RIGHT_TURN);

    /** The ones that rewrite two rails rather than one. */
    private static final java.util.Set<TrackType> WIDE_SWITCHES =
            java.util.Set.of(
                    TrackType.MEDIUM_LEFT_PARALLEL_SWITCH, TrackType.MEDIUM_RIGHT_PARALLEL_SWITCH,
                    TrackType.LARGE_LEFT_SWITCH, TrackType.LARGE_RIGHT_SWITCH);

    public boolean isManualOverride() {
        return manualOverride;
    }

    public void setManualOverride(boolean manualOverride) {
        this.manualOverride = manualOverride;
    }

    // ------------------------------------------------------------------ sync

    public void syncToClients() {
        invalidateRenderBounds();
        if (level == null) {
            return;
        }
        setChanged();
        BlockState old = getBlockState();
        BlockState updated = TrackBlock.withDerivedState(old, getTrackType(), facingMeta);
        if (updated != old) {
            level.setBlock(worldPosition, updated, Block.UPDATE_ALL);
        } else {
            level.sendBlockUpdated(worldPosition, old, old, Block.UPDATE_ALL);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        loadWithComponents(input);
        // The piece may have been re-planned or re-owned; the cached extent no longer stands.
        invalidateRenderBounds();
    }

    @Override
    public void onDataPacket(Connection net, ValueInput input) {
        loadWithComponents(input);
        invalidateRenderBounds();
    }

    // ------------------------------------------------------------------ persistence

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (dropType != null) {
            output.putString("idDrop", dropType.name());
        }
        output.putInt("placedFacing", placedFacing);
        if (originY != Integer.MIN_VALUE) {
            output.putInt("originX", originX);
            output.putInt("originY", originY);
            output.putInt("originZ", originZ);
        }
        TrackGeometry.Curve curve = geometry.curve();
        TrackGeometry.Slope slope = geometry.slope();
        double r = curve == null ? 0 : curve.radius();
        double cx = curve == null ? 0 : curve.centerX();
        double cy = curve == null ? 0 : curve.centerY();
        double cz = curve == null ? 0 : curve.centerZ();
        double slopeHeight = slope == null ? 0 : slope.height();
        double slopeLength = slope == null ? 0 : slope.length();
        double slopeAngle = slope == null ? 0 : slope.angle();
        output.putDouble("r", r);
        output.putDouble("cx", cx);
        output.putDouble("cy", cy);
        output.putDouble("cz", cz);
        output.putDouble("slopeHeight", slopeHeight);
        output.putDouble("slopeLength", slopeLength);
        output.putDouble("slopeAngle", slopeAngle);
        output.putByte("Orientation", (byte) facingMeta);
        if (type != null) {
            output.putString("type", type);
        }
        output.putBoolean("isLinkedToRail", isLinkedToRail);
        output.putInt("linkedX", linkedX);
        output.putInt("linkedY", linkedY);
        output.putInt("linkedZ", linkedZ);
        output.putBoolean("hasModel", hasModel);
        output.putBoolean("switchActive", switchActive);
        output.putBoolean("canTypeBeModifiedBySwitch", canTypeBeModifiedBySwitch);
        output.putBoolean("previousRedstoneState", previousRedstoneState);
        output.putBoolean("manualOverride", manualOverride);
        output.putBoolean("hasRotated", hasRotated);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        dropType =
                input.getString("idDrop")
                        .map(
                                name -> {
                                    try {
                                        return TrackType.valueOf(name);
                                    } catch (IllegalArgumentException e) {
                                        return null;
                                    }
                                })
                        .orElse(null);
        placedFacing = input.getIntOr("placedFacing", 0);
        originY = input.getIntOr("originY", Integer.MIN_VALUE);
        originX = input.getIntOr("originX", 0);
        originZ = input.getIntOr("originZ", 0);
        double r = input.getDoubleOr("r", 0.0);
        double cx = input.getDoubleOr("cx", 0.0);
        double cy = input.getDoubleOr("cy", 0.0);
        double cz = input.getDoubleOr("cz", 0.0);
        double slopeHeight = input.getDoubleOr("slopeHeight", 0.0);
        double slopeLength = input.getDoubleOr("slopeLength", 0.0);
        double slopeAngle = input.getDoubleOr("slopeAngle", 0.0);
        geometry =
                new TrackGeometry(
                        r != 0 || cx != 0 || cz != 0
                                ? new TrackGeometry.Curve(cx, cy, cz, r)
                                : null,
                        slopeLength > 0
                                ? new TrackGeometry.Slope(slopeHeight, slopeLength, slopeAngle)
                                : null);
        invalidateRenderBounds();
        facingMeta = input.getByteOr("Orientation", (byte) 0);
        type = input.getString("type").orElse(type);
        trackType = TrackType.byLabel(type);
        isLinkedToRail = input.getBooleanOr("isLinkedToRail", false);
        linkedX = input.getIntOr("linkedX", 0);
        linkedY = input.getIntOr("linkedY", 0);
        linkedZ = input.getIntOr("linkedZ", 0);
        hasModel = input.getBooleanOr("hasModel", true);
        switchActive = input.getBooleanOr("switchActive", false);
        canTypeBeModifiedBySwitch = input.getBooleanOr("canTypeBeModifiedBySwitch", false);
        previousRedstoneState = input.getBooleanOr("previousRedstoneState", false);
        manualOverride = input.getBooleanOr("manualOverride", false);
        hasRotated = input.getBooleanOr("hasRotated", false);
    }
}
