package dev.xkmc.golemdungeons.content.equipments;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDConfig;
import dev.xkmc.golemdungeons.init.data.GDDamageTypes;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.entity.targeting.TargetManager;
import dev.xkmc.modulargolems.content.item.equipments.ExtraAttackGolemWeapon;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkScythe extends MetalGolemWeaponItem implements ExtraAttackGolemWeapon {

	public SculkScythe(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		int perc = (int) Math.round(100 * GDConfig.COMMON.sculkScytheDamage.get());
		list.add(GDLang.SCULK_SCYTHE_ATK.get(Component.literal(perc + "%")));
		super.appendHoverText(stack, level, list, flag);
	}

	@Override
	public boolean repeatAttack(MetalGolemEntity self, Entity target, float dmg, boolean prev) {
		if (target instanceof LivingEntity le && self.level() instanceof ServerLevel sl) {
			double x = target.getX();
			double y = target.getY() + target.getBbHeight() / 2;
			double z = target.getZ();
			sl.sendParticles(ParticleTypes.SONIC_BOOM, x, y, z, 1, 0, 0, 0, 0);
			long time = sl.getGameTime();
			float damage = dmg * GDConfig.COMMON.sculkScytheDamage.get().floatValue();
			GeneralEventHandler.schedulePersistent(() -> {
				if (sl.getGameTime() >= time + 8) {
					var source = self.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
							.getHolderOrThrow(GDDamageTypes.ECHO);
					var aabb = new AABB(x, y, z, x, y, z).inflate(2);
					var list = sl.getEntitiesOfClass(LivingEntity.class, aabb);
					for (var e : list) {
						if (TargetManager.predicateTarget(self, e) != null) {
							e.hurt(new DamageSource(source, self), damage);
						}
					}
					return true;
				}
				return false;
			});
		}
		return true;
	}

	public static ItemEntry<SculkScythe> buildItem(String id, VanillaGolemWeaponMaterial material) {
		return GolemDungeons.REGISTRATE.item(id, p -> new SculkScythe(material.modify(p.stacksTo(1)),
						0, material.getDamage() * 0.05, 1, 2))
				.model((ctx, pvd) -> pvd.getBuilder(ctx.getName())
						.guiLight(BlockModel.GuiLight.FRONT)
						.customLoader(SeparateTransformsModelBuilder::begin)
						.base(new ItemModelBuilder(null, pvd.existingFileHelper)
								.parent(new ModelFile.UncheckedModelFile(ModularGolems.loc(GolemWeaponType.AXE.model)))
								.texture("layer0", pvd.modLoc("item/equipments/" + ctx.getName())))
						.perspective(ItemDisplayContext.GUI, new ItemModelBuilder(null, pvd.existingFileHelper)
								.parent(pvd.getExistingFile(pvd.mcLoc("item/generated")))
								.texture("layer0", pvd.modLoc("item/equipments/" + ctx.getName() + "_icon")))
				).defaultLang().register();
	}

}
