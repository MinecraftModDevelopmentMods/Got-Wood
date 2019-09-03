package panda.gotwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.registry.ItemRegistry;
import panda.gotwood.util.IFireDrops;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public final class BlockBambooLog extends Block implements IOreDictionaryEntry, IGrowable, IFireDrops {

    public static final PropertyBool LEAVES = PropertyBool.create("leaves");

    public BlockBambooLog(WoodMaterial wood) {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LEAVES, false));
        this.blockHardness = wood.getPlankBlockHardness();
        this.blockResistance = wood.getBlastResistance();
        this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
        this.setRegistryName(wood.getName() + "_log");

    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state == this.getDefaultState() ? new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D) : new AxisAlignedBB(0D, 0D, 0D, 1D, 1.0D, 1D);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState == this.getDefaultState() ? new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D) : new AxisAlignedBB(0D, 0D, 0D, 1D, 1.0D, 1D);
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new java.util.ArrayList<>();
        ret.add(new ItemStack(ItemRegistry.bamboo_pole));
        return ret;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return side != EnumFacing.DOWN || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos frompos) {
        if (world.getBlockState(pos.north()).getBlock() == BlockRegistry.bamboo_leaves || world.getBlockState(pos.west()).getBlock() == BlockRegistry.bamboo_leaves || world.getBlockState(pos.east()).getBlock() == BlockRegistry.bamboo_leaves || world.getBlockState(pos.south()).getBlock() == BlockRegistry.bamboo_leaves) {
            world.setBlockState(pos, this.getDefaultState().withProperty(LEAVES, true), 2);
        } else {
            world.setBlockState(pos, this.getDefaultState().withProperty(LEAVES, false), 2);
        }
        this.checkAndDropBlock(world, pos, state);

    }

    protected final void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
        boolean drop = world.getBlockState(pos.down()).getBlock() != this && !world.getBlockState(pos.down()).isNormalCube();
        if (drop && !world.isRemote) {
            world.destroyBlock(pos, true);
            breakBlock(world, pos, state);
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        int k = pos.getX();
        int l = pos.getY();
        int i1 = pos.getZ();
        if (worldIn.isAreaLoaded(new BlockPos(k - 2, l - 2, i1 - 2), new BlockPos(k + 2, l + 2, i1 + 2))) {
            for (int j1 = -1; j1 <= 1; ++j1) {
                for (int k1 = -1; k1 <= 1; ++k1) {
                    for (int l1 = -1; l1 <= 1; ++l1) {
                        BlockPos blockpos = pos.add(j1, k1, l1);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);
                        if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    }

    @Override
    public String getOreDictionaryName() {
        return "log" + WoodMaterials.bamboo.getCapitalizedName();
    }

    @Override
    public boolean hasFireDrops() {
        return true;
    }

    @Override
    public List<ItemStack> addFireDrops(List<ItemStack> drops, Random random) {
        if (random.nextBoolean()) {
            drops.add(new ItemStack(ItemRegistry.ash));
        } else {
            drops.add(new ItemStack(ItemRegistry.bamboo_charcoal));
        }
        return drops;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LEAVES, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LEAVES) ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LEAVES);
    }
}
