package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import net.minecraft.util.Unit;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CataclysmModEventHandler {

	@SubscribeEvent
	public static void onTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(GDItems.TAB.getKey())) {
			var stack = ModItems.BURNING_ASHES.get().getDefaultInstance();
			GDItems.MEDAL.set(stack, Unit.INSTANCE);
			event.accept(stack, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
		}
	}

}
