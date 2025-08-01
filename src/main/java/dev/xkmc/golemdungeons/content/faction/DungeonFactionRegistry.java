package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;

public class DungeonFactionRegistry {

	public static final HostileFaction REMNANT = HostileGolemRegistry.register(
			new RemnantFaction(GolemDungeons.loc("factory_remnant")));

	public static final HostileFaction ILLAGER = HostileGolemRegistry.register(
			new IllagerFaction(GolemDungeons.loc("illagers_creation")));

	public static final HostileFaction PIGLIN = HostileGolemRegistry.register(
			new PiglinFaction(GolemDungeons.loc("piglin_legacy")));

	public static final HostileFaction SCULK = HostileGolemRegistry.register(
			new SculkFaction(GolemDungeons.loc("sculk_infestation")));

	public static void register() {

	}

}
