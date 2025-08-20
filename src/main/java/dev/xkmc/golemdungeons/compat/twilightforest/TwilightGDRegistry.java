package dev.xkmc.golemdungeons.compat.twilightforest;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.golemdungeons.compat.twilightforest.item.GiantFierySword;
import dev.xkmc.golemdungeons.compat.twilightforest.item.GiantKnightmetalSword;
import dev.xkmc.golemdungeons.compat.twilightforest.item.TFGiantTier;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.reg.GDModifiers;
import dev.xkmc.l2core.init.reg.simple.Val;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFCompatRegistry;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import dev.xkmc.modulargolems.content.item.upgrade.SimpleUpgradeItem;
import dev.xkmc.modulargolems.content.modifier.base.AttributeGolemModifier;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import twilightforest.item.GiantPickItem;

public class TwilightGDRegistry {

	public static final ItemEntry<Item> GIANT_IRONWOOD_INGOT, GIANT_KNIGHT_INGOT, GIANT_FIERY_INGOT;
	public static final ItemEntry<GiantPickItem> GIANT_IRONWOOD_PICKAXE, GIANT_KNIGHTMETAL_PICKAXE, GIANT_FIERY_PICKAXE;
	public static final ItemEntry<MetalGolemWeaponItem> GIANT_IRONWOOD_SWORD;
	public static final ItemEntry<GiantKnightmetalSword> GIANT_KNIGHT_SWORD;
	public static final ItemEntry<GiantFierySword> GIANT_FIERY_SWORD;

	public static final Val<AttributeGolemModifier> GIANT;
	public static final ItemEntry<SimpleUpgradeItem> ITEM_GIANT;

	static {
		 TagKey<Item> giant = ItemTags.create(ModularGolems.loc("giant_items"));

		{
			GIANT_IRONWOOD_INGOT = GolemDungeons.REGISTRATE.item("giant_ironwood_ingot", Item::new)
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("ironwood_ingot")))
					.asOptional().tag(giant)
					.register();

			GIANT_KNIGHT_INGOT = GolemDungeons.REGISTRATE.item("giant_knightmetal_ingot", Item::new)
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("knightmetal_ingot")))
					.asOptional().tag(giant)
					.register();

			GIANT_FIERY_INGOT = GolemDungeons.REGISTRATE.item("giant_fiery_ingot", Item::new)
					.properties(p -> p.fireResistant())
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("fiery_ingot")))
					.asOptional().tag(giant)
					.register();

			GIANT_IRONWOOD_PICKAXE = GolemDungeons.REGISTRATE.item("giant_ironwood_pickaxe", p ->
							new GiantPickItem(TFGiantTier.IRONWOOD, p))
					.model((ctx, pvd) ->
							giantPickModel(ctx, pvd, loc("ironwood_pickaxe")))
					.asOptional().tag(giant, ItemTags.PICKAXES)
					.register();

			GIANT_KNIGHTMETAL_PICKAXE = GolemDungeons.REGISTRATE.item("giant_knightmetal_pickaxe", p ->
							new GiantPickItem(TFGiantTier.KNIGHTMETAL, p))
					.model((ctx, pvd) ->
							giantPickModel(ctx, pvd, loc("knightmetal_pickaxe")))
					.asOptional().tag(giant, ItemTags.PICKAXES)
					.register();

			GIANT_FIERY_PICKAXE = GolemDungeons.REGISTRATE.item("giant_fiery_pickaxe", p ->
							new GiantPickItem(TFGiantTier.FIERY, p))
					.properties(p -> p.fireResistant())
					.model((ctx, pvd) ->
							giantPickModel(ctx, pvd, loc("fiery_pickaxe")))
					.asOptional().tag(giant, ItemTags.PICKAXES)
					.register();

			GIANT_IRONWOOD_SWORD = GolemDungeons.REGISTRATE.item("giant_ironwood_sword", p ->
							new MetalGolemWeaponItem(p, 10, 0, 3, 3))
					.properties(p -> p.stacksTo(1))
					.model((ctx, pvd) ->
							giantSwordModel(ctx, pvd, loc("ironwood_sword")))
					.asOptional().tag(giant, ItemTags.SWORD_ENCHANTABLE, ItemTags.SHARP_WEAPON_ENCHANTABLE)
					.register();

			GIANT_KNIGHT_SWORD = GolemDungeons.REGISTRATE.item("giant_knightmetal_sword", p ->
							new GiantKnightmetalSword(p, 10, 0.3, 3, 3))
					.properties(p -> p.stacksTo(1))
					.model((ctx, pvd) ->
							giantSwordModel(ctx, pvd, loc("knightmetal_sword")))
					.asOptional().tag(giant, ItemTags.SWORD_ENCHANTABLE, ItemTags.SHARP_WEAPON_ENCHANTABLE)
					.register();

			GIANT_FIERY_SWORD = GolemDungeons.REGISTRATE.item("giant_fiery_sword", p ->
							new GiantFierySword(p, 10, 0.5, 3, 3))
					.properties(p -> p.stacksTo(1).fireResistant())
					.model((ctx, pvd) ->
							giantSwordModel(ctx, pvd, loc("fiery_sword")))
					.asOptional().tag(giant, ItemTags.SWORD_ENCHANTABLE, ItemTags.SHARP_WEAPON_ENCHANTABLE)
					.register();
		}

		{
			GIANT = GDModifiers.reg("giant", () -> new AttributeGolemModifier(1,
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_SPEED, () -> 1),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_HEALTH_P, () -> 3),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_SIZE_P, () -> 3),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_RANGE, () -> 6)
			), null);

			ITEM_GIANT = GDModifiers.regUpgradeImpl("giant_upgrade", () -> GIANT, 1, false, GolemDungeons.MODID).register();

		}

	}


	private static <T extends Item> void giantViewModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id, int u1, int v1) {
		pvd.getBuilder("item/" + ctx.getName()).customLoader(SeparateTransformsModelBuilder::begin)
				.base(new ItemModelBuilder(null, pvd.existingFileHelper)
						.parent(new ModelFile.UncheckedModelFile(loc("item/giant_tool_base")))
						.texture("layer0", id.withPrefix("item/")))
				.perspective(ItemDisplayContext.GUI, new ItemModelBuilder(null, pvd.existingFileHelper)
						.element().from(0, 0, 0).to(16, 16, 0)
						.face(Direction.SOUTH).uvs(u1, v1, u1 + 8, v1 + 8).texture("#all").end().end()
						.texture("all", id.withPrefix("item/")))
				.end()
				.parent(new ModelFile.UncheckedModelFile(id.withPrefix("item/")));
	}

	private static <T extends Item> void giantIngotModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id) {
		giantViewModel(ctx, pvd, id, 8, 2);
	}


	private static <T extends Item> void giantPickModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id) {
		giantViewModel(ctx, pvd, id, 7, 2);
	}

	private static <T extends Item> void giantSwordModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id) {
		giantViewModel(ctx, pvd, id, 3, 5);
	}

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(TFDispatch.MODID, id);
	}

	public static void register() {

	}

}
