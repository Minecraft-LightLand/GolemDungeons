package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

public class AbstractGolemSpawn {

	public static ItemStack getWeapon(VanillaGolemWeaponMaterial mat, GolemWeaponType type) {
		return GolemItems.METALGOLEM_WEAPON[type.ordinal()][mat.ordinal()].asStack();
	}

	public static ItemStack tipped(Holder<Potion> potion) {
		var ans = Items.TIPPED_ARROW.getDefaultInstance();
		ans.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
		return ans;
	}

	public static TrialConfig.WaveEntry of(ResourceLocation target, int num) {
		return new TrialConfig.WaveEntry(target, num);
	}

	public static SpawnConfig.GolemMaterialEntry noArm(int weight) {
		return new SpawnConfig.GolemMaterialEntry(weight)
				.ban(GolemItems.GOLEM_ARM.get())
				.ban(GolemItems.HUMANOID_ARMS.get());
	}

	public static SpawnConfig.GolemMaterialEntry atkOnly(int weight) {
		return new SpawnConfig.GolemMaterialEntry(weight)
				.ban(GolemItems.GOLEM_BODY.get())
				.ban(GolemItems.GOLEM_LEGS.get())
				.ban(GolemItems.HUMANOID_BODY.get())
				.ban(GolemItems.HUMANOID_LEGS.get())
				.ban(GolemItems.DOG_LEGS.get());
	}

}
