package dev.xkmc.golemdungeons.compat.twilightforest;

import dev.xkmc.golemdungeons.init.reg.GDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class FinalCastleModifier {

	public static void modifyDungeonRoom(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map((room.minX() + room.maxX()) / 2, room.minY(), (room.minZ() + room.maxZ()) / 2);
		level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
	}

	public static void modifyArea1(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map(6, 1, 6);
		level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
	}

	public static void modifyArea2(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {
		BlockPos pos = mapper.map(7, 9, 8);
		level.setBlock(pos, GDItems.SPAWNER.getDefaultState(), 3);
	}

	public static void modifyBoss(WorldGenLevel level, BoundingBox box, BoundingBox room, PosMapper mapper) {

	}

}
