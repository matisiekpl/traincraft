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

import org.joml.Matrix4f;
import org.jspecify.annotations.Nullable;

import traincraft.client.render.models.VehicleModels;
import traincraft.client.render.state.RollingStockRenderState;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.entity.TracksBuilderEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Draws a piece of rolling stock that is nothing but its model: no smoke, no moving rods.
 *
 * <p>The transform chain is the one the locomotives use -- drop to the rail, turn to the heading,
 * lean to the pitch, then the render row's own translation, rotation and scale.
 */
public class RollingStockRenderer<T extends RollingStockEntity>
        extends EntityRenderer<T, RollingStockRenderState> {

    private static final float RAIL_DROP = -0.30F;

    public record Attachment(String modelName, Identifier texture, Matrix4f transform) {}

    private final @Nullable String modelName;
    private final Function<RollingStockRenderState, Identifier> texture;
    private final float[] translation;
    private final float[] rotation;
    private final float[] scale;
    private final Function<RollingStockRenderState, List<Attachment>> attachments;
    private final float @Nullable [] wreath;

    public RollingStockRenderer(
            EntityRendererProvider.Context context,
            String modelName,
            Identifier texture,
            float[] translation,
            float[] rotation,
            float[] scale) {
        this(context, modelName, state -> texture, translation, rotation, scale);
    }

    /** Liveried stock: the texture is {@code textures/ported/<prefix><colour>.png}. */
    public RollingStockRenderer(
            EntityRendererProvider.Context context,
            String modelName,
            String liveryPrefix,
            float[] translation,
            float[] rotation,
            float[] scale) {
        this(context, modelName, liveried(liveryPrefix), translation, rotation, scale);
    }

    private RollingStockRenderer(
            EntityRendererProvider.Context context,
            String modelName,
            Function<RollingStockRenderState, Identifier> texture,
            float[] translation,
            float[] rotation,
            float[] scale) {
        this(context, modelName, texture, translation, rotation, scale, attachments(List.of()), null);
    }

    public RollingStockRenderer(
            EntityRendererProvider.Context context,
            @Nullable String modelName,
            Function<RollingStockRenderState, Identifier> texture,
            float[] translation,
            float[] rotation,
            float[] scale,
            Function<RollingStockRenderState, List<Attachment>> attachments,
            float @Nullable [] wreath) {
        super(context);
        this.modelName = modelName;
        this.texture = texture;
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
        this.attachments = attachments;
        this.wreath = wreath;
        this.shadowRadius = 0.0F;
    }

    public static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(
                traincraft.Traincraft.MODID, "textures/ported/" + name + ".png");
    }

    public static Matrix4f transform(float... columnMajor) {
        return new Matrix4f().set(columnMajor);
    }

    public static Function<RollingStockRenderState, List<Attachment>> attachments(
            List<Attachment> fixed) {
        return state -> fixed;
    }

    public static Function<RollingStockRenderState, List<Attachment>> byColour(
            Map<String, List<Attachment>> byColour, List<Attachment> otherwise) {
        return state -> byColour.getOrDefault(state.colour, otherwise);
    }

    public static Function<RollingStockRenderState, Identifier> ported(String name) {
        Identifier texture = Identifier.fromNamespaceAndPath(
                traincraft.Traincraft.MODID, "textures/ported/" + name + ".png");
        return state -> texture;
    }

    /** CE's builder swaps its whole skin while it digs; the idle skin is the same sheet with the lights off. */
    public static Function<RollingStockRenderState, Identifier> working(String workingName, String idleName) {
        Identifier working = texture(workingName);
        Identifier idle = texture(idleName);
        return state -> state.working ? working : idle;
    }

    public static Function<RollingStockRenderState, Identifier> liveried(String prefix) {
        return state -> Identifier.fromNamespaceAndPath(
                traincraft.Traincraft.MODID,
                "textures/ported/" + prefix + state.colour.toLowerCase(java.util.Locale.ROOT) + ".png");
    }

    public static Function<RollingStockRenderState, Identifier> winter(
            String winterName, Function<RollingStockRenderState, Identifier> otherwise) {
        Function<RollingStockRenderState, Identifier> winter = ported(winterName);
        return state -> Holiday.isHoliday() ? winter.apply(state) : otherwise.apply(state);
    }

    /**
     * The hitbox is one block; the model reaches nearly five blocks from it. Culling on the hitbox
     * alone drops a locomotive whose middle is off screen while its ends are not.
     */
    static AABB cullingBox(RollingStockEntity entity) {
        return entity.getBoundingBox().inflate(6.0, 0.0, 6.0).expandTowards(0.0, 3.0, 0.0);
    }

    @Override
    protected AABB getBoundingBoxForCulling(T entity) {
        return cullingBox(entity);
    }

    @Override
    public RollingStockRenderState createRenderState() {
        return new RollingStockRenderState();
    }

    @Override
    public void extractRenderState(T entity, RollingStockRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getYRot();
        state.pitch = entity.getXRot();
        state.wheelAngle = entity.wheelAngle(partialTicks);
        state.cargo = entity.filledSlots();
        state.working = entity instanceof TracksBuilderEntity builder && builder.isWorking();
        state.spin = state.working ? (entity.tickCount + partialTicks) * 0.1F : 0.0F;
        state.hurtTime = entity.getHurtTime();
        state.hurtDir = entity.getHurtDir();
        state.damage = entity.getDamage();
        state.partialTicks = partialTicks;
        state.colour = entity.getColour();
    }

    /**
     * The rock a piece of stock does after it is hit. CE's {@code RenderRollingStock} used the boat
     * formula, which jerks tens of degrees; this is one full swing to either side over the ten
     * hurt ticks, starting and ending level, at most four degrees, its first lean the way the blow
     * came from.
     */
    static void applyHurtWobble(PoseStack poseStack, RollingStockRenderState state) {
        float time = state.hurtTime - state.partialTicks;
        if (time <= 0.0F || state.damage <= 0.0F) {
            return;
        }
        float amplitude = Math.min(state.damage, 40.0F) / 40.0F * 4.0F;
        float angle = amplitude * (float) Math.sin((10.0F - time) * Math.PI / 5.0) * time / 10.0F;
        poseStack.mulPose(Axis.XP.rotationDegrees(angle * state.hurtDir));
    }

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
        applyHurtWobble(poseStack, state);

        poseStack.translate(translation[0], translation[1], translation[2]);
        poseStack.mulPose(Axis.XP.rotationDegrees(rotation[0]));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation[1]));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation[2]));
        poseStack.scale(scale[0], scale[1], scale[2]);

        int light = state.lightCoords;
        float wheelAngle = state.wheelAngle;
        int cargo = state.cargo;
        if (modelName != null) {
            var model = VehicleModels.INSTANCE.get(modelName);
            collector.submitCustomGeometry(
                    poseStack,
                    RenderTypes.entityCutoutCull(texture.apply(state)),
                    (pose, buffer) -> model.render(pose, buffer, light, wheelAngle, 0));
            var load = VehicleModels.INSTANCE.find(modelName + "_cargo");
            if (load != null) {
                collector.submitCustomGeometry(
                        poseStack,
                        RenderTypes.entityCutout(texture.apply(state)),
                        (pose, buffer) -> load.render(pose, buffer, light, 0.0F, cargo));
            }
        }
        for (Attachment attachment : attachments.apply(state)) {
            var attached = VehicleModels.INSTANCE.get(attachment.modelName());
            poseStack.pushPose();
            poseStack.mulPose(attachment.transform());
            collector.submitCustomGeometry(
                    poseStack,
                    attachment.transform().determinant3x3() < 0.0F
                            ? RenderTypes.entityCutout(attachment.texture())
                            : RenderTypes.entityCutoutCull(attachment.texture()),
                    (pose, buffer) -> attached.render(pose, buffer, light, state.spin, 0));
            poseStack.popPose();
        }
        if (wreath != null && Holiday.isHoliday()) {
            Holiday.submitWreath(poseStack, collector, light, wreath);
        }

        poseStack.popPose();
    }
}
