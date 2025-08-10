package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.FactoryGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class GDLootGen {

	public static final ResourceLocation FACTORY = FactoryGolemSpawn.FACTORY_ALL.withPrefix("trial_reward/");
	public static final ResourceLocation PIGLIN = PiglinGolemSpawn.PIGLIN_ALL.withPrefix("trial_reward/");
	public static final ResourceLocation SCULK = SculkGolemSpawn.SCULK_ALL.withPrefix("trial_reward/");


	public static final ResourceLocation SCULK_ROOT = GolemDungeons.loc("sculk_factory/root");
	public static final ResourceLocation SCULK_CRANE = GolemDungeons.loc("sculk_factory/crane");
	public static final ResourceLocation SCULK_HEART_CHEST = GolemDungeons.loc("sculk_factory/heart_chest");
	public static final ResourceLocation SCULK_HEART_BARREL = GolemDungeons.loc("sculk_factory/heart_barrel");

	public static final ResourceLocation PIGLIN_CHEST = GolemDungeons.loc("piglin_factory/root");

	public static void genLoot(RegistrateLootTableProvider pvd) {
		{
			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(FACTORY,
					LootTable.lootTable()
							.withPool(LootPool.lootPool()
									.add(LootItem.lootTableItem(GolemItems.SIZE_UPGRADE))
									.add(LootItem.lootTableItem(GolemItems.RECYCLE))
									.add(LootItem.lootTableItem(GolemItems.GOLD))
									.add(LootItem.lootTableItem(GolemItems.SWIM))
									.add(LootItem.lootTableItem(GolemItems.DIAMOND))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.GUNPOWDER, 16, 24))
									.add(LootTableTemplate.getItem(Items.COAL, 16, 24))
									.add(LootTableTemplate.getItem(Items.ENDER_PEARL, 8, 12))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.REDSTONE, 16, 24))
									.add(LootTableTemplate.getItem(Items.LAPIS_LAZULI, 16, 24))
									.add(LootTableTemplate.getItem(Items.AMETHYST_SHARD, 16, 24))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.EMERALD, 4, 8))
									.add(LootTableTemplate.getItem(Items.DIAMOND, 3, 6))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.GOLD_INGOT, 12, 16))
									.add(LootTableTemplate.getItem(Items.GOLDEN_APPLE, 4, 6))
									.add(LootTableTemplate.getItem(Items.ENCHANTED_GOLDEN_APPLE, 1))
									.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
							)
			));
		}

		{
			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(PIGLIN,
					LootTable.lootTable()
							.withPool(LootPool.lootPool()
									.add(LootItem.lootTableItem(GolemItems.RECYCLE))
									.add(LootItem.lootTableItem(GolemItems.NETHERITE))
									.add(LootItem.lootTableItem(GolemItems.ENCHANTED_GOLD))
									.add(LootItem.lootTableItem(GolemItems.ADD_DIAMOND))
									.add(LootItem.lootTableItem(GolemItems.ADD_NETHERITE))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.GHAST_TEAR, 12, 16))
									.add(LootTableTemplate.getItem(Items.BLAZE_ROD, 12, 16))
									.add(LootTableTemplate.getItem(Items.ENDER_PEARL, 12, 16))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.OBSIDIAN, 16, 24))
									.add(LootTableTemplate.getItem(Items.CRYING_OBSIDIAN, 16, 24))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 3, 6))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.WITHER_SKELETON_SKULL, 4, 8))
									.add(LootTableTemplate.getItem(Items.NETHER_STAR, 1))
									.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
							)
			));
		}

		{
			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootItem.lootTableItem(GolemItems.RECYCLE))
									.add(LootItem.lootTableItem(GolemItems.ENCHANTED_GOLD))
									.add(LootItem.lootTableItem(GolemItems.ADD_DIAMOND))
									.add(LootItem.lootTableItem(GolemItems.CAULDRON))
									.add(LootItem.lootTableItem(GolemItems.TALENTED))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ECHO_SHARD, 12, 18))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ENCHANTED_GOLDEN_APPLE, 3, 6))
									.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
							)
			));
		}

		{
			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK_ROOT,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootTableTemplate.getItem(GolemItems.GOLEM_TEMPLATE.get(), 4, 8))
									.add(LootTableTemplate.getItem(GolemItems.EMPTY_UPGRADE.get(), 4, 8))
							)
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootTableTemplate.getItem(Items.COAL, 8, 16))
									.add(LootTableTemplate.getItem(Items.AMETHYST_SHARD, 8, 16))
									.add(LootTableTemplate.getItem(Items.LAPIS_LAZULI, 8, 16))
									.add(LootTableTemplate.getItem(Items.REDSTONE, 8, 16))
							)
							.withPool(LootTableTemplate.getPool(2, 1)
									.add(LootTableTemplate.getItem(Items.IRON_INGOT, 4, 8))
									.add(LootTableTemplate.getItem(Items.COPPER_INGOT, 4, 8))
									.add(LootTableTemplate.getItem(Items.GOLD_INGOT, 2, 4))
									.add(LootTableTemplate.getItem(Items.DIAMOND, 1, 3))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ECHO_SHARD, 1, 3))
							)
			));

			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK_CRANE,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(2, 1)
									.add(LootTableTemplate.getItem(Items.ANVIL, 4, 8))
									.add(LootTableTemplate.getItem(Items.IRON_BLOCK, 4, 8))
									.add(LootTableTemplate.getItem(Items.IRON_INGOT, 16, 32))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ECHO_SHARD, 1, 3))
							)
			));

			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK_HEART_BARREL,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootTableTemplate.getItem(GolemItems.GOLEM_TEMPLATE.get(), 4, 8))
									.add(LootTableTemplate.getItem(GolemItems.EMPTY_UPGRADE.get(), 4, 8))
							)
							.withPool(LootTableTemplate.getPool(4, 1)
									.add(LootTableTemplate.getItem(Items.AMETHYST_SHARD, 8, 16))
									.add(LootTableTemplate.getItem(Items.LAPIS_LAZULI, 8, 16))
									.add(LootTableTemplate.getItem(Items.REDSTONE, 8, 16))
									.add(LootTableTemplate.getItem(Items.IRON_INGOT, 8, 16))
									.add(LootTableTemplate.getItem(Items.GOLD_INGOT, 2, 4))
									.add(LootTableTemplate.getItem(Items.DIAMOND, 1, 3))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ECHO_SHARD, 4, 6))
							)
			));

			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK_HEART_CHEST,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(4, 1)
									.add(LootTableTemplate.getItem(Items.AMETHYST_SHARD, 8, 16))
									.add(LootTableTemplate.getItem(Items.IRON_INGOT, 4, 8))
									.add(LootTableTemplate.getItem(Items.DIAMOND, 1, 3))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.ECHO_SHARD, 6, 8))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
							)
			));
		}

		{

			pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(PIGLIN_CHEST,
					LootTable.lootTable()
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootTableTemplate.getItem(GolemItems.GOLEM_TEMPLATE.get(), 4, 8))
									.add(LootTableTemplate.getItem(GolemItems.EMPTY_UPGRADE.get(), 4, 8))
							)
							.withPool(LootTableTemplate.getPool(3, 1)
									.add(LootTableTemplate.getItem(Items.NETHERITE_INGOT, 1, 2))
									.add(LootTableTemplate.getItem(Items.NETHERITE_SCRAP, 4, 8))
									.add(LootTableTemplate.getItem(Items.ANCIENT_DEBRIS, 4, 8))
									.add(LootTableTemplate.getItem(Items.GOLD_INGOT, 16, 24))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1, 3))
							)
							.withPool(LootPool.lootPool()
									.add(LootTableTemplate.getItem(Items.NETHERITE_INGOT, 1))
									.add(LootTableTemplate.getItem(Items.ENCHANTED_GOLDEN_APPLE, 1))
									.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
							)
			));
		}
	}

}
