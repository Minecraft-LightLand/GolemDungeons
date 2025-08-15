package dev.xkmc.golemdungeons.init.data.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import dev.xkmc.golemdungeons.init.reg.GDWorldGen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ModLootItem extends LootPoolSingletonContainer {
	final ResourceLocation item;

	ModLootItem(ResourceLocation item, int weight, int quality, LootItemCondition[] cond, LootItemFunction[] func) {
		super(weight, quality, cond, func);
		this.item = item;
	}

	public LootPoolEntryType getType() {
		return GDWorldGen.ITEM.get();
	}

	public void createItemStack(Consumer<ItemStack> cons, LootContext ctx) {
		var ans = ForgeRegistries.ITEMS.getValue(item);
		if (ans == null || ans == Items.AIR) return;
		cons.accept(new ItemStack(ans));
	}

	public static LootPoolSingletonContainer.Builder<?> lootTableItem(ItemLike item) {
		return simpleBuilder((weight, quality, cond, func) ->
				new ModLootItem(item.asItem().builtInRegistryHolder().unwrapKey().orElseThrow().location(),
						weight, quality, cond, func));
	}

	public static LootPoolSingletonContainer.Builder<?> lootTableItem(ItemLike item, int min, int max) {
		return lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
	}

	public static class Serializer extends LootPoolSingletonContainer.Serializer<ModLootItem> {
		public void serializeCustom(JsonObject data, ModLootItem ins, JsonSerializationContext ctx) {
			super.serializeCustom(data, ins, ctx);
			data.addProperty("name", ins.item.toString());
		}

		protected ModLootItem deserialize(JsonObject p_79594_, JsonDeserializationContext p_79595_, int weight, int quality, LootItemCondition[] cond, LootItemFunction[] func) {
			var item = ResourceLocation.tryParse(GsonHelper.getAsString(p_79594_, "name"));
			return new ModLootItem(item, weight, quality, cond, func);
		}
	}
}