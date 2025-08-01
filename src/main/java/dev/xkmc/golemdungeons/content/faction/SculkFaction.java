package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

public class SculkFaction extends DungeonFaction {

	public SculkFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType() == EntityType.WARDEN) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
