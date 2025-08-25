package dev.xkmc.golemdungeons.compat.twilightforest.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFItems;

import java.util.List;

public class GDFieryPickItem extends GDGiantPickaxeItem {

	public GDFieryPickItem(Tier material, Properties properties) {
		super(material, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, level, tooltip, flags);
		tooltip.add(Component.translatable(TFItems.FIERY_PICKAXE.get().getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
	}
}
