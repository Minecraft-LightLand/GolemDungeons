package dev.xkmc.golemdungeons.compat.twilightforest.data;

import dev.xkmc.golemdungeons.compat.twilightforest.TwilightFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.AbstractGolemSpawn;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFCompatRegistry;
import dev.xkmc.modulargolems.compat.materials.twilightforest.TFDispatch;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import twilightforest.init.TFItems;

public class TwilightGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_ARMOR = loc("twilight_large_armor");
	public static final ResourceLocation ITEM_LARGE_WEAPON = loc("twilight_large_weapon");
	public static final ResourceLocation ITEM_HUMANOID_ARMOR = loc("twilight_humanoid_armor");
	public static final ResourceLocation ITEM_HUMANOID_MELEE = loc("twilight_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_HUMANOID_BOW = loc("twilight_humanoid_weapon_bow");

	public static final ResourceLocation ITEM_HUMANOID_ARMOR_BETTER = loc("twilight_humanoid_armor_better");
	public static final ResourceLocation ITEM_HUMANOID_MELEE_BETTER = loc("twilight_humanoid_weapon_melee_better");

	public static final ResourceLocation LV1 = loc("twilight_lv1");
	public static final ResourceLocation LV2 = loc("twilight_lv2");
	public static final ResourceLocation ALL = loc("twilight_invasion");

	public static void add(ConfigDataProvider.Collector map) {

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

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, TFItems.KNIGHTMETAL_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.KNIGHTMETAL_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.KNIGHTMETAL_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.KNIGHTMETAL_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.IRONWOOD_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.IRONWOOD_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.IRONWOOD_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.IRONWOOD_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.STEELEAF_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.STEELEAF_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.STEELEAF_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.STEELEAF_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.ARCTIC_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.ARCTIC_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.ARCTIC_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.ARCTIC_BOOTS.get(), 30)

			);


			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR_BETTER, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, TFItems.FIERY_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.FIERY_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.FIERY_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.FIERY_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.KNIGHTMETAL_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.KNIGHTMETAL_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.KNIGHTMETAL_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.KNIGHTMETAL_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.YETI_HELMET.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.YETI_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.LEGS, 100, TFItems.YETI_LEGGINGS.get(), 30)
					.add(EquipmentSlot.FEET, 100, TFItems.YETI_BOOTS.get(), 30)

					.add(EquipmentSlot.HEAD, 100, TFItems.NAGA_CHESTPLATE.get(), 30)
					.add(EquipmentSlot.CHEST, 100, TFItems.NAGA_LEGGINGS.get(), 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, TFItems.KNIGHTMETAL_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.KNIGHTMETAL_AXE.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.IRONWOOD_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.IRONWOOD_AXE.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.STEELEAF_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.STEELEAF_AXE.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.GOLDEN_MINOTAUR_AXE.get(), 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_MELEE_BETTER, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 200, TFItems.FIERY_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.KNIGHTMETAL_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.KNIGHTMETAL_AXE.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.STEELEAF_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.STEELEAF_AXE.get(), 30)
					.add(EquipmentSlot.MAINHAND, 200, TFItems.ICE_SWORD.get(), 30)
					.add(EquipmentSlot.MAINHAND, 200, TFItems.DIAMOND_MINOTAUR_AXE.get(), 30)
					.add(EquipmentSlot.OFFHAND, 100, TFItems.KNIGHTMETAL_SHIELD.get(), 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_BOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.TRIPLE_BOW.get(), 30)
					.add(EquipmentSlot.MAINHAND, 100, TFItems.ICE_BOW.get(), 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.ARROW)
			);

		}

		{

			map.add(GolemDungeons.SPAWN, LV1, createLv1()
					.upgradeChance(1, 0.7, 0.6, 0.5)
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


			map.add(GolemDungeons.SPAWN, LV2, createLv2()
					.upgradeChance(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR_BETTER))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE_BETTER)
							.add(100, ITEM_HUMANOID_BOW))
			);

			map.add(GolemDungeons.SPAWN, ALL, createGeneric().asTrialKey(ALL)
					.upgradeChance(1, 1, 0.7, 0.7)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR)
							.add(100, ITEM_HUMANOID_ARMOR_BETTER))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE)
							.add(100, ITEM_HUMANOID_MELEE_BETTER)
							.add(200, ITEM_HUMANOID_BOW))
			);


			map.add(GolemDungeons.TRIAL, ALL, new TrialConfig()
					.setCost(100).setTriggerRange(7, -1, 3)
					.add(of(LV1, 1))
					.add(of(LV1, 4))
					.add(of(LV1, 6))
					.add(of(LV1, 4), of(LV2, 4))
					.add(of(LV2, 12))
					.add(of(LV2, 16))
			);
		}

	}

	private static SpawnConfig createLv1() {
		return new SpawnConfig(TwilightFactions.DEFAULT)
				.upgrade(TFCompatRegistry.UP_IRONWOOD.get(), 100)
				.upgrade(TFCompatRegistry.UP_STEELEAF.get(), 100)
				.upgrade(TFCompatRegistry.UP_KNIGHTMETAL.get(), 100)
				.upgrade(GolemItems.GOLD.get(), 100)
				.mat(loc("ironwood"), noArm(100))
				.mat(loc("steeleaf"), atkOnly(100))
				.mat(loc("knightmetal"), 100);
	}


	private static SpawnConfig createLv2() {
		return new SpawnConfig(TwilightFactions.DEFAULT)
				.upgrade(TFCompatRegistry.UP_IRONWOOD.get(), 100)
				.upgrade(TFCompatRegistry.UP_STEELEAF.get(), 100)
				.upgrade(TFCompatRegistry.UP_KNIGHTMETAL.get(), 100)
				.upgrade(TFCompatRegistry.UP_NAGA.get(), 100)
				.upgrade(TFCompatRegistry.UP_CARMINITE.get(), 100)
				.upgrade(TFCompatRegistry.UP_FIERY.get(), 100)
				.upgrade(GolemItems.GOLD.get(), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.get(), 100)
				.upgrade(GolemItems.ADD_DIAMOND.get(), 200)
				.upgrade(GolemItems.ADD_NETHERITE.get(), 200)
				.mat(loc("ironwood"), noArm(100))
				.mat(loc("steeleaf"), atkOnly(50))
				.mat(loc("knightmetal"), 100)
				.mat(loc("fiery"), 100);
	}

	private static SpawnConfig createGeneric() {
		return new SpawnConfig(TwilightFactions.DEFAULT)
				.upgrade(TFCompatRegistry.UP_IRONWOOD.get(), 100)
				.upgrade(TFCompatRegistry.UP_STEELEAF.get(), 100)
				.upgrade(TFCompatRegistry.UP_KNIGHTMETAL.get(), 100)
				.upgrade(TFCompatRegistry.UP_NAGA.get(), 100)
				.upgrade(TFCompatRegistry.UP_CARMINITE.get(), 100)
				.upgrade(TFCompatRegistry.UP_FIERY.get(), 100)
				.upgrade(GolemItems.GOLD.get(), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.get(), 50)
				.upgrade(GolemItems.ADD_DIAMOND.get(), 50)
				.upgrade(GolemItems.ADD_NETHERITE.get(), 50)
				.mat(loc("ironwood"), noArm(100))
				.mat(loc("steeleaf"), atkOnly(100))
				.mat(loc("knightmetal"), 100)
				.mat(loc("fiery"), 100);
	}

	private static ResourceLocation loc(String id) {
		return new ResourceLocation(TFDispatch.MODID, id);
	}

}
