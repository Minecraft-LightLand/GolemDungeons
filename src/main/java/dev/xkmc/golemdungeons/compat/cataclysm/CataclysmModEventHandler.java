package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CataclysmModEventHandler {

	@SubscribeEvent
	public static void onTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(GDItems.TAB.getKey())) {
			var stack = ModItems.BURNING_ASHES.get().getDefaultInstance();
			stack.getOrCreateTag().putString("GolemMedal", "GolemMedal");
			event.accept(stack, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
		}
	}

}
