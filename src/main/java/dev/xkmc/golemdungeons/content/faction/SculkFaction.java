package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.l2serial.util.LazyFunction;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;

import java.util.List;

public class SculkFaction extends DungeonFaction {

	private static final LazyFunction<Level, ItemStack> BANNER = LazyFunction.create(level -> {
		var reg = level.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN);
		ItemStack stack = new ItemStack(Items.WHITE_BANNER);
		stack.set(DataComponents.BANNER_PATTERNS, new BannerPatternLayers(List.of(
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.STRIPE_SMALL), DyeColor.CYAN),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.CURLY_BORDER), DyeColor.BLACK),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.FLOWER), DyeColor.BLUE),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.TRIANGLE_TOP), DyeColor.BLACK),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.SKULL), DyeColor.CYAN),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.CREEPER), DyeColor.BLACK)
		)));
		stack.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
		return stack;
	});

	public SculkFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get(e.level());
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType() == EntityType.WARDEN) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
