package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

import traincraft.Traincraft;
import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.state.RollingStockRenderState;
import traincraft.vehicle.entity.AliceLocomotiveEntity;

public class AliceRenderer extends EntityRenderer<AliceLocomotiveEntity, RollingStockRenderState> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(
                    Traincraft.MODID, "textures/trains/0-4-0-loco-alice.png");

    private static final float[] MODEL_TRANSLATION = {0.0F, 0.15F, 0.0F};
    private static final float[] MODEL_ROTATION = {0.0F, 180.0F, 180.0F};
    private static final float[] MODEL_SCALE = {0.9F, 1.0F, 0.9F};

    public AliceRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    protected net.minecraft.world.phys.AABB getBoundingBoxForCulling(AliceLocomotiveEntity entity) {
        return RollingStockRenderer.cullingBox(entity);
    }

    @Override
    public RollingStockRenderState createRenderState() {
        return new RollingStockRenderState();
    }

    @Override
    public void extractRenderState(
            AliceLocomotiveEntity entity, RollingStockRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();
        state.wheelAngle = entity.wheelAngle(partialTicks);
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partialTicks;
    }

    /** The render row's explosion entry, which is the cylinder steam: one puff each side. */
    private static final double[][] CYLINDER_EMITTERS = {{1.8, -0.4, 0.8}};

    private static final int CYLINDER_ITERATIONS = 2;

    private static final float RAIL_DROP = -0.30F;

    @Override
    public void submit(
            RollingStockRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.0F, RAIL_DROP, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - state.yaw));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-state.pitch));
        RollingStockRenderer.applyHurtWobble(poseStack, state);

        poseStack.translate(MODEL_TRANSLATION[0], MODEL_TRANSLATION[1], MODEL_TRANSLATION[2]);
        poseStack.mulPose(Axis.XP.rotationDegrees(MODEL_ROTATION[0]));
        poseStack.mulPose(Axis.YP.rotationDegrees(MODEL_ROTATION[1]));
        poseStack.mulPose(Axis.ZP.rotationDegrees(MODEL_ROTATION[2]));
        poseStack.scale(MODEL_SCALE[0], MODEL_SCALE[1], MODEL_SCALE[2]);

        var model = VehicleModels.INSTANCE.get("alice");
        int light = state.lightCoords;
        float wheelAngle = state.wheelAngle;
        collector.submitCustomGeometry(
                poseStack,
                RenderTypes.entityCutoutCull(TEXTURE),
                (pose, buffer) -> model.render(pose, buffer, light, wheelAngle, 0));

        poseStack.popPose();
    }
}
