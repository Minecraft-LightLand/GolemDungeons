package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.compat.cataclysm.data.HarbingerGolemSpawn;
import dev.xkmc.golemdungeons.compat.cataclysm.data.MonstrosityGolemSpawn;
import dev.xkmc.golemdungeons.compat.cataclysm.factions.HarbingerFaction;
import dev.xkmc.golemdungeons.compat.cataclysm.factions.MonstrosityFaction;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CataclysmFactions {

	public static final HostileFaction HARBINGER = HostileGolemRegistry.register(
			new HarbingerFaction(new ResourceLocation(CataDispatch.MODID, "harbinger")));

	public static final HostileFaction MONSTROSITY = HostileGolemRegistry.register(
			new MonstrosityFaction(new ResourceLocation(CataDispatch.MODID, "monstrosity")));
	public static final List<CataBossEntry> BOSSES = new ArrayList<>();

	public static void register() {
		BOSSES.add(new CataBossEntry(ModItems.MECH_EYE::get, HarbingerGolemSpawn.HARBINGER_ALL,
				The_Harbinger_Entity.class, CataclysmFactions.HARBINGER));
		BOSSES.add(new CataBossEntry(ModItems.MONSTROUS_EYE::get, MonstrosityGolemSpawn.MONSTROSITY_ALL,
				Netherite_Monstrosity_Entity.class, CataclysmFactions.MONSTROSITY));
	}

	public static void genSpawn(ConfigDataProvider.Collector map) {
		HarbingerGolemSpawn.add(map);
		MonstrosityGolemSpawn.add(map);
	}

}
