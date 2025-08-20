package dev.xkmc.golemdungeons.init.data.advancement;

import dev.xkmc.l2core.serial.advancements.BaseCriterion;
import dev.xkmc.l2core.serial.advancements.BaseCriterionInstance;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class TrialSummonTrigger extends BaseCriterion<TrialSummonTrigger.Ins, TrialSummonTrigger> {

	public static Ins ins(ResourceLocation trial) {
		Ins ans = new Ins(GDTriggers.SUMMON.get());
		ans.trial = trial;
		return ans;
	}

	public TrialSummonTrigger() {
		super(Ins.class);
	}

	public void trigger(ServerPlayer player, ResourceLocation trial) {
		this.trigger(player, e -> trial.equals(e.trial));
	}

	@SerialClass
	public static class Ins extends BaseCriterionInstance<Ins, TrialSummonTrigger> {

		@SerialField
		private ResourceLocation trial;

		protected Ins(TrialSummonTrigger t) {
			super(t);
		}

	}

}
