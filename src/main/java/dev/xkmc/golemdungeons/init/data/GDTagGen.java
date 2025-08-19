package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class GDTagGen {

	public static final TagKey<Item> FIERY = ItemTags.create(GolemDungeons.loc("fiery_pick"));


	public static void genItemTag(RegistrateItemTagsProvider pvd) {
		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			pvd.addTag(FIERY).add(TwilightGDRegistry.GIANT_FIERY_PICKAXE.get());
		}
	}
}
