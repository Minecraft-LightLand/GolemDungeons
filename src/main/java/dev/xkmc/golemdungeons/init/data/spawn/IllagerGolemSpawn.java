package dev.xkmc.golemdungeons.init.data.spawn;

import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.RaidConfig;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public class IllagerGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_ILLAGER_LARGE_ARMOR = GolemDungeons.loc("illager_large_armor");
	public static final ResourceLocation ITEM_ILLAGER_LARGE_WEAPON = GolemDungeons.loc("illager_large_weapon");
	public static final ResourceLocation ITEM_ILLAGER_HUMANOID_ARMOR = GolemDungeons.loc("illager_humanoid_armor");
	public static final ResourceLocation ITEM_ILLAGER_HUMANOID_MELEE = GolemDungeons.loc("illager_humanoid_weapon_melee");
	public static final ResourceLocation ITEM_ILLAGER_HUMANOID_TIPPED = GolemDungeons.loc("illager_humanoid_weapon_tipped_arrow");
	public static final ResourceLocation ITEM_ILLAGER_HUMANOID_ROCKET = GolemDungeons.loc("illager_humanoid_weapon_rocket_crossbow");

	public static final ResourceLocation ILLAGER_ALL = GolemDungeons.loc("illagers_creation");
	public static final ResourceLocation RAID_HUMANOID_MELEE = GolemDungeons.loc("raid_humanoid_melee");
	public static final ResourceLocation RAID_HUMANOID_RANGED = GolemDungeons.loc("raid_humanoid_ranged");
	public static final ResourceLocation RAID_LARGE = GolemDungeons.loc("raid_large");

	public static final ResourceLocation RAIDS = GolemDungeons.loc("raids");

	public static void add(ConfigDataProvider.Collector map) {
		addLarge(map);
		addHumanoid(map);
		addSpawn(map);
		addRaid(map);
	}

	private static void addLarge(ConfigDataProvider.Collector map) {
		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_LARGE_ARMOR, new EquipmentConfig()
				.add(EquipmentSlot.HEAD, 100, GDItems.SAMURAI_HELMET, 30)
				.add(EquipmentSlot.CHEST, 100, GDItems.SAMURAI_CHESTPLATE, 30)
				.add(EquipmentSlot.LEGS, 100, GDItems.SAMURAI_SHINGUARD, 30)
		);

		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_LARGE_WEAPON, new EquipmentConfig()
				.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.AXE), 30)
				.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SWORD), 30)
				.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SPEAR), 30)
		);

	}

	private static void addHumanoid(ConfigDataProvider.Collector map) {

		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_HUMANOID_ARMOR, new EquipmentConfig()
				.add(EquipmentSlot.HEAD, 20, Items.DIAMOND_HELMET, 20)
				.add(EquipmentSlot.CHEST, 20, Items.DIAMOND_CHESTPLATE, 20)
				.add(EquipmentSlot.LEGS, 20, Items.DIAMOND_LEGGINGS, 20)
				.add(EquipmentSlot.FEET, 20, Items.DIAMOND_BOOTS, 20)
				.add(EquipmentSlot.HEAD, 100, Items.IRON_HELMET, 30)
				.add(EquipmentSlot.CHEST, 100, Items.IRON_CHESTPLATE, 30)
				.add(EquipmentSlot.LEGS, 100, Items.IRON_LEGGINGS, 30)
				.add(EquipmentSlot.FEET, 100, Items.IRON_BOOTS, 30)
		);

		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_HUMANOID_MELEE, new EquipmentConfig()
				.add(EquipmentSlot.MAINHAND, 100, Items.IRON_AXE, 30)
				.add(EquipmentSlot.MAINHAND, 100, Items.IRON_SWORD, 30)
				.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
		);

		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_HUMANOID_TIPPED, new EquipmentConfig()
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.LONG_POISON), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.STRONG_SLOWNESS), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.LONG_WEAKNESS), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.STRONG_HARMING), 0)
				.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
		);

		map.add(GolemDungeons.ITEMS, ITEM_ILLAGER_HUMANOID_ROCKET, new EquipmentConfig()
				.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
				.add(EquipmentSlot.OFFHAND, 100, Items.FIREWORK_ROCKET, 10)
		);

	}

	private static void addSpawn(ConfigDataProvider.Collector map) {
		map.add(GolemDungeons.SPAWN, RAID_HUMANOID_MELEE, new SpawnConfig(DungeonFactionRegistry.ILLAGER)
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
						.addMount(EntityType.HORSE, 100)
				)
				.mat(ModularGolems.loc("iron"), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_ILLAGER_HUMANOID_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_ILLAGER_HUMANOID_MELEE))
		);

		map.add(GolemDungeons.SPAWN, RAID_HUMANOID_RANGED, new SpawnConfig(DungeonFactionRegistry.ILLAGER)
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
						.addMount(EntityType.HORSE, 100)
				)
				.mat(ModularGolems.loc("iron"), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_ILLAGER_HUMANOID_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(50, ITEM_ILLAGER_HUMANOID_TIPPED)
						.add(50, ITEM_ILLAGER_HUMANOID_ROCKET))
		);


		map.add(GolemDungeons.SPAWN, RAID_LARGE, new SpawnConfig(DungeonFactionRegistry.ILLAGER)
				.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 1)
						.addMount(EntityType.RAVAGER, 100)
				)
				.mat(ModularGolems.loc("iron"), 100)
				.upgrade(GolemItems.QUARTZ.asItem(), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_ILLAGER_LARGE_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_ILLAGER_LARGE_WEAPON))
		);

		map.add(GolemDungeons.SPAWN, ILLAGER_ALL, new SpawnConfig(DungeonFactionRegistry.ILLAGER)
				.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0.5)
						.addMount(EntityType.RAVAGER, 100)
				)
				.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0.5)
						.addMount(EntityType.HORSE, 100)
				)
				.mat(ModularGolems.loc("iron"), 100)
				.upgrade(GolemItems.QUARTZ.asItem(), 100)
				.upgrade(GolemItems.GOLD.asItem(), 100)
				.upgrade(GolemItems.SPEED.asItem(), 100)
				.upgrade(GolemItems.DIAMOND.asItem(), 100)
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_ILLAGER_LARGE_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
						.add(100, ITEM_ILLAGER_LARGE_WEAPON))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_ILLAGER_HUMANOID_ARMOR))
				.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
						.add(100, ITEM_ILLAGER_HUMANOID_MELEE)
						.add(50, ITEM_ILLAGER_HUMANOID_TIPPED)
						.add(50, ITEM_ILLAGER_HUMANOID_ROCKET))
		);
	}

	private static void addRaid(ConfigDataProvider.Collector map) {
		map.add(GolemDungeons.RAID, RAIDS, new RaidConfig().add().add().add().add()
				.add(new RaidConfig.WaveEntry(RAID_HUMANOID_RANGED, 1, 1))
				.add(new RaidConfig.WaveEntry(RAID_HUMANOID_RANGED, 1, 1),
						new RaidConfig.WaveEntry(RAID_HUMANOID_MELEE, 1, 1))
				.add(new RaidConfig.WaveEntry(RAID_HUMANOID_RANGED, 1, 1),
						new RaidConfig.WaveEntry(RAID_LARGE, 1, 1))
				.add(new RaidConfig.WaveEntry(RAID_HUMANOID_RANGED, 2, 2),
						new RaidConfig.WaveEntry(RAID_HUMANOID_MELEE, 1, 1),
						new RaidConfig.WaveEntry(RAID_LARGE, 1, 1))
		);
	}

}
