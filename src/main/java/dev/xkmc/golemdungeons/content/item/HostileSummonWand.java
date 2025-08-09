package dev.xkmc.golemdungeons.content.item;

import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.util.GolemUtils;
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
		var id = getId(stack);
		var config = GolemDungeons.SPAWN.getEntry(id);
		var target = config.targetTrial;
		if (target != null)
			return GDLang.fromTrial(target);
		return super.getName(stack);
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		if (ctx.getLevel() instanceof ServerLevel sl) {
			var id = getId(ctx.getItemInHand());
			var entry = GolemDungeons.SPAWN.getEntry(id);
			if (ctx.getLevel().getBlockEntity(ctx.getClickedPos()) instanceof GolemTrialBlockEntity be) {
				if (entry.targetTrial != null && GolemDungeons.TRIAL.getEntry(entry.targetTrial) != null)
					be.setTrial(entry.targetTrial);
			} else {
				var e = entry.summon(sl);
				if (e != null) {
					e.setPos(ctx.getClickLocation());
					GolemUtils.recursiveAdd(sl, e);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}
