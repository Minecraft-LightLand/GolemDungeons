package dev.xkmc.golemdungeons.content.faction;

import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2serial.util.LazyFunction;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IllagerFaction extends DungeonFaction {

	private static final LazyFunction<Level, ItemStack> BANNER = LazyFunction.create(level ->
			Raid.getLeaderBannerInstance(level.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN)));

	public IllagerFaction(ResourceLocation id) {
		super(id);
	}

	@Override
	public ItemStack getBanner(AbstractGolemEntity<?, ?> e, int col) {
		if (e.getItemBySlot(EquipmentSlot.CHEST).is(GDItems.SAMURAI_CHESTPLATE.get()))
			return ItemStack.EMPTY;
		return BANNER.get(e.level());
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other.getType().is(EntityTypeTags.RAIDERS)) return validAlly(other);
		return super.isAlliedTo(other);
	}

	@Override
	public boolean hostileGolemAttacks(LivingEntity target) {
		return super.hostileGolemAttacks(target) || target instanceof Villager vil && vil.canBeSeenAsEnemy();
	}

}
