package dev.xkmc.golemdungeons.events;

import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.content.item.card.PathRecordCard;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = GolemDungeons.MODID, bus = EventBusSubscriber.Bus.GAME)
public class GolemItemPriorityHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onItemUseOnBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getItemStack().is(GolemItems.CARD_PATH.get()) &&
				event.getLevel().getBlockEntity(event.getPos()) instanceof GolemTrialBlockEntity be) {
			if (event.getEntity().getAbilities().instabuild) {
				if (!event.getLevel().isClientSide()) {
					var pos = PathRecordCard.getList(event.getItemStack());
					if (pos != null)
						be.setSummonPos(pos);
				}
				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.SUCCESS);
			}
		}
	}

}
