package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;

public class GDWorldGen {

	public static final RegistryEntry<StructurePoolElementType<GDCustomPiece>> CUSTOM;

	static {
		CUSTOM = GolemDungeons.REGISTRATE.simple("custom_beard", Registries.STRUCTURE_POOL_ELEMENT,
				() -> () -> GDCustomPiece.CODEC);

	}

	public static void register() {
	}

}
