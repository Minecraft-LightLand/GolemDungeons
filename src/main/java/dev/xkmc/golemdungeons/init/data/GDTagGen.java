package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.structure.GDStructureGen;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.Tags;

public class GDTagGen {

	public static final ProviderType<RegistrateTagsProvider.Impl<Biome>> BIOME_TAG = ProviderType.registerDynamicTag("tags/biome", "biomes", Registries.BIOME);
	public static final ProviderType<RegistrateTagsProvider.Impl<Structure>> STRUCTURE_TAG = ProviderType.registerDynamicTag("tags/structure", "structures", Registries.STRUCTURE);

	public static final TagKey<Item> FIERY = ItemTags.create(GolemDungeons.loc("fiery_pick"));

	public static void genItemTag(RegistrateItemTagsProvider pvd) {
		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			pvd.addTag(FIERY).add(TwilightGDRegistry.GIANT_FIERY_PICKAXE.get());
		}
	}

	public static void genBiomeTag(RegistrateTagsProvider.Impl<Biome> pvd) {
		pvd.addTag(GDStructureGen.SCULK_FACTORY.biomes()).add(Biomes.DEEP_DARK);
		pvd.addTag(GDStructureGen.PIGLIN_FACTORY.biomes()).add(Biomes.CRIMSON_FOREST);
		pvd.addTag(GDStructureGen.ABANDONED_FACTORY.biomes()).addTag(Tags.Biomes.IS_PLAINS);
	}

	public static void genStructureTag(RegistrateTagsProvider.Impl<Structure> pvd) {
		pvd.addTag(GDStructureGen.SCULK_FACTORY.asTag()).add(GDStructureGen.SCULK_FACTORY.asKey());
		pvd.addTag(GDStructureGen.PIGLIN_FACTORY.asTag()).add(GDStructureGen.PIGLIN_FACTORY.asKey());
		pvd.addTag(GDStructureGen.ABANDONED_FACTORY.asTag()).add(GDStructureGen.ABANDONED_FACTORY.asKey());
	}
}
