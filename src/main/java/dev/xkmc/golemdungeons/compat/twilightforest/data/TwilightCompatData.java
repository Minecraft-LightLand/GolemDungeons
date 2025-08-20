package dev.xkmc.golemdungeons.compat.twilightforest.data;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.data.advancement.TrialCompleteTrigger;
import dev.xkmc.golemdungeons.init.data.loot.GDLootGen;
import dev.xkmc.l2core.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2core.serial.advancements.CriterionBuilder;
import dev.xkmc.l2core.serial.advancements.ModLoadedAdv;
import dev.xkmc.l2core.serial.config.ConfigDataProvider;
import dev.xkmc.l2core.serial.loot.LootTableTemplate;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.Util;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

public class TwilightCompatData {

	public static void genSpawn(ConfigDataProvider.Collector map) {
		TwilightGolemSpawn.add(map);
	}

	public static void genRecipe(RegistrateRecipeProvider pvd) {
		TFGDRecipeGen.genRecipe(pvd);
	}

	public static void genLang(RegistrateLangProvider pvd) {
		pvd.add("trial_selector." + TFDispatch.MODID, "Twilight Invasion");

		pvd.add(Util.makeDescriptionId("trial", TwilightGolemSpawn.ALL), "Twilight Invasion");
	}

	public static void genAdv(RegistrateAdvancementProvider pvd, AdvancementGenerator.TabBuilder.Entry defeat) {
		var tf = defeat.create("defeat_twilight_dungeon", TFBlocks.DEADROCK.get().asItem(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(TwilightGolemSpawn.ALL).build()),
						"The Sealed Invasion", "Defeat Trial of Twilight Invasion in dungeons of the Final Castle")
				.add(new ModLoadedAdv(TFDispatch.MODID));
		tf.create("giant_upgrade", TwilightGDRegistry.ITEM_GIANT.get(),
				CriterionBuilder.item(TwilightGDRegistry.ITEM_GIANT.get()),
				"Giant Golems", "Grind golem trials to collect materials and craft the Giant Upgrade");
		tf.create("craft_giant_knightmetal_ingot", TwilightGDRegistry.GIANT_KNIGHTMETAL_PICKAXE.get(),
						CriterionBuilder.item(TwilightGDRegistry.GIANT_KNIGHTMETAL_PICKAXE.get()),
						"Blessing to Giant Miners", "Grind golem trials to collect materials and craft the Giant Knightmetal Pickaxe. Use it to mine Giant Obsidian!")
				.type(AdvancementType.GOAL).add(new ModLoadedAdv(TFDispatch.MODID))
				.create("craft_fiery_giant_sword", TwilightGDRegistry.GIANT_FIERY_SWORD.get(),
						CriterionBuilder.item(TwilightGDRegistry.GIANT_FIERY_SWORD.get()),
						"Grinder Warfare of Twilight", "Grind golem trials to collect materials and craft the Giant Fiery Sword")
				.type(AdvancementType.CHALLENGE).add(new ModLoadedAdv(TFDispatch.MODID));
	}

	public static final ResourceKey<LootTable> REWARD = GDLootGen.loc("trial_reward/twilight_invasion");

	@SuppressWarnings("deprecation")
	public static void genLoot(RegistrateLootTableProvider pvd) {
		pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(REWARD,
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(4))
								.add(LootTableTemplate.getItem(TFItems.CARMINITE.get(), 4, 8))
								.add(LootTableTemplate.getItem(TFItems.FIERY_BLOOD.get(), 2, 4))
								.add(LootTableTemplate.getItem(TFItems.FIERY_TEARS.get(), 2, 4))
								.add(LootTableTemplate.getItem(TFItems.NAGA_SCALE.get(), 4, 8))
								.add(LootTableTemplate.getItem(TFItems.CHARM_OF_KEEPING_3.get(), 2, 4))
								.add(LootTableTemplate.getItem(TFItems.CHARM_OF_LIFE_2.get(), 2, 4))
						)
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(3))
								.add(LootTableTemplate.getItem(TFItems.HYDRA_CHOP.get(), 2, 4))
								.add(LootTableTemplate.getItem(TFItems.MAZE_WAFER.get(), 16, 24))
								.add(LootTableTemplate.getItem(TFItems.EXPERIMENT_115.get(), 4, 8))
								.add(LootItem.lootTableItem(TFItems.MAGIC_BEANS.get()))
						)
						.withPool(LootPool.lootPool()
								.add(LootItem.lootTableItem(TFItems.MOONWORM_QUEEN.get()))
								.add(LootItem.lootTableItem(TFItems.PEACOCK_FEATHER_FAN.get()))
								.add(LootItem.lootTableItem(TFItems.ORE_MAGNET.get()))
						)
						.withPool(LootPool.lootPool()
								.add(LootItem.lootTableItem(TFItems.GLASS_SWORD.get()))
								.add(LootItem.lootTableItem(TFItems.ENDER_BOW.get()))
								.add(LootItem.lootTableItem(TFItems.SEEKER_BOW.get()))
								.add(LootItem.lootTableItem(TFItems.FORTIFICATION_SCEPTER.get()))
								.add(LootItem.lootTableItem(TFItems.ZOMBIE_SCEPTER.get()))
								.add(LootItem.lootTableItem(TFItems.PHANTOM_CHESTPLATE.get()))
								.add(LootItem.lootTableItem(TFItems.PHANTOM_HELMET.get()))
						)
		));
	}
}
