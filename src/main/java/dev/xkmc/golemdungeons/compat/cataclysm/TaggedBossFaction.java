package dev.xkmc.golemdungeons.compat.cataclysm;

import dev.xkmc.golemdungeons.content.faction.DungeonFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class TaggedBossFaction extends DungeonFaction {

	private final TagKey<EntityType<?>> tag;

	public TaggedBossFaction(ResourceLocation id, TagKey<EntityType<?>> tag) {
		super(id);
		this.tag = tag;
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType().is(tag)) return true;
		return super.isAlliedTo(other);
	}

}
