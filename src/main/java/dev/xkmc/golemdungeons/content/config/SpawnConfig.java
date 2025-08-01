package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2library.serial.config.CollectType;
import dev.xkmc.l2library.serial.config.ConfigCollect;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.l2serial.util.Wrappers;
import dev.xkmc.modulargolems.content.config.GolemMaterial;
import dev.xkmc.modulargolems.content.core.GolemType;
import dev.xkmc.modulargolems.content.entity.common.AbstractGolemEntity;
import dev.xkmc.modulargolems.content.entity.hostile.HostileFaction;
import dev.xkmc.modulargolems.content.entity.hostile.HostileGolemRegistry;
import dev.xkmc.modulargolems.content.item.upgrade.IUpgradeItem;
import dev.xkmc.modulargolems.content.item.upgrade.UpgradeItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SerialClass
public class SpawnConfig extends BaseConfig {

	@SerialClass.SerialField
	public ResourceLocation faction = HostileGolemRegistry.DEFAULT.id;

	@ConfigCollect(CollectType.MAP_OVERWRITE)
	@SerialClass.SerialField
	public final Map<EntityType<?>, GolemTypeEntry> types = new LinkedHashMap<>();

	@ConfigCollect(CollectType.MAP_OVERWRITE)
	@SerialClass.SerialField
	public final Map<ResourceLocation, GolemMaterialEntry> materials = new LinkedHashMap<>();

	@ConfigCollect(CollectType.COLLECT)
	@SerialClass.SerialField
	public final List<EquipmentGroup> equipments = new ArrayList<>();

	@ConfigCollect(CollectType.COLLECT)
	@SerialClass.SerialField
	public final List<UpgradeEntry> upgrades = new ArrayList<>();

	private SimpleWeightedRandomList<GolemTypeInfo> typeTable;
	private SimpleWeightedRandomList<ResourceLocation> matTable;
	private SimpleWeightedRandomList<UpgradeEntry> upTable;
	private List<EquipmentGroupInfo> equipmentTable;

	@SerialClass.OnInject
	public void onInject() {
		var typeList = SimpleWeightedRandomList.<GolemTypeInfo>builder();
		for (var ent : types.entrySet()) {
			typeList.add(new GolemTypeInfo(ent.getKey(), ent.getValue()), ent.getValue().weight());
		}
		typeTable = typeList.build();

		var matList = SimpleWeightedRandomList.<ResourceLocation>builder();
		for (var ent : materials.entrySet()) {
			matList.add(ent.getKey(), ent.getValue().weight());
		}
		matTable = matList.build();

		var upList = SimpleWeightedRandomList.<UpgradeEntry>builder();
		for (var e : upgrades) {
			upList.add(e, e.weight());
		}
		upTable = upList.build();

		equipmentTable = new ArrayList<>();
		for (var e : equipments) {
			equipmentTable.add(new EquipmentGroupInfo(e));
		}
	}

	public SpawnConfig() {
	}

	public SpawnConfig(HostileFaction faction) {
		this.faction = faction.id;
	}

	@Nullable
	public LivingEntity summon(ServerLevel sl) {
		var r = sl.getRandom();
		var type = typeTable.getRandomValue(r);
		if (type.isEmpty()) return null;
		var golemType = GolemType.getGolemType(Wrappers.cast(type.get().type));
		if (golemType == null) return null;
		var golem = createGolem(sl, golemType, r);
		if (golem == null) return null;
		attachEquipments(golem, r);
		if (!type.get().entry.mounts.isEmpty() && r.nextFloat() < type.get().entry.mountChance) {
			var mount = type.get().mountTable.getRandomValue(r);
			if (!mount.isEmpty()) {
				var mountType = mount.get();
				var mountGolem = GolemType.getGolemType(Wrappers.cast(mountType));
				Entity mountEntity;
				if (mountGolem != null) {
					mountEntity = createGolem(sl, mountGolem, r);
				} else {
					mountEntity = mountType.create(sl);
				}
				if (mountEntity instanceof LivingEntity le) {
					attachEquipments(le, r);
					if (golem.startRiding(le)) {
						sl.addFreshEntityWithPassengers(le);
						return le;
					}
				}
			}
		}
		sl.addFreshEntity(golem);
		return golem;
	}

	@Nullable
	private AbstractGolemEntity<?, ?> createGolem(ServerLevel sl, GolemType<?, ?> golem, RandomSource r) {
		var ups = new ArrayList<IUpgradeItem>();
		var mats = new ArrayList<GolemMaterial>();
		int max = golem.values().length;
		for (var p : golem.values()) {
			var mat = matTable.getRandomValue(r);
			if (mat.isEmpty()) return null;
			mats.add(p.toItem().parseMaterial(mat.get()));
			for (var b : materials.get(mat.get()).bonus) {
				if (r.nextFloat() < b.chance && ups.size() < max && b.upgrade instanceof IUpgradeItem item)
					ups.add(item);
			}
		}
		if (ups.size() < max) {
			var up = upTable.getRandomValue(r);
			if (up.isPresent() && up.get().upgrade instanceof IUpgradeItem item)
				ups.add(item);
		}
		var e = golem.create(sl);
		var fac = HostileGolemRegistry.getFaction(faction);
		var uuid = (fac == null ? HostileGolemRegistry.DEFAULT : fac).uuid;
		e.onCreate(mats, ups, uuid);
		return e;
	}

	private void attachEquipments(LivingEntity e, RandomSource r) {
		for (var ent : equipmentTable) {
			if (ent.group.type != e.getType()) continue;
			var set = ent.setTable.getRandomValue(r);
			if (set.isEmpty()) continue;
			for (var slot : set.get().itemTable.entrySet()) {
				var item = slot.getValue().getRandomValue(r);
				if (item.isEmpty()) continue;
				var stack = item.get().get(r);
				e.setItemSlot(slot.getKey(), stack);
			}
		}
	}

	public record UpgradeBonus(Item upgrade, double chance) {
	}

	public record UpgradeEntry(int weight, Item upgrade) {
	}

	public record MountEntry(int weight, EntityType<?> mount) {
	}

	public record GolemTypeEntry(
			int weight, double mountChance,
			ArrayList<UpgradeBonus> bonus,
			ArrayList<MountEntry> mounts
	) {

		public GolemTypeEntry(int weight, double mountChance) {
			this(weight, mountChance, new ArrayList<>(), new ArrayList<>());
		}

		public GolemTypeEntry add(UpgradeItem upgrade, double chance) {
			bonus.add(new UpgradeBonus(upgrade, chance));
			return this;
		}

		public GolemTypeEntry addMount(EntityType<?> mount, int weight) {
			mounts.add(new MountEntry(weight, mount));
			return this;
		}

	}

	public record GolemMaterialEntry(int weight, ArrayList<UpgradeBonus> bonus) {

		public GolemMaterialEntry(int weight) {
			this(weight, new ArrayList<>());
		}

		public GolemMaterialEntry add(UpgradeItem upgrade, double chance) {
			bonus.add(new UpgradeBonus(upgrade, chance));
			return this;
		}

	}

	public record EquipmentGroup(EntityType<?> type, ArrayList<EquipmentSet> sets) {

		public EquipmentGroup(EntityType<?> type) {
			this(type, new ArrayList<>());
		}

		public EquipmentGroup add(EquipmentSet set) {
			sets.add(set);
			return this;
		}

	}

	public record EquipmentSet(int weight, LinkedHashMap<EquipmentSlot, ArrayList<EquipmentEntry>> items) {

		public EquipmentSet(int weight) {
			this(weight, new LinkedHashMap<>());
		}

		public EquipmentSet add(EquipmentSlot slot, EquipmentEntry entry) {
			items.computeIfAbsent(slot, k -> new ArrayList<>()).add(entry);
			return this;
		}

		public EquipmentSet add(EquipmentSlot slot, int weight, ItemLike stack) {
			return add(slot, new EquipmentEntry(weight, stack.asItem()));
		}


		public EquipmentSet add(EquipmentSlot slot, int weight, ItemLike stack, int enchantLevel) {
			return add(slot, new EquipmentEntry(weight, new ItemStack(stack.asItem()), enchantLevel));
		}

		public EquipmentSet add(EquipmentSlot slot, int weight, ItemStack stack, int enchantLevel) {
			return add(slot, new EquipmentEntry(weight, stack, enchantLevel));
		}

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

	public SpawnConfig type(GolemType<?, ?> type, GolemTypeEntry entry) {
		types.put(type.type(), entry);
		return this;
	}

	public SpawnConfig mat(ResourceLocation mat, GolemMaterialEntry entry) {
		materials.put(mat, entry);
		return this;
	}

	public SpawnConfig equipments(EquipmentGroup entry) {
		equipments.add(entry);
		return this;
	}

	public SpawnConfig upgrade(Item upgrade, int weight) {
		upgrades.add(new UpgradeEntry(weight, upgrade));
		return this;
	}

	private static class GolemTypeInfo {

		private final EntityType<?> type;
		private final GolemTypeEntry entry;
		private final SimpleWeightedRandomList<EntityType<?>> mountTable;

		private GolemTypeInfo(EntityType<?> type, GolemTypeEntry entry) {
			this.type = type;
			this.entry = entry;
			SimpleWeightedRandomList.Builder<EntityType<?>> mountList = SimpleWeightedRandomList.builder();
			for (var e : entry.mounts)
				mountList.add(e.mount(), e.weight);
			mountTable = mountList.build();
		}
	}

	private static class EquipmentGroupInfo {

		private final EquipmentGroup group;
		private final SimpleWeightedRandomList<EquipmentSetInfo> setTable;

		private EquipmentGroupInfo(EquipmentGroup group) {
			this.group = group;
			var builder = SimpleWeightedRandomList.<EquipmentSetInfo>builder();
			for (var e : group.sets()) builder.add(new EquipmentSetInfo(e.items()), e.weight());
			setTable = builder.build();
		}
	}

	private static class EquipmentSetInfo {

		private final Map<EquipmentSlot, SimpleWeightedRandomList<EquipmentEntry>> itemTable = new LinkedHashMap<>();

		private EquipmentSetInfo(LinkedHashMap<EquipmentSlot, ArrayList<EquipmentEntry>> items) {
			for (var ent : items.entrySet()) {
				var builder = SimpleWeightedRandomList.<EquipmentEntry>builder();
				for (var e : ent.getValue()) builder.add(e, e.weight());
				itemTable.put(ent.getKey(), builder.build());
			}
		}

	}

}
