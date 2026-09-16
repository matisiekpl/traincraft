package traincraft.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import traincraft.TraincraftConfig;

public class GeneratorBlockEntity extends StructureBlockEntity {

    public enum Kind {
        WATER_WHEEL(80, 80),
        WIND_MILL(240, 80),
        DIESEL(320, 80);

        final int capacity;
        final int transfer;

        Kind(int capacity, int transfer) {
            this.capacity = capacity;
            this.transfer = transfer;
        }
    }

    private final Kind kind;
    private final SimpleEnergyHandler handler;
    private int ticks;
    private boolean sheltered;
    private int spinDirection = -1;

    public GeneratorBlockEntity(Kind kind, BlockPos pos, BlockState blockState) {
        super(StructureRegistry.GENERATOR.get(), pos, blockState);
        this.kind = kind;
        this.handler = new SimpleEnergyHandler(kind.capacity, 0, kind.transfer);
    }

    public Kind kind() {
        return kind;
    }

    public int stored() {
        return handler.getAmountAsInt();
    }

    public boolean spinning() {
        return state() > 0;
    }

    protected void receive(int amount) {
        handler.set(Math.min(kind.capacity, stored() + amount));
        setChanged();
    }

    public void tick() {
        ticks++;
        produce();
        push();
    }

    protected void produce() {
        if (kind == Kind.WATER_WHEEL) {
            boolean turning = false;
            for (Direction side : Direction.values()) {
                var fluid = level.getFluidState(worldPosition.relative(side));
                if (!fluid.isEmpty() && fluid.getType() != Fluids.LAVA && fluid.getType() != Fluids.FLOWING_LAVA && !fluid.isSource()) {
                    turning = true;
                    break;
                }
            }
            if (turning) {
                receive(5);
            }
            if (turning != spinning()) {
                setState(turning ? 1 : 0);
            }
        } else if (kind == Kind.WIND_MILL) {
            if (ticks % 120 == 0) {
                sheltered = false;
                int radius = TraincraftConfig.WINDMILL_CHECK_RADIUS.get();
                for (int x = -radius; x <= radius && !sheltered; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (!level.canSeeSky(worldPosition.offset(x, 1, z))) {
                            sheltered = true;
                            break;
                        }
                    }
                }
                if (spinning() == sheltered) {
                    setState(sheltered ? 0 : 1);
                }
            }
            if (!sheltered && ticks % 4 == 0) {
                receive(Math.max(0, 10 + Math.round(worldPosition.getY() * 0.25F) * 10));
                if (level.isThundering()) {
                    receive(Math.round(stored() * 3.5F));
                } else if (level.isRaining()) {
                    receive(Math.round(stored() * 2.2F));
                }
            }
        }
    }

    private void push() {
        if (stored() == 0) {
            return;
        }
        for (Direction side : Direction.values()) {
            EnergyHandler target = level.getCapability(Capabilities.Energy.BLOCK, worldPosition.relative(side), side.getOpposite());
            if (target == null) {
                continue;
            }
            try (Transaction transaction = Transaction.openRoot()) {
                int accepted = target.insert(Math.min(stored(), kind.transfer), transaction);
                if (accepted > 0) {
                    handler.set(stored() - accepted);
                    transaction.commit();
                    setChanged();
                }
            }
        }
    }

    public EnergyHandler handler() {
        return handler;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.child("energy").ifPresent(handler::deserialize);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        handler.serialize(output.child("energy"));
    }
}
