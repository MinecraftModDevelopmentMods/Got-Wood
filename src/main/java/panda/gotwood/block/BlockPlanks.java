package panda.gotwood.block;

import com.mcmoddev.lib.material.IMMDObject;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;


/**
 * Metal Block
 */
public final class BlockPlanks extends Block implements IMMDObject  {
	private final MMDMaterial material;


	public BlockPlanks(MMDMaterial material) {
		super(Material.WOOD);
		this.material = material;
		this.setSoundType(SoundType.WOOD);
		this.fullBlock = true;
		this.lightOpacity = 255;
		this.translucent = false;

		this.blockHardness = this.material.getBlockHardness();
		this.blockResistance = this.material.getBlastResistance();
		this.setHarvestLevel("axe", this.material.getRequiredHarvestLevel());
		this.setDefaultState(this.blockState.getBaseState());
		Blocks.FIRE.setFireInfo(this, 5, 20);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.WOOD;
	}

	@Override
	public boolean isFullBlock(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isPassable(final IBlockAccess world, final BlockPos pos) {
		return false;
	}

	@Override
	public boolean isReplaceable(final IBlockAccess w, final BlockPos p) {
		return false;
	}

	@Override
	public boolean isNormalCube(final IBlockState bs, final IBlockAccess w, final BlockPos coord) {
		return this.isNormalCube(bs);
	}

	@Override
	public MMDMaterial getMMDMaterial() {
		return this.material;
	}
}
