package dev.xkmc.golemdungeons.init.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GDConfig {

	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public final ForgeConfigSpec.BooleanValue enableRaidGolems;
		public final ForgeConfigSpec.DoubleValue flameSwordDamage;
		public final ForgeConfigSpec.DoubleValue flameSwordLoot;

		Common(ForgeConfigSpec.Builder builder) {
			enableRaidGolems = builder.comment("Enable adding hostile golems to raids")
					.define("enableRaidGolems", true);

			flameSwordDamage = builder.comment("Flame sword extra damage as percentage original attack")
					.defineInRange("flameSwordDamage", 0.25, 0, 1);
			flameSwordLoot = builder.comment("Flame sword material loot as percentage of crafting cost")
					.defineInRange("flameSwordLoot", 0.33, 0, 1);
		}

	}

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();

		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void init() {
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
		register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static void register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = "l2_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
	}


}
