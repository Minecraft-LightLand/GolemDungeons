package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2core.util.ConfigInit;
import net.neoforged.neoforge.common.ModConfigSpec;

public class GDConfig {

	public static class Client extends ConfigInit {

		Client(Builder builder) {
			markL2();
		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.BooleanValue enableRaidGolems;
		public final ModConfigSpec.DoubleValue flameSwordDamage;
		public final ModConfigSpec.DoubleValue flameSwordLoot;
		public final ModConfigSpec.DoubleValue sculkScytheDamage;
		public final ModConfigSpec.DoubleValue fierySwordDamage;

		Server(Builder builder) {
			markL2();
			enableRaidGolems = builder.text("Enable adding hostile golems to raids")
					.define("enableRaidGolems", true);

			flameSwordDamage = builder.text("Flame sword extra damage as percentage original attack")
					.defineInRange("flameSwordDamage", 0.25, 0, 10);
			flameSwordLoot = builder.text("Flame sword material loot as percentage of crafting cost")
					.defineInRange("flameSwordLoot", 0.33, 0, 1);
			sculkScytheDamage = builder.text("Sculk Scythe extra damage as percentage original attack")
					.defineInRange("sculkScytheDamage", 0.25, 0, 10);

			fierySwordDamage = builder.text("Giant Fiery sword extra damage as percentage original attack")
					.defineInRange("fierySwordDamage", 1d, 0, 10);
		}

	}

	public static final Client CLIENT = GolemDungeons.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = GolemDungeons.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}


}
