package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class AbstractGolemSpawn {

	public static ItemStack getWeapon(VanillaGolemWeaponMaterial mat, GolemWeaponType type) {
		return GolemItems.METALGOLEM_WEAPON[type.ordinal()][mat.ordinal()].asStack();
	}

	public static ItemStack tipped(Potion potion) {
		return PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), potion);
	}

}
