package dev.xkmc.golemdungeons.content.summon;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class HostileSummonWand extends Item {

	private static final String KEY = "SummonFaction";

	public static ItemStack of(ResourceLocation rl) {
		var ans = GDItems.SUMMON.asStack();
		ans.getOrCreateTag().putString(KEY, rl.toString());
		return ans;
	}

	public static ResourceLocation getId(ItemStack stack) {
		var root = stack.getTag();
		if (root != null && root.contains(KEY, Tag.TAG_STRING)) {
			var ans = ResourceLocation.tryParse(root.getString(KEY));
			if (ans != null && GolemDungeons.SPAWN.getEntry(ans) != null) return ans;
		}
		return SummonWandSelector.getAll().get(0);

	}

	public HostileSummonWand(Properties p) {
		super(p);
	}

	@Override
	public Component getName(ItemStack stack) {
		return super.getName(stack).copy().append(" - " + getId(stack));
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		if (ctx.getLevel() instanceof ServerLevel sl) {
			var id = getId(ctx.getItemInHand());
			var entry = GolemDungeons.SPAWN.getEntry(id);
			var e = entry.summon(sl);
			if (e != null)
				e.setPos(ctx.getClickLocation());
		}
		return InteractionResult.SUCCESS;
	}
}
