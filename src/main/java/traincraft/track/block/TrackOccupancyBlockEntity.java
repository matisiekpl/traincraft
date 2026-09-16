package traincraft.track.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import org.jspecify.annotations.Nullable;

import traincraft.bootstrap.BlockEntityRegistry;

public class TrackOccupancyBlockEntity extends BlockEntity {

    /** Position of the {@link TrackBlockEntity} this gag belongs to. */
    private int originX;

    private int originY;
    private int originZ;

    /**
     * Collision height, in blocks. Only the slope family varies it: a ramp's gags step upwards, so
     * each one needs its own height for pedestrians to walk up the slope.
     */
    private double bbHeight = 0.125;

    private @Nullable String type;

    public TrackOccupancyBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.TC_RAIL_GAG.get(), pos, state);
    }

    public void setOrigin(int x, int y, int z) {
        this.originX = x;
        this.originY = y;
        this.originZ = z;
    }

    public double collisionHeight() {
        return bbHeight;
    }

    public void setCollisionHeight(double height) {
        if (!Double.isFinite(height) || height < 0)
            throw new IllegalArgumentException("Invalid collision height");
        bbHeight = height;
        setChanged();
    }

    public BlockPos origin() {
        return new BlockPos(originX, originY, originZ);
    }

    public @Nullable String getTypeLabel() {
        return type;
    }

    public void setTypeLabel(@Nullable String type) {
        this.type = type;
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
    }

    @Override
    public void onDataPacket(Connection net, ValueInput input) {
        loadWithComponents(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("originX", originX);
        output.putInt("originY", originY);
        output.putInt("originZ", originZ);
        output.putDouble("bbHeight", bbHeight);
        if (type != null) {
            output.putString("type", type);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        originX = input.getIntOr("originX", 0);
        originY = input.getIntOr("originY", 0);
        originZ = input.getIntOr("originZ", 0);
        bbHeight = input.getDoubleOr("bbHeight", 0.125);
        type = input.getString("type").orElse(null);
    }
}
