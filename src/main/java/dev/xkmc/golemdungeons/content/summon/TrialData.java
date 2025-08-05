package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.util.GolemUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SerialClass
public class TrialData {

	private static final int WAVE_DELAY = 40, SUMMON_DELAY = 10, COMPLETE_DELAY = 60;

	@SerialClass.SerialField
	private final List<UUID> trialGolems = new ArrayList<>();

	@Nullable
	@SerialClass.SerialField
	private ResourceLocation id;

	@SerialClass.SerialField
	private int wave = 0, entryCount = 0, entryIndex = 0, toSummon = 0, mobIndex = 0;

	@SerialClass.SerialField
	private long nextAction = 0;

	@SerialClass.SerialField
	private float totalHealth = 0;

	@Nullable
	private TrialConfig config;

	public boolean tickTrial(TrialTicker be, ServerLevel level, long time) {
		if (id == null) {
			// When trial is completed, still showing winning info
			if (nextAction <= time && be.isOnGoing())
				be.stop();
			return false;
		}
		if (config == null) config = GolemDungeons.TRIAL.getEntry(id);
		if (config == null) return false; // nothing to raid
		boolean update = false;
		if (entryCount > 0) { // summoning progress
			update = tickSummon(config, be, level, time);
		} else if (trialGolems.isEmpty()) { // wave complete
			if (wave >= config.list.size()) { // no more waves
				be.complete(level, config, time); // drop bonus
				id = null;
				config = null;
				totalHealth = 0;
				nextAction = time + COMPLETE_DELAY;
				return true;
			}
			initWave(config, be, level, time); // init wave
			return true;
		}
		update |= trialGolems.removeIf(e -> {
			var golem = level.getEntity(e);
			if (golem == null) return true;
			return !be.isValidTracked(golem);
		});
		return update;
	}

	private boolean tickSummon(TrialConfig config, TrialTicker be, ServerLevel level, long time) {
		if (time < nextAction) return false;
		var list = config.list.get(wave - 1);
		if (toSummon == 0) {
			entryIndex++;
			if (entryIndex >= list.size()) { // wave complete
				entryCount = 0;
				mobIndex = 0;
				entryIndex = 0;
				return true;
			}
			toSummon = list.get(entryIndex).roll(level.getRandom());
		}
		var entry = list.get(entryIndex);
		if (toSummon > 0) {
			toSummon--;
			var target = GolemDungeons.SPAWN.getEntry(entry.target());
			if (target != null) {
				var e = target.summon(level);
				if (e != null) {
					be.addCost(config.spawnCost, time);
					be.configureEntity(e, mobIndex);
					for (var le : e.getSelfAndPassengers().toList()) {
						if (le instanceof AbstractGolemEntity<?, ?> golem) {
							totalHealth += golem.getMaxHealth();
							trialGolems.add(golem.getUUID());
							be.configureGolem(golem, mobIndex);
						}
					}
					GolemUtils.recursiveAdd(level, e);
					mobIndex++;
					nextAction = time + SUMMON_DELAY;
				}
			}
		}
		return true;
	}

	private void initWave(TrialConfig config, TrialTicker be, ServerLevel level, long time) {
		wave++;
		int ind = wave - 1;
		var entry = config.list.get(ind);
		entryCount = entry.size();
		entryIndex = -1;
		mobIndex = 0;
		totalHealth = 0;
		nextAction = time + WAVE_DELAY;
	}

	public void start(TrialTicker be, long time, ResourceLocation trial, TrialConfig config) {
		trialGolems.clear();
		this.id = trial;
		this.config = config;
		wave = 0;
		entryCount = 0;
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

	public void updateBar(CustomBossEvent bar, ServerLevel level, long time) {
		if (id == null) {
			// victory
			bar.setColor(BossEvent.BossBarColor.GREEN);
			bar.setMax(COMPLETE_DELAY);
			bar.setValue((int) (nextAction - time));
			bar.setName(GDLang.BAR_VICTORY.get());
		} else if (entryIndex < 0) {
			// preparing
			bar.setColor(BossEvent.BossBarColor.YELLOW);
			bar.setMax(WAVE_DELAY);
			bar.setValue(WAVE_DELAY - (int) (nextAction - time));
			bar.setName(GDLang.fromTrial(id).append(GDLang.BAR_WAVE.get(wave)).append(GDLang.BAR_CHARGING.get()));
		} else if (entryCount > 0) {
			// summoning
			bar.setColor(BossEvent.BossBarColor.PURPLE);
			bar.setMax(entryCount);
			bar.setValue(entryIndex);
			bar.setName(GDLang.fromTrial(id).append(GDLang.BAR_WAVE.get(wave)).append(GDLang.BAR_SUMMONING.get()));
		} else if (totalHealth > 0) {
			float hp = 0;
			int count = 0;
			for (var e : trialGolems) {
				var golem = level.getEntity(e);
				if (golem instanceof Mob mob && mob.isAlive()) {
					hp += mob.getHealth();
					count++;
				}
			}
			bar.setColor(BossEvent.BossBarColor.RED);
			bar.setMax((int) totalHealth);
			bar.setValue((int) hp);
			bar.setName(GDLang.fromTrial(id).append(GDLang.BAR_WAVE.get(wave)).append(GDLang.BAR_PROGRESS.get(count)));
		} else {
			bar.setVisible(false);
			return;
		}
		bar.setVisible(true);
	}

}
