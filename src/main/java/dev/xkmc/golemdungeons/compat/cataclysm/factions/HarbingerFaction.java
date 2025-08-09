package dev.xkmc.golemdungeons.compat.cataclysm.factions;

import com.github.L_Ender.cataclysm.init.ModTag;
import dev.xkmc.golemdungeons.content.faction.DungeonFaction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class HarbingerFaction extends DungeonFaction {

	public HarbingerFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType().is(ModTag.TEAM_THE_HARBINGER)) return true;
		return super.isAlliedTo(other);
	}

}
