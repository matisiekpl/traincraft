package traincraft.client.render.structure;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.structure.ContainerBlockEntity;
import traincraft.structure.GeneratorBlockEntity;
import traincraft.structure.SignalBlock;
import traincraft.structure.StructureBlock;
import traincraft.structure.StructureBlockEntity;
import traincraft.structure.StructureRegistry;

public class StructureRenderer<T extends BlockEntity> implements BlockEntityRenderer<T, StructureRenderState> {

    private static final float WIG_WAG_STEP = 1.75F;
    private static final float WIG_WAG_LIMIT = 20.0F;

    public StructureRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public StructureRenderState createRenderState() {
        return new StructureRenderState();
    }

    @Override
    public AABB getRenderBoundingBox(T blockEntity) {
        return new AABB(blockEntity.getBlockPos()).inflate(2.0, 3.0, 2.0);
    }

    @Override
    public void extractRenderState(T entity, StructureRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, cameraPosition, breakProgress);
        BlockState blockState = entity.getBlockState();
        state.name = BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getPath();
        state.facing = blockState.hasProperty(StructureBlock.FACING) ? blockState.getValue(StructureBlock.FACING).getSerializedName() : "south";
        state.key = "default";
        state.colour = -1;
        boolean powered = blockState.hasProperty(BlockStateProperties.POWERED) && blockState.getValue(BlockStateProperties.POWERED);
        long time = entity.getLevel() == null ? 0 : entity.getLevel().getGameTime();
        float ticks = time + partialTicks;
        if (blockState.getBlock() instanceof SignalBlock) {
            boolean lights = state.name.endsWith("signal");
            state.key = lights ? (powered ? "green" : "red") : (powered ? "on" : "off");
            if (powered && state.name.equals("wig_wag")) {
                float period = WIG_WAG_LIMIT * 4 / WIG_WAG_STEP;
                float phase = ticks % period;
                float swing = phase * WIG_WAG_STEP;
                state.arm = swing <= WIG_WAG_LIMIT * 2 ? swing - WIG_WAG_LIMIT : WIG_WAG_LIMIT * 3 - swing;
            } else {
                state.arm = 0.0F;
            }
        } else if (blockState.hasProperty(BlockStateProperties.POWERED)) {
            state.key = powered ? "on" : "off";
        }
        if (entity instanceof ContainerBlockEntity container) {
            state.key = ContainerBlockEntity.COLOURS[container.colour()];
        } else if (entity instanceof GeneratorBlockEntity generator) {
            state.spinning = generator.spinning();
            state.spin = state.spinning ? ticks * (generator.kind() == GeneratorBlockEntity.Kind.WIND_MILL ? 2.0F : 4.0F) : 0.0F;
        } else if (entity instanceof StructureBlockEntity structure) {
            if (state.name.equals("speed_sign")) {
                state.key = Integer.toString(structure.state());
            }
            if (state.name.equals("lantern")) {
                state.colour = 0xFF000000 | structure.colour();
            }
        }
    }

    @Override
    public void submit(StructureRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        submit(state.name, state.facing, state.key, state.colour, state.spin, state.arm, state.lightCoords, poseStack, collector);
    }

    public static void submit(String name, String facing, String key, int colour, float spin, float arm, int light, PoseStack poseStack, SubmitNodeCollector collector) {
        StructureTransforms.Entry entry = StructureTransforms.INSTANCE.get(name);
        String texture = entry.textures().getOrDefault(key, entry.textures().getOrDefault("default", entry.textures().values().iterator().next()));
        int tint = entry.tint() != null ? 0xFF000000 | entry.tint() : colour;
        poseStack.pushPose();
        StructurePose.apply(poseStack, entry.base());
        var perFacing = entry.facing().get(facing);
        if (perFacing != null) {
            StructurePose.apply(poseStack, perFacing);
        }
        if (entry.spin() != null) {
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-spin));
        }
        String model = entry.models().get(key);
        if (model == null && !entry.models().isEmpty()) {
            model = entry.models().values().iterator().next();
        }
        if (model != null) {
            StructurePose.model(poseStack, collector, model, texture, light, -1);
        }
        if (entry.arms() != null) {
            for (StructureTransforms.Arm armModel : entry.arms().values()) {
                poseStack.pushPose();
                float px = armModel.pivot().get(0).floatValue() / 16.0F;
                float py = armModel.pivot().get(1).floatValue() / 16.0F;
                float pz = armModel.pivot().get(2).floatValue() / 16.0F;
                poseStack.translate(px, py, pz);
                poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(arm));
                poseStack.translate(-px, -py, -pz);
                StructurePose.model(poseStack, collector, armModel.model(), texture, light, -1);
                poseStack.popPose();
            }
        }
        if (!entry.obj().isEmpty()) {
            poseStack.pushPose();
            StructurePose.apply(poseStack, entry.objOps());
            for (var obj : entry.obj().entrySet()) {
                StructurePose.obj(poseStack, collector, obj.getKey(), obj.getValue(), light, tint);
            }
            poseStack.popPose();
        }
        poseStack.popPose();
        if (entry.wheel() != null) {
            poseStack.pushPose();
            StructurePose.apply(poseStack, entry.wheel().base());
            if (perFacing != null) {
                StructurePose.apply(poseStack, perFacing);
            }
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-spin));
            StructurePose.obj(poseStack, collector, entry.wheel().obj(), entry.textures().values().iterator().next(), light, 0xFF000000 | entry.wheel().tint());
            poseStack.popPose();
        }
    }

    public static boolean renders(BlockState state) {
        return StructureRegistry.MODEL_BLOCKS.contains(BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath());
    }
}
