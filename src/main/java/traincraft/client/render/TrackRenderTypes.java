package traincraft.client.render;

import com.mojang.blaze3d.pipeline.RenderPipeline;

import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;

import traincraft.Traincraft;

import java.util.function.Function;

@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class TrackRenderTypes {

    /**
     * {@code RenderPipelines.ENTITY_CUTOUT} with directional shading removed.
     *
     * <p>{@code ENTITY_SNIPPET} is private in vanilla and made public by NeoForge's access
     * transformer, which is what makes deriving from it legitimate here.
     */
    public static final RenderPipeline TRACK_UNLIT =
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withLocation(
                            Identifier.fromNamespaceAndPath(
                                    Traincraft.MODID, "pipeline/track_unlit"))
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withShaderDefine("NO_CARDINAL_LIGHTING")
                    .withBindGroupLayout(BindGroupLayouts.SAMPLER1)
                    .withCull(false)
                    .build();

    /** Memoised per texture, exactly as the vanilla entity render types are. */
    private static final Function<Identifier, RenderType> TRACK =
            Util.memoize(
                    texture -> {
                        RenderSetup setup =
                                RenderSetup.builder(TRACK_UNLIT)
                                        .withTexture("Sampler0", texture)
                                        .useLightmap()
                                        .useOverlay()
                                        .affectsCrumbling()
                                        .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                                        .createRenderSetup();
                        return RenderType.create("tc_track_unlit", setup);
                    });

    private TrackRenderTypes() {}

    public static RenderType track(Identifier texture) {
        return TRACK.apply(texture);
    }


    @SubscribeEvent
    static void registerPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(TRACK_UNLIT);
    }
}
