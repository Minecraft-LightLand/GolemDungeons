package dev.xkmc.golemdungeons.init.data.advancement;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import dev.xkmc.golemdungeons.compat.cataclysm.data.CataclysmCompatData;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.golemdungeons.init.data.structure.GDStructureGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2library.serial.advancements.CriterionBuilder;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ModList;

public class GDAdvGen {

	public static void genAdv(RegistrateAdvancementProvider pvd) {
		AdvancementGenerator gen = new AdvancementGenerator(pvd, GolemDungeons.MODID);

		var root = gen.new TabBuilder("dungeons")
				.root("visit_abandoned_factory", GDItems.TRIAL_MEDAL.get(),
						CriterionBuilder.item(GDItems.TRIAL_MEDAL.get()),//TODO
						"Remnant of History", "Visit Abandoned Golem Factory");
		var defeat = root.create("defeat_abandoned_factory", GDItems.ANCIENT_FORGE.get(),
				CriterionBuilder.one(TrialCompleteTrigger.ins(FactoryGolemSpawn.FACTORY_ALL)),
				"Mechanical Warfare", "Defeat Trial of Abandoned Factory");

		defeat.create("visit_piglin_factory", Blocks.GILDED_BLACKSTONE.asItem(),
						CriterionBuilder.one(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(GDStructureGen.PIGLIN_FACTORY.asKey()))),
						"Ancient War Machine", "Visit Piglin Golem Factory")
				.create("defeat_piglin_factory", GDItems.FLAME_SWORD.get(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(PiglinGolemSpawn.PIGLIN_ALL)),
						"Legacy of Golden Time", "Defeat Trial of Piglin Legacy");

		defeat.create("visit_sculk_factory", Blocks.SCULK.asItem(),
						CriterionBuilder.one(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(GDStructureGen.SCULK_FACTORY.asKey()))),
						"Infested Laboratory", "Visit Sculk Infested Golem Factory")
				.create("defeat_sculk_factory", GDItems.SCULK_SCYTHE.get(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(SculkGolemSpawn.SCULK_ALL)),
						"The Hidden Calamity", "Defeat Trial of Sculk Infestation");

		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			CataclysmCompatData.genAdv(pvd, defeat);
		}

		root.build();

	}

}
