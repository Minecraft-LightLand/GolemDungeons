package dev.xkmc.golemdungeons.init.data.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLootGen;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GDStructureGen extends DatapackBuiltinEntriesProvider {

	public record GDStructure(
			ResourceLocation id, int spacing, int separation,
			List<StructureProcessor> processors,
			List<GDPiece> pools,
			Map<MobCategory, StructureSpawnOverride> spawns,
			HeightProvider height,
			GenerationStep.Decoration step, TerrainAdjustment beard) {

		public TagKey<Biome> biomes() {
			return TagKey.create(Registries.BIOME, id.withPrefix("has_structure/"));
		}

	}

	public record GDPiece(String id, List<String> pool, List<StructureProcessor> processors) {

		public GDPiece with(StructureProcessor... processors) {
			this.processors.addAll(List.of(processors));
			return this;
		}

		public GDPiece with(ProcessorRule... processors) {
			this.processors.add(new RuleProcessor(List.of(processors)));
			return this;
		}

	}

	public static final GDStructure SCULK_FACTORY, PIGLIN_FACTORY;

	private static final List<GDStructure> STRUCTURES = new ArrayList<>();

	static {
		SCULK_FACTORY = new GDStructure(GolemDungeons.loc("sculk_factory"), 24, 8,
				List.of(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)),
				List.of(singlePiece("root").with(injectData(Blocks.CHEST, GDLootGen.SCULK_ROOT)),
						singlePiece("heart").with(
								injectData(Blocks.BARREL, GDLootGen.SCULK_HEART_BARREL),
								injectData(Blocks.CHEST, GDLootGen.SCULK_HEART_CHEST)),
						singlePiece("crane").with(injectData(Blocks.CHEST, GDLootGen.SCULK_CRANE)),
						singlePiece("crane_arm"),
						singlePiece("treasure"),
						singlePiece("left_plaza"),
						singlePiece("right_plaza"), singlePiece("right_open"),
						singlePiece("sculpture"), singlePiece("pile"), singlePiece("side_path"),
						folder("pillar", "pillar_front_1", "pillar_front_2", "pillar_front_3",
								"pillar_back_1", "pillar_back_2")
				), Map.of(), ConstantHeight.of(VerticalAnchor.absolute(-27)),
				GenerationStep.Decoration.UNDERGROUND_DECORATION, TerrainAdjustment.BEARD_BOX
		);


		PIGLIN_FACTORY = new GDStructure(GolemDungeons.loc("piglin_factory"), 24, 8,
				List.of(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)),
				List.of(singlePiece("piglin_factory").with(injectData(Blocks.CHEST, GDLootGen.PIGLIN_CHEST))
				), Map.of(), ConstantHeight.of(VerticalAnchor.absolute(33)),
				GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX
		);

		STRUCTURES.add(SCULK_FACTORY);
		STRUCTURES.add(PIGLIN_FACTORY);
	}

	private static GDPiece singlePiece(String str) {
		return new GDPiece(str, List.of(str), new ArrayList<>());
	}

	private static GDPiece folder(String str, String... items) {
		for (int i = 0; i < items.length; i++) {
			items[i] = str + "/" + items[i];
		}
		return new GDPiece(str, List.of(items), new ArrayList<>());
	}


	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.PROCESSOR_LIST, ctx -> {
				for (var e : STRUCTURES) {
					ctx.register(ResourceKey.create(Registries.PROCESSOR_LIST, e.id),
							new StructureProcessorList(e.processors()));
					for (var pool : e.pools) {
						var poolId = e.id().withSuffix("/" + pool.id());
						if (!pool.processors().isEmpty()) {
							var merged = new ArrayList<>(e.processors());
							merged.addAll(pool.processors());
							ctx.register(ResourceKey.create(Registries.PROCESSOR_LIST, poolId),
									new StructureProcessorList(merged));
						}
					}
				}
			})
			.add(Registries.TEMPLATE_POOL, ctx -> {
				var empty = ctx.lookup(Registries.TEMPLATE_POOL)
						.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation("empty")));
				for (var e : STRUCTURES) {
					var base = ctx.lookup(Registries.PROCESSOR_LIST)
							.getOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, e.id()));
					for (var pool : e.pools) {
						var poolId = e.id().withSuffix("/" + pool.id());
						var processors = pool.processors().isEmpty() ? base :
								ctx.lookup(Registries.PROCESSOR_LIST)
										.getOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, poolId));
						List<Pair<StructurePoolElement, Integer>> pieces = new ArrayList<>();
						for (var elem : pool.pool()) {
							pieces.add(Pair.of(new SinglePiece(e.id.withSuffix("/" + elem),
									processors, StructureTemplatePool.Projection.RIGID), 1));
						}
						ctx.register(ResourceKey.create(Registries.TEMPLATE_POOL, poolId),
								new StructureTemplatePool(empty, pieces));
					}
				}
			})
			.add(Registries.STRUCTURE, ctx -> {
				for (var e : STRUCTURES) {
					var biome = ctx.lookup(Registries.BIOME).getOrThrow(e.biomes());
					var start = e.id().withSuffix("/" + e.pools.get(0).id());
					var pool = ctx.lookup(Registries.TEMPLATE_POOL)
							.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, start));
					ctx.register(ResourceKey.create(Registries.STRUCTURE, e.id()), new JigsawStructure(
							new Structure.StructureSettings(biome, e.spawns(), e.step(), e.beard()),
							pool, 7, e.height(), false)
					);
				}
			})
			.add(Registries.STRUCTURE_SET, ctx -> {
				for (var e : STRUCTURES) {
					var str = ctx.lookup(Registries.STRUCTURE).getOrThrow(ResourceKey.create(Registries.STRUCTURE, e.id));
					ctx.register(ResourceKey.create(Registries.STRUCTURE_SET, e.id), new StructureSet(
							str, new RandomSpreadStructurePlacement(e.spacing(), e.separation(), RandomSpreadType.LINEAR, e.id.hashCode() & 0x7fffffff)));
				}
			});

	public GDStructureGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of("minecraft", GolemDungeons.MODID));
	}

	@NotNull
	public String getName() {
		return "Golem Dungeons Data";
	}

	private static ProcessorRule injectData(Block block, ResourceLocation table) {
		return new ProcessorRule(new BlockMatchTest(block), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE,
				block.defaultBlockState(), new AppendLoot(table));
	}

	private static ProcessorRule injectData(Block block, Direction dir, ResourceLocation table) {
		var state = block.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, dir);
		return new ProcessorRule(new BlockStateMatchTest(state), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE,
				state, new AppendLoot(table));
	}

	private static class SinglePiece extends SinglePoolElement {

		protected SinglePiece(ResourceLocation template, Holder<StructureProcessorList> list, StructureTemplatePool.Projection proj) {
			super(Either.left(template), list, proj);
		}

	}

}
