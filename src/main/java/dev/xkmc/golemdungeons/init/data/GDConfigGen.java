package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.compat.cataclysm.data.CataclysmCompatData;
import dev.xkmc.golemdungeons.compat.twilightforest.data.TwilightCompatData;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.IllagerGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.l2core.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.fml.ModList;

import java.util.concurrent.CompletableFuture;

public class GDConfigGen extends ConfigDataProvider {

	public GDConfigGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> pvd) {
		super(generator, pvd, "Golem Spawn Config");
	}

	public void add(ConfigDataProvider.Collector map) {
		FactoryGolemSpawn.add(map);
		IllagerGolemSpawn.add(map);
		PiglinGolemSpawn.add(map);
		SculkGolemSpawn.add(map);
		if (ModList.get().isLoaded(CataDispatch.MODID)) {
			CataclysmCompatData.genSpawn(map);
		}
		if (ModList.get().isLoaded(TFDispatch.MODID)) {
			TwilightCompatData.genSpawn(map);
		}
	}

}
