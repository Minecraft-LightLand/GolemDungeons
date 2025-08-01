package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.content.config.SpawnConfig;
import dev.xkmc.golemdungeons.content.faction.DungeonFaction;
import dev.xkmc.golemdungeons.content.faction.DungeonFactionRegistry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import dev.xkmc.modulargolems.init.registrate.GolemItems;
import dev.xkmc.modulargolems.init.registrate.GolemTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

public class GDConfigGen extends ConfigDataProvider {
	public GDConfigGen(DataGenerator generator) {
		super(generator, "Golem Spawn Config");
	}

	public void add(ConfigDataProvider.Collector map) {
		{
			map.add(GolemDungeons.SPAWN, GolemDungeons.loc("factory_remnant"), new SpawnConfig(DungeonFactionRegistry.REMNANT)
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
					.mat(ModularGolems.loc("copper"), new SpawnConfig.GolemMaterialEntry(50))
					.mat(ModularGolems.loc("iron"), new SpawnConfig.GolemMaterialEntry(40))
					.mat(ModularGolems.loc("gold"), new SpawnConfig.GolemMaterialEntry(10)
							.add(GolemItems.SPEED.get(), 1f))
					.upgrade(Items.AIR, 170)
					.upgrade(GolemItems.QUARTZ.asItem(), 100)
					.upgrade(GolemItems.GOLD.asItem(), 100)
					.upgrade(GolemItems.SPEED.asItem(), 100)
					.upgrade(GolemItems.DIAMOND.asItem(), 30)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, Items.AIR)
									.add(EquipmentSlot.HEAD, 20, GolemItems.GOLEMGUARD_HELMET, 20)
									.add(EquipmentSlot.CHEST, 100, Items.AIR)
									.add(EquipmentSlot.CHEST, 20, GolemItems.GOLEMGUARD_CHESTPLATE, 20)
									.add(EquipmentSlot.LEGS, 100, Items.AIR)
									.add(EquipmentSlot.LEGS, 20, GolemItems.GOLEMGUARD_SHINGUARD, 20)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.AIR)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.AXE), 20)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SWORD), 20)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SPEAR), 20)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
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
							).add(new SpawnConfig.EquipmentSet(20)
									.add(EquipmentSlot.HEAD, 50, Items.GOLDEN_HELMET, 30)
									.add(EquipmentSlot.CHEST, 50, Items.GOLDEN_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 50, Items.GOLDEN_LEGGINGS, 30)
									.add(EquipmentSlot.FEET, 50, Items.GOLDEN_BOOTS, 30)
							).add(new SpawnConfig.EquipmentSet(20)
									.add(EquipmentSlot.HEAD, 50, Items.CHAINMAIL_HELMET, 20)
									.add(EquipmentSlot.CHEST, 50, Items.CHAINMAIL_CHESTPLATE, 20)
									.add(EquipmentSlot.LEGS, 50, Items.CHAINMAIL_LEGGINGS, 20)
									.add(EquipmentSlot.FEET, 50, Items.CHAINMAIL_BOOTS, 20)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 200, Items.STONE_AXE, 10)
									.add(EquipmentSlot.MAINHAND, 200, Items.STONE_SWORD, 20)
									.add(EquipmentSlot.MAINHAND, 100, Items.STONE_PICKAXE, 10)
									.add(EquipmentSlot.MAINHAND, 100, Items.IRON_AXE, 10)
									.add(EquipmentSlot.MAINHAND, 100, Items.IRON_SWORD, 20)
									.add(EquipmentSlot.MAINHAND, 50, Items.IRON_PICKAXE, 10)
									.add(EquipmentSlot.MAINHAND, 150, Items.TRIDENT, 10)
									.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 20)
									.add(EquipmentSlot.OFFHAND, 100, Items.AIR)
							).add(new SpawnConfig.EquipmentSet(30)
									.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.ARROW)
							).add(tippedSet(50)
									.add(EquipmentSlot.MAINHAND, 100, Items.BOW, 10)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 10)
							).add(new SpawnConfig.EquipmentSet(20)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.FIREWORK_ROCKET, 7)
							)
					)

			);
		}

		{
			map.add(GolemDungeons.SPAWN, GolemDungeons.loc("illagers_creation"), new SpawnConfig(DungeonFactionRegistry.ILLAGER)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0.5)
							.addMount(EntityType.RAVAGER, 100)
					)
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(EntityType.HORSE, 100)
					)
					.mat(ModularGolems.loc("iron"), new SpawnConfig.GolemMaterialEntry(100))
					.upgrade(GolemItems.QUARTZ.asItem(), 100)
					.upgrade(GolemItems.GOLD.asItem(), 100)
					.upgrade(GolemItems.SPEED.asItem(), 100)
					.upgrade(GolemItems.DIAMOND.asItem(), 100)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, GDItems.SAMURAI_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, GDItems.SAMURAI_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, GDItems.SAMURAI_SHINGUARD, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.AXE), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SWORD), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.IRON, GolemWeaponType.SPEAR), 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 20, Items.DIAMOND_HELMET, 20)
									.add(EquipmentSlot.CHEST, 20, Items.DIAMOND_CHESTPLATE, 20)
									.add(EquipmentSlot.LEGS, 20, Items.DIAMOND_LEGGINGS, 20)
									.add(EquipmentSlot.FEET, 20, Items.DIAMOND_BOOTS, 20)
									.add(EquipmentSlot.HEAD, 100, Items.IRON_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, Items.IRON_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, Items.IRON_LEGGINGS, 30)
									.add(EquipmentSlot.FEET, 100, Items.IRON_BOOTS, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.IRON_AXE, 30)
									.add(EquipmentSlot.MAINHAND, 100, Items.IRON_SWORD, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 20)
							).add(tippedSet(50)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
							).add(new SpawnConfig.EquipmentSet(50)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.FIREWORK_ROCKET, 7)
							)
					)

			);
		}

		{
			map.add(GolemDungeons.SPAWN, GolemDungeons.loc("piglin_legacy"), new SpawnConfig(DungeonFactionRegistry.PIGLIN)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.mat(ModularGolems.loc("iron"), new SpawnConfig.GolemMaterialEntry(100))
					.mat(ModularGolems.loc("gold"), new SpawnConfig.GolemMaterialEntry(100)
							.add(GolemItems.SPEED.get(), 100))
					.mat(ModularGolems.loc("netherite"), new SpawnConfig.GolemMaterialEntry(100)
							.add(GolemItems.SPEED.get(), 100))
					.upgrade(GolemItems.QUARTZ.asItem(), 100)
					.upgrade(GolemItems.GOLD.asItem(), 100)
					.upgrade(GolemItems.DIAMOND.asItem(), 100)
					.upgrade(GolemItems.NETHERITE.asItem(), 100)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, GolemItems.BARBARICFLAMEVANGUARD_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, GolemItems.BARBARICFLAMEVANGUARD_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, GolemItems.BARBARICFLAMEVANGUARD_SHINGUARD, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.AXE), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SWORD), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.NETHERITE, GolemWeaponType.SPEAR), 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, Items.NETHERITE_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, Items.NETHERITE_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, Items.NETHERITE_LEGGINGS, 30)
									.add(EquipmentSlot.FEET, 100, Items.NETHERITE_BOOTS, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_AXE, 30)
									.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_SWORD, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
							).add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.ARROW, 7)
							)
					)
			);
		}

		{
			map.add(GolemDungeons.SPAWN, GolemDungeons.loc("sculk_infestation"), new SpawnConfig(DungeonFactionRegistry.SCULK)
					.type(GolemTypes.TYPE_GOLEM.get(), new SpawnConfig.GolemTypeEntry(50, 0))
					.type(GolemTypes.TYPE_HUMANOID.get(), new SpawnConfig.GolemTypeEntry(50, 1)
							.addMount(GolemTypes.ENTITY_DOG.get(), 100))
					.mat(ModularGolems.loc("iron"), new SpawnConfig.GolemMaterialEntry(100))
					.mat(ModularGolems.loc("sculk"), new SpawnConfig.GolemMaterialEntry(100))
					.upgrade(GolemItems.GOLD.asItem(), 100)
					.upgrade(GolemItems.DIAMOND.asItem(), 100)
					.upgrade(GolemItems.NETHERITE.asItem(), 100)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, GolemItems.WINDSPIRIT_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, GolemItems.WINDSPIRIT_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, GolemItems.WINDSPIRIT_SHINGUARD, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_GOLEM.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.AXE), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SWORD), 30)
									.add(EquipmentSlot.MAINHAND, 50, getWeapon(VanillaGolemWeaponMaterial.DIAMOND, GolemWeaponType.SPEAR), 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.HEAD, 100, Items.DIAMOND_HELMET, 30)
									.add(EquipmentSlot.CHEST, 100, Items.DIAMOND_CHESTPLATE, 30)
									.add(EquipmentSlot.LEGS, 100, Items.DIAMOND_LEGGINGS, 30)
									.add(EquipmentSlot.FEET, 100, Items.DIAMOND_BOOTS, 30)
							)
					)
					.equipments(new SpawnConfig.EquipmentGroup(GolemTypes.ENTITY_HUMANOID.get())
							.add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.DIAMOND_AXE, 30)
									.add(EquipmentSlot.MAINHAND, 100, Items.NETHERITE_SWORD, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.SHIELD, 30)
							).add(new SpawnConfig.EquipmentSet(100)
									.add(EquipmentSlot.MAINHAND, 100, Items.CROSSBOW, 30)
									.add(EquipmentSlot.OFFHAND, 100, Items.ARROW, 7)
							)
					)
			);
		}

	}

	private ItemStack getWeapon(VanillaGolemWeaponMaterial mat, GolemWeaponType type) {
		return GolemItems.METALGOLEM_WEAPON[type.ordinal()][mat.ordinal()].asStack();
	}

	public static SpawnConfig.EquipmentSet tippedSet(int weight) {
		return new SpawnConfig.EquipmentSet(weight)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.POISON), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.SLOWNESS), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.WEAKNESS), 0)
				.add(EquipmentSlot.OFFHAND, 100, tipped(Potions.HARMING), 0);
	}

	public static ItemStack tipped(Potion potion) {
		return PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), potion);
	}

}
