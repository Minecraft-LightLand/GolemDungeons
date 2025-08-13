package dev.xkmc.golemdungeons.content.spawner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.content.client.outline.BlockOutliner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
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
			var aabb = new AABB(e.getX() - 1, e.getY(), e.getZ() - 1,
					e.getX() + 2, e.getY() + 3, e.getZ() + 2);
			renderBox(pose, vc, aabb, zero, 0xFF7FCDE0);
		}
		if (be.trial == null) return;
		var e = GolemDungeons.TRIAL.getEntry(be.trial);
		if (e == null) return;
		var vec = Vec3.atCenterOf(be.getBlockPos());
		var aabb = new AABB(vec.x - e.triggerRange, vec.y + e.minY, vec.z - e.triggerRange,
				vec.x + e.triggerRange, vec.y + e.maxY, vec.z + e.triggerRange);
		renderBox(pose, vc, aabb, zero, 0xFF7FFF7F);
	}

	private static void renderBox(
			PoseStack pose, VertexConsumer vc, AABB box,
			Vector3f pos, int color
	) {
		float offset = 1f / 32;
		BlockOutliner.renderCube(pose, vc,
				(float) box.minX + offset, (float) box.minY + offset, (float) box.minZ + offset,
				(float) box.maxX - offset, (float) box.maxY - offset, (float) box.maxZ - offset,
				pos, color);
	}
}
