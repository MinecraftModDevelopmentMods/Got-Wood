package panda.gotwood.block;

import java.util.List;
import java.util.Random;

import com.mcmoddev.lib.material.IMMDObject;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import panda.gotwood.GotWood;
import panda.gotwood.registry.BlockRegistry;


public final class BlockPalmLeaves extends BlockLeaves implements IMMDObject {
	public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");

	public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");

	private final  MMDMaterial material;

	public BlockPalmLeaves(MMDMaterial material) {
		this.material = material;
		Blocks.FIRE.setFireInfo(this, 30, 60);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState();
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote && state.getValue(CHECK_DECAY) && state.getValue(DECAYABLE)) {
			int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			int r = 10;
			if (worldIn.isAreaLoaded(new BlockPos(i - r, j - r, k - r), new BlockPos(i + r, j + r, k + r))) {
				for (BlockPos blockpos : BlockPos.getAllInBox(new BlockPos(i - r, j - r, k - r), new BlockPos(i + r, j + r, k + r))) {
					if (worldIn.getBlockState(blockpos).getBlock() == BlockRegistry.palm_log) {
						return;
					}
				}
				this.destroyBlock(worldIn, pos);
			}
		}
	}

	private void destroyBlock(World worldIn, BlockPos pos) {
		this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
		worldIn.setBlockToAir(pos);
	}

	//Should never be called for apple trees
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(GotWood.MODID, material + "_seed"));
	}//TODO

	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state) {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFoliage(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf(meta > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!state.getValue(DECAYABLE)) {
			i |= 1;
		}
		if (state.getValue(CHECK_DECAY)) {
			i |= 2;
		}
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		return java.util.Arrays.asList(new ItemStack(this, 1));
	}
	
	@Override
	public MMDMaterial getMMDMaterial() {
		return this.material;
	}

}
