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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public class FactoryGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_FACTORY_LARGE_ARMOR = GolemDungeons.loc("factory_large_armor");
	public static final ResourceLocation ITEM_FACTORY_LARGE_WEAPON = GolemDungeons.loc("factory_large_weapon");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_ARMOR_MISC = GolemDungeons.loc("factory_humanoid_armor_misc");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_ARMOR_CHAIN = GolemDungeons.loc("factory_humanoid_armor_chain");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_ARMOR_GOLD = GolemDungeons.loc("factory_humanoid_armor_gold");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_MELEE = GolemDungeons.loc("factory_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_BOW = GolemDungeons.loc("factory_humanoid_weapon_bow");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_TIPPED = GolemDungeons.loc("factory_humanoid_weapon_tipped_arrow");
	public static final ResourceLocation ITEM_FACTORY_HUMANOID_ROCKET = GolemDungeons.loc("factory_humanoid_weapon_rocket_crossbow");
	public static final ResourceLocation FACTORY_ALL = GolemDungeons.loc("factory_remnant");

	public static void add(ConfigDataProvider.Collector map) {

		// metal golem equipments
		{
			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.AIR)
					.add(EquipmentSlot.HEAD, 20, GolemItems.GOLEMGUARD_HELMET, 20)
					.add(EquipmentSlot.CHEST, 100, Items.AIR)
					.add(EquipmentSlot.CHEST, 20, GolemItems.GOLEMGUARD_CHESTPLATE, 20)
					.add(EquipmentSlot.LEGS, 100, Items.AIR)
					.add(EquipmentSlot.LEGS, 20, GolemItems.GOLEMGUARD_SHINGUARD, 20)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.AIR)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.AXE), 20)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SWORD), 20)
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SPEAR), 20)

			);
		}

		// humanoid armors
		{
			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_ARMOR_MISC, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.AIR)
					.add(EquipmentSlot.CHEST, 100, Items.AIR)
					.add(EquipmentSlot.LEGS, 100, Items.AIR)
					.add(EquipmentSlot.FEET, 100, Items.AIR)
					.add(EquipmentSlot.HEAD, 20, Items.DIAMOND_HELMET, 10)
					.add(EquipmentSlot.CHEST, 20, Items.DIAMOND_CHESTPLATE, 10)
					.add(EquipmentSlot.LEGS, 20, Items.DIAMOND_LEGGINGS, 10)
					.add(EquipmentSlot.FEET, 20, Items.DIAMOND_BOOTS, 10)
					.add(EquipmentSlot.HEAD, 50, Items.IRON_HELMET, 15)
					.add(EquipmentSlot.CHEST, 50, Items.IRON_CHESTPLATE, 15)
					.add(EquipmentSlot.LEGS, 50, Items.IRON_LEGGINGS, 15)
					.add(EquipmentSlot.FEET, 50, Items.IRON_BOOTS, 15)
					.add(EquipmentSlot.HEAD, 50, Items.CHAINMAIL_HELMET, 20)
					.add(EquipmentSlot.CHEST, 50, Items.CHAINMAIL_CHESTPLATE, 20)
					.add(EquipmentSlot.LEGS, 50, Items.CHAINMAIL_LEGGINGS, 20)
					.add(EquipmentSlot.FEET, 50, Items.CHAINMAIL_BOOTS, 20)
					.add(EquipmentSlot.HEAD, 50, Items.GOLDEN_HELMET, 30)
					.add(EquipmentSlot.CHEST, 50, Items.GOLDEN_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 50, Items.GOLDEN_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 50, Items.GOLDEN_BOOTS, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_ARMOR_CHAIN, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 50, Items.CHAINMAIL_HELMET, 20)
					.add(EquipmentSlot.CHEST, 50, Items.CHAINMAIL_CHESTPLATE, 20)
					.add(EquipmentSlot.LEGS, 50, Items.CHAINMAIL_LEGGINGS, 20)
					.add(EquipmentSlot.FEET, 50, Items.CHAINMAIL_BOOTS, 20)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_ARMOR_GOLD, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 50, Items.GOLDEN_HELMET, 30)
					.add(EquipmentSlot.CHEST, 50, Items.GOLDEN_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 50, Items.GOLDEN_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 50, Items.GOLDEN_BOOTS, 30)
			);

		}

		// humanoid weapons
		{
			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_MELEE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 200, Items.STONE_AXE, 10)
					.add(EquipmentSlot.MAINHAND, 200, Items.STONE_SWORD, 20)
					.add(EquipmentSlot.MAINHAND, 100, Items.STONE_PICKAXE, 10)
					.add(EquipmentSlot.MAINHAND, 100, Items.IRON_AXE, 10)
					.add(EquipmentSlot.MAINHAND, 100, Items.IRON_SWORD, 20)
					.add(EquipmentSlot.MAINHAND, 50, Items.IRON_PICKAXE, 10)
					.add(EquipmentSlot.MAINHAND, 150, Items.TRIDENT, 10)
					.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 20)
					.add(EquipmentSlot.OFFHAND, 100, Items.AIR)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_BOW, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.ARROW)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_TIPPED, new EquipmentConfig()
					.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.POISON), 0)
					.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.SLOWNESS), 0)
					.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.WEAKNESS), 0)
					.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.HARMING), 0)
					.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 10)
					.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 10)
			);

			map.add(GolemDungeons.ITEMS, ITEM_FACTORY_HUMANOID_ROCKET, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
					.add(EquipmentSlot.OFFHAND, 100, Items.FIREWORK_ROCKET, 7)
			);
		}

		map.add(GolemDungeons.SPAWN, FACTORY_ALL, new SpawnConfig(DungeonFactionRegistry.REMNANT)
				.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(30, 0)
						.add(GolemItems.SPEED.get(), 0.5f)
				)
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(40, 0.5)
						.addMount(EntityType.HORSE, 50)
						.addMount(EntityType.SKELETON_HORSE, 50)
						.addMount(GolemTypes.ENTITY_DOG.get(), 100)
				)
				.type(GolemTypes.TYPE_DOG.get(), new SpawnConfig.GolemTypeEntry(30, 0)
						.add(GolemItems.DIAMOND.get(), 0.75f)
						.add(GolemItems.SIZE_UPGRADE.get(), 0.5f)
				)
				.mat(ModularGolems.loc("copper"), 50)
				.mat(ModularGolems.loc("iron"), 40)
				.mat(ModularGolems.loc("gold"), new SpawnConfig.GolemMaterialEntry(10)
						.add(GolemItems.SPEED.get(), 1))
				.upgrade(Items.AIR, 170)
				.upgrade(GolemItems.QUARTZ.asItem(), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.SPEED.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 30)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_FACTORY_LARGE_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_FACTORY_LARGE_WEAPON))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_FACTORY_HUMANOID_ARMOR_MISC)
						.add(20, ITEM_FACTORY_HUMANOID_ARMOR_CHAIN)
						.add(20, ITEM_FACTORY_HUMANOID_ARMOR_GOLD))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_FACTORY_HUMANOID_MELEE)
						.add(30, ITEM_FACTORY_HUMANOID_BOW)
						.add(50, ITEM_FACTORY_HUMANOID_TIPPED)
						.add(20, ITEM_FACTORY_HUMANOID_ROCKET))
		);
	}

}
