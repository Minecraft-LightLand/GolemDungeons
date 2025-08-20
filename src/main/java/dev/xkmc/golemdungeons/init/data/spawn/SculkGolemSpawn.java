package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.loot.GDLootGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.golemdungeons.init.reg.GDModifiers;
import dev.xkmc.l2core.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public class SculkGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_ARMOR = GolemDungeons.loc("sculk_large_armor");
	public static final ResourceLocation ITEM_LARGE_WEAPON = GolemDungeons.loc("sculk_large_weapon");
	public static final ResourceLocation ITEM_LARGE_WEAPON_BETTER = GolemDungeons.loc("sculk_large_weapon_better");
	public static final ResourceLocation ITEM_HUMANOID_ARMOR = GolemDungeons.loc("sculk_humanoid_armor");
	public static final ResourceLocation ITEM_HUMANOID_MELEE = GolemDungeons.loc("sculk_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_HUMANOID_BOW = GolemDungeons.loc("sculk_humanoid_weapon_crossbow");

	public static final ResourceLocation SCULK_ALL = GolemDungeons.loc("sculk_infestation");
	public static final ResourceLocation LARGE = GolemDungeons.loc("sculk_large_1");
	public static final ResourceLocation HUMANOID_MELEE = GolemDungeons.loc("sculk_humanoid_melee");
	public static final ResourceLocation HUMANOID_RANGED = GolemDungeons.loc("sculk_humanoid_ranged");
	public static final ResourceLocation SCULK_BETTER = GolemDungeons.loc("sculk_full");

	public static void add(ConfigDataProvider.Collector map) {

		// large golem
		{
			map.add(GolemDungeons.ITEMS, ITEM_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, GolemItems.WINDSPIRIT_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, GolemItems.WINDSPIRIT_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, GolemItems.WINDSPIRIT_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SWORD), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SPEAR), 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_LARGE_WEAPON_BETTER, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SWORD), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SPEAR), 30)
					.add(EquipmentSlot.MAINHAND, 50, GDItems.SCULK_SCYTHE.get(), 30, 1)
			);

		}

		// humanoid
		{

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.DIAMOND_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, Items.DIAMOND_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, Items.DIAMOND_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 100, Items.DIAMOND_BOOTS, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.DIAMOND_AXE, 30)
					.add(EquipmentSlot.MAINHAND, 100, Items.DIAMOND_SWORD, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_BOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 30)
					.add(EquipmentSlot.OFFHAND, 50, Items.ARROW)
					.add(EquipmentSlot.OFFHAND, 50, tipped(Potions.STRONG_HARMING))
			);

		}

		// waves
		{

			map.add(GolemDungeons.SPAWN, LARGE, createBase()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID_MELEE, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE))
			);


			map.add(GolemDungeons.SPAWN, HUMANOID_RANGED, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_BOW))
			);

			map.add(GolemDungeons.SPAWN, SCULK_ALL, createBase().asTrialKey(SCULK_ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE)
							.add(100, ITEM_HUMANOID_BOW))
			);

			map.add(GolemDungeons.SPAWN, SCULK_BETTER, createBetter()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 2)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON_BETTER))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE)
							.add(100, ITEM_HUMANOID_BOW))
			);
		}

		// trial
		{
			map.add(GolemDungeons.TRIAL, SCULK_ALL, new TrialConfig().setReward(GDLootGen.SCULK)
					.setCost(400).setTriggerRange(16, -2, 8)
					.add(of(HUMANOID_MELEE, 1))
					.add(of(LARGE, 1),
							of(HUMANOID_RANGED, 1))
					.add(of(LARGE, 1),
							of(HUMANOID_MELEE, 1),
							of(HUMANOID_RANGED, 2))
					.add(of(LARGE, 2),
							of(HUMANOID_MELEE, 2),
							of(HUMANOID_RANGED, 2))
					.add(of(LARGE, 3),
							of(HUMANOID_MELEE, 2),
							of(HUMANOID_RANGED, 2),
							of(SCULK_ALL, 3),
							of(SCULK_BETTER, 2))
					.add(of(SCULK_BETTER, 16))
			);
		}
	}

	private static SpawnConfig createBase() {
		return new SpawnConfig(DungeonFactionRegistry.SCULK)
				.mat(ModularGolems.loc("iron"), 100)
				.mat(ModularGolems.loc("sculk"), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

	private static SpawnConfig createBetter() {
		return new SpawnConfig(DungeonFactionRegistry.SCULK)
				.mat(ModularGolems.loc("sculk"), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgrade(GDModifiers.ITEM_RESISTANCE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}


}
