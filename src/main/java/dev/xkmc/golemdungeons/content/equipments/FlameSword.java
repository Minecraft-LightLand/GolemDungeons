package dev.xkmc.golemdungeons.content.equipments;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDConfig;
import dev.xkmc.golemdungeons.init.data.GDDamageTypes;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.modulargolems.content.config.GolemMaterial;
import dev.xkmc.modulargolems.content.config.GolemMaterialConfig;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.item.equipments.CustomDropGolemWeapon;
import dev.xkmc.modulargolems.content.item.equipments.ExtraAttackGolemWeapon;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlameSword extends MetalGolemWeaponItem implements ExtraAttackGolemWeapon, CustomDropGolemWeapon {

	public FlameSword(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	protected Double getDamage() {
		return GDConfig.SERVER.flameSwordDamage.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
		int perc = (int) Math.round(100 * getDamage());
		list.add(GDLang.FLAME_SWORD_ATK.get(Component.literal(perc + "%")));
		list.add(GDLang.FLAME_SWORD_LOOT.get());
		super.appendHoverText(stack, ctx, list, flag);
	}

	@Override
	public boolean dropCustomDeathLoot(AbstractGolemEntity<?, ?> self, MetalGolemEntity attacker, ItemStack stack, DamageSource source) {
		if (attacker.isHostile()) return false;
		Map<Item, Integer> drop = new HashMap<>();
		for (GolemMaterial mat : self.getMaterials()) {
			Item item = GolemMaterialConfig.get().getCraftIngredient(mat.id()).getItems()[0].getItem();
			int count = (int) Math.round(mat.getPart().count * GDConfig.SERVER.flameSwordLoot.get());
			drop.compute(item, (e, old) -> (old == null ? 0 : old) + count);
		}
		drop.forEach((k, v) -> self.spawnAtLocation(new ItemStack(k, v)));
		return true;
	}

	@Override
	public boolean repeatAttack(MetalGolemEntity self, Entity target, float v, boolean b) {
		if (target instanceof LivingEntity le) {
			var source = self.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
					.getHolderOrThrow(GDDamageTypes.FLAME);
			return le.hurt(new DamageSource(source, self), v * getDamage().floatValue());
		}
		return false;
	}


	public static ItemEntry<FlameSword> buildItem(String id, VanillaGolemWeaponMaterial material) {
		return GolemDungeons.REGISTRATE.item(id, p -> new FlameSword(material.modify(p.stacksTo(1)),
						material.getDamage(), 0, 1, 2))
				.model((ctx, pvd) -> pvd.getBuilder(ctx.getName())
						.guiLight(BlockModel.GuiLight.FRONT)
						.customLoader(SeparateTransformsModelBuilder::begin)
						.base(new ItemModelBuilder(null, pvd.existingFileHelper)
								.parent(new ModelFile.UncheckedModelFile(ModularGolems.loc(GolemWeaponType.SWORD.model)))
								.texture("layer0", pvd.modLoc("item/equipments/" + ctx.getName())))
						.perspective(ItemDisplayContext.GUI, new ItemModelBuilder(null, pvd.existingFileHelper)
								.parent(pvd.getExistingFile(pvd.mcLoc("item/generated")))
								.texture("layer0", pvd.modLoc("item/equipments/" + ctx.getName() + "_icon")))
				).defaultLang().register();
	}

}
