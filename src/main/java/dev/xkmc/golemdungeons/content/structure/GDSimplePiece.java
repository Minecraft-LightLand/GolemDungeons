package dev.xkmc.golemdungeons.content.structure;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class GDSimplePiece extends SinglePoolElement {

	public GDSimplePiece(
			ResourceLocation template,
			Holder<StructureProcessorList> list,
			StructureTemplatePool.Projection proj
	) {
		super(Either.left(template), list, proj);
	}

}
