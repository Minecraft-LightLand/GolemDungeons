package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.golemdungeons.compat.twilightforest.TwilightGDRegistry;
import dev.xkmc.golemdungeons.content.client.GDModelPaths;
import dev.xkmc.golemdungeons.content.equipments.AncientForge;
import dev.xkmc.golemdungeons.content.equipments.FlameSword;
import dev.xkmc.golemdungeons.content.equipments.SculkScythe;
import dev.xkmc.golemdungeons.content.item.HostileSummonWand;
import dev.xkmc.golemdungeons.content.item.StructureEye;
import dev.xkmc.golemdungeons.content.item.TrialMedal;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlock;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialRenderer;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.init.data.structure.GDStructureGen;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2core.init.reg.simple.DCReg;
import dev.xkmc.l2core.init.reg.simple.DCVal;
import dev.xkmc.l2itemselector.init.data.L2ISTagGen;
import dev.xkmc.l2modularblock.core.BlockTemplates;
import dev.xkmc.l2modularblock.core.DelegateBlock;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemArmorItem;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.ModList;

import static dev.xkmc.golemdungeons.init.GolemDungeons.REGISTRATE;

public class GDItems {

	public static final SimpleEntry<CreativeModeTab> TAB = REGISTRATE.buildL2CreativeTab(
			"golem_dungeons", "Golem Dungeons", b -> b.icon(GDItems.SUMMON::asStack));

	public static final ItemEntry<StructureEye> EYE_OF_ANCIENT_FACTORY;
	public static final ItemEntry<StructureEye> EYE_OF_CRIMSON_FACTORY;
	public static final ItemEntry<StructureEye> EYE_OF_SCULK_FACTORY;

	public static final ItemEntry<TrialMedal> TRIAL_MEDAL;
	public static final ItemEntry<Item> MEDAL_OF_CONQUEROR, FLAME_CORE;

	public static final ItemEntry<MetalGolemArmorItem> SAMURAI_HELMET, SAMURAI_CHESTPLATE, SAMURAI_SHINGUARD;

	public static final ItemEntry<AncientForge> ANCIENT_FORGE;
	public static final ItemEntry<FlameSword> FLAME_SWORD;
	public static final ItemEntry<SculkScythe> SCULK_SCYTHE;

	public static final ItemEntry<HostileSummonWand> SUMMON;

	public static final BlockEntry<DelegateBlock> SPAWNER;
	public static final BlockEntityEntry<GolemTrialBlockEntity> BE_SPAWNER;

	private static final DCReg DC = DCReg.of(GolemDungeons.REG);
	public static final DCVal<Unit> MEDAL = DC.unit("medal");
	public static final DCVal<String> MODID = DC.str("modid");
	public static final DCVal<ResourceLocation> TRIAL = DC.loc("trial");

	static {

		EYE_OF_ANCIENT_FACTORY = REGISTRATE.item("eye_of_ancient_factory", p ->
						new StructureEye(p, GDStructureGen.ABANDONED_FACTORY.asTag(), GDLang.LOCATE_ABANDONED::get))
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16))
				.lang("Eye of Ancient Factory")
				.register();

		EYE_OF_CRIMSON_FACTORY = REGISTRATE.item("eye_of_crimson_factory", p ->
						new StructureEye(p, GDStructureGen.PIGLIN_FACTORY.asTag(), GDLang.LOCATE_CRIMSON::get))
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16).fireResistant())
				.lang("Eye of Crimson Factory")
				.register();

		EYE_OF_SCULK_FACTORY = REGISTRATE.item("eye_of_sculk_factory", p ->
						new StructureEye(p, GDStructureGen.SCULK_FACTORY.asTag(), GDLang.LOCATE_SCULK::get))
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16).fireResistant())
				.lang("Eye of Sculk Factory")
				.register();

		TRIAL_MEDAL = REGISTRATE.item("trial_medal", TrialMedal::new)
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16).fireResistant())
				.register();

		MEDAL_OF_CONQUEROR = REGISTRATE.item("medal_of_conqueror", Item::new)
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16).fireResistant())
				.register();

		FLAME_CORE = REGISTRATE.item("flame_core", Item::new)
				.properties(p -> p.rarity(Rarity.RARE).stacksTo(16).fireResistant())
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
				.transform(e -> e.tab(TAB.getKey(), x -> e.getEntry().fillItemCategory(x)))
				.tag(L2ISTagGen.SELECTABLE)
				.register();

		SPAWNER = REGISTRATE.block("golem_spawner", p -> DelegateBlock.newBaseBlock(p,
						BlockTemplates.HORIZONTAL, new GolemTrialBlock(), GolemTrialBlock.TE))
				.properties(p -> p.noOcclusion().strength(50, 1200).requiresCorrectToolForDrops().noLootTable())
				.blockstate(GolemTrialBlock::buildStates)
				.simpleItem()
				.tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)
				.register();

		BE_SPAWNER = REGISTRATE.blockEntity("golem_spawner", GolemTrialBlockEntity::new)
				.validBlock(SPAWNER)
				.renderer(() -> GolemTrialRenderer::new)
				.register();

		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			TwilightGDRegistry.register();
		}

	}

	public static void register() {

	}

}
