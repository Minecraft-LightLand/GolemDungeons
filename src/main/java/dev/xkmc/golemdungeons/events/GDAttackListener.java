package dev.xkmc.golemdungeons.events;

import dev.xkmc.golemdungeons.content.equipments.GolemCustomSourceWeapon;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;

public class GDAttackListener implements AttackListener {

	@Override
	public void onCreateSource(CreateSourceEvent event) {
		if (event.getAttacker() instanceof MetalGolemEntity golem) {
			if (golem.getMainHandItem().getItem() instanceof GolemCustomSourceWeapon weapon) {
				if (event.getResult() != null && event.getResult().toRoot() == L2DamageTypes.MOB_ATTACK) {
					weapon.onModifySource(event, golem, golem.getMainHandItem());
				}
			}
		}
	}

}
