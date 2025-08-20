package dev.xkmc.golemdungeons.init.data.advancement;

import dev.xkmc.l2core.serial.advancements.BaseCriterion;
import dev.xkmc.l2core.serial.advancements.BaseCriterionInstance;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class TrialCompleteTrigger extends BaseCriterion<TrialCompleteTrigger.Ins, TrialCompleteTrigger> {

	public static Ins ins(ResourceLocation trial) {
		Ins ans = new Ins(GDTriggers.COMPLETE.get());
		ans.trial = trial;
		return ans;
	}

	public TrialCompleteTrigger() {
		super(Ins.class);
	}

	public void trigger(ServerPlayer player, ResourceLocation trial) {
		this.trigger(player, e -> trial.equals(e.trial));
	}

	@SerialClass
	public static class Ins extends BaseCriterionInstance<Ins, TrialCompleteTrigger> {

		@SerialField
		private ResourceLocation trial;

		protected Ins(TrialCompleteTrigger t) {
			super(t);
		}

	}

}
