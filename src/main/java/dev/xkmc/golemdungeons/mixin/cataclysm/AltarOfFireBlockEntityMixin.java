package dev.xkmc.golemdungeons.mixin.cataclysm;

import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.github.L_Ender.cataclysm.blockentities.AltarOfFire_Block_Entity")
public abstract class AltarOfFireBlockEntityMixin extends BlockEntity {

	@Shadow
	public int summoningticks;

	@Shadow public abstract ItemStack getItem(int index);

	public AltarOfFireBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		super(type, pos, blockState);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lcom/github/L_Ender/cataclysm/blockentities/AltarOfFire_Block_Entity;Sphereparticle(FF)V"))
	private void golemdungeons$checkSummonGolems(CallbackInfo ci) {
		if (summoningticks == 120 && level instanceof ServerLevel sl) {
			ItemStack stack = getItem(0);
			if (GDItems.MEDAL.get(stack) != null) {
				stack.remove(GDItems.MEDAL);
				CataclysmFactions.ENTRY_IGNIS.summon(sl, getBlockPos());
			}
		}
	}

}
