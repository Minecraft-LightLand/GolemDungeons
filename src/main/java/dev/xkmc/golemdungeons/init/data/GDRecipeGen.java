package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import dev.xkmc.golemdungeons.compat.cataclysm.data.CataclysmCompatData;
import dev.xkmc.golemdungeons.compat.twilightforest.data.TwilightCompatData;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.init.reg.GDModifiers;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ModList;

import java.util.function.BiFunction;

public class GDRecipeGen {

	public static void genRecipe(RegistrateRecipeProvider pvd) {

		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDItems.EYE_OF_ANCIENT_FACTORY.get())::unlockedBy, GolemItems.GOLEM_TEMPLATE.get())
				.pattern(" A ").pattern("BEB").pattern(" B ")
				.define('A', GolemItems.GOLEM_TEMPLATE)
				.define('E', Items.ENDER_EYE)
				.define('B', Items.RAW_COPPER)
				.save(pvd);

		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDItems.EYE_OF_CRIMSON_FACTORY.get())::unlockedBy, GDItems.MEDAL_OF_CONQUEROR.get())
				.pattern(" A ").pattern("BEB").pattern(" C ")
				.define('A', GDItems.MEDAL_OF_CONQUEROR)
				.define('E', Items.ENDER_EYE)
				.define('B', Items.BLAZE_POWDER)
				.define('C', Items.NETHERITE_SCRAP)
				.save(pvd);

		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDItems.EYE_OF_SCULK_FACTORY.get())::unlockedBy, GDItems.MEDAL_OF_CONQUEROR.get())
				.pattern(" A ").pattern("BEB").pattern(" C ")
				.define('A', GDItems.MEDAL_OF_CONQUEROR)
				.define('E', Items.ENDER_EYE)
				.define('B', Items.SCULK)
				.define('C', Items.DIAMOND)
				.save(pvd);

		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDItems.TRIAL_MEDAL.get())::unlockedBy, GDItems.MEDAL_OF_CONQUEROR.get())
				.pattern("DAD").pattern("ARA").pattern("DAD")
				.define('A', GDItems.MEDAL_OF_CONQUEROR)
				.define('R', Items.REDSTONE_BLOCK)
				.define('D', Items.DIAMOND)
				.save(pvd);

		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDItems.FLAME_CORE.get())::unlockedBy, GDItems.FLAME_SWORD.get())
				.pattern(" B ").pattern("BEB").pattern(" C ")
				.define('E', GDItems.FLAME_SWORD)
				.define('B', Items.BLAZE_POWDER)
				.define('C', Items.LAVA_BUCKET)
				.save(pvd);


		unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GDModifiers.ITEM_REFORGE.get())::unlockedBy, GDItems.FLAME_SWORD.get())
				.pattern(" I ").pattern("BEB").pattern(" C ")
				.define('E', GolemItems.EMPTY_UPGRADE)
				.define('B', GDItems.FLAME_CORE)
				.define('C', Blocks.ANVIL)
				.define('I', Blocks.BLAST_FURNACE)
				.save(pvd);

		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			CataclysmCompatData.genRecipe(pvd);
		}
		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			TwilightCompatData.genRecipe(pvd);
		}

	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
	}


}
