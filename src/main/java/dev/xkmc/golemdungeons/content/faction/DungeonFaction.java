package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class DungeonFaction extends HostileFaction {

	public DungeonFaction(ResourceLocation id) {
		super(id);
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
