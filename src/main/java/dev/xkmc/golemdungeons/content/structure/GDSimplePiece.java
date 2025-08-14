package dev.xkmc.golemdungeons.content.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class GDSimplePiece extends SinglePoolElement {

	public static final Codec<GDSimplePiece> CODEC = RecordCodecBuilder.create((i) -> i.group(
			templateCodec(),
			processorsCodec(),
			projectionCodec()
	).apply(i, GDSimplePiece::new));

	protected GDSimplePiece(Either<ResourceLocation, StructureTemplate> id, Holder<StructureProcessorList> proc, StructureTemplatePool.Projection projection) {
		super(id, proc, projection);
	}

	public GDSimplePiece(
			ResourceLocation template,
			Holder<StructureProcessorList> list,
			StructureTemplatePool.Projection proj
	) {
		super(Either.left(template), list, proj);
	}

	@Override
	protected StructurePlaceSettings getSettings(Rotation rotation, BoundingBox box, boolean jigsaw) {
		return super.getSettings(rotation, box, jigsaw).setKeepLiquids(false);
	}

	public StructurePoolElementType<?> getType() {
		return GDWorldGen.SIMPLE.get();
	}


}
