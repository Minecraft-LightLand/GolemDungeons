package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public interface TrialTicker {

	void addCost(int cost, long time);

	void stop();

	void configureEntity(LivingEntity e, int index);

	boolean isValidTracked(Entity golem);

	void complete(ServerLevel level, long time);

	boolean isOnGoing();

	void configureGolem(AbstractGolemEntity<?,?> golem, int mobIndex);

}
