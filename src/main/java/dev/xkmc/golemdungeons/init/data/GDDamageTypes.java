package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GDDamageTypes extends DamageTypeAndTagsGen {

	public static final ResourceKey<DamageType> FLAME = createDamage("flame_attack");
	public static final ResourceKey<DamageType> ECHO = createDamage("echo_attack");

	public GDDamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> pvd, ExistingFileHelper helper) {
		super(output, pvd, helper, GolemDungeons.MODID);
		new DamageTypeHolder(FLAME, new DamageType("in_fire", 0.1f))
				.add(DamageTypeTags.IS_FIRE, L2DamageTypes.MAGIC,
						DamageTypeTags.BYPASSES_COOLDOWN, DamageTypeTags.BYPASSES_ARMOR);

		new DamageTypeHolder(ECHO, new DamageType("sonic_boom", 0.1f))
				.add(L2DamageTypes.MAGIC, DamageTypeTags.BYPASSES_COOLDOWN, DamageTypeTags.BYPASSES_ARMOR)
				.add(L2DamageTypes.BYPASS_MAGIC);
	}

	private static ResourceKey<DamageType> createDamage(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, GolemDungeons.loc(id));
	}

}
