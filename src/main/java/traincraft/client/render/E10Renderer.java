package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;

import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.state.RollingStockRenderState;
import traincraft.vehicle.entity.E10LocomotiveEntity;

/** RenderEnum.locoE10DB plus the two bogie draws in ModelE10DB.render. */
public final class E10Renderer extends EntityRenderer<E10LocomotiveEntity, RollingStockRenderState> {

    public E10Renderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0;
    }

    @Override
    protected AABB getBoundingBoxForCulling(E10LocomotiveEntity entity) {
        return RollingStockRenderer.cullingBox(entity);
    }

    @Override
    public RollingStockRenderState createRenderState() {
        return new RollingStockRenderState();
    }

    @Override
    public void extractRenderState(E10LocomotiveEntity entity, RollingStockRenderState state, float partial) {
        super.extractRenderState(entity, state, partial);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partial;
        state.colour = entity.getColour();
    }

    @Override
    public void submit(RollingStockRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        stack.pushPose();
        stack.translate(0, -0.3, 0);
        stack.mulPose(Axis.YP.rotationDegrees(90 - state.yaw));
        stack.mulPose(Axis.ZP.rotationDegrees(-state.pitch));
        RollingStockRenderer.applyHurtWobble(stack, state);
        stack.translate(-1.7, 0.05, 0);
        stack.mulPose(Axis.ZP.rotationDegrees(180));
        stack.scale(0.9F, 0.9F, 0.9F);
        draw("e10", "locoe10_db_" + state.colour.toLowerCase(java.util.Locale.ROOT), state.lightCoords, stack, collector);

        stack.pushPose();
        stack.translate(-1.6, 0.15, -0.375);
        draw("e10_bogie", "e10_bogie", state.lightCoords, stack, collector);
        stack.translate(3.3, 0, 0);
        draw("e10_bogie", "e10_bogie", state.lightCoords, stack, collector);
        stack.popPose();
        stack.popPose();
    }

    private static void draw(String modelName, String texture, int light, PoseStack stack, SubmitNodeCollector collector) {
        var model = VehicleModels.INSTANCE.get(modelName);
        collector.submitCustomGeometry(stack,
                RenderTypes.entityCutoutCull(Identifier.fromNamespaceAndPath("tc", "textures/ported/" + texture + ".png")),
                (pose, buffer) -> model.render(pose, buffer, light, 0, 0));
    }
}
