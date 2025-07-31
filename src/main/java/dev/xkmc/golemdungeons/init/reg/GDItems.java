package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.item.HostileSummonWand;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2itemselector.init.data.L2ISTagGen;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;

public class GDItems {

	public static final RegistryEntry<CreativeModeTab> TAB = GolemDungeons.REGISTRATE.buildL2CreativeTab(
			"golem_dungeons", "Golem Dungeons", b -> b.icon(GDItems.SUMMON::asStack));

	public static final ItemEntry<HostileSummonWand> SUMMON;

	static {


		SUMMON = GolemDungeons.REGISTRATE.item("hostile_summon_wand", HostileSummonWand::new)
				.properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
				.tag(L2ISTagGen.SELECTABLE)
				.register();
	}

	public static void register() {

	}

}
