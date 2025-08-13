package dev.xkmc.golemdungeons.content.item;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2itemselector.select.item.IItemSelector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SummonWandSelector extends IItemSelector {

	private final String modid;

	public SummonWandSelector(String id) {
		super(GolemDungeons.loc(id));
		this.modid = id;
	}

	public boolean test(ItemStack stack) {
		return stack.is(GDItems.SUMMON.get()) && HostileSummonWand.getModid(stack).equals(modid);
	}

	public static List<ResourceLocation> getAll(String modid) {
		var all = GolemDungeons.SPAWN.getAll();
		var ans = new ArrayList<ResourceLocation>();
		for (var e : all) {
			if (e.getID().getNamespace().equals(modid) && e.targetTrial != null)
				ans.add(e.getID());
		}
		return ans;
	}

	@Override
	public int getIndex(Player player) {
		var list = getAll(modid);
		if (test(player.getMainHandItem()))
			return list.indexOf(HostileSummonWand.getId(player.getMainHandItem()));
		return list.indexOf(HostileSummonWand.getId(player.getOffhandItem()));
	}

	@Override
	public List<ItemStack> getList() {
		var all = GolemDungeons.SPAWN.getAll();
		var ans = new ArrayList<ItemStack>();
		for (var e : all) {
			if (e.getID().getNamespace().equals(modid) && e.targetTrial != null)
				ans.add(HostileSummonWand.of(e.getID()));
		}
		return ans;
	}

}
