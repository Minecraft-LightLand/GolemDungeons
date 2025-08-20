package dev.xkmc.golemdungeons.content.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

public class GDSimplePiece extends SinglePoolElement {

	public static final MapCodec<GDSimplePiece> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
			templateCodec(),
			processorsCodec(),
			projectionCodec()
	).apply(i, GDSimplePiece::new));

	protected GDSimplePiece(Either<ResourceLocation, StructureTemplate> id, Holder<StructureProcessorList> proc, StructureTemplatePool.Projection projection) {
		super(id, proc, projection, Optional.of(LiquidSettings.IGNORE_WATERLOGGING));
	}

	public GDSimplePiece(
			ResourceLocation template,
			Holder<StructureProcessorList> list,
			StructureTemplatePool.Projection proj
	) {
		this(Either.left(template), list, proj);
	}

	public StructurePoolElementType<?> getType() {
		return GDWorldGen.SIMPLE.get();
	}

}
