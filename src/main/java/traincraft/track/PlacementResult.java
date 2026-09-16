package traincraft.track;

import net.minecraft.core.BlockPos;

public sealed interface PlacementResult {
    record Placed() implements PlacementResult {}

    record Blocked(BlockPos position) implements PlacementResult {}

    /** Nothing with a solid top under one of the piece's blocks. Track does not hang in the air. */
    record NoSupport(BlockPos position) implements PlacementResult {}

    record Unsupported(String detail) implements PlacementResult {}

    record Failed(BlockPos position, String detail) implements PlacementResult {}

    default boolean placed() {
        return this instanceof Placed;
    }

    default String reason() {
        return switch (this) {
            case Placed ignored -> "";
            case Blocked blocked -> "The track needs more space";
            case NoSupport noSupport -> "The track needs solid ground under all of it";
            case Unsupported unsupported -> unsupported.detail();
            case Failed failed -> "The track could not be placed";
        };
    }
}
