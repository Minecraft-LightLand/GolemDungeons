package dev.xkmc.golemdungeons.init.data;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.Tags;

public class GDDamageTypes extends DamageTypeAndTagsGen {

	public static final ResourceKey<DamageType> FLAME = createDamage("flame_attack");
	public static final ResourceKey<DamageType> ECHO = createDamage("echo_attack");

	public GDDamageTypes(L2Registrate reg) {
		super(reg);
		new DamageTypeHolder(FLAME, new DamageType("in_fire", 0.1f))
				.add(DamageTypeTags.IS_FIRE, Tags.DamageTypes.IS_MAGIC,
						DamageTypeTags.BYPASSES_COOLDOWN, DamageTypeTags.BYPASSES_ARMOR);

		new DamageTypeHolder(ECHO, new DamageType("sonic_boom", 0.1f))
				.add(Tags.DamageTypes.IS_MAGIC, DamageTypeTags.BYPASSES_COOLDOWN, DamageTypeTags.BYPASSES_ARMOR)
				.add(L2DamageTypes.BYPASS_MAGIC);
	}

	private static ResourceKey<DamageType> createDamage(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, GolemDungeons.loc(id));
	}

}
