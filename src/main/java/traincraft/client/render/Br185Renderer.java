package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.state.RollingStockRenderState;
import traincraft.vehicle.entity.Br185LocomotiveEntity;

/** RenderEnum.locoBR185 and the two bogie draws in BR185_EngineModel.render. */
public final class Br185Renderer extends EntityRenderer<Br185LocomotiveEntity, RollingStockRenderState> {
    public Br185Renderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0;
    }
    @Override protected net.minecraft.world.phys.AABB getBoundingBoxForCulling(Br185LocomotiveEntity entity) { return RollingStockRenderer.cullingBox(entity); }
    @Override public RollingStockRenderState createRenderState() { return new RollingStockRenderState(); }
    @Override public void extractRenderState(Br185LocomotiveEntity entity, RollingStockRenderState state, float partial) {
        super.extractRenderState(entity, state, partial);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partial;
        state.colour = entity.getColour();
    }
    @Override public void submit(RollingStockRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        stack.pushPose();
        stack.translate(0, -0.3, 0);
        stack.mulPose(Axis.YP.rotationDegrees(90 - state.yaw));
        stack.mulPose(Axis.ZP.rotationDegrees(-state.pitch));
        RollingStockRenderer.applyHurtWobble(stack, state);
        stack.translate(-2, 0.1, 0);
        stack.mulPose(Axis.YP.rotationDegrees(180));
        stack.mulPose(Axis.ZP.rotationDegrees(180));
        draw("br185", "br185_engine_" + state.colour.toLowerCase(java.util.Locale.ROOT), state.lightCoords, stack, collector);
        for (double x : new double[] {-1.5, 0.7}) {
            stack.pushPose();
            stack.translate(x, 0.27, 0);
            stack.scale(0.9F, 0.9F, 0.8F);
            draw("br185_bogie", "br185_bogie", state.lightCoords, stack, collector);
            stack.popPose();
        }
        stack.popPose();
    }
    private static void draw(String modelName, String texture, int light, PoseStack stack, SubmitNodeCollector collector) {
        var model = VehicleModels.INSTANCE.get(modelName);
        collector.submitCustomGeometry(stack,
                RenderTypes.entityCutoutCull(Identifier.fromNamespaceAndPath("tc", "textures/ported/" + texture + ".png")),
                (pose, buffer) -> model.render(pose, buffer, light, 0, 0));
    }
}
