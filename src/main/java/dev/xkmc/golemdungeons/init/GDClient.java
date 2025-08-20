package dev.xkmc.golemdungeons.init;

import dev.xkmc.golemdungeons.content.client.EvilArmors;
import dev.xkmc.golemdungeons.content.client.TrialOverlay;
import dev.xkmc.modulargolems.content.client.armor.GolemEquipmentModels;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(value = Dist.CLIENT, modid = GolemDungeons.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GDClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
	}

	@SubscribeEvent
	public static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.CROSSHAIR, GolemDungeons.loc("trial"), new TrialOverlay());
	}

	@SubscribeEvent
	public static void registerArmorLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		GolemEquipmentModels.regAndAdd(event, EvilArmors.HELMET_LAYER, EvilArmors::createHelmet);
		GolemEquipmentModels.regAndAdd(event, EvilArmors.CHESTPLATE_LAYER, EvilArmors::createChestplate);
		GolemEquipmentModels.regAndAdd(event, EvilArmors.SHINGUARD_LAYER, EvilArmors::createLeggings);
	}


}
