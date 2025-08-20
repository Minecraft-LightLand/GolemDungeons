package dev.xkmc.golemdungeons.init.data.advancement;

import dev.xkmc.l2core.init.reg.simple.SR;
import dev.xkmc.l2core.init.reg.simple.Val;
import dev.xkmc.modulargolems.init.ModularGolems;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;

public class GDTriggers {

	public static final SR<CriterionTrigger<?>> REG = SR.of(ModularGolems.REG, Registries.TRIGGER_TYPE);

	public static final Val<TrialCompleteTrigger> COMPLETE = REG.reg("trial_complete", TrialCompleteTrigger::new);
	public static final Val<TrialSummonTrigger> SUMMON = REG.reg("trial_summon", TrialSummonTrigger::new);

	public static void register() {
	}

}
