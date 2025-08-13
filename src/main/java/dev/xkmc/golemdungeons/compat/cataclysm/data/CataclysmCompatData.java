package dev.xkmc.golemdungeons.compat.cataclysm.data;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDRecipeGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.l2library.serial.recipe.NBTRecipe;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import net.minecraft.Util;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

public class CataclysmCompatData {

	public static void genSpawn(ConfigDataProvider.Collector map) {
		HarbingerGolemSpawn.add(map);
		MonstrosityGolemSpawn.add(map);
		EnderGuardianGolemSpawn.add(map);
		IgnisGolemSpawn.add(map);
		ScyllaGolemSpawn.add(map);
	}

	public static void genRecipe(RegistrateRecipeProvider pvd) {
		var stack = ModItems.BURNING_ASHES.get().getDefaultInstance();
		stack.getOrCreateTag().putString("GolemMedal", "GolemMedal");

		GDRecipeGen.unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BURNING_ASHES.get())::unlockedBy, GDItems.TRIAL_MEDAL.get())
				.requires(ModItems.BURNING_ASHES.get()).requires(GDItems.TRIAL_MEDAL.get())
				.save(r -> ConditionalRecipeWrapper.mod(pvd, CataDispatch.MODID).accept(new NBTRecipe(r, stack)),
						GolemDungeons.loc("burning_ashes_with_medal"));
	}

	public static void genLang(RegistrateLangProvider pvd) {
		pvd.add(Util.makeDescriptionId("trial", HarbingerGolemSpawn.HARBINGER_ALL), "Harbinger's Revenge");
		pvd.add(Util.makeDescriptionId("trial", MonstrosityGolemSpawn.MONSTROSITY_ALL), "Netherite Reinforcement");
		pvd.add(Util.makeDescriptionId("trial", EnderGuardianGolemSpawn.ENDER_GUARDIAN_ALL), "Meknight of the End");
		pvd.add(Util.makeDescriptionId("trial", ScyllaGolemSpawn.SCYLLA_ALL), "Heavenly Storm");
		pvd.add(Util.makeDescriptionId("trial", IgnisGolemSpawn.IGNIS_ALL), "Resurgent Flames");
	}

}
