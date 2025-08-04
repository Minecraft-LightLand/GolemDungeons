package dev.xkmc.golemdungeons.mixin;

import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity")
public class HarbingerMixin {

	@Inject(method = "lambda$static$0", at = @At("HEAD"), cancellable = true)
	private static void golemdungeons$teamHarbinger(LivingEntity instance, CallbackInfoReturnable<Boolean> cir) {
		if (instance instanceof AbstractGolemEntity<?, ?> golem) {
			if (golem.isHostile() && HostileGolemRegistry.getFaction(golem.getOwnerUUID()) == CataclysmFactions.HARBINGER) {
				cir.setReturnValue(false);
			}
		}
	}

}
