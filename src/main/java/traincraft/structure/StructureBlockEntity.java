package traincraft.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class StructureBlockEntity extends BlockEntity {

    private int state;
    private int colour = 0xFFFFFF;

    public StructureBlockEntity(BlockPos pos, BlockState blockState) {
        this(StructureRegistry.STRUCTURE.get(), pos, blockState);
    }

    protected StructureBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public int state() {
        return state;
    }

    public void setState(int value) {
        state = value;
        sync();
    }

    public int colour() {
        return colour;
    }

    public void setColour(int value) {
        colour = value;
        sync();
    }

    protected void sync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        state = input.getIntOr("state", 0);
        colour = input.getIntOr("colour", 0xFFFFFF);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("state", state);
        output.putInt("colour", colour);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
