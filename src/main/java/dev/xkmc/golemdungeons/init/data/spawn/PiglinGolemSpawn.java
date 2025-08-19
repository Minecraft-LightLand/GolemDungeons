package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.loot.GDLootGen;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;

public class PiglinGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_ARMOR = GolemDungeons.loc("piglin_large_armor");
	public static final ResourceLocation ITEM_LARGE_WEAPON = GolemDungeons.loc("piglin_large_weapon");
	public static final ResourceLocation ITEM_HUMANOID_ARMOR = GolemDungeons.loc("piglin_humanoid_armor");
	public static final ResourceLocation ITEM_HUMANOID_MELEE = GolemDungeons.loc("piglin_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_HUMANOID_CROSSBOW = GolemDungeons.loc("piglin_humanoid_weapon_crossbow");

	public static final ResourceLocation PIGLIN_ALL = GolemDungeons.loc("piglin_legacy");

	public static final ResourceLocation LARGE = GolemDungeons.loc("piglin_large_1");
	public static final ResourceLocation HUMANOID_MELEE = GolemDungeons.loc("piglin_humanoid_melee");
	public static final ResourceLocation HUMANOID_RANGED = GolemDungeons.loc("piglin_humanoid_ranged");

	public static void add(ConfigDataProvider.Collector map) {

		// large golem items
		{
			map.add(GolemDungeons.ITEMS, ITEM_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, GolemItems.BARBARICFLAMEVANGUARD_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, GolemItems.BARBARICFLAMEVANGUARD_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, GolemItems.BARBARICFLAMEVANGUARD_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SWORD), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SPEAR), 30)
					.add(EquipmentSlot.MAINHAND, 50, GDItems.FLAME_SWORD.get(), 30, 2)
			);

		}

		// humanoid items
		{

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.NETHERITE_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, Items.NETHERITE_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, Items.NETHERITE_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 100, Items.NETHERITE_BOOTS, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_AXE, 30)
					.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_SWORD, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_CROSSBOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.ARROW, 0)
			);

		}

		//waves
		{
			map.add(GolemDungeons.SPAWN, LARGE, createBase()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
			);


			map.add(GolemDungeons.SPAWN, HUMANOID_MELEE, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID_RANGED, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_CROSSBOW))
			);

			map.add(GolemDungeons.SPAWN, PIGLIN_ALL, createBase().asTrialKey(PIGLIN_ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_MELEE)
							.add(100, ITEM_HUMANOID_CROSSBOW))
			);
		}

		// trial
		{
			map.add(GolemDungeons.TRIAL, PIGLIN_ALL, new TrialConfig().setReward(GDLootGen.PIGLIN)
					.setCost(400).setTriggerRange(10, -6, 6)
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
							of(PIGLIN_ALL, 5))
					.add(of(LARGE, 3),
							of(PIGLIN_ALL, 2),
							of(HUMANOID_RANGED, 2),
							of(PIGLIN_ALL, 3),
							of(HUMANOID_MELEE, 2),
							of(HUMANOID_RANGED, 2))
			);
		}

	}

	private static SpawnConfig createBase() {
		return new SpawnConfig(DungeonFactionRegistry.PIGLIN)
				.mat(ModularGolems.loc("iron"), new SpawnConfig.GolemMaterialEntry(100)
						.add(GolemItems.FIRE_IMMUNE.get(), 1))
				.mat(ModularGolems.loc("gold"), noArm(100)
						.add(GolemItems.SPEED.get(), 1)
						.add(GolemItems.FIRE_IMMUNE.get(), 1))
				.mat(ModularGolems.loc("netherite"), new SpawnConfig.GolemMaterialEntry(100)
						.add(GolemItems.SPEED.get(), 1))
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

}
