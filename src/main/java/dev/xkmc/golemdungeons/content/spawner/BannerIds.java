package dev.xkmc.golemdungeons.content.spawner;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class BannerIds {

	public static final Map<UUID, ResourceLocation> MAP = new LinkedHashMap<>();

	public static synchronized void update(GolemTrialBlockEntity e) {
		MAP.put(e.barId, e.trial);
	}

	public static synchronized @Nullable ResourceLocation get(UUID id) {
		return MAP.get(id);
	}

}
