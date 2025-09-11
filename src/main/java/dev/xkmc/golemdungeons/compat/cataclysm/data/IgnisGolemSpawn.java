package dev.xkmc.golemdungeons.compat.cataclysm.data;

import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.AbstractGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.PiglinGolemSpawn;
import dev.xkmc.l2core.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

public class IgnisGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_LARGE_WEAPON = loc("ignis_large_weapon");
	public static final ResourceLocation ITEM_HUMANOID_WEAPON = loc("ignis_humanoid_weapon");
	public static final ResourceLocation ITEM_HUMANOID_ARMOR = loc("ignis_humanoid_armor");

	public static final ResourceLocation ALL = loc("resurgent_flame");
	public static final ResourceLocation LARGE = loc("ignis_large");
	public static final ResourceLocation HUMANOID = loc("ignis_humanoid");

	public static void add(ConfigDataProvider.Collector map) {

		// equipments
		{
			map.add(GolemDungeons.ITEMS, ITEM_LARGE_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.THE_INCINERATOR.get(), 30, 0.5f)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.THE_INCINERATOR.get(), 30, 0.2f)
					.add(EquipmentSlot.OFFHAND, 50, ModItems.BULWARK_OF_THE_FLAME.get(), 30, 0.5f)
			);

			map.add(GolemDungeons.ITEMS, ITEM_HUMANOID_ARMOR, new EquipmentConfig()
					.add(EquipmentSlot.HEAD, 50, ModItems.IGNITIUM_HELMET.get(), 30, 0.2f)
					.add(EquipmentSlot.CHEST, 50, ModItems.IGNITIUM_CHESTPLATE.get(), 30, 0.2f)
					.add(EquipmentSlot.LEGS, 50, ModItems.IGNITIUM_LEGGINGS.get(), 30, 0.2f)
					.add(EquipmentSlot.FEET, 50, ModItems.IGNITIUM_BOOTS.get(), 30, 0.2f)
			);

		}

		// waves
		{

			map.add(GolemDungeons.SPAWN, LARGE, createBase()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, PiglinGolemSpawn.ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_WEAPON))
			);

			map.add(GolemDungeons.SPAWN, ALL, createBase().asTrialKey(ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, PiglinGolemSpawn.ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_LARGE_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_HUMANOID_WEAPON))
			);
		}

		// trial
		{
			map.add(GolemDungeons.TRIAL, ALL, new TrialConfig()
					.add(of(LARGE, 2), of(HUMANOID, 2))
			);
		}
	}

	private static SpawnConfig createBase() {
		return new SpawnConfig(CataclysmFactions.IGNIS)
				.mat(loc("ignitium"), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

	private static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(CataDispatch.MODID, id);
	}

}
