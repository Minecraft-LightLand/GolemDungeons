package dev.xkmc.golemdungeons.content.item;

import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TrialMedal extends Item {

	public TrialMedal(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		list.add(GDLang.TRIAL_MEDAL.get());
		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			list.add(GDLang.TRIAL_MEDAL_CATA.get());
		}
	}
}
