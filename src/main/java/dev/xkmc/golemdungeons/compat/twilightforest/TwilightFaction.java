package dev.xkmc.golemdungeons.compat.twilightforest;

import dev.xkmc.golemdungeons.content.faction.DungeonFaction;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.Lazy;

public class TwilightFaction extends DungeonFaction {

	private static final Lazy<ItemStack> BANNER = Lazy.of(() -> {
		ItemStack stack = new ItemStack(Items.YELLOW_BANNER);
		CompoundTag comp = new CompoundTag();
		ListTag list = new BannerPattern.Builder()
				.addPattern(BannerPatterns.GRADIENT_UP, DyeColor.LIGHT_GRAY)
				.addPattern(BannerPatterns.BRICKS, DyeColor.WHITE)
				.addPattern(BannerPatterns.TRIANGLES_TOP, DyeColor.LIGHT_BLUE)
				.addPattern(BannerPatterns.GLOBE, DyeColor.GREEN)
				.toListTag();
		comp.put("Patterns", list);
		BlockItem.setBlockEntityData(stack, BlockEntityType.BANNER, comp);
		stack.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
		return stack;
	});

	public TwilightFaction(ResourceLocation id) {
		super(id);
	}

	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get();
	}

}
