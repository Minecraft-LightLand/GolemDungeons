package dev.xkmc.golemdungeons.content.equipments;

import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import net.minecraft.world.item.ItemStack;

public interface GolemCustomSourceWeapon {

	void onModifySource(CreateSourceEvent event, MetalGolemEntity golem, ItemStack stack);

}
