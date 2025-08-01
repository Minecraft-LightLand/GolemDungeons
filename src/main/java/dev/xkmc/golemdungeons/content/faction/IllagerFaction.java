package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.ItemStack;

public class IllagerFaction extends DungeonFaction {

	public IllagerFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		return Raid.getLeaderBannerInstance();
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType().is(EntityTypeTags.RAIDERS)) return validAlly(other);
		return super.isAlliedTo(other);
	}

}
