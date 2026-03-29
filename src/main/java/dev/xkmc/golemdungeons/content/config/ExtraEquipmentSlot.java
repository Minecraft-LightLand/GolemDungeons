package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.modulargolems.content.entity.common.SweepGolemEntity;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public enum ExtraEquipmentSlot {
	BACKUP((e, stack) -> e.getBackupHand().setItem(stack)),
	ARROW((e, stack) -> e.getArrowSlot().setItem(stack)),
	LEFT_SHOULDER((e, stack) -> {
		if (e instanceof MetalGolemEntity g) g.getLeftShoulder().setItem(stack);
	}),
	RIGHT_SHOULDER((e, stack) -> {
		if (e instanceof MetalGolemEntity g) g.getRightShoulder().setItem(stack);
	}),
	;

	private final BiConsumer<SweepGolemEntity<?, ?>, ItemStack> setter;

	ExtraEquipmentSlot(BiConsumer<SweepGolemEntity<?, ?>, ItemStack> setter) {
		this.setter = setter;
	}

	public void set(SweepGolemEntity<?, ?> golem, ItemStack stack) {
		setter.accept(golem, stack);
	}
}
