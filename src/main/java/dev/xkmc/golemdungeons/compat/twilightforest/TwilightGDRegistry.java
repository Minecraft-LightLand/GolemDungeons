package dev.xkmc.golemdungeons.compat.twilightforest;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.xkmc.golemdungeons.compat.twilightforest.item.GiantFierySword;
import dev.xkmc.golemdungeons.compat.twilightforest.item.GiantKnightmetalSword;
import dev.xkmc.golemdungeons.compat.twilightforest.item.TFGiantTier;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import dev.xkmc.modulargolems.content.item.upgrade.SimpleUpgradeItem;
import dev.xkmc.modulargolems.content.modifier.base.AttributeGolemModifier;
import dev.xkmc.modulargolems.content.modifier.base.GolemModifier;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import twilightforest.item.GiantPickItem;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TwilightGDRegistry {

	public static final ItemEntry<Item> GIANT_IRONWOOD_INGOT, GIANT_KNIGHT_INGOT, GIANT_FIERY_INGOT;
	public static final ItemEntry<GiantPickItem> GIANT_IRONWOOD_PICKAXE, GIANT_KNIGHTMETAL_PICKAXE, GIANT_FIERY_PICKAXE;
	public static final ItemEntry<MetalGolemWeaponItem> GIANT_IRONWOOD_SWORD;
	public static final ItemEntry<GiantKnightmetalSword> GIANT_KNIGHT_SWORD;
	public static final ItemEntry<GiantFierySword> GIANT_FIERY_SWORD;

	public static final RegistryEntry<AttributeGolemModifier> GIANT;
	public static final ItemEntry<SimpleUpgradeItem> ITEM_GIANT;

	static {
		{
			GIANT_IRONWOOD_INGOT = GolemDungeons.REGISTRATE.item("giant_ironwood_ingot", Item::new)
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("ironwood_ingot")))
					.register();

			GIANT_KNIGHT_INGOT = GolemDungeons.REGISTRATE.item("giant_knightmetal_ingot", Item::new)
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("knightmetal_ingot")))
					.register();

			GIANT_FIERY_INGOT = GolemDungeons.REGISTRATE.item("giant_fiery_ingot", Item::new)
					.properties(p -> p.fireResistant())
					.model((ctx, pvd) ->
							giantIngotModel(ctx, pvd, loc("fiery_ingot")))
					.register();

	GIANT_IRONWOOD_PICKAXE = GolemDungeons.REGISTRATE.item("giant_ironwood_pickaxe", p ->
						new GiantPickItem(TFGiantTier.IRONWOOD, p))
				.model((ctx, pvd) ->
						giantToolModel(ctx, pvd, loc("ironwood_pickaxe")))
				.register();

		GIANT_KNIGHTMETAL_PICKAXE = GolemDungeons.REGISTRATE.item("giant_knightmetal_pickaxe", p ->
						new GiantPickItem(TFGiantTier.KNIGHTMETAL, p))
				.model((ctx, pvd) ->
						giantToolModel(ctx, pvd, loc("knightmetal_pickaxe")))
				.register();

		GIANT_FIERY_PICKAXE = GolemDungeons.REGISTRATE.item("giant_fiery_pickaxe", p ->
						new GiantPickItem(TFGiantTier.FIERY, p))
				.properties(p -> p.fireResistant())
				.model((ctx, pvd) ->
						giantToolModel(ctx, pvd, loc("fiery_pickaxe")))
				.register();		GIANT_IRONWOOD_SWORD = GolemDungeons.REGISTRATE.item("giant_ironwood_sword", p ->
							new MetalGolemWeaponItem(p, 10, 0, 3, 3))
					.properties(p -> p.stacksTo(1))
					.model((ctx, pvd) ->
							giantToolModel(ctx, pvd, loc("ironwood_sword")))
					.register();

			GIANT_KNIGHT_SWORD = GolemDungeons.REGISTRATE.item("giant_knightmetal_sword", p ->
							new GiantKnightmetalSword(p, 10, 0.3, 3, 3))
					.properties(p -> p.stacksTo(1))
					.model((ctx, pvd) ->
							giantToolModel(ctx, pvd, loc("knightmetal_sword")))
					.register();

			GIANT_FIERY_SWORD = GolemDungeons.REGISTRATE.item("giant_fiery_sword", p ->
							new GiantFierySword(p, 10, 0.5, 3, 3))
					.properties(p -> p.stacksTo(1).fireResistant())
					.model((ctx, pvd) ->
							giantToolModel(ctx, pvd, loc("fiery_sword")))
					.register();
		}

		{
			GIANT = reg("giant", () -> new AttributeGolemModifier(1,
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_SPEED, () -> 1),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_HEALTH_P, () -> 1),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_SIZE, () -> 5),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_RANGE, () -> 3),
					new AttributeGolemModifier.AttrEntry(GolemTypes.STAT_JUMP, () -> 2)
			), null);

			ITEM_GIANT = regUpgradeImpl("giant_upgrade", () -> GIANT, 1, false, GolemDungeons.MODID).register();

		}

	}

	public static <T extends GolemModifier> RegistryEntry<T> reg(String id, NonNullSupplier<T> sup, @Nullable String def) {
		Mutable<RegistryEntry<T>> holder = new MutableObject<>();
		L2Registrate.GenericBuilder<GolemModifier, T> ans = GolemDungeons.REGISTRATE.generic(GolemTypes.MODIFIERS, id, sup).defaultLang();
		if (def != null) {
			ans.addMiscData(ProviderType.LANG, (pvd) -> pvd.add(holder.getValue().get().getDescriptionId() + ".desc", def));
		}
		RegistryEntry<T> result = ans.register();
		holder.setValue(result);
		return result;
	}

	private static <T extends Item> void giantIngotModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id) {
		pvd.getBuilder("item/" + ctx.getName()).customLoader(SeparateTransformsModelBuilder::begin)
				.base(new ItemModelBuilder(null, pvd.existingFileHelper)
						.parent(new ModelFile.UncheckedModelFile(loc("item/giant_tool_base")))
						.texture("layer0", id.withPrefix("item/")))
				.perspective(ItemDisplayContext.GUI, new ItemModelBuilder(null, pvd.existingFileHelper)
						.element().from(0, 0, 0).to(16, 16, 0)
						.face(Direction.SOUTH).uvs(8, 2, 16, 10).texture("#all").end().end()
						.texture("all", id.withPrefix("item/")))
				.end()
				.parent(new ModelFile.UncheckedModelFile(id.withPrefix("item/")));
	}

	private static <T extends Item> void giantToolModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd, ResourceLocation id) {
		pvd.getBuilder("item/" + ctx.getName()).customLoader(SeparateTransformsModelBuilder::begin)
				.base(new ItemModelBuilder(null, pvd.existingFileHelper)
						.parent(new ModelFile.UncheckedModelFile(loc("item/giant_tool_base")))
						.texture("layer0", id.withPrefix("item/")))
				.perspective(ItemDisplayContext.GUI, new ItemModelBuilder(null, pvd.existingFileHelper)
						.element().from(0, 0, 0).to(16, 16, 0)
						.face(Direction.SOUTH).uvs(3, 5, 11, 13).texture("#all").end().end()
						.texture("all", id.withPrefix("item/")))
				.end()
				.parent(new ModelFile.UncheckedModelFile(id.withPrefix("item/")));
	}


	private static ItemBuilder<SimpleUpgradeItem, L2Registrate> regUpgradeImpl(String id, Supplier<RegistryEntry<? extends GolemModifier>> mod, int level, boolean foil, String modid) {
		return GolemDungeons.REGISTRATE.item(id, (p) -> new SimpleUpgradeItem(p, mod.get()::get, level, foil))
				.model((ctx, pvd) ->
						pvd.generated(ctx, new ResourceLocation(modid, "item/upgrades/" + id)));
	}


	public static ResourceLocation loc(String id) {
		return new ResourceLocation(TFDispatch.MODID, id);
	}

	public static void register() {

	}

}
