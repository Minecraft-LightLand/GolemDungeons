package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.Lazy;

public class SculkFaction extends DungeonFaction {

	private static final Lazy<ItemStack> BANNER = Lazy.of(() -> {
		ItemStack stack = new ItemStack(Items.BLACK_BANNER);
		CompoundTag comp = new CompoundTag();
		ListTag list = new BannerPattern.Builder()
				.addPattern(BannerPatterns.STRIPE_SMALL, DyeColor.CYAN)
				.addPattern(BannerPatterns.CURLY_BORDER, DyeColor.BLACK)
				.addPattern(BannerPatterns.FLOWER, DyeColor.BLUE)
				.addPattern(BannerPatterns.TRIANGLE_TOP, DyeColor.BLACK)
				.addPattern(BannerPatterns.SKULL, DyeColor.CYAN)
				.addPattern(BannerPatterns.CREEPER, DyeColor.BLACK)
				.toListTag();
		comp.put("Patterns", list);
		BlockItem.setBlockEntityData(stack, BlockEntityType.BANNER, comp);
		stack.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
		return stack;
	});

	public SculkFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get();
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType() == EntityType.WARDEN) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
