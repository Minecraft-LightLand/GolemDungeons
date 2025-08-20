package dev.xkmc.golemdungeons.init;

import com.tterrag.registrate.providers.ProviderType;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmEventHandler;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmModEventHandler;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.content.item.SummonWandSelector;
import dev.xkmc.golemdungeons.events.GDAttackListener;
import dev.xkmc.golemdungeons.init.data.*;
import dev.xkmc.golemdungeons.init.data.advancement.GDAdvGen;
import dev.xkmc.golemdungeons.init.data.advancement.GDTriggers;
import dev.xkmc.golemdungeons.init.data.loot.GDLootGen;
import dev.xkmc.golemdungeons.init.data.structure.GDStructureGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.init.reg.GDModifiers;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.simple.Reg;
import dev.xkmc.l2core.serial.config.ConfigTypeEntry;
import dev.xkmc.l2core.serial.config.PacketHandlerWithConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2itemselector.select.item.IItemSelector;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GolemDungeons.MODID)
@EventBusSubscriber(modid = GolemDungeons.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GolemDungeons {

	public static final String MODID = "golemdungeons";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Reg REG = new Reg(MODID);
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final PacketHandlerWithConfig HANDLER = new PacketHandlerWithConfig(
			GolemDungeons.MODID, 1
	);

	public static final ConfigTypeEntry<SpawnConfig> SPAWN = new ConfigTypeEntry<>(HANDLER, "spawn", SpawnConfig.class);
	public static final ConfigTypeEntry<EquipmentConfig> ITEMS = new ConfigTypeEntry<>(HANDLER, "equipment", EquipmentConfig.class);
	public static final ConfigTypeEntry<TrialConfig> TRIAL = new ConfigTypeEntry<>(HANDLER, "trial", TrialConfig.class);

	public GolemDungeons(IEventBus bus) {
		GDItems.register();
		GDModifiers.register();
		GDWorldGen.register();
		GDTriggers.register();
		GDConfig.init();
		AttackEventHandler.register(3513, new GDAttackListener());
		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			NeoForge.EVENT_BUS.register(CataclysmEventHandler.class);
			bus.register(CataclysmModEventHandler.class);
		}
	}

	@SubscribeEvent
	public static void modifyAttributes(EntityAttributeModificationEvent event) {
	}

	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DungeonFactionRegistry.register();
			IItemSelector.register(new SummonWandSelector(GolemDungeons.MODID));
			if (ModList.get().isLoaded(CataDispatch.MODID)) {
				CataclysmFactions.register();
				IItemSelector.register(new SummonWandSelector(CataDispatch.MODID));
			}
			if (ModList.get().isLoaded(TFDispatch.MODID)) {
				TwilightFactions.register();
				//IItemSelector.register(new SummonWandSelector(CataDispatch.MODID));
			}
		});
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		REGISTRATE.addDataGenerator(ProviderType.LANG, GDLang::genLang);
		REGISTRATE.addDataGenerator(ProviderType.LOOT, GDLootGen::genLoot);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, GDRecipeGen::genRecipe);
		REGISTRATE.addDataGenerator(ProviderType.ADVANCEMENT, GDAdvGen::genAdv);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, GDTagGen::genItemTag);
		REGISTRATE.addDataGenerator(GDTagGen.BIOME_TAG, GDTagGen::genBiomeTag);
		REGISTRATE.addDataGenerator(GDTagGen.STRUCTURE_TAG, GDTagGen::genStructureTag);
		var init = REGISTRATE.getDataGenInitializer();
		GDStructureGen.add(init);
		init.addDependency(ProviderType.RECIPE, ProviderType.DYNAMIC);
		init.addDependency(GDTagGen.STRUCTURE_TAG, ProviderType.DYNAMIC);
		new GDDamageTypes(REGISTRATE).generate();
		var gen = event.getGenerator();
		var output = gen.getPackOutput();
		var pvd = event.getLookupProvider();
		var server = event.includeServer();
		gen.addProvider(server, new GDConfigGen(gen, pvd));
		gen.addProvider(server, new GDGLMGen(output, pvd));
	}

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(MODID, id);
	}

}
