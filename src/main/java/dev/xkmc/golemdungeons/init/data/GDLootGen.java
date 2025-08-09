package dev.xkmc.golemdungeons.init.data;

import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
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

	public static void genLoot(RegistrateLootTableProvider pvd) {
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

		pvd.addLootAction(LootContextParamSets.CHEST, sub -> sub.accept(SCULK,
				LootTable.lootTable()
						.withPool(LootTableTemplate.getPool(3, 1)
								.add(LootItem.lootTableItem(GolemItems.RECYCLE))
								.add(LootItem.lootTableItem(GolemItems.ENCHANTED_GOLD))
								.add(LootItem.lootTableItem(GolemItems.ADD_DIAMOND))
								.add(LootItem.lootTableItem(GolemItems.CAULDRON))
						)
						.withPool(LootPool.lootPool()
								.add(LootItem.lootTableItem(GolemItems.TALENTED))
						)
						.withPool(LootPool.lootPool()
								.add(LootTableTemplate.getItem(Items.ENCHANTED_GOLDEN_APPLE, 3, 6))
								.add(LootTableTemplate.getItem(GDItems.TRIAL_MEDAL.get(), 1))
						)
		));
	}

}
