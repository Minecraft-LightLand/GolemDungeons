package dev.xkmc.golemdungeons.content.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class GDCustomPiece extends SinglePoolElement {

	public static final Codec<GDCustomPiece> CODEC = RecordCodecBuilder.create((i) -> i.group(
			templateCodec(),
			processorsCodec(),
			projectionCodec(),
			TerrainAdjustment.CODEC.fieldOf("terrain_adjustment").forGetter(e -> e.adj),
			Codec.INT.fieldOf("horizontal_expansion").forGetter(e -> e.xzMargin)
	).apply(i, GDCustomPiece::new));


	private final TerrainAdjustment adj;
	private final int xzMargin;

	protected GDCustomPiece(
			Either<ResourceLocation, StructureTemplate> id,
			Holder<StructureProcessorList> list,
			StructureTemplatePool.Projection proj,
			TerrainAdjustment adj, int xz) {
		super(id, list, proj);
		this.adj = adj;
		this.xzMargin = xz;
	}

	public GDCustomPiece(
			ResourceLocation template,
			Holder<StructureProcessorList> list,
			StructureTemplatePool.Projection proj,
			TerrainAdjustment adj, int xz
	) {
		this(Either.left(template), list, proj, adj, xz);
	}

	public StructurePoolElementType<?> getType() {
		return GDWorldGen.CUSTOM.get();
	}

	public BoundingBox getBeardifierBox(StructurePiece piece) {
		var box = piece.getBoundingBox();
		return new BoundingBox(
				box.minX() - xzMargin, box.minY(), box.minZ() - xzMargin,
				box.maxX() + xzMargin, box.maxY(), box.maxZ() + xzMargin
		);
	}

	public TerrainAdjustment getTerrainAdjustment() {
		return adj;
	}

}
