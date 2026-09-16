package traincraft.client.render.structure;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class StructureRenderState extends BlockEntityRenderState {
    public String name = "";
    public String facing = "south";
    public String key = "default";
    public int colour = -1;
    public float spin;
    public float arm;
    public boolean spinning;
}
