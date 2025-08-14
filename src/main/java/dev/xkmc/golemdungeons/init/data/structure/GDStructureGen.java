package dev.xkmc.golemdungeons.init.data.structure;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLootGen;
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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class GDStructureGen extends DatapackBuiltinEntriesProvider {

	public record GDStructure(
			ResourceLocation id, int spacing, int separation,
			List<StructureProcessor> processors,
			List<GDPieceData> pools,
			Map<MobCategory, StructureSpawnOverride> spawns,
			Optional<Heightmap.Types> heightMap, HeightProvider height,
			GenerationStep.Decoration step, TerrainAdjustment beard) {

		public TagKey<Biome> biomes() {
			return TagKey.create(Registries.BIOME, id.withPrefix("has_structure/"));
		}

		public TagKey<Structure> asTag() {
			return TagKey.create(Registries.STRUCTURE, id);
		}

		public ResourceKey<Structure> asKey() {
			return ResourceKey.create(Registries.STRUCTURE, id);
		}

	}

	public static final GDStructure ABANDONED_FACTORY, PIGLIN_FACTORY, SCULK_FACTORY;

	private static final List<GDStructure> STRUCTURES = new ArrayList<>();

	static {
		SCULK_FACTORY = new GDStructure(GolemDungeons.loc("sculk_factory"), 48, 16,
				List.of(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)),
				List.of(singlePiece("root").custom(ElementFactory.thickBox(22))
								.loot(injectData(Blocks.CHEST, GDLootGen.SCULK_ROOT)),
						singlePiece("heart").custom(ElementFactory.thickBox(22)).loot(
								injectData(Blocks.BARREL, GDLootGen.SCULK_HEART_BARREL),
								injectData(Blocks.CHEST, GDLootGen.SCULK_HEART_CHEST)),
						singlePiece("crane").custom(ElementFactory.fatBox(2))
								.loot(injectData(Blocks.CHEST, GDLootGen.SCULK_CRANE)),
						singlePiece("crane_arm"),
						singlePiece("treasure").custom(ElementFactory.thickBox(22)),
						singlePiece("left_plaza").custom(ElementFactory.thickBox(22)),
						singlePiece("right_plaza").custom(ElementFactory.thickBox(22)),
						singlePiece("right_open").custom(ElementFactory.thickBox(22)),
						singlePiece("sculpture").custom(ElementFactory.thickBox(22)),
						singlePiece("pile").custom(ElementFactory.thickBox(22)),
						singlePiece("side_path").custom(ElementFactory.thickBox(22)),
						folder("pillar", "pillar_front_1", "pillar_front_2", "pillar_front_3",
								"pillar_back_1", "pillar_back_2")
								.custom(ElementFactory.fatBox(2))
				), Map.of(),
				Optional.empty(), ConstantHeight.of(VerticalAnchor.absolute(-27)),
				GenerationStep.Decoration.UNDERGROUND_DECORATION, TerrainAdjustment.BEARD_BOX
		);


		PIGLIN_FACTORY = new GDStructure(GolemDungeons.loc("piglin_factory"), 48, 16,
				List.of(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)),
				List.of(singlePiece("piglin_factory").loot(injectData(Blocks.CHEST, GDLootGen.PIGLIN_CHEST))
				), Map.of(),
				Optional.empty(), ConstantHeight.of(VerticalAnchor.absolute(33)),
				GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX
		);

		ABANDONED_FACTORY = new GDStructure(GolemDungeons.loc("abandoned_factory"), 64, 24,
				List.of(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)),
				List.of(singlePiece("top").custom(ElementFactory.thin()),
						singlePiece("garden").custom(ElementFactory.thin()),
						singlePiece("pillar").custom(ElementFactory.thin()),
						singlePiece("elevator").custom(ElementFactory.bury()),
						singlePiece("elevator_base").custom(ElementFactory.bury()),
						singlePiece("room").loot(
								injectData(Blocks.CHEST, GDLootGen.FACTORY_ROOM_CHEST),
								injectData(Blocks.BARREL, GDLootGen.FACTORY_ROOM_BARREL),
								injectData(Blocks.SUSPICIOUS_GRAVEL, GDLootGen.FACTORY_ROOM_GRAVEL)
						).custom(ElementFactory.bury()),
						singlePiece("stair").custom(ElementFactory.bury()),
						singlePiece("cross").custom(ElementFactory.bury()),
						singlePiece("hall").custom(ElementFactory.bury()),
						singlePiece("tway").custom(ElementFactory.bury()),
						folder("end", "end0", "end1", "end2", "end3", "end4").loot(
								injectData(Blocks.CHEST, GDLootGen.FACTORY_END_CHEST),
								injectData(Blocks.SUSPICIOUS_GRAVEL, GDLootGen.FACTORY_END_GRAVEL)
						).custom(ElementFactory.bury())
				), Map.of(),
				Optional.of(Heightmap.Types.WORLD_SURFACE_WG), ConstantHeight.of(VerticalAnchor.absolute(0)),
				GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN
		);

		STRUCTURES.add(SCULK_FACTORY);
		STRUCTURES.add(PIGLIN_FACTORY);
		STRUCTURES.add(ABANDONED_FACTORY);
	}

	private static GDPieceData singlePiece(String str) {
		return new GDPieceData(str, List.of(str));
	}

	private static GDPieceData folder(String str, String... items) {
		for (int i = 0; i < items.length; i++) {
			items[i] = str + "/" + items[i];
		}
		return new GDPieceData(str, List.of(items));
	}

	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.PROCESSOR_LIST, ctx -> {
				for (var e : STRUCTURES) {
					ctx.register(ResourceKey.create(Registries.PROCESSOR_LIST, e.id),
							new StructureProcessorList(e.processors()));
					for (var pool : e.pools) {
						pool.buildProcessors(ctx,e.id, e.processors());
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
						pool.buildTemplate(ctx, e.id(), base, empty);
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
							pool, Optional.empty(), 7, e.height(), false, e.heightMap(), 80)
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

}
