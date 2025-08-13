package dev.xkmc.golemdungeons.compat.cataclysm.data;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDRecipeGen;
import dev.xkmc.golemdungeons.init.data.advancement.TrialSummonTrigger;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2library.serial.advancements.CriterionBuilder;
import dev.xkmc.l2library.serial.advancements.ModLoadedAdv;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.l2library.serial.recipe.NBTRecipe;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.Util;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

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
		pvd.add("trial_selector." + CataDispatch.MODID, "Cataclysm Reinforcements");

		pvd.add(Util.makeDescriptionId("trial", HarbingerGolemSpawn.ALL), "Harbinger's Revenge");
		pvd.add(Util.makeDescriptionId("trial", MonstrosityGolemSpawn.ALL), "Netherite Reinforcement");
		pvd.add(Util.makeDescriptionId("trial", EnderGuardianGolemSpawn.ALL), "Meknight of the End");
		pvd.add(Util.makeDescriptionId("trial", ScyllaGolemSpawn.ALL), "Heavenly Storm");
		pvd.add(Util.makeDescriptionId("trial", IgnisGolemSpawn.ALL), "Resurgent Flames");
	}

	public static void genAdv(RegistrateAdvancementProvider pvd, AdvancementGenerator.TabBuilder.Entry defeat) {
		defeat.create("summon_harbinger_golem", GolemItems.HOLDER_GOLEM.get().withUniformMaterial(loc("witherite")),
						CriterionBuilder.one(TrialSummonTrigger.ins(HarbingerGolemSpawn.ALL)),
						"Harbinger's Revenge", "Summon Harbinger with Golem Reinforcement")
				.add(new ModLoadedAdv(CataDispatch.MODID));
		defeat.create("summon_monstrosity_golem", GolemItems.HOLDER_GOLEM.get().withUniformMaterial(ModularGolems.loc("netherite")),
						CriterionBuilder.one(TrialSummonTrigger.ins(MonstrosityGolemSpawn.ALL)),
						"Netherite Reinforcement", "Summon Netherite Monstrosity with Golem Reinforcement")
				.add(new ModLoadedAdv(CataDispatch.MODID));
		defeat.create("summon_ender_guardian_golem", GolemItems.HOLDER_GOLEM.get().withUniformMaterial(loc("ender_guardian")),
						CriterionBuilder.one(TrialSummonTrigger.ins(EnderGuardianGolemSpawn.ALL)),
						"Meknight of the End", "Summon Ender Guardian with Golem Reinforcement")
				.add(new ModLoadedAdv(CataDispatch.MODID));
		defeat.create("summon_scylla_golem", GolemItems.HOLDER_GOLEM.get().withUniformMaterial(loc("storm")),
						CriterionBuilder.one(TrialSummonTrigger.ins(ScyllaGolemSpawn.ALL)),
						"Heavenly Storm", "Summon Scylla with Golem Reinforcement")
				.add(new ModLoadedAdv(CataDispatch.MODID));
		defeat.create("summon_ignis_golem", GolemItems.HOLDER_GOLEM.get().withUniformMaterial(loc("ignitium")),
						CriterionBuilder.one(TrialSummonTrigger.ins(IgnisGolemSpawn.ALL)),
						"Resurgent Flames", "Summon Ignis with Golem Reinforcement")
				.add(new ModLoadedAdv(CataDispatch.MODID));
	}

	private static ResourceLocation loc(String id) {
		return new ResourceLocation(CataDispatch.MODID, id);
	}

}
