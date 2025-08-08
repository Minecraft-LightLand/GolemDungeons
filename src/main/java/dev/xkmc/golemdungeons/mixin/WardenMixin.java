package dev.xkmc.golemdungeons.mixin;

import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public class WardenMixin {

	@Inject(method = "canTargetEntity", cancellable = true, at = @At("HEAD"))
	private void golemdungeons$wardenTarget(Entity e, CallbackInfoReturnable<Boolean> cir) {
		if (e instanceof AbstractGolemEntity<?, ?> golem) {
			if (HostileGolemRegistry.tryGetFaction(golem).orElse(null) == DungeonFactionRegistry.SCULK) {
				cir.setReturnValue(false);
			}
		}
	}

}
