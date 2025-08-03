package dev.xkmc.golemdungeons.content.config;

import dev.xkmc.golemdungeons.init.GolemDungeons;
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
import dev.xkmc.modulargolems.content.item.golem.GolemHolder;
import dev.xkmc.modulargolems.content.item.upgrade.IUpgradeItem;
import dev.xkmc.modulargolems.content.item.upgrade.UpgradeItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

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

	@Nullable
	@SerialClass.SerialField
	public ResourceLocation targetTrial = null;

	@SerialClass.SerialField
	public double[] upgradeChance = {1d, 0.5d, 0.5d, 0.5d};

	private SimpleWeightedRandomList<GolemTypeInfo> typeTable;
	private SimpleWeightedRandomList<ResourceLocation> matTable;
	private SimpleWeightedRandomList<UpgradeEntry> upTable;
	private List<EquipmentGroupInfo> equipmentTable;

	@SerialClass.OnInject
	public void onInject() {
		var typeList = SimpleWeightedRandomList.<GolemTypeInfo>builder();
		for (var ent : types.entrySet()) {
			if (ent.getValue().weight() > 0)
				typeList.add(new GolemTypeInfo(ent.getKey(), ent.getValue()), ent.getValue().weight());
		}
		typeTable = typeList.build();

		var matList = SimpleWeightedRandomList.<ResourceLocation>builder();
		for (var ent : materials.entrySet()) {
			if (ent.getValue().weight() > 0)
				matList.add(ent.getKey(), ent.getValue().weight());
		}
		matTable = matList.build();

		var upList = SimpleWeightedRandomList.<UpgradeEntry>builder();
		for (var e : upgrades) {
			if (e.weight() > 0)
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
						return le;
					}
				}
			}
		}
		return golem;
	}

	private boolean fitsOn(
			ArrayList<GolemMaterial> mats, ArrayList<IUpgradeItem> upgrades,
			GolemHolder<?, ?> holder, UpgradeItem upgrade
	) {
		if (!upgrade.fitsOn(holder.getEntityType())) return false;
		var copy = new ArrayList<>(upgrades);
		copy.add(upgrade);
		int remaining = holder.getRemaining(mats, copy);
		if (remaining < 0) return false;
		var map = GolemMaterial.collectModifiers(mats, upgrades);
		for (var e : upgrade.get()) {
			if (map.getOrDefault(e.mod(), 0) >= e.mod().maxLevel) return false;
		}
		return true;
	}

	private ArrayList<IUpgradeItem> validate(
			ArrayList<GolemMaterial> mats, ArrayList<IUpgradeItem> upgrades,
			GolemHolder<?, ?> holder
	) {
		ArrayList<IUpgradeItem> ans = new ArrayList<>();
		for (var e : upgrades) {
			if (e instanceof UpgradeItem item && fitsOn(mats, ans, holder, item)) {
				ans.add(item);
			}
		}
		return ans;
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
		var holder = GolemType.getGolemHolder(golem);
		ups = validate(mats, ups, holder);
		for (double prob : upgradeChance) {
			if (r.nextFloat() > prob) break;
			var up = upTable.getRandomValue(r);
			if (up.isPresent() && up.get().upgrade instanceof UpgradeItem item && fitsOn(mats, ups, holder, item))
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
			var data = GolemDungeons.ITEMS.getEntry(set.get());
			if (data == null) continue;
			data.cache.apply(e, r);
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

		public EquipmentGroup add(int weight, ResourceLocation id) {
			sets.add(new EquipmentSet(weight, id));
			return this;
		}

	}

	public record EquipmentSet(int weight, ResourceLocation target) {

	}

	public SpawnConfig type(GolemType<?, ?> type, GolemTypeEntry entry) {
		types.put(type.type(), entry);
		return this;
	}

	public SpawnConfig mat(ResourceLocation mat, int weight) {
		materials.put(mat, new GolemMaterialEntry(weight));
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

	public SpawnConfig asTrialKey(ResourceLocation trial) {
		targetTrial = trial;
		return this;
	}

	public SpawnConfig upgradeChance(double... chance) {
		this.upgradeChance = chance;
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
				if (e.weight > 0)
					mountList.add(e.mount(), e.weight);
			mountTable = mountList.build();
		}
	}

	private static class EquipmentGroupInfo {

		private final EquipmentGroup group;
		private final SimpleWeightedRandomList<ResourceLocation> setTable;

		private EquipmentGroupInfo(EquipmentGroup group) {
			this.group = group;
			var builder = SimpleWeightedRandomList.<ResourceLocation>builder();
			for (var e : group.sets())
				if (e.weight > 0)
					builder.add(e.target(), e.weight());

			setTable = builder.build();
		}
	}

}
