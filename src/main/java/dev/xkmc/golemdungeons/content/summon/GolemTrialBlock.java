package dev.xkmc.golemdungeons.content.summon;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import dev.xkmc.golemdungeons.init.reg.GDItems;
import dev.xkmc.l2modularblock.DelegateBlock;
import dev.xkmc.l2modularblock.impl.BlockEntityBlockMethodImpl;
import dev.xkmc.l2modularblock.mult.CreateBlockStateBlockMethod;
import dev.xkmc.l2modularblock.mult.DefaultStateBlockMethod;
import dev.xkmc.l2modularblock.type.BlockMethod;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Locale;

public class GolemTrialBlock implements CreateBlockStateBlockMethod, DefaultStateBlockMethod {

	public static final BlockMethod TE = new BlockEntityBlockMethodImpl<>(GDItems.BE_SPAWNER, GolemTrialBlockEntity.class);

	public static final EnumProperty<State> STATE = EnumProperty.create("state", State.class);

	public enum State implements StringRepresentable {
		IDLE, ACTIVATED, VICTORY, CHARGING;

		@Override
		public String getSerializedName() {
			return name().toLowerCase(Locale.ROOT);
		}

	}

	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STATE);
	}

	@Override
	public BlockState getDefaultState(BlockState state) {
		return state.setValue(STATE, State.IDLE);
	}

	public static void buildStates(DataGenContext<Block, DelegateBlock> ctx, RegistrateBlockstateProvider pvd) {
		pvd.horizontalBlock(ctx.get(), state -> {
			var s = state.getValue(STATE);
			String suffix = s == State.IDLE ? "" : ("_" + s.getSerializedName());
			return pvd.models().cubeAll("block/" + ctx.getName() + suffix, pvd.modLoc("block/" + ctx.getName() + suffix));
		});
	}

}
