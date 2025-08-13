package dev.xkmc.golemdungeons.init.data.structure;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public final class GDStructureTagsProvider extends StructureTagsProvider {

	public GDStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> pvd, ExistingFileHelper helper) {
		super(output, pvd, GolemDungeons.MODID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pvd) {
		tag(GDStructureGen.SCULK_FACTORY.asTag()).add(GDStructureGen.SCULK_FACTORY.asKey());
		tag(GDStructureGen.PIGLIN_FACTORY.asTag()).add(GDStructureGen.PIGLIN_FACTORY.asKey());
		//TODO
	}

	public static TagKey<Biome> asTag(String name) {
		return TagKey.create(Registries.BIOME, GolemDungeons.loc(name));
	}

	public static TagKey<Biome> forgeTag(String name) {
		return TagKey.create(Registries.BIOME, new ResourceLocation("forge", name));
	}

	public static TagKey<Biome> cTag(String name) {
		return TagKey.create(Registries.BIOME, new ResourceLocation("c", name));
	}

}