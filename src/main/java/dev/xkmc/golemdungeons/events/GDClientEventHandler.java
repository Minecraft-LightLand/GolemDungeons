package dev.xkmc.golemdungeons.events;

import dev.xkmc.golemdungeons.content.spawner.BannerIds;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = GolemDungeons.MODID)
public class GDClientEventHandler {

	@SubscribeEvent
	public static void bossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
		var level = Minecraft.getInstance().level;
		if (level == null) return;
		var tex = BannerIds.get(event.getBossEvent().getId());
		if (tex == null) return;
		tex = tex.withPrefix("textures/gui/sprites/boss_bar/");
		var bar = tex.withSuffix("_bar.png");
		var overlay = tex.withSuffix("_overlay.png");
		if (Minecraft.getInstance().getResourceManager().getResource(bar).isEmpty())
			return;
		if (Minecraft.getInstance().getResourceManager().getResource(overlay).isEmpty())
			return;
		var g = event.getGuiGraphics();
		int x = event.getX();
		int y = event.getY();
		drawBar(g, bar, overlay, x, y, event.getBossEvent());
		event.setCanceled(true);
	}

	public static void drawBar(GuiGraphics g, ResourceLocation bar, ResourceLocation overlay, int x, int y, BossEvent event) {
		g.blit(bar, x, y, 0, 0, 182, 5, 256, 16);
		int i = (int) (event.getProgress() * 183.0F);
		if (i > 0) {
			g.blit(bar, x, y, 0, 5, i, 5, 256, 16);
		}
		g.blit(overlay, x - 3, y - 2, 0, 0, 188, 9, 256, 16);
		Component component = event.getName();
		var font = Minecraft.getInstance().font;
		int l = font.width(component);
		int i1 = g.guiWidth() / 2 - l / 2;
		int j1 = y - 9;
		g.drawString(font, component, i1, j1, 16777215);
	}

}
