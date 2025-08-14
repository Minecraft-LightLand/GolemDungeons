package dev.xkmc.golemdungeons.compat.twilightforest.data;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.data.GDRecipeGen;
import dev.xkmc.golemdungeons.init.data.advancement.TrialCompleteTrigger;
import dev.xkmc.l2library.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2library.serial.advancements.CriterionBuilder;
import dev.xkmc.l2library.serial.advancements.ModLoadedAdv;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.Util;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

public class TwilightCompatData {

	public static void genSpawn(ConfigDataProvider.Collector map) {
		TwilightGolemSpawn.add(map);
	}


	public static void genRecipe(RegistrateRecipeProvider pvd) {
		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get())::unlockedBy,
						TFBlocks.GIANT_COBBLESTONE.get().asItem()).pattern("BIB").pattern("BGB").pattern("BBB")
				.define('I', TFItems.IRONWOOD_INGOT.get())
				.define('B', TFBlocks.IRONWOOD_BLOCK.get())
				.define('G', TFBlocks.GIANT_COBBLESTONE.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_KNIGHT_INGOT.get())::unlockedBy,
						TFBlocks.GIANT_COBBLESTONE.get().asItem()).pattern("BIB").pattern("BGB").pattern("BBB")
				.define('I', TFItems.KNIGHTMETAL_INGOT.get())
				.define('B', TFBlocks.KNIGHTMETAL_BLOCK.get())
				.define('G', TFBlocks.GIANT_COBBLESTONE.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_FIERY_INGOT.get())::unlockedBy,
						TFBlocks.GIANT_OBSIDIAN.get().asItem()).pattern("BIB").pattern("BGB").pattern("BBB")
				.define('I', TFItems.FIERY_INGOT.get())
				.define('B', TFBlocks.FIERY_BLOCK.get())
				.define('G', TFBlocks.GIANT_OBSIDIAN.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_IRONWOOD_SWORD.get())::unlockedBy,
						TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get()).pattern("I").pattern("I").pattern("L")
				.define('I', TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get())
				.define('L', TFBlocks.GIANT_LOG.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_KNIGHT_SWORD.get())::unlockedBy,
						TwilightGDRegistry.GIANT_KNIGHT_INGOT.get()).pattern("I").pattern("I").pattern("L")
				.define('I', TwilightGDRegistry.GIANT_KNIGHT_INGOT.get())
				.define('L', TFBlocks.GIANT_LOG.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_FIERY_SWORD.get())::unlockedBy,
						TwilightGDRegistry.GIANT_FIERY_INGOT.get()).pattern("I").pattern("I").pattern("L")
				.define('I', TwilightGDRegistry.GIANT_FIERY_INGOT.get())
				.define('L', Blocks.NETHERITE_BLOCK)
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

	}

	public static void genLang(RegistrateLangProvider pvd) {
		pvd.add("trial_selector." + TFDispatch.MODID, "Twilight Invasion");

		pvd.add(Util.makeDescriptionId("trial", TwilightGolemSpawn.ALL), "Twilight Invasion");
	}

	public static void genAdv(RegistrateAdvancementProvider pvd, AdvancementGenerator.TabBuilder.Entry defeat) {
		defeat.create("defeat_twilight_dungeon", TFBlocks.DEADROCK.get().asItem(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(TwilightGolemSpawn.ALL)),
						"The Sealed Invasion", "Defeat Trial of Twilight Invasion")
				.add(new ModLoadedAdv(TFDispatch.MODID))
				.create("craft_fiery_giant_sword", TwilightGDRegistry.GIANT_FIERY_SWORD.get(),
						CriterionBuilder.item(TwilightGDRegistry.GIANT_FIERY_SWORD.get()),
						"Twilight Verdun", "Craft the Fiery Giant Sword")
				.add(new ModLoadedAdv(TFDispatch.MODID));
	}

}
