package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.compat.cataclysm.data.CataclysmCompatData;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.IllagerGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.ModList;

public class GDConfigGen extends ConfigDataProvider {

	public GDConfigGen(DataGenerator generator) {
		super(generator, "Golem Spawn Config");
	}

	public void add(ConfigDataProvider.Collector map) {
		FactoryGolemSpawn.add(map);
		IllagerGolemSpawn.add(map);
		PiglinGolemSpawn.add(map);
		SculkGolemSpawn.add(map);
		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			CataclysmCompatData.genSpawn(map);
		}
	}

}
