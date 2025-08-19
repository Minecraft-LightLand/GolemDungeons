//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dev.xkmc.golemdungeons.compat.twilightforest.item;

import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import twilightforest.TwilightForestMod;

import java.util.List;

public class TFGiantTier {
	public static final Tier IRONWOOD;
	public static final Tier FIERY;
	public static final Tier KNIGHTMETAL;

	static {
		IRONWOOD = TierSortingRegistry.registerTier(new ForgeTier(2, 2048, 6.5F, 2.0F, 25, BlockTags.create(TwilightForestMod.prefix("needs_ironwood_tool")), () -> Ingredient.of(TwilightGDRegistry.GIANT_IRONWOOD_INGOT.get())), GolemDungeons.loc("ironwood"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
		FIERY = TierSortingRegistry.registerTier(new ForgeTier(4, 8192, 9.0F, 4.0F, 10, BlockTags.create(TwilightForestMod.prefix("needs_fiery_tool")), () -> Ingredient.of(TwilightGDRegistry.GIANT_FIERY_INGOT.get())), GolemDungeons.loc("fiery"), List.of(Tiers.NETHERITE), List.of());
		KNIGHTMETAL = TierSortingRegistry.registerTier(new ForgeTier(3, 2048, 8.0F, 3.0F, 8, BlockTags.create(TwilightForestMod.prefix("needs_knightmetal_tool")), () -> Ingredient.of(TwilightGDRegistry.GIANT_KNIGHT_INGOT.get())), GolemDungeons.loc("knightmetal"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
	}
}
