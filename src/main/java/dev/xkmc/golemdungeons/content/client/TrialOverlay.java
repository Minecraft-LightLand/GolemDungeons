package dev.xkmc.golemdungeons.content.client;

import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.l2itemselector.overlay.OverlayUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.BlockHitResult;

public class TrialOverlay implements LayeredDraw.Layer {

	@Override
	public void render(GuiGraphics g, DeltaTracker deltaTracker) {
		if (Minecraft.getInstance().screen != null) return;
		ClientLevel level = Minecraft.getInstance().level;
		var player = Minecraft.getInstance().player;
		if (level == null || player == null) return;
		var hit = Minecraft.getInstance().hitResult;
		if (!(hit instanceof BlockHitResult bhit)) return;
		if (!(level.getBlockEntity(bhit.getBlockPos()) instanceof GolemTrialBlockEntity tile)) return;
		var text = tile.getText(player);
		if (text.isEmpty()) return;
		int sw = g.guiWidth();
		int sh = g.guiHeight();
		new OverlayUtil(g, (int) (sw * 0.7), (int) (sh * 0.5), sw / 4)
				.renderLongText(Minecraft.getInstance().font, text);
	}

}
