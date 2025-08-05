package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.l2library.base.tile.BaseBlockEntity;
import dev.xkmc.l2modularblock.tile_api.TickableBlockEntity;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static dev.xkmc.golemdungeons.content.summon.GolemTrialBlock.STATE;
import static dev.xkmc.golemdungeons.content.summon.GolemTrialBlock.State.*;

@SerialClass
public class GolemTrialBlockEntity extends BaseBlockEntity implements TickableBlockEntity, TrialTicker {

	@SerialClass.SerialField
	private final List<BlockPos> targets = new ArrayList<>();

	@SerialClass.SerialField
	private final List<UUID> trialPlayer = new ArrayList<>();

	@SerialClass.SerialField
	private final TrialData data = new TrialData();

	@Nullable
	@SerialClass.SerialField
	private ResourceLocation trial = null;

	@SerialClass.SerialField
	private long lastCost, lastTime;

	@Nullable
	private CustomBossEvent bar;

	public GolemTrialBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void tick() {
		if (!(level instanceof ServerLevel sl)) return;
		long time = level.getGameTime();
		if (!trialPlayer.isEmpty()) {
			if (trial == null) {
				stop();
				return;
			}
			boolean change = trialPlayer.removeIf(e -> {
				var player = level.getPlayerByUUID(e);
				return player == null || player.isSpectator() || !isValidTracked(player);
			});
			if (change) {
				setChanged();
			}
			if (trialPlayer.isEmpty()) {
				stop();
				return;
			}
			if (bar == null) {
				bar = new CustomBossEvent(trial, GDLang.fromTrial(trial));
				change = true;
			}
			if (change) {
				List<ServerPlayer> players = new ArrayList<>();
				for (var e : trialPlayer) {
					if (level.getPlayerByUUID(e) instanceof ServerPlayer sp) {
						players.add(sp);
					}
				}
				bar.setPlayers(players);
			}
		}
		if (trial == null) return;
		if (trialPlayer.isEmpty() && time % 20 != 0) return;
		if (getBlockState().getValue(STATE) == CHARGING) {
			if (lastCost + lastTime < time)
				level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, IDLE));
		}
		var config = GolemDungeons.TRIAL.getEntry(trial);
		if (config == null) return;
		if (trialPlayer.isEmpty()) {
			if (time - lastTime < lastCost) return;
			List<ServerPlayer> players = new ArrayList<>();
			for (var pl : level.players()) {
				if (!isValidPlayer(pl)) continue;
				if (pl instanceof ServerPlayer sp) {
					players.add(sp);
				}
			}
			if (players.isEmpty()) return;
			lastCost = 0;
			lastTime = time;
			for (var e : players) {
				trialPlayer.add(e.getUUID());
			}
			bar = new CustomBossEvent(trial, GDLang.fromTrial(trial));
			bar.setPlayers(players);
			data.start(this, time, trial, config);
			level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, ACTIVATED));
			sync();
			setChanged();
		} else if (time % 5 == 0) {
			boolean added = false;
			for (var pl : level.players()) {
				if (!isValidPlayer(pl)) continue;
				if (trialPlayer.contains(pl.getUUID())) continue;
				trialPlayer.add(pl.getUUID());
				added = true;
			}
			if (added) {
				List<ServerPlayer> players = new ArrayList<>();
				if (bar == null)
					bar = new CustomBossEvent(trial, GDLang.fromTrial(trial));
				bar.setPlayers(players);
				setChanged();
			}
		}
		if (data.tickTrial(this, sl, time)) {
			sync();
			setChanged();
		}
		if (bar != null)
			data.updateBar(bar, sl, time);
	}

	public void setTrial(ResourceLocation id) {
		if (!trialPlayer.isEmpty()) stop();
		trial = id;
		setChanged();
		sync();
	}

	public void addCost(int cost, long time) {
		long since = time - lastTime;
		long remain = lastCost - since;
		if (remain < 0) lastCost = cost;
		else lastCost = cost + remain;
		lastTime = time;
		sync();
		setChanged();
	}

	public void stop() {
		if (level instanceof ServerLevel sl) {
			data.stop(sl, this);
			level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, CHARGING));
		}
		trialPlayer.clear();
		if (bar != null) bar.removeAllPlayers();
		bar = null;
		sync();
		setChanged();
	}

	@Override
	public void complete(ServerLevel level, TrialConfig config, long time) {
		level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, VICTORY));
		if (config.reward == null) return;
		var loot = level.getServer().getLootData().getLootTable(config.reward);
		var params = new LootParams.Builder(level)
				.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(getBlockPos()))
				.create(LootContextParamSets.CHEST);
		var list = loot.getRandomItems(params);
		for (var stack : list) Block.popResource(level, getBlockPos().above(), stack);
	}

	@Override
	public void configureEntity(LivingEntity e, int index) {
		e.setPos(Vec3.atCenterOf(getBlockPos().above()).add(
				e.getRandom().nextGaussian(), 0, e.getRandom().nextGaussian()
		));
	}

	@Override
	public void configureGolem(AbstractGolemEntity<?, ?> golem, int mobIndex) {
	}

	@Override
	public boolean isOnGoing() {
		return !trialPlayer.isEmpty();
	}

	private boolean isValidPlayer(Player pl) {
		if (pl.isCreative() || pl.isSpectator()) return false;
		if (pl instanceof FakePlayer) return false;
		return pl.isAlive() && pl.distanceToSqr(Vec3.atCenterOf(getBlockPos())) <= 24 * 24;
	}

	@Override
	public boolean isValidTracked(Entity e) {
		return e.isAlive() && e.level() == level && e.distanceToSqr(Vec3.atCenterOf(getBlockPos())) < 48 * 48;
	}

	@Override
	public void setRemoved() {
		if (level instanceof ServerLevel sl) {
			data.stop(sl, this);
		}
		if (bar != null) bar.removeAllPlayers();
		super.setRemoved();
	}
}
