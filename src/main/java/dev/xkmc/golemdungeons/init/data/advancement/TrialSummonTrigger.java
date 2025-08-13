package dev.xkmc.golemdungeons.init.data.advancement;

import dev.xkmc.l2library.serial.advancements.BaseCriterion;
import dev.xkmc.l2library.serial.advancements.BaseCriterionInstance;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulargolems.init.advancement.GolemTriggers;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class TrialSummonTrigger extends BaseCriterion<TrialSummonTrigger.Ins, TrialSummonTrigger> {

	public static Ins ins(ResourceLocation trial) {
		Ins ans = new Ins(GDTriggers.SUMMON.getId(), ContextAwarePredicate.ANY);
		ans.trial = trial;
		return ans;
	}

	public TrialSummonTrigger(ResourceLocation id) {
		super(id, Ins::new, Ins.class);
	}

	public void trigger(ServerPlayer player, ResourceLocation trial) {
		this.trigger(player, e -> trial.equals(e.trial));
	}

	@SerialClass
	public static class Ins extends BaseCriterionInstance<Ins, TrialSummonTrigger> {

		@SerialClass.SerialField
		private ResourceLocation trial;

		public Ins(ResourceLocation id, ContextAwarePredicate player) {
			super(id, player);
		}

	}

}
