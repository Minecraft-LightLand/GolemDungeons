package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.client.GDModelPaths;
import dev.xkmc.golemdungeons.content.summon.HostileSummonWand;
import dev.xkmc.l2itemselector.init.data.L2ISTagGen;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;

import static dev.xkmc.golemdungeons.init.GolemDungeons.REGISTRATE;

public class GDItems {

	public static final RegistryEntry<CreativeModeTab> TAB = REGISTRATE.buildL2CreativeTab(
			"golem_dungeons", "Golem Dungeons", b -> b.icon(GDItems.SUMMON::asStack));

	public static final ItemEntry<MetalGolemArmorItem> SAMURAI_HELMET, SAMURAI_CHESTPLATE, SAMURAI_SHINGUARD;

	public static final ItemEntry<HostileSummonWand> SUMMON;

	static {


		SAMURAI_HELMET = REGISTRATE.item("samurai_golem_helmet", p -> new MetalGolemArmorItem(p.stacksTo(1),
						ArmorItem.Type.HELMET, 9, 5, GDModelPaths.SAMURAI_HELMET))
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/equipments/" + ctx.getName())))
				.defaultLang().register();
		SAMURAI_CHESTPLATE = REGISTRATE.item("samurai_golem_chestplate", p -> new MetalGolemArmorItem(p.stacksTo(1),
						ArmorItem.Type.CHESTPLATE, 12, 5, GDModelPaths.SAMURAI_CHESTPLATE))
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/equipments/" + ctx.getName())))
				.defaultLang().register();
		SAMURAI_SHINGUARD = REGISTRATE.item("samurai_golem_shinguard", p -> new MetalGolemArmorItem(p.stacksTo(1),
						ArmorItem.Type.LEGGINGS, 7, 5, GDModelPaths.SAMURAI_LEGGING))
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/equipments/" + ctx.getName())))
				.defaultLang().register();

		SUMMON = REGISTRATE.item("hostile_summon_wand", HostileSummonWand::new)
				.properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
				.tag(L2ISTagGen.SELECTABLE)
				.register();
	}

	public static void register() {

	}

}
