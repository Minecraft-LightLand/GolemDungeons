package dev.xkmc.golemdungeons.content.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.List;

public class GDRuleProcessor extends StructureProcessor {

	public static final Codec<GDRuleProcessor> CODEC = ProcessorRule.CODEC.listOf().fieldOf("rules")
			.xmap(GDRuleProcessor::new, e -> e.rules).codec();

	private final ImmutableList<ProcessorRule> rules;

	public GDRuleProcessor(List<? extends ProcessorRule> p_74296_) {
		this.rules = ImmutableList.copyOf(p_74296_);
	}

	@Nullable
	public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos absPos, BlockPos relPos, StructureTemplate.StructureBlockInfo rel, StructureTemplate.StructureBlockInfo old, StructurePlaceSettings settings) {
		RandomSource rand = RandomSource.create(Mth.getSeed(old.pos()));
		BlockState state = level.getBlockState(old.pos());

		for (ProcessorRule r : this.rules) {
			if (r.test(old.state(), state, rel.pos(), old.pos(), relPos, rand)) {
				return new StructureTemplate.StructureBlockInfo(old.pos(), old.state(), r.getOutputTag(rand, old.nbt()));
			}
		}

		return old;
	}

	protected StructureProcessorType<?> getType() {
		return GDWorldGen.RULE.get();
	}

}