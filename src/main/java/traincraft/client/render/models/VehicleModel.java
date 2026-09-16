package traincraft.client.render.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.texture.OverlayTexture;

import org.joml.Matrix4f;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Immutable model geometry; animation is supplied separately for each draw. Coordinates use model
 * pixels.
 */
public record VehicleModel(List<Part> parts) {
    public enum Group {
        BODY,
        WHEELS,
        ROTOR
    }

    public enum RotationOrder {
        ZYX,
        YZX
    }

    public record Vector(float x, float y, float z) {}

    public record Vertex(
            float x, float y, float z, float u, float v, float nx, float ny, float nz) {}

    /** One run of filled slot counts over which the part is drawn at {@code base + step * (cargo - from)}. */
    public record CargoStage(int from, int to, float[] base, float[] step) {}

    public record Part(
            String name,
            Group group,
            boolean emissive,
            RotationOrder rotationOrder,
            Vector pivot,
            Vector rotation,
            List<Vertex> vertices,
            List<CargoStage> cargo) {
        public Part {
            vertices = List.copyOf(vertices);
            cargo = List.copyOf(cargo);
        }

        /** The pose for this many filled slots, or null when the part is not drawn at that count. */
        public Matrix4f cargoPose(int filled) {
            int count = Math.min(filled, cargo.getLast().to());
            for (CargoStage stage : cargo) {
                if (count < stage.from() || count > stage.to()) {
                    continue;
                }
                float[] values = new float[16];
                for (int i = 0; i < 16; i++) {
                    values[i] = stage.base()[i] + stage.step()[i] * (count - stage.from());
                }
                return new Matrix4f().set(values);
            }
            return null;
        }
    }

    public VehicleModel {
        parts = List.copyOf(parts);
    }

    public static VehicleModel read(Reader reader) {
        var root = JsonParser.parseReader(reader).getAsJsonObject();
        List<Part> parts = new ArrayList<>();
        var names = new HashSet<String>();
        for (var element : root.getAsJsonArray("parts")) {
            var part = element.getAsJsonObject();
            String name = part.get("name").getAsString();
            if (!names.add(name))
                throw new IllegalArgumentException("Duplicate model part: " + name);
            List<Vertex> vertices = new ArrayList<>();
            for (var faceElement : part.getAsJsonArray("faces")) {
                var face = faceElement.getAsJsonArray();
                if (face.size() < 3)
                    throw new IllegalArgumentException("A face needs at least three vertices");
                if (face.size() == 4) {
                    for (var value : face) vertices.add(vertex(value.getAsJsonArray()));
                } else {
                    for (int i = 1; i + 1 < face.size(); i++) {
                        vertices.add(vertex(face.get(0).getAsJsonArray()));
                        vertices.add(vertex(face.get(i).getAsJsonArray()));
                        Vertex last = vertex(face.get(i + 1).getAsJsonArray());
                        vertices.add(last);
                        vertices.add(last);
                    }
                }
            }
            List<CargoStage> cargo = new ArrayList<>();
            if (part.has("cargo")) {
                for (var stageElement : part.getAsJsonArray("cargo")) {
                    var stage = stageElement.getAsJsonArray();
                    cargo.add(new CargoStage(stage.get(0).getAsInt(), stage.get(1).getAsInt(), matrix(stage.get(2).getAsJsonArray()), matrix(stage.get(3).getAsJsonArray())));
                }
            }
            parts.add(
                    new Part(
                            name,
                            Group.valueOf(
                                    part.get("group")
                                            .getAsString()
                                            .toUpperCase(java.util.Locale.ROOT)),
                            part.get("emissive").getAsBoolean(),
                            RotationOrder.valueOf(part.get("rotationOrder").getAsString()),
                            vector(part.getAsJsonArray("pivot")),
                            vector(part.getAsJsonArray("rotation")),
                            vertices,
                            cargo));
        }
        if (parts.isEmpty()) throw new IllegalArgumentException("Empty vehicle model");
        return new VehicleModel(parts);
    }

    private static float[] matrix(JsonArray values) {
        if (values.size() != 16)
            throw new IllegalArgumentException("Expected a sixteen-component matrix");
        float[] result = new float[16];
        for (int i = 0; i < 16; i++) result[i] = number(values, i);
        return result;
    }

    private static Vector vector(JsonArray values) {
        if (values.size() != 3)
            throw new IllegalArgumentException("Expected a three-component vector");
        return new Vector(number(values, 0), number(values, 1), number(values, 2));
    }

    private static Vertex vertex(JsonArray values) {
        if (values.size() != 8)
            throw new IllegalArgumentException("Expected position, UV and normal");
        return new Vertex(
                number(values, 0),
                number(values, 1),
                number(values, 2),
                number(values, 3),
                number(values, 4),
                number(values, 5),
                number(values, 6),
                number(values, 7));
    }

    private static float number(JsonArray values, int index) {
        float value = values.get(index).getAsFloat();
        if (!Float.isFinite(value))
            throw new IllegalArgumentException("Non-finite model coordinate");
        return value;
    }

    public void render(PoseStack.Pose origin, VertexConsumer buffer, int light, float wheelAngle, int cargo) {
        PoseStack stack = new PoseStack();
        stack.last().set(origin);
        for (Part part : parts) {
            Matrix4f cargoPose = part.cargo().isEmpty() ? null : part.cargoPose(cargo);
            if (!part.cargo().isEmpty() && cargoPose == null) {
                continue;
            }
            stack.pushPose();
            if (cargoPose != null) {
                stack.mulPose(cargoPose);
            }
            stack.translate(part.pivot().x() / 16, part.pivot().y() / 16, part.pivot().z() / 16);
            float z = part.group() == Group.WHEELS ? wheelAngle : part.rotation().z();
            float x = part.group() == Group.ROTOR ? part.rotation().x() + wheelAngle : part.rotation().x();
            if (part.rotationOrder() == RotationOrder.ZYX) {
                stack.mulPose(Axis.ZP.rotationDegrees(z * 57.29578F));
                stack.mulPose(Axis.YP.rotationDegrees(part.rotation().y() * 57.29578F));
            } else {
                stack.mulPose(Axis.YP.rotationDegrees(part.rotation().y() * 57.29578F));
                stack.mulPose(Axis.ZP.rotationDegrees(z * 57.29578F));
            }
            stack.mulPose(Axis.XP.rotationDegrees(x * 57.29578F));
            int partLight = part.emissive() ? 15728880 : light;
            for (Vertex vertex : part.vertices()) {
                buffer.addVertex(stack.last(), vertex.x() / 16, vertex.y() / 16, vertex.z() / 16)
                        .setColor(-1)
                        .setUv(vertex.u(), vertex.v())
                        .setOverlay(OverlayTexture.NO_OVERLAY)
                        .setLight(partLight)
                        .setNormal(stack.last(), vertex.nx(), vertex.ny(), vertex.nz());
            }
            stack.popPose();
        }
    }
}
