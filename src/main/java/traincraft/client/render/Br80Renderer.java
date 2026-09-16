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
import traincraft.vehicle.entity.Br80LocomotiveEntity;

public class Br80Renderer extends EntityRenderer<Br80LocomotiveEntity, RollingStockRenderState> {

    private static final Identifier WINTER_TEXTURE =
            Identifier.fromNamespaceAndPath(
                    Traincraft.MODID, "textures/trains/locobr80_db_winter.png");

    private static final float[] MODEL_TRANSLATION = {-0.75F, -0.44F, 0.0F};

    private static final float[] WREATH = {-1.4F, 1.2F, 0.0F};

    /** The chimney, from the same row. */
    private static final double[][] SMOKE_EMITTERS = {{1.8, 1.75, 0.0}};

    private static final int SMOKE_ITERATIONS = 3;

    /** The same row's explosion entry, which is the cylinder steam: one puff each side. */
    private static final double[][] CYLINDER_EMITTERS = {{1.6, -0.4, 0.8}};

    private static final int CYLINDER_ITERATIONS = 4;

    public Br80Renderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    protected net.minecraft.world.phys.AABB getBoundingBoxForCulling(Br80LocomotiveEntity entity) {
        return RollingStockRenderer.cullingBox(entity);
    }

    @Override
    public RollingStockRenderState createRenderState() {
        return new RollingStockRenderState();
    }

    @Override
    public void extractRenderState(
            Br80LocomotiveEntity entity, RollingStockRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();

        state.wheelAngle = entity.wheelAngle(partialTicks);
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partialTicks;
        state.colour = entity.getColour();
    }

    private static final float RAIL_DROP = -0.30F;

    @Override
    public void submit(
            RollingStockRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {
        boolean holiday = Holiday.isHoliday();

        poseStack.pushPose();

        poseStack.translate(0.0F, RAIL_DROP, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - state.yaw));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-state.pitch));
        RollingStockRenderer.applyHurtWobble(poseStack, state);
        poseStack.translate(MODEL_TRANSLATION[0], MODEL_TRANSLATION[1], MODEL_TRANSLATION[2]);

        var model = VehicleModels.INSTANCE.get("br80");
        int light = state.lightCoords;
        float wheelAngle = state.wheelAngle;
        collector.submitCustomGeometry(
                poseStack,
                RenderTypes.entityCutoutCull(holiday ? WINTER_TEXTURE : Identifier.fromNamespaceAndPath(
                        Traincraft.MODID, "textures/trains/locobr80_db_"
                                + state.colour.toLowerCase(java.util.Locale.ROOT) + ".png")),
                (pose, buffer) -> model.render(pose, buffer, light, wheelAngle, 0));

        if (holiday) {
            Holiday.submitWreath(poseStack, collector, light, WREATH);
        }
        poseStack.popPose();
    }
}
