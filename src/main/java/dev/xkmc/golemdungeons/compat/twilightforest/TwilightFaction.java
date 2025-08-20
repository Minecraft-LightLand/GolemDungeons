package dev.xkmc.golemdungeons.compat.twilightforest;

import dev.xkmc.golemdungeons.content.faction.DungeonFaction;
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

public class TwilightFaction extends DungeonFaction {

	private static final LazyFunction<Level, ItemStack> BANNER = LazyFunction.create(level -> {
		var reg = level.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN);
		ItemStack stack = new ItemStack(Items.WHITE_BANNER);
		stack.set(DataComponents.BANNER_PATTERNS, new BannerPatternLayers(List.of(
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.GRADIENT_UP), DyeColor.LIGHT_GRAY),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.BRICKS), DyeColor.WHITE),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.TRIANGLES_TOP), DyeColor.LIGHT_BLUE),
				new BannerPatternLayers.Layer(reg.getOrThrow(BannerPatterns.GLOBE), DyeColor.GREEN)
		)));
		stack.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
		return stack;
	});

	public TwilightFaction(ResourceLocation id) {
		super(id);
	}

	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return BANNER.get(e.level());
	}

}
