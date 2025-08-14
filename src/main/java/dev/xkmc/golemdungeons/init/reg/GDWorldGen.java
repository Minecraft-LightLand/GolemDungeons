package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import dev.xkmc.golemdungeons.content.structure.GDRuleProcessor;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class GDWorldGen {

	public static final RegistryEntry<StructurePoolElementType<GDCustomPiece>> CUSTOM;
	public static final RegistryEntry<StructureProcessorType<GDRuleProcessor>> RULE;

	static {
		CUSTOM = GolemDungeons.REGISTRATE.simple("custom_beard", Registries.STRUCTURE_POOL_ELEMENT,
				() -> () -> GDCustomPiece.CODEC);

		RULE = GolemDungeons.REGISTRATE.simple("set_nbt_rules", Registries.STRUCTURE_PROCESSOR,
				() -> () -> GDRuleProcessor.CODEC);

	}

	public static void register() {
	}

}
