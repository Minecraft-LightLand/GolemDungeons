package dev.xkmc.golemdungeons.compat.twilightforest.item;

import dev.xkmc.golemdungeons.content.equipments.GolemCustomSourceWeapon;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GiantKnightmetalSword extends MetalGolemWeaponItem implements GolemCustomSourceWeapon {

	public GiantKnightmetalSword(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	public void onModifySource(CreateSourceEvent event, MetalGolemEntity golem, ItemStack stack) {
		event.enable(DefaultDamageState.BYPASS_ARMOR);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
		list.add(GDLang.ANCIENT_FORGE_ATK.get());
		super.appendHoverText(stack, ctx, list, flag);
	}

}
