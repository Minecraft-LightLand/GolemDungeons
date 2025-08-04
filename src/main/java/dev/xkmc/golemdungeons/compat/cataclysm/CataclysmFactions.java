package dev.xkmc.golemdungeons.compat.cataclysm;

import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.resources.ResourceLocation;

public class CataclysmFactions {

	public static final HostileFaction HARBINGER = HostileGolemRegistry.register(
			new HarbingerFaction(new ResourceLocation(CataDispatch.MODID, "harbinger")));

	public static void register() {

	}


}
