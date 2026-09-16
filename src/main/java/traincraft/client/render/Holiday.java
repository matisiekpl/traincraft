package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

import traincraft.Traincraft;
import traincraft.TraincraftConfig;
import traincraft.client.render.obj.ObjMesh;
import traincraft.client.render.obj.ObjMeshManager;

import java.util.Calendar;

public final class Holiday {

    private static final Identifier RING_TEXTURE =
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/models/ring.png");

    private static final String RING_MODEL = "ring.obj";

    private Holiday() {}

    public static boolean isHoliday() {
        String skins = TraincraftConfig.HOLIDAY_SKINS.get();
        if (skins.equalsIgnoreCase("force")) {
            return true;
        }
        if (skins.equalsIgnoreCase("false")) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return month == Calendar.DECEMBER
                || (month == Calendar.JANUARY && calendar.get(Calendar.DAY_OF_MONTH) < 7);
    }

    public static void submitWreath(
            PoseStack poseStack, SubmitNodeCollector collector, int light, float[] translation) {
        ObjMesh ring = ObjMeshManager.INSTANCE.get(RING_MODEL);
        if (ring == null) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(translation[0], translation[1], translation[2]);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.scale(0.7F, 0.7F, 0.7F);
        collector.submitCustomGeometry(
                poseStack,
                RenderTypes.entityCutout(RING_TEXTURE),
                (pose, buffer) -> emit(pose, buffer, ring, light));
        poseStack.popPose();
    }

    private static void emit(PoseStack.Pose pose, VertexConsumer buffer, ObjMesh mesh, int light) {
        int slots = mesh.vertexSlotCount();
        for (int i = 0; i < slots; i++) {
            buffer.addVertex(pose, mesh.posX(i), mesh.posY(i), mesh.posZ(i))
                    .setColor(-1)
                    .setUv(mesh.u(i), mesh.v(i))
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(light)
                    .setNormal(pose, mesh.normX(i), mesh.normY(i), mesh.normZ(i));
        }
    }
}
