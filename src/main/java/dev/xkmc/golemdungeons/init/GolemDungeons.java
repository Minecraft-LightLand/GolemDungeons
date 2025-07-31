package dev.xkmc.golemdungeons.init;

import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.init.data.GDConfigGen;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.serial.config.ConfigTypeEntry;
import dev.xkmc.l2library.serial.config.PacketHandlerWithConfig;
import dev.xkmc.modulargolems.init.data.MGConfigGen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GolemDungeons.MODID)
@Mod.EventBusSubscriber(modid = GolemDungeons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GolemDungeons {

	public static final String MODID = "golemdungeons";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
	public static final IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

	public static final PacketHandlerWithConfig HANDLER = new PacketHandlerWithConfig(
			new ResourceLocation(GolemDungeons.MODID, "main"), 2
	);

	public static final ConfigTypeEntry<SpawnConfig> SPAWN = new ConfigTypeEntry<>(HANDLER, "spawn", SpawnConfig.class);

	public GolemDungeons() {

	}

	@SubscribeEvent
	public static void modifyAttributes(EntityAttributeModificationEvent event) {
	}

	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
		});
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		var gen = event.getGenerator();
		gen.addProvider(event.includeServer(), new GDConfigGen(gen));
	}


	public static ResourceLocation loc(String id) {
		return new ResourceLocation(MODID, id);
	}

}
