package dev.xkmc.golemdungeons.compat.cataclysm.data;

import com.github.L_Ender.cataclysm.init.ModItems;
import dev.xkmc.golemdungeons.compat.cataclysm.CataclysmFactions;
import dev.xkmc.golemdungeons.content.config.EquipmentConfig;
import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.config.TrialConfig;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.spawn.AbstractGolemSpawn;
import dev.xkmc.golemdungeons.init.data.spawn.SculkGolemSpawn;
import dev.xkmc.l2core.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.compat.materials.cataclysm.CataDispatch;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

public class ScyllaGolemSpawn extends AbstractGolemSpawn {

	public static final ResourceLocation ITEM_WEAPON = loc("scylla_weapon");
	public static final ResourceLocation ALL = loc("heavenly_storm");
	public static final ResourceLocation LARGE = loc("scylla_large");
	public static final ResourceLocation HUMANOID = loc("scylla_humanoid");

	public static void add(ConfigDataProvider.Collector map) {

		// equipments
		{
			map.add(GolemDungeons.ITEMS, ITEM_WEAPON, new EquipmentConfig()
					.add(EquipmentSlot.MAINHAND, 50, ModItems.ASTRAPE.get())
					.add(EquipmentSlot.MAINHAND, 50, ModItems.CERAUNUS.get())
			);

		}

		// waves
		{

			map.add(GolemDungeons.SPAWN, LARGE, createBase()
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, SculkGolemSpawn.ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_WEAPON))
			);

			map.add(GolemDungeons.SPAWN, HUMANOID, createBase()
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, SculkGolemSpawn.ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_WEAPON))
			);

			map.add(GolemDungeons.SPAWN, ALL, createBase().asTrialKey(ALL)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, SculkGolemSpawn.ITEM_LARGE_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(100, ITEM_WEAPON))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, SculkGolemSpawn.ITEM_HUMANOID_ARMOR))
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(100, ITEM_WEAPON))
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
		return new SpawnConfig(CataclysmFactions.SCYLLA)
				.mat(loc("storm"), 100)
				.upgrade(GolemItems.ENCHANTED_GOLD.asItem(), 100)
				.upgrade(GolemItems.NETHERITE.asItem(), 100)
				.upgradeChance(1, 1, 0.7, 0.7);
	}

	private static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(CataDispatch.MODID, id);
	}

}
