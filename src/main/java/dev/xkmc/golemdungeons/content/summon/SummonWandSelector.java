package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2itemselector.select.item.IItemSelector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SummonWandSelector extends IItemSelector {

	public SummonWandSelector(ResourceLocation id) {
		super(id);
	}

	public boolean test(ItemStack stack) {
		return stack.is(GDItems.SUMMON.get());
	}

	public static List<ResourceLocation> getAll() {
		var all = GolemDungeons.SPAWN.getAll();
		var ans = new ArrayList<ResourceLocation>();
		for (var e : all) ans.add(e.getID());
		return ans;
	}

	@Override
	public int getIndex(Player player) {
		var list = getAll();
		if (test(player.getMainHandItem()))
			return list.indexOf(HostileSummonWand.getId(player.getMainHandItem()));
		return list.indexOf(HostileSummonWand.getId(player.getOffhandItem()));
	}

	@Override
	public List<ItemStack> getList() {
		var all = GolemDungeons.SPAWN.getAll();
		var ans = new ArrayList<ItemStack>();
		for (var e : all) ans.add(HostileSummonWand.of(e.getID()));
		return ans;
	}

}
