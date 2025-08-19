package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import dev.xkmc.golemdungeons.content.structure.GDRuleProcessor;
import dev.xkmc.golemdungeons.content.structure.GDSimplePiece;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.loot.ModLootItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;

public class GDWorldGen {

	public static final RegistryEntry<StructurePoolElementType<GDSimplePiece>> SIMPLE;
	public static final RegistryEntry<StructurePoolElementType<GDCustomPiece>> CUSTOM;
	public static final RegistryEntry<StructureProcessorType<GDRuleProcessor>> RULE;
	public static final RegistryEntry<LootPoolEntryType> ITEM;

	static {
		SIMPLE = GolemDungeons.REGISTRATE.simple("no_liquid", Registries.STRUCTURE_POOL_ELEMENT,
				() -> () -> GDSimplePiece.CODEC);

		CUSTOM = GolemDungeons.REGISTRATE.simple("custom_beard", Registries.STRUCTURE_POOL_ELEMENT,
				() -> () -> GDCustomPiece.CODEC);

		RULE = GolemDungeons.REGISTRATE.simple("set_nbt_rules", Registries.STRUCTURE_PROCESSOR,
				() -> () -> GDRuleProcessor.CODEC);

		ITEM = GolemDungeons.REGISTRATE.simple("mod_item", Registries.LOOT_POOL_ENTRY_TYPE,
				() -> new LootPoolEntryType(new ModLootItem.Serializer()));

	}

	public static void register() {
	}

}
