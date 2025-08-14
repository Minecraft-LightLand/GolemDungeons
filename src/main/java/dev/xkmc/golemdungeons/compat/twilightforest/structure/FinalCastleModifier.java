package dev.xkmc.golemdungeons.compat.twilightforest.structure;

import dev.xkmc.golemdungeons.compat.twilightforest.data.TwilightGolemSpawn;
import dev.xkmc.golemdungeons.content.spawner.GolemTrialBlockEntity;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.modulargolems.content.item.card.PathRecordCard;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import twilightforest.init.TFDimensionSettings;

import java.util.List;

public class FinalCastleModifier {

	public static void modifyDungeonRoom(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map((room.minX() + room.maxX()) / 2, room.minY(), (room.minZ() + room.maxZ()) / 2);
		level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
		var id = TFDimensionSettings.TWILIGHT_LEVEL_STEM.location();
		if (level.getBlockEntity(pos) instanceof GolemTrialBlockEntity be) {
			be.setTrial(TwilightGolemSpawn.ALL);
			be.setSummonPos(List.of(
					new PathRecordCard.Pos(id, pos.offset(-6, 0, -6)),
					new PathRecordCard.Pos(id, pos.offset(6, 0, 6)),
					new PathRecordCard.Pos(id, pos.offset(-6, 0, 6)),
					new PathRecordCard.Pos(id, pos.offset(6, 0, -6)),
					new PathRecordCard.Pos(id, pos.offset(-6, 0, 0)),
					new PathRecordCard.Pos(id, pos.offset(6, 0, 0)),
					new PathRecordCard.Pos(id, pos.offset(0, 0, -6)),
					new PathRecordCard.Pos(id, pos.offset(0, 0, 6))
			));
		}
	}

	public static void modifyArea1(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map(6, 1, 6);
		//level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
	}

	public static void modifyArea2(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map(7, 9, 8);
		//level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
	}

	public static void modifyBoss(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {

	}

}
