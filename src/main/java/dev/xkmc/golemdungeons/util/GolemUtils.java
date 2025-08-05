package dev.xkmc.golemdungeons.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class GolemUtils {

	public static void recursiveAdd(ServerLevel level, Entity e) {
		level.addFreshEntity(e);
		for (var x : e.getPassengers()) {
			x.setPos(e.position());
			recursiveAdd(level, x);
		}
	}

}
