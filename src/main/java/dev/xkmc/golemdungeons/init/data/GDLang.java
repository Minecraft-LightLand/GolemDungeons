package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.golemdungeons.compat.cataclysm.data.HarbingerGolemSpawn;
import dev.xkmc.golemdungeons.compat.cataclysm.data.MonstrosityGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.IllagerGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.modulargolems.init.ModularGolems;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Locale;

public enum GDLang {
	FLAME_SWORD_ATK("tooltip.flame_sword_atk", "Deal %s extra fire damage when attacking, bypassing target armor", 1, ChatFormatting.GRAY),
	FLAME_SWORD_LOOT("tooltip.flame_sword_loot", "Drop more golem materials when metal golems slay other golems with this sword", 0, ChatFormatting.GRAY),
	SCULK_SCYTHE_ATK("tooltip.sculk_scythe_atk", "On hit, create sonic burst dealing sonic damage at %s of attack damage", 1, ChatFormatting.GRAY),
	TRIAL_MEDAL("tooltip.trial_medal.spawner", "Right click golem spawner to charge them immediately", 0, ChatFormatting.GRAY),
	TRIAL_MEDAL_CATA("tooltip.trial_medal.cata", "Right click Cataclysm boss spawner to summon boss with golem reinforcement", 0, ChatFormatting.GRAY),
	CHARGE_TIME("tooltip.charge_time", "Charging: %s", 1, ChatFormatting.GRAY),

	BAR_WAVE("bar.wave", " - Wave %s: ", 1, null),
	BAR_VICTORY("bar.victory", "Victory", 0, null),
	BAR_CHARGING("bar.charging", "Charging", 0, null),
	BAR_SUMMONING("bar.summoning", "Summoning", 0, null),
	BAR_PROGRESS("bar.progress", "%s Remaining", 1, null),
	;

	private final String key, def;
	private final int arg;
	private final ChatFormatting format;

	GDLang(String key, String def, int arg, @Nullable ChatFormatting format) {
		this.key = ModularGolems.MODID + "." + key;
		this.def = def;
		this.arg = arg;
		this.format = format;
	}

	public static String asId(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

	public static MutableComponent getTranslate(String s) {
		return Component.translatable(ModularGolems.MODID + "." + s);
	}

	public static MutableComponent fromTrial(ResourceLocation id) {
		return Component.translatable(Util.makeDescriptionId("trial", id));
	}

	public MutableComponent get(Object... args) {
		if (args.length != arg)
			throw new IllegalArgumentException("for " + name() + ": expect " + arg + " parameters, got " + args.length);
		MutableComponent ans = Component.translatable(key, args);
		if (format != null) {
			return ans.withStyle(format);
		}
		return ans;
	}

	public static void genLang(RegistrateLangProvider pvd) {
		for (GDLang lang : GDLang.values()) {
			pvd.add(lang.key, lang.def);
		}
		pvd.add(Util.makeDescriptionId("trial", FactoryGolemSpawn.FACTORY_ALL), "Factory Remnant");
		pvd.add(Util.makeDescriptionId("trial", PiglinGolemSpawn.PIGLIN_ALL), "Piglin Legacy");
		pvd.add(Util.makeDescriptionId("trial", SculkGolemSpawn.SCULK_ALL), "Sculk Infestation");
		pvd.add(Util.makeDescriptionId("trial", IllagerGolemSpawn.ILLAGER_ALL), "Illagers' Creations");
		pvd.add(Util.makeDescriptionId("trial", HarbingerGolemSpawn.HARBINGER_ALL), "Harbinger's Revenge");
		pvd.add(Util.makeDescriptionId("trial", MonstrosityGolemSpawn.MONSTROSITY_ALL), "Netherite Reinforcement");
	}
}
