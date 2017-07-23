package panda.gotwood.block;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class BlockBambooFence extends BlockFence implements IOreDictionaryEntry {
	private final WoodMaterial wood;

	protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
		new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D),
		new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D),
		new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D),
		new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D),
		new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D),
		new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D),
		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D),
		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D),
		new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D),
		new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D),
		new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D),
		new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D),
		new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D),
		new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D),
		new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
	};

	public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);

	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);

	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);

	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);

	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);

	public BlockBambooFence(WoodMaterial wood) {
		super(Material.WOOD, BlockPlanks.EnumType.OAK.getMapColor());
		this.setSoundType(SoundType.WOOD);
		this.wood = wood;
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		Blocks.FIRE.setFireInfo(this, 5, 20);
		this.setRegistryName(wood.getName() + "_fence");
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
		IBlockState actualstate = state.getActualState(worldIn, pos);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);

		if (actualstate.getValue(NORTH)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
		}

		if (actualstate.getValue(EAST)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
		}

		if (actualstate.getValue(SOUTH)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		}

		if (actualstate.getValue(WEST)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		}
	}

	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOXES[getBoundingBoxIdx(this.getActualState(state, source, pos))];
	}

	private static int getBoundingBoxIdx(IBlockState state) {
		int i = 0;

		if (state.getValue(NORTH)) {
			i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
		}

		if (state.getValue(EAST)) {
			i |= 1 << EnumFacing.EAST.getHorizontalIndex();
		}

		if (state.getValue(SOUTH)) {
			i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
		}

		if (state.getValue(WEST)) {
			i |= 1 << EnumFacing.WEST.getHorizontalIndex();
		}

		return i;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "fence" + this.wood.getCapitalizedName();
	}
}
