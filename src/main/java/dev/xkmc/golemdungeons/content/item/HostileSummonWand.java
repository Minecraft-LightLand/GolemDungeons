package dev.xkmc.golemdungeons.content.item;

import com.tterrag.registrate.util.CreativeModeTabModifier;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.util.GolemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

import java.util.LinkedHashSet;
import java.util.List;

public class HostileSummonWand extends Item {

	public static ItemStack of(ResourceLocation rl) {
		var ans = GDItems.SUMMON.asStack();
		GDItems.TRIAL.set(ans, rl);
		GDItems.MODID.set(ans, rl.getNamespace());
		return ans;
	}

	public static ResourceLocation getId(ItemStack stack) {
		var ans = GDItems.TRIAL.get(stack);
		if (ans != null && GolemDungeons.SPAWN.getEntry(ans) != null) return ans;
		return SummonWandSelector.getAll(getModid(stack)).get(0);

	}

	public HostileSummonWand(Properties p) {
		super(p);
	}

	public static String getModid(ItemStack stack) {
		var ans = GDItems.MODID.get(stack);
		for (var e : GolemDungeons.SPAWN.getAll()) {
			if (e.getID().getNamespace().equals(ans)) {
				return ans;
			}
		}
		return GolemDungeons.MODID;
	}

	@Override
	public Component getName(ItemStack stack) {
		var trial = GDItems.TRIAL.get(stack);
		if (trial == null)
			return super.getName(stack).copy().append(": ").append(Component.translatable("trial_selector." + getModid(stack)));
		var id = getId(stack);
		var config = GolemDungeons.SPAWN.getEntry(id);
		var target = config.targetTrial;
		if (target != null)
			return GDLang.fromTrial(target);
		return super.getName(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
		list.add(GDLang.WAND_USAGE.get());
		list.add(GDLang.WAND_MODID.get(Component.translatable("trial_selector." + getModid(stack)).withStyle(ChatFormatting.GOLD)));
		list.add(GDLang.WAND_FACTION.get(GDLang.fromTrial(getId(stack)).withStyle(ChatFormatting.LIGHT_PURPLE)));
		list.add(GDLang.WAND_BLOCK.get());
		list.add(GDLang.WAND_TRIAL.get());
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
			GDItems.MODID.set(stack, id);
			x.accept(stack);
		}
	}

}
