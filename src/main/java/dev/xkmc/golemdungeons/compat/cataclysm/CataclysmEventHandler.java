package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.blockentities.Boss_Respawn_Spawner_Block_Entity;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CataclysmEventHandler {

	@SubscribeEvent
	public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
		if (event.getLevel().getBlockEntity(event.getPos()) instanceof Boss_Respawn_Spawner_Block_Entity be &&
				event.getItemStack().is(GDItems.TRIAL_MEDAL.get())) {
			var requirement = be.getItem(0);
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

}
