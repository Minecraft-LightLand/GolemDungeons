package dev.xkmc.golemdungeons.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = GolemDungeons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GDClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

	}

	@SubscribeEvent
	public static void registerOverlays(RegisterGuiOverlaysEvent event) {
	}

	@SubscribeEvent
	public static void registerArmorLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
	}

	@SubscribeEvent
	public static void onResourceReload(RegisterClientReloadListenersEvent event) {
	}

	@SubscribeEvent
	public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
	}

}
