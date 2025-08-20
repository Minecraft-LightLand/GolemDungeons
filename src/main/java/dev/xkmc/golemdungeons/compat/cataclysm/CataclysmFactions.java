package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Scylla.Scylla_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModTag;
import dev.xkmc.golemdungeons.compat.cataclysm.data.*;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CataclysmFactions {

	public static final HostileFaction HARBINGER = HostileGolemRegistry.register(
			new TaggedBossFaction(loc("harbinger"), ModTag.TEAM_THE_HARBINGER));

	public static final HostileFaction MONSTROSITY = HostileGolemRegistry.register(
			new TaggedBossFaction(loc("monstrosity"), ModTag.TEAM_MONSTROSITY));

	public static final HostileFaction ENDER_GUARDIAN = HostileGolemRegistry.register(
			new TaggedBossFaction(loc("ender_guardian"), ModTag.TEAM_ENDER_GUARDIAN));

	public static final HostileFaction IGNIS = HostileGolemRegistry.register(
			new TaggedBossFaction(loc("ignis"), ModTag.TEAM_IGNIS));

	public static final HostileFaction SCYLLA = HostileGolemRegistry.register(
			new TaggedBossFaction(loc("scylla"), ModTag.TEAM_SCYLLA));

	public static CataBossEntry ENTRY_IGNIS;

	public static final List<CataBossEntry> BOSSES = new ArrayList<>();

	public static void register() {
		BOSSES.add(new CataBossEntry(ModItems.MECH_EYE::get, HarbingerGolemSpawn.ALL,
				The_Harbinger_Entity.class, CataclysmFactions.HARBINGER));
		BOSSES.add(new CataBossEntry(ModItems.MONSTROUS_EYE::get, MonstrosityGolemSpawn.ALL,
				Netherite_Monstrosity_Entity.class, CataclysmFactions.MONSTROSITY));
		BOSSES.add(new CataBossEntry(ModItems.VOID_EYE::get, EnderGuardianGolemSpawn.ALL,
				Ender_Guardian_Entity.class, CataclysmFactions.ENDER_GUARDIAN));
		BOSSES.add(ENTRY_IGNIS = new CataBossEntry(ModItems.FLAME_EYE::get, IgnisGolemSpawn.ALL,
				Ignis_Entity.class, CataclysmFactions.IGNIS));
		BOSSES.add(new CataBossEntry(ModItems.STORM_EYE::get, ScyllaGolemSpawn.ALL,
				Scylla_Entity.class, CataclysmFactions.SCYLLA));
	}

	private static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(CataDispatch.MODID, id);
	}


}
