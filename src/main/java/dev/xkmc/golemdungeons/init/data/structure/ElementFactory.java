package dev.xkmc.golemdungeons.init.data.structure;

import dev.xkmc.golemdungeons.content.structure.GDCustomPiece;
import dev.xkmc.golemdungeons.content.structure.GDSimplePiece;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public interface ElementFactory {

	static ElementFactory rigid() {
		return (rl, proc) ->
				new GDSimplePiece(rl, proc, StructureTemplatePool.Projection.RIGID);
	}

	static ElementFactory fatBox(int xz) {
		return (id, list) -> new GDCustomPiece(
				id, list, StructureTemplatePool.Projection.RIGID, TerrainAdjustment.BEARD_BOX, xz, 0);
	}

	static ElementFactory thin() {
		return (id, list) -> new GDCustomPiece(
				id, list, StructureTemplatePool.Projection.RIGID, TerrainAdjustment.BEARD_THIN, 0, 0);
	}

	static ElementFactory bury() {
		return (id, list) -> new GDCustomPiece(
				id, list, StructureTemplatePool.Projection.RIGID, TerrainAdjustment.BURY, 0, 0);
	}

	static ElementFactory thickBox(int height) {
		return (id, list) -> new GDCustomPiece(
				id, list, StructureTemplatePool.Projection.RIGID, TerrainAdjustment.BEARD_BOX, 0, height);
	}

	SinglePoolElement create(ResourceLocation id, Holder<StructureProcessorList> processors);

}
