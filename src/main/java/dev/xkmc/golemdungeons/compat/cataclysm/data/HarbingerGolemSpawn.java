package dev.xkmc.golemdungeons.compat.cataclysm.data;

import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.AbstractGolemSpawn;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataCompatRegistry;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;

public class HarbingerGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_ARMOR = loc("witherite_large_armor");
	public static final ResourceLocation ITEM_HUMANOID_ARMOR = loc("witherite_humanoid_armor");
	public static final ResourceLocation ITEM_SHREDDER = loc("witherite_shredder");
	public static final ResourceLocation ITEM_CANNON = loc("witherite_cannon");
	public static final ResourceLocation ITEM_LASER = loc("witherite_laser");
	public static final ResourceLocation ITEM_LARGE = loc("witherite_large_weapon");

	public static final ResourceLocation ALL = loc("harbingers_revenge");

	public static final ResourceLocation LARGE = loc("witherite_large");
	public static final ResourceLocation HUMANOID_SHREDDER = loc("witherite_humanoid_shredder");
	public static final ResourceLocation HUMANOID_LASER = loc("witherite_humanoid_laser");
	public static final ResourceLocation HUMANOID_CANNON = loc("witherite_humanoid_cannon");

	public static void add(ConfigDataProvider.Collector map) {

		// equipments
		{
			map.add(GolemDungeons.ITEMS, ITEM_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, CataCompatRegistry.HARBINGER_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, CataCompatRegistry.HARBINGER_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, CataCompatRegistry.HARBINGER_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_SHREDDER, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.MEAT_SHREDDER.get())
			);

			map.add(GolemDungeons.ITEMS, ITEM_CANNON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.WITHER_ASSULT_SHOULDER_WEAPON.get())
			);

			map.add(GolemDungeons.ITEMS, ITEM_LASER, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.LASER_GATLING.get())
			);

			map.add(GolemDungeons.ITEMS, ITEM_LARGE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.OFFHAND, 50, ModItems.VOID_ASSULT_SHOULDER_WEAPON.get())
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, Items.NETHERITE_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, Items.NETHERITE_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, Items.NETHERITE_LEGGINGS, 30)
					.add(EquipmentSlot.FEET, 100, Items.NETHERITE_BOOTS, 30)
			);

		}

		// waves
		{

			map.add(GolemDungeons.SPAWN, LARGE, createBase()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID_SHREDDER, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_SHREDDER))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID_CANNON, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_CANNON))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID_LASER, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_LASER))
			);

			map.add(GolemDungeons.SPAWN, ALL, createBase().asTrialKey(ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_SHREDDER)
							.add(100, ITEM_CANNON)
							.add(100, ITEM_LASER))
			);
		}

		// trial
		{
			map.add(GolemDungeons.TRIAL, ALL, new TrialConfig()
					.add(of(LARGE, 1), of(HUMANOID_SHREDDER, 1), of(HUMANOID_CANNON, 1), of(HUMANOID_LASER, 1))
			);
		}
	}

	private static SpawnConfig createBase() {
		return new SpawnConfig(CataclysmFactions.HARBINGER)
				.mat(loc( "witherite"), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

	private static ResourceLocation loc(String id) {
		return new ResourceLocation(CataDispatch.MODID, id);
	}

}
