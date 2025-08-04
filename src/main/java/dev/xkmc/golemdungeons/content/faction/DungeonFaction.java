package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

import java.util.ArrayList;
import java.util.Optional;

public class DungeonFaction extends HostileFaction {

	public DungeonFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean hostileGolemAttacks(LivingEntity e) {
		var list = new ArrayList<Optional<LivingEntity>>();
		list.add(Optional.ofNullable(e.getLastHurtMob()));
		list.add(Optional.ofNullable(e.getLastHurtByMob()));
		if (e instanceof Mob mob) {
			list.add(Optional.ofNullable(mob.getTarget()));
		}
		for (var target : list) {
			if (target.isPresent() && isAlliedTo(target.get())) return true;
		}
		return super.hostileGolemAttacks(e);
	}

	public boolean validAlly(Entity other) {
		if (other instanceof Mob mob) {
			if (mob.getTarget() instanceof AbstractGolemEntity<?, ?> golem) {
				return !golem.isHostile() || !uuid.equals(golem.getOwnerUUID());
			}
		}
		return true;
	}

}
