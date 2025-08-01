package dev.xkmc.golemdungeons.init;

import dev.xkmc.golemdungeons.content.client.EvilArmors;
import dev.xkmc.modulargolems.content.client.armor.GolemEquipmentModels;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
		GolemEquipmentModels.regAndAdd(event, EvilArmors.HELMET_LAYER, EvilArmors::createHelmet);
		GolemEquipmentModels.regAndAdd(event, EvilArmors.CHESTPLATE_LAYER, EvilArmors::createChestplate);
		GolemEquipmentModels.regAndAdd(event, EvilArmors.SHINGUARD_LAYER, EvilArmors::createLeggings);
	}


}
