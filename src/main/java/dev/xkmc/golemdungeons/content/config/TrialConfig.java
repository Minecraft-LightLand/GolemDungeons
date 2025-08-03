package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2library.serial.config.CollectType;
import dev.xkmc.l2library.serial.config.ConfigCollect;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class TrialConfig extends BaseConfig {

	@ConfigCollect(CollectType.COLLECT)
	@SerialClass.SerialField
	public final List<ArrayList<WaveEntry>> list = new ArrayList<>();

	public TrialConfig add(WaveEntry... entries) {
		list.add(new ArrayList<>(List.of(entries)));
		return this;
	}

	public record WaveEntry(ResourceLocation target, int min, int max) {

		public int roll(RandomSource random) {
			return random.nextInt(min, max + 1);
		}

	}

}
