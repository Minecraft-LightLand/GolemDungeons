package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.l2serial.util.LazyFunction;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;

import java.util.List;

public class RemnantFaction extends DungeonFaction {

	private static final LazyFunction<Level, ItemStack> BANNER = LazyFunction.create(level -> {
		var reg = level.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN);
		ItemStack stack = new ItemStack(Items.WHITE_BANNER);
		stack.set(DataComponents.BANNER_PATTERNS, new BannerPatternLayers(List.of(
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.TRIANGLE_BOTTOM), DyeColor.ORANGE),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.STRIPE_TOP), DyeColor.YELLOW),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.STRIPE_MIDDLE), DyeColor.GRAY)
		)));
		stack.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
		return stack;
	});

	public RemnantFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get(e.level());
	}

}
