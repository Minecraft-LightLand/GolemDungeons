package dev.xkmc.golemdungeons.init.data.structure;

import com.mojang.datafixers.util.Pair;
import dev.xkmc.golemdungeons.content.structure.GDRuleProcessor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.List;

public class GDPieceData {
	private final String id;
	private final List<String> pool;
	private final List<StructureProcessor> processors = new ArrayList<>();
	private ElementFactory factory;

	public GDPieceData(
			String id, List<String> pool
	) {
		this.id = id;
		this.pool = pool;
		this.factory = ElementFactory.rigid();
	}

	public GDPieceData with(StructureProcessor... processors) {
		this.processors.addAll(List.of(processors));
		return this;
	}

	public GDPieceData loot(ProcessorRule... processors) {
		this.processors.add(new GDRuleProcessor(List.of(processors)));
		return this;
	}

	public GDPieceData custom(ElementFactory factory) {
		this.factory = factory;
		return this;
	}

	public String id() {
		return id;
	}

	public List<String> pool() {
		return pool;
	}

	public List<StructureProcessor> processors() {
		return processors;
	}

	public ElementFactory factory() {
		return factory;
	}

	public void buildProcessors(BootstapContext<StructureProcessorList> ctx, ResourceLocation sid, List<StructureProcessor> def) {
		var poolId = sid.withSuffix("/" + id());
		if (!processors().isEmpty()) {
			var merged = new ArrayList<>(def);
			merged.addAll(processors());
			ctx.register(ResourceKey.create(Registries.PROCESSOR_LIST, poolId),
					new StructureProcessorList(merged));
		}
	}

	public void buildTemplate(BootstapContext<StructureTemplatePool> ctx, ResourceLocation sid, Holder.Reference<StructureProcessorList> base, Holder.Reference<StructureTemplatePool> empty) {
		var poolId = sid.withSuffix("/" + id());
		var processors = processors().isEmpty() ? base :
				ctx.lookup(Registries.PROCESSOR_LIST)
						.getOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, poolId));
		List<Pair<StructurePoolElement, Integer>> pieces = new ArrayList<>();
		for (var elem : pool()) {
			pieces.add(Pair.of(factory().create(sid.withSuffix("/" + elem), processors), 1));
		}
		ctx.register(ResourceKey.create(Registries.TEMPLATE_POOL, poolId),
				new StructureTemplatePool(empty, pieces));
	}

}
