package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.golemdungeons.content.client.GDModelPaths;
import dev.xkmc.golemdungeons.content.equipments.AncientForge;
import dev.xkmc.golemdungeons.content.equipments.FlameSword;
import dev.xkmc.golemdungeons.content.equipments.SculkScythe;
import dev.xkmc.golemdungeons.content.item.HostileSummonWand;
import dev.xkmc.golemdungeons.content.item.TrialMedal;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlock;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialRenderer;
import dev.xkmc.l2itemselector.init.data.L2ISTagGen;
import dev.xkmc.l2modularblock.BlockProxy;
import dev.xkmc.l2modularblock.DelegateBlock;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemArmorItem;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;

import static dev.xkmc.golemdungeons.init.GolemDungeons.REGISTRATE;

public class GDItems {

	public static final RegistryEntry<CreativeModeTab> TAB = REGISTRATE.buildL2CreativeTab(
			"golem_dungeons", "Golem Dungeons", b -> b.icon(GDItems.SUMMON::asStack));

	public static final ItemEntry<TrialMedal> TRIAL_MEDAL;

	public static final ItemEntry<MetalGolemArmorItem> SAMURAI_HELMET, SAMURAI_CHESTPLATE, SAMURAI_SHINGUARD;

	public static final ItemEntry<AncientForge> ANCIENT_FORGE;
	public static final ItemEntry<FlameSword> FLAME_SWORD;
	public static final ItemEntry<SculkScythe> SCULK_SCYTHE;

	public static final ItemEntry<HostileSummonWand> SUMMON;

	public static final BlockEntry<DelegateBlock> SPAWNER;
	public static final BlockEntityEntry<GolemTrialBlockEntity> BE_SPAWNER;

	static {

		TRIAL_MEDAL = REGISTRATE.item("trial_medal", TrialMedal::new)
				.properties(p -> p.rarity(Rarity.EPIC).stacksTo(16).fireResistant())
				.register();

		ANCIENT_FORGE = AncientForge.buildItem("ancient_forge", VanillaGolemWeaponMaterial.IRON);
		FLAME_SWORD = FlameSword.buildItem("flame_sword", VanillaGolemWeaponMaterial.NETHERITE);
		SCULK_SCYTHE = SculkScythe.buildItem("sculk_golem_scythe", VanillaGolemWeaponMaterial.NETHERITE);

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

		SPAWNER = REGISTRATE.block("golem_spawner", p -> DelegateBlock.newBaseBlock(p,
						BlockProxy.HORIZONTAL, new GolemTrialBlock(), GolemTrialBlock.TE))
				.properties(p -> p.noOcclusion().strength(50, 1200).requiresCorrectToolForDrops().noLootTable())
				.blockstate(GolemTrialBlock::buildStates)
				.simpleItem()
				.tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)
				.register();

		BE_SPAWNER = REGISTRATE.blockEntity("golem_spawner", GolemTrialBlockEntity::new)
				.validBlock(SPAWNER)
				.renderer(() -> GolemTrialRenderer::new)
				.register();
	}

	public static void register() {

	}

}
