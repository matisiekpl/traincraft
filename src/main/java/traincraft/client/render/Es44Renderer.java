package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;

import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.state.RollingStockRenderState;
import traincraft.vehicle.entity.Es44LocomotiveEntity;

/** RenderEnum.ES44 plus the two truck draws in ModelES44.render. */
public final class Es44Renderer extends EntityRenderer<Es44LocomotiveEntity, RollingStockRenderState> {

    public Es44Renderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0;
    }

    @Override
    protected AABB getBoundingBoxForCulling(Es44LocomotiveEntity entity) {
        return RollingStockRenderer.cullingBox(entity);
    }

    @Override
    public RollingStockRenderState createRenderState() {
        return new RollingStockRenderState();
    }

    @Override
    public void extractRenderState(Es44LocomotiveEntity entity, RollingStockRenderState state, float partial) {
        super.extractRenderState(entity, state, partial);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partial;
        state.colour = entity.getColour();
        state.engineNumber = entity.getEngineNumber();
    }

    @Override
    public void submit(RollingStockRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        stack.pushPose();
        stack.translate(0, -0.3, 0);
        stack.mulPose(Axis.YP.rotationDegrees(90 - state.yaw));
        stack.mulPose(Axis.ZP.rotationDegrees(-state.pitch));
        RollingStockRenderer.applyHurtWobble(stack, state);
        stack.translate(-2.2, 0.15, 0);
        stack.mulPose(Axis.YP.rotationDegrees(180));
        stack.mulPose(Axis.ZP.rotationDegrees(180));
        draw("es44", "es44_" + state.colour.toLowerCase(java.util.Locale.ROOT), state.lightCoords, stack, collector);
        drawEngineNumber(state, stack, collector);

        // CE gives the Orange livery light grey trucks and every other livery black ones.
        String truck = state.colour.equals("Orange") ? "newgevotruck_lightgrey" : "newgevotruck_black";
        stack.pushPose();
        stack.translate(-1.8, 0, 0);
        draw("es44_truck", truck, state.lightCoords, stack, collector);
        stack.mulPose(Axis.YP.rotationDegrees(180));
        stack.translate(-3.65, 0, 0);
        draw("es44_truck", truck, state.lightCoords, stack, collector);
        stack.popPose();
        stack.popPose();
    }

    private static void drawEngineNumber(
            RollingStockRenderState state, PoseStack stack, SubmitNodeCollector collector) {
        if (state.engineNumber.isEmpty()) return;
        var text = Component.literal(state.engineNumber).getVisualOrderText();
        for (float side : new float[] {-1.0F, 1.0F}) {
            stack.pushPose();
            stack.translate(0.35F, -1.55F, side * 0.571F);
            if (side > 0) stack.mulPose(Axis.YP.rotationDegrees(180));
            stack.scale(0.0125F, -0.0125F, 0.0125F);
            collector.submitText(stack, -3.0F * state.engineNumber.length(), 0, text, false,
                    Font.DisplayMode.POLYGON_OFFSET, state.lightCoords, 0xFFFFFFFF, 0, 0);
            stack.popPose();
        }
    }

    private static void draw(String modelName, String texture, int light, PoseStack stack, SubmitNodeCollector collector) {
        var model = VehicleModels.INSTANCE.get(modelName);
        collector.submitCustomGeometry(stack,
                RenderTypes.entityCutoutCull(Identifier.fromNamespaceAndPath("tc", "textures/ported/" + texture + ".png")),
                (pose, buffer) -> model.render(pose, buffer, light, 0, 0));
    }
}
