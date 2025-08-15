package dev.xkmc.golemdungeons.compat.twilightforest;

import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.resources.ResourceLocation;

public class TwilightFactions {

	public static final HostileFaction DEFAULT = HostileGolemRegistry.register(
			new TwilightFaction(new ResourceLocation(TFDispatch.MODID, "default")));

	public static void register() {
	}

}
