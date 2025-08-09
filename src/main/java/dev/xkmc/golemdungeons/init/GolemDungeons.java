package dev.xkmc.golemdungeons.init;

import com.tterrag.registrate.providers.ProviderType;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmEventHandler;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.content.item.SummonWandSelector;
import dev.xkmc.golemdungeons.init.data.*;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2itemselector.select.item.IItemSelector;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.serial.config.ConfigTypeEntry;
import dev.xkmc.l2library.serial.config.PacketHandlerWithConfig;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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
			new ResourceLocation(GolemDungeons.MODID, "main"), 1
	);

	public static final ConfigTypeEntry<SpawnConfig> SPAWN = new ConfigTypeEntry<>(HANDLER, "spawn", SpawnConfig.class);
	public static final ConfigTypeEntry<EquipmentConfig> ITEMS = new ConfigTypeEntry<>(HANDLER, "equipment", EquipmentConfig.class);
	public static final ConfigTypeEntry<TrialConfig> TRIAL = new ConfigTypeEntry<>(HANDLER, "trial", TrialConfig.class);

	public GolemDungeons() {
		GDItems.register();
		GDConfig.init();
		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			MinecraftForge.EVENT_BUS.register(CataclysmEventHandler.class);
		}
	}

	@SubscribeEvent
	public static void modifyAttributes(EntityAttributeModificationEvent event) {
	}

	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DungeonFactionRegistry.register();
			IItemSelector.register(new SummonWandSelector(loc("faction_select")));
			if (ModList.get().isLoaded(CataDispatch.MODID)) {
				CataclysmFactions.register();
			}
		});
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		REGISTRATE.addDataGenerator(ProviderType.LANG, GDLang::genLang);
		REGISTRATE.addDataGenerator(ProviderType.LOOT, GDLootGen::genLoot);

		var gen = event.getGenerator();
		var output = gen.getPackOutput();
		var pvd = event.getLookupProvider();
		var helper = event.getExistingFileHelper();
		var server = event.includeServer();
		gen.addProvider(server, new GDConfigGen(gen));
		new GDDamageTypes(output, pvd, helper).generate(server, gen);
	}


	public static ResourceLocation loc(String id) {
		return new ResourceLocation(MODID, id);
	}

}
