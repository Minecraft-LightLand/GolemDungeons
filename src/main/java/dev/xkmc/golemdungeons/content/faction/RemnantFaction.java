package dev.xkmc.golemdungeons.content.faction;

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

public class RemnantFaction extends DungeonFaction {

	private static final Lazy<ItemStack> BANNER = Lazy.of(() -> {
		ItemStack stack = new ItemStack(Items.WHITE_BANNER);
		CompoundTag comp = new CompoundTag();
		ListTag list = new BannerPattern.Builder()
				.addPattern(BannerPatterns.TRIANGLE_BOTTOM, DyeColor.ORANGE)
				.addPattern(BannerPatterns.STRIPE_TOP, DyeColor.YELLOW)
				.addPattern(BannerPatterns.STRIPE_MIDDLE, DyeColor.GRAY)
				.toListTag();
		comp.put("Patterns", list);
		BlockItem.setBlockEntityData(stack, BlockEntityType.BANNER, comp);
		stack.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
		return stack;
	});

	public RemnantFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get();
	}

}
