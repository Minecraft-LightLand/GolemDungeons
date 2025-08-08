package dev.xkmc.golemdungeons.compat.cataclysm;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.resources.ResourceLocation;

public class CataclysmFactions {

	public static final HostileFaction HARBINGER = HostileGolemRegistry.register(
			new HarbingerFaction(new ResourceLocation(CataDispatch.MODID, "harbinger")));

	public static final HostileFaction MONSTROSITY = HostileGolemRegistry.register(
			new MonstrosityFaction(new ResourceLocation(CataDispatch.MODID, "monstrosity")));

	public static void register() {

	}

	public static void genSpawn(ConfigDataProvider.Collector map) {
		HarbingerGolemSpawn.add(map);
		MonstrosityGolemSpawn.add(map);
	}

}
