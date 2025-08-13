package dev.xkmc.golemdungeons.compat.cataclysm;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.advancement.GDTriggers;
import dev.xkmc.golemdungeons.util.GolemUtils;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public record CataBossEntry(ItemLike activator, ResourceLocation trial, Class<?> boss, HostileFaction faction) {

	public void summon(ServerLevel sl, BlockPos pos) {
		var config = GolemDungeons.TRIAL.getEntry(trial);
		if (config == null) return;
		for (var e : sl.getPlayers(e -> config.isInRange(e, pos))) {
			GDTriggers.SUMMON.trigger(e, trial);
		}
		List<LivingEntity> targets = new ArrayList<>();
		for (var waves : config.list) {
			for (var entry : waves) {
				var target = GolemDungeons.SPAWN.getEntry(entry.target());
				if (target == null) continue;
				int n = entry.roll(sl.getRandom());
				for (int i = 0; i < n; i++) {
					targets.add(target.summon(sl));
				}
			}
		}
		var center = Vec3.atCenterOf(pos.above());
		int n = targets.size();
		for (int i = 0; i < n; i++) {
			var le = targets.get(i);
			le.setPos(center.add(Math.cos(Math.PI * 2 / n * i), 0, Math.sin(Math.PI * 2 / n * i)));
			GolemUtils.recursiveAdd(sl, le);
		}
	}
}
