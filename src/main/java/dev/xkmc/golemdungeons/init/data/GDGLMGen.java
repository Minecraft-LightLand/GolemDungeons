package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import twilightforest.loot.modifiers.FieryToolSmeltingModifier;

public class GDGLMGen extends GlobalLootModifierProvider {

	public GDGLMGen(PackOutput output) {
		super(output, GolemDungeons.MODID);
	}

	protected void start() {
		this.add("fiery_pick_smelting", new FieryToolSmeltingModifier(new LootItemCondition[]{MatchTool.toolMatches(Builder.item().of(GDTagGen.FIERY)).build()}));
	}

}
