package dev.xkmc.golemdungeons.content.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;
import java.util.function.Supplier;

public class StructureEye extends Item {

	private final TagKey<Structure> tag;
	private final Supplier<Component> comp;

	public StructureEye(Properties properties, TagKey<Structure> tag, Supplier<Component> comp) {
		super(properties);
		this.tag = tag;
		this.comp = comp;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
		list.add(comp.get());
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		player.startUsingItem(hand);
		if (level instanceof ServerLevel sl) {
			BlockPos blockpos = sl.findNearestMapStructure(tag, player.blockPosition(), 100, false);
			if (blockpos != null) {
				EyeOfEnder e = new EyeOfEnder(level, player.getX(), player.getY(0.5D), player.getZ());
				e.setItem(stack);
				e.signalTo(blockpos);
				//e.surviveAfterDeath = true;
				level.gameEvent(GameEvent.PROJECTILE_SHOOT, e.position(), GameEvent.Context.of(player));
				level.addFreshEntity(e);
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
				level.levelEvent(null, 1003, player.blockPosition(), 0);
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				player.swing(hand, true);
				return InteractionResultHolder.success(stack);
			}
		}

		return InteractionResultHolder.consume(stack);
	}

}
