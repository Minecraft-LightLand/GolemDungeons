package dev.xkmc.golemdungeons.events;

import dev.xkmc.golemdungeons.content.summon.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.content.item.card.PathRecordCard;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GolemDungeons.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GolemItemPriorityHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onItemUseOnBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getItemStack().is(GolemItems.CARD_PATH.get()) &&
				event.getLevel().getBlockEntity(event.getPos()) instanceof GolemTrialBlockEntity be) {
			if (event.getEntity().getAbilities().instabuild) {
				if (!event.getLevel().isClientSide())
					be.setSummonPos(PathRecordCard.getList(event.getItemStack()));
				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.SUCCESS);
			}
		}
	}

}
