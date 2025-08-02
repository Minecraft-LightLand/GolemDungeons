package dev.xkmc.golemdungeons.content.equipments;

import dev.xkmc.golemdungeons.init.data.GDDamageTypes;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.item.equipments.ExtraAttackGolemWeapon;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FlameSword extends MetalGolemWeaponItem implements ExtraAttackGolemWeapon {

	public FlameSword(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	public boolean repeatAttack(MetalGolemEntity self, Entity target, float v, boolean b) {
		if (target instanceof LivingEntity le) {
			var source = self.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
					.getHolderOrThrow(GDDamageTypes.FLAME);
			return le.hurt(new DamageSource(source, self), v / 4f);
		}
		return false;
	}

}
