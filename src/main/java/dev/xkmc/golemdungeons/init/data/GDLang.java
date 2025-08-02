package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
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

	public static Component fromTrial(ResourceLocation id) {
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
	}
}
