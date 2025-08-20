package dev.xkmc.golemdungeons.compat.twilightforest.data;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.advancement.TrialCompleteTrigger;
import dev.xkmc.golemdungeons.init.data.loot.ModLootItem;
import dev.xkmc.l2library.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2library.serial.advancements.CriterionBuilder;
import dev.xkmc.l2library.serial.advancements.ModLoadedAdv;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.Util;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
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
						CriterionBuilder.one(TrialCompleteTrigger.ins(TwilightGolemSpawn.ALL)),
						"The Sealed Invasion", "Defeat Trial of Twilight Invasion in dungeons of the Final Castle")
				.add(new ModLoadedAdv(TFDispatch.MODID));
		tf.create("giant_upgrade", TwilightGDRegistry.ITEM_GIANT.get(),
				CriterionBuilder.item(TwilightGDRegistry.ITEM_GIANT.get()),
				"Giant Golems", "Grind golem trials to collect materials and craft the Giant Upgrade");
		tf.create("craft_giant_knightmetal_ingot", TwilightGDRegistry.GIANT_KNIGHTMETAL_PICKAXE.get(),
						CriterionBuilder.item(TwilightGDRegistry.GIANT_KNIGHTMETAL_PICKAXE.get()),
						"Blessing to Giant Miners", "Grind golem trials to collect materials and craft the Giant Knightmetal Pickaxe. Use it to mine Giant Obsidian!")
				.type(FrameType.GOAL).add(new ModLoadedAdv(TFDispatch.MODID))
				.create("craft_fiery_giant_sword", TwilightGDRegistry.GIANT_FIERY_SWORD.get(),
						CriterionBuilder.item(TwilightGDRegistry.GIANT_FIERY_SWORD.get()),
						"Grinder Warfare of Twilight", "Grind golem trials to collect materials and craft the Giant Fiery Sword")
				.type(FrameType.CHALLENGE).add(new ModLoadedAdv(TFDispatch.MODID));
	}

	public static final ResourceLocation REWARD = GolemDungeons.loc("trial_reward/twilight_invasion");

	public static void genLoot(RegistrateLootTableProvider pvd) {
		pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(REWARD,
				LootTable.lootTable()
						.withPool(LootTableTemplate.getPool(4, 1)
								.add(ModLootItem.lootTableItem(TFItems.CARMINITE.get(), 4, 8))
								.add(ModLootItem.lootTableItem(TFItems.FIERY_BLOOD.get(), 2, 4))
								.add(ModLootItem.lootTableItem(TFItems.FIERY_TEARS.get(), 2, 4))
								.add(ModLootItem.lootTableItem(TFItems.NAGA_SCALE.get(), 4, 8))
								.add(ModLootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_3.get(), 2, 4))
								.add(ModLootItem.lootTableItem(TFItems.CHARM_OF_LIFE_2.get(), 2, 4))
						)
						.withPool(LootTableTemplate.getPool(3, 1)
								.add(ModLootItem.lootTableItem(TFItems.HYDRA_CHOP.get(), 2, 4))
								.add(ModLootItem.lootTableItem(TFItems.MAZE_WAFER.get(), 16, 24))
								.add(ModLootItem.lootTableItem(TFItems.EXPERIMENT_115.get(), 4, 8))
								.add(ModLootItem.lootTableItem(TFItems.MAGIC_BEANS.get()))
						)
						.withPool(LootTableTemplate.getPool(1, 1)
								.add(ModLootItem.lootTableItem(TFItems.MOONWORM_QUEEN.get()))
								.add(ModLootItem.lootTableItem(TFItems.PEACOCK_FEATHER_FAN.get()))
								.add(ModLootItem.lootTableItem(TFItems.ORE_MAGNET.get()))
						)
						.withPool(LootTableTemplate.getPool(1, 1)
								.add(ModLootItem.lootTableItem(TFItems.GLASS_SWORD.get()))
								.add(ModLootItem.lootTableItem(TFItems.ENDER_BOW.get()))
								.add(ModLootItem.lootTableItem(TFItems.SEEKER_BOW.get()))
								.add(ModLootItem.lootTableItem(TFItems.FORTIFICATION_SCEPTER.get()))
								.add(ModLootItem.lootTableItem(TFItems.ZOMBIE_SCEPTER.get()))
								.add(ModLootItem.lootTableItem(TFItems.PHANTOM_CHESTPLATE.get()))
								.add(ModLootItem.lootTableItem(TFItems.PHANTOM_HELMET.get()))
						)
		));
	}
}
