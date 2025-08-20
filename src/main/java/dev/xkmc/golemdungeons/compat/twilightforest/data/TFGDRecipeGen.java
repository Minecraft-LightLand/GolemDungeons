package dev.xkmc.golemdungeons.compat.twilightforest.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.data.GDRecipeGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2core.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

public class TFGDRecipeGen {

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


		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_IRONWOOD_PICKAXE.get())::unlockedBy,
						TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get()).pattern("III").pattern(" L ").pattern(" L ")
				.define('I', TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get())
				.define('L', TFBlocks.GIANT_LOG.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_KNIGHTMETAL_PICKAXE.get())::unlockedBy,
						TwilightGDRegistry.GIANT_KNIGHT_INGOT.get()).pattern("III").pattern(" L ").pattern(" L ")
				.define('I', TwilightGDRegistry.GIANT_KNIGHT_INGOT.get())
				.define('L', TFBlocks.GIANT_LOG.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.GIANT_FIERY_PICKAXE.get())::unlockedBy,
						TwilightGDRegistry.GIANT_FIERY_INGOT.get()).pattern("III").pattern("SLS").pattern("SLS")
				.define('I', TwilightGDRegistry.GIANT_FIERY_INGOT.get())
				.define('L', TFBlocks.GIANT_OBSIDIAN.get())
				.define('S', GDItems.FLAME_CORE.get())
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
						TwilightGDRegistry.GIANT_FIERY_INGOT.get()).pattern("SIS").pattern("SIS").pattern("SLS")
				.define('I', TwilightGDRegistry.GIANT_FIERY_INGOT.get())
				.define('L', Blocks.NETHERITE_BLOCK)
				.define('S', GDItems.FLAME_CORE.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

		GDRecipeGen.unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwilightGDRegistry.ITEM_GIANT.get())::unlockedBy,
						TwilightGDRegistry.GIANT_FIERY_INGOT.get()).pattern("IBI").pattern("IEI").pattern("I I")
				.define('I', TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get())
				.define('B', TFItems.MAGIC_BEANS.get())
				.define('E', GolemItems.EMPTY_UPGRADE.get())
				.save(ConditionalRecipeWrapper.mod(pvd, TFDispatch.MODID));

	}

}
