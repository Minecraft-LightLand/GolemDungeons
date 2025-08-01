package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.item.ItemStack;

public class PiglinFaction extends DungeonFaction {

	public PiglinFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other instanceof AbstractPiglin) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
