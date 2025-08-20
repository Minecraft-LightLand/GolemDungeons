package dev.xkmc.golemdungeons.events;

import dev.xkmc.golemdungeons.content.equipments.GolemCustomSourceWeapon;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import net.minecraft.world.entity.LivingEntity;

public class GDAttackListener implements AttackListener {

	@Override
	public boolean onAttack(DamageData.Attack cache) {
		var attacker = cache.getSource().getEntity();
		if (!(attacker instanceof LivingEntity le)) return false;
		if (cache.getTarget() instanceof AbstractGolemEntity<?, ?> golem) {
			if (golem.isAlliedTo(le)) {
				return true;
			}
		}
		return false;
	}

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
