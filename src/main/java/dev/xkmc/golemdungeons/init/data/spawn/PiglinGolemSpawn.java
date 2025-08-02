package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
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

	public static final ResourceLocation ITEM_PIGLIN_LARGE_ARMOR = GolemDungeons.loc("piglin_large_armor");
	public static final ResourceLocation ITEM_PIGLIN_LARGE_WEAPON = GolemDungeons.loc("piglin_large_weapon");
	public static final ResourceLocation ITEM_PIGLIN_HUMANOID_ARMOR = GolemDungeons.loc("piglin_humanoid_armor");
	public static final ResourceLocation ITEM_PIGLIN_HUMANOID_MELEE = GolemDungeons.loc("piglin_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_PIGLIN_HUMANOID_CROSSBOW = GolemDungeons.loc("piglin_humanoid_weapon_crossbow");
	public static final ResourceLocation PIGLIN_ALL = GolemDungeons.loc("piglin_legacy");

	public static void add(ConfigDataProvider.Collector map) {

		// large golem
		{
			map.add(GolemDungeons.ITEMS, ITEM_PIGLIN_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, GolemItems.BARBARICFLAMEVANGUARD_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, GolemItems.BARBARICFLAMEVANGUARD_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, GolemItems.BARBARICFLAMEVANGUARD_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_PIGLIN_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SWORD), 30)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SPEAR), 30)
					.add(EquipmentSlot.MAINHAND, 50, GDItems.FLAME_SWORD.get(), 30, 1)
			);

		}

		// humanoid
		{

			map.add(GolemDungeons.ITEMS, ITEM_PIGLIN_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.NETHERITE_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, Items.NETHERITE_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, Items.NETHERITE_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 100, Items.NETHERITE_BOOTS, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_PIGLIN_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_AXE, 30)
					.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_SWORD, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_PIGLIN_HUMANOID_CROSSBOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.ARROW, 0)
			);

		}

		map.add(GolemDungeons.SPAWN, PIGLIN_ALL, new SpawnConfig(DungeonFactionRegistry.PIGLIN)
				.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0))
				.mat(ModularGolems.loc("iron"), 100)
				.mat(ModularGolems.loc("gold"), new SpawnConfig.GolemMaterialEntry(100)
						.add(GolemItems.SPEED.get(), 1))
				.mat(ModularGolems.loc("netherite"), new SpawnConfig.GolemMaterialEntry(100)
						.add(GolemItems.SPEED.get(), 1))
				.upgrade(GolemItems.QUARTZ.asItem(), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_PIGLIN_LARGE_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_PIGLIN_LARGE_WEAPON))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_PIGLIN_HUMANOID_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_PIGLIN_HUMANOID_MELEE)
						.add(100, ITEM_PIGLIN_HUMANOID_CROSSBOW))
		);
	}

}
