package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
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

	public static final ResourceLocation ITEM_SCULK_LARGE_ARMOR = GolemDungeons.loc("sculk_large_armor");
	public static final ResourceLocation ITEM_SCULK_LARGE_WEAPON = GolemDungeons.loc("sculk_large_weapon");
	public static final ResourceLocation ITEM_SCULK_HUMANOID_ARMOR = GolemDungeons.loc("sculk_humanoid_armor");
	public static final ResourceLocation ITEM_SCULK_HUMANOID_MELEE = GolemDungeons.loc("sculk_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_SCULK_HUMANOID_BOW = GolemDungeons.loc("sculk_humanoid_weapon_crossbow");
	public static final ResourceLocation SCULK_ALL = GolemDungeons.loc("sculk_infestation");

	public static void add(ConfigDataProvider.Collector map) {

		// large golem
		{
			map.add(GolemDungeons.ITEMS, ITEM_SCULK_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, GolemItems.WINDSPIRIT_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, GolemItems.WINDSPIRIT_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, GolemItems.WINDSPIRIT_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_SCULK_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SWORD), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SPEAR), 30)
			);

		}

		// humanoid
		{

			map.add(GolemDungeons.ITEMS, ITEM_SCULK_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.DIAMOND_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, Items.DIAMOND_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, Items.DIAMOND_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 100, Items.DIAMOND_BOOTS, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_SCULK_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.DIAMOND_AXE, 30)
					.add(EquipmentSlot.MAINHAND, 100, Items.DIAMOND_SWORD, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_SCULK_HUMANOID_BOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 30)
					.add(EquipmentSlot.OFFHAND, 50, Items.ARROW, 0)
					.add(EquipmentSlot.OFFHAND, 50, tipped(Potions.STRONG_HARMING), 0)
			);

		}

		map.add(GolemDungeons.SPAWN, SCULK_ALL, new SpawnConfig(DungeonFactionRegistry.SCULK)
				.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
						.addMount(GolemTypes.ENTITY_DOG.get(), 100))
				.mat(ModularGolems.loc("iron"), 100)
				.mat(ModularGolems.loc("sculk"), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_SCULK_LARGE_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_SCULK_LARGE_WEAPON))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_SCULK_HUMANOID_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_SCULK_HUMANOID_MELEE)
						.add(100, ITEM_SCULK_HUMANOID_BOW))
		);
	}


}
