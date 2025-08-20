package dev.xkmc.golemdungeons.init.reg;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.xkmc.golemdungeons.content.modifier.ReforgeModifier;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.simple.Val;
import dev.xkmc.modulargolems.content.item.upgrade.SimpleUpgradeItem;
import dev.xkmc.modulargolems.content.modifier.base.GolemModifier;
import dev.xkmc.modulargolems.content.modifier.base.PotionDefenseModifier;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.data.MGTagGen;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class GDModifiers {

	public static final Val<PotionDefenseModifier> RESISTANCE;
	public static final Val<ReforgeModifier> REFORGE;

	public static final ItemEntry<SimpleUpgradeItem> ITEM_RESISTANCE, ITEM_REFORGE;

	static {
		RESISTANCE = reg("resistance", () -> new PotionDefenseModifier(3, () -> MobEffects.DAMAGE_RESISTANCE), null);
		REFORGE = reg("reforge", ReforgeModifier::new, "Reforge: Consumes body material to repair itself at the cost of max health. Consumption be restored with ingot.");

		ITEM_RESISTANCE = regUpgradeImpl("resistance_upgrade", () -> RESISTANCE, 1, false, GolemDungeons.MODID).tag(MGTagGen.POTION_UPGRADES).register();
		ITEM_REFORGE = regUpgradeImpl("reforge_upgrade", () -> REFORGE, 1, false, GolemDungeons.MODID).tag(MGTagGen.BLUE_UPGRADES).register();
	}

	public static <T extends GolemModifier> Val<T> reg(String id, NonNullSupplier<T> sup, @Nullable String def) {
		Mutable<Val<T>> holder = new MutableObject<>();
		L2Registrate.GenericBuilder<GolemModifier, T> ans = GolemDungeons.REGISTRATE.generic(GolemTypes.MODIFIERS, id, sup).defaultLang();
		if (def != null) {
			ans.addMiscData(ProviderType.LANG, (pvd) -> pvd.add(holder.getValue().get().getDescriptionId() + ".desc", def));
		}
		Val<T> result = new Val.Registrate<>(ans.register());
		holder.setValue(result);
		return result;
	}

	public static ItemBuilder<SimpleUpgradeItem, L2Registrate> regUpgradeImpl(String id, Supplier<Val<? extends GolemModifier>> mod, int level, boolean foil, String modid) {
		return GolemDungeons.REGISTRATE.item(id, (p) -> new SimpleUpgradeItem(p, mod.get()::get, level, foil))
				.model((ctx, pvd) -> pvd.generated(ctx, ResourceLocation.fromNamespaceAndPath(modid, "item/upgrades/" + id))
						.override().predicate(ModularGolems.loc("blue_arrow"), 0.5f)
						.model(pvd.getBuilder(pvd.name(ctx) + "_purple")
								.parent(new ModelFile.UncheckedModelFile("item/generated"))
								.texture("layer0", ResourceLocation.fromNamespaceAndPath(modid, "item/upgrades/" + id))
								.texture("layer1", ModularGolems.loc("item/purple_arrow")))
						.end().override().predicate(ModularGolems.loc("blue_arrow"), 1)
						.model(pvd.getBuilder(pvd.name(ctx) + "_blue")
								.parent(new ModelFile.UncheckedModelFile("item/generated"))
								.texture("layer0", ResourceLocation.fromNamespaceAndPath(modid, "item/upgrades/" + id))
								.texture("layer1", ModularGolems.loc("item/blue_arrow")))
						.end())
				.tab(GolemItems.UPGRADES.getKey());
	}

	public static void register() {

	}

}
