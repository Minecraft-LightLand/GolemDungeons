package dev.xkmc.golemdungeons.mixin.twilightforest;

import dev.xkmc.golemdungeons.compat.twilightforest.data.TwilightGolemSpawn;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.modulargolems.content.item.card.PathRecordCard;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.init.TFDimensionSettings;
import twilightforest.world.components.structures.stronghold.StrongholdTrainingRoomComponent;
import twilightforest.world.components.structures.stronghold.StructureTFStrongholdComponent;

import java.util.List;

@Mixin(StrongholdTrainingRoomComponent.class)
public abstract class StrongholdTrainingRoomComponentMixin extends StructureTFStrongholdComponent {

	public StrongholdTrainingRoomComponentMixin(StructurePieceType type, int i, Direction facing, int x, int y, int z) {
		super(type, i, facing, x, y, z);
	}

	@Inject(method = "postProcess", at = @At("TAIL"))
	public void golemdungeons$addSpawner(WorldGenLevel world, StructureManager manager, ChunkGenerator generator, RandomSource rand, BoundingBox sbb, ChunkPos chunkPosIn, BlockPos blockPos, CallbackInfo ci) {
		if (rand.nextBoolean()) return;
		placeBlock(world, GDItems.SPAWNER.getDefaultState(), 8, 1, 9, sbb);
		var id = TFDimensionSettings.TWILIGHT_LEVEL_STEM.location();
		if (world.getBlockEntity(getWorldPos(8, 1, 9)) instanceof GolemTrialBlockEntity be) {
			be.setTrial(TwilightGolemSpawn.KNIGHT);
			var pos = getWorldPos(9, 1, 8);
			be.setSummonPos(List.of(
					new PathRecordCard.Pos(id, pos.offset(-4, 0, 0)),
					new PathRecordCard.Pos(id, pos.offset(0, 0, -4)),
					new PathRecordCard.Pos(id, pos.offset(5, 0, 0)),
					new PathRecordCard.Pos(id, pos.offset(0, 0, 5))
			));
		}
	}

}
