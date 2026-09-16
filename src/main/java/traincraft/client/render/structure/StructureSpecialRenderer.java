package traincraft.client.render.structure;

import java.util.List;
import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import traincraft.Traincraft;

public class StructureSpecialRenderer implements NoDataSpecialModelRenderer {

    public static final Identifier ID = Identifier.fromNamespaceAndPath(Traincraft.MODID, "structure");

    private final String name;
    private final String key;
    private final boolean entity;

    public StructureSpecialRenderer(String name, String key, boolean entity) {
        this.name = name;
        this.key = key;
        this.entity = entity;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light, int overlay, boolean foil, int outlineColour) {
        poseStack.pushPose();
        if (entity) {
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.scale(0.25F, 0.25F, 0.25F);
        }
        List<List<Object>> item = StructureTransforms.INSTANCE.get(name).item();
        if (item != null) {
            StructurePose.apply(poseStack, item);
        }
        StructureRenderer.submit(name, "south", key, -1, 0.0F, 0.0F, light, poseStack, collector);
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        output.accept(new Vector3f(0.0F, 0.0F, 0.0F));
        output.accept(new Vector3f(1.0F, 1.0F, 1.0F));
    }

    public record Unbaked(String name, String key, boolean entity) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                com.mojang.serialization.Codec.STRING.fieldOf("name").forGetter(Unbaked::name),
                com.mojang.serialization.Codec.STRING.optionalFieldOf("key", "default").forGetter(Unbaked::key),
                com.mojang.serialization.Codec.BOOL.optionalFieldOf("entity", false).forGetter(Unbaked::entity)).apply(instance, Unbaked::new));

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public StructureSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            return new StructureSpecialRenderer(name, key, entity);
        }
    }
}
