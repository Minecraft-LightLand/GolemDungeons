package dev.xkmc.golemdungeons.content.spawner;

import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.init.data.advancement.GDTriggers;
import dev.xkmc.l2library.base.tile.BaseBlockEntity;
import dev.xkmc.l2modularblock.BlockProxy;
import dev.xkmc.l2modularblock.tile_api.TickableBlockEntity;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.item.card.PathRecordCard;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static dev.xkmc.golemdungeons.content.spawner.GolemTrialBlock.STATE;
import static dev.xkmc.golemdungeons.content.spawner.GolemTrialBlock.State.*;

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


	public void clearCharging() {
		if (level != null && getBlockState().getValue(STATE) == CHARGING) {
			lastCost = 0;
			level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, IDLE));
		}
	}


	public void setSummonPos(List<PathRecordCard.Pos> list) {
		var self = getBlockPos();
		var dir = getBlockState().getValue(BlockProxy.HORIZONTAL_FACING);
		var rot = switch (dir) {
			case WEST -> Rotation.CLOCKWISE_90;
			case EAST -> Rotation.COUNTERCLOCKWISE_90;
			case SOUTH -> Rotation.CLOCKWISE_180;
			default -> Rotation.NONE;
		};
		targets.clear();
		for (var e : list) {
			if (e.pos().distSqr(self) > 48 * 48) continue;
			var diff = e.pos().subtract(self).rotate(rot);
			targets.add(diff);
		}
		sync();
		setChanged();
	}

	public List<BlockPos> getTargets() {
		var self = getBlockPos();
		var dir = getBlockState().getValue(BlockProxy.HORIZONTAL_FACING);
		var rot = switch (dir) {
			case WEST -> Rotation.COUNTERCLOCKWISE_90;
			case EAST -> Rotation.CLOCKWISE_90;
			case SOUTH -> Rotation.CLOCKWISE_180;
			default -> Rotation.NONE;
		};
		var ans = new ArrayList<BlockPos>();
		for (var e : targets) {
			ans.add(e.rotate(rot).offset(self));
		}
		return ans;
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
				if (!isTriggeringPlayer(pl, config)) continue;
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
		for (var id : trialPlayer) {
			var pl = level.getPlayerByUUID(id);
			if (pl instanceof ServerPlayer sp) {
				GDTriggers.COMPLETE.trigger(sp, config.getID());
			}
		}
		level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(STATE, VICTORY));
		if (config.reward == null) return;
		var loot = level.getServer().getLootData().getLootTable(config.reward);
		var params = new LootParams.Builder(level)
				.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(getBlockPos()))
				.create(LootContextParamSets.CHEST);
		List<ItemStack> list = loot.getRandomItems(params);
		var be = level.getBlockEntity(getBlockPos().above());
		if (be != null) {
			var opt = be.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
			if (opt.isPresent()) {
				var ans = new ArrayList<ItemStack>();
				var cap = opt.get();
				for (var stack : list) {
					var remain = ItemHandlerHelper.insertItem(cap, stack, false);
					if (!remain.isEmpty()) {
						ans.add(remain);
					}
				}
				list = ans;
			}
		}
		for (var stack : list) Block.popResource(level, getBlockPos().above(), stack);
	}

	@Override
	public void configureEntity(LivingEntity e, int index) {
		var self = getBlockPos();
		var dir = getBlockState().getValue(BlockProxy.HORIZONTAL_FACING);
		var rot = switch (dir) {
			case WEST -> Rotation.COUNTERCLOCKWISE_90;
			case EAST -> Rotation.CLOCKWISE_90;
			case SOUTH -> Rotation.CLOCKWISE_180;
			default -> Rotation.NONE;
		};
		var pos = targets.isEmpty() ? self.above() : targets.get(index % targets.size()).rotate(rot).offset(self);
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				for (int y = 0; y <= 2; y++) {
					e.level().removeBlock(pos.offset(x, y, z), false);
				}
			}
		}
		var vec = Vec3.atCenterOf(pos);
		if (targets.size() <= 1)
			vec = vec.add(e.getRandom().nextGaussian(), 0, e.getRandom().nextGaussian());
		e.setPos(vec);
	}

	@Override
	public void configureGolem(AbstractGolemEntity<?, ?> golem, int mobIndex) {
	}

	@Override
	public boolean isOnGoing() {
		return !trialPlayer.isEmpty();
	}

	private boolean isTriggeringPlayer(Player pl, TrialConfig config) {
		if (pl.isCreative() || pl.isSpectator()) return false;
		if (pl instanceof FakePlayer) return false;
		if (!pl.isAlive()) return false;
		return config.isInRange(pl, getBlockPos());
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

	public List<Component> getText(Player player) {
		var ans = new ArrayList<Component>();
		if (trial != null && player.getAbilities().instabuild) {
			ans.add(GDLang.fromTrial(trial));
		}
		var state = getBlockState().getValue(STATE);
		if (state == CHARGING) {
			long time = player.level().getGameTime();
			long since = time - lastTime;
			long remain = lastCost - since;
			if (remain > 0) {
				long sec = remain / 20;
				String str = "%d:%02d".formatted(sec / 60, sec % 60);
				ans.add(GDLang.CHARGE_TIME.get(str));
			}
		}
		return ans;
	}

	@Override
	public AABB getRenderBoundingBox() {
		return super.getRenderBoundingBox().inflate(48);
	}

}
