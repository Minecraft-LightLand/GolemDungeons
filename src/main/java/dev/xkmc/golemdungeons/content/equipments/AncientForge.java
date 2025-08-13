package dev.xkmc.golemdungeons.content.equipments;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.golemdungeons.init.data.GDLang;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import dev.xkmc.modulargolems.content.entity.metalgolem.MetalGolemEntity;
import dev.xkmc.modulargolems.content.item.equipments.MetalGolemWeaponItem;
import dev.xkmc.modulargolems.init.ModularGolems;
import dev.xkmc.modulargolems.init.material.GolemWeaponType;
import dev.xkmc.modulargolems.init.material.VanillaGolemWeaponMaterial;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.model.generators.ModelFile;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AncientForge extends MetalGolemWeaponItem implements GolemCustomSourceWeapon {

	public AncientForge(Properties properties, int attackDamage, double percentAttack, float range, float sweep) {
		super(properties, attackDamage, percentAttack, range, sweep);
	}

	@Override
	public void onModifySource(CreateSourceEvent event, MetalGolemEntity golem, ItemStack stack) {
		event.enable(DefaultDamageState.BYPASS_ARMOR);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		list.add(GDLang.ANCIENT_FORGE_ATK.get());
		super.appendHoverText(stack, level, list, flag);
	}

	public static ItemEntry<AncientForge> buildItem(String id, VanillaGolemWeaponMaterial material) {
		return GolemDungeons.REGISTRATE.item(id, p -> new AncientForge(material.modify(p.stacksTo(1)),
						2, 0, 1, 0))
				.model((ctx, pvd) -> pvd.getBuilder(ctx.getName())
						.parent(new ModelFile.UncheckedModelFile(ModularGolems.loc(GolemWeaponType.SPEAR.model)))
						.texture("layer0", pvd.modLoc("item/equipments/" + ctx.getName())))
				.defaultLang().register();
	}

}
