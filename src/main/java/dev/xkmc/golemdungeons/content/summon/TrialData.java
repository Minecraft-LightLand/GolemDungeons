package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.content.config.RaidConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SerialClass
public class TrialData {

	@SerialClass.SerialField
	private final List<UUID> trialGolems = new ArrayList<>();

	@Nullable
	@SerialClass.SerialField
	private ResourceLocation id;

	@SerialClass.SerialField
	private int wave = 0;

	@SerialClass.SerialField
	private float totalHealth = 0;

	@Nullable
	private RaidConfig config;

	public boolean tickTrial(TrialTicker be, ServerLevel level, long time) {
		if (!trialGolems.isEmpty() || id == null) return false;
		if (config == null) config = GolemDungeons.RAID.getEntry(id);
		if (config == null) return false;
		if (trialGolems.isEmpty()) {
			spawn(be, level, time);
			return true;
		}
		for (var e : trialGolems) {
			var golem = level.getEntity(e);
			if (golem != null) golem.discard();
		}
		return false;
	}

	private void spawn(TrialTicker be, ServerLevel level, long time) {
	}

	public void start(TrialTicker be, long time, ResourceLocation trial, RaidConfig config) {
		trialGolems.clear();
		this.id = trial;
		this.config = config;
		wave = 0;
	}

	public void stop(ServerLevel level, TrialTicker be) {
		for (var e : trialGolems) {
			var golem = level.getEntity(e);
			if (golem != null) golem.discard();
		}
		trialGolems.clear();
		id = null;
		config = null;
		totalHealth = 0;
	}

}
