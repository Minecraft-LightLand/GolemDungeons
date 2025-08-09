package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.Lazy;

public class PiglinFaction extends DungeonFaction {

	private static final Lazy<ItemStack> BANNER = Lazy.of(() -> {
		ItemStack stack = new ItemStack(Items.BLACK_BANNER);
		CompoundTag comp = new CompoundTag();
		ListTag list = new BannerPattern.Builder()
				.addPattern(BannerPatterns.STRIPE_SMALL, DyeColor.YELLOW)
				.addPattern(BannerPatterns.BRICKS, DyeColor.GRAY)
				.addPattern(BannerPatterns.CURLY_BORDER, DyeColor.BLACK)
				.addPattern(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.RED)
				.addPattern(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.ORANGE)
				.addPattern(BannerPatterns.TRIANGLES_TOP, DyeColor.ORANGE)
				.addPattern(BannerPatterns.PIGLIN, DyeColor.RED)
				.addPattern(BannerPatterns.GRADIENT_UP, DyeColor.ORANGE)
				.addPattern(BannerPatterns.GRADIENT, DyeColor.ORANGE)
				.addPattern(BannerPatterns.BORDER, DyeColor.BLACK)
				.addPattern(BannerPatterns.PIGLIN, DyeColor.YELLOW)
				.toListTag();
		comp.put("Patterns", list);
		BlockItem.setBlockEntityData(stack, BlockEntityType.BANNER, comp);
		stack.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
		return stack;
	});

	public PiglinFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get();
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other instanceof AbstractPiglin) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
