package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SerialClass
public class EquipmentConfig extends BaseConfig {

	@SerialClass.SerialField
	public final LinkedHashMap<EquipmentSlot, ArrayList<EquipmentEntry>> items = new LinkedHashMap<>();

	EquipmentSetInfo cache;

	@SerialClass.OnInject
	public void onInject() {
		cache = new EquipmentSetInfo(items);
	}

	public EquipmentConfig add(EquipmentSlot slot, EquipmentEntry entry) {
		items.computeIfAbsent(slot, k -> new ArrayList<>()).add(entry);
		return this;
	}

	public EquipmentConfig add(EquipmentSlot slot, int weight, ItemLike stack) {
		return add(slot, new EquipmentEntry(weight, stack.asItem()));
	}


	public EquipmentConfig add(EquipmentSlot slot, int weight, ItemLike stack, int enchantLevel) {
		return add(slot, new EquipmentEntry(weight, new ItemStack(stack.asItem()), enchantLevel));
	}

	public EquipmentConfig add(EquipmentSlot slot, int weight, ItemStack stack, int enchantLevel) {
		return add(slot, new EquipmentEntry(weight, stack, enchantLevel));
	}

	public record EquipmentEntry(int weight, ItemStack stack, int enchantLevel) {

		public EquipmentEntry(int weight, Item stack) {
			this(weight, new ItemStack(stack), 0);
		}

		public ItemStack get(RandomSource r) {
			if (stack.is(Items.FIREWORK_ROCKET)) {
				int n = enchantLevel <= 1 ? 1 : r.nextInt(enchantLevel) + 1;
				return getRocket(r, Mth.clamp(n, 1, 7));
			}
			var ans = stack.copy();
			if (stack.isEnchantable() && enchantLevel > 0) {
				EnchantmentHelper.enchantItem(r, stack, enchantLevel, true);
			}
			return ans;
		}

		public static ItemStack getRocket(RandomSource r, int n) {
			ItemStack ans = new ItemStack(Items.FIREWORK_ROCKET);
			CompoundTag tag = ans.getOrCreateTagElement("Fireworks");
			ListTag list = new ListTag();
			for (int i = 0; i < n; i++) {
				CompoundTag comp = new CompoundTag();
				comp.putIntArray("Colors", List.of(DyeColor.values()[r.nextInt(16)].getFireworkColor()));
				comp.putByte("Type", (byte) 0);
				list.add(comp);
			}
			tag.putByte("Flight", (byte) 3);
			tag.put("Explosions", list);
			return ans;
		}

	}

	public static class EquipmentSetInfo {

		private final Map<EquipmentSlot, SimpleWeightedRandomList<EquipmentEntry>> itemTable = new LinkedHashMap<>();

		private EquipmentSetInfo(LinkedHashMap<EquipmentSlot, ArrayList<EquipmentConfig.EquipmentEntry>> items) {
			for (var ent : items.entrySet()) {
				var builder = SimpleWeightedRandomList.<EquipmentEntry>builder();
				for (var e : ent.getValue())
					if (e.weight() > 0)
						builder.add(e, e.weight());
				itemTable.put(ent.getKey(), builder.build());
			}
		}

		public void apply(LivingEntity e, RandomSource r) {
			for (var slot : itemTable.entrySet()) {
				var item = slot.getValue().getRandomValue(r);
				if (item.isEmpty()) continue;
				var stack = item.get().get(r);
				e.setItemSlot(slot.getKey(), stack);
			}
		}
	}


}
