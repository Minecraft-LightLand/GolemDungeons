package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.blockentities.Boss_Respawn_Spawner_Block_Entity;
import com.github.L_Ender.cataclysm.init.ModBlocks;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class CataclysmEventHandler {

	@SubscribeEvent
	public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
		if (event.getLevel().getBlockEntity(event.getPos()) instanceof Boss_Respawn_Spawner_Block_Entity be &&
				event.getItemStack().is(GDItems.TRIAL_MEDAL.get())) {
			var requirement = be.getTheItem();
			for (var e : CataclysmFactions.BOSSES) {
				if (requirement.is(e.activator().asItem())) {
					if (event.getLevel() instanceof ServerLevel sl) {
						event.getItemStack().shrink(1);
						be.onHit(sl);
						e.summon(sl, event.getPos());
					}
					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.SUCCESS);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void entityJoinLevel(EntityJoinLevelEvent event) {
		for (var boss : CataclysmFactions.BOSSES) {
			var e = event.getEntity();
			if (event.getLevel() instanceof ServerLevel sl && boss.boss().isInstance(e) && e instanceof LivingEntity leader) {
				for (var golem : sl.getEntities(EntityTypeTest.forClass(AbstractGolemEntity.class), e.getBoundingBox().inflate(8), x -> true)) {
					if (HostileGolemRegistry.tryGetFaction(golem).orElse(null) == boss.faction()) {
						golem.setLeader(leader);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		if (stack.is(ModItems.BURNING_ASHES.get())) {
			if (GDItems.MEDAL.get(stack) != null) {
				event.getToolTip().add(GDLang.OFFERING_CATA.get(
						ModBlocks.ALTAR_OF_FIRE.get().asItem().getDefaultInstance().getHoverName(),
						Component.translatable(ModEntities.IGNIS.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)
				));
			}
		}
	}

}
