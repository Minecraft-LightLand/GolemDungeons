package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.l2core.serial.config.BaseConfig;
import dev.xkmc.l2core.serial.config.CollectType;
import dev.xkmc.l2core.serial.config.ConfigCollect;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class TrialConfig extends BaseConfig {

	@ConfigCollect(CollectType.COLLECT)
	@SerialField
	public final List<ArrayList<WaveEntry>> list = new ArrayList<>();

	@SerialField
	public int spawnCost = 1000;

	@SerialField
	public int triggerRange = 12, minY = -4, maxY = 6;

	@SerialField
	public boolean generateChest = false;

	@Nullable
	@SerialField
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

	public TrialConfig setReward(ResourceKey<LootTable> loot) {
		this.reward = loot.location();
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
