package traincraft.client.render.structure;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;

import traincraft.vehicle.entity.ZeppelinEntity;

public class ZeppelinRenderer extends EntityRenderer<ZeppelinEntity, ZeppelinRenderer.State> {

    public static class State extends EntityRenderState {
        public float yaw;
        public float hurtTime;
        public float hurtDirection;
        public float damage;
        public boolean twoBalloons;
    }

    public ZeppelinRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 1.0F;
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(ZeppelinEntity entity, State state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getYRot();
        state.hurtTime = entity.getHurtTime() - partialTicks;
        state.hurtDirection = entity.getHurtDir();
        state.damage = entity.getDamage() - partialTicks;
        state.twoBalloons = entity.twoBalloons();
    }

    @Override
    public void submit(State state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - state.yaw));
        if (state.hurtTime > 0.0F && state.damage > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees((float) Math.sin(state.hurtTime) * state.hurtTime * state.damage / 10.0F * state.hurtDirection));
        }
        StructureRenderer.submit(state.twoBalloons ? "airship" : "zeppelin", "south", "default", -1, 0.0F, 0.0F, state.lightCoords, poseStack, collector);
        poseStack.popPose();
        super.submit(state, poseStack, collector, camera);
    }
}
