package dev.xkmc.golemdungeons.compat.twilightforest.item;

import dev.xkmc.golemdungeons.content.equipments.FlameSword;
import dev.xkmc.golemdungeons.init.data.GDConfig;

public class GiantFierySword extends FlameSword {

	public GiantFierySword(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	protected Double getDamage() {
		return GDConfig.COMMON.fierySwordDamage.get();
	}

}
