package traincraft.client.render.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;

/**
 * Render state for a locomotive.
 *
 * <p>{@code submit} has no access to the entity, so anything the transform chain needs has to be
 * copied across in {@code extractRenderState}. Packed light already arrives in {@code lightCoords}.
 */
public class RollingStockRenderState extends EntityRenderState {
    public String colour = "Red";
    public String engineNumber = "";
    public float yaw;
    public float pitch;

    public float wheelAngle;

    /** Filled cargo slots, for the models whose load shows. */
    public int cargo;

    /** The tunnel builder's cutting head: whether it turns, and its angle in radians. */
    public boolean working;

    public float spin;

    /** How hard, how recently and which way round the stock was last hit. */
    public int hurtTime;

    public int hurtDir;
    public float damage;

    /** The fraction of a tick this frame falls at; the wobble needs it and the base state has no field for it. */
    public float partialTicks;
}
