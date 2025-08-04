package dev.xkmc.golemdungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDConfig;
import dev.xkmc.golemdungeons.init.data.spawn.IllagerGolemSpawn;
import dev.xkmc.golemdungeons.util.GolemUtils;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@Mixin(Raid.class)
public abstract class RaidMixin {

	@Unique
	private List<AbstractGolemEntity<?, ?>> golemdungeons$golemRaiders = new LinkedList<>();

	@Shadow
	private int groupsSpawned;

	@Shadow
	@Final
	private ServerLevel level;

	@Shadow
	public abstract void joinRaid(int p_37714_, Raider p_37715_, @Nullable BlockPos p_37716_, boolean p_37717_);

	@Shadow
	@Nullable
	public abstract Raider getLeader(int p_37751_);

	@Shadow
	private float totalHealth;

	@Shadow
	public abstract boolean isStopped();

	@Shadow
	public abstract BlockPos getCenter();

	@Inject(method = "spawnGroup", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/raid/Raid;updateBossbar()V"))
	private void golemdungeons$raidGolems(BlockPos pos, CallbackInfo ci) {
		if (!GDConfig.COMMON.enableRaidGolems.get()) return;
		int wave = groupsSpawned;
		var data = GolemDungeons.TRIAL.getEntry(IllagerGolemSpawn.RAIDS);
		if (data == null) return;
		if (wave >= data.list.size()) wave = data.list.size() - 1;
		if (wave < 0) return;
		var list = data.list.get(wave);
		var leader = getLeader(wave);
		for (var ent : list) {
			var e = GolemDungeons.SPAWN.getEntry(ent.target());
			if (e == null) continue;
			var le = e.summon(level);
			if (le == null) continue;
			if (le instanceof Raider raider) {
				joinRaid(wave, raider, pos, false);
			} else {
				le.setPos(Vec3.atBottomCenterOf(pos));
				GolemUtils.recursiveAdd(level, le);
			}
			if (leader != null) {
				if (!(le instanceof AbstractGolemEntity<?, ?>)) {
					le = le.getControllingPassenger();
				}
				if (le instanceof AbstractGolemEntity<?, ?> golem) {
					golem.setLeader(leader);
					totalHealth += golem.getMaxHealth();
					golemdungeons$golemRaiders.add(golem);
				}
			}
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void golemdungeons$tickRaidGolem(CallbackInfo ci) {
		if (isStopped() || golemdungeons$golemRaiders.isEmpty()) return;
		golemdungeons$golemRaiders.removeIf(e ->
				!e.isAlive() || e.level() != level ||
						getCenter().distSqr(e.blockPosition()) > 12544);
		var leader = getLeader(groupsSpawned);
		if (leader == null || !leader.isAlive() || leader.tickCount % 10 != 0) return;
		for (var e : golemdungeons$golemRaiders) {
			var old = e.getLeader();
			if (leader == old) continue;
			e.setLeader(leader);
		}
	}

	@ModifyReturnValue(method = "getHealthOfLivingRaiders", at = @At("RETURN"))
	private float golemdungeons$addGolemHealth(float original) {
		for (var e : golemdungeons$golemRaiders) {
			original += e.getHealth();
		}
		return original;
	}

	@ModifyReturnValue(method = "getTotalRaidersAlive", at = @At("RETURN"))
	private int golemdungeons$addGolem(int original) {
		return original + golemdungeons$golemRaiders.size();
	}

}
