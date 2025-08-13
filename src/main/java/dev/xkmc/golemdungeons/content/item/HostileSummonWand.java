package dev.xkmc.golemdungeons.content.item;

import com.tterrag.registrate.util.CreativeModeTabModifier;
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

import java.util.LinkedHashSet;

public class HostileSummonWand extends Item {

	private static final String KEY_FACTION = "SummonFaction";
	private static final String KEY_MOD = "FactionMod";

	public static ItemStack of(ResourceLocation rl) {
		var ans = GDItems.SUMMON.asStack();
		ans.getOrCreateTag().putString(KEY_FACTION, rl.toString());
		return ans;
	}

	public static ResourceLocation getId(ItemStack stack) {
		var root = stack.getTag();
		if (root != null && root.contains(KEY_FACTION, Tag.TAG_STRING)) {
			var ans = ResourceLocation.tryParse(root.getString(KEY_FACTION));
			if (ans != null && GolemDungeons.SPAWN.getEntry(ans) != null) return ans;
		}
		return SummonWandSelector.getAll(getModid(stack)).get(0);

	}

	public HostileSummonWand(Properties p) {
		super(p);
	}

	public static String getModid(ItemStack stack) {
		var root = stack.getTag();
		if (root != null && root.contains(KEY_MOD, Tag.TAG_STRING)) {
			var ans = root.getString(KEY_MOD);
			for (var e : GolemDungeons.SPAWN.getAll()) {
				if (e.getID().getNamespace().equals(ans)) {
					return ans;
				}
			}
		}
		return GolemDungeons.MODID;
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

	public void fillItemCategory(CreativeModeTabModifier x) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		set.add(GolemDungeons.MODID);
		for (var e : GolemDungeons.SPAWN.getAll()) {
			set.add(e.getID().getNamespace());
		}
		for (var id : set) {
			var stack = getDefaultInstance();
			stack.getOrCreateTag().putString(KEY_MOD, id);
			x.accept(stack);
		}
	}

}
