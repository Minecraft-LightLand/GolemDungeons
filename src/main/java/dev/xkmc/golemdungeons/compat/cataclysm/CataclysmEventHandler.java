package dev.xkmc.golemdungeons.compat.cataclysm;

import com.github.L_Ender.cataclysm.blockentities.Boss_Respawn_Spawner_Block_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.util.GolemUtils;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class CataclysmEventHandler {

	@SubscribeEvent
	public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
		if (event.getLevel().getBlockEntity(event.getPos()) instanceof Boss_Respawn_Spawner_Block_Entity be) {
			var requirement = be.getItem(0);
			if (requirement.is(ModItems.MECH_EYE.get()) && event.getItemStack().is(Items.NETHER_STAR)) {
				if (event.getLevel() instanceof ServerLevel sl) {
					event.getItemStack().shrink(1);
					be.onHit(sl);
					summon(sl, HarbingerGolemSpawn.HARBINGER_ALL, event.getPos());
				}
				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.SUCCESS);
			}
			if (requirement.is(ModItems.MONSTROUS_EYE.get()) && event.getItemStack().is(Items.NETHER_STAR)) {
				if (event.getLevel() instanceof ServerLevel sl) {
					event.getItemStack().shrink(1);
					be.onHit(sl);
					summon(sl, MonstrosityGolemSpawn.MONSTROSITY_ALL, event.getPos());
				}
				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.SUCCESS);
			}
		}
	}

	@SubscribeEvent
	public static void entityJoinLevel(EntityJoinLevelEvent event) {
		if (event.getLevel() instanceof ServerLevel sl && event.getEntity() instanceof The_Harbinger_Entity e) {
			for (var le : sl.getEntities(EntityTypeTest.forClass(AbstractGolemEntity.class), e.getBoundingBox().inflate(8), x -> true)) {
				if (le.isHostile() && HostileGolemRegistry.getFaction(le.getOwnerUUID()) == CataclysmFactions.HARBINGER) {
					le.setLeader(e);
				}
			}
		}
		if (event.getLevel() instanceof ServerLevel sl && event.getEntity() instanceof Netherite_Monstrosity_Entity e) {
			for (var le : sl.getEntities(EntityTypeTest.forClass(AbstractGolemEntity.class), e.getBoundingBox().inflate(8), x -> true)) {
				if (le.isHostile() && HostileGolemRegistry.getFaction(le.getOwnerUUID()) == CataclysmFactions.MONSTROSITY) {
					le.setLeader(e);
				}
			}
		}
	}

	private static void summon(ServerLevel sl, ResourceLocation trial, BlockPos pos) {
		var config = GolemDungeons.TRIAL.getEntry(trial);
		if (config != null) {
			List<LivingEntity> targets = new ArrayList<>();
			for (var waves : config.list) {
				for (var entry : waves) {
					var target = GolemDungeons.SPAWN.getEntry(entry.target());
					if (target == null) continue;
					int n = entry.roll(sl.getRandom());
					for (int i = 0; i < n; i++) {
						targets.add(target.summon(sl));
					}
				}
			}
			var center = Vec3.atCenterOf(pos.above());
			int n = targets.size();
			for (int i = 0; i < n; i++) {
				var le = targets.get(i);
				le.setPos(center.add(Math.cos(Math.PI * 2 / n * i), 0, Math.sin(Math.PI * 2 / n * i)));
				GolemUtils.recursiveAdd(sl, le);
			}
		}
	}

}
