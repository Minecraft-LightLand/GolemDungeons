package dev.xkmc.golemdungeons.mixin.twilightforest;

import dev.xkmc.golemdungeons.compat.twilightforest.structure.FinalCastleModifier;
import dev.xkmc.golemdungeons.compat.twilightforest.structure.PosMapper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.world.components.structures.TFStructureComponent;
import twilightforest.world.components.structures.finalcastle.*;

import java.util.function.Predicate;

@Pseudo
@Mixin(targets = "twilightforest.world.components.structures.TFStructureComponentOld")
public abstract class FinalCastleMixin extends TFStructureComponent {

	public FinalCastleMixin(StructurePieceType piece, CompoundTag nbt) {
		super(piece, nbt);
	}

	@Inject(method = "fillWithAir", at = @At("TAIL"))
	private void golemdungeons$afterExcavation(WorldGenLevel level, BoundingBox box, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Predicate<BlockState> predicate, CallbackInfo ci) {
		var room = new BoundingBox(xMin, yMin, zMin, xMax, yMax, zMax);
		PosMapper mapper = (x, y, z) -> getWorldPos(x, y, z);
		if ((Object) getClass() == FinalCastleDungeonRoom31Component.class) {
			FinalCastleModifier.modifyDungeonRoom(level, box, room, mapper);
		}
		if ((Object) getClass() == FinalCastleLargeTowerComponent.class) {
			FinalCastleModifier.modifyArea1(level, box, room, mapper);
		}
		if ((Object) getClass() == FinalCastleBellTower21Component.class) {
			FinalCastleModifier.modifyArea2(level, box, room, mapper);
		}
		if ((Object) getClass() == FinalCastleBossGazeboComponent.class) {
			FinalCastleModifier.modifyBoss(level, box, room, mapper);
		}
		if ((Object) getClass() == FinalCastleDungeonForgeRoomComponent.class) {

		}
	}

}
