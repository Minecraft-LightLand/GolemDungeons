package dev.xkmc.golemdungeons.compat.twilightforest.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFItems;
import twilightforest.item.GiantPickItem;

import java.util.List;

public class GDGiantPickaxeItem extends GiantPickItem {

	public GDGiantPickaxeItem(Tier material, Properties properties) {
		super(material, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
		tooltip.add(Component.translatable(TFItems.GIANT_PICKAXE.get().getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
	}

}
