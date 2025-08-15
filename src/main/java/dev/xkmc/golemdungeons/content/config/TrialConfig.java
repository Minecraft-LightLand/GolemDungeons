package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2library.serial.config.CollectType;
import dev.xkmc.l2library.serial.config.ConfigCollect;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class TrialConfig extends BaseConfig {

	@ConfigCollect(CollectType.COLLECT)
	@SerialClass.SerialField
	public final List<ArrayList<WaveEntry>> list = new ArrayList<>();

	@SerialClass.SerialField
	public int spawnCost = 1000;

	@SerialClass.SerialField
	public int triggerRange = 12, minY = -4, maxY = 6;

	@SerialClass.SerialField
	public boolean generateChest = false;

	@Nullable
	@SerialClass.SerialField
	public ResourceLocation reward;

	public TrialConfig add(WaveEntry... entries) {
		list.add(new ArrayList<>(List.of(entries)));
		return this;
	}

	public TrialConfig setCost(int cost) {
		spawnCost = cost;
		return this;
	}

	public TrialConfig genChest() {
		generateChest = true;
		return this;
	}

	public TrialConfig setTriggerRange(int range, int minY, int maxY) {
		this.triggerRange = range;
		this.minY = minY;
		this.maxY = maxY;
		return this;
	}

	public TrialConfig setReward(ResourceLocation loot) {
		this.reward = loot;
		return this;
	}

	public boolean isInRange(Player pl, BlockPos pos) {
		var diff = pl.position().subtract(Vec3.atCenterOf(pos));
		if (Math.abs(diff.x()) > triggerRange) return false;
		if (Math.abs(diff.z()) > triggerRange) return false;
		return diff.y > minY && diff.y < maxY;
	}

	public record WaveEntry(ResourceLocation target, int num) {

	}

}
