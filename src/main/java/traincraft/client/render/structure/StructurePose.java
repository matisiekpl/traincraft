package traincraft.client.render.structure;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

import org.joml.Vector3f;

import traincraft.Traincraft;
import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.obj.ObjMesh;
import traincraft.client.render.obj.ObjMeshManager;

public final class StructurePose {

    private StructurePose() {}

    public static void apply(PoseStack poseStack, List<List<Object>> operations) {
        for (List<Object> operation : operations) {
            String kind = (String) operation.get(0);
            float a = ((Number) operation.get(1)).floatValue();
            float b = ((Number) operation.get(2)).floatValue();
            float c = ((Number) operation.get(3)).floatValue();
            switch (kind) {
                case "translate" -> poseStack.translate(a, b, c);
                case "scale" -> poseStack.scale(a, b, c);
                case "rotate" -> {
                    float d = ((Number) operation.get(4)).floatValue();
                    if (a != 0.0F) {
                        poseStack.mulPose(new org.joml.Quaternionf().rotateAxis((float) Math.toRadians(a), new Vector3f(b, c, d).normalize()));
                    }
                }
                default -> throw new IllegalArgumentException(kind);
            }
        }
    }

    public static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/structure/" + name + ".png");
    }

    public static void model(PoseStack poseStack, SubmitNodeCollector collector, String model, String texture, int light, int colour) {
        var baked = VehicleModels.INSTANCE.get(model);
        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutoutCull(texture(texture)), (pose, buffer) -> baked.render(pose, buffer, light, 0.0F, 0));
    }

    public static void obj(PoseStack poseStack, SubmitNodeCollector collector, String obj, String texture, int light, int colour) {
        ObjMesh mesh = ObjMeshManager.INSTANCE.get(obj + ".obj");
        if (mesh == null) {
            return;
        }
        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(texture(texture)), (pose, buffer) -> emit(pose, buffer, mesh, light, colour));
    }

    private static void emit(PoseStack.Pose pose, VertexConsumer buffer, ObjMesh mesh, int light, int colour) {
        int slots = mesh.vertexSlotCount();
        for (int i = 0; i < slots; i++) {
            buffer.addVertex(pose, mesh.posX(i), mesh.posY(i), mesh.posZ(i))
                    .setColor(colour)
                    .setUv(mesh.u(i), mesh.v(i))
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(light)
                    .setNormal(pose, mesh.normX(i), mesh.normY(i), mesh.normZ(i));
        }
    }
}
