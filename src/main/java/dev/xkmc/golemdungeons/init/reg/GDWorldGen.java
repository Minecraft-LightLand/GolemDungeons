package dev.xkmc.golemdungeons.init.reg;

import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import dev.xkmc.golemdungeons.content.structure.GDRuleProcessor;
import dev.xkmc.golemdungeons.content.structure.GDSimplePiece;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2core.init.reg.simple.SR;
import dev.xkmc.l2core.init.reg.simple.Val;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class GDWorldGen {

	public static final Val<StructurePoolElementType<GDSimplePiece>> SIMPLE;
	public static final Val<StructurePoolElementType<GDCustomPiece>> CUSTOM;
	public static final Val<StructureProcessorType<GDRuleProcessor>> RULE;

	static {
		var spe = SR.of(GolemDungeons.REG, Registries.STRUCTURE_POOL_ELEMENT);

		SIMPLE = spe.reg("no_liquid", () -> () -> GDSimplePiece.CODEC);
		CUSTOM = spe.reg("custom_beard", () -> () -> GDCustomPiece.CODEC);

		RULE = SR.of(GolemDungeons.REG, Registries.STRUCTURE_PROCESSOR)
				.reg("set_nbt_rules", () -> () -> GDRuleProcessor.CODEC);

	}

	public static void register() {
	}

}
