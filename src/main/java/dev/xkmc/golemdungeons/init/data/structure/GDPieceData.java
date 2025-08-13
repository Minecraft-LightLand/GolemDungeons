package dev.xkmc.golemdungeons.init.data.structure;

import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

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

	public GDPieceData with(ProcessorRule... processors) {
		this.processors.add(new RuleProcessor(List.of(processors)));
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

}
