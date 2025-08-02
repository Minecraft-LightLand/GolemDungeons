package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.l2library.base.tile.BaseBlockEntity;
import dev.xkmc.l2modularblock.tile_api.TickableBlockEntity;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
		var pos = getBlockPos();
		if (!trialPlayer.isEmpty()) {
			if (trial == null) {
				stop();
				return;
			}
			boolean change = trialPlayer.removeIf(e -> {
				var player = level.getPlayerByUUID(e);
				return player == null || player.level() != level || !player.isAlive() ||
						pos.distSqr(player.blockPosition()) > 48 * 48;
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
		if (time % (trialPlayer.isEmpty() ? 20 : 5) == 0) return;
		var config = GolemDungeons.RAID.getEntry(trial);
		if (config == null) return;
		if (trialPlayer.isEmpty()) {
			if (time - lastTime < lastCost) return;
			var center = Vec3.atCenterOf(pos);
			List<ServerPlayer> players = new ArrayList<>();
			for (var pl : level.players()) {
				if (pl.isCreative() || pl.isSpectator()) continue;
				if (pl instanceof FakePlayer) continue;
				if (pl.isAlive() && pl.distanceToSqr(center) <= 24 * 24 && pl instanceof ServerPlayer sp) {
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
			sync();
			setChanged();
		} else {
			var center = Vec3.atCenterOf(pos);
			boolean added = false;
			for (var pl : level.players()) {
				if (pl.isCreative() || pl.isSpectator()) continue;
				if (pl instanceof FakePlayer) continue;
				if (trialPlayer.contains(pl.getUUID())) continue;
				if (pl.isAlive() && pl.distanceToSqr(center) <= 24 * 24) {
					trialPlayer.add(pl.getUUID());
					added = true;
				}
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
		if (level instanceof ServerLevel sl)
			data.stop(sl, this);
		trialPlayer.clear();
		if (bar != null) bar.removeAllPlayers();
		bar = null;
		sync();
		setChanged();
	}

}
