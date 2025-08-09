package dev.xkmc.golemdungeons.content.spawner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.xkmc.modulargolems.content.client.outline.BlockOutliner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class GolemTrialRenderer implements BlockEntityRenderer<GolemTrialBlockEntity> {

	public GolemTrialRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public boolean shouldRenderOffScreen(GolemTrialBlockEntity be) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 48;
	}

	@Override
	public boolean shouldRender(GolemTrialBlockEntity be, Vec3 pos) {
		var player = Minecraft.getInstance().player;
		return player != null && player.getAbilities().instabuild;
	}

	@Override
	public void render(GolemTrialBlockEntity be, float pTick, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
		var vc = buffer.getBuffer(RenderType.lines());
		var zero = Vec3.atLowerCornerOf(be.getBlockPos()).toVector3f();
		for (var e : be.getTargets()) {
			renderBox(pose, vc, e, zero, 0xFF7FCDE0);
		}
	}

	private static void renderBox(
			PoseStack pose, VertexConsumer vc, BlockPos box,
			Vector3f pos, int color
	) {
		float offset = 1f / 32;
		BlockOutliner.renderCube(pose, vc,
				box.getX() + offset-1, box.getY() + offset, box.getZ() + offset-1,
				box.getX() + 2 - offset, box.getY() + 3 - offset, box.getZ() + 2 - offset,
				pos, color);
	}
}
