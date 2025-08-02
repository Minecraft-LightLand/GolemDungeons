package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.IllagerGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.minecraft.data.DataGenerator;

public class GDConfigGen extends ConfigDataProvider {

	public GDConfigGen(DataGenerator generator) {
		super(generator, "Golem Spawn Config");
	}

	public void add(ConfigDataProvider.Collector map) {
		FactoryGolemSpawn.add(map);
		IllagerGolemSpawn.add(map);
		PiglinGolemSpawn.add(map);
		SculkGolemSpawn.add(map);
	}

}
