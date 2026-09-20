package traincraft.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.client.render.obj.ObjMesh;
import traincraft.client.render.obj.TrackMeshRegistry;
import traincraft.client.render.state.TrackRenderState;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;

import java.util.List;

public class TrackRenderer implements BlockEntityRenderer<TrackBlockEntity, TrackRenderState> {

    public TrackRenderer(BlockEntityRendererProvider.Context context) {
        // no baked models or fonts needed; geometry comes from TrackMeshRegistry
    }

    @Override
    public net.minecraft.world.phys.AABB getRenderBoundingBox(TrackBlockEntity blockEntity) {
        // The whole piece, not the one block the entity sits in. See
        // TrackBlockEntity#pieceRenderBounds:
        // a track piece is a dozen blocks of geometry drawn from a single block entity, and the
        // default box makes all of it disappear the moment that one block leaves the frustum.
        return blockEntity.pieceRenderBounds();
    }

    @Override
    public TrackRenderState createRenderState() {
        return new TrackRenderState();
    }

    @Override
    public void extractRenderState(
            TrackBlockEntity blockEntity,
            TrackRenderState state,
            float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(
                blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.trackType = blockEntity.hasModel() ? blockEntity.getTrackType() : null;

        state.facing =
                TrackBlock.metaFromFacing(blockEntity.getBlockState().getValue(TrackBlock.FACING));
        state.switchActive = blockEntity.isSwitchActive();
    }

    @Override
    public void submit(
            TrackRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {
        if (state.trackType == null) {
            return;
        }
        List<TrackMeshRegistry.Part> parts =
                TrackMeshRegistry.get(state.trackType, state.facing, state.switchActive);
        if (parts.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        poseStack.translate(0.5, 0.0, 0.5);

        int light = state.lightCoords;
        for (TrackMeshRegistry.Part part : parts) {
            ObjMesh mesh = part.mesh();
            collector.submitCustomGeometry(
                    poseStack,
                    RenderTypes.entityCutout(part.texture()),
                    (pose, buffer) -> emit(pose, buffer, mesh, light));
        }

        poseStack.popPose();
    }

    /**
     * Writes one mesh into the vertex stream.
     *
     * <p>Safe to capture {@code mesh} in the submitted lambda: {@code submitCustomGeometry} copies
     * the pose eagerly and defers the lambda only until this frame's prepare pass, and the mesh
     * itself is immutable and owned by the resource-reload listener.
     *
     * <p>Vertex element order follows {@code DefaultVertexFormat.ENTITY}: position, colour, uv0,
     * uv1 (overlay), uv2 (light), normal. Omitting any of them makes the buffer throw.
     */
    private static void emit(
            PoseStack.Pose pose, VertexConsumer buffer, ObjMesh mesh, int light) {
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
