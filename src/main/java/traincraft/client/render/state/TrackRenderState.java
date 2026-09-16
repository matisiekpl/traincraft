package traincraft.client.render.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import org.jspecify.annotations.Nullable;

import traincraft.track.TrackType;

/**
 * Render state for a track piece.
 *
 * <p>{@code BlockEntityRenderState} keeps {@code blockState} private and {@code submit} has no
 * level access, so anything the renderer needs has to be copied across in {@code
 * extractRenderState}. Packed light already arrives in the base class's {@code lightCoords}.
 */
public class TrackRenderState extends BlockEntityRenderState {
    public @Nullable TrackType trackType;
    public int facing;
    public boolean switchActive;
}
