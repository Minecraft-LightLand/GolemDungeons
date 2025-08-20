//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dev.xkmc.golemdungeons.compat.twilightforest.item;

import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import twilightforest.data.tags.BlockTagGenerator;

public class TFGiantTier {
	public static final Tier IRONWOOD;
	public static final Tier FIERY;
	public static final Tier KNIGHTMETAL;

	static {
		IRONWOOD = new SimpleTier(BlockTagGenerator.INCORRECT_FOR_IRONWOOD_TOOL, 2048, 6.5F, 2.0F, 25, () -> Ingredient.of(TwilightGDRegistry.GIANT_IRONWOOD_INGOT));
		FIERY = new SimpleTier(BlockTagGenerator.INCORRECT_FOR_FIERY_TOOL, 8192, 9.0F, 4.0F, 10, () -> Ingredient.of(TwilightGDRegistry.GIANT_FIERY_INGOT));
		KNIGHTMETAL = new SimpleTier(BlockTagGenerator.INCORRECT_FOR_KNIGHTMETAL_TOOL, 2048, 8.0F, 3.0F, 8, () -> Ingredient.of(TwilightGDRegistry.GIANT_KNIGHT_INGOT));
	}
}
