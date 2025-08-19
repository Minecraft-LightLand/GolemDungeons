package dev.xkmc.golemdungeons.init.data.advancement;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import dev.xkmc.golemdungeons.compat.cataclysm.data.CataclysmCompatData;
import dev.xkmc.golemdungeons.compat.twilightforest.data.TwilightCompatData;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.golemdungeons.init.data.structure.GDStructureGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.init.reg.GDModifiers;
import dev.xkmc.l2library.serial.advancements.AdvancementGenerator;
import dev.xkmc.l2library.serial.advancements.CriterionBuilder;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraftforge.fml.ModList;

public class GDAdvGen {

	public static void genAdv(RegistrateAdvancementProvider pvd) {
		AdvancementGenerator gen = new AdvancementGenerator(pvd, GolemDungeons.MODID);

		var root = gen.new TabBuilder("dungeons")
				.root("visit_abandoned_factory", GDItems.EYE_OF_ANCIENT_FACTORY.get(),
						CriterionBuilder.one(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(GDStructureGen.ABANDONED_FACTORY.asKey()))),
						"Remnant of History", "Visit Abandoned Golem Factory");
		var defeat = root.create("defeat_abandoned_factory", GDItems.ANCIENT_FORGE.get(),
				CriterionBuilder.one(TrialCompleteTrigger.ins(FactoryGolemSpawn.FACTORY_ALL)),
				"Mechanical Warfare", "Defeat Trial of Abandoned Factory");

		defeat.create("visit_piglin_factory", GDItems.EYE_OF_CRIMSON_FACTORY.asItem(),
						CriterionBuilder.one(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(GDStructureGen.PIGLIN_FACTORY.asKey()))),
						"Ancient War Machine", "Visit Piglin Golem Factory")
				.create("defeat_piglin_factory", GDItems.FLAME_SWORD.get(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(PiglinGolemSpawn.PIGLIN_ALL)),
						"Legacy of Golden Time", "Defeat Trial of Piglin Legacy")
				.create("reforge_upgrade", GDModifiers.ITEM_REFORGE.get(),
						CriterionBuilder.item(GDModifiers.ITEM_REFORGE.get()),
						"Melt and Repair", "Craft Reforge Upgrade")
				.type(FrameType.GOAL);

		defeat.create("visit_sculk_factory", GDItems.EYE_OF_SCULK_FACTORY.asItem(),
						CriterionBuilder.one(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(GDStructureGen.SCULK_FACTORY.asKey()))),
						"Infested Laboratory", "Visit Sculk Infested Golem Factory")
				.create("defeat_sculk_factory", GDItems.SCULK_SCYTHE.get(),
						CriterionBuilder.one(TrialCompleteTrigger.ins(SculkGolemSpawn.SCULK_ALL)),
						"The Hidden Calamity", "Defeat Trial of Sculk Infestation")
				.create("resistance_upgrade", GDModifiers.ITEM_RESISTANCE.get(),
						CriterionBuilder.item(GDModifiers.ITEM_RESISTANCE.get()),
						"Rob the Golem", "Use Slicing Axe to obtain Resistance Upgrade")
				.type(FrameType.CHALLENGE);

		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			CataclysmCompatData.genAdv(pvd, defeat);
		}
		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			TwilightCompatData.genAdv(pvd, defeat);
		}

		root.build();

	}

}
