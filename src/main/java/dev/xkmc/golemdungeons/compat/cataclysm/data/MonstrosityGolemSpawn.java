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
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

public class MonstrosityGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_ARMOR = loc("monstrosity_large_armor");
	public static final ResourceLocation ITEM_LARGE = loc("monstrosity_large_weapon");
	public static final ResourceLocation ALL = loc("monstrosity_expanded");
	public static final ResourceLocation LARGE = loc("monstrosity_large");

	public static void add(ConfigDataProvider.Collector map) {

		// equipments
		{
			map.add(GolemDungeons.ITEMS, ITEM_LARGE_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 100, CataCompatRegistry.MONSTROSITY_HELMET, 30)
					.add(EquipmentSlot.CHEST, 100, CataCompatRegistry.MONSTROSITY_CHESTPLATE, 30)
					.add(EquipmentSlot.LEGS, 100, CataCompatRegistry.MONSTROSITY_SHINGUARD, 30)
			);

			map.add(GolemDungeons.ITEMS, ITEM_LARGE, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.AXE), 30)
					.add(EquipmentSlot.MAINHAND, 50, ModItems.INFERNAL_FORGE.get())
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

			map.add(GolemDungeons.SPAWN, ALL, createBase().asTrialKey(ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE))
			);
		}

		// trial
		{
			map.add(GolemDungeons.TRIAL, ALL, new TrialConfig().add(of(LARGE, 4)));
		}
	}

	private static SpawnConfig createBase() {
		return new SpawnConfig(CataclysmFactions.MONSTROSITY)
				.mat(ModularGolems.loc("netherite"), new SpawnConfig.GolemMaterialEntry(100)
						.add(CataCompatRegistry.MONSTROSITY.get(), 1))
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

	private static ResourceLocation loc(String id) {
		return new ResourceLocation(CataDispatch.MODID, id);
	}

}
