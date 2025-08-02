package dev.xkmc.golemdungeons.content.equipments;

import dev.xkmc.modulargolems.content.config.GolemMaterial;
import dev.xkmc.modulargolems.content.config.GolemMaterialConfig;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.item.equipments.CustomDropGolemWeapon;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MoltenSword extends FlameSword implements CustomDropGolemWeapon {

	public MoltenSword(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	public void dropCustomDeathLoot(AbstractGolemEntity<?, ?> self, MetalGolemEntity attacker, ItemStack stack, DamageSource source) {
		Map<Item, Integer> drop = new HashMap<>();
		for (GolemMaterial mat : self.getMaterials()) {
			Item item = GolemMaterialConfig.get().getCraftIngredient(mat.id()).getItems()[0].getItem();
			drop.compute(item, (e, old) -> (old == null ? 0 : old) + mat.getPart().count / 3);
		}
		drop.forEach((k, v) -> self.spawnAtLocation(new ItemStack(k, v)));
	}

}
