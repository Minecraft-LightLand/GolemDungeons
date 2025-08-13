package dev.xkmc.golemdungeons.init.data.advancement;

import dev.xkmc.golemdungeons.init.GolemDungeons;

public class GDTriggers {

	public static final TrialCompleteTrigger COMPLETE = new TrialCompleteTrigger(GolemDungeons.loc("trial_complete"));
	public static final TrialSummonTrigger SUMMON = new TrialSummonTrigger(GolemDungeons.loc("trial_summon"));

	public static void register() {
	}

}
