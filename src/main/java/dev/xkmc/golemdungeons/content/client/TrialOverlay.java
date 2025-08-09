package dev.xkmc.golemdungeons.content.client;

import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.l2library.base.overlay.OverlayUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class TrialOverlay implements IGuiOverlay {

	@Override
	public void render(ForgeGui gui, GuiGraphics g, float pTick, int sw, int sh) {
		if (Minecraft.getInstance().screen != null) return;
		ClientLevel level = Minecraft.getInstance().level;
		var player = Minecraft.getInstance().player;
		if (level == null || player == null) return;
		var hit = Minecraft.getInstance().hitResult;
		if (!(hit instanceof BlockHitResult bhit)) return;
		if (!(level.getBlockEntity(bhit.getBlockPos()) instanceof GolemTrialBlockEntity tile)) return;
		var text = tile.getText(player);
		if (text.isEmpty()) return;
		new OverlayUtil(g, (int) (sw * 0.7), (int) (sh * 0.5), sw / 4)
				.renderLongText(gui.getFont(), text);
	}

}
