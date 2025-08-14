package dev.xkmc.golemdungeons.compat.twilightforest.data;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;

public class TwilightCompatData {

	public static void genSpawn(ConfigDataProvider.Collector map) {
		TwilightGolemSpawn.add(map);
	}

}
