package dev.xkmc.golemdungeons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Beardifier.class)
public class BeardifierMixin {

	@WrapOperation(method = "lambda$forStructuresInChunk$2", at = @At(value = "INVOKE",
			target = "Lit/unimi/dsi/fastutil/objects/ObjectList;add(Ljava/lang/Object;)Z", remap = false, ordinal = 1))
	private static boolean youkaishomecoming$add(
			ObjectList<Beardifier.Rigid> instance, Object obj, Operation<Boolean> original,
			@Local StructurePiece piece
	) {
		if (piece instanceof PoolElementStructurePiece poolPiece) {
			if (poolPiece.getElement() instanceof GDCustomPiece simple) {
				return original.call(instance, new Beardifier.Rigid(
						simple.getBeardifierBox(piece),
						simple.getTerrainAdjustment(),
						simple.getGroundLevelDelta()
				));
			}
		}
		return original.call(instance, obj);
	}

}
